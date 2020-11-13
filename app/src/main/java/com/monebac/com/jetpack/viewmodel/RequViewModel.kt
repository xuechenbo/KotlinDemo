package com.monebac.com.jetpack.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.monebac.com.wkyk.netutils.RetrofitNet
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class RequViewModel(repository: String) : ViewModel() {
    /* 1.MutableLiveData的父类是LiveData
     2.LiveData在实体类里可以通知指定某个字段的数据更新.
     3.MutableLiveData则是完全是整个实体类或者数据类型变化后才通知.不会细节到某个字段*/

    var userData: MutableLiveData<String> = MutableLiveData()

    override fun onCleared() {
        super.onCleared()
        //TODO 释放资源
    }

    fun loadUsers(map: Map<String, String>) {
        RetrofitNet().api.getRequeData(map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe {
                    if (it.str39 == "00") {
                        userData.postValue(it.str39)
                    } else {
                        userData.postValue(it.str39)
                    }
                }
    }

    fun getLoginMsg() {

    }

}