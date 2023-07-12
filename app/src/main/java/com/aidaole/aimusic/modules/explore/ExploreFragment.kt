package com.aidaole.aimusic.modules.explore

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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
import com.aidaole.base.ext.logi
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
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

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        fitStatusBarHeight()
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun initViews() {
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

    override fun initViewModels() {
        lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                "initVM-> launchWhenStarted".logi(TAG)
                launch {
                    exploreVM.hotplaylistTags.collectLatest {
                        "initVM-> hotplaylistTags.collect".logi(TAG)
                        when (it) {
                            is StateValue.Succ -> {
                                layout.musicTagsText.toVisible()
                                hotPlayListTagListAdapter.updateDatas(it.value!!.tags)
                            }
                        }
                    }
                }
                launch {
                    exploreVM.recommendPlayList.collectLatest {
                        "initVM-> recommendPlayList.collectLatest".logi(TAG)
                        when (it) {
                            is StateValue.Succ -> {
                                layout.recommendPlaylistText.toVisible()
                                recommendPlayListAdapter.updateDatas(it.value!!.playlists)
                                recommendPlayListAdapter.onItemClick = {
                                    playMusicVM.playList(it)
                                    mainViewModel.naviTo(MainPage.MUSIC)
                                }
                            }
                        }
                    }
                }

                launch {
                    exploreVM.topSongs.collectLatest {
                        "initVM-> topSongs.collect".logi(TAG)
                        when (it) {
                            is StateValue.Succ -> {
                                layout.topSongsText.toVisible()
                                topSongsAdapter.updateDatas(it.value!!.songs)
                                topSongsAdapter.onItemClick = { song ->
                                    playMusicVM.play(song)
                                    mainViewModel.naviTo(MainPage.MUSIC)
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    override fun doAfterInit() {

    }
}