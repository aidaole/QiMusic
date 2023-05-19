package com.aidaole.aimusic

import android.app.Application
import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Handler
import android.view.WindowManager
import dagger.hilt.android.HiltAndroidApp
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import okhttp3.*
import java.io.IOException
import javax.inject.Inject

@HiltAndroidApp
class App : Application() {

    @Inject
    lateinit var okHttpClient: OkHttpClient
    private lateinit var mainHandler: Handler


    companion object {
        private lateinit var appContext: App
        private val windowManager by lazy { appContext.getSystemService(Context.WINDOW_SERVICE) as WindowManager }

        fun get(): App {
            return appContext
        }

        fun getScreenHeight(): Int {
            return windowManager.defaultDisplay.height
        }

        val mainScope = CoroutineScope(SupervisorJob() + Dispatchers.Main)
        val workScope = CoroutineScope(SupervisorJob() + Dispatchers.Default)
        val ioScope = CoroutineScope(SupervisorJob() + Dispatchers.IO)
    }

    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)
        appContext = this
        mainHandler = Handler(mainLooper)
    }

    fun loadImage(url: String, callback: ((Bitmap?) -> Unit)? = null) {
        val request = Request.Builder()
            .url(url)
            .build()

        okHttpClient.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                runMainThread { callback?.invoke(null) }
            }

            override fun onResponse(call: Call, response: Response) {
                val bytes = response.body?.bytes()!!
                val bitmap = BitmapFactory.decodeByteArray(bytes, 0, bytes.size)
                runMainThread { callback?.invoke(bitmap) }
            }
        })
    }

    fun runMainThread(call: () -> Unit) {
        mainHandler.post(call)
    }
}