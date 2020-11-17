package com.monebac.com.wkyk.ui

import android.content.Intent
import com.monebac.com.R
import com.monebac.com.base.BaseActivity
import com.monebac.com.utils.LogsUtils
import com.monebac.com.wkyk.dialogfrg.CalendarDialogFrg
import com.monebac.com.wkyk.dialogfrg.ChangeTypeDialog
import com.monebac.com.wkyk.dialogfrg.ShowPlanMsgDialog
import com.monebac.com.wkyk.event.ClosePlanEvent
import com.monebac.com.wkyk.model.AreaModel
import com.monebac.com.wkyk.model.BindCard
import com.monebac.com.wkyk.model.PreviewPlanModel
import com.monebac.com.wkyk.netutils.BaseEntity
import com.monebac.com.wkyk.netutils.OkClient
import com.lzy.okgo.model.Response
import kotlinx.android.synthetic.main.act_change_area.*
import kotlinx.android.synthetic.main.activity_make_design.*
import kotlinx.android.synthetic.main.layout_plan_header.*
import kotlinx.android.synthetic.main.layout_title.*
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.startActivityForResult
import org.jetbrains.anko.toast
import java.math.BigDecimal
import com.monebac.com.wkyk.ui.PreviewDetailActivity as PreviewDetailActivity1

class MakePlanDesActivity : BaseActivity() {
    lateinit var bindCard: BindCard
    private var acqCode: String? = null
    var dailyNum: String = "2"
    private lateinit var Industry: String
    lateinit var selectAreaMap: MutableMap<String, AreaModel>

    override fun initLayout(): Int = R.layout.activity_make_design

    override fun initData() {
        EventBus.getDefault().register(this)
        bindCard = intent.getSerializableExtra("BindCard_Class") as BindCard
        acqCode = intent.getStringExtra("acqCode")

        tv_title.text = "制定计划"
        tv_bank_name.text = bindCard.BANK_NAME
        tv_bank_account.text = "尾号:${bindCard.BANK_ACCOUNT.run {
            substring(length - 4, length)
        }}"
        tv_billDay.text = bindCard.BILL_DAY
        tv_payDay.text = bindCard.REPAYMENT_DAY
        tv_limit.text = bindCard.LIMIT_MONEY

        initListener()
    }

    private fun initListener() {
        back.setOnClickListener { finish() }
        tv_totalTime.setOnClickListener {
            val calendarDialogFrg = CalendarDialogFrg()
            //TODO 方法1 java仿
//            calendarDialogFrg.setSuccessCallback(object : CalendarDialogFrg.SuccessCallback {
//                override fun success(value: MutableList<Calendar>) {
//                    LogsUtils.e("abc$value")
//                }
//            })
            //TODO 方法二
            calendarDialogFrg.setSuccess {
                LogsUtils.e(it.toString())
                tv_totalTime.text = it.toString()
            }
            calendarDialogFrg.show(supportFragmentManager, "")
        }

        //每日几笔
        tv_hkbs.setOnClickListener {
            val listOf = listOf("每天2笔", "每天3笔")
            var changeDialogFrg = ChangeTypeDialog().getInstance(listOf, "")

            changeDialogFrg.setSuccess {
                dailyNum = it
                tv_hkbs.text = "每日${it}笔"
            }
            changeDialogFrg.show(supportFragmentManager, "")

        }

        //地区
        tv_choiceArea.setOnClickListener {
            startActivityForResult<ChangeAreaActivity>(0,
                    "bindId" to bindCard.ID,
                    "acqCode" to acqCode)
        }

        bt_calculate.setOnClickListener {
            when {
                et_inputPayAmount.text.isNullOrEmpty() -> toast("还款金额为空")
                et_principalMoney.text.isNullOrEmpty() -> toast("可用额度为空")
                tv_choiceArea.text.isNullOrEmpty() -> toast("请选择地区")
                else -> requestData()
            }
        }
    }


    private fun requestData() {
        showProgress()
        val params = OkClient.getParamsInstance().params
        params.put("3", "193000")
        params.put("7", dailyNum)//每日几笔
        params.put("8", et_inputPayAmount.text.toString())//还款金额
        params.put("9", "1")
        params.put("10", "0")
        params.put("11", bindCard.BANK_ACCOUNT)   //卡号
        params.put("12", bindCard.ID)       //卡id
        params.put("16", "0")
        params.put("35", selectAreaMap["city"]!!.id)
        params.put("36", selectAreaMap["pri"]!!.id)
        params.put("42", getMerNo())
        params.put("43", acqCode)
        params.put("44", et_principalMoney.text.toString())//预留额度
        OkClient.getInstance().post(params, object : OkClient.EntityCallBack<BaseEntity>(context, BaseEntity::class.java) {
            override fun onSuccess(response: Response<BaseEntity>?) {
                hideProgress(smarFresh)
                val result = response?.body() ?: return
                if (result.str39 == "00") {
                    var previewPlanModel = PreviewPlanModel()
                    previewPlanModel.run {
                        HkMoney = result.str8
                        val split = result.str10.split(",")
                        startTime = split[0]
                        endTime = split[split.size - 1]
                        ZzjMoney = result.str40
                        fee = result.str9
                        timeFee = result.str7
                        totoNum = result.str20
                        totalFee = BigDecimal(result.str40).add(BigDecimal(result.str9)).add(BigDecimal(result.str7)).toString()
                        address = selectAreaMap
                        industryStr = Industry
                        AcqCode = acqCode
                        acqName = intent.getStringExtra("acqName")
                        rNum = dailyNum
                        planDetail = result.str57
                    }
                    val instance = ShowPlanMsgDialog().getInstance(previewPlanModel)
                    instance.success {
                        startActivity<PreviewDetailActivity1>(
                                "previewPlanModel" to previewPlanModel,
                                "bindCard" to bindCard)
                    }
                    instance.show(supportFragmentManager, "")
                } else {
                    toast(result.str39)
                }
            }

            override fun onError(response: Response<BaseEntity>?) {
                hideProgress()
            }
        })
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 0 && resultCode == 1009) {
            data?.run {
                selectAreaMap = data!!.extras.getSerializable("selectAreaMap") as MutableMap<String, AreaModel>
                Industry = extras.getString("Industry")
                tv_choiceArea.text = "${selectAreaMap["pri"]!!.divisionName}-${selectAreaMap["city"]!!.divisionName}"
            }
        }
    }


    @Subscribe
    fun onEvent(closePlanEvent: ClosePlanEvent) {
        finish()
    }

    override fun onDestroy() {
        super.onDestroy()
        EventBus.getDefault().unregister(this)
    }

}