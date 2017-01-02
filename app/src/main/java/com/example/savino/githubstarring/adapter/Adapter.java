package com.example.savino.githubstarring.adapter;

import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.savino.githubstarring.R;
import com.example.savino.githubstarring.databinding.LayoutRecyclerViewBinding;
import com.example.savino.githubstarring.model.Stargazers;

import java.util.ArrayList;


public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {

    private ArrayList<Stargazers> mData;

    public Adapter(ArrayList<Stargazers> data) {
        mData = data;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.layout_recycler_view, parent, false);

        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        Stargazers user = mData.get(position);

        final LayoutRecyclerViewBinding holderBinding = holder.getBinding();
        holderBinding.setUser(user);
        holderBinding.executePendingBindings();  // executePendingBindings to make binding happen immediately
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private LayoutRecyclerViewBinding mBinding;

        public ViewHolder(View itemView) {
            super(itemView);
            mBinding = DataBindingUtil.bind(itemView);
        }

        public LayoutRecyclerViewBinding getBinding() {
            return mBinding;
        }
    }
}
