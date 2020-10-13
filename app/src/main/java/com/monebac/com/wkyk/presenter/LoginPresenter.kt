package com.monebac.com.wkyk.presenter

import com.monebac.com.base.BasePresenter
import com.monebac.com.wkyk.contract.LoginContract
import com.monebac.com.wkyk.netutils.RetrofitNet
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class LoginPresenter : BasePresenter<LoginContract.View>(), LoginContract.Presenter {
    override fun LoginSub(map: Map<String, String>) {
        getView()!!.showLoading()
        RetrofitNet().api.getRequeData(map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe {
                    getView()!!.dismissLoading()
                    if (it.str39 == "00") {
                        getView()?.onLoginSuccess(it)
                    } else {
                        getView()?.onLoginFail(it.str39)
                    }
                }

    }

}