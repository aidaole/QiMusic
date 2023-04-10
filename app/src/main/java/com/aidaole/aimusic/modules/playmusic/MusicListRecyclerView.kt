package com.aidaole.aimusic.modules.playmusic

import android.content.Context
import android.util.AttributeSet
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.PagerSnapHelper
import androidx.recyclerview.widget.RecyclerView
import com.aidaole.base.utils.logi

class MusicListRecyclerView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : RecyclerView(context, attrs, defStyleAttr) {
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
                    RecyclerView.SCROLL_STATE_IDLE -> {
                        val view = snapHelper.findSnapView(layoutManager)!!
                        val position = recyclerView.getChildAdapterPosition(view)
                        if (currentPosition != position) {
                            // 滑动了
                            "onScrollStateChanged-> $position".logi(TAG)
                        }
                        currentPosition = position
                    }
                    RecyclerView.SCROLL_STATE_DRAGGING -> {

                    }
                    RecyclerView.SCROLL_STATE_SETTLING -> {

                    }
                }
            }
        })
    }
}