package com.monebac.com.wkyk.ui

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.monebac.com.R
import com.monebac.com.base.BaseActivity
import com.monebac.com.utils.LogsUtils
import com.monebac.com.utils.getTimeCurrYMD
import com.monebac.com.wkyk.adapter.PreviewDetailAdapter
import com.monebac.com.wkyk.model.BindCard
import com.monebac.com.wkyk.model.PlanItemModel
import com.monebac.com.wkyk.model.PreviewPlanModel
import com.monebac.com.wkyk.event.ClosePlanEvent
import com.monebac.com.wkyk.netutils.BaseEntity
import com.monebac.com.wkyk.netutils.OkClient
import com.lzy.okgo.model.Response
import kotlinx.android.synthetic.main.act_preview_detail.*
import kotlinx.android.synthetic.main.layout_title.*
import org.greenrobot.eventbus.EventBus
import org.jetbrains.anko.toast

class PreviewDetailActivity : BaseActivity() {
    lateinit var previewPlanModel: PreviewPlanModel
    lateinit var bindCard: BindCard
    lateinit var previewDetailAdapter: PreviewDetailAdapter
    lateinit var mList: List<PlanItemModel>

    override fun initLayout(): Int = R.layout.act_preview_detail

    override fun initData() {
        mList = ArrayList()
        previewPlanModel = intent.getSerializableExtra("previewPlanModel") as PreviewPlanModel
        bindCard = intent.getSerializableExtra("bindCard") as BindCard

        back.setOnClickListener { finish() }
        tv_title.text = "提交计划"
        acqChName.text = previewPlanModel.acqName
        tv_preRepayAmount.text = previewPlanModel.HkMoney
        tv_total_service_price.text = previewPlanModel.totalFee
        tv_RNum.text = "${previewPlanModel.rNum}笔"

        val planItemList = Gson().fromJson<List<PlanItemModel>>(previewPlanModel.planDetail, object : TypeToken<List<PlanItemModel>>() {}.type)
        //商户
        //val industryStr = previewPlanModel.industryStr
        val address = previewPlanModel.address

        mList = planItemList.map {
            it.customizecity = "${address?.run {
                "${get("pri")!!.divisionName}-${get("city")!!.divisionName}"
            }}"
            it
        }
        LogsUtils.d(mList.toString())
        previewDetailAdapter = PreviewDetailAdapter(R.layout.item_preview_detail_plan, mList)
        recyCler.adapter = previewDetailAdapter

        bt_submit_plan.setOnClickListener {
            submitPlan()
        }
    }


    private fun submitPlan() {
        showProgress()
        val params = OkClient.getParamsInstance().params
        params.put("3", "190210")
        params.put("5", "1")
        params.put("8", previewPlanModel.HkMoney)
        params.put("9", previewPlanModel.ZzjMoney)
        params.put("10", previewPlanModel.startTime!!.getTimeCurrYMD())
        params.put("11", previewPlanModel.endTime!!.getTimeCurrYMD())
        params.put("12", previewPlanModel.fee)
        params.put("13", previewPlanModel.timeFee)
        params.put("14", previewPlanModel.rNum)
        params.put("42", getMerNo())
        params.put("43", previewPlanModel.AcqCode)
        params.put("57", mList.toString())
        OkClient.getInstance().post(params, object : OkClient.EntityCallBack<BaseEntity>(context, BaseEntity::class.java) {
            override fun onSuccess(response: Response<BaseEntity>?) {
                hideProgress()
                val result = response?.body() ?: return
                if (result.str39 == "00") {
                    toast("提交成功")
                    EventBus.getDefault().post(ClosePlanEvent())
                    finish()
                } else {
                    toast(result.str39)
                }
            }

            override fun onError(response: Response<BaseEntity>?) {
                super.onError(response)
                hideProgress()
            }
        })
    }

}