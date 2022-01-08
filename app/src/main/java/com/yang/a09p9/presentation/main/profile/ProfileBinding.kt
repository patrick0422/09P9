package com.yang.a09p9.presentation.main.profile

import android.net.Uri
import android.view.View
import android.widget.Button
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import coil.load
import com.google.firebase.auth.FirebaseUser
import com.yang.a09p9.R

object ProfileBinding {
    @JvmStatic
    @BindingAdapter("loadImageFromUrl")
    fun loadImageFromUrl(imageView: ImageView, imageUrl: Uri?) {
        imageView.load(imageUrl) {
            crossfade(600)
            error(R.drawable.hamster)
        }
    }
}