package com.aidaole.aimusic.modules.explore

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aidaole.base.datas.entities.PlayListSongs.Songs
import com.aidaole.base.datas.network.NeteaseApi
import com.aidaole.base.datas.network.RetrofitNeteaseApi
import com.aidaole.base.utils.logi
import com.aidaole.utils.ext.toStateFlow
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ExploreViewModel @Inject constructor(
    private val neteaseApi: NeteaseApi,
    private val retrofitNeteaseApi: RetrofitNeteaseApi
) : ViewModel() {
    companion object {
        private const val TAG = "ExploreViewModel"
    }

    val recommendPlayList = neteaseApi.loadTopPlayList().toStateFlow(null)
    val hotplaylistTags = neteaseApi.loadHotPlaylistTags().toStateFlow(null)
    val topSongs = neteaseApi.loadTopPlaylistSongs().toStateFlow(null)

    fun refresh() {
    }

    fun clickSong(song: Songs) {
        viewModelScope.launch {
            val result = retrofitNeteaseApi.getMusicDetail("${song.id}")
            "clickSong-> $result".logi(TAG)
        }
    }
}