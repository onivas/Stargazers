package com.example.savino.githubstarring.activities;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;

import com.example.savino.githubstarring.MyApplication;
import com.example.savino.githubstarring.Presenter.OwnerRepoPresenter;
import com.example.savino.githubstarring.R;
import com.example.savino.githubstarring.databinding.ActivityOwnerReposBinding;
import com.example.savino.githubstarring.di.component.OwnerRepoPresenterComponent;
import com.example.savino.githubstarring.model.Repos;
import com.example.savino.githubstarring.mvp.OwnerContract;

import javax.inject.Inject;

public class OwnerReposActivity extends AppCompatActivity implements OwnerContract.View {

    public static final String OWNER = "OWNER";

    @Inject
    OwnerRepoPresenter mPresenter;

    private ActivityOwnerReposBinding mBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        OwnerRepoPresenterComponent component = ((MyApplication) getApplication()).getOwnerRepoPresenterComponent();
        component.inject(this);  // Presenter is provided here, see @Inject annotation on field variable

        Intent intent = getIntent();
        String ownerName = intent.getStringExtra(OWNER);

        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_owner_repos);

        setupToolbar();

        mPresenter.setView(this);
        mPresenter.start();

        if (!TextUtils.isEmpty(ownerName)) {
            mPresenter.repos(ownerName);
        } else {
            showErrorPage("Empty Owner name");
        }

    }

    private void setupToolbar() {
        setSupportActionBar(mBinding.toolbar);
        ActionBar supportActionBar = getSupportActionBar();
        supportActionBar.setTitle("Repos");
        supportActionBar.setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void showResults(Repos repos) {
        mBinding.setRepo(repos);
        mBinding.executePendingBindings();

        mBinding.activityOwnerRepos.setVisibility(View.VISIBLE);
        mBinding.error.setVisibility(View.GONE);
    }

    @Override
    public void showErrorPage(String message) {
        mBinding.error.setText(message);

        mBinding.activityOwnerRepos.setVisibility(View.GONE);
        mBinding.error.setVisibility(View.VISIBLE);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPresenter.stop();
    }
}
