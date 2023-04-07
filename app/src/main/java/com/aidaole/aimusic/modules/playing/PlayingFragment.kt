package com.aidaole.aimusic.modules.playing

import android.os.Bundle
import android.view.View
import com.aidaole.aimusic.databinding.FragmentPlayingBinding
import com.aidaole.aimusic.framework.ViewBindingFragment

class PlayingFragment : ViewBindingFragment<FragmentPlayingBinding>() {
    override fun getViewBinding(): FragmentPlayingBinding = FragmentPlayingBinding.inflate(layoutInflater)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        initViews()
    }

    private fun initViews() {
        
    }
}