package com.example.savino.githubstarring.mvp;

import com.example.savino.githubstarring.model.Stargazers;

import java.util.ArrayList;

import rx.Observable;

public class ContractListing {

    public interface View extends BaseView{

        void populateResult(ArrayList<Stargazers> stargazerses);

        void showEmptyPage();
    }

    public interface Presenter extends BasePresenter{

        boolean manageCall(String owner, String repo);

        void openOwnerRepo(Observable<String> observable);
    }
}
