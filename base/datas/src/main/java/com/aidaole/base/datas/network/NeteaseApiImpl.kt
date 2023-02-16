package com.aidaole.base.datas.network

import android.content.Context
import com.aidaole.base.datas.UserInfoManager
import com.aidaole.base.datas.entities.*
import com.aidaole.base.utils.logi
import com.google.gson.Gson
import okhttp3.OkHttpClient

class NeteaseApiImpl(
    private val client: OkHttpClient,
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

    override fun getQrScannedCode(qrKey: String): RespCheckLoginQr? {
        val request =
            createRequest("$BASE_URL/login/qr/check?key=$qrKey".addTimeStamp()).build()
        client.newCall(request).execute().use {
            if (it.isSuccessful) {
                val result = gson.fromJson(it.body?.string(), RespCheckLoginQr::class.java)
                "checkQrScanedCode-> $result".logi(TAG)
                return result
            } else {
                "checkQrScanedCode-> failed: ${it.code}".logi(TAG)
            }
        }
        return null
    }

    override fun getUserInfo(context: Context): RespUserInfo? {
        val request = createRequest("$BASE_URL/user/account".addTimeStamp()).build()
        return client.newCall(request).execute().use {
            if (it.isSuccessful) {
                val result = it.body?.string()
                "getUserInfo-> $result".logi(TAG)
                UserInfoManager.writeUserInfoToSp(context, result)
                return@use result?.toJsonObject(gson, RespUserInfo::class.java)
            } else {
                return@use null
            }
        }
    }

    private fun getLoginQrKey(): String? {
        val request = createRequest("$BASE_URL/login/qr/key".addTimeStamp()).build()
        client.newCall(request).execute().use {
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
        val request =
            createRequest("$BASE_URL/login/qr/create?key=$qrKey&qrimg=true".addTimeStamp()).build()
        client.newCall(request).execute().use {
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