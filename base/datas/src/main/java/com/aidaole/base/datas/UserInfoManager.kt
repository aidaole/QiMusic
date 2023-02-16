package com.aidaole.base.datas

import android.content.Context
import android.content.SharedPreferences
import androidx.core.content.edit

object UserInfoManager {

    private const val PREF_USERINFO = "pref_userinfo"
    private const val KEY_USER = "key_user"

    fun writeUserInfoToSp(context: Context, userInfo: String?) {
        getSp(context).edit {
            putString(KEY_USER, userInfo ?: "")
            commit()
        }
    }

    fun getUserInfoFromSp(context: Context): String {
        return getSp(context).getString(KEY_USER, "") ?: ""
    }

    private fun getSp(context: Context): SharedPreferences {
        return context.getSharedPreferences(PREF_USERINFO, Context.MODE_PRIVATE)
    }
}