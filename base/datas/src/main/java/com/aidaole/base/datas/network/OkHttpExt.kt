package com.aidaole.base.datas.network

import com.google.gson.Gson
import okhttp3.Request

fun createRequest(url: String): Request.Builder {
    return Request.Builder().apply {
        this.url(url)
    }
}

fun <T : Any> String.toJsonObject(gson: Gson, clazz: Class<T>): T {
    return gson.fromJson(this, clazz) as T
}