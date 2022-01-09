package com.yang.a09p9.presentation.user

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
import com.yang.a09p9.databinding.FragmentSignUpBinding
import com.yang.a09p9.presentation.main.MainActivity
import com.yang.a09p9.util.Constants.TAG
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SignUpFragment :
    BaseFragment<FragmentSignUpBinding>(R.layout.fragment_sign_up) {
    private lateinit var auth: FirebaseAuth

    override fun init() {
        binding.signUpFragment = this

        auth = Firebase.auth
    }

    fun onRegister() {
        hideErrors()

        val emailInput = binding.editEmail.text.toString()
        val nameInput = binding.editName.text.toString()
        val passwordInput = binding.editPassword.text.toString()
        val passwordCheckInput = binding.editPasswordCheck.text.toString()
        val termsInput = binding.checkTermsAgreement.isChecked

        if (emailInput.isBlank() || nameInput.isBlank() || passwordInput.isBlank() || passwordCheckInput.isBlank()) { // 모든 정보가 입력되지 않은 경우
            makeToast("모든 정보를 입력해주세요.")
        } else if (!android.util.Patterns.EMAIL_ADDRESS.matcher(emailInput).matches()) { // 이메일 형식이 올바르지 않은 경우
            showEmailError(true)
        } else if (passwordInput.length < 6) {
            showPasswordError(true)
        } else if (passwordInput != passwordCheckInput) { // 비밀번호와 비밀번호 확인이 일치하지 않는 경우
            showPasswordCheckError(true)
        } else if (!termsInput) {
            makeToast("약관 미동의 시 가입할 수 없습니다.")
        }
        else { // 정상 진행
            allowInput(false)
            register(emailInput, passwordInput, nameInput)
        }
    }

    private fun register(email: String, password: String, name: String) {
        auth.createUserWithEmailAndPassword(email, password)
            .addOnSuccessListener {
                val profileUpdates = userProfileChangeRequest {
                    displayName = name
                }

                auth.currentUser!!.updateProfile(profileUpdates).addOnSuccessListener {
                    Log.d(TAG, "register: profile updated.")
                }

                startActivity(Intent(activity, MainActivity::class.java))
                makeToast("회원가입이 성공했습니다!")
                activity?.finish()
            }
            .addOnFailureListener {
                if (it.message!!.contains("already in use")) {
                    showEmailError(true, getString(R.string.message_error_email_already_in_use))
                } else {
                    makeToast(it.message ?: "알 수 없는 예외")
                }

                allowInput(true)
            }
    }

    private fun allowInput(isAllowed: Boolean) = with(binding) {
        editEmail.isEnabled = isAllowed
        editName.isEnabled = isAllowed
        editPassword.isEnabled = isAllowed
        editPasswordCheck.isEnabled = isAllowed
        buttonRegister.isClickable = isAllowed
        textGoLogin.isEnabled = isAllowed

        showProgress(!isAllowed)
    }

    private fun hideErrors() {
        showEmailError(false)
        showPasswordError(false)
        showPasswordCheckError(false)
    }

    private fun showProgress(isVisible: Boolean) {
        binding.progress.visibility = if (isVisible) View.VISIBLE else View.GONE
    }

    private fun showEmailError(isVisible: Boolean) = with(binding) {
        textEmailWarning.text = getString(R.string.message_error_email_format)

        imageEmailWarning.visibility = if (isVisible) View.VISIBLE else View.GONE
        textEmailWarning.visibility = if (isVisible) View.VISIBLE else View.GONE
    }
    private fun showEmailError(isVisible: Boolean, message: String) = with(binding) {
        textEmailWarning.text = message

        imageEmailWarning.visibility = if (isVisible) View.VISIBLE else View.GONE
        textEmailWarning.visibility = if (isVisible) View.VISIBLE else View.GONE
    }

    private fun showPasswordError(isVisible: Boolean) = with(binding) {
        imagePasswordWarning.visibility = if (isVisible) View.VISIBLE else View.GONE
        textPasswordWarning.visibility = if (isVisible) View.VISIBLE else View.GONE
    }

    private fun showPasswordCheckError(isVisible: Boolean) = with(binding) {
        imagePasswordCheckWarning.visibility = if (isVisible) View.VISIBLE else View.GONE
        textPasswordCheckWarning.visibility = if (isVisible) View.VISIBLE else View.GONE
    }

    fun toLogin() = findNavController().navigate(R.id.action_signUpFragment_to_signInFragment)

    fun toTerms() = findNavController().navigate(R.id.action_signUpFragment_to_termsFragment)
}