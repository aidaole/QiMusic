package com.aidaole.base.utils

import android.util.Log

const val TAG = "AiMusic"

fun String.logd(tag: String = TAG, needThreadInfo: Boolean = false) {
    if (needThreadInfo) {
        Log.d(tag, "${Thread.currentThread()}, msg: $this")
    } else {
        Log.d(tag, this)
    }
}

fun String.logi(tag: String = TAG, needThreadInfo: Boolean = false) {
    if (needThreadInfo) {
        Log.i(tag, "${Thread.currentThread()}, msg: $this")
    } else {
        Log.i(tag, this)
    }
}