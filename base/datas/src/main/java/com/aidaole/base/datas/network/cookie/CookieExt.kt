package com.aidaole.base.datas.network.cookie

import okhttp3.Cookie
import org.json.JSONArray
import org.json.JSONObject

fun String.toCookieList(): List<Cookie> {
    val cookieList = mutableListOf<Cookie>()
    JSONArray(this).run {
        for (i in 0 until this.length()) {
            cookieList.add(getJSONObject(i).toCookie())
        }
    }
    return cookieList
}

fun List<Cookie>.toJsonStr(): String {
    return JSONArray().apply {
        this@toJsonStr.forEach { cookie ->
            this.put(cookie.toJson())
        }
    }.toString()
}

fun Cookie.toJson(): JSONObject {
    return JSONObject().apply {
        put("name", this@toJson.name)
        put("value", this@toJson.value)
        put("expiresAt", this@toJson.expiresAt)
        put("domain", this@toJson.domain)
        put("path", this@toJson.path)
        put("secure", this@toJson.secure)
        put("httpOnly", this@toJson.httpOnly)
        put("hostOnly", this@toJson.hostOnly)
        put("persistent", this@toJson.persistent)
    }
}

fun JSONObject.toCookie(): Cookie {
    val builder = Cookie.Builder()
    this.run {
        builder.name(getString("name"))
        builder.value(getString("value"))
        builder.expiresAt(getLong("expiresAt"))
        builder.path(getString("path"))
        val domain = getString("domain")
        if (getBoolean("hostOnly")) {
            builder.hostOnlyDomain(domain)
        } else {
            builder.domain(domain)
        }
        if (getBoolean("secure")) {
            builder.secure()
        }
        if (getBoolean("httpOnly")) {
            builder.httpOnly()
        }
    }
    return builder.build()
}