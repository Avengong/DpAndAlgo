package com.tencent.avengong.designpattern.samples.apistatistics

import java.util.*

class MemoryMetricStorage : IMetricsStorage {
    override fun getRequestInfos(
        startTimeInMillis: Long,
        endTimeInMillis: Long
    ): HashMap<String, ArrayList<RequestInfo>> {

        return mResponseTimesMap
    }

    val mResponseTimesMap = hashMapOf<String, ArrayList<RequestInfo>>()

    override fun storage(requestInfo: RequestInfo) {

        val arrayList = mResponseTimesMap[requestInfo.api]
        if (arrayList == null) {
            mResponseTimesMap[requestInfo.api] = arrayListOf()
        }
        mResponseTimesMap[requestInfo.api]!!.add(requestInfo)

    }


}