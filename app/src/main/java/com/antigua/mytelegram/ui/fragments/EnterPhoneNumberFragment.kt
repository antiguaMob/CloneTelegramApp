package com.antigua.mytelegram.ui.fragments

import android.util.Log
import android.widget.Toast
import com.antigua.mytelegram.R
import kotlinx.android.synthetic.main.fragment_enter_phone_number.*


class EnterPhoneNumberFragment: BaseFragment<Any?>(R.layout.fragment_enter_phone_number){

    override fun onStart() {
        super.onStart()
        register_btn_next.setOnClickListener {
          sendCode()
        }
    }

   private fun sendCode() {
       if(register_input_phone_number.text.toString().isNotEmpty()){
          fragmentManager
               ?.beginTransaction()
               ?.replace(R.id.registerDataContainer,EnterCodeFragment())
               ?.addToBackStack(null)
               ?.commit()
       } else {
           Toast.makeText(activity,getString(R.string.register_toast_enter_phone),Toast.LENGTH_SHORT ).show()
       }
   }

}