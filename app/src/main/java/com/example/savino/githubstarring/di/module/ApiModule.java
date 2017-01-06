package com.example.savino.githubstarring.di.module;

import com.example.savino.githubstarring.api.ApiManager;
import com.example.savino.githubstarring.api.Manager;

import dagger.Module;
import dagger.Provides;


@Module
public class ApiModule {

    @Provides
    public ApiManager provideManager() {
        return new Manager();
    }
}
