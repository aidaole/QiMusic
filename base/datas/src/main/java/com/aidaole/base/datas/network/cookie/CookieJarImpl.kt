package com.aidaole.base.datas.network.cookie

import android.content.Context
import com.aidaole.base.datas.CookieManager
import com.aidaole.base.ext.logi
import okhttp3.Cookie
import okhttp3.CookieJar
import okhttp3.HttpUrl

class CookieJarImpl(val context: Context) : CookieJar {
    companion object {
        private const val TAG = "CookieJarImpl"
    }

    private val cookieStore = mutableMapOf<String, List<Cookie>>()

    override fun loadForRequest(url: HttpUrl): List<Cookie> {
        var cookies = cookieStore[url.host] ?: emptyList()
        if (cookies.isEmpty()) {
            "loadForRequest-> 为空，尝试从sp中读取".logi(TAG)
            cookies = CookieManager.getCookies(context, url.host)
            cookieStore[url.host] = cookies
        }
        "loadForRequest-> url: ${url.host}, size: ${cookies.size}".logi(TAG)
        return cookies
    }

    override fun saveFromResponse(url: HttpUrl, cookies: List<Cookie>) {
        "saveFromResponse-> url: ${url.host}".logi(TAG)
        val host = url.host
        val cacheCookies = cookieStore[host]
        val newCookies = mutableListOf<Cookie>()
        cookies.forEach { newCookie ->
            newCookies.add(newCookie)
        }
        "saveFromResponse-> cacheCookieSize: ${cacheCookies?.size}".logi(TAG)
        cacheCookies?.forEach {
            val findCookie = newCookies.find { newCookie -> it.path == newCookie.path && it.name == newCookie.name }
            if (findCookie == null) {
                newCookies.add(it)
            }
        }
        cookieStore[host] = newCookies
        newCookies.forEach {
            "saveFromResponse-> 保存cookie: $it".logi(TAG)
        }
        CookieManager.saveCookies(url, newCookies, context)
    }
}