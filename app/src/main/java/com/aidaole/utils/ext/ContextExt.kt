package com.aidaole.utils.ext

import android.content.Context
import com.aidaole.aimusic.R

fun Context.getStatusBarHeight(): Int {
    var height = 0
    val resourceId = this.resources.getIdentifier("status_bar_height", "dimen", "android")
    height = if (resourceId > 0) {
        this.resources.getDimensionPixelSize(resourceId)
    } else {
        resources.getDimensionPixelSize(R.dimen.default_statusbar_height)
    }
    return height
}