package com.aidaole.base.datas.network

import com.aidaole.base.utils.logi
import okhttp3.FormBody
import okhttp3.OkHttpClient
import okhttp3.Request

class NeteaseApiImpl(
    private val okHttpClient: OkHttpClient
) : NeteaseApi {
    companion object {
        const val BASE_URL = "http://192.168.31.148:3000"
    }

    fun String.addTimeStamp(): String {
        if (this.contains("?")) {
            return "${this}&${System.currentTimeMillis()}"
        } else {
            return "${this}?timestamp=${System.currentTimeMillis()}"
        }
    }

    override fun login(username: String, password: String): String {
        "login-> username:$username, password: $password".logi(needThreadInfo = true)
        val request = Request.Builder()
            .url("$BASE_URL/login/cellphone".addTimeStamp())
            .post(
                FormBody.Builder()
                    .add("phone", username)
                    .add("password", password)
                    .build()
            )
            .build()
        val resp = okHttpClient.newCall(request).execute().use {
            if (it.isSuccessful) {
                val result = it.body?.string() ?: "error"
                "login-> result: $result".logi()
                return result
            }
        }
        return "failed"
    }
}