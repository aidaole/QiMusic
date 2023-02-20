package com.aidaole.base.datas

open class StateValue<T>(
    val value: T? = null,
    val state: Int = 0
) {
    companion object {
        const val STATE_SUCC = 1
        const val STATE_FAIL = -1
//        fun <T> succ(value: T, state: Int = 1) = StateValue(value, state)
//        fun <T> fail(value: T? = null, state: Int = -1) = StateValue(value, state)
    }

    class succ<T>(v: T? = null, s: Int = 1) : StateValue<T>(v, s)
    class fail<T>(v: T? = null, s: Int = -1) : StateValue<T>(v, s)
}