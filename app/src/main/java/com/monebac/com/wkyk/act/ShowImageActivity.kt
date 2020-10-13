package com.monebac.com.wkyk.act

import com.monebac.com.R
import com.monebac.com.base.BaseActivity
import com.monebac.com.utils.LoadBigImage
import kotlinx.android.synthetic.main.activity_show_image.*
import kotlinx.android.synthetic.main.layout_title.*

class ShowImageActivity : BaseActivity() {
    override fun initLayout(): Int {
        return R.layout.activity_show_image
    }

    override fun initData() {
        val url = intent.getStringExtra("url")
        val title = intent.getStringExtra("title")
        tv_title.text = title
        back.setOnClickListener { finish() }
        LoadBigImage(context, url, iv)
    }
}