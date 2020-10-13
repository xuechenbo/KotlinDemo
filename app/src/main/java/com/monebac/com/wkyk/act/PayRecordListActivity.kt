package com.monebac.com.wkyk.act

import com.monebac.com.R
import com.monebac.com.base.BaseActivity
import com.monebac.com.utils.getList
import com.monebac.com.wkyk.adapter.PayRecordAdapter
import com.monebac.com.wkyk.model.PayItemModel
import com.monebac.com.wkyk.netutils.BaseEntity
import com.monebac.com.wkyk.netutils.OkClient
import com.lzy.okgo.model.Response
import com.scwang.smartrefresh.layout.footer.ClassicsFooter
import com.scwang.smartrefresh.layout.header.ClassicsHeader
import kotlinx.android.synthetic.main.activity_bank_list.*
import kotlinx.android.synthetic.main.layout_title.*
import org.jetbrains.anko.toast

class PayRecordListActivity : BaseActivity() {
    private lateinit var mList: MutableList<PayItemModel>
    private lateinit var payRecordAdapter: PayRecordAdapter
    var page: Int = 1

    override fun initLayout(): Int = R.layout.activity_pay_record_list

    override fun initData() {
        mList = ArrayList()
        back.setOnClickListener { finish() }
        tv_title.text = "交易提醒"

        smarFresh.run {
            refreshHeader = ClassicsHeader(context)
            refreshFooter = ClassicsFooter(context)
            setOnRefreshListener {
                mList.clear()
                requestData()
            }
            setOnLoadmoreListener {
                page++
                requestData(page)
            }
        }
        payRecordAdapter = PayRecordAdapter(mList)
        recyCler.adapter = payRecordAdapter

        requestData()

    }


    private fun requestData(page: Int = 1) {
        showProgress()
        val params = OkClient.getParamsInstance().params
        params.put("3", "081424")
        params.put("32", "$page")
        params.put("33", "10")
        params.put("42", getMerNo())
        OkClient.getInstance().post(params, object : OkClient.EntityCallBack<BaseEntity>(context, BaseEntity::class.java) {
            override fun onSuccess(response: Response<BaseEntity>?) {
                hideProgress(smarFresh)
                val result = response?.body() ?: return
                if (result.str39 == "00") {
                    val list = getList<List<PayItemModel>>(result.str32)
                    mList.addAll(list)
                    payRecordAdapter.setNewData(mList)
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