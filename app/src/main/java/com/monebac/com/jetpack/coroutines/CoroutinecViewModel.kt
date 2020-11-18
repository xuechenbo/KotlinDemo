package com.monebac.com.jetpack.coroutines

import androidx.lifecycle.MutableLiveData
import com.monebac.com.jetpack.jetbase.BaseViewModel

class CoroutinecViewModel : BaseViewModel() {

    /*
    //协程是否存活
    job.isActive
    //是否取消
    job.isCancelled
    //是否完成
    job.isCompleted
    //取消协程
    job.cancel()
    //（非懒启动模式的）启动协程
    job.start()
    * */

    //TODO 这个是干啥的
    private val loginRepository by lazy { LoginRepository() }
    val loginMsg: MutableLiveData<String> = MutableLiveData()

    fun getData(map: MutableMap<String, String>) {
        val launch = launch(
                block = {
                    val login = loginRepository.login(map)
                    loginMsg.postValue(login.str39)
                },
                error = {

                },
                cancel = {

                },
                showErrorToast = false
        )

        /*viewModelScope是一个绑定到当前viewModel的作用域
        当ViewModel被清除时会自动取消该作用域，所以不用担心内存泄漏为问题*/
//        viewModelScope.launch {
//            /*withContext表示挂起块  配合Retrofit声明的suspend函数执行 该块会挂起直到里面的网络请求完成 最一行就是返回值*/
//            withContext(Dispatchers.IO) {
//                val image = NetworkService.apiService.getImage(map)
//                loginMsg.postValue(image.str39)
//            }
//        }
    }

    override fun onCleared() {
        super.onCleared()
    }
}
