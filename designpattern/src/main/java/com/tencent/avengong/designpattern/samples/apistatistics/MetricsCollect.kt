package com.tencent.avengong.designpattern.samples.apistatistics

class MetricsCollect {


    var IMetricsStorage: IMetricsStorage

    constructor(IMetricsStorage: IMetricsStorage) {
        this.IMetricsStorage = IMetricsStorage
    }


    fun recordRequestInfo(requestInfo: RequestInfo) {

        IMetricsStorage.storage(requestInfo)

    }

}