@file:JvmName("WindowExt")
package com.aidaole.utils.ext

import android.graphics.Color
import android.view.Window
import androidx.core.view.WindowCompat

class WindowExt

fun Window.fitImmersive() {
    WindowCompat.setDecorFitsSystemWindows(this, false)
    this.statusBarColor = Color.TRANSPARENT
    this.navigationBarColor = Color.TRANSPARENT
}