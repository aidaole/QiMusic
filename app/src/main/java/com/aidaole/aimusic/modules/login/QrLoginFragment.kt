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
import com.aidaole.aimusic.databinding.FragmentQrLoginBinding
import com.aidaole.base.datas.StateValue
import com.aidaole.base.utils.toast
import dagger.hilt.android.AndroidEntryPoint

/**
 * Qr login fragment
 *
 * @constructor Create empty Qr login fragment
 */
@AndroidEntryPoint
class QrLoginFragment : Fragment() {

    private val loginVM: LoginViewModel by viewModels()
    private val layout by lazy {
        FragmentQrLoginBinding.inflate(layoutInflater)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
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
        loginVM.qrImgBitmap.observe(this.viewLifecycleOwner) { bitmapState ->
            when (bitmapState) {
                is StateValue.Succ -> {
                    layout.qrImg.load(bitmapState.value)
                    loginVM.checkQrScanned()
                    layout.scanText.text = "请用网易云音乐扫码登录"
                }
                is StateValue.Fail -> {
                    layout.qrImg.load(R.mipmap.ic_launcher)
                    layout.scanText.text = "请刷新二维码"
                }
            }
        }
        loginVM.finalQrLoginState.observe(this.viewLifecycleOwner) { state ->
            when (state) {
                is StateValue.Succ -> {
                    "登录成功".toast(requireContext())
                    findNavController().navigate(R.id.action_global_mainFragment)
                }
                is StateValue.Fail -> {
                    "登录失败，请刷新二维码！".toast(requireContext())
                }
            }
        }
    }
}