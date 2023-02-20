package com.aidaole.base.datas.network

import com.google.gson.Gson
import okhttp3.Request

fun createRequest(url: String, addTimeStamp: Boolean = true): Request.Builder {
    return Request.Builder().apply {
        this.url(if (addTimeStamp) url.addTimeStamp() else url)
    }
}

private fun String.addTimeStamp(): String {
    return if (this.contains("?")) {
        "${this}&timestamp=${System.currentTimeMillis()}"
    } else {
        "${this}?timestamp=${System.currentTimeMillis()}"
    }
}

fun <T : Any> String.toJsonObject(gson: Gson, clazz: Class<T>): T {
    return gson.fromJson(this, clazz) as T
}