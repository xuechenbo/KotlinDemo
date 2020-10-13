package com.monebac.com.wkyk.model

import java.io.Serializable

class PreviewPlanModel : Serializable {
    var HkMoney: String? = null
    var totoNum: String? = null
    var startTime: String? = null
    var endTime: String? = null
    var ZzjMoney: String? = null
    var fee: String? = null
    var timeFee: String? = null
    var totalFee: String? = null
    var address: MutableMap<String, AreaModel>? = null
    var industryStr: String? = null
    var AcqCode: String? = null
    var acqName: String? = null
    var rNum: String? = null
    var planDetail: String? = null
}
