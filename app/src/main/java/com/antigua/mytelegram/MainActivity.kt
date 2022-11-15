package com.antigua.mytelegram

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.widget.Toolbar
import com.antigua.mytelegram.activities.RegisterActivity
import com.antigua.mytelegram.databinding.ActivityMainBinding
import com.antigua.mytelegram.models.User
import com.antigua.mytelegram.ui.fragments.ChatsFragment
import com.antigua.mytelegram.ui.objects.AppDrawer
import com.antigua.mytelegram.utilits.*
import com.antigua.mytelegram.utilits.AppConstants.APP_ACTIVITY


class MainActivity : AppCompatActivity() {

    private lateinit var mBinding: ActivityMainBinding
    lateinit var mAppDrawer: AppDrawer
    private lateinit var mToolbar: Toolbar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mBinding.root)
        APP_ACTIVITY = this
        initFields()
        initFunc()
    }


    private fun initFunc() {
        setSupportActionBar(mToolbar)
        mAppDrawer.create()
        if (AUTH.currentUser != null) {

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

}