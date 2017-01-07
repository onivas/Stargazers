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

        createListingRepoComponent();
    }

    private ListingRepoPresenterComponent createListingRepoComponent() {
        mListingRepoPresenterComponent = DaggerListingRepoPresenterComponent.builder()
                .apiModule(new ApiModule())
                .build();

        return mListingRepoPresenterComponent;
    }

    public void setListingRepoPresenterComponent(ListingRepoPresenterComponent component) {
        mListingRepoPresenterComponent = component;
    }

    public ListingRepoPresenterComponent getListingRepoPresenterComponent(){
        if (mListingRepoPresenterComponent != null) {
            return mListingRepoPresenterComponent;
        }

        return createListingRepoComponent();
    }
}
