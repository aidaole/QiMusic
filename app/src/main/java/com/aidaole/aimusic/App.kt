package com.aidaole.aimusic

import android.app.Application
import android.content.Context
import dagger.hilt.android.HiltAndroidApp
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob

@HiltAndroidApp
class App : Application() {

    companion object {
        private lateinit var appContext: Context
        fun getContext(): Context {
            return appContext
        }

        val mainScope = CoroutineScope(SupervisorJob() + Dispatchers.Main)
        val workScope = CoroutineScope(SupervisorJob() + Dispatchers.Default)
        val ioScope = CoroutineScope(SupervisorJob() + Dispatchers.IO)
    }

    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)
        appContext = this
    }
}