package com.monebac.com.jetpack.coroutines

import android.util.Log
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.monebac.com.R
import com.monebac.com.jetpack.BaseJetActivity
import kotlinx.android.synthetic.main.act_coroutines_demo.*
import kotlinx.android.synthetic.main.layout_title.*
import org.jetbrains.anko.toast

class CoroutinesDemoActivity : BaseJetActivity() {

    private val viewmodel by lazy {
        //新版写法
        ViewModelProvider(this)[CoroutinecViewModel::class.java]
        //旧版写法
//        ViewModelProviders.of(this).get(CoroutinecViewModel::class.java)
    }

    override fun initLayout(): Int = R.layout.act_coroutines_demo

    override fun initData() {

        tv_title.text = "coroutines"
        back.setOnClickListener { finish() }

        btn.setOnClickListener {
            viewmodel.getData()
        }

        viewmodel.loadState.observe(this, Observer {
            when (it) {
                is LoadState.Fail -> {
                    Log.e("测试", "fail")
                }
                else -> {
                    Log.e("测试", "else")
                }
            }
        })

        viewmodel.imageData.observe(this, Observer {
            Log.e("测试", "imageData")

            toast(it)
        })
    }
}