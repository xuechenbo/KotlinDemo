package com.monebac.com.wkyk.contract

import com.monebac.com.base.IView
import com.monebac.com.wkyk.model.BindCard

interface BankCardListContract {
    interface View : IView {
        fun successList(list: List<BindCard>)
        fun failStr(str: String)
    }

    interface Presenter {
        fun getCardList(map: Map<String, String>)
    }
}