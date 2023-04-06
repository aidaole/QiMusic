package com.aidaole.aimusic.modules

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.ImageView
import androidx.core.view.children
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.aidaole.aimusic.R
import com.aidaole.aimusic.databinding.FragmentMainBinding
import com.aidaole.base.utils.toast

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
    }

    private fun initViews() {
        layout.menuMusic.setOnClickListener {
            selectItem(it)
        }
        layout.menuExplore.setOnClickListener {
            selectItem(it)
            "点击了explore".toast(context)
            layout.modulesContainer.findNavController().navigate(R.id.exploreFragment)
        }
        layout.menuUser.setOnClickListener {
            selectItem(it)
            "点击了user".toast(context)
            layout.modulesContainer.findNavController().navigate(R.id.userinfoFragment)
        }
    }

    private fun selectItem(view: View) {
        layout.bottomTabs.children.filter {
            (it is FrameLayout) //  or (it is ImageView)
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