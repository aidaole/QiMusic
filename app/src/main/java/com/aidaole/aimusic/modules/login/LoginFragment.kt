package com.aidaole.aimusic.modules.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.aidaole.aimusic.R
import com.aidaole.aimusic.databinding.FragmentLoginBinding
import com.aidaole.aimusic.databinding.FragmentQrLoginBinding
import com.aidaole.aimusic.databinding.FragmentUserinfoBinding
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class LoginFragment : Fragment() {
    companion object {
        private const val TAG = "LoginFragment"
    }

    private val loginVM: LoginViewModel by viewModels()
    private val layout by lazy{
        FragmentLoginBinding.inflate(layoutInflater)
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
        layout.qrLoginBtn.setOnClickListener {
            findNavController().navigate(R.id.action_loginFragment_to_qrLoginFragment)
        }
    }

    private fun initVM() {

    }
}