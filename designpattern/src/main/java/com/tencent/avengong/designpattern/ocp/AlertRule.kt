package com.tencent.avengong.designpattern.ocp

class AlertRule {

    internal var mMaxTps: Int

    internal var mMaxErrorCount: Int

    internal var mMaxTimeoutCount: Int

    private var mAlerRules = mutableMapOf<String, AlertRule>()

    constructor(mMaxTps: Int, mMaxErrorCode: Int, mMaxTimeoutCount: Int) {
        this.mMaxTps = mMaxTps
        this.mMaxErrorCount = mMaxErrorCode
        this.mMaxTimeoutCount = mMaxTimeoutCount
    }

    fun registerAletRulues(api: String, alertRule: AlertRule) {

        mAlerRules[api] = alertRule

    }

    fun getMatchRules(api: String): AlertRule? {
        return mAlerRules.get(api)
    }


}