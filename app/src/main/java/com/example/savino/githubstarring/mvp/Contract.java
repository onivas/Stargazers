package com.example.savino.githubstarring.mvp;

import com.example.savino.githubstarring.model.Stargazers;

import java.util.ArrayList;

/**
 * Created by savino on 30/12/16.
 */

public class Contract {

    public interface View {

        void populateResult(ArrayList<Stargazers> stargazerses);

        void showResults();

        void showErrorPage(String message);

        void showEmptyPage();
    }

    public interface Presenter {

        void start();

        void stop();

        boolean manageCall(String owner, String repo);

    }
}
