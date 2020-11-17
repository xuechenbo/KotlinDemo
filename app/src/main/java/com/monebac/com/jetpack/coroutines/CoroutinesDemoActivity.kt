package com.monebac.com.jetpack.coroutines

import androidx.lifecycle.Observer
import com.monebac.com.R
import com.monebac.com.jetpack.jetbase.BaseVmActivity
import com.monebac.com.utils.getMap
import com.monebac.com.wkyk.Constant
import kotlinx.android.synthetic.main.act_coroutines_demo.*
import kotlinx.android.synthetic.main.layout_title.*
import org.jetbrains.anko.toast

class CoroutinesDemoActivity : BaseVmActivity<CoroutinecViewModel>() {

    override fun layoutRes() = R.layout.act_coroutines_demo

    override fun viewModelClass() = CoroutinecViewModel::class.java

    override fun initData() {

        tv_title.text = "登录"
        back.setOnClickListener { finish() }

        btn.setOnClickListener {
            mViewModel.getData(getMap(mutableMapOf(
                    "1" to "13858069996",
                    "3" to "190928",
                    "8" to Constant.Md5("123456"))))
        }
    }

    override fun initView() {

    }

    override fun observe() {
        super.observe()
        mViewModel.loginMsg.observe(this, Observer {
            toast(it)
        })
    }
}