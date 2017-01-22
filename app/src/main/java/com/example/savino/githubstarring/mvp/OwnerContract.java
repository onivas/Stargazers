package com.example.savino.githubstarring.mvp;


import com.example.savino.githubstarring.model.Repos;

import java.util.ArrayList;

public class OwnerContract {

    public interface View extends BaseView{

        void showResults(ArrayList<Repos> repos);
    }

    public interface Presenter extends BasePresenter{

        void repos(String owner);
    }
}
