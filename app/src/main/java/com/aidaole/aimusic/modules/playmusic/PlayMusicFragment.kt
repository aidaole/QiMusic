package com.aidaole.aimusic.modules.playmusic

import androidx.fragment.app.activityViewModels
import com.aidaole.aimusic.databinding.FragmentPlayMusicBinding
import com.aidaole.aimusic.framework.ViewBindingFragment
import com.aidaole.base.datas.StateValue
import com.aidaole.base.utils.toast
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PlayMusicFragment : ViewBindingFragment<FragmentPlayMusicBinding>() {
    companion object {
        private const val TAG = "PlaylistFragment"
    }

    private val playMusicVM by activityViewModels<PlayMusicViewModel>()
    private lateinit var playListViewAdapter: PlayListViewAdapter

    override fun getViewBinding(): FragmentPlayMusicBinding = FragmentPlayMusicBinding.inflate(layoutInflater)

    override fun initViews() {
        playListViewAdapter = PlayListViewAdapter()
        layout.playListView.adapter = playListViewAdapter
        playListViewAdapter.updateMusicItems(listOf())
        layout.playListView.setScrollPositionChangeListener { position ->
            playMusicVM.setCurrentSongIndex(position)
        }
    }

    override fun initViewModels() {
        playMusicVM.playingSongs.observe(viewLifecycleOwner) {
            when (it) {
                is StateValue.Succ -> {
                    playListViewAdapter.updateMusicItems(it.value!!)
                }
                is StateValue.Fail -> {
                    "加载playlist失败".toast(context)
                    playListViewAdapter.updateMusicItems(emptyList())
                }
            }
        }
    }

    override fun doAfterInit() {
        playMusicVM.loadDefaultTopSongs()
    }
}