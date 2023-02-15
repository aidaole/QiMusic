package com.aidaole.base.datas.network

import com.aidaole.base.datas.entities.QrCheckParams
import com.aidaole.base.datas.entities.RespQrImg
import com.aidaole.base.datas.entities.RespQrKey
import com.aidaole.base.utils.logi
import com.google.gson.Gson
import okhttp3.OkHttpClient
import okhttp3.Request

class NeteaseApiImpl(
    private val okHttpClient: OkHttpClient,
    private val gson: Gson
) : NeteaseApi {
    companion object {
        private const val TAG = "NeteaseApiImpl"
        const val BASE_URL = "http://10.101.81.229:3000"
    }

    private fun String.addTimeStamp(): String {
        return if (this.contains("?")) {
            "${this}&timestamp=${System.currentTimeMillis()}"
        } else {
            "${this}?timestamp=${System.currentTimeMillis()}"
        }
    }

    override fun getQrImg(): QrCheckParams? {
        val qrKey = getLoginQrKey() ?: return null
        "1. getQrImg-> qrKey: $qrKey".logi(TAG)
        val qrImgBase64 = getLoginQrImg(qrKey) ?: return null
        "2. getQrImg-> img base64: $qrImgBase64 ".logi(TAG)
        return QrCheckParams(qrImgBase64, qrKey)
    }

    override fun checkQrScaned(qrKey: String): String? {
        val request = Request.Builder()
            .url("$BASE_URL/login/qr/check?key=$qrKey".addTimeStamp())
            .build()
        okHttpClient.newCall(request).execute().use {
            if (it.isSuccessful) {
                return it.body?.string()
//                val respContent = gson.fromJson(it.body?.string(), RespQrImg::class.java)
//                if (respContent.code == 200) {
//                    return respContent.data.qrimg
//                }
            }
        }
        return null
    }

    private fun getLoginQrKey(): String? {
        val request = Request.Builder()
            .url("$BASE_URL/login/qr/key".addTimeStamp())
            .build()
        okHttpClient.newCall(request).execute().use {
            if (it.isSuccessful) {
                val respContent = gson.fromJson(it.body?.string(), RespQrKey::class.java)
                if (respContent.code == 200) {
                    return respContent.data.unikey
                }
            }
        }
        return null
    }

    private fun getLoginQrImg(qrKey: String): String? {
        val request = Request.Builder()
            .url("$BASE_URL/login/qr/create?key=$qrKey&qrimg=true".addTimeStamp())
            .build()
        okHttpClient.newCall(request).execute().use {
            if (it.isSuccessful) {
                val respContent = gson.fromJson(it.body?.string(), RespQrImg::class.java)
                if (respContent.code == 200) {
                    return respContent.data.qrimg
                }
            }
        }
        return null
    }
}