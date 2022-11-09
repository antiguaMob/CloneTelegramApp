package com.antigua.mytelegram.ui.fragments

import android.view.Menu
import android.view.MenuInflater
import com.antigua.mytelegram.R

class SettingsFragment :  BaseFragment(R.layout.fragment_settings) {

    override fun onResume() {
        super.onResume()
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        activity?.menuInflater?.inflate(R.menu.settings_action_menu, menu)
    }
}