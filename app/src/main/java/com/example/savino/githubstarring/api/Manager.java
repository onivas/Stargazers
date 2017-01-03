package com.example.savino.githubstarring.api;

import com.example.savino.githubstarring.model.Stargazers;

import java.util.ArrayList;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;


public class Manager implements ApiManager {

    private Retrofit mRetrofit;

    @Override
    public Observable<ArrayList<Stargazers>> listRepository(String owner, String repo) {
        return apiManager().listRepository(owner, repo);
    }

    private ApiManager apiManager() {
        if (mRetrofit == null) {
            mRetrofit = new Retrofit.Builder()
                    .baseUrl("https://api.github.com")
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    .build();
        }

        return mRetrofit.create(ApiManager.class);
    }
}
