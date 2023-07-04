package com.aidaole.aimusic.modules.explore

import android.os.Bundle
import android.view.View
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.aidaole.aimusic.databinding.FragmentExploreBinding
import com.aidaole.aimusic.framework.ViewBindingFragment
import com.aidaole.aimusic.modules.MainPage
import com.aidaole.aimusic.modules.MainViewModel
import com.aidaole.aimusic.modules.playmusic.PlayMusicViewModel
import com.aidaole.base.datas.StateValue
import com.aidaole.base.ext.toVisible
import com.aidaole.base.utils.logi
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class ExploreFragment : ViewBindingFragment<FragmentExploreBinding>() {

    companion object {
        private const val TAG = "ExploreFragment"
    }

    private val exploreVM by viewModels<ExploreViewModel>()
    private val playMusicVM by activityViewModels<PlayMusicViewModel>()
    private val mainViewModel by activityViewModels<MainViewModel>()

    private val hotPlayListTagListAdapter = HotplayListTagListAdapter()
    private val recommendPlayListAdapter = RecommendPlayListAdapter()
    private val topSongsAdapter = TopSongsAdapter()
    override fun getViewBinding(): FragmentExploreBinding = FragmentExploreBinding.inflate(layoutInflater)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        initViews()
        initVM()
    }

    private fun initViews() {
        layout.hotPlayList.layoutManager = GridLayoutManager(
            requireContext(), 2, RecyclerView.HORIZONTAL, false
        )
        layout.hotPlayList.adapter = hotPlayListTagListAdapter

        layout.recommendPlayList.layoutManager = GridLayoutManager(
            requireContext(), 2, RecyclerView.HORIZONTAL, false
        )
        layout.recommendPlayList.adapter = recommendPlayListAdapter

        layout.topSongs.layoutManager = object : LinearLayoutManager(requireContext()) {
            override fun canScrollVertically(): Boolean {
                return false
            }
        }
        layout.topSongs.adapter = topSongsAdapter
    }

    private fun initVM() {
        lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                "initVM-> launchWhenStarted".logi(TAG)
                launch {
                    exploreVM.hotplaylistTags.collect {
                        "initVM-> hotplaylistTags.collect".logi(TAG)
                        it?.let {
                            layout.musicTagsText.toVisible()
                            hotPlayListTagListAdapter.updateDatas(it.tags)
                        }
                    }
                }

                launch {
                    exploreVM.recommendPlayList.collect {
                        "initVM-> recommendPlayList.collectLatest".logi(TAG)
                        it?.let {
                            layout.recommendPlaylistText.toVisible()
                            recommendPlayListAdapter.updateDatas(it.playlists)
                            recommendPlayListAdapter.onItemClick = {
                                playMusicVM.playList(it)
                                mainViewModel.naviTo(MainPage.MUSIC)
                            }
                        }
                    }
                }

                launch {
                    exploreVM.topSongs.collect {
                        "initVM-> topSongs.collect".logi(TAG)
                        when (it) {
                            is StateValue.Succ -> {
                                layout.topSongsText.toVisible()
                                topSongsAdapter.updateDatas(it.value!!.songs)
                                topSongsAdapter.onItemClick = {
                                    playMusicVM.play(it)
                                    mainViewModel.naviTo(MainPage.MUSIC)
                                }
                            }
                            else -> {
                                "加载热门歌曲失败".logi(TAG)
                            }
                        }
                    }
                }
            }
        }
    }
}