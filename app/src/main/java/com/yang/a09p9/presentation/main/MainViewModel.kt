package com.yang.a09p9.presentation.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
class MainViewModel : ViewModel() {
    val auth by lazy { Firebase.auth }
    val user: FirebaseUser? get() = auth.currentUser

    var isReady = false

    private val _isEmailSent = MutableLiveData(false)
    val isEmailSent: LiveData<Boolean> get() = _isEmailSent

    fun sendEmailVerification() {
        auth.useAppLanguage()
        user?.sendEmailVerification()?.addOnSuccessListener {
            _isEmailSent.value = true
        }
    }
}