package com.monebac.com.jetpack.jetbase

import android.os.Bundle
import androidx.lifecycle.ViewModelProvider

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

    }
}
