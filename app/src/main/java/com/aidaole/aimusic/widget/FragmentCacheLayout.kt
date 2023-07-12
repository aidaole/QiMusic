package com.aidaole.aimusic.widget

import android.content.Context
import android.util.AttributeSet
import android.widget.FrameLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.commit
import com.aidaole.base.ext.logi

class FragmentCacheLayout @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {
    companion object {
        private const val TAG = "FragmentCacheLayout"
    }

    private lateinit var fragmentContainer: Map<String, Fragment>
    private lateinit var fragmentManager: FragmentManager

    fun init(manager: FragmentManager, keyFragments: Map<String, Fragment>) {
        fragmentManager = manager
        fragmentContainer = keyFragments
    }

    fun navigate(tag: String) {
        fragmentManager.commit {
            fragmentContainer.forEach { (name, fragment) ->
                if (tag == name) {
                    if (fragmentManager.findFragmentByTag(name) == null) {
                        add(id, fragment, name)
                    }
                    "navigate-> show ${fragment.javaClass.simpleName}".logi(TAG)
                    show(fragment)
                } else {
                    if (fragmentManager.findFragmentByTag(name) != null) {
                        hide(fragment)
                    }
                    "navigate-> hide ${fragment.javaClass.simpleName}".logi(TAG)
                }
            }
        }
    }

    fun navigate(clazz: Class<out Fragment>) {
        fragmentManager.commit {
            fragmentContainer.forEach { (name, fragment) ->
                if (clazz.simpleName == fragment.javaClass.simpleName) {
                    if (fragmentManager.findFragmentByTag(name) == null) {
                        add(id, fragment, name)
                    }
                    "navigate-> show ${fragment.javaClass.simpleName}".logi(TAG)
                    show(fragment)
                } else {
                    if (fragmentManager.findFragmentByTag(name) != null) {
                        hide(fragment)
                    }
                    "navigate-> hide ${fragment.javaClass.simpleName}".logi(TAG)
                }
            }
        }
    }
}