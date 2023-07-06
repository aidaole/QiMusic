package com.aidaole.aimusic.modules.login

import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.aidaole.aimusic.R
import com.aidaole.aimusic.databinding.FragmentLoginBinding
import com.aidaole.aimusic.framework.ViewBindingFragment
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
    }

    override fun initViewModels() {
    }

    override fun doAfterInit() {
    }
}