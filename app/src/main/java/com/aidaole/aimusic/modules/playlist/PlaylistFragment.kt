package com.aidaole.aimusic.modules.playlist

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.aidaole.aimusic.databinding.FragmentPlaylistBinding
import com.aidaole.aimusic.framework.ViewBindingFragment
import com.aidaole.base.datas.UserInfoManager
import com.aidaole.base.utils.logi
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PlaylistFragment : ViewBindingFragment<FragmentPlaylistBinding>() {
    companion object {
        private const val TAG = "PlaylistFragment"
    }

    private val playlistVM: PlaylistViewModel by viewModels()
    override fun getViewBinding(): FragmentPlaylistBinding = FragmentPlaylistBinding.inflate(layoutInflater)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val userinfo = UserInfoManager.getUserInfo(requireContext())
        "onViewCreated-> $userinfo".logi(TAG)
        if (userinfo == null) {
//            findNavController().navigate(R.id.action_playlistFragment_to_loginFragment)
            return
        }
        layout.nickname.text = userinfo.profile?.nickname ?: "用户加载失败，请重新登录"
    }
}