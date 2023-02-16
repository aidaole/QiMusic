package com.aidaole.aimusic.modules.user

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aidaole.aimusic.App
import com.aidaole.base.datas.UserInfoManager
import com.aidaole.base.datas.entities.RespUserInfo
import com.aidaole.base.datas.network.NeteaseApi
import com.aidaole.base.datas.network.toJsonObject
import com.aidaole.base.utils.logi
import com.google.gson.Gson
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class UserInfoViewModel @Inject constructor(
    private val neteaseApi: NeteaseApi,
    private val gson: Gson
) : ViewModel() {
    companion object {
        private const val TAG = "UserInfoViewModel"
    }

    val userInfoData = MutableLiveData<RespUserInfo?>(null)

    fun loadUserInfo() {
        viewModelScope.launch {
            coroutineIO {
                val spInfo = UserInfoManager.getUserInfoFromSp(App.getContext())
                var userinfo: RespUserInfo? = null
                if (spInfo.isNotBlank()) {
                    userinfo = spInfo.toJsonObject(gson, RespUserInfo::class.java)
                }
                userInfoData.postValue(userinfo)
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