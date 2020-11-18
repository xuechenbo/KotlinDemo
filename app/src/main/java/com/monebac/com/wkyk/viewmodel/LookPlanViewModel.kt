package com.monebac.com.wkyk.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.monebac.com.utils.getList
import com.monebac.com.wkyk.model.PlanAllModel
import com.monebac.com.wkyk.netutils.RetrofitNet
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.launch

class LookPlanViewModel : ViewModel() {
    private lateinit var mList: List<PlanAllModel>
    var lookup: MutableLiveData<List<PlanAllModel>> = MutableLiveData()
    var fail: MutableLiveData<String> = MutableLiveData()


    override fun onCleared() {
        super.onCleared()
    }

    fun getListData(map: Map<String, String>) {

        RetrofitNet().api.getRequeData(map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe {
                    if (it.str39 == "00") {
                        mList = getList(it.str57)
                        lookup.postValue(mList)
                    } else {
                        fail.postValue(it.str39)
                    }
                }
    }

}