package com.antigua.mytelegram

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.widget.Toolbar
import com.antigua.mytelegram.activities.RegisterActivity
import com.antigua.mytelegram.databinding.ActivityMainBinding
import com.antigua.mytelegram.models.User
import com.antigua.mytelegram.ui.fragments.ChatsFragment
import com.antigua.mytelegram.ui.objects.AppDrawer
import com.antigua.mytelegram.utilits.*
import com.antigua.mytelegram.utilits.AppConstants.APP_ACTIVITY
import com.theartofdev.edmodo.cropper.CropImage


class MainActivity : AppCompatActivity() {

    private lateinit var mBinding: ActivityMainBinding
    lateinit var mAppDrawer: AppDrawer
    private lateinit var mToolbar: Toolbar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mBinding.root)
    }

    override fun onStart() {
        super.onStart()
        APP_ACTIVITY = this
        initFields()
        initFunc()
    }

    private fun initFunc() {
        if (AUTH.currentUser != null) {
            setSupportActionBar(mToolbar)
            mAppDrawer.create()
            replaceFragment(ChatsFragment(),false)
        } else {
            replaceActivity(RegisterActivity())
        }
    }


    private fun initFields() {
        mToolbar = mBinding.mainToolbar
        mAppDrawer = AppDrawer(this, mToolbar)
        initFirebase()
        initUser()
    }

    private fun initUser() {
        REF_DATABASE_ROOT.child(NODE_USERS).child(CURRENT_UID)
            .addListenerForSingleValueEvent(AppValueEventListener{

                USER = it.getValue(User::class.java) ?: User()
            })
    }

    @Deprecated("Deprecated in Java")
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE
            && resultCode == RESULT_OK && data != null){
            val uri = CropImage.getActivityResult(data).uri
            val path = REF_STORAGE_ROOT.child(FOLDER_PROFILE_IMAGE)
                .child(CURRENT_UID)
            path.putFile(uri).addOnCompleteListener { Task1 ->
                if(Task1.isSuccessful){
                    //showToast(getString(R.string.toast_data_update))
                    path.downloadUrl.addOnCompleteListener { Task2 ->
                        if(Task2.isSuccessful){
                            val photoUrl = Task2.result.toString()
                            REF_DATABASE_ROOT.child(NODE_USERS).child(CURRENT_UID)
                                .child(CHILD_PHOTO_URL)
                                .setValue(photoUrl)
                                .addOnCompleteListener {
                                    if(it.isSuccessful){
                                        showToast(getString(R.string.toast_data_update))
                                        USER.photoUrl = photoUrl
                                    }
                                }
                        }
                    }
                }
            }
        }
    }

    fun hideKeyboard(){
        val imm : InputMethodManager = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(window.decorView.windowToken,0)
    }
}