package com.antigua.mytelegram.ui.fragments

import androidx.fragment.app.Fragment
import com.antigua.mytelegram.R
import com.antigua.mytelegram.utilits.AppConstants.APP_ACTIVITY

class MainFragment : Fragment(R.layout.fragment_chats) {
    override fun onResume() {
        super.onResume()
        APP_ACTIVITY.title = "Telegram"
        APP_ACTIVITY.mAppDrawer.enableDrawer()
    }
}