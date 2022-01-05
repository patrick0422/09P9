package com.yang.a09p9.presentation.main.profile


import android.content.Intent
import androidx.fragment.app.viewModels
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.yang.a09p9.R
import com.yang.a09p9.base.BaseFragment
import com.yang.a09p9.databinding.FragmentProfileBinding
import com.yang.a09p9.presentation.main.MainViewModel
import com.yang.a09p9.presentation.user.UserActivity

class ProfileFragment : BaseFragment<FragmentProfileBinding>(R.layout.fragment_profile) {
    private val mainViewModel: MainViewModel by viewModels()

    override fun init() {
        binding.profileFragment = this
    }

    fun onLogout() {
        mainViewModel.auth.signOut()
        startActivity(Intent(activity, UserActivity::class.java))
        activity?.finish()
    }
}