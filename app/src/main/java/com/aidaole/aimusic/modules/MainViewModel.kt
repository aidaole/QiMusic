package com.aidaole.aimusic.modules

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.aidaole.aimusic.modules.explore.ExploreFragment
import com.aidaole.aimusic.modules.playmusic.PlayMusicFragment
import com.aidaole.aimusic.modules.user.UserInfoFragment
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor() : ViewModel() {
    private val _pageTag = MutableLiveData(PlayMusicFragment::class.java.simpleName)
    val pageTag = _pageTag as LiveData<String>

    fun naviTo(page: MainPage) {
        _pageTag.value = when (page) {
            MainPage.EXPLORE -> ExploreFragment::class.java.simpleName
            MainPage.MUSIC -> PlayMusicFragment::class.java.simpleName
            MainPage.USER -> UserInfoFragment::class.java.simpleName
            else -> {
                throw RuntimeException("Wrong page: $page")
            }
        }
    }
}

enum class MainPage {
    EXPLORE, MUSIC, USER
}