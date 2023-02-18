package com.aidaole.base.datas

import android.content.Context
import android.content.SharedPreferences
import androidx.core.content.edit
import com.aidaole.base.datas.network.cookie.toCookieList
import com.aidaole.base.datas.network.cookie.toJsonStr
import com.aidaole.base.utils.logi
import okhttp3.Cookie
import okhttp3.HttpUrl
import java.util.concurrent.atomic.AtomicBoolean

object CookieManager {
    private const val TAG = "CookieManager"
    private const val SP_NAME = "sp_cookies"
    private lateinit var sp: SharedPreferences
    private val cookieStore: HashMap<String, List<Cookie>> = HashMap()
    private val inited = AtomicBoolean(false)
    private val changed = AtomicBoolean(false)

    fun getCookies(context: Context, host: String): List<Cookie> {
        init(context)
        if (changed.get()) {
            loadCookies()
            changed.set(false)
        }
        return cookieStore[host] ?: emptyList()
    }

    fun saveCookies(url: HttpUrl, cookies: List<Cookie>, context: Context) {
        init(context)
        sp.edit(true) {
            "saveCookies-> ${cookies.toJsonStr()}".logi(TAG)
            putString(url.host, cookies.toJsonStr())
            changed.set(true)
        }
    }

    private fun init(context: Context) {
        if (inited.get()) {
            return
        }
        inited.set(true)
        sp = context.getSharedPreferences(SP_NAME, 0)
        loadCookies()
    }

    private fun loadCookies() {
        sp.all.keys.forEach { host ->
            loadCookie(host)
            "loadCookies-> host: $host".logi(TAG)
        }
    }

    private fun loadCookie(host: String) {
        val cookiesStr = sp.getString(host, "")
        "loadCookie-> cookiesStr: $cookiesStr".logi(TAG)
        if (cookiesStr!!.isNotBlank()) {
            val cookieList = cookiesStr.toCookieList()
            "loadCookies-> ${cookieList.toJsonStr()}".logi(TAG)
            cookieStore[host] = cookieList
        }
    }

}