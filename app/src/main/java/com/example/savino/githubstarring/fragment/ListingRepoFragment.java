package com.example.savino.githubstarring.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.savino.githubstarring.MainActivity;
import com.example.savino.githubstarring.R;
import com.example.savino.githubstarring.adapter.Adapter;
import com.example.savino.githubstarring.model.Stargazers;
import com.example.savino.githubstarring.mvp.Contract;

import java.util.ArrayList;

/**
 * Created by savino on 29/12/16.
 */

public class ListingRepoFragment extends Fragment implements Contract.View,
        MainActivity.onDialogFilledListener {

    ListingRepoPresenter mPresenter;
    private RecyclerView mRecyclerView;
    private TextView mErrorMessage;
    private TextView mEmptyPage;

    public static ListingRepoFragment newInstance() {
        ListingRepoFragment fragment = new ListingRepoFragment();
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        MainActivity activity = (MainActivity) getActivity();
        activity.setListener(this);

        View view = inflater.inflate(R.layout.fragment_listing_repo, container, false);

        mErrorMessage = (TextView) view.findViewById(R.id.error);
        mEmptyPage = (TextView) view.findViewById(R.id.empty);

        mRecyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);
        mRecyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(activity);
        mRecyclerView.setLayoutManager(layoutManager);

        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mPresenter =  new ListingRepoPresenter(this);
        mPresenter.start();
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mPresenter.stop();
    }

    @Override
    public void populateResult(ArrayList<Stargazers> stargazerses) {
        Adapter adapter = new Adapter(stargazerses, getActivity());
        mRecyclerView.swapAdapter(adapter, false);
        showResults();
    }

    @Override
    public void showResults() {
        mErrorMessage.setVisibility(View.GONE);
        mEmptyPage.setVisibility(View.GONE);
        mRecyclerView.setVisibility(View.VISIBLE);
    }

    @Override
    public void showErrorPage(String message) {
        mErrorMessage.setText(message);

        mErrorMessage.setVisibility(View.VISIBLE);
        mEmptyPage.setVisibility(View.GONE);
        mRecyclerView.setVisibility(View.GONE);
    }

    @Override
    public void showEmptyPage() {
        mErrorMessage.setVisibility(View.GONE);
        mEmptyPage.setVisibility(View.VISIBLE);
        mRecyclerView.setVisibility(View.GONE);
    }

    @Override
    public void onDialogFilled(String owner, String repo) {
        mPresenter.manageCall(owner, repo);
    }
}
