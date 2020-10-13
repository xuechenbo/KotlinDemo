package com.monebac.com.wkyk.contract

import com.monebac.com.base.IView
import com.monebac.com.wkyk.netutils.BaseResp

interface LoginContract {
    interface View : IView {
        fun onLoginSuccess(baseResp: BaseResp)
        fun onLoginFail(str: String)
    }

    interface Presenter {
        fun LoginSub(map: Map<String, String>)
    }


}