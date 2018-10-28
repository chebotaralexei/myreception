package com.electricsheeps.myreception.presentation.base;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.Toast;

import com.arellomobile.mvp.MvpAppCompatActivity;
import com.electricsheeps.myreception.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

public abstract class MvpBaseActivity extends MvpAppCompatActivity implements MvpBaseView {
    private Toast toast;
    protected CompositeDisposable compositeDisposable = new CompositeDisposable();
    protected View rootView;

    @Nullable
    @BindView(R.id.progressBar)
    View progressBar;

    protected void addSub(Disposable subscription) {
        compositeDisposable.add(subscription);
    }

    protected void afterOnCreate(Bundle savedInstanceState){

    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        if (compositeDisposable != null && !compositeDisposable.isDisposed())
            compositeDisposable.dispose();
    }
    protected abstract int setLayoutRes();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(setLayoutRes());
        rootView = findViewById(android.R.id.content);
        ButterKnife.bind(this, rootView);
    }



    protected void showToast(String message) {
        if (toast != null) toast.cancel();
        toast = Toast.makeText(this, message, Toast.LENGTH_LONG);
        toast.show();
    }

    protected void showToast(int message) {
        if (toast != null) toast.cancel();
        toast = Toast.makeText(this, message, Toast.LENGTH_LONG);
        toast.show();
    }

    protected void showDialog(Context context, String title, String message) {
        new AlertDialog.Builder(context)
                .setTitle(title)
                .setMessage(message)
                .setCancelable(false)
                .setPositiveButton(android.R.string.ok, null)
                .create()
                .show();
    }

    protected void showDialog(Context context, String title, String message, DialogInterface.OnClickListener lis) {
        new AlertDialog.Builder(context)
                .setTitle(title)
                .setMessage(message)
                .setCancelable(false)
                .setPositiveButton(android.R.string.ok, lis)
                .create()
                .show();
    }

    public void showLoading(boolean b) {
        if (progressBar != null)
            progressBar.setVisibility(b ? View.VISIBLE : View.GONE);
    }
}
