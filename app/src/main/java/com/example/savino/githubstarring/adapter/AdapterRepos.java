package com.example.savino.githubstarring.adapter;

import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.savino.githubstarring.R;
import com.example.savino.githubstarring.databinding.OwnerReposRecyclerViewBinding;
import com.example.savino.githubstarring.model.Repos;

import java.util.ArrayList;

public class AdapterRepos extends RecyclerView.Adapter<AdapterRepos.ViewHolder>{

    ArrayList<Repos> mReposes;

    public AdapterRepos(ArrayList<Repos> reposes) {
        mReposes = reposes;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.owner_repos_recycler_view, parent, false);

        AdapterRepos.ViewHolder vh = new AdapterRepos.ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Repos repo = mReposes.get(position);

        OwnerReposRecyclerViewBinding holderBinding = holder.getBinding();
        holderBinding.setRepo(repo);
        holderBinding.executePendingBindings();

    }

    @Override
    public int getItemCount() {
        return mReposes.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {


        private final OwnerReposRecyclerViewBinding mBinding;

        public ViewHolder(View itemView) {
            super(itemView);
            mBinding = DataBindingUtil.bind(itemView);
        }

        public OwnerReposRecyclerViewBinding getBinding() {
            return mBinding;
        }
    }
}
