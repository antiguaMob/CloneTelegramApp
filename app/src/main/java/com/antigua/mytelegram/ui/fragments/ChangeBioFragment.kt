package com.antigua.mytelegram.ui.fragments

import com.antigua.mytelegram.R
import com.antigua.mytelegram.utilits.*
import kotlinx.android.synthetic.main.fragment_change_bio.*



class ChangeBioFragment : BaseChangeFragment(R.layout.fragment_change_bio) {
    override fun onResume() {
        super.onResume()
        settings_input_bio.setText(USER.bio)
    }

    override fun change() {
        super.change()
        val newBio = settings_input_bio.text.toString()
        setBioToDatabase(newBio)
    }
}