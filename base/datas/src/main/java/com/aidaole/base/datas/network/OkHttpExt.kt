package com.aidaole.base.datas.network

import com.aidaole.base.datas.CookieManager
import okhttp3.Request

fun createRequest(url: String): Request.Builder {
    return Request.Builder()
        .url(url)
        .addHeader("Cookie", CookieManager.cookie)
}