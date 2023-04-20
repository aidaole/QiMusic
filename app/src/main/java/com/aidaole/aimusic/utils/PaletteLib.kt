package com.aidaole.aimusic.utils

import android.graphics.Color
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import androidx.annotation.DrawableRes
import androidx.core.content.res.ResourcesCompat
import androidx.palette.graphics.Palette
import com.aidaole.aimusic.App

fun getPaletteColor(drawable: Drawable) =
    Palette.from((drawable as BitmapDrawable).bitmap).generate()
        .getLightMutedColor(Color.BLACK)


fun getPaletteColor(@DrawableRes drawableId: Int): Int {
    val drawable = ResourcesCompat.getDrawable(App.getContext().resources, drawableId, null)
    return Palette.from((drawable as BitmapDrawable).bitmap).generate()
        .getLightMutedColor(Color.BLACK)
}