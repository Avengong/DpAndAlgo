package com.tencent.avengong.designpattern.samples.apistatistics

import java.util.*

class Aggerator {

    companion object {
        fun aggregate(requestInfos: List<RequestInfo>, durationInMillis: Long): RequestStat {
            var maxRespTime = java.lang.Double.MIN_VALUE
            var minRespTime = java.lang.Double.MAX_VALUE
            var avgRespTime = -1.0
            var p999RespTime = -1.0
            var p99RespTime = -1.0
            var sumRespTime = 0.0
            var count: Long = 0
            for (requestInfo in requestInfos) {
                ++count
                val respTime = requestInfo.responseTime.toDouble()
                if (maxRespTime < respTime) {
                    maxRespTime = respTime
                }
                if (minRespTime > respTime) {
                    minRespTime = respTime
                }
                sumRespTime += respTime
            }
            if (count != 0L) {
                avgRespTime = sumRespTime / count
            }
            val tps = count / durationInMillis * 1000
            Collections.sort(requestInfos, object : Comparator<RequestInfo> {
                override fun compare(o1: RequestInfo, o2: RequestInfo): Int {
                    val diff = (o1.responseTime - o2.responseTime).toDouble()
                    return if (diff < 0.0) {
                        -1
                    } else if (diff > 0.0) {
                        1
                    } else {
                        0
                    }
                }
            })

            if (count != 0L) {
                val idx999 = (count * 0.999).toInt()
                val idx99 = (count * 0.99).toInt()
                p999RespTime = requestInfos[idx999].responseTime.toDouble()
                p99RespTime = requestInfos[idx99].responseTime.toDouble()
            }
            val requestStat = RequestStat()
            requestStat.maxResponseTime = (maxRespTime)
            requestStat.minResponseTime = (minRespTime)
            requestStat.avgResponseTime = (avgRespTime)
            requestStat.p999ResponseTime = (p999RespTime)
            requestStat.p99ResponseTime = (p99RespTime)
            requestStat.count = (count)
            requestStat.tps = (tps)
            return requestStat
        }

        class RequestStat {
            var maxResponseTime: Double = 0.toDouble()
            var minResponseTime: Double = 0.toDouble()
            var avgResponseTime: Double = 0.toDouble()
            var p999ResponseTime: Double = 0.toDouble()
            var p99ResponseTime: Double = 0.toDouble()
            var count: Long = 0
            var tps: Long = 0
            //...省略getter/setter方法...

        }
    }


}

