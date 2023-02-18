package com.aidaole.aimusic.modules.login

import android.graphics.Bitmap
import androidx.core.graphics.drawable.toBitmap
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aidaole.aimusic.App
import com.aidaole.aimusic.R
import com.aidaole.base.datas.entities.QrCheckParams
import com.aidaole.base.datas.network.NeteaseApi
import com.aidaole.base.utils.base64toBitmap
import com.aidaole.base.utils.logd
import com.aidaole.base.utils.logi
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val neteaseApi: NeteaseApi
) : ViewModel() {

    companion object {
        private const val TAG = "LoginViewModel"
        private const val STATE_INI = 0
        private const val STATE_SUCC = 1
        private const val STATE_FAIL = -1
    }


    private val defaultQrBitmap =
        App.getContext().resources.getDrawable(R.mipmap.ic_launcher).toBitmap(200, 200)
    private val _qrImgBitmap = MutableLiveData<Bitmap?>(defaultQrBitmap)
    val qrImgBitmap = _qrImgBitmap as LiveData<Bitmap?>

    private var qrCheckParams: QrCheckParams? = null
    private var loginCookie: String = ""
    val finalQrLoginState = MutableLiveData<Int>(STATE_INI)

    fun refreshQr() {
        viewModelScope.launch {
            val qrParams = withContext(Dispatchers.IO) {
                return@withContext neteaseApi.getQrImg()
            }
            qrCheckParams = qrParams
            qrCheckParams?.let {
                "doLogin-> result: $it".logd(TAG, true)
                _qrImgBitmap.value = it.base64Img.base64toBitmap()
            }
        }
    }

    fun checkQrScanned() {
        viewModelScope.launch {
            qrCheckParams?.let {
                val result = withContext(Dispatchers.IO) {
                    var checkTimes = 10
                    var state = STATE_FAIL
                    while (checkTimes > 0) {
                        val scanReps =
                            neteaseApi.getQrScannedCode(it.keyCode) ?: return@withContext STATE_FAIL
                        val code = scanReps.code
                        "checkQrScaned-> code: $code".logi(TAG)
                        when (code) {
                            803 -> {
                                // 成功
                                loginCookie = scanReps.cookie
                                state = STATE_SUCC
                                break
                            }
                            800 -> {
                                // 过期，需要刷新
                                loginCookie = ""
                                _qrImgBitmap.value = defaultQrBitmap
                                break
                            }
                            801, 802 -> {
                                // 待扫码，待确认
                                delay(5 * 1000)
                            }
                            else -> {
                                "checkQrScanned-> code: $code".logi(TAG)
                                break
                            }
                        }
                        checkTimes--
                    }
                    return@withContext state
                }
                if (result < 0) {
                    finalQrLoginState.value = STATE_FAIL
                } else {
                    finalQrLoginState.value = STATE_SUCC
                }
            } ?: run {
                "checkQrScanned-> qrCheckParams:$qrCheckParams".logi(TAG)
            }
        }
    }
}