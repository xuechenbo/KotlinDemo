package com.monebac.com.jetpack.livedata

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.monebac.com.R
import com.monebac.com.base.BaseActivity
import com.monebac.com.jetpack.demo.UserInfo
import kotlinx.android.synthetic.main.act_livedata.*
import kotlinx.android.synthetic.main.layout_title.*

class LiveDataActivity : BaseActivity() {
    override fun initLayout(): Int = R.layout.act_livedata

    /**
     * 1,LiveData 的实现基于观察者模式
     * 2,LiveData 跟 LifecycleOwner 绑定，能感知生命周期变化，
     * 并且只会在 LifecycleOwner 处于 Active 状态（STARTED/RESUMED）下通知数据改变
     * 3,LiveData 会自动在 DESTROYED 的状态下移除 Observer ，取消订阅，所以不用担心内存泄露
     * MutableLiveData 是LiveData的一个实现类，
     *
     * 定义了postValue和setValue用来通知观察者更新， 回调onChanged
     * postValue异步
     * setValue同步
     *
     * lieveData属于被观察者，UI观察者
     */
    override fun initData() {

        back.setOnClickListener { finish() }
        tv_title.text = "LiveData"

        var userData: MutableLiveData<UserInfo> = MutableLiveData()

        userData.observe(this, Observer {
            tvShow.text = "${it.name} \n ${it.age}"
            finish()
        })

        btn.setOnClickListener {
            userData.postValue(UserInfo("名字", 12))
        }


    }

}