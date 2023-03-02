package com.aidaole.aimusic.modules.playlist

import androidx.lifecycle.ViewModel
import com.aidaole.base.datas.network.NeteaseApi
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class PlaylistViewModel @Inject constructor(
    private val neteaseApi: NeteaseApi
) : ViewModel() {

}