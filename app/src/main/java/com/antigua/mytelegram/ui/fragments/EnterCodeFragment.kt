package com.antigua.mytelegram.ui.fragments

import android.util.Log
import com.antigua.mytelegram.MainActivity
import com.antigua.mytelegram.R
import com.antigua.mytelegram.activities.RegisterActivity
import com.antigua.mytelegram.utilits.*
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
        //Log.d("MyLog","Enter code")
        val code = register_input_code.text.toString()
        val credential = PhoneAuthProvider.getCredential(id,code)
        AUTH.signInWithCredential(credential).addOnCompleteListener {
            if(it.isSuccessful){
                val uid = AUTH.currentUser?.uid.toString()
                //Log.d("MyLog","Sign In: $uid ")
                val dateMap = mutableMapOf<String,Any>()
                dateMap [CHILD_ID] = uid
                dateMap[CHILD_PHONE]  = phoneNumber
                dateMap[CHILD_USERNAME] = uid
                //Log.d("MyLog","Database: $REF_DATABASE_ROOT")
                REF_DATABASE_ROOT.child(NODE_USERS).child(uid).updateChildren(dateMap)
                    .addOnCompleteListener { task2->
                    if(task2.isSuccessful){
                        showToast("Добро пожаловать")
                        (activity as RegisterActivity).replaceActivity(MainActivity())
                    } else {
                        showToast(task2.exception?.message.toString())
                    }
                }
            } else {
                //Log.d("MyLog","Sign In error")
                showToast(it.exception?.message.toString())
            }
        }
    }
}