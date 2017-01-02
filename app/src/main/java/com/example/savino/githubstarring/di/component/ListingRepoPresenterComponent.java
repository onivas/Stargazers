package com.example.savino.githubstarring.di.component;

import com.example.savino.githubstarring.di.module.ListingRepoPresenterModule;
import com.example.savino.githubstarring.fragment.ListingRepoPresenter;

import dagger.Component;

@Component(modules = {ListingRepoPresenterModule.class})
public interface ListingRepoPresenterComponent {

    ListingRepoPresenter provideListingRepoPresenter();

}
