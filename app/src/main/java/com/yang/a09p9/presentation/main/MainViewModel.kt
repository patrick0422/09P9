package com.yang.a09p9.presentation.main

import androidx.lifecycle.ViewModel
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class MainViewModel : ViewModel() {
    val auth by lazy { Firebase.auth }

    var isReady = false
}