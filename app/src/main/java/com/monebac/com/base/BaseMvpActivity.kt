package com.monebac.com.base

abstract class BaseMvpActivity<in V : IView, P : IPresenter<in V>> : mBaseActivity() {


    protected lateinit var presenter: P

    override fun initData() {
        super.initData()
        presenter = createPresenter()
        presenter.attachView(this as V)
    }

    abstract fun createPresenter(): P

}