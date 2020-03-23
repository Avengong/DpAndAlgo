package com.tencent.avengong.designpattern.samples.apistatistics

interface IDisplayOutput {

    fun display(hashMap: HashMap<String, Aggerator.Companion.RequestStat>)
}