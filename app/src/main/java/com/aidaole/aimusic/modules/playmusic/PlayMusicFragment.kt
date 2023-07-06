package com.aidaole.aimusic.modules.playmusic

import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.aidaole.aimusic.databinding.FragmentPlayMusicBinding
import com.aidaole.aimusic.framework.ViewBindingFragment
import com.aidaole.base.datas.StateValue
import com.aidaole.base.utils.toast
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

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
    }

    override fun initViewModels() {
    }

    override fun doAfterInit() {
        lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                playMusicVM.playingSongsList.collectLatest {
                    when (it) {
                        is StateValue.Succ -> {
                            playListViewAdapter.updateMusicItems(it.value!!.songs)
                        }
                        is StateValue.Fail -> {
                            "加载playlist失败".toast(context)
                            playListViewAdapter.updateMusicItems(emptyList())
                        }
                    }
                }
            }
        }
    }
}