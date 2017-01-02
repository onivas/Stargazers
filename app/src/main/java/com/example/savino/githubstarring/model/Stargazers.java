package com.example.savino.githubstarring.model;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

import com.google.gson.annotations.SerializedName;


public class Stargazers extends BaseObservable{

    @SerializedName("id")
    String mId;

    @SerializedName("login")
    String mLogin;

    @SerializedName("avatar_url")
    String mAvatar;

    public Stargazers(String id, String login, String avatar) {
        mId = id;
        mLogin = login;
        mAvatar = avatar;
    }

    @Bindable
    public String getId() {
        return mId;
    }

    @Bindable
    public String getLogin() {
        return mLogin;
    }

    public String getAvatar() {
        return mAvatar;
    }
}
