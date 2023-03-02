package com.aidaole.aimusic.modules.playlist

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

    fun loadDefaultPlayList() {
        val userInfo = UserInfoManager.getUserInfo(App.getContext())
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                neteaseApiImpl.loadTopPlayList()
            }
        }
    }
}