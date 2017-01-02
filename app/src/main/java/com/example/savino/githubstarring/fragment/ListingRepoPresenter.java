package com.example.savino.githubstarring.fragment;

import android.util.Log;

import com.example.savino.githubstarring.api.ApiManager;
import com.example.savino.githubstarring.model.Stargazers;
import com.example.savino.githubstarring.mvp.Contract;

import java.util.ArrayList;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;


public class ListingRepoPresenter implements Contract.Presenter {

    ListingRepoFragment mView;
    private Retrofit mRetrofit;

    public ListingRepoPresenter() {}

    public void setView(ListingRepoFragment view) {
        mView = view;
    }

    @Override
    public void start() {

    }

    @Override
    public void stop() {
        mView = null;
    }

    @Override
    public boolean manageCall(String owner, String repo) {

        apiManager().listRepository(owner, repo)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<ArrayList<Stargazers>>() {
                    @Override
                    public void call(ArrayList<Stargazers> stargazerses) {
                        if (stargazerses.isEmpty()) {
                            mView.showEmptyPage();
                            return;
                        }
                        mView.populateResult(stargazerses);
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        showErrorPage(throwable.getMessage());
                        Log.e(">>> ERROR manageCall:", throwable.getMessage());
                    }
                });

        return true;
    }

    public void showErrorPage(String message) {
        mView.showErrorPage(message);
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
