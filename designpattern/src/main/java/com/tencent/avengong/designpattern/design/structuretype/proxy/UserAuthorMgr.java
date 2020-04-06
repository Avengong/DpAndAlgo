package com.tencent.avengong.designpattern.design.structuretype.proxy;

public class UserAuthorMgr implements IUserAthor {


    public void UserAuthorMgr() {

    }

    public void register() {
        // do register

        //statistic
        System.out.println("register ---origin, go ");

    }

    public void login() {
        //statistic

        System.out.println("login ---origin, go ");
    }

}
