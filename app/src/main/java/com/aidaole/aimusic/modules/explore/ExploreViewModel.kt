package com.aidaole.aimusic.modules.explore

import androidx.lifecycle.ViewModel
import com.aidaole.base.datas.network.NeteaseApi
import com.aidaole.utils.ext.toStateFlow
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ExploreViewModel @Inject constructor(
    private val neteaseApi: NeteaseApi
) : ViewModel() {

    val recommendPlayList = neteaseApi.loadTopPlayList().toStateFlow(null)
    val hotplaylistTags = neteaseApi.loadHotPlaylistTags().toStateFlow(null)
    val topSongs = neteaseApi.loadTopPlaylistSongs().toStateFlow(null)

    fun refresh() {
    }
}