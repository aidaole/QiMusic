package com.aidaole.aimusic.modules

import android.view.ViewGroup
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
            // 清理选中状态
            children.forEach {
                if (it is ImageView) {
                    it.isSelected = false
                } else if (it is ViewGroup) {
                    it.children.forEach {
                        it.isSelected = false
                    }
                }
            }
            // 选中状态+navbar背景
            when (tag) {
                PlayMusicFragment::class.java.simpleName -> {
                    background = null
                    layout.menuMusic.isSelected = true
                }
                ExploreFragment::class.java.simpleName -> {
                    setBackgroundResource(R.color.explore_bg_end_color)
                    layout.menuExploreImg.isSelected = true
                }
                UserInfoFragment::class.java.simpleName -> {
                    setBackgroundResource(R.color.explore_bg_end_color)
                    layout.menuUserImg.isSelected = true
                }
                else -> {
                    throw RuntimeException("naviToPage error: $tag")
                }
            }
        }
    }
}