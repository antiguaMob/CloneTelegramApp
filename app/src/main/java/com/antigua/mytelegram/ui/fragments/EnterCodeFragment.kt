package com.antigua.mytelegram.ui.fragments

import com.antigua.mytelegram.MainActivity
import com.antigua.mytelegram.R
import com.antigua.mytelegram.activities.RegisterActivity
import com.antigua.mytelegram.utilits.AUTH
import com.antigua.mytelegram.utilits.AppTextWatcher
import com.antigua.mytelegram.utilits.replaceActivity
import com.antigua.mytelegram.utilits.showToast
import com.google.firebase.auth.PhoneAuthProvider
import kotlinx.android.synthetic.main.fragment_enter_code.*

class EnterCodeFragment(val phoneNumber: String, val id: String) :  BaseFragment(R.layout.fragment_enter_code) {

    override fun onStart() {
        super.onStart()
        (activity as RegisterActivity).title = phoneNumber
       register_input_code.addTextChangedListener(AppTextWatcher {
              val string = register_input_code.text.toString()
              if(string.length==6){
                  enterCode()
              }
        })
    }

    private fun enterCode() {
        val code = register_input_code.text.toString()
        val credential = PhoneAuthProvider.getCredential(id,code)
        AUTH.signInWithCredential(credential).addOnCompleteListener {
            if(it.isSuccessful){
                showToast("Добро пожаловать")
                (activity as RegisterActivity).replaceActivity(MainActivity())
            } else {
                showToast(it.exception?.message.toString())
            }
        }
    }
}