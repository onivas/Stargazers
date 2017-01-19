package com.example.savino.githubstarring.activities;

import android.content.DialogInterface;
import android.databinding.DataBindingUtil;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.View;
import android.widget.FrameLayout;

import com.example.savino.githubstarring.R;
import com.example.savino.githubstarring.databinding.ActivityMainBinding;
import com.example.savino.githubstarring.databinding.DialogBinding;
import com.example.savino.githubstarring.fragment.ListingRepoFragment;


public class MainActivity extends AppCompatActivity {

    onDialogFilledListener mListener;
    private ActivityMainBinding mBinding;
    private ListingRepoFragment mFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FragmentManager supportFragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = supportFragmentManager.beginTransaction();

        if (savedInstanceState == null) {
            mFragment = ListingRepoFragment.newInstance();
            fragmentTransaction.replace(R.id.container, mFragment);
            fragmentTransaction.commit();
        }

        mBinding.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createDialog();
            }
        });
    }

    private void createDialog() {
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(MainActivity.this, R.style.dialog);

        final DialogBinding binding = DataBindingUtil.inflate(getLayoutInflater(), R.layout.dialog, null, false);

        AlertDialog dialog = dialogBuilder.setView(binding.getRoot())
                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String o = binding.owner.getText().toString();
                        String r = binding.repo.getText().toString();
                        if (!hasEmptyField(o, r)) {
                            if (mListener != null) {
                                mListener.onDialogFilled(o, r);
                            }
                        }
                    }

                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                })
                .create();

        DisplayMetrics metrics = dialogBuilder.getContext()
                .getResources()
                .getDisplayMetrics();

        final int width = metrics.widthPixels;
        dialog.setOnShowListener(new DialogInterface.OnShowListener() {

            @Override
            public void onShow(DialogInterface dialog) {
                AlertDialog alertDialog = (AlertDialog) dialog;
                View view = alertDialog.getWindow().getDecorView()
                        .findViewById(android.R.id.content);
                FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) view.getLayoutParams();
                layoutParams.width = 4 * width / 5; // 80% of screen
                layoutParams.gravity = Gravity.CENTER;
                view.setLayoutParams(layoutParams);
                alertDialog.getWindow().setBackgroundDrawable(
                        new ColorDrawable(Color.WHITE));
            }
        });

        dialog.show();

    }

    private boolean hasEmptyField(String o, String r) {
        if (TextUtils.isEmpty(o) || TextUtils.isEmpty(r)) {
            mBinding.error.setVisibility(View.VISIBLE);
            mBinding.container.setVisibility(View.GONE);
            return true;
        }
        mBinding.error.setVisibility(View.GONE);
        mBinding.container.setVisibility(View.VISIBLE);
        return false;
    }

    @Override
    protected void onStop() {
        super.onStop();
        mListener = null;
    }

    public void setListener(ListingRepoFragment fragment) {
        if (fragment instanceof onDialogFilledListener) {
            mListener = fragment;
        }
    }

    public interface onDialogFilledListener {

        void onDialogFilled(String owner, String repo);
    }
}
