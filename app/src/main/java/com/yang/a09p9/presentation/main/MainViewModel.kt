package com.yang.a09p9.presentation.main

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
}