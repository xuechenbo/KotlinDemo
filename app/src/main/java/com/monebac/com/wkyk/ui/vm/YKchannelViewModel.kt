package com.monebac.com.wkyk.ui.vm

import androidx.lifecycle.MutableLiveData
import com.google.gson.Gson
import com.monebac.com.jetpack.jetbase.BaseViewModel
import com.monebac.com.wkyk.model.AcqData
import com.monebac.com.wkyk.model.ChannelModel

class YKchannelViewModel : BaseViewModel() {

    val ykchan = MutableLiveData<List<AcqData>>()
    val ykToast = MutableLiveData<String>()
    val ykUrl = MutableLiveData<String>()

    fun getYkChannel(map: MutableMap<String, String>) {
        launch(
                block = {
                    val result = resultRepository.getResult(map)
                    if (result.str39 == "00") {
                        val channelModel = Gson().fromJson(result.str57, ChannelModel::class.java)
                        ykchan.value = channelModel.acqData
                    }
                }
        )
    }

    fun getChannelStatus(map: MutableMap<String, String>) {
        launch(
                block = {
                    val result = resultRepository.getResult(map)
                    if (result.str39 == "00") {
                        if (result.str38 == "00")
                            ykToast.value = "绑定成功"
                        else
                            ykUrl.value = result.str38
                    } else {
                        ykToast.value = result.str39
                    }
                }
        )
    }
}