package com.aidaole.aimusic.modules.playlist

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aidaole.aimusic.App
import com.aidaole.base.datas.UserInfoManager
import com.aidaole.base.datas.network.NeteaseApi
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class PlaylistViewModel @Inject constructor(
    private val neteaseApiImpl: NeteaseApi
) : ViewModel() {
    companion object {
        private const val TAG = "PlaylistViewModel"
    }

    fun loadDefaultPlayList() {
        val userInfo = UserInfoManager.getUserInfo(App.getContext())

        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                val list = neteaseApiImpl.loadTopPlayList(userInfo?.account?.id ?: 0)
                list?.playlists?.forEach {
                    Log.i(TAG, "loadDefaultPlayList: ${it.name}, ${it.coverImgUrl}")
                }
            }
        }
    }
}