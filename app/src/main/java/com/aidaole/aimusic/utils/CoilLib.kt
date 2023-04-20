package com.aidaole.aimusic.utils

import coil.ImageLoader
import coil.memory.MemoryCache
import com.aidaole.aimusic.App

val imageLoader = ImageLoader.Builder(App.get())
    .memoryCache { MemoryCache.Builder(App.get()).maxSizePercent(0.25).build() }
    .crossfade(true)
    .build()
