package com.aidaole.aimusic.modules.user

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aidaole.aimusic.App
import com.aidaole.base.datas.NeteaseRepo
import com.aidaole.base.datas.UserInfoManager
import com.aidaole.base.datas.entities.RespUserInfo
import com.aidaole.base.ext.logi
import com.google.gson.Gson
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class UserInfoViewModel @Inject constructor(
    private val neteaseRepo: NeteaseRepo,
    private val gson: Gson
) : ViewModel() {
    companion object {
        private const val TAG = "UserInfoViewModel"
    }

    private val _userInfoData = MutableLiveData(RespUserInfo())
    val userInfoData = _userInfoData as LiveData<RespUserInfo?>

    fun loadUserInfo() {
        viewModelScope.launch {
            coroutineIO {
                var userinfo = UserInfoManager.getUserInfo(App.get())
                if (userinfo == null) {
                    _userInfoData.postValue(null)
                }
                _userInfoData.postValue(userinfo)
                "loadUserInfo-> userInfo: $userinfo".logi(TAG)
            }
        }
    }
}

suspend inline fun coroutineIO(crossinline runBody: () -> Unit) {
    withContext(Dispatchers.IO) {
        runBody.invoke()
    }
}