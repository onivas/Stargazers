package com.example.savino.githubstarring.api;

import com.example.savino.githubstarring.model.Stargazers;

import java.util.ArrayList;

import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;


public interface ApiManager {

    @GET("/repos/{owner}/{repo}/stargazers")
    Observable<ArrayList<Stargazers>> listRepository(@Path("owner") String owner, @Path("repo") String repo);
}
