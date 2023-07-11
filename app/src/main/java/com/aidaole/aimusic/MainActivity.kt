package com.aidaole.aimusic

import android.os.Bundle
import androidx.activity.viewModels
import com.aidaole.aimusic.databinding.ActivityMainBinding
import com.aidaole.aimusic.framework.ViewBindingActivity
import com.aidaole.aimusic.modules.playmusic.PlayMusicViewModel
import com.aidaole.base.ext.fitImmersive
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ViewBindingActivity<ActivityMainBinding>() {
    private val playingVm by viewModels<PlayMusicViewModel>()

    override fun getViewBinding(): ActivityMainBinding = ActivityMainBinding.inflate(layoutInflater)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.fitImmersive()
    }
}