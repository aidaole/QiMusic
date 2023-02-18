package com.aidaole.aimusic.modules.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import coil.load
import com.aidaole.aimusic.R
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
        layout.userinfoBtn.setOnClickListener {
            findNavController().navigate(R.id.action_loginFragment_to_userinfoFragment)
        }
    }

    private fun initVM() {
        loginVM.qrImgBitmap.observe(this.viewLifecycleOwner) { bitmap ->
            bitmap?.let {
                layout.qrImg.load(bitmap)
                loginVM.checkQrScanned()
            } ?: run {
                "加载登录二维码失败！".toast(requireContext())
            }
        }
        loginVM.finalQrLoginState.observe(this.viewLifecycleOwner) { state ->
            when {
                state > 0 -> {
                    "登录成功".toast(requireContext())
                }
                state < 0 -> {
                    "登录失败，请刷新二维码！".toast(requireContext())
                }
            }
        }
    }
}