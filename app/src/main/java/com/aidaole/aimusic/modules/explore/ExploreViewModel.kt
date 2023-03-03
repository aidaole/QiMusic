package com.aidaole.aimusic.modules.explore

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aidaole.base.datas.entities.HotPlayListTags
import com.aidaole.base.datas.entities.RespPlayList
import com.aidaole.base.datas.network.NeteaseApi
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class ExploreViewModel @Inject constructor(
    private val neteaseApi: NeteaseApi
) : ViewModel() {

    private val _recommendPlaylist = MutableLiveData<RespPlayList>()
    val recommendPlayList = _recommendPlaylist as LiveData<RespPlayList>

    private val _hotplaylistTags = MutableLiveData<HotPlayListTags>()
    val hotplaylistTags = _hotplaylistTags as LiveData<HotPlayListTags>

    fun loadRecommendLists() {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                _recommendPlaylist.postValue(neteaseApi.loadTopPlayList())
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

    fun loadCatlist() {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                neteaseApi.loadCatlist()
            }
        }
    }
}