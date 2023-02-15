package com.aidaole.aimusic.modules.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aidaole.base.datas.entities.QrCheckParams
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

    private val _qrImgUrl = MutableLiveData<QrCheckParams?>(null)
    val qrImgUrl = _qrImgUrl as LiveData<QrCheckParams?>


    fun refreshQr() {
        viewModelScope.launch {
            val qrParams = withContext(Dispatchers.IO) {
                return@withContext neteaseApi.getQrImg()
            }
            qrParams?.let {
                "doLogin-> result: $it".logd(TAG, true)
                _qrImgUrl.value = qrParams
            }
        }
    }

    fun checkQrScaned(keyCode: String) {
        viewModelScope.launch {
            val result = withContext(Dispatchers.IO) {
                return@withContext neteaseApi.checkQrScaned(keyCode)
            }
        }
    }
}