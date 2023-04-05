package com.aidaole.aimusic.modules

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.aidaole.aimusic.databinding.FragmentMain2Binding
import com.aidaole.aimusic.databinding.FragmentMainBinding

class MainFragment : Fragment() {

    private val layout by lazy { FragmentMain2Binding.inflate(layoutInflater) }

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
    }
}