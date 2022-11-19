package com.antigua.mytelegram.ui.fragments

import android.view.View
import com.antigua.mytelegram.R
import com.antigua.mytelegram.models.CommonModel
import com.antigua.mytelegram.utilits.AppConstants.APP_ACTIVITY
import kotlinx.android.synthetic.main.activity_main.view.*

class SingleChatFragment(contact: CommonModel) : BaseFragment(R.layout.fragment_single_chat) {

    override fun onResume() {
        super.onResume()
        APP_ACTIVITY.mToolbar.toolbar_info.visibility = View.VISIBLE
    }

    override fun onPause() {
        super.onPause()
        APP_ACTIVITY.mToolbar.toolbar_info.visibility = View.GONE
    }
}
