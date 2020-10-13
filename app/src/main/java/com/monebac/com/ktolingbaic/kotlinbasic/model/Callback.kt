package com.monebac.com.ktolingbaic.kotlinbasic.model

class Callback() {

    private var onShowMsg: OnShowMessage? = null


    internal fun getFun() {
        if (onShowMsg != null) {
            onShowMsg!!.showMsg()
        }
    }


    internal fun setOnShowMsg(onShowMsg: OnShowMessage) {
        this.onShowMsg = onShowMsg
    }

    internal interface OnShowMessage {
        fun showMsg()
    }
}