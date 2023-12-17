package com.aidaole.aimusic.modules.login

import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.aidaole.aimusic.R
import com.aidaole.aimusic.databinding.FragmentLoginBinding
import com.aidaole.aimusic.framework.ViewBindingFragment
import com.aidaole.aimusic.utils.md5
import com.aidaole.base.ext.logi
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class LoginFragment : ViewBindingFragment<FragmentLoginBinding>() {
    companion object {
        private const val TAG = "LoginFragment"
    }

    private val loginVM: LoginViewModel by viewModels()
    override fun getViewBinding(): FragmentLoginBinding = FragmentLoginBinding.inflate(layoutInflater)

    override fun initViews() {
        layout.qrLoginBtn.setOnClickListener {
            findNavController().navigate(R.id.action_loginFragment_to_qrLoginFragment)
        }
        layout.loginBtn.setOnClickListener {
            loginVM.doPhonePasswordLogin(
                layout.inputPhone.text.toString(),
                layout.inputPassword.text.toString().md5()
            )
        }
    }

    override fun initViewModels() {
        loginVM.phonePasswordLoginState.observe(viewLifecycleOwner) {
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