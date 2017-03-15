package com.mich.android.mich;

import android.app.Application;

public class App extends Application {

    private static App singleton;
    private String loginToken;



    private int userID;


    public static App getInstance(){
        return singleton;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        singleton = this;
    }

    public void setLoginToken(String loginToken) {
        this.loginToken = loginToken;
    }

    public String  getLoginToken() {
        return loginToken;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }
}
