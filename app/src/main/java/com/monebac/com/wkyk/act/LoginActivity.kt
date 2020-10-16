package com.monebac.com.wkyk.act

import com.monebac.com.R
import com.monebac.com.base.BaseMvpActivity
import com.monebac.com.jetpack.MainJetpackActivity
import com.monebac.com.ktolingbaic.kotlinbasic.KotlinActivity
import com.monebac.com.utils.LogsUtils
import com.monebac.com.utils.PreferencesUtil
import com.monebac.com.utils.fromJson
import com.monebac.com.utils.getMap
import com.monebac.com.wkyk.Constant
import com.monebac.com.wkyk.contract.LoginContract
import com.monebac.com.wkyk.model.UserInfoModel
import com.monebac.com.wkyk.netutils.BaseResp
import com.monebac.com.wkyk.presenter.LoginPresenter
import com.monebac.com.wkyk.web.AgentWebActivity
import kotlinx.android.synthetic.main.activity_new_login.*
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast

class LoginActivity : BaseMvpActivity<LoginContract.View, LoginPresenter>(), LoginContract.View {

    override fun getLayoutResId(): Int = R.layout.activity_new_login


    override fun createPresenter(): LoginPresenter {
        return LoginPresenter()
    }

    override fun initView() {
        val phone = PreferencesUtil.getString("phone")
        val pwd = PreferencesUtil.getString("pwd")
        if (phone.isNotEmpty() || pwd.isNotEmpty()) {
            et_phone.setText(phone)
            et_pass.setText(pwd)
        }
    }

    override fun onLoginSuccess(result: BaseResp) {
        LogsUtils.d(result.toString())
        val fromJson = fromJson<UserInfoModel>(result.str42)
        val userInfoModelItem = fromJson?.get(0)
        LogsUtils.e(userInfoModelItem!!.merchantNo)
        PreferencesUtil.saveValue("merNo", userInfoModelItem.merchantNo)
        PreferencesUtil.saveValue("merId", userInfoModelItem.id)
        PreferencesUtil.saveValue("phone", et_phone.text.toString())
        PreferencesUtil.saveValue("pwd", et_pass.text.toString())
        startActivity<BankCardListActivity>()
        finish()
    }

    override fun onLoginFail(str: String) {
        toast(str)
    }

    override fun initData() {
        super.initData()
        btn_login.setOnClickListener {
            when {
                et_phone.text.isNullOrEmpty() -> toast("手机号为空")
                et_pass.text.isNullOrEmpty() -> toast("密码为空")
                else -> presenter.LoginSub(
                        getMap(mutableMapOf(
                                "1" to et_phone.text.toString(),
                                "3" to "190928",
                                "8" to Constant.Md5(et_pass.text.toString()))))
            }
        }

        tv_start.setOnClickListener {
            startActivity<KotlinActivity>()
        }

        tv_forget_pass.setOnClickListener {
            startActivity<AgentWebActivity>("title" to "忘记密码", "url" to "https://www.zhihu.com")
        }

        tv_jetpack.setOnClickListener {
            startActivity<MainJetpackActivity>()
            finish()
        }
    }


    override fun showLoading() {
        showProgress()
    }

    override fun dismissLoading() {
        hideProgress()
    }

}