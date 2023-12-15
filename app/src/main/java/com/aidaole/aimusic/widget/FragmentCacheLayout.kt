package com.aidaole.aimusic.widget

import android.content.Context
import android.util.AttributeSet
import android.widget.FrameLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.commit
import com.aidaole.aimusic.modules.explore.ExploreFragment
import com.aidaole.aimusic.modules.playmusic.PlayMusicFragment
import com.aidaole.aimusic.modules.user.UserInfoFragment

class FragmentCacheLayout @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {
    companion object {
        private const val TAG = "FragmentCacheLayout"
    }

    private lateinit var fragmentManager: FragmentManager

    fun init(manager: FragmentManager) {
        fragmentManager = manager
    }

    fun navigate(tag: String) {
        fragmentManager.commit {
            fragmentManager.fragments.forEach {
                hide(it)
            }
            val findFragment = fragmentManager.findFragmentByTag(tag)
            if (findFragment != null) {
                show(findFragment)
            } else {
                val newFragment = createFragment(tag)
                add(id, newFragment, tag)
            }
        }
    }

    private fun createFragment(tag: String): Fragment {
        return when (tag) {
            ExploreFragment::class.java.simpleName -> ExploreFragment()
            PlayMusicFragment::class.java.simpleName -> PlayMusicFragment()
            UserInfoFragment::class.java.simpleName -> UserInfoFragment()
            else -> throw IllegalStateException()
        }
    }
}