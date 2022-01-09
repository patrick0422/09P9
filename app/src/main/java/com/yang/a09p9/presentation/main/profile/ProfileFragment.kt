package com.yang.a09p9.presentation.main.profile


import android.content.Intent
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import androidx.fragment.app.activityViewModels
import com.yang.a09p9.R
import com.yang.a09p9.base.BaseFragment
import com.yang.a09p9.databinding.FragmentProfileBinding
import com.yang.a09p9.presentation.main.MainViewModel
import com.yang.a09p9.presentation.user.UserActivity

class ProfileFragment : BaseFragment<FragmentProfileBinding>(R.layout.fragment_profile) {
    private val mainViewModel: MainViewModel by activityViewModels()

    override fun init() {
        binding.profileFragment = this
        binding.mainViewModel = mainViewModel

        observeData() //FIXME 고쳐줘...

        if (mainViewModel.auth.currentUser != null)
            setVerificationButtonVisibility()
    }

    private fun setVerificationButtonVisibility() {
        val isVerified = mainViewModel.auth.currentUser!!.isEmailVerified

        if (isVerified)
            binding.buttonVerification.visibility = View.INVISIBLE
        else
            binding.buttonVerification.visibility = View.VISIBLE
    }

    private fun observeData() {
        mainViewModel.isEmailSent.observe(this, { isEmailSent ->
            if (isEmailSent) {
                makeToast(getString(R.string.message_verification_email_sent))
            }
        })
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_profile ,menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId) {
            R.id.action_sign_out -> {
                mainViewModel.auth.signOut()
                startActivity(Intent(activity, UserActivity::class.java))
                activity?.finish()
                true
            }

            else -> super.onOptionsItemSelected(item)
        }
    }
}