package com.monebac.com.jetpack.jetbase

import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.monebac.com.R

abstract class BaseVmActivity<VM : BaseViewModel> : BaseActivity() {

    protected open lateinit var mViewModel: VM

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initViewModel()
        observe()
        initView()
        initData()
    }

    /**
     * 初始化ViewModel
     */
    private fun initViewModel() {
        mViewModel = ViewModelProvider(this)[viewModelClass()]
    }

    protected abstract fun viewModelClass(): Class<VM>

    abstract fun initView()

    abstract fun initData()

    open fun observe() {
//        mViewModel.loading.observe(this, Observer {
//            if (it) showProgressDialog(R.string.loading) else dismissProgressDialog()
//        })
    }
}
