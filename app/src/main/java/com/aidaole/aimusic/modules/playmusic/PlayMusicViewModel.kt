package com.aidaole.aimusic.modules.playmusic

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.aidaole.base.datas.NeteaseRepo
import com.aidaole.base.datas.StateValue
import com.aidaole.base.datas.entities.RespPlayList
import com.aidaole.base.datas.entities.RespSongs
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.single
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PlayMusicViewModel @Inject constructor(
    private val neteaseApi: NeteaseRepo,
    application: Application
) : AndroidViewModel(application) {

    private val _playingSongs = MutableLiveData<StateValue<MutableList<RespSongs.Song>?>>()
    val playingSongs = _playingSongs as LiveData<StateValue<MutableList<RespSongs.Song>?>>

    private val _currentSongIndex = MutableLiveData(0)
    private val currentSongIndex = _currentSongIndex as LiveData<Int>
    fun setCurrentSongIndex(position: Int) {
        _currentSongIndex.value = position
    }

    fun playList(playList: RespPlayList.PlaylistsEntity) {
        viewModelScope.launch {
            val songs = neteaseApi.loadPlaylistTrackAll(playList.id).single() ?: emptyList()
            val oldRespSongs = _playingSongs.value?.value ?: emptyList()
            _playingSongs.value = StateValue.Succ(mutableListOf<RespSongs.Song>().apply {
                addAll(oldRespSongs)
                addAll(currentSongIndex.value!!, songs)
            })
        }
    }

    fun play(song: RespSongs.Song) {
        val oldSongLists = _playingSongs.value?.value ?: emptyList()
        val sameSongInList = oldSongLists.singleOrNull {
            it.id == song.id
        }
        if (sameSongInList == null) {
            _playingSongs.value = StateValue.Succ(mutableListOf<RespSongs.Song>().apply {
                addAll(oldSongLists)
                add(currentSongIndex.value!!, song)
            })
        }
    }

    fun loadDefaultTopSongs() {
        viewModelScope.launch {
            val topSongs = neteaseApi.loadTopPlaylistSongs().single()
            when (topSongs) {
                is StateValue.Succ -> {
                    _playingSongs.value = StateValue.Succ(topSongs.value!!.songs)
                }
                is StateValue.Fail -> {
                    _playingSongs.value = StateValue.Fail(null)
                }
            }
        }
    }
}