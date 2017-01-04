package com.example.savino.githubstarring.fragment;

import android.util.Log;

import com.example.savino.githubstarring.api.Manager;
import com.example.savino.githubstarring.di.component.DaggerListingRepoPresenterComponent;
import com.example.savino.githubstarring.di.component.ListingRepoPresenterComponent;
import com.example.savino.githubstarring.di.module.ApiModule;
import com.example.savino.githubstarring.model.Stargazers;
import com.example.savino.githubstarring.mvp.Contract;

import java.util.ArrayList;

import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;


public class ListingRepoPresenter implements Contract.Presenter {

    ListingRepoFragment mView;
    private Manager mManager;

    public ListingRepoPresenter() {}

    public void setView(ListingRepoFragment view) {
        mView = view;
    }

    @Override
    public void start() {
        ListingRepoPresenterComponent component = DaggerListingRepoPresenterComponent.builder()
                .apiModule(new ApiModule())
                .build();
        mManager =  component.provideManager();
    }

    @Override
    public void stop() {
        mView = null;
    }

    @Override
    public boolean manageCall(String owner, String repo) {

        mManager.listRepository(owner, repo)
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

}
