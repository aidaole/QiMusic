package com.aidaole.base.utils

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Base64

fun String.base64toBitmap(): Bitmap? {
    var bitmap: Bitmap? = null
    try {
        val bitmapArray: ByteArray = Base64.decode(this.split(",")[1], Base64.DEFAULT)
        bitmap = BitmapFactory.decodeByteArray(bitmapArray, 0, bitmapArray.size)
    } catch (e: Exception) {
        "base64toBitmap-> e: $e".logi(TAG)
    }
    return bitmap
}