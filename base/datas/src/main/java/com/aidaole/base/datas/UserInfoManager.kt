package com.aidaole.base.datas

import android.content.Context
import android.content.SharedPreferences
import com.aidaole.base.datas.entities.RespUserInfo
import com.google.gson.Gson

object UserInfoManager {

    private const val PREF_USERINFO = "pref_userinfo"
    private const val KEY_USER = "key_user"
    private val gson = Gson()
    private var memoryUserInfo: RespUserInfo? = null

    fun writeUserInfoToSp(context: Context, userInfo: String?) {
        getSp(context).edit().run {
            putString(KEY_USER, userInfo ?: "")
            commit()
        }
        userInfo?.let {
            memoryUserInfo = gson.fromJson(it, RespUserInfo::class.java)
        } ?: run {
            memoryUserInfo = null
        }
    }

    fun getUserInfo(context: Context): RespUserInfo? {
        if (memoryUserInfo != null) {
            return memoryUserInfo
        }
        getUserInfoFromSp(context).run {
            if (this.isNotBlank()) {
                return gson.fromJson(this, RespUserInfo::class.java).also {
                    memoryUserInfo = it
                }
            }
        }
        return null
    }

    fun clearUserInfo(context: Context) {
        writeUserInfoToSp(context, null)
    }

    private fun getUserInfoFromSp(context: Context): String {
        return getSp(context).getString(KEY_USER, "") ?: ""
    }

    private fun getSp(context: Context): SharedPreferences {
        return context.getSharedPreferences(PREF_USERINFO, Context.MODE_PRIVATE)
    }
}