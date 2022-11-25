package com.antigua.mytelegram.ui.screens

import androidx.fragment.app.Fragment
import com.antigua.mytelegram.R
import com.antigua.mytelegram.utilits.AppConstants.APP_ACTIVITY
import com.antigua.mytelegram.utilits.hideKeyboard

class MainFragment : Fragment(R.layout.fragment_chats) {
    override fun onResume() {
        super.onResume()
        APP_ACTIVITY.title = "Telegram"
        APP_ACTIVITY.mAppDrawer.enableDrawer()
        hideKeyboard()
    }
}