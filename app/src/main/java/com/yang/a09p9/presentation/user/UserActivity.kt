package com.yang.a09p9.presentation.user

import androidx.navigation.fragment.NavHostFragment
import com.yang.a09p9.R
import com.yang.a09p9.base.BaseActivity
import com.yang.a09p9.databinding.ActivityUserBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class UserActivity : BaseActivity<ActivityUserBinding>(R.layout.activity_user) {
    private val navHostFragment by lazy { supportFragmentManager.findFragmentById(R.id.userFragmentContainerView) as NavHostFragment }
    private val navController by lazy { navHostFragment.navController }

    override fun init() {
        supportActionBar?.hide()
    }
}