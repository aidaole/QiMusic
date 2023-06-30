package com.aidaole.aimusic

import android.os.Bundle
import android.view.ViewGroup.LayoutParams
import androidx.activity.viewModels
import com.aidaole.aimusic.databinding.ActivityMainBinding
import com.aidaole.aimusic.framework.ViewBindingActivity
import com.aidaole.aimusic.modules.playmusic.PlayMusicViewModel
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
        layout.rootLayout.setPadding(0, statusBarHeight(), 0, 0)
    }

    private fun statusBarHeight(): Int {
        var height = 0
        val resourceId = applicationContext.resources.getIdentifier("status_bar_height", "dimen", "android")
        if (resourceId > 0) {
            height = applicationContext.resources.getDimensionPixelSize(resourceId)
        } else {
            height = resources.getDimensionPixelSize(R.dimen.default_statusbar_height)
        }
        return height
    }
}