package com.monebac.com.jetpack.demo

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SecondViewModel : ViewModel() {

    var userData: MutableLiveData<UserInfo> = MutableLiveData()

    /**
     *  模拟获取数据
     */
    fun getUserInfo() {
        val user = UserInfo("李四", (10..90).random())
        userData.postValue(user)
    }
}