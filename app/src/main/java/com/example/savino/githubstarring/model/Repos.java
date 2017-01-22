package com.example.savino.githubstarring.model;

import com.google.gson.annotations.SerializedName;

import java.util.Date;

public class Repos {
    @SerializedName("name")
    String mName;

    @SerializedName("description")
    String mDescription;

    @SerializedName("forks")
    int mForks;

    @SerializedName("watchers")
    int mWatchers;

    @SerializedName("language")
    String mLanguage;

    @SerializedName("created_at")
    Date mCreatedAt;

    public Repos(String name, String description, int forks, int watchers, String language, Date createdAt) {
        mName = name;
        mDescription = description;
        mForks = forks;
        mWatchers = watchers;
        mLanguage = language;
        mCreatedAt = createdAt;
    }

    public String getName() {
        return mName;
    }

    public String getDescription() {
        return mDescription;
    }

    public int getForks() {
        return mForks;
    }

    public int getWatchers() {
        return mWatchers;
    }

    public String getLanguage() {
        return mLanguage;
    }

    public Date getCreatedAt() {
        return mCreatedAt;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Repos)) return false;

        Repos repos = (Repos) o;

        if (mForks != repos.mForks) return false;
        if (mWatchers != repos.mWatchers) return false;
        if (mName != null ? !mName.equals(repos.mName) : repos.mName != null) return false;
        if (mDescription != null ? !mDescription.equals(repos.mDescription) : repos.mDescription != null)
            return false;
        if (mLanguage != null ? !mLanguage.equals(repos.mLanguage) : repos.mLanguage != null)
            return false;
        return mCreatedAt != null ? mCreatedAt.equals(repos.mCreatedAt) : repos.mCreatedAt == null;

    }

    @Override
    public int hashCode() {
        int result = mName != null ? mName.hashCode() : 0;
        result = 31 * result + (mDescription != null ? mDescription.hashCode() : 0);
        result = 31 * result + mForks;
        result = 31 * result + mWatchers;
        result = 31 * result + (mLanguage != null ? mLanguage.hashCode() : 0);
        result = 31 * result + (mCreatedAt != null ? mCreatedAt.hashCode() : 0);
        return result;
    }

}
