package com.example.savino.githubstarring.Presenter;


import android.util.Log;

import com.example.savino.githubstarring.MyApplication;
import com.example.savino.githubstarring.activities.OwnerReposActivity;
import com.example.savino.githubstarring.api.ApiManager;
import com.example.savino.githubstarring.di.component.OwnerRepoPresenterComponent;
import com.example.savino.githubstarring.model.Repos;
import com.example.savino.githubstarring.mvp.OwnerContract;

import java.util.ArrayList;

import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

public class OwnerRepoPresenter implements OwnerContract.Presenter{

    OwnerReposActivity mView;

    private ApiManager mApiManager;

    @Override
    public void repos(String owner) {

        mApiManager.userRepos(owner)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Action1<ArrayList<Repos>>() {
                    @Override
                    public void call(ArrayList<Repos> reposes) {
                        mView.showResults(reposes);
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        String message = throwable.getMessage();

                        mView.showErrorPage(message);
                        Log.d(">>>>>>", message);
                    }
                });
    }

    @Override
    public void stop() {

    }

    @Override
    public void start() {
        OwnerRepoPresenterComponent component = ((MyApplication) mView.getApplication()).getOwnerRepoPresenterComponent();
        mApiManager = component.provideApiManager();
    }

    public void setView(OwnerReposActivity activity) {
        mView = activity;
    }
}
