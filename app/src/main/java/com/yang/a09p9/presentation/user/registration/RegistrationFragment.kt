package com.yang.a09p9.presentation.user.registration

import android.content.Intent
import androidx.navigation.fragment.findNavController
import com.yang.a09p9.R
import com.yang.a09p9.base.BaseFragment
import com.yang.a09p9.databinding.FragmentRegistrationBinding
import com.yang.a09p9.presentation.MainActivity


class RegistrationFragment  : BaseFragment<FragmentRegistrationBinding>(R.layout.fragment_registration) {


    override fun init() {
        binding.registrationFragment = this
    }

    fun toLogin() = findNavController().navigate(R.id.action_registrationFragment_to_loginFragment)

//    fun toTerms() = findNavController().navigate(R.id.action_)
    
    fun onRegister() { startActivity(Intent(requireContext(), MainActivity::class.java)) }
}