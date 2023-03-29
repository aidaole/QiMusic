package com.aidaole.aimusic.modules.explore

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.aidaole.aimusic.databinding.FragmentExploreBinding
import com.aidaole.base.ext.toVisible
import com.aidaole.base.utils.logi
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class ExploreFragment : Fragment() {

    companion object {
        private const val TAG = "ExploreFragment"
    }

    private val layout by lazy { FragmentExploreBinding.inflate(layoutInflater) }
    private val exploreVM by viewModels<ExploreViewModel>()
    private val recommendPlayListAdapter = RecommendPlayListAdapter()
    private val hotPlayListTagListAdapter = HotplayListTagListAdapter()
    private val topSongsAdapter = TopSongsAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return layout.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        initViews()
        initVM()
    }

    private fun initViews() {
        layout.recommendPlayList.layoutManager = GridLayoutManager(
            requireContext(), 2, RecyclerView.HORIZONTAL, false
        )
        layout.recommendPlayList.adapter = recommendPlayListAdapter

        layout.hotPlayList.layoutManager = GridLayoutManager(
            requireContext(), 2, RecyclerView.HORIZONTAL, false
        )
        layout.hotPlayList.adapter = hotPlayListTagListAdapter

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
                        }
                    }
                }

                launch {
                    exploreVM.topSongs.collect {
                        "initVM-> topSongs.collect".logi(TAG)
                        it?.let {
                            layout.topSongsText.toVisible()
                            topSongsAdapter.updateDatas(it.songs)
                        }
                    }
                }
            }
        }
    }
}