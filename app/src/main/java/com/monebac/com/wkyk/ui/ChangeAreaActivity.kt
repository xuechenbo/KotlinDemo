package com.monebac.com.wkyk.ui

import android.content.Intent
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.OrientationHelper
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.lzy.okgo.model.Response
import com.monebac.com.R
import com.monebac.com.base.BaseActivity
import com.monebac.com.wkyk.adapter.ChangeAreaAdapter
import com.monebac.com.wkyk.model.AreaModel
import com.monebac.com.wkyk.netutils.BaseEntity
import com.monebac.com.wkyk.netutils.OkClient
import com.scwang.smartrefresh.layout.header.ClassicsHeader
import kotlinx.android.synthetic.main.act_change_area.*
import kotlinx.android.synthetic.main.dialog_calendar.tv_title
import kotlinx.android.synthetic.main.layout_title.*
import org.jetbrains.anko.toast
import java.io.Serializable

//TODO 看下git修改的日志2
class ChangeAreaActivity : BaseActivity() {
    lateinit var mChangeAreaAdapter: ChangeAreaAdapter
    lateinit var selectAreaMap: MutableMap<String, AreaModel>
    private lateinit var mList: List<AreaModel>
    var seleType = ""

    override fun initLayout(): Int {
        return R.layout.act_change_area
    }

    override fun initData() {
        tv_title.text = "选择商户"
        mList = ArrayList()
        selectAreaMap = HashMap()
        initListener()
        getArea()
    }

    private fun initListener() {
        back.setOnClickListener { finish() }
        smarFresh.run {
            isEnableLoadmore = false
            isEnableRefresh = false
            refreshHeader = ClassicsHeader(context)
            setOnRefreshListener {

            }
        }
        recyCler.addItemDecoration(DividerItemDecoration(context, OrientationHelper.VERTICAL))
        mChangeAreaAdapter = ChangeAreaAdapter(R.layout.item_change_area, mList)
        recyCler.adapter = mChangeAreaAdapter


        mChangeAreaAdapter.setOnItemClickListener { adapter, view, position ->
            if (seleType == "0") {
                selectAreaMap["pri"] = mList[position]
                getArea(mList[position].id)
            } else {
                selectAreaMap["city"] = mList[position]
                getIndustry(selectAreaMap["pri"]!!.id, selectAreaMap["city"]!!.id)
            }
        }
    }

    //TODO 商户
    private fun getIndustry(priId: String, cityId: String) {
        showProgress()
        val params = OkClient.getParamsInstance().params
        params.put("3", "490006")
        params.put("30", intent.getStringExtra("bindId"))
        params.put("31", priId)
        params.put("32", cityId)
        params.put("33", intent.getStringExtra("acqCode"))
        OkClient.getInstance().post(params, object : OkClient.EntityCallBack<BaseEntity>(context, BaseEntity::class.java) {
            override fun onSuccess(response: Response<BaseEntity>?) {
                hideProgress(smarFresh)
                val result = response?.body() ?: return
                if (result.str39 == "00") {
                    val intent = Intent()
                    intent.putExtra("selectAreaMap", selectAreaMap as Serializable)
                    intent.putExtra("Industry", result.str38)
                    setResult(1009, intent)
                    finish()
                } else {
                    toast(result.str39)
                }
            }

            override fun onError(response: Response<BaseEntity>?) {
                hideProgress()
            }
        })
    }

    //TODO 省 市
    private fun getArea(id: String = "0") {
        showProgress()
        val params = OkClient.getParamsInstance().params
        params.put("3", "490004")
        params.put("41", id)
        OkClient.getInstance().post(params, object : OkClient.EntityCallBack<BaseEntity>(context, BaseEntity::class.java) {
            override fun onSuccess(response: Response<BaseEntity>?) {
                hideProgress(smarFresh)
                val result = response?.body() ?: return
                if (result.str39 == "00") {
                    seleType = id
                    mList = Gson().fromJson<List<AreaModel>>(result.str38, object : TypeToken<List<AreaModel>>() {}.type)
                    mChangeAreaAdapter.setNewData(mList)
                } else {
                    toast(result.str39)
                }
            }

            override fun onError(response: Response<BaseEntity>?) {
                hideProgress()
            }
        })
    }

}