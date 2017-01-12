package com.example.savino.githubstarring;

import android.app.Application;

import com.example.savino.githubstarring.di.component.DaggerListingRepoPresenterComponent;
import com.example.savino.githubstarring.di.component.DaggerOwnerRepoPresenterComponent;
import com.example.savino.githubstarring.di.component.ListingRepoPresenterComponent;
import com.example.savino.githubstarring.di.component.OwnerRepoPresenterComponent;
import com.example.savino.githubstarring.di.module.ApiModule;
import com.example.savino.githubstarring.di.module.ListingRepoPresenterModule;
import com.example.savino.githubstarring.di.module.OwnerReposModule;


public class MyApplication extends Application {

    private ListingRepoPresenterComponent mListingRepoPresenterComponent;
    private OwnerRepoPresenterComponent mOwnerRepoPresenterComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        createListingRepoComponent();

        createOwnerRepoComponent();
    }

    private OwnerRepoPresenterComponent createOwnerRepoComponent() {

        mOwnerRepoPresenterComponent = DaggerOwnerRepoPresenterComponent.builder()
                .apiModule(new ApiModule())
                .ownerReposModule(new OwnerReposModule())
                .build();

        return mOwnerRepoPresenterComponent;
    }

    private ListingRepoPresenterComponent createListingRepoComponent() {
        mListingRepoPresenterComponent = DaggerListingRepoPresenterComponent.builder()
                .apiModule(new ApiModule())
                .listingRepoPresenterModule(new ListingRepoPresenterModule())
                .build();

        return mListingRepoPresenterComponent;
    }

    public void setListingRepoPresenterComponent(ListingRepoPresenterComponent component) {
        mListingRepoPresenterComponent = component;
    }

    public ListingRepoPresenterComponent getListingRepoPresenterComponent() {
        if (mListingRepoPresenterComponent != null) {
            return mListingRepoPresenterComponent;
        }

        return createListingRepoComponent();
    }

    public OwnerRepoPresenterComponent getOwnerRepoPresenterComponent() {
        if (mOwnerRepoPresenterComponent != null) {
            return mOwnerRepoPresenterComponent;
        }

        return createOwnerRepoComponent();
    }

    public void setOwnerRepoPresenterComponent(OwnerRepoPresenterComponent component) {
        mOwnerRepoPresenterComponent = component;
    }

}
