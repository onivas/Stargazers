package com.example.savino.githubstarring.adapter;

import android.app.Activity;
import android.graphics.Bitmap;
import android.support.v4.app.FragmentActivity;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.target.BitmapImageViewTarget;
import com.example.savino.githubstarring.R;
import com.example.savino.githubstarring.model.Stargazers;

import java.util.ArrayList;

/**
 * Created by savino on 30/12/16.
 */

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {

    private ArrayList<Stargazers> mData;
    private Activity mActivity;

    public Adapter(ArrayList<Stargazers> data, FragmentActivity activity) {
        mData = data;
        mActivity = activity;
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
        holder.mLogin.setText(mData.get(position).getLogin());
        Glide.with(mActivity)
                .load(mData.get(position).getAvatar())
                .asBitmap()
                .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                .into(new BitmapImageViewTarget(holder.mAvatar) {
                    @Override
                    protected void setResource(Bitmap resource) {
                        super.setResource(resource);
                        RoundedBitmapDrawable drawable = RoundedBitmapDrawableFactory.create(mActivity.getResources(), resource);
                        drawable.setCircular(true);
                        holder.mAvatar.setImageDrawable(drawable);
                    }
                });
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView mLogin;
        public ImageView mAvatar;

        public ViewHolder(View itemView) {
            super(itemView);
            mLogin = (TextView) itemView.findViewById(R.id.login);
            mAvatar = (ImageView) itemView.findViewById(R.id.avatar);
        }
    }
}
