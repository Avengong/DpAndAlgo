package com.tencent.avengong.designpattern.samples.apiauthor2;

public class AuthorToken {

    private static final long EXPIRE_TIME = 1000;
    private long mCreateTime;
    private String mToken;

    public static AuthorToken createToken(long mCreateTime, String mToken) {


        return new AuthorToken();
    }

    public boolean isExpired() {
        return System.currentTimeMillis() - mCreateTime > EXPIRE_TIME;
    }

    public boolean isValid(AuthorToken authorToken) {
        return authorToken.mToken.equals(mToken);
    }


}
