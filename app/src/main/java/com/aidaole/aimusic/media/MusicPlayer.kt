package com.aidaole.aimusic.media

import android.media.MediaPlayer
import android.os.Handler
import android.os.Looper

object MusicPlayer : MediaPlayer.OnPreparedListener, MediaPlayer.OnCompletionListener {
    private const val NOTIFY_PROGRESS_MSG = 1
    private const val NOTIFY_PROGRESS_DURATION = 1000L
    private val stateListeners = mutableSetOf<StateListener>()
    var state = State.STOP
        set(value) {
            field = value
            notifyStateListeners()
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
    var onProcessChangeListener: OnProcessChangeListener? = null

    private fun sendProcessMessage() {
        msgHandler.sendEmptyMessageDelayed(NOTIFY_PROGRESS_MSG, NOTIFY_PROGRESS_DURATION)
    }

    init {
        mediaPlayer.setOnPreparedListener(this)
        mediaPlayer.setOnCompletionListener(this)
    }

    fun play(songUrl: String) {
        mediaPlayer.reset()
        mediaPlayer.setDataSource(songUrl)
        mediaPlayer.prepare()
        state = State.PREPARING
    }

    fun pauseOrStartMusic() {
        if (state == State.PLAYING) {
            mediaPlayer.pause()
            state = State.PAUSE
        } else {
            mediaPlayer.start()
            state = State.PLAYING
            sendProcessMessage()
        }
    }


    override fun onPrepared(mp: MediaPlayer?) {
        mediaPlayer.start()
        state = State.PLAYING
        sendProcessMessage()
    }

    override fun onCompletion(mp: MediaPlayer?) {
        msgHandler.removeMessages(NOTIFY_PROGRESS_MSG)
        onProcessChangeListener?.onProcessChange(100)
        state = State.STOP
    }

    /**
     * Seek progress
     *
     * @param progress
     * max = 100
     */
    fun seekProgress(progress: Int) {
        mediaPlayer.seekTo((mediaPlayer.duration * (progress * 1.0 / 100)).toInt())
    }

    interface OnProcessChangeListener {
        fun onProcessChange(process: Int)
    }

    interface StateListener {
        fun onStateChange(state: State)
    }

    fun registerStateListener(listener: StateListener) {
        stateListeners.add(listener)
    }

    fun unRegisterStateListener(listener: StateListener) {
        stateListeners.remove(listener)
    }

    private fun notifyStateListeners() {
        stateListeners.forEach { it.onStateChange(state) }
    }

    enum class State {
        PLAYING, STOP, PAUSE, PREPARING
    }

}