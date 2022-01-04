package com.yang.a09p9.presentation

import android.animation.ObjectAnimator
import android.content.Intent
import android.os.Build
import android.view.View
import android.view.ViewTreeObserver
import android.view.animation.AnticipateInterpolator
import androidx.core.animation.doOnEnd
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.yang.a09p9.R
import com.yang.a09p9.base.BaseActivity
import com.yang.a09p9.databinding.ActivityMainBinding
import com.yang.a09p9.presentation.user.UserActivity
import dagger.hilt.android.AndroidEntryPoint
import kotlin.concurrent.thread

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding>(R.layout.activity_main) {
    private lateinit var auth : FirebaseAuth

    var isReady = false

    override fun preLoad() {
        installSplashScreen()
    }

    override fun init() {
        binding.activity = this
        auth = Firebase.auth

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

        thread(start = true) {
            Thread.sleep(1000)
            isReady = true
        }

        val content: View = findViewById(android.R.id.content)
        content.viewTreeObserver.addOnPreDrawListener(
            object : ViewTreeObserver.OnPreDrawListener {
                override fun onPreDraw(): Boolean {
                    // Check if the initial data is ready.
                    return if (isReady) {
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
    }

    fun onLogout() {
        auth.signOut()
        makeToast("로그아웃되었습니다.")
        startActivity(Intent(this, UserActivity::class.java))
        finish()
    }
}