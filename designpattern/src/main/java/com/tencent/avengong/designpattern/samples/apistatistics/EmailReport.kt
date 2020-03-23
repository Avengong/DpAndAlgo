package com.tencent.avengong.designpattern.samples.apistatistics

import java.util.concurrent.Executors
import java.util.concurrent.TimeUnit

class EmailReport {
    val scheduledExecutorService = Executors.newSingleThreadScheduledExecutor()
    lateinit var iMetricsStorage: IMetricsStorage
    private val DAY_HOURS_IN_SECONDS = 86400L
    lateinit var iDisplayOutput: IDisplayOutput

    constructor(iMetricsStorage: IMetricsStorage)
            : this(iMetricsStorage, EmaileOutput()) { //依赖注入
    }

    constructor(iMetricsStorage: IMetricsStorage, iDisplayOutput: IDisplayOutput) {
        this.iMetricsStorage = iMetricsStorage
        this.iDisplayOutput = iDisplayOutput
    }


    fun startScheduleReport(delay: Long) {

        val mutableMapOf = hashMapOf<String, Aggerator.Companion.RequestStat>()
        scheduledExecutorService.schedule({
            val durationInMillis = DAY_HOURS_IN_SECONDS * 1000
            val endTimeInMillis = System.currentTimeMillis()
            val startTimeInMillis = endTimeInMillis - durationInMillis
            val requestInfos = iMetricsStorage.getRequestInfos(startTimeInMillis, endTimeInMillis)

            requestInfos.forEach {
                val key = it.key
                val value = it.value
                //得到转换数据
                val requestStat = Aggerator.aggregate(value, durationInMillis)
                mutableMapOf.put(key, requestStat)
                //显示到中断
                iDisplayOutput.display(mutableMapOf)
            }


        }, delay, TimeUnit.MILLISECONDS)


    }


}