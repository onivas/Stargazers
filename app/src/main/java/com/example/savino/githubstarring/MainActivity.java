package com.example.savino.githubstarring;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TextInputEditText;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.example.savino.githubstarring.fragment.ListingRepoFragment;


public class MainActivity extends AppCompatActivity {

    private FloatingActionButton mFab;
    private ListingRepoFragment mFragment;

    onDialogFilledListener mListener;
    private TextView mErrorMessage;
    private FrameLayout mFrameLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FragmentManager supportFragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = supportFragmentManager.beginTransaction();

        mFrameLayout = (FrameLayout) findViewById(R.id.container);

        mFragment = ListingRepoFragment.newInstance();
        fragmentTransaction.replace(R.id.container, mFragment);
        fragmentTransaction.commit();

        mFab = (FloatingActionButton) findViewById(R.id.fab);
        mFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createDialog();
            }
        });

        mErrorMessage = (TextView) findViewById(R.id.error);
    }

    private void createDialog() {
        AlertDialog.Builder dialog = new AlertDialog.Builder(MainActivity.this, R.style.dialog);

        LayoutInflater inflater = getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog, null);
        final TextInputEditText owner = (TextInputEditText) view.findViewById(R.id.owner);
        final TextInputEditText repo = (TextInputEditText) view.findViewById(R.id.repo);

        dialog.setView(view);
        dialog.setTitle("Fill both fields");
        dialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String o = owner.getText().toString();
                String r = repo.getText().toString();
                if (!hasEmptyField(o, r) ) {
                    if (mListener != null) {
                        mListener.onDialogFilled(o, r);
                    }
                }
            }

        });
        dialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        dialog.create().show();
    }

    private boolean hasEmptyField(String o, String r) {
        if (TextUtils.isEmpty(o) || TextUtils.isEmpty(r)) {
            mErrorMessage.setVisibility(View.VISIBLE);
            mFrameLayout.setVisibility(View.GONE);
            return true;
        }
        mErrorMessage.setVisibility(View.GONE);
        mFrameLayout.setVisibility(View.VISIBLE);
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
