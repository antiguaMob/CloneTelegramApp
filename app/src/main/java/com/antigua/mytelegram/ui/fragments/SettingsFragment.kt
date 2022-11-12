package com.antigua.mytelegram.ui.fragments

import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import com.antigua.mytelegram.MainActivity
import com.antigua.mytelegram.R
import com.antigua.mytelegram.activities.RegisterActivity
import com.antigua.mytelegram.utilits.AUTH
import com.antigua.mytelegram.utilits.replaceActivity

class SettingsFragment :  BaseFragment(R.layout.fragment_settings) {

    override fun onResume() {
        super.onResume()
        setHasOptionsMenu(true)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        activity?.menuInflater?.inflate(R.menu.settings_action_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.settings_menu_exit -> {
                AUTH.signOut()
                (activity as MainActivity).replaceActivity(RegisterActivity())
            }
        }
        return  true
    }
}