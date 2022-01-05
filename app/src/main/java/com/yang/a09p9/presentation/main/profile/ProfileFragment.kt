package com.yang.a09p9.presentation.main.profile


import android.content.Intent
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.yang.a09p9.R
import com.yang.a09p9.base.BaseFragment
import com.yang.a09p9.databinding.FragmentProfileBinding
import com.yang.a09p9.presentation.user.UserActivity

class ProfileFragment : BaseFragment<FragmentProfileBinding>(R.layout.fragment_profile) {
    private val auth by lazy { Firebase.auth }

    override fun init() {
        binding.profileFragment = this
    }

    fun onLogout() {
        auth.signOut()
        startActivity(Intent(activity, UserActivity::class.java))
        activity?.finish()
    }
}