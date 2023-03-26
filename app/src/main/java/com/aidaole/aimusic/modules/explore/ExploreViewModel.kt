package com.aidaole.aimusic.modules.explore

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aidaole.base.datas.entities.HotPlayListTags
import com.aidaole.base.datas.entities.PlayListSongs
import com.aidaole.base.datas.entities.RespPlayList
import com.aidaole.base.datas.network.NeteaseApi
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.shareIn
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class ExploreViewModel @Inject constructor(
    private val neteaseApi: NeteaseApi
) : ViewModel() {

    private val _recommendPlaylist = MutableStateFlow<RespPlayList?>(null)
    val recommendPlayList = _recommendPlaylist as StateFlow<RespPlayList?>

    private val _hotplaylistTags = MutableLiveData<HotPlayListTags>()
    val hotplaylistTags = _hotplaylistTags as LiveData<HotPlayListTags>

    private val _topSongs = MutableLiveData<PlayListSongs>()
    val topSongs = _topSongs as LiveData<PlayListSongs>

    fun loadRecommendLists() {
        viewModelScope.launch {
            neteaseApi.loadTopPlayList().collect {
                _recommendPlaylist.value = it
            }
        }
    }

    fun loadHotPlaylistTags() {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                _hotplaylistTags.postValue(neteaseApi.loadHotPlaylistTags())
            }
        }
    }

    fun loadTopPlaylistSongs() {
        viewModelScope.launch(Dispatchers.IO) {
            _topSongs.postValue(neteaseApi.loadTopPlaylistSongs())
        }
    }
}