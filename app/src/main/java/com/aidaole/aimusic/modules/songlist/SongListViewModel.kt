package com.aidaole.aimusic.modules.songlist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aidaole.base.datas.entities.RespPlayList
import com.aidaole.base.datas.network.NeteaseApi
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class SongListViewModel @Inject constructor(
    private val neteaseApi: NeteaseApi
) : ViewModel() {

    private val _topPlaylist = MutableLiveData<RespPlayList>()
    val topPlayList = _topPlaylist as LiveData<RespPlayList>

    fun loadSongLists() {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                _topPlaylist.postValue(neteaseApi.loadTopPlayList())
            }
        }
    }
}