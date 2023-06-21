package com.aidaole.aimusic.modules

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.ImageView
import androidx.core.view.children
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.aidaole.aimusic.databinding.FragmentMainBinding
import com.aidaole.aimusic.modules.explore.ExploreFragment
import com.aidaole.aimusic.modules.playmusic.PlayMusicFragment
import com.aidaole.aimusic.modules.user.UserInfoFragment

class MainFragment : Fragment() {

    private val layout by lazy { FragmentMainBinding.inflate(layoutInflater) }
    private val mainViewModel by activityViewModels<MainViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return layout.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
        initViewModels()
        doAfterInit()
    }

    private fun initViews() {
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

    private fun initViewModels() {
        mainViewModel.pageTag.observe(viewLifecycleOwner) {
            naviToPage(it)
        }
    }

    private fun doAfterInit() {

    }

    private fun naviToPage(tag: String) {
        layout.pageContainer.navigate(tag)
        layout.bottomTabs.children.filter {
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