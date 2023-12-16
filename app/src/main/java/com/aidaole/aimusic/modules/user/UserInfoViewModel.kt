package com.aidaole.aimusic.modules.user

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.aidaole.aimusic.App
import com.aidaole.base.datas.NeteaseRepo
import com.aidaole.base.datas.UserInfoManager
import com.aidaole.base.datas.entities.RespUserInfo
import com.aidaole.base.ext.logi
import com.google.gson.Gson
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.single
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class UserInfoViewModel @Inject constructor(
    private val neteaseRepo: NeteaseRepo,
    private val gson: Gson,
    private val app: Application
) : AndroidViewModel(app) {
    companion object {
        private const val TAG = "UserInfoViewModel"
        private const val LOGIN_OUT_FLAG = -10
    }

    private val _userInfoData = MutableLiveData(RespUserInfo())
    val userInfoData = _userInfoData as LiveData<RespUserInfo?>

    fun loadUserInfo() {
        viewModelScope.launch(Dispatchers.IO) {
            // TODO: 检查登录失效，暂时不要不要
            val loginStatus = neteaseRepo.checkUserLoginStatus().single()
            if (loginStatus?.status == LOGIN_OUT_FLAG) {
                "loadUserInfo-> 登录失效".logi(TAG)
                neteaseRepo.logout()
                UserInfoManager.clearUserInfo(app)
            }
            var userinfo = UserInfoManager.getUserInfo(App.get())
            if (userinfo == null) {
                _userInfoData.postValue(null)
            }
            _userInfoData.postValue(userinfo)
            "loadUserInfo-> userInfo: $userinfo".logi(TAG)
        }
    }
}

suspend inline fun coroutineIO(crossinline runBody: () -> Unit) {
    withContext(Dispatchers.IO) {
        runBody.invoke()
    }
}