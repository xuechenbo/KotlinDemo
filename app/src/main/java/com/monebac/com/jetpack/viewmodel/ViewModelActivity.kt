package com.monebac.com.jetpack.viewmodel

import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.monebac.com.R
import com.monebac.com.jetpack.BaseJetActivity
import com.monebac.com.utils.getMap
import kotlinx.android.synthetic.main.act_view_model.*
import kotlinx.android.synthetic.main.layout_title.*
import org.jetbrains.anko.toast

class ViewModelActivity : BaseJetActivity() {
    /**
     * 可以解决数据不一保存问题
     * ViewModel
     * MVVM   中的VM
     * 将界面和数据分离
     */
    private var mViewModel: RequViewModel? = null


    override fun initLayout(): Int {
        return R.layout.act_view_model
    }

    private val viewModel by lazy {
        ViewModelProviders.of(this, object : ViewModelProvider.Factory {
            override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                //TODO 传参数
                val repository = ""
                return RequViewModel(repository) as T
            }
        }).get(RequViewModel::class.java)
    }

    override fun initData() {
        tv_title.text = "ViewModel"
        back.setOnClickListener { finish() }

        mViewModel = ViewModelProviders.of(this).get(RequViewModel::class.java)
        btnLogin.setOnClickListener {
            mViewModel!!.loadUsers(getMap(mutableMapOf(
                    "1" to "15239585210",
                    "3" to "190928",
                    "8" to "123456")))
        }
        mViewModel!!.userData.observe(this, Observer {
            toast(it)
        })
    }
}
