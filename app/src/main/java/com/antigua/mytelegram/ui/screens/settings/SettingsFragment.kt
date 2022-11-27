package com.antigua.mytelegram.ui.screens.settings

import android.app.Activity.RESULT_OK
import android.content.Intent
import android.util.Log
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import com.antigua.mytelegram.R
import com.antigua.mytelegram.database.*
import com.antigua.mytelegram.ui.screens.BaseFragment
import com.antigua.mytelegram.utilits.*
import com.antigua.mytelegram.utilits.AppConstants.APP_ACTIVITY
import com.theartofdev.edmodo.cropper.CropImage
import com.theartofdev.edmodo.cropper.CropImageView
import kotlinx.android.synthetic.main.fragment_settings.*

class SettingsFragment :  BaseFragment(R.layout.fragment_settings) {

    override fun onResume() {
        super.onResume()
        setHasOptionsMenu(true)
        initFields()
    }

    private fun initFields() {
        settings_bio.text = USER.bio
        settings_full_name.text = USER.fullname
        settings_phone_number.text = USER.phone
        settings_status.text = USER.state
        settings_username.text = USER.username
        settings_btn_change_username.setOnClickListener {
            replaceFragment(ChangeUsernameFragment())
        }
        settings_btn_change_bio.setOnClickListener {
            replaceFragment(ChangeBioFragment())
        }
        settings_change_photo.setOnClickListener {
            changePhotoUser()
        }
        settings_user_photo.downloadAndSetImage(USER.photoUrl)
    }

    private fun changePhotoUser() {
        /* Изменение фото пользователя */
        CropImage.activity()
            .setAspectRatio(1,1)
            .setRequestedSize(250,250)
            .setCropShape(CropImageView.CropShape.OVAL)
            .start(APP_ACTIVITY,this)
    }



    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        activity?.menuInflater?.inflate(R.menu.settings_action_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        /* Слушатель выбора пунктов выпадающего меню */
        when(item.itemId){
            R.id.settings_menu_exit -> {
                AppStates.updateState(AppStates.OFFLINE)
                AUTH.signOut()
                restartActivity()
            }
            R.id.settings_menu_change_name ->{
                replaceFragment(ChangeNameFragment())
            }
        }
        return  true
    }

    @Deprecated("Deprecated in Java")
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE
            && resultCode == RESULT_OK && data != null){
            Log.d("MyLog","CropImage -> RESULT_OK")
            val uri = CropImage.getActivityResult(data).uri
            val path = REF_STORAGE_ROOT.child(FOLDER_PROFILE_IMAGE)
                .child(CURRENT_UID)
            Log.d("MyLog","path  -> $path")
            putFileToStorage(uri,path){
                getUrlFromStorage(path){
                    putUrlToDatabase(it){
                        Log.d("MyLog","Set photo  -> $it")
                        settings_user_photo.downloadAndSetImage(it)
                        showToast(getString(R.string.toast_data_update))
                        USER.photoUrl = it
                        APP_ACTIVITY.mAppDrawer.updateHeader()
                    }
                }
            }
        }
   }
}