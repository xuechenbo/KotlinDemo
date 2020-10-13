package com.monebac.com.wkyk.act

import com.monebac.com.R
import com.monebac.com.base.BaseActivity
import com.monebac.com.utils.LogsUtils
import com.monebac.com.utils.getList
import com.monebac.com.wkyk.adapter.LookPlanAdapter
import com.monebac.com.wkyk.model.BindCard
import com.monebac.com.wkyk.model.PlanAllModel
import com.monebac.com.wkyk.netutils.BaseEntity
import com.monebac.com.wkyk.netutils.OkClient
import com.lzy.okgo.model.Response
import com.scwang.smartrefresh.layout.header.ClassicsHeader
import kotlinx.android.synthetic.main.activity_bank_list.*
import kotlinx.android.synthetic.main.layout_title.*
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast

class LookPlanActivity : BaseActivity() {
    lateinit var bindCard: BindCard
    private lateinit var mList: List<PlanAllModel>
    private lateinit var lookPlanAdapter: LookPlanAdapter

    override fun initLayout(): Int = R.layout.activity_look_plan


    override fun initData() {
        bindCard = intent.getSerializableExtra("BindCard_Class") as BindCard
        mList = ArrayList()
        back.setOnClickListener { finish() }
        tv_title.text = "查看计划"
        lookPlanAdapter = LookPlanAdapter(mList)
        recyCler.adapter = lookPlanAdapter
        initListener()
        requestPlanItem()
    }

    val initListener = {
        smarFresh.run {
            refreshHeader = ClassicsHeader(context)
            isEnableLoadmore = false
            setOnRefreshListener {
                requestPlanItem()
            }
        }

        lookPlanAdapter.setOnItemChildClickListener { _, view, position ->
            when (view.id) {
                R.id.btn_detail -> startActivity<PlanDetailActivity>(
                        "planDetail" to mList[position],
                        "bindCard" to bindCard)
            }
        }
    }

    private fun requestPlanItem() {
        showProgress()
        val params = OkClient.getParamsInstance().params
        params.put("3", "190212")
        params.put("42", getMerNo())
        params.put("43", bindCard.BANK_ACCOUNT)
        OkClient.getInstance().post(params, object : OkClient.EntityCallBack<BaseEntity>(context, BaseEntity::class.java) {
            override fun onSuccess(response: Response<BaseEntity>?) {
                hideProgress(smarFresh)
                val result = response?.body() ?: return
                if (result.str39 == "00") {
                    mList = getList(result.str57)
                    LogsUtils.d(mList.toString())
                    lookPlanAdapter.setNewData(mList)

                } else {
                    toast(result.str39)
                }
            }

            override fun onError(response: Response<BaseEntity>?) {
                super.onError(response)
                hideProgress(smarFresh)
            }
        })
    }


}