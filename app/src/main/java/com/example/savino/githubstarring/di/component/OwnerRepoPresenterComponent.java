package com.example.savino.githubstarring.di.component;

import com.example.savino.githubstarring.Presenter.OwnerRepoPresenter;
import com.example.savino.githubstarring.activities.OwnerReposActivity;
import com.example.savino.githubstarring.api.ApiManager;
import com.example.savino.githubstarring.di.module.ApiModule;
import com.example.savino.githubstarring.di.module.OwnerReposModule;

import dagger.Component;

@Component(modules = {OwnerReposModule.class, ApiModule.class})
public interface OwnerRepoPresenterComponent {

    void inject(OwnerReposActivity activity);

    OwnerRepoPresenter provideOwnerRepoPresenter();

    ApiManager provideApiManager();
}
