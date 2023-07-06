package com.aidaole.aimusic.modules.user

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import coil.load
import com.aidaole.aimusic.R
import com.aidaole.aimusic.databinding.FragmentUserinfoBinding
import com.aidaole.aimusic.databinding.TestStringItemViewBinding
import com.aidaole.aimusic.framework.ViewBindingFragment
import com.aidaole.base.datas.entities.RespUserInfo
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class UserInfoFragment : ViewBindingFragment<FragmentUserinfoBinding>() {
    override fun getViewBinding(): FragmentUserinfoBinding = FragmentUserinfoBinding.inflate(layoutInflater)
    private val userinfoVM: UserInfoViewModel by viewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        fitStatusBarHeight()
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onStart() {
        super.onStart()
        userinfoVM.loadUserInfo()
    }

    override fun initViews() {
        layout.loginPageBtn.setOnClickListener {
            findNavController().navigate(R.id.action_global_login_graph)
        }
        layout.recyclerview.layoutManager = LinearLayoutManager(context)
        layout.recyclerview.adapter = UserProfileListAdapter(mutableListOf<String>().apply {
            add("1")
            add("2")
            add("3")
            add("4")
            add("5")
            add("6")
            add("7")
            add("8")
            add("9")
            add("10")
        })
    }

    override fun initViewModels() {
        userinfoVM.userInfoData.observe(viewLifecycleOwner) { userInfo ->
            if (userInfo == null) {
                findNavController().navigate(R.id.action_global_login_graph)
            } else {
                if (userInfo.profile != null) {
                    updateUserProfileUi(userInfo.profile)
                }
            }
        }
    }

    override fun doAfterInit() {

    }

    private fun updateUserProfileUi(it: RespUserInfo.ProfileEntity) {
        layout.avatarImg.load(it.avatarUrl) {
            error(R.mipmap.ic_launcher)
        }
        layout.userName.text = it.nickname
        layout.userSignature.text = it.signature
    }
}

class UserProfileListAdapter(val datas: MutableList<String> = mutableListOf()) :
    RecyclerView.Adapter<UserProfileListAdapter.StringViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StringViewHolder {
        return StringViewHolder.create(parent)
    }

    override fun onBindViewHolder(holder: StringViewHolder, position: Int) {
        val text = datas[position]
        holder.bind(text)
    }

    override fun getItemCount(): Int = datas.size

    class StringViewHolder(itemView: View) : ViewHolder(itemView) {
        private val layout = TestStringItemViewBinding.bind(itemView)

        companion object {
            fun create(parent: ViewGroup): StringViewHolder {
                val root = LayoutInflater.from(parent.context).inflate(R.layout.test_string_item_view, parent, false)
                return StringViewHolder(root)
            }
        }

        fun bind(data: String) {
            layout.name.text = data
        }
    }
}