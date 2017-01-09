package com.example.savino.githubstarring.Presenter;

import android.content.Intent;
import android.util.Log;

import com.example.savino.githubstarring.MyApplication;
import com.example.savino.githubstarring.activities.OwnerReposActivity;
import com.example.savino.githubstarring.api.ApiManager;
import com.example.savino.githubstarring.di.component.ListingRepoPresenterComponent;
import com.example.savino.githubstarring.fragment.ListingRepoFragment;
import com.example.savino.githubstarring.model.Stargazers;
import com.example.savino.githubstarring.mvp.Contract;

import java.util.ArrayList;

import rx.Observable;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;


public class ListingRepoPresenter implements Contract.Presenter {

    ListingRepoFragment mView;
    private ApiManager mManager;

    private CompositeSubscription mSubscription = new CompositeSubscription();

    public ListingRepoPresenter() {
    }

    public void setView(ListingRepoFragment view) {
        mView = view;
    }

    @Override
    public void start() {
        ListingRepoPresenterComponent component = ((MyApplication) mView.getActivity().getApplication())
                .getListingRepoPresenterComponent();

        mManager = component.provideManager();
    }

    @Override
    public void stop() {
        mView = null;
        mSubscription.unsubscribe();
    }

    @Override
    public boolean manageCall(String owner, String repo) {

        Subscription subscribe = mManager.listRepository(owner, repo)
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

        mSubscription.add(subscribe);
        return true;
    }

    @Override
    public void openOwnerRepo(Observable<String> observable) {

        Subscription subscribe = observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<String>() {
                    @Override
                    public void call(String owner) {
                        Intent intent = new Intent(mView.getContext(), OwnerReposActivity.class);
                        intent.putExtra(OwnerReposActivity.OWNER, owner);
                        mView.startActivity(intent);
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        Log.e(">>> ERROR manageCall:", throwable.getMessage());
                    }
                });

        mSubscription.add(subscribe);
    }

    public void showErrorPage(String message) {
        mView.showErrorPage(message);
    }

}
