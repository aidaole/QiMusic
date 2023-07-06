package com.aidaole.base.datas

open class StateValue<T>(
    val value: T? = null,
    val state: Int = -1,
    val throwable: Throwable? = null
) {
    class Succ<T>(v: T, s: Int = 200) : StateValue<T>(v, s)
    class Fail<T>(v: T? = null, s: Int = -1) : StateValue<T>(v, s)
    class Exception<T>(v: T? = null, s: Int = -1, t: Throwable? = null) : StateValue<T>(v, s, t)
}