package com.example.savino.githubstarring.di.component;

import com.example.savino.githubstarring.api.ApiManager;
import com.example.savino.githubstarring.di.module.ApiModule;
import com.example.savino.githubstarring.di.module.ListingRepoPresenterModule;
import com.example.savino.githubstarring.fragment.ListingRepoPresenter;

import dagger.Component;

@Component(modules = {ListingRepoPresenterModule.class, ApiModule.class})
public interface ListingRepoPresenterComponent {

    ListingRepoPresenter provideListingRepoPresenter();

    ApiManager provideManager();
}
