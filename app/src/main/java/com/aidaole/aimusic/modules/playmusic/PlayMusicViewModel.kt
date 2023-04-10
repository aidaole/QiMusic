package com.aidaole.aimusic.modules.playmusic

import androidx.lifecycle.ViewModel
import com.aidaole.base.datas.network.NeteaseApi
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class PlayMusicViewModel @Inject constructor(
    private val neteaseApi: NeteaseApi
) : ViewModel() {

}