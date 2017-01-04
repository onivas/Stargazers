package com.example.savino.githubstarring.di.module;

import com.example.savino.githubstarring.api.Manager;

import dagger.Module;
import dagger.Provides;


@Module
public class ApiModule {

    @Provides
    Manager provideManager() {
        return new Manager();
    }
}
