package com.aidaole.aimusic.media

import android.media.MediaPlayer
import android.os.Handler
import android.os.Looper

class MusicPlayer : MediaPlayer.OnPreparedListener, MediaPlayer.OnCompletionListener {
    companion object {
        private const val NOTIFY_PROGRESS_MSG = 1
        private const val NOTIFY_PROGRESS_DURATION = 1000L
    }

    private val mediaPlayer by lazy { MediaPlayer() }
    private val msgHandler = Handler(Looper.getMainLooper()) { msg ->
        when (msg.what) {
            NOTIFY_PROGRESS_MSG -> {
                onProcessChangeListener?.onProcessChange(
                    ((mediaPlayer.currentPosition * 1.0 / mediaPlayer.duration) * 100).toInt()
                )
                sendProcessMessage()
            }
        }
        return@Handler true
    }

    private fun sendProcessMessage() {
        msgHandler.sendEmptyMessageDelayed(NOTIFY_PROGRESS_MSG, NOTIFY_PROGRESS_DURATION)
    }


    var onProcessChangeListener: OnProcessChangeListener? = null
        set(value) {
            field = value
        }

    init {
        mediaPlayer.setOnPreparedListener(this)
        mediaPlayer.setOnCompletionListener(this)
    }

    fun play(songUrl: String) {
        mediaPlayer.reset()
        mediaPlayer.setDataSource(songUrl)
        mediaPlayer.prepare()
    }

    override fun onPrepared(mp: MediaPlayer?) {
        mediaPlayer.start()
        sendProcessMessage()
    }

    override fun onCompletion(mp: MediaPlayer?) {
        msgHandler.removeMessages(NOTIFY_PROGRESS_MSG)
    }

    interface OnProcessChangeListener {
        fun onProcessChange(process: Int)
    }
}