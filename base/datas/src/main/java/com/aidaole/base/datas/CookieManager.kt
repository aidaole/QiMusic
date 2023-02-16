package com.aidaole.base.datas

import okhttp3.Cookie

object CookieManager {

    val cookieStore: HashMap<String, List<Cookie>> = HashMap()

    var cookie = ""
}