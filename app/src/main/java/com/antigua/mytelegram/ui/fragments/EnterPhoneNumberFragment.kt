package com.antigua.mytelegram.ui.fragments

import com.antigua.mytelegram.utilits.showToast
import com.antigua.mytelegram.R
import com.antigua.mytelegram.utilits.replaceFragment
import kotlinx.android.synthetic.main.fragment_enter_phone_number.*


class EnterPhoneNumberFragment: BaseFragment(R.layout.fragment_enter_phone_number){

    override fun onStart() {
        super.onStart()
        register_btn_next.setOnClickListener {
          sendCode()
        }
    }

   private fun sendCode() {
       if(register_input_phone_number.text.toString().isNotEmpty()){
         replaceFragment(EnterCodeFragment())
       } else {
           showToast(getString(R.string.register_toast_enter_phone))
       }
   }
}