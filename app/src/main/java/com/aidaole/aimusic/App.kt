package com.aidaole.aimusic

import android.app.Application
import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Handler
import android.view.WindowManager
import androidx.lifecycle.HasDefaultViewModelProviderFactory
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelLazy
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStore
import dagger.hilt.android.HiltAndroidApp
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import okhttp3.Call
import okhttp3.Callback
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import java.io.IOException
import javax.inject.Inject

@HiltAndroidApp
class App : Application(), HasDefaultViewModelProviderFactory {

    @Inject
    lateinit var okHttpClient: OkHttpClient
    private lateinit var mainHandler: Handler

    val viewModelStore = ViewModelStore()
    lateinit var viewModelFactory: ViewModelProvider.Factory

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

    override fun getDefaultViewModelProviderFactory(): ViewModelProvider.Factory {
        if (viewModelFactory == null) {
            viewModelFactory = ViewModelProvider.AndroidViewModelFactory.getInstance(this)
        }
        return viewModelFactory
    }
}

internal inline fun <reified VM : ViewModel> appViewModels() = ViewModelLazy(VM::class,
    { App.get().viewModelStore },
    { App.get().viewModelFactory })