package com.aidaole.aimusic.framework

import android.os.Bundle
import androidx.fragment.app.FragmentActivity
import androidx.viewbinding.ViewBinding

abstract class ViewBindingActivity<T : ViewBinding> : FragmentActivity() {
    internal lateinit var layout: T

    abstract fun getViewBinding(): T

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        layout = getViewBinding()
        setContentView(layout.root)
    }
}