package com.aidaole.aimusic.modules.explore

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aidaole.base.datas.NeteaseRepo
import com.aidaole.aimusic.utils.toStateFlow
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ExploreViewModel @Inject constructor(
    private val neteaseRepo: NeteaseRepo
) : ViewModel() {
    companion object {
        private const val TAG = "ExploreViewModel"
    }

    val recommendPlayList = neteaseRepo.loadPlaylistHighQuality().toStateFlow(null, viewModelScope)
    val hotplaylistTags = neteaseRepo.loadPlaylistHot().toStateFlow(null, viewModelScope)
    val topSongs = neteaseRepo.loadTopPlaylistSongs().toStateFlow(null, viewModelScope)
}