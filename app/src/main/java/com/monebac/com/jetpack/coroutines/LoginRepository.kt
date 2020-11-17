package com.monebac.com.jetpack.coroutines


class LoginRepository {
    suspend fun login(map: MutableMap<String, String>) = NetworkService.apiService.getResult(map)
}