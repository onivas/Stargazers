package com.example.savino.githubstarring.api;

import com.example.savino.githubstarring.model.Repos;
import com.example.savino.githubstarring.model.Stargazers;

import java.util.ArrayList;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Path;
import retrofit2.http.Query;
import rx.Observable;


public class Manager implements ApiManager {

    private Retrofit mRetrofit;

    @Override
    public Observable<ArrayList<Stargazers>> listRepository(String owner, String repo) {
        return apiManager().listRepository(owner, repo);
    }

    @Override
    public Observable<ArrayList<Stargazers>> loadMoreRepositories(@Path("owner") String owner, @Path("repo") String repo, @Query("page") int pageNumber) {
        return apiManager().loadMoreRepositories(owner, repo, pageNumber);
    }

    @Override
    public Observable<ArrayList<Repos>> userRepos(@Path("owner") String owner) {
        return apiManager().userRepos(owner);
    }

    private ApiManager apiManager() {
        if (mRetrofit == null) {
            HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
            logging.setLevel(HttpLoggingInterceptor.Level.BASIC);
            OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
            httpClient.addInterceptor(logging);  // <-- this is the important line!

            mRetrofit = new Retrofit.Builder()
                    .baseUrl("https://api.github.com")
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    .client(httpClient.build())
                    .build();
        }

        return mRetrofit.create(ApiManager.class);
    }
}
