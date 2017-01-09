package com.example.savino.githubstarring.di.module;


import com.example.savino.githubstarring.Presenter.ListingRepoPresenter;

import dagger.Module;
import dagger.Provides;

@Module
public class ListingRepoPresenterModule {

    @Provides
    ListingRepoPresenter provideListingRepoPresenter() {
        return new ListingRepoPresenter();
    }
}
