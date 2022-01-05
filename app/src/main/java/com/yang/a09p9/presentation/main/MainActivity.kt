package com.yang.a09p9.presentation.main

import android.animation.ObjectAnimator
import android.os.Build
import android.view.View
import android.view.ViewTreeObserver
import android.view.animation.AnticipateInterpolator
import androidx.activity.viewModels
import androidx.core.animation.doOnEnd
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.yang.a09p9.R
import com.yang.a09p9.base.BaseActivity
import com.yang.a09p9.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.concurrent.thread


@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding>(R.layout.activity_main) {
    private val mainViewModel: MainViewModel by viewModels()

    private val navHostFragment by lazy { supportFragmentManager.findFragmentById(R.id.mainFragmentContainerView) as NavHostFragment }
    private val navController by lazy { navHostFragment.navController }

    override fun preLoad() {
        installSplashScreen()
    }

    override fun init() {
        binding.activity = this

        setupBottomNav()
        setUpSplashScreen()
    }

    private fun setupBottomNav() {
        supportActionBar?.hide()

        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.homeFragment,
                R.id.recommendationFragment,
                R.id.bookmarkFragment,
                R.id.profileFragment
            )
        )

        binding.bottomNav.setupWithNavController(navController)
        setupActionBarWithNavController(navController, appBarConfiguration)
    }

    private fun setUpSplashScreen() {
        setSplashAnimation()
        delaySplashScreen()
    }

    private fun delaySplashScreen() {
        val content: View = findViewById(android.R.id.content)
        content.viewTreeObserver.addOnPreDrawListener(
            object : ViewTreeObserver.OnPreDrawListener {
                override fun onPreDraw(): Boolean {
                    // Check if the initial data is ready.
                    return if (mainViewModel.isReady) {
                        // The content is ready; start drawing.
                        content.viewTreeObserver.removeOnPreDrawListener(this)
                        true
                    } else {
                        // The content is not ready; suspend.
                        false
                    }
                }
            }
        )
        CoroutineScope(Dispatchers.IO).launch {
            delay(1000)
            mainViewModel.isReady = true
        }
    }

    private fun setSplashAnimation() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            splashScreen.setOnExitAnimationListener { splashScreenView ->
                ObjectAnimator.ofFloat(splashScreenView, View.TRANSLATION_Y, 0f, -splashScreenView.height.toFloat()).apply {
                    interpolator = AnticipateInterpolator()
                    duration = 200L
                    // Call SplashScreenView.remove at the end of your custom animation.
                    doOnEnd { splashScreenView.remove() }
                    // Run your animation.
                    start()
                }
            }
        }
    }

    override fun onNavigateUp(): Boolean = navController.navigateUp() || super.onNavigateUp()
}