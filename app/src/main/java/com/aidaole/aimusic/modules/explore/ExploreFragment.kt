package com.aidaole.aimusic.modules.explore

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.aidaole.aimusic.databinding.FragmentExploreBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ExploreFragment : Fragment() {

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
        exploreVM.loadRecommendLists()
        exploreVM.loadHotPlaylistTags()
        exploreVM.loadTopPlaylistSongs()
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

        layout.topSongs.layoutManager = object: LinearLayoutManager(requireContext()) {
            override fun canScrollVertically(): Boolean {
                return false
            }
        }
        layout.topSongs.adapter = topSongsAdapter
    }

    private fun initVM() {
        exploreVM.recommendPlayList.observe(viewLifecycleOwner) {
            recommendPlayListAdapter.updateDatas(it.playlists)
        }
        exploreVM.hotplaylistTags.observe(viewLifecycleOwner) {
            hotPlayListTagListAdapter.updateDatas(it.tags)
        }
        exploreVM.topSongs.observe(viewLifecycleOwner) {
            topSongsAdapter.updateDatas(it.songs)
        }
    }
}