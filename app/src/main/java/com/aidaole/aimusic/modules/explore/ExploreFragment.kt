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
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.aidaole.aimusic.databinding.FragmentExploreBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ExploreFragment : Fragment() {

    private val layout by lazy { FragmentExploreBinding.inflate(layoutInflater) }
    private val exploreVM by viewModels<ExploreViewModel>()
    private val recommendPlayListAdapter = RecommendPlayListAdapter()
    private val hotPlayListTagListAdapter = HotplayListTagListAdapter()

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
    }

    private fun initViews() {
        layout.recommendPlayList.layoutManager =
            LinearLayoutManager(requireContext(), RecyclerView.HORIZONTAL, false)
        layout.recommendPlayList.adapter = recommendPlayListAdapter

        layout.hotPlayList.layoutManager = GridLayoutManager(
            requireContext(), 2, RecyclerView.HORIZONTAL, false
        )
        layout.hotPlayList.adapter = hotPlayListTagListAdapter
    }

    private fun initVM() {
        exploreVM.recommendPlayList.observe(viewLifecycleOwner) {
            recommendPlayListAdapter.updateDatas(it.playlists)
        }
        exploreVM.hotplaylistTags.observe(viewLifecycleOwner) {
            hotPlayListTagListAdapter.updateDatas(it.tags)
        }
    }
}