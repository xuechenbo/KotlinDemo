package com.monebac.com.base

import android.app.Activity
import android.content.pm.ActivityInfo
import android.os.Bundle
import android.support.v4.app.FragmentActivity
import android.view.KeyEvent
import com.monebac.com.R
import com.monebac.com.utils.DialogPress
import com.monebac.com.utils.PreferencesUtil
import com.scwang.smartrefresh.layout.SmartRefreshLayout

abstract class BaseActivity : FragmentActivity() {
    lateinit var context: Activity
    private var progressDialog: DialogPress? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        context = this
        setContentView(initLayout())
        //设置数据
        initData()
        //全局设置竖屏
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
    }

    /**
     * 设置布局
     *
     * @return
     */
    abstract fun initLayout(): Int

    /**
     * 设置数据
     */
    abstract fun initData()

    override fun onKeyDown(keyCode: Int, event: KeyEvent): Boolean {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            finish()
            return true
        }
        return super.onKeyDown(keyCode, event)
    }

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
