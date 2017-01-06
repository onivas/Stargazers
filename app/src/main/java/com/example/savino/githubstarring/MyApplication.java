package com.example.savino.githubstarring;
import android.app.Application;

import com.example.savino.githubstarring.di.component.DaggerListingRepoPresenterComponent;
import com.example.savino.githubstarring.di.component.ListingRepoPresenterComponent;
import com.example.savino.githubstarring.di.module.ApiModule;


public class MyApplication extends Application {

    private ListingRepoPresenterComponent mListingRepoPresenterComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        mListingRepoPresenterComponent = createListingRepoComponent();
    }

    private ListingRepoPresenterComponent createListingRepoComponent() {
        return DaggerListingRepoPresenterComponent.builder()
                .apiModule(new ApiModule())
                .build();
    }

    public void setListingRepoPresenterComponent(ListingRepoPresenterComponent component) {
        mListingRepoPresenterComponent = component;
    }

    public ListingRepoPresenterComponent getListingRepoPresenterComponent(){
        if (mListingRepoPresenterComponent != null) {
            return mListingRepoPresenterComponent;
        }

        mListingRepoPresenterComponent = createListingRepoComponent();
        return mListingRepoPresenterComponent;
    }
}
