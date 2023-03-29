package com.aidaole.aimusic.modules.explore

import androidx.lifecycle.ViewModel
import com.aidaole.base.datas.network.NeteaseApi
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.*
import javax.inject.Inject

@HiltViewModel
class ExploreViewModel @Inject constructor(
    private val neteaseApi: NeteaseApi
) : ViewModel() {

    val recommendPlayList = neteaseApi.loadTopPlayList()
    val hotplaylistTags = neteaseApi.loadHotPlaylistTags()
    val topSongs = neteaseApi.loadTopPlaylistSongs()

    fun refresh() {

    }
}

fun <T> Flow<T>.toStateFlow(scope: CoroutineScope, initialValue: T): StateFlow<T> =
    this.onStart { emit(initialValue) }
        .stateIn(scope, SharingStarted.Eagerly, initialValue)