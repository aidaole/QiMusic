package com.aidaole.aimusic.modules.login

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.util.Base64
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import coil.load
import com.aidaole.aimusic.databinding.FragmentLoginBinding
import com.aidaole.base.utils.logi
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
        loginVM.qrImgUrl.observe(this.viewLifecycleOwner) { qrParams ->
            qrParams?.let {
                layout.qrImg.load(it.base64Img.base64toBitmap())
                loginVM.checkQrScaned(it.keyCode)
            } ?: run {
                "加载登录二维码失败！".toast(requireContext())
            }
        }
    }

    private fun String.base64toBitmap(): Bitmap? {
        var bitmap: Bitmap? = null
        try {
            val bitmapArray: ByteArray = Base64.decode(this.split(",")[1], Base64.DEFAULT)
            bitmap = BitmapFactory.decodeByteArray(bitmapArray, 0, bitmapArray.size)
        } catch (e: Exception) {
            "base64toBitmap-> e: $e".logi(TAG)
        }
        return bitmap
    }
}