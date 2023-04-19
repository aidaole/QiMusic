package com.aidaole.aimusic.modules.explore

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aidaole.base.datas.NeteaseRepo
import com.aidaole.base.datas.entities.PlayListSongs.Songs
import com.aidaole.base.utils.logi
import com.aidaole.utils.ext.toStateFlow
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ExploreViewModel @Inject constructor(
    private val neteaseRepo: NeteaseRepo
) : ViewModel() {
    companion object {
        private const val TAG = "ExploreViewModel"
    }

    val recommendPlayList = neteaseRepo.loadPlaylistHighQuality().toStateFlow(null)
    val hotplaylistTags = neteaseRepo.loadPlaylistHot().toStateFlow(null)
    val topSongs = neteaseRepo.loadTopPlaylistSongs().toStateFlow(null)

    fun refresh() {
    }

    fun clickSong(song: Songs) {
        viewModelScope.launch {
            val result = neteaseRepo.loadSongDetail("${song.id}")
            "clickSong-> $result".logi(TAG)
        }
    }
}