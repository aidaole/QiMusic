package com.aidaole.aimusic.modules.login

import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.aidaole.aimusic.R
import com.aidaole.aimusic.databinding.FragmentCaptchaLoginBinding
import com.aidaole.aimusic.framework.ViewBindingFragment
import com.aidaole.base.ext.logi
import dagger.hilt.android.AndroidEntryPoint

/**
 * Captcha login fragment
 * 验证码登录
 *
 * @constructor Create empty Captcha login fragment
 */
@AndroidEntryPoint
class CaptchaLoginFragment : ViewBindingFragment<FragmentCaptchaLoginBinding>() {
    companion object {
        private const val TAG = "CaptchaLoginFragment"
    }

    private val loginVm: LoginViewModel by viewModels()
    override fun getViewBinding() = FragmentCaptchaLoginBinding.inflate(layoutInflater)

    override fun initViews() {
        layout.getCaptchaBtn.setOnClickListener {
            loginVm.getCaptcha(layout.inputPhone.text.toString())
        }
        layout.loginBtn.setOnClickListener {
            loginVm.doCaptchaLogin(
                layout.inputPhone.text.toString(),
                layout.inputCaptcha.text.toString()
            )
        }
    }

    override fun initViewModels() {
        loginVm.loginResultState.observe(viewLifecycleOwner) {
            when (it.value) {
                1 -> {
                    "登录成功".logi(TAG)
                    findNavController().navigate(R.id.action_global_mainFragment)
                }

                else -> {
                    "登录失败".logi(TAG)
                }
            }
        }
    }

    override fun doAfterInit() {

    }
}