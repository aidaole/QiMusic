package com.aidaole.aimusic.modules.playlist

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.aidaole.aimusic.databinding.FragmentPlayingBinding
import com.aidaole.aimusic.framework.ViewBindingFragment
import com.aidaole.base.datas.UserInfoManager
import com.aidaole.base.utils.logi
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PlayingFragment : ViewBindingFragment<FragmentPlayingBinding>() {
    companion object {
        private const val TAG = "PlaylistFragment"
    }

    private val playingVM: PlayingViewModel by viewModels()
    override fun getViewBinding(): FragmentPlayingBinding = FragmentPlayingBinding.inflate(layoutInflater)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val userinfo = UserInfoManager.getUserInfo(requireContext())
    }
}