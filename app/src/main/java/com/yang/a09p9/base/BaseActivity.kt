package com.yang.a09p9.base

import android.os.Bundle
import android.widget.Toast
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.yang.a09p9.R

abstract class BaseActivity<B : ViewDataBinding>(@LayoutRes private val layoutResId: Int) : AppCompatActivity() {
    lateinit var binding: B
    private var waitTime: Long = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        preLoad()

        setTheme(R.style.Theme_09P9)
        binding = DataBindingUtil.setContentView(this, layoutResId)
        binding.lifecycleOwner = this@BaseActivity

        init()
    }

    open fun preLoad() {}

    abstract fun init()

    override fun onBackPressed() {
        if (System.currentTimeMillis() - waitTime >= 1500) {
            waitTime = System.currentTimeMillis()
            makeToast("한 번 더 눌러 종료합니다")
        } else finish()
    }

    protected fun makeToast(msg: String) = Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
}