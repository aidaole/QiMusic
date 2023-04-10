package com.aidaole.aimusic.modules.playmusic

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.aidaole.aimusic.databinding.FragmentPlayMusicBinding
import com.aidaole.aimusic.framework.ViewBindingFragment
import com.aidaole.base.datas.UserInfoManager
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PlayMusicFragment : ViewBindingFragment<FragmentPlayMusicBinding>() {
    companion object {
        private const val TAG = "PlaylistFragment"
    }

    private val playingVM: PlayMusicViewModel by viewModels()
    override fun getViewBinding(): FragmentPlayMusicBinding = FragmentPlayMusicBinding.inflate(layoutInflater)
    private lateinit var playListViewAdapter: PlayListViewAdapter


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val userinfo = UserInfoManager.getUserInfo(requireContext())
        initViews()
    }

    private fun initViews() {
        playListViewAdapter = PlayListViewAdapter()
        layout.playListView.adapter = playListViewAdapter
        playListViewAdapter.updateMusicItems(listOf("1", "2", "3"))
    }
}