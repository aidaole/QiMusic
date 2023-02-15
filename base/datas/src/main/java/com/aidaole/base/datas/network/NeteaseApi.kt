package com.aidaole.base.datas.network

import com.aidaole.base.datas.entities.QrCheckParams

interface NeteaseApi {

    fun getQrImg(): QrCheckParams?

    fun checkQrScaned(qrKey: String): String?
}