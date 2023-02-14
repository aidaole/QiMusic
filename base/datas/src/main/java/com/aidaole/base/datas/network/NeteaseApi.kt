package com.aidaole.base.datas.network

interface NeteaseApi {

    fun login(username: String, password: String): String
}