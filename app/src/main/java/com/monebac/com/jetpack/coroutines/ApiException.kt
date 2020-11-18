package com.monebac.com.jetpack.coroutines

class ApiException(var code: Int, override var message: String) : RuntimeException()