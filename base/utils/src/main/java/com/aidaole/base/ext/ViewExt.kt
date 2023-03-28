package com.aidaole.base.ext

import android.view.View

fun View.toVisible() {
    this.visibility = View.VISIBLE
}

internal fun View.toGone() {
    this.visibility = View.GONE
}

internal fun View.toInvisible() {
    this.visibility = View.INVISIBLE
}

internal fun View.isVisible() = this.visibility == View.VISIBLE