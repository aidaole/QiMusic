package com.aidaole.aimusic.modules.playmusic

import android.os.Bundle
import android.view.View
import androidx.fragment.app.activityViewModels
import com.aidaole.aimusic.databinding.FragmentPlayMusicBinding
import com.aidaole.aimusic.framework.ViewBindingFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PlayMusicFragment : ViewBindingFragment<FragmentPlayMusicBinding>() {
    companion object {
        private const val TAG = "PlaylistFragment"
    }

    private val playMusicVM by activityViewModels<PlayMusicViewModel>()
    private lateinit var playListViewAdapter: PlayListViewAdapter

    override fun getViewBinding(): FragmentPlayMusicBinding = FragmentPlayMusicBinding.inflate(layoutInflater)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        initViews()
        initViewModels()
    }

    private fun initViews() {
        playListViewAdapter = PlayListViewAdapter()
        layout.playListView.adapter = playListViewAdapter
        playListViewAdapter.updateMusicItems(listOf())
    }

    private fun initViewModels() {
        playMusicVM.playingSongs.observe(viewLifecycleOwner) {
            playListViewAdapter.updateMusicItems(it)
        }
    }
}