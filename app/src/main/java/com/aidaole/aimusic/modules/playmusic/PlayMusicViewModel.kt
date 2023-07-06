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
import com.aidaole.utils.ext.toStateFlow
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

    val playingSongsList = neteaseApi.loadTopPlaylistSongs().toStateFlow(null, viewModelScope)

    fun playList(playList: RespPlayList.PlaylistsEntity) {
        viewModelScope.launch {
            val songs = neteaseApi.loadPlaylistTrackAll(playList.id).single()
            songs?.let {
                _playingSongs.value = StateValue.Succ(it)
            }
        }
    }

    fun play(song: RespSongs.Song) {
        val inPlaylist = playingSongs.value!!.value?.singleOrNull {
            it.id == song.id
        }
        if (inPlaylist == null) {
            _playingSongs.value =
                StateValue.Succ(mutableListOf<RespSongs.Song>().apply {
                    add(0, song)
                    addAll(_playingSongs.value!!.value!!)
                })
        }
    }
}