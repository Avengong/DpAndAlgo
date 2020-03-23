package com.tencent.avengong.designpattern.samples.apistatistics

import java.util.*

interface IMetricsStorage {

    fun storage(requestInfo: RequestInfo)
    fun getRequestInfos(startTimeInMillis: Long, endTimeInMillis: Long): HashMap<String, ArrayList<RequestInfo>>

//    fun getRequestInfo()
}