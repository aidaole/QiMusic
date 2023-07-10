package com.aidaole.aimusic.modules

import android.widget.FrameLayout
import android.widget.ImageView
import androidx.core.view.children
import androidx.fragment.app.activityViewModels
import com.aidaole.aimusic.R
import com.aidaole.aimusic.databinding.FragmentMainBinding
import com.aidaole.aimusic.framework.ViewBindingFragment
import com.aidaole.aimusic.modules.explore.ExploreFragment
import com.aidaole.aimusic.modules.playmusic.PlayMusicFragment
import com.aidaole.aimusic.modules.user.UserInfoFragment

class MainFragment : ViewBindingFragment<FragmentMainBinding>() {

    private val mainViewModel by activityViewModels<MainViewModel>()

    override fun getViewBinding(): FragmentMainBinding {
        return FragmentMainBinding.inflate(layoutInflater)
    }

    override fun initViews() {
        layout.pageContainer.apply {
            init(
                parentFragmentManager, mapOf(
                    ExploreFragment::class.java.simpleName to ExploreFragment(),
                    PlayMusicFragment::class.java.simpleName to PlayMusicFragment(),
                    UserInfoFragment::class.java.simpleName to UserInfoFragment()
                )
            )
            layout.menuMusic.tag = PlayMusicFragment::class.java.simpleName
            layout.menuExplore.tag = ExploreFragment::class.java.simpleName
            layout.menuUser.tag = UserInfoFragment::class.java.simpleName

            layout.menuMusic.setOnClickListener {
                mainViewModel.naviTo(MainPage.MUSIC)
            }
            layout.menuExplore.setOnClickListener {
                mainViewModel.naviTo(MainPage.EXPLORE)
            }
            layout.menuUser.setOnClickListener {
                mainViewModel.naviTo(MainPage.USER)
            }
        }
    }

    override fun initViewModels() {
        mainViewModel.pageTag.observe(viewLifecycleOwner) {
            naviToPage(it)
        }
    }

    override fun doAfterInit() {

    }

    private fun naviToPage(tag: String) {
        layout.pageContainer.navigate(tag)
        layout.bottomTabsContainer.run {
            when (tag) {
                PlayMusicFragment::class.java.simpleName -> {
                    background = null
                }
                else -> {
                    setBackgroundResource(R.color.explore_bg_end_color)
                }
            }

            children.filter {
                (it is FrameLayout) or (it is ImageView)
            }.forEach {
                if (it is ImageView) {
                    it.isSelected = it == view
                } else {
                    (it as FrameLayout).children.forEach { item ->
                        item.isSelected = item == view
                    }
                }
            }
        }
    }
}