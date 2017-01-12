package com.example.savino.githubstarring.di.module;

import com.example.savino.githubstarring.Presenter.OwnerRepoPresenter;

import dagger.Module;
import dagger.Provides;

@Module
public class OwnerReposModule {

    @Provides
    OwnerRepoPresenter provideOwnerRepoPresenter() {
        return new OwnerRepoPresenter();
    }
}
