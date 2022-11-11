package com.antigua.mytelegram.ui.fragments

import com.antigua.mytelegram.R
import com.antigua.mytelegram.utilits.AppTextWatcher
import com.antigua.mytelegram.utilits.showToast
import kotlinx.android.synthetic.main.fragment_enter_code.*


class EnterCodeFragment :  BaseFragment(R.layout.fragment_enter_code) {

    override fun onStart() {
        super.onStart()
       register_input_code.addTextChangedListener(AppTextWatcher {
              val string = register_input_code.text.toString()
              if(string.length==6){
                  verificateCode()
              }
        })
    }

    private fun verificateCode() {
        showToast("OK")
    }
}