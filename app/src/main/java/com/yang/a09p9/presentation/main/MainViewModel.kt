package com.yang.a09p9.presentation.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class MainViewModel : ViewModel() {
    val auth by lazy { Firebase.auth }

    var isReady = false

    private val _isEmailSent = MutableLiveData(false)
    val isEmailSent: LiveData<Boolean> get() = _isEmailSent

    fun sendEmailVerification() {
        auth.useAppLanguage()
        auth.currentUser?.sendEmailVerification()?.addOnSuccessListener {
            _isEmailSent.value = true
        }
    }
}