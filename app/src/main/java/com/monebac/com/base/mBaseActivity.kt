package com.monebac.com.base

import android.content.Context
import android.os.Bundle
import android.support.v4.app.FragmentActivity
import com.monebac.com.R
import com.monebac.com.utils.DialogPress
import com.monebac.com.utils.PreferencesUtil
import com.scwang.smartrefresh.layout.SmartRefreshLayout

abstract class mBaseActivity : FragmentActivity() {
    private lateinit var mContext: Context
    private var progressDialog: DialogPress? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getLayoutResId())
        mContext = this
        initView()
        initData()
    }

    open fun initData() {

    }

    abstract fun initView()

    abstract fun getLayoutResId(): Int


    fun getMerNo(): String = PreferencesUtil.getString("merNo")
    fun getMerId(): String = PreferencesUtil.getString("merId")


    /**
     * 加载进度
     * @param message
     */
    fun showProgress(message: String = "加载中..") {
        showProgress(message, false)
    }

    fun showProgress(message: String, cancelable: Boolean) {
        try {
            if (progressDialog == null) {
                progressDialog = DialogPress(this, R.style.CustomDialog)
                progressDialog!!.setMessage(message)
                progressDialog!!.setCancelable(cancelable)
                progressDialog!!.show()
            } else {
                progressDialog!!.setMessage(message)
                progressDialog!!.setCancelable(cancelable)
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }

    }

    fun hideProgress(smartRefreshLayout: SmartRefreshLayout? = null) {
        smartRefreshLayout?.run {
            finishRefresh()
            finishLoadmore()
        }
        if (progressDialog != null) {
            try {
                progressDialog!!.dismiss()
                progressDialog = null
            } catch (e: Exception) {
                e.printStackTrace()
            }

        }
    }

}