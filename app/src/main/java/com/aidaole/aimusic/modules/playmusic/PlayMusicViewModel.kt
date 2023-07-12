package com.aidaole.aimusic.modules.playmusic

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.aidaole.base.datas.NeteaseRepo
import com.aidaole.base.datas.StateValue
import com.aidaole.base.datas.entities.RespPlayList
import com.aidaole.base.datas.entities.RespSongs.Song
import com.aidaole.base.ext.logi
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.single
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PlayMusicViewModel @Inject constructor(
    private val neteaseRepo: NeteaseRepo,
    application: Application
) : AndroidViewModel(application) {
    companion object {
        private const val TAG = "PlayMusicViewModel"
    }

    private val _playingSongs = MutableLiveData<StateValue<MutableList<Song>?>>()
    val playingSongs = _playingSongs as LiveData<StateValue<MutableList<Song>?>>

    private val _currentSongIndex = MutableLiveData(0)
    val currentSongIndex = _currentSongIndex as LiveData<Int>

    private val _curPlaySong = MutableLiveData<Song?>()
    val curPlaySong = _curPlaySong as LiveData<Song?>

    fun setCurrentSongIndex(position: Int) {
        _currentSongIndex.value = position
        _curPlaySong.value = _playingSongs.value?.value?.get(position)
    }

    fun playList(playList: RespPlayList.PlaylistsEntity) {
        viewModelScope.launch {
            val songs = neteaseRepo.loadPlaylistTrackAll(playList.id).single() ?: emptyList()
            val oldRespSongs = _playingSongs.value?.value ?: emptyList()
            _playingSongs.value = StateValue.Succ(mutableListOf<Song>().apply {
                addAll(oldRespSongs)
                addAll(currentSongIndex.value!!, songs)

                _curPlaySong.value = this[currentSongIndex.value!!]
            })
        }
    }

    fun play(song: Song) {
        val oldSongLists = _playingSongs.value?.value ?: emptyList()
        val sameSongInList = oldSongLists.singleOrNull {
            it.id == song.id
        }
        if (sameSongInList == null) {
            _playingSongs.value = StateValue.Succ(mutableListOf<Song>().apply {
                addAll(oldSongLists)
                add(currentSongIndex.value!!, song)

                _curPlaySong.value = this[currentSongIndex.value!!]
            })
        }
    }

    fun loadDefaultTopSongs() {
        viewModelScope.launch {
            val topSongs = neteaseRepo.loadTopPlaylistSongs().single()
            when (topSongs) {
                is StateValue.Succ -> {
                    _playingSongs.value = StateValue.Succ(topSongs.value!!.songs)

                    _curPlaySong.value = topSongs.value!!.songs[0]
                    _currentSongIndex.value = 0
                }
                is StateValue.Fail -> {
                    _playingSongs.value = StateValue.Fail(null)
                }
            }
        }
    }

    fun playMusic(song: Song?) {
        song?.let {
            viewModelScope.launch {
                val song = neteaseRepo.getSongUrl(song.id.toString()).single()
            }
        } ?: run {
            "playMusic-> 要play的song为空，出错".logi(TAG)
        }
    }
}