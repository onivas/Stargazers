package com.example.savino.githubstarring.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by savino on 29/12/16.
 */

public class Stargazers {

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

    public String getId() {
        return mId;
    }

    public String getLogin() {
        return mLogin;
    }

    public String getAvatar() {
        return mAvatar;
    }
}
