package com.monebac.com.jetpack.coroutines


sealed class LoadState(var msg: String) {
    //sealed类是一种特殊的父类，它只允许内部继承，所以在与when表达式合用来判断状态时很适合。
    // 其中Fail状态必须指定错误信息，其他的状态信息可为空
    class Loading(msg: String = "") : LoadState(msg)
    class Success(msg: String = "") : LoadState(msg)
    class Fail(msg: String) : LoadState(msg)
}