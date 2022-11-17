package com.antigua.mytelegram.ui.fragments

import com.antigua.mytelegram.R
import com.antigua.mytelegram.utilits.AppConstants.APP_ACTIVITY

class ContactsFragment : BaseFragment(R.layout.fragment_contacts) {

    override fun onResume() {
        super.onResume()
        APP_ACTIVITY.title = "Контакты"
    }

}