package com.tencent.avengong.designpattern.samples.apiauthor2;

public class ApiRequest {

    private String mBaseUrl;
    private String mAppId;
    private String mToken;
    private long mCreateTime;


    public static ApiRequest parseUrl(String originUrl) {

        return new ApiRequest();
    }

}
