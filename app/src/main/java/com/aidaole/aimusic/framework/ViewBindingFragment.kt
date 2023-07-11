package com.aidaole.aimusic.framework

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding
import com.aidaole.base.ext.getStatusBarHeight

abstract class ViewBindingFragment<T : ViewBinding> : Fragment() {
    internal lateinit var layout: T

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        layout = getViewBinding()
    }

    abstract fun getViewBinding(): T

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return layout.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
        initViewModels()
        doAfterInit()
    }

    fun fitStatusBarHeight() {
        layout.root.setPadding(0, requireContext().getStatusBarHeight(), 0, 0)
    }

    abstract fun initViews()
    abstract fun initViewModels()
    abstract fun doAfterInit()
}
