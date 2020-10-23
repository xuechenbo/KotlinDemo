package com.monebac.com.jetpack.demo

import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.monebac.com.R
import com.monebac.com.base.BaseActivity
import kotlinx.android.synthetic.main.act_second.*
import kotlinx.android.synthetic.main.layout_title.*

class SecondActivity : BaseActivity() {
    override fun initLayout(): Int = R.layout.act_second

    override fun initData() {
        back.setOnClickListener { finish() }
        tv_title.text = "测试"


        val secondViewModel = ViewModelProviders.of(this).get(SecondViewModel::class.java)
        secondViewModel.userData.observe(this, Observer {
            mTvShow.text = "姓名：${it.name} \n年龄：${it.age}"
        })

        btn.setOnClickListener {
            secondViewModel.getUserInfo()
        }

    }


}