package com.yang.a09p9.presentation.user

import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import com.yang.a09p9.R
import com.yang.a09p9.base.BaseActivity
import com.yang.a09p9.databinding.ActivityUserBinding

class UserActivity : BaseActivity<ActivityUserBinding>(R.layout.activity_user) {
    lateinit var navController: NavController

    override fun init() {
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.fragmentContainerView) as NavHostFragment
        navController = navHostFragment.navController

    }
}