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