package com.aidaole.aimusic.modules

import androidx.fragment.app.activityViewModels
import com.aidaole.aimusic.R
import com.aidaole.aimusic.databinding.FragmentMainBinding
import com.aidaole.aimusic.framework.ViewBindingFragment
import com.aidaole.aimusic.media.MusicPlayer
import com.aidaole.aimusic.modules.explore.ExploreFragment
import com.aidaole.aimusic.modules.playmusic.PlayMusicFragment
import com.aidaole.aimusic.modules.user.UserInfoFragment
import com.aidaole.base.ext.toGone
import com.aidaole.base.ext.toVisible

class MainFragment : ViewBindingFragment<FragmentMainBinding>(), MusicPlayer.StateListener {

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
                if (!mainViewModel.isPage(MainPage.MUSIC)) {
                    mainViewModel.naviTo(MainPage.MUSIC)
                } else {
                    MusicPlayer.pauseOrStartMusic()
                }
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
        MusicPlayer.registerStateListener(this)
    }

    override fun onDestroy() {
        super.onDestroy()
        MusicPlayer.unRegisterStateListener(this)
    }

    private fun naviToPage(tag: String) {
        layout.pageContainer.navigate(tag)
        layout.bottomTabsContainer.run {
            // 清理选中状态
            listOf(layout.menuUserImg, layout.menuExploreImg, layout.menuUserImg).forEach {
                it.isSelected = false
            }
            // 选中状态+navbar背景
            when (tag) {
                PlayMusicFragment::class.java.simpleName -> {
                    background = null
                    layout.menuMusicImg.isSelected = true
                    toggleMusicPlayState(true)
                }

                ExploreFragment::class.java.simpleName -> {
                    setBackgroundResource(R.color.explore_bg_end_color)
                    layout.menuExploreImg.isSelected = true
                    toggleMusicPlayState(false)
                }

                UserInfoFragment::class.java.simpleName -> {
                    setBackgroundResource(R.color.explore_bg_end_color)
                    layout.menuUserImg.isSelected = true
                    toggleMusicPlayState(false)
                }
            }
        }
    }

    private fun toggleMusicPlayState(isMusicMenuSelected: Boolean) {
        if (isMusicMenuSelected) {
            layout.musicPlayLottie.toGone()
            layout.menuMusicImg.toVisible()
            if (MusicPlayer.state == MusicPlayer.State.PLAYING) {
                layout.menuMusicImg.setImageResource(R.drawable.ic_music_pause)
            } else {
                layout.menuMusicImg.setImageResource(R.drawable.ic_music_play)
            }
        } else {
            if (MusicPlayer.state == MusicPlayer.State.PLAYING) {
                layout.musicPlayLottie.toVisible()
                layout.menuMusicImg.toGone()
            } else {
                layout.musicPlayLottie.toGone()
                layout.menuMusicImg.toVisible()
                layout.menuMusicImg.setImageResource(R.drawable.ic_music_play)
            }
        }
    }

    override fun onStateChange(state: MusicPlayer.State) {
        toggleMusicPlayState(mainViewModel.isPage(MainPage.MUSIC))
    }
}