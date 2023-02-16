package com.aidaole.aimusic.modules.user

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.aidaole.aimusic.R
import com.aidaole.aimusic.databinding.FragmentUserinfoBinding
import com.aidaole.base.datas.UserInfoManager
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class UserInfoFragment : Fragment() {

    private val layout by lazy { FragmentUserinfoBinding.inflate(layoutInflater) }
    private val userinfoVM: UserInfoViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return layout.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        layout.refreshBtn.setOnClickListener {
            userinfoVM.loadUserInfo()
        }
        layout.loginPageBtn.setOnClickListener {
            findNavController().navigate(R.id.action_userinfoFragment_to_loginFragment)
        }
        userinfoVM.userInfoData.observe(viewLifecycleOwner) { userInfo ->
            userInfo?.let {
                layout.userName.text = it.account.userName
            }
        }
        userinfoVM.loadUserInfo()
    }
}