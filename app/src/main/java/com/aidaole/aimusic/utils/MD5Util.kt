package com.aidaole.aimusic.utils

import com.aidaole.base.ext.logi
import java.security.MessageDigest

class MD5Util

fun String.md5(): String {
    return try {
        val md = MessageDigest.getInstance("MD5")
        val messageDigest = md.digest(toByteArray())
        val hexString = StringBuilder()
        for (b in messageDigest) {
            hexString.append(String.format("%02x", b))
        }
        hexString.toString()
    } catch (e: Exception) {
        "md5-> $e".logi("MD5Util")
        ""
    }
}
