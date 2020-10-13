package com.monebac.com.wkyk.act

import com.google.gson.Gson
import com.monebac.com.R
import com.monebac.com.base.BaseActivity
import com.monebac.com.wkyk.adapter.YkChannelAdapter
import com.monebac.com.wkyk.model.AcqData
import com.monebac.com.wkyk.model.BindCard
import com.monebac.com.wkyk.model.ChannelModel
import com.monebac.com.wkyk.event.ClosePlanEvent
import com.monebac.com.wkyk.netutils.BaseEntity
import com.monebac.com.wkyk.netutils.OkClient
import com.monebac.com.wkyk.web.AgentWebActivity
import com.lzy.okgo.model.Response
import com.scwang.smartrefresh.layout.header.ClassicsHeader
import kotlinx.android.synthetic.main.activity_bank_list.*
import kotlinx.android.synthetic.main.layout_title.*
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast

class YKchannelActivity : BaseActivity() {

    lateinit var ykChannelAdapter: YkChannelAdapter
    private lateinit var mList: List<AcqData>
    private var bindCard: BindCard? = null

    override fun initLayout(): Int {
        return R.layout.act_ykchannel
    }

    override fun initData() {
        EventBus.getDefault().register(this)
        mList = ArrayList()
        bindCard = intent.getSerializableExtra("BindCard_Class") as BindCard
        tv_title.text = "通道列表"
        back.setOnClickListener { finish() }
        ykChannelAdapter = YkChannelAdapter(R.layout.item_yk_channel, mList)
        recyCler.adapter = ykChannelAdapter
        initListener()
        requestData()
    }

    private fun initListener() {
        smarFresh.run {
            refreshHeader = ClassicsHeader(context)
            isEnableLoadmore = false
            setOnRefreshListener {
                requestData()
            }
        }

        ykChannelAdapter.run {
            setOnItemChildClickListener { _, view, position ->
                when (view.id) {
                    R.id.bt_chose ->
                        if (mList[position].status == "未开通")
                            initCard(mList[position].acqcode)
                        else
                            startActivity<MakePlanDesActivity>("BindCard_Class" to bindCard,
                                    "acqCode" to mList[position].acqcode,
                                    "acqName" to mList[position].channelName)

                    R.id.tv_remark -> startActivity<ShowImageActivity>("title" to "限额说明", "url" to mList[position].acqcode)
                }
            }
        }
    }

    private fun initCard(acqcode: String) {
        showProgress()
        val params = OkClient.getParamsInstance().params
        params.put("3", "490008")
        params.put("5", bindCard!!.LIMIT_MONEY)
        params.put("6", bindCard!!.BILL_DAY)
        params.put("7", bindCard!!.REPAYMENT_DAY)
        params.put("37", bindCard!!.ID)
        params.put("42", bindCard!!.ID_CARD_NUMBER)
        params.put("43", getMerId())
        params.put("45", bindCard!!.BANK_ACCOUNT)
        params.put("46", bindCard!!.BANK_PHONE)
        params.put("47", bindCard!!.EXPDATE)
        params.put("48", bindCard!!.CVN)
        params.put("49", acqcode)
        params.put("31", "")
        params.put("41", "")
        OkClient.getInstance().post(params, object : OkClient.EntityCallBack<BaseEntity>(context, BaseEntity::class.java) {
            override fun onSuccess(response: Response<BaseEntity>?) {
                hideProgress()

                val result = response?.body() ?: return
                if (result.str39 == "00") {
                    if (result.str38 == "00")
                        toast("绑定成功")
                    else
                        startActivity<AgentWebActivity>(
                                "title" to "通道绑卡",
                                "url" to result.str38)
                } else {
                    toast(result.str39)
                }
            }
        })
    }


    private fun requestData() {
        showProgress()
        val params = OkClient.getParamsInstance().params
        params.put("3", "390013")
        params.put("42", getMerNo())
        params.put("43", "YK")
        params.put("44", bindCard!!.ID)
        OkClient.getInstance().post(params, object : OkClient.EntityCallBack<BaseEntity>(context, BaseEntity::class.java) {
            override fun onSuccess(response: Response<BaseEntity>?) {
                hideProgress(smarFresh)
                val result = response?.body() ?: return
                if (result.str39 == "00") {
                    val channelModel = Gson().fromJson(result.str57, ChannelModel::class.java)
                    mList = channelModel.acqData
                    ykChannelAdapter.setNewData(mList)
                } else {
                    toast(result.str39)
                }
            }

            override fun onError(response: Response<BaseEntity>?) {

            }
        })
    }

    @Subscribe
    fun onEvent(event: ClosePlanEvent) {
        finish()
    }

    override fun onDestroy() {
        super.onDestroy()
        EventBus.getDefault().unregister(this)
    }

}