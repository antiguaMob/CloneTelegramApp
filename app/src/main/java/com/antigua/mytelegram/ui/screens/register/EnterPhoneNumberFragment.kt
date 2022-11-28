package com.antigua.mytelegram.ui.screens.register

import android.util.Log
import com.antigua.mytelegram.R
import com.antigua.mytelegram.database.AUTH
import com.antigua.mytelegram.ui.screens.base.BaseFragment
import com.antigua.mytelegram.utilits.*
import com.antigua.mytelegram.utilits.AppConstants.APP_ACTIVITY
import com.google.firebase.FirebaseException
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthProvider
import kotlinx.android.synthetic.main.fragment_enter_phone_number.*
import java.util.concurrent.TimeUnit


class EnterPhoneNumberFragment: BaseFragment(R.layout.fragment_enter_phone_number) {

    private lateinit var mPhoneNumber: String
    private lateinit var mCallback: PhoneAuthProvider.OnVerificationStateChangedCallbacks

    override fun onStart() {
        super.onStart()
            /* Callback который возврвщает результат верификации */
        mCallback = object : PhoneAuthProvider.OnVerificationStateChangedCallbacks(){

            override fun onVerificationCompleted(credential: PhoneAuthCredential) {
                /* Функция срабатывает если верификация уже была произведена */
                /* пользователь авторизуется в приложении без подтверждения по смс */
                AUTH.signInWithCredential(credential).addOnCompleteListener {
                    if(it.isSuccessful){
                        showToast("Добро пожаловать")
                        restartActivity()
                    } else {
                        showToast(it.exception?.message.toString())
                    }
                }
            }

            override fun onVerificationFailed(p0: FirebaseException) {
                showToast(p0.message.toString())
            }

            override fun onCodeSent(id: String, token: PhoneAuthProvider.ForceResendingToken) {
                replaceFragment(EnterCodeFragment(mPhoneNumber,id))
            }
        }
        register_btn_next.setOnClickListener {
            sendCode()
        }
    }

    private fun sendCode() {
        if (register_input_phone_number.text.toString().isNotEmpty()) {
            authUser()
        } else {
            showToast(getString(R.string.register_toast_enter_phone))
        }
    }

    private fun authUser() {
        mPhoneNumber = register_input_phone_number.text.toString()
        Log.d("MyLog","phone $mPhoneNumber")
//        Phone number	Verification code
//                +1 650-555-3434	123456
//                +1 650-555-3435	123456
//*******************************************************************
        PhoneAuthProvider.getInstance().verifyPhoneNumber(
            mPhoneNumber,
            60,
            TimeUnit.SECONDS,
            APP_ACTIVITY,
            mCallback
        )

//        PhoneAuthProvider.verifyPhoneNumber(
//            PhoneAuthOptions
//                .newBuilder(FirebaseAuth.getInstance())
//                .setActivity(APP_ACTIVITY)
//                .setPhoneNumber(mPhoneNumber)
//                .setTimeout(60L, TimeUnit.SECONDS)
//                .setCallbacks(mCallback)
//                .build()
//        )
    }
}