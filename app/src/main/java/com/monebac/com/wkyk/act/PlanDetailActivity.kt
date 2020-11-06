package com.monebac.com.wkyk.act

import androidx.recyclerview.widget.LinearLayoutManager
import com.monebac.com.R
import com.monebac.com.base.BaseActivity
import com.monebac.com.utils.getList
import com.monebac.com.wkyk.adapter.PlanDetailAdapter
import com.monebac.com.wkyk.model.BindCard
import com.monebac.com.wkyk.model.PlanAllModel
import com.monebac.com.wkyk.model.PlanItemDetailModel
import com.monebac.com.wkyk.netutils.BaseEntity
import com.monebac.com.wkyk.netutils.OkClient
import com.lzy.okgo.model.Response
import kotlinx.android.synthetic.main.activity_plan_detail.*
import kotlinx.android.synthetic.main.layout_title.*
import org.jetbrains.anko.toast

class PlanDetailActivity : BaseActivity() {
    private lateinit var planAllModel: PlanAllModel
    private lateinit var bindCard: BindCard
    private lateinit var planDetailAdapter: PlanDetailAdapter
    private var mList: List<PlanItemDetailModel> = ArrayList()

    override fun initLayout(): Int = R.layout.activity_plan_detail

    override fun initData() {
        back.setOnClickListener { finish() }
        tv_title.text = "计划详情"
        planAllModel = intent.getSerializableExtra("planDetail") as PlanAllModel
        bindCard = intent.getSerializableExtra("bindCard") as BindCard

        planDetailAdapter = PlanDetailAdapter(mList)
        recyCler.run {
            //TODO NestedScrollView 嵌套recyclerView 滑动卡顿问题  对于老版本手机(api21-)，处理方式如下
            layoutManager = LinearLayoutManager(context).apply {
                //isSmoothScrollbarEnabled = true
                //isAutoMeasureEnabled = true
            }
            //TODO api21+  以上的不卡顿
            isNestedScrollingEnabled = false
            //setHasFixedSize(true)
        }

        tv_status.setOnClickListener {
            when {
                tv_status.text == "查看更多" -> {
                    tv_status.text = "收起更多"
                    planDetailAdapter.setShowOnlyItem(false)
                }
                else -> {
                    tv_status.text = "查看更多"
                    planDetailAdapter.setShowOnlyItem(true)
                }
            }
        }

        bt_stopPlan.setOnClickListener {
            toast("没写别点了")
        }
        planDetailAdapter.bindToRecyclerView(recyCler)
        requestPlanDetail()
    }


    private fun requestPlanDetail() {
        showProgress()
        val params = OkClient.getParamsInstance().params
        params.put("3", "190213")
        params.put("8", planAllModel.ID)
        OkClient.getInstance().post(params, object : OkClient.EntityCallBack<BaseEntity>(context, BaseEntity::class.java) {
            override fun onSuccess(response: Response<BaseEntity>?) {
                hideProgress()
                val result = response?.body() ?: return
                if (result.str39 == "00") {
                    mList = getList(result.str57)
                    planDetailAdapter.setNewData(mList)
                    planDetailAdapter.setShowOnlyItem(true)
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