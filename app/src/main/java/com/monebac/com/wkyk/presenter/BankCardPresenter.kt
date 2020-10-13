package com.monebac.com.wkyk.presenter

import com.monebac.com.base.BasePresenter
import com.monebac.com.utils.getList
import com.monebac.com.wkyk.contract.BankCardListContract
import com.monebac.com.wkyk.netutils.RetrofitNet
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class BankCardPresenter : BasePresenter<BankCardListContract.View>(), BankCardListContract.Presenter {

    override fun getCardList(map: Map<String, String>) {

        getView()!!.showLoading()
        RetrofitNet().api.getRequeData(map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe {
                    getView()!!.dismissLoading()
                    if (it.str39 == "00") {
                        getView()?.successList(getList(it.str57))
                    } else {
                        getView()?.failStr(it.str39)
                    }
                }

    }


}