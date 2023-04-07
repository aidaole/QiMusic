package com.aidaole.aimusic.modules

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.ImageView
import androidx.core.view.children
import androidx.fragment.app.Fragment
import com.aidaole.aimusic.databinding.FragmentMainBinding
import com.aidaole.aimusic.modules.explore.ExploreFragment
import com.aidaole.aimusic.modules.playlist.PlayingFragment
import com.aidaole.aimusic.modules.user.UserInfoFragment

class MainFragment : Fragment() {

    private val layout by lazy { FragmentMainBinding.inflate(layoutInflater) }

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
        doAfterInit()
    }

    private fun initViews() {
        layout.pageContainer.apply {
            init(
                childFragmentManager, mapOf(
                    ExploreFragment::class.java.simpleName to ExploreFragment(),
                    PlayingFragment::class.java.simpleName to PlayingFragment(),
                    UserInfoFragment::class.java.simpleName to UserInfoFragment()
                )
            )
            layout.menuMusic.tag = PlayingFragment::class.java.simpleName
            layout.menuExplore.tag = ExploreFragment::class.java.simpleName
            layout.menuUser.tag = UserInfoFragment::class.java.simpleName
        }

        layout.menuMusic.setOnClickListener {
            selectItem(it)
        }
        layout.menuExplore.setOnClickListener {
            selectItem(it)
        }
        layout.menuUser.setOnClickListener {
            selectItem(it)
        }
    }

    private fun doAfterInit() {
        loadDefaultFragment()
    }

    private fun loadDefaultFragment() {
        layout.pageContainer.navigate(PlayingFragment::class.java)
    }

    private fun selectItem(view: View) {
        layout.pageContainer.navigate(view.tag.toString())
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