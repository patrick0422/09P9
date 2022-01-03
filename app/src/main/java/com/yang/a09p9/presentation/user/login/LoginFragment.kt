package com.yang.a09p9.presentation.user.login

import android.content.Intent
import androidx.navigation.fragment.findNavController
import com.yang.a09p9.R
import com.yang.a09p9.base.BaseFragment
import com.yang.a09p9.databinding.FragmentLoginBinding
import com.yang.a09p9.presentation.MainActivity

class LoginFragment : BaseFragment<FragmentLoginBinding>(R.layout.fragment_login) {


    override fun init() {
        binding.loginFragment = this
    }

    fun toRegister() = findNavController().navigate(R.id.action_loginFragment_to_registrationFragment)

    fun onLogin() { startActivity(Intent(requireContext(), MainActivity::class.java)) }
}