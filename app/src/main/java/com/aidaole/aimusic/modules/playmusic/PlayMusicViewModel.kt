package com.aidaole.aimusic.modules.playmusic

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.aidaole.base.datas.NeteaseRepo
import com.aidaole.base.datas.entities.RespSongs
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class PlayMusicViewModel @Inject constructor(
    private val neteaseApi: NeteaseRepo,
    application: Application
) : AndroidViewModel(application) {

    private val _playingSongs = MutableLiveData<MutableList<RespSongs.Song>>()
    val playingSongs = _playingSongs as LiveData<MutableList<RespSongs.Song>>

    init {
        _playingSongs.value = mutableListOf()
    }

    fun play(song: RespSongs.Song) {
        val inPlaylist = playingSongs.value?.singleOrNull {
            it.id == song.id
        }
        if (inPlaylist == null) {
            _playingSongs.value =
                mutableListOf<RespSongs.Song>().apply {
                    add(0, song)
                    addAll(_playingSongs.value!!)
                }
        }
    }
}