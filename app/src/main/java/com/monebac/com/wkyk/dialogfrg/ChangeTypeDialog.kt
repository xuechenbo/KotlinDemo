package com.monebac.com.wkyk.dialogfrg

import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.util.DisplayMetrics
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.monebac.com.R
import com.monebac.com.wkyk.adapter.ChangeTypeAdapter
import kotlinx.android.synthetic.main.changetype_dialogfragment.view.*
import java.io.Serializable

class ChangeTypeDialog : DialogFragment() {

    var mList: List<String>? = null
    var type = ""
    var selectItem: String = ""
    lateinit var myAdapter: ChangeTypeAdapter

    fun getInstance(mlist: List<String>, type: String): ChangeTypeDialog {
        val changeTypeDialog = ChangeTypeDialog()
        val bundle = Bundle()
        bundle.putSerializable("list", mlist as Serializable)
        bundle.putString("type", type)
        changeTypeDialog.arguments = bundle
        return changeTypeDialog
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(STYLE_NO_FRAME, R.style.MyDialog)

        //TODO as~~~~~~~~~~~~~~~~~~~~转换
        mList = arguments!!.getSerializable("list") as List<String>
        type = arguments!!.getString("type")


    }

    override fun onStart() {
        super.onStart()
        val dialog = dialog
        dialog!!.setCancelable(false)
        dialog.setCanceledOnTouchOutside(false)
        if (dialog != null) {
            val dm = DisplayMetrics()
            activity!!.windowManager.defaultDisplay.getMetrics(dm)
            dialog.window!!.setLayout(dm.widthPixels * 1, ViewGroup.LayoutParams.WRAP_CONTENT)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.changetype_dialogfragment, container)
        initData(view)
        return view
    }

    private fun initData(view: View) {
        dialog.setCanceledOnTouchOutside(false)
        val window = dialog.window
        window!!.setGravity(Gravity.BOTTOM)
        window.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)

        view.run {
            iv_close.setOnClickListener {
                //myListener?.invoke(selectItem)
                dialog.dismiss()
            }
            tv_success.setOnClickListener {
                mListener?.invoke(selectItem)
                dialog.dismiss()
            }
            myAdapter = ChangeTypeAdapter(R.layout.item_change_dialog_fragment, mList)
            recyclerView.adapter = myAdapter
        }

        myAdapter.setOnItemClickListener { adapter, view, position ->
            selectItem = "${position + 2}"
            mListener?.invoke(selectItem)
            dialog.dismiss()
        }

    }


    //TODO lambda表达式
    lateinit var mListener: (String) -> Unit

    //TODO 高阶函数，函数当参数
    fun setSuccess(mlistener: (String) -> Unit) {
        this.mListener = mlistener
    }

    //TODO 一样

    private var myListener: (String) -> Unit = {} //无参无返回值

    fun getMsg(mMsg: (String) -> Unit) {
        this.myListener = mMsg
    }

}
