package com.aidaole.aimusic.modules.user

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import coil.load
import com.aidaole.aimusic.R
import com.aidaole.aimusic.databinding.FragmentUserinfoBinding
import com.aidaole.aimusic.framework.ViewBindingFragment
import com.aidaole.base.datas.entities.RespUserInfo
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
            findNavController().navigate(R.id.action_global_login_graph)
        }
        userinfoVM.userInfoData.observe(viewLifecycleOwner) { userInfo ->
            userInfo?.let { userinfo ->
                userinfo.profile?.let {
                    updateUserProfileUi(it)
                }
            }
        }
        userinfoVM.loadUserInfo()
    }

    private fun updateUserProfileUi(it: RespUserInfo.ProfileEntity) {
        layout.avatarImg.load(it.avatarUrl) {
            error(R.mipmap.ic_launcher)
        }
        layout.userName.text = it.nickname
        layout.userSignature.text = it.signature
    }
}