package com.monebac.com.ktolingbaic.kotlinbasic

import com.monebac.com.R
import com.monebac.com.base.BaseActivity

class MaterialDesignActivity : BaseActivity() {

    override fun initLayout(): Int {
        return R.layout.activity_material_design
    }

    override fun initData() {

        //转androidX 导致了问题 没改
//        app_bar.addOnOffsetChangedListener { appBarLayout, verticalOffset ->
//            //垂直方向偏移量
//            val offset = Math.abs(verticalOffset)
//            //最大偏移距离
//            val scrollRange = appBarLayout.totalScrollRange
//            if (offset <= scrollRange / 2) {//当滑动没超过一半，展开状态下toolbar显示内容，根据收缩位置，改变透明值
//                include_toolbar_open.visibility = View.VISIBLE
//                include_toolbar_close.visibility = View.GONE
//                //根据偏移百分比 计算透明值
//                val scale2 = offset.toFloat() / (scrollRange / 2)
//                val alpha2 = (255 * scale2).toInt()
//                bg_toolbar_open.setBackgroundColor(Color.argb(alpha2, 25, 131, 209))
//            } else {//当滑动超过一半，收缩状态下toolbar显示内容，根据收缩位置，改变透明值
//                include_toolbar_close.visibility = View.VISIBLE
//                include_toolbar_open.visibility = View.GONE
//                val scale3 = (scrollRange - offset).toFloat() / (scrollRange / 2)
//                val alpha3 = (255 * scale3).toInt()
//                bg_toolbar_close.setBackgroundColor(Color.argb(alpha3, 25, 131, 209))
//            }
//            //根据偏移百分比计算扫一扫布局的透明度值
//            val scale = offset.toFloat() / scrollRange
//            val alpha = (255 * scale).toInt()
//            bg_content.setBackgroundColor(Color.argb(alpha, 25, 131, 209))
//        }

    }

}