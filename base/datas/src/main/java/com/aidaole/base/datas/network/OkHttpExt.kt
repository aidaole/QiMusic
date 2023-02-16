package com.aidaole.base.datas.network

import com.aidaole.base.datas.CookieManager
import com.google.gson.Gson
import okhttp3.Request

fun createRequest(url: String, withCookie: Boolean = true): Request.Builder {
    return Request.Builder().apply {
        this.url(url)
        if (withCookie) {
            addHeader("Cookie", CookieManager.cookie)
        }
    }
}

fun <T : Any> String.toJsonObject(gson: Gson, clazz: Class<T>): T {
    return gson.fromJson(this, clazz) as T
}