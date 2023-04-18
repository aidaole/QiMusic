package com.aidaole.base.datas.network.retrofit.calladapter

import retrofit2.Call
import retrofit2.CallAdapter
import java.lang.reflect.Type

class RespCallAdapter<R>(private val responseType: Type): CallAdapter<R, Any> {

    override fun responseType(): Type = responseType

    override fun adapt(call: Call<R>): Any = Resp(call)
}