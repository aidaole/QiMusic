package com.aidaole.aimusic

import com.aidaole.aimusic.databinding.ActivityMainBinding
import com.aidaole.aimusic.framework.ViewBindingActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ViewBindingActivity<ActivityMainBinding>() {
    override fun getViewBinding(): ActivityMainBinding = ActivityMainBinding.inflate(layoutInflater)
}