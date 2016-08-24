package com.example.qube.firebasechatroom;

/**
 * Created by Qube on 8/24/16.
 */
public class Messages {
    private String mUserName, mMessage;

    public Messages() {
    }

    public Messages(String userName, String message) {
        this.mUserName = userName;
        this.mMessage = message;
    }

    public String getmUserName() {
        return mUserName;
    }

    public void setmUserName(String mUserName) {
        this.mUserName = mUserName;
    }

    public String getmMessage() {
        return mMessage;
    }

    public void setmMessage(String mMessage) {
        this.mMessage = mMessage;
    }
}
