package com.aidaole.aimusic.modules.playmusic

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.SeekBar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.PagerSnapHelper
import androidx.recyclerview.widget.RecyclerView
import com.aidaole.aimusic.R
import com.aidaole.base.datas.entities.RespSongs
import com.aidaole.base.ext.logi

class MusicListRecyclerView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : RecyclerView(context, attrs, defStyleAttr) {

    private var onPositionChangeListener: ((Int) -> Unit)? = null
    private lateinit var playListAdapter: PlayListViewAdapter
    fun setScrollPositionChangeListener(listener: (Int) -> Unit) {
        this.onPositionChangeListener = listener
    }

    companion object {
        private const val TAG = "MusicListRecyclerView"
    }

    private val snapHelper = PagerSnapHelper()
    private val verticalLayoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
    private var currentPosition: Int = 0

    init {
        layoutManager = verticalLayoutManager
        snapHelper.attachToRecyclerView(this)
        addOnScrollListener(object : OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                when (newState) {
                    SCROLL_STATE_IDLE -> {
                        val view = snapHelper.findSnapView(layoutManager)
                        view?.let {
                            val position = recyclerView.getChildAdapterPosition(it)
                            if (currentPosition != position) {
                                // 滑动了
                                "onScrollStateChanged-> $position".logi(TAG)
                            }
                            currentPosition = position
                            onPositionChangeListener?.invoke(currentPosition)
                        }
                    }

                    SCROLL_STATE_DRAGGING -> {

                    }

                    SCROLL_STATE_SETTLING -> {

                    }
                }
            }
        })
    }

    fun setAdapter(adapter: PlayListViewAdapter) {
        super.setAdapter(adapter)
        playListAdapter = adapter
    }

    fun updatePlayingProgress(song: RespSongs.Song?, progress: Int) {
        playListAdapter.datas[currentPosition].progress = progress
        if (PlayListViewAdapter.isSeekBarTouching) return
        song?.let {
            val progressBar = findViewWithTag<View>(song.id).findViewById<SeekBar>(R.id.progress_bar)
            progressBar?.let {
                it.progress = progress
                it.invalidate()
            }
        }
    }

    fun setSeekBarProgressChangeListener(callback: ((Int) -> Unit)) {
        playListAdapter.seekProgressChangeCallback = callback
    }
}