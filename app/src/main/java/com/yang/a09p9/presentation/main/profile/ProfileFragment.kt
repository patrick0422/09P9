package com.yang.a09p9.presentation.main.profile


import android.content.Intent
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
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