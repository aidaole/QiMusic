package com.aidaole.base.datas.network

import android.content.Context
import com.aidaole.base.datas.entities.QrCheckParams
import com.aidaole.base.datas.entities.RespCheckLoginQr
import com.aidaole.base.datas.entities.RespUserInfo

interface NeteaseApi {
    companion object {
        var BASE_URL = "http://10.101.80.59:3000"
    }

    fun getQrImg(): QrCheckParams?

    fun getQrScannedCode(qrKey: String): RespCheckLoginQr?

    fun getUserInfo(context: Context): RespUserInfo?
}