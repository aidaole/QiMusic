package com.aidaole.aimusic.modules.user.widget

import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.view.View
import android.view.ViewTreeObserver.OnGlobalLayoutListener
import android.widget.FrameLayout
import android.widget.LinearLayout
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.NestedScrollingParent2
import androidx.core.view.NestedScrollingParentHelper
import androidx.core.view.ViewCompat
import com.aidaole.aimusic.App
import com.aidaole.aimusic.R
import com.aidaole.base.ext.toInvisible
import com.aidaole.base.ext.toVisible
import com.aidaole.base.utils.logi

class UserProfileNestedScrollParent @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : LinearLayout(context, attrs, defStyleAttr), NestedScrollingParent2 {
    companion object {
        private const val TAG = "UserProfileNestedScrollParent"
    }

    private val parentHelper = NestedScrollingParentHelper(this)
    private lateinit var actionBarLayout: View
    private var actionBarHeight = 0
    private lateinit var topBg: View
    private var topBgHeight = 0
    private lateinit var userInfoLayout: View
    private var userInfoLayoutHeight = 0
    private lateinit var recyclerView: View
    private lateinit var bottomContentContainer: View

    override fun onFinishInflate() {
        super.onFinishInflate()
        actionBarLayout = this.findViewById(R.id.action_bar_layout)
        topBg = this.findViewById(R.id.top_bg)
        bottomContentContainer = this.findViewById(R.id.bottom_content_container)
        userInfoLayout = this.findViewById(R.id.user_info_layout)
        recyclerView = this.findViewById(R.id.recyclerview)
        actionBarLayout.viewTreeObserver.addOnGlobalLayoutListener(object : OnGlobalLayoutListener {
            override fun onGlobalLayout() {
                actionBarLayout.viewTreeObserver.removeOnGlobalLayoutListener(this)
                actionBarHeight = actionBarLayout.measuredHeight
                topBgHeight = topBg.measuredHeight
                userInfoLayoutHeight = userInfoLayout.measuredHeight
                "onGlobalLayout-> actionbarheight: $actionBarHeight".logi(TAG)
                "onGlobalLayout-> songlisbgheight: $topBgHeight".logi(TAG)
                "onGlobalLayout-> userinfolayoutheight: $userInfoLayoutHeight".logi(TAG)
            }
        })
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        val heightSize = MeasureSpec.getSize(heightMeasureSpec)
        val newHeightSpec = MeasureSpec.makeMeasureSpec(heightSize, MeasureSpec.UNSPECIFIED)

        ("onMeasure-> " +
                "screenHeight: ${App.getScreenHeight()}, " +
                "parent: ${this@UserProfileNestedScrollParent.measuredHeight}, " +
                "actionbar: ${actionBarLayout.measuredHeight}, " +
                "topBg: ${topBg.measuredHeight}, " +
                "userInfoLayout: ${userInfoLayout.measuredHeight}, " +
                "bottomContentContainer: ${bottomContentContainer.measuredHeight}")
            .logi(TAG)
        super.onMeasure(widthMeasureSpec, newHeightSpec)
    }

    override fun onStartNestedScroll(child: View, target: View, axes: Int, type: Int): Boolean {
        return (ViewCompat.SCROLL_AXIS_VERTICAL and axes) != 0
    }

    override fun onNestedScrollAccepted(child: View, target: View, axes: Int, type: Int) {
        parentHelper.onNestedScrollAccepted(child, target, axes, type)
    }

    override fun onNestedPreScroll(target: View, dx: Int, dy: Int, consumed: IntArray, type: Int) {
        val topGap = topBgHeight + userInfoLayoutHeight - actionBarHeight
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

        computeActionBarAlpha(scrollY, topGap)
    }

    private fun computeActionBarAlpha(scrollY: Int, topGap: Int) {
        if (scrollY >= topGap) {
            actionBarLayout.toVisible()
            userInfoLayout.toInvisible()
        } else if (scrollY < topGap) {
            actionBarLayout.toInvisible()
            userInfoLayout.toVisible()
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