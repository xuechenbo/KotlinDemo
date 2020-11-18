package com.monebac.com.http

data class ApiException(var errCode: Int, var errMsg: String) : Exception()