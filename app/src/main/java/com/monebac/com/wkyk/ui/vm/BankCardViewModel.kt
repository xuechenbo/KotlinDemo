package com.monebac.com.wkyk.ui.vm

import androidx.lifecycle.MutableLiveData
import com.monebac.com.jetpack.jetbase.BaseViewModel
import com.monebac.com.utils.getList
import com.monebac.com.wkyk.model.BindCard
import com.monebac.com.wkyk.ui.repository.ResultRepository

class BankCardViewModel : BaseViewModel() {
    override fun onCleared() {
        super.onCleared()
    }

    private val resultRepository by lazy {
        ResultRepository()
    }

    val bankList = MutableLiveData<List<BindCard>>()


    fun getBankList(map: MutableMap<String, String>) {
        launch(
                block = {
                    loading.value = true
                    val result = resultRepository.getResult(map)
                    if (result.str39 == "00") {
                        bankList.value = getList(result.str57)
                    }
                    loading.value = false
                },
                error = {

                }
        )
    }

}