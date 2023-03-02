package com.aidaole.aimusic.modules.songlist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.LayoutManager
import com.aidaole.aimusic.databinding.FragmentSongListBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SongListFragment : Fragment() {

    private val layout by lazy { FragmentSongListBinding.inflate(layoutInflater) }
    private val songListVM by viewModels<SongListViewModel>()
    private val songsListRecyclerAdapter = SongListAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return layout.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        initViews()
        initVM()
        songListVM.loadSongLists()
    }

    private fun initViews() {
        layout.recommendSongList.layoutManager =
            LinearLayoutManager(requireContext(), RecyclerView.HORIZONTAL, false)
        layout.recommendSongList.adapter = songsListRecyclerAdapter
    }

    private fun initVM() {
        songListVM.topPlayList.observe(viewLifecycleOwner) {
            songsListRecyclerAdapter.updateDatas(it.playlists)
        }
    }
}