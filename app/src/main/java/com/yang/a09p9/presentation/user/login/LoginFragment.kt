package com.yang.a09p9.presentation.user.login

import android.content.Intent
import android.util.Log
import android.view.View
import androidx.navigation.fragment.findNavController
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.auth.ktx.userProfileChangeRequest
import com.google.firebase.ktx.Firebase
import com.yang.a09p9.R
import com.yang.a09p9.base.BaseFragment
import com.yang.a09p9.databinding.FragmentLoginBinding
import com.yang.a09p9.presentation.MainActivity
import com.yang.a09p9.util.Constants.TAG
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginFragment : BaseFragment<FragmentLoginBinding>(R.layout.fragment_login) {
    private lateinit var auth : FirebaseAuth
    override fun init() {
        binding.loginFragment = this

        auth = Firebase.auth

        if (auth.currentUser != null) { // 이미 로그인 해 있는 경우
            proceed()
        }
    }

    fun toRegister() = findNavController().navigate(R.id.action_loginFragment_to_registrationFragment)

    fun onLogin() {
        val emailInput = binding.editEmail.text.toString()
        val passwordInput = binding.editPassword.text.toString()

        if (emailInput.isBlank() || passwordInput.isBlank()) {
            makeToast("모든 정보를 입력해주세요.")
        } else {
            allowInput(false)
            login(emailInput, passwordInput)
        }
    }

    fun login(email: String, password: String) {
        showAccountError(false)

        auth.signInWithEmailAndPassword(email, password)
            .addOnSuccessListener {
                proceed()
            }
            .addOnFailureListener {
                Log.d(TAG, "login: ${it.message ?: "알 수 없는 예외"}")
                showAccountError(true)
                allowInput(true)
            }
    }

    private fun allowInput(isAllowed: Boolean) = with(binding) {
        editEmail.isEnabled = isAllowed
        editPassword.isEnabled = isAllowed
        buttonLogin.isClickable = isAllowed
        textGoRegister.isEnabled = isAllowed

        showProgress(!isAllowed)
    }

    private fun showAccountError(isVisible: Boolean) = with(binding) {
        imageAccountWarning.visibility = if (isVisible) View.VISIBLE else View.GONE
        textAccountWarning.visibility = if (isVisible) View.VISIBLE else View.GONE
    }

    private fun showProgress(isVisible: Boolean) {
        binding.progress.visibility = if (isVisible) View.VISIBLE else View.GONE
    }

    private fun proceed() {
        startActivity(Intent(activity, MainActivity::class.java))
        activity?.finish()
    }
}