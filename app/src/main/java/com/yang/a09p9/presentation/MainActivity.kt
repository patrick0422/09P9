package com.yang.a09p9.presentation

import android.view.View
import android.view.ViewTreeObserver
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.yang.a09p9.R
import com.yang.a09p9.base.BaseActivity
import com.yang.a09p9.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlin.concurrent.thread

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding>(R.layout.activity_main) {
    var isReady = false

    override fun preLoad() {
        installSplashScreen()
    }

    override fun init() {
        thread(start=true) {
            for (i in 1..5) {
                Thread.sleep(1000)
            }
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
}