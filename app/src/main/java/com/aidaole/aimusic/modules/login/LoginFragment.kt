package com.aidaole.aimusic.modules.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import coil.load
import com.aidaole.aimusic.databinding.FragmentLoginBinding
import com.aidaole.base.utils.toast
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class LoginFragment : Fragment() {
    companion object {
        private const val TAG = "LoginFragment"
    }

    private val loginVM: LoginViewModel by viewModels()
    private val layout: FragmentLoginBinding by lazy { FragmentLoginBinding.inflate(layoutInflater) }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return layout.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        initVM()
        layout.refreshBtn.setOnClickListener {
            loginVM.refreshQr()
        }
        loginVM.refreshQr()
    }

    private fun initVM() {
        loginVM.qrImgBitmap.observe(this.viewLifecycleOwner) { bitmap ->
            bitmap?.let {
                layout.qrImg.load(bitmap)
                loginVM.checkQrScaned()
            } ?: run {
                "加载登录二维码失败！".toast(requireContext())
            }
        }
        loginVM.finalQrLoginState.observe(this.viewLifecycleOwner) { state ->
            if (state < 0) {
                "登录失败，请刷新二维码！".toast(requireContext())
            } else {
                "登录成功".toast(requireContext())
            }
        }
    }
}