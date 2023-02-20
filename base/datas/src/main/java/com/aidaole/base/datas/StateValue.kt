package com.aidaole.base.datas

open class StateValue<T>(
    val value: T? = null,
    val state: Int = 0
) {
    class Succ<T>(v: T? = null, s: Int = 0) : StateValue<T>(v, s)
    class Fail<T>(v: T? = null, s: Int = 0) : StateValue<T>(v, s)
}