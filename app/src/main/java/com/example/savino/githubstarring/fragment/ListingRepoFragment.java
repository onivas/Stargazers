package com.example.savino.githubstarring.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.savino.githubstarring.Presenter.ListingRepoPresenter;
import com.example.savino.githubstarring.R;
import com.example.savino.githubstarring.activities.MainActivity;
import com.example.savino.githubstarring.adapter.Adapter;
import com.example.savino.githubstarring.databinding.FragmentListingRepoBinding;
import com.example.savino.githubstarring.di.component.DaggerListingRepoPresenterComponent;
import com.example.savino.githubstarring.di.component.ListingRepoPresenterComponent;
import com.example.savino.githubstarring.di.module.ListingRepoPresenterModule;
import com.example.savino.githubstarring.model.Stargazers;
import com.example.savino.githubstarring.mvp.ContractListing;

import java.util.ArrayList;


public class ListingRepoFragment extends Fragment implements ContractListing.View,
        MainActivity.onDialogFilledListener {

    ListingRepoPresenter mPresenter;
    private RecyclerView mRecyclerView;
    private FragmentListingRepoBinding mBinding;
    private MainActivity mActivity;

    public static ListingRepoFragment newInstance() {
        ListingRepoFragment fragment = new ListingRepoFragment();
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ListingRepoPresenterComponent component = DaggerListingRepoPresenterComponent.builder()
                .listingRepoPresenterModule(new ListingRepoPresenterModule())
                .build();

        mPresenter = component.provideListingRepoPresenter();
        mPresenter.setView(this);
        mPresenter.start();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        mActivity = (MainActivity) getActivity();

        View view = inflater.inflate(R.layout.fragment_listing_repo, container, false);
        mBinding = FragmentListingRepoBinding.bind(view);

        mRecyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);
        mRecyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(mActivity);
        mRecyclerView.setLayoutManager(layoutManager);

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();

        mActivity.setListener(this);
    }

    @Override
    public void onDestroy() {
        mPresenter.stop();
        super.onDestroy();
    }

    @Override
    public void populateResult(ArrayList<Stargazers> stargazerses) {
        Adapter adapter = new Adapter(stargazerses);
        mPresenter.openOwnerRepo(adapter.getPositionClicks());
        mRecyclerView.swapAdapter(adapter, false);
        showResults();
    }

    @Override
    public void showResults() {
        mBinding.error.setVisibility(View.GONE);
        mBinding.empty.setVisibility(View.GONE);
        mRecyclerView.setVisibility(View.VISIBLE);
    }

    @Override
    public void showErrorPage(String message) {
        mBinding.error.setText(message);

        mBinding.error.setVisibility(View.VISIBLE);
        mBinding.empty.setVisibility(View.GONE);
        mRecyclerView.setVisibility(View.GONE);
    }

    @Override
    public void showEmptyPage() {
        mBinding.error.setVisibility(View.GONE);
        mBinding.empty.setVisibility(View.VISIBLE);
        mRecyclerView.setVisibility(View.GONE);
    }

    @Override
    public void onDialogFilled(String owner, String repo) {
        mPresenter.manageCall(owner, repo);
    }
}
