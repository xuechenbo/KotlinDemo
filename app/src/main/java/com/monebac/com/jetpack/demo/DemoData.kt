package com.monebac.com.jetpack.demo

import androidx.lifecycle.LiveData

class DemoData : LiveData<DemoData?>() {
    //TODO 更新单个属性

    var tag1 = 0
        set(tag1) {
            field = tag1
            postValue(this)
        }

    var tag2 = 0
        set(tag2) {
            field = tag2
            postValue(this)
        }

}