package com.antigua.mytelegram.ui.fragments.register

import com.antigua.mytelegram.R
import com.antigua.mytelegram.ui.fragments.BaseFragment
import com.antigua.mytelegram.utilits.*
import com.antigua.mytelegram.utilits.AppConstants.APP_ACTIVITY
import com.google.firebase.auth.PhoneAuthProvider
import kotlinx.android.synthetic.main.fragment_enter_code.*

class EnterCodeFragment(val phoneNumber: String, val id: String) :  BaseFragment(R.layout.fragment_enter_code) {

    override fun onStart() {
        super.onStart()
        APP_ACTIVITY.title = phoneNumber
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
        val credential = PhoneAuthProvider.getCredential(id, code)
        AUTH.signInWithCredential(credential).addOnCompleteListener {
            if (it.isSuccessful) {
                val uid = AUTH.currentUser?.uid.toString()
                //Log.d("MyLog","Sign In: $uid ")
                val dateMap = mutableMapOf<String, Any>()
                dateMap[CHILD_ID] = uid
                dateMap[CHILD_PHONE] = phoneNumber
                dateMap[CHILD_USERNAME] = uid
                REF_DATABASE_ROOT.child(NODE_PHONES).child(phoneNumber).setValue(uid)
                    .addOnFailureListener { showToast(it.message.toString()) }
                    .addOnSuccessListener {
                        REF_DATABASE_ROOT.child(NODE_USERS).child(uid).updateChildren(dateMap)
                            .addOnSuccessListener {
                                showToast("Добро пожаловать")
                                restartActivity()
                            }
                            .addOnFailureListener { showToast(it.message.toString()) }
                    }
            } else  showToast(it.exception?.message.toString())
        }
    }
}