package com.aidaole.aimusic.modules.user

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import coil.load
import com.aidaole.aimusic.R
import com.aidaole.aimusic.databinding.FragmentUserinfoBinding
import com.aidaole.aimusic.framework.ViewBindingFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class UserInfoFragment : ViewBindingFragment<FragmentUserinfoBinding>() {

    override fun getViewBinding(): FragmentUserinfoBinding = FragmentUserinfoBinding.inflate(layoutInflater)
    private val userinfoVM: UserInfoViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        layout.refreshBtn.setOnClickListener {
            userinfoVM.loadUserInfo()
        }
        layout.loginPageBtn.setOnClickListener {
//            findNavController().navigate(R.id.action_userinfoFragment_to_loginFragment)
        }
        userinfoVM.userInfoData.observe(viewLifecycleOwner) { userInfo ->
            userInfo?.let { userinfo ->
                userinfo.profile?.let {
                    layout.avatarImg.load(it.avatarUrl) {
                        error(R.mipmap.ic_launcher)
                    }
                    layout.nickname.text = it.nickname
                }
            }
        }
        userinfoVM.loadUserInfo()
    }
}