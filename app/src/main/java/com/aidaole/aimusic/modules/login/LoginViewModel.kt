package com.aidaole.aimusic.modules.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aidaole.base.datas.network.NeteaseApi
import com.aidaole.base.utils.logd
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val neteaseApi: NeteaseApi
) : ViewModel() {

    companion object {
        private const val TAG = "LoginViewModel"
    }

    fun doLogin(username: String, password: String) {
        viewModelScope.launch {
            val result = withContext(Dispatchers.IO) {
                return@withContext neteaseApi.login(username, password)
            }
            "doLogin-> result: $result".logd(TAG, true)
        }
    }
}