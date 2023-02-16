package com.aidaole.base.datas.network

import com.aidaole.base.datas.entities.QrCheckParams
import com.aidaole.base.datas.entities.RespCheckLoginQr

interface NeteaseApi {

    fun getQrImg(): QrCheckParams?

    fun getQrScannedCode(qrKey: String): RespCheckLoginQr?
}