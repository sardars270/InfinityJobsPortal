package com.example.infinityjobportal;

import android.app.Application;

class global_vars extends Application {
    public String getLoginUserID() {
        return LoginUserID;
    }

    public void setLoginUserID(String loginUserID) {
        LoginUserID = loginUserID;
    }

    private   String LoginUserID;
}
