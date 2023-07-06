package com.aidaole.aimusic

import android.os.Bundle
import android.view.WindowManager
import androidx.activity.viewModels
import com.aidaole.aimusic.databinding.ActivityMainBinding
import com.aidaole.aimusic.framework.ViewBindingActivity
import com.aidaole.aimusic.modules.playmusic.PlayMusicViewModel
import com.aidaole.utils.ext.getStatusBarHeight
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ViewBindingActivity<ActivityMainBinding>() {
    private val playingVm by viewModels<PlayMusicViewModel>()

    override fun getViewBinding(): ActivityMainBinding = ActivityMainBinding.inflate(layoutInflater)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        fitStatusBarHeight()
    }

    private fun fitStatusBarHeight() {
        window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
//        layout.rootLayout.setPadding(0, getStatusBarHeight(), 0, 0)
    }
}