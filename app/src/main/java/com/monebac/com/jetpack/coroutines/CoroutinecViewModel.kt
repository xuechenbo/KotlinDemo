package com.monebac.com.jetpack.coroutines

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.monebac.com.utils.getMap
import com.monebac.com.wkyk.Constant
import kotlinx.coroutines.launch

class CoroutinecViewModel : ViewModel() {

    val imageData: MutableLiveData<String> = MutableLiveData()

    val loadState = MutableLiveData<LoadState>()

    override fun onCleared() {
        super.onCleared()
    }

    fun getData() {
        viewModelScope.launch {
            val image = NetworkService.apiService.getImage(getMap(mutableMapOf(
                    "1" to "13858069994",
                    "3" to "190928",
                    "8" to Constant.Md5("123456"))))
            imageData.postValue(image.str39)
        }
    }
}
