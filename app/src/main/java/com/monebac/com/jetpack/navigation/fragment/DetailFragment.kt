package com.monebac.com.jetpack.navigation.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.monebac.com.R
import kotlinx.android.synthetic.main.frag_detail.view.*
import kotlinx.android.synthetic.main.layout_title.view.*

class DetailFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val inflate = inflater.inflate(R.layout.frag_detail, container, false)
        initData(inflate)
        return inflate

    }

    fun initData(inflate: View) {
        inflate.run {
            back.setOnClickListener {
                findNavController().popBackStack()
//                Navigation.findNavController(requireView()).popBackStack()
            }
            bt_login.setOnClickListener {
                findNavController().navigate(R.id.action_detailFragment_to_detail1Fragment)
            }
        }
    }

}