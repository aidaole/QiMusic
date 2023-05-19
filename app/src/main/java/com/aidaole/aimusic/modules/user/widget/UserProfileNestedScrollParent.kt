package com.aidaole.aimusic.modules.user.widget

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.view.ViewTreeObserver.OnGlobalLayoutListener
import android.widget.FrameLayout
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.NestedScrollingParent2
import androidx.core.view.NestedScrollingParentHelper
import androidx.core.view.ViewCompat
import androidx.recyclerview.widget.RecyclerView
import com.aidaole.aimusic.App
import com.aidaole.aimusic.R
import com.aidaole.base.ext.toInvisible
import com.aidaole.base.ext.toVisible
import com.aidaole.base.utils.logi

class UserProfileNestedScrollParent @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr), NestedScrollingParent2 {
    companion object {
        private const val TAG = "UserProfileNestedScrollParent"
    }

    private val parentHelper = NestedScrollingParentHelper(this)
    private lateinit var actionBarLayout: View
    private var actionBarHeight = 0
    private lateinit var songsListBg: View
    private var songListBgHeight = 0
    private lateinit var userInfoLayout: View
    private var userInfoLayoutHeight = 0
    private lateinit var recyclerView: View
    private lateinit var bottomContentContainer: View

    override fun onFinishInflate() {
        super.onFinishInflate()
        actionBarLayout = this.findViewById(R.id.action_bar_layout)
        songsListBg = this.findViewById(R.id.songs_list_bg)
        userInfoLayout = this.findViewById(R.id.user_info_layout)
        recyclerView = this.findViewById(R.id.user_lists)
        bottomContentContainer = this.findViewById(R.id.bottom_content_container)
        actionBarLayout.viewTreeObserver.addOnGlobalLayoutListener(object : OnGlobalLayoutListener {
            override fun onGlobalLayout() {
                actionBarLayout.viewTreeObserver.removeOnGlobalLayoutListener(this)
                actionBarHeight = actionBarLayout.measuredHeight
                songListBgHeight = songsListBg.measuredHeight
                userInfoLayoutHeight = userInfoLayout.measuredHeight
            }
        })
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        "onMeasure-> ".logi(TAG)

        val heightSize = MeasureSpec.getSize(heightMeasureSpec)
        val newHeightSpec = MeasureSpec.makeMeasureSpec(heightSize, MeasureSpec.UNSPECIFIED)

        "onMeasure-> parent: ${this@UserProfileNestedScrollParent.measuredHeight}, actionbar: ${actionBarLayout.measuredHeight}".logi(
            TAG
        )
        val spec2 = MeasureSpec.makeMeasureSpec(
            this@UserProfileNestedScrollParent.measuredHeight,
            MeasureSpec.UNSPECIFIED
        )
        bottomContentContainer.measure(widthMeasureSpec, spec2)
        val recyclerviewHeightSpec = MeasureSpec.makeMeasureSpec(
            this@UserProfileNestedScrollParent.measuredHeight - actionBarLayout.measuredHeight,
            MeasureSpec.UNSPECIFIED
        )
        recyclerView.measure(widthMeasureSpec, recyclerviewHeightSpec)

        super.onMeasure(widthMeasureSpec, newHeightSpec)
    }

    override fun onLayout(changed: Boolean, left: Int, top: Int, right: Int, bottom: Int) {
        super.onLayout(changed, left, top, right, bottom)
        val childCount = childCount
        var topMargin = 0
        for (i in 0 until childCount) {
            val childView = getChildAt(i)
            "onLayout-> ${childView}".logi(TAG)
            if (childView.visibility != View.GONE) {
                if (childView is ConstraintLayout) {
                    "onLayout-> ${App.getScreenHeight()}".logi(TAG)
                    val parentHeight = this@UserProfileNestedScrollParent.measuredHeight + (userInfoLayoutHeight + 120)
                    childView.layout(0, topMargin, childView.measuredWidth, parentHeight)
                    topMargin += parentHeight
                } else {
                    childView.layout(0, topMargin, childView.measuredWidth, topMargin + childView.measuredHeight)
                    topMargin += childView.measuredHeight
                }
            }
        }
    }

    override fun onStartNestedScroll(child: View, target: View, axes: Int, type: Int): Boolean {
        return (ViewCompat.SCROLL_AXIS_VERTICAL and axes) != 0
    }

    override fun onNestedScrollAccepted(child: View, target: View, axes: Int, type: Int) {
        parentHelper.onNestedScrollAccepted(child, target, axes, type)
    }

    override fun onNestedPreScroll(target: View, dx: Int, dy: Int, consumed: IntArray, type: Int) {
        val topGap = songListBgHeight + userInfoLayoutHeight - actionBarHeight
//        "onNestedPreScroll-> dy: $dy, scrollY: $scrollY, topGap: $topGap".logi(TAG)
        if (dy > 0) {
            // 向上
            if (scrollY < topGap) {
                if (dy + scrollY > topGap) {
                    consumed[1] = dy
                    scrollTo(0, topGap)
                } else {
                    consumed[1] = dy
                    scrollBy(0, dy)
                }
            }
        } else if (dy < 0) {
            // 向下
            if (recyclerView.canScrollVertically(-1)) {
                // 可向下, parent不消费
            } else if (scrollY > 0) {
                if (dy + scrollY < 0) {
                    consumed[1] = dy
                    scrollTo(0, 0)
                } else {
                    consumed[1] = dy
                    scrollBy(0, dy)
                }
            }
        }

        if (scrollY >= topGap) {
            actionBarLayout.toVisible()
        } else if (scrollY < topGap) {
            actionBarLayout.toInvisible()
        }
    }

    override fun onStopNestedScroll(target: View, type: Int) {
        parentHelper.onStopNestedScroll(target, type)
    }

    override fun onNestedScroll(
        target: View,
        dxConsumed: Int,
        dyConsumed: Int,
        dxUnconsumed: Int,
        dyUnconsumed: Int,
        type: Int
    ) {
    }
}