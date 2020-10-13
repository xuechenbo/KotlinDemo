package com.monebac.com.ktolingbaic.kotlinbasic

import com.monebac.com.ktolingbaic.kotlinbasic.model.CalloutInterface

//TODO 接口代理
class TheAgent(call: CalloutInterface) : CalloutInterface by call