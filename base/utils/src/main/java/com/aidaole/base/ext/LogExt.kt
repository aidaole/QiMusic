package com.aidaole.base.ext

import android.util.Log

const val TAG = "AiMusic"
const val LOG_SUB_LEN = 2000

fun String.logd(tag: String = TAG, needThreadInfo: Boolean = false) {
    if (this.length > LOG_SUB_LEN) {
        this.splitLog {
            Log.d(tag, it)
        }
    } else {
        Log.d(tag, this)
    }
}

fun String.logi(tag: String = TAG, needThreadInfo: Boolean = false) {
    if (this.length > LOG_SUB_LEN) {
        this.splitLog {
            Log.i(tag, it)
        }
    } else {
        Log.i(tag, this)
    }
}

private fun String.splitLog(lambda: (subLog: String) -> Unit) {
    val sb = StringBuffer(this)
    var starIndex = 0
    while (starIndex * LOG_SUB_LEN < sb.length) {
        lambda(
            this.substring(
                starIndex * LOG_SUB_LEN,
                ((starIndex + 1) * LOG_SUB_LEN).coerceAtMost(sb.length)
            )
        )
        starIndex++
    }
}