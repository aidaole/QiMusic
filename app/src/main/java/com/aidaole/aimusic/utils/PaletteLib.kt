package com.aidaole.aimusic.utils

import android.graphics.Bitmap
import android.graphics.Color
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import androidx.annotation.DrawableRes
import androidx.core.content.res.ResourcesCompat
import androidx.palette.graphics.Palette
import com.aidaole.aimusic.App

fun getPaletteColor(bitmap: Bitmap): Int {
    return Palette.from(bitmap).generate().darkMutedSwatch?.rgb ?: 0
}


fun getPaletteColor(drawable: Drawable): Int {
    val bitmap = (drawable as BitmapDrawable).bitmap
    return getPaletteColor(bitmap)
}


fun getPaletteColor(@DrawableRes drawableId: Int): Int {
    val drawable = ResourcesCompat.getDrawable(App.get().resources, drawableId, null)
    return Palette.from((drawable as BitmapDrawable).bitmap).generate().getLightMutedColor(Color.BLACK)
}