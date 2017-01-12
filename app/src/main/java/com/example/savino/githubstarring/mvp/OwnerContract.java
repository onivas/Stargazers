package com.example.savino.githubstarring.mvp;


import com.example.savino.githubstarring.model.Repos;

public class OwnerContract {

    public interface View extends BaseView{

        void showResults(Repos repos);
    }

    public interface Presenter extends BasePresenter{

        void repos(String owner);
    }
}
