package com.example.savino.githubstarring.api;

import com.example.savino.githubstarring.model.Repos;
import com.example.savino.githubstarring.model.Stargazers;

import java.util.ArrayList;

import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import rx.Observable;


public interface ApiManager {

    @GET("/repos/{owner}/{repo}/stargazers")
    Observable<ArrayList<Stargazers>> listRepository(@Path("owner") String owner, @Path("repo") String repo);

    @GET("/repos/{owner}/{repo}/stargazers")
    Observable<ArrayList<Stargazers>> loadMoreRepositories(@Path("owner") String owner, @Path("repo") String repo, @Query("page") int pageNumber);

    @GET("/users/{owner}/repos")
    Observable<ArrayList<Repos>> userRepos(@Path("owner") String owner);
}
