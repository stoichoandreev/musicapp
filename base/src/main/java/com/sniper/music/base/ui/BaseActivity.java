package com.sniper.music.base.ui;


import android.os.Bundle;
import android.support.annotation.CallSuper;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.sniper.music.base.di.BaseComponent;
import com.sniper.music.base.di.ComponentsManager;
import com.sniper.music.base.mvp.Presenter;

public abstract class BaseActivity<P extends Presenter, C extends BaseComponent> extends AppCompatActivity {

    protected abstract C getScreenComponent();
    protected abstract String getComponentKey();
    protected abstract P getPresenter();
    protected boolean wasSavedInstanceState;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        wasSavedInstanceState = savedInstanceState != null;
    }

    @Override
    protected void onResume() {
        super.onResume();
        wasSavedInstanceState = false;
    }

    @CallSuper
    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (isFinishing()) {
            ComponentsManager.get().removeBaseComponent(getComponentKey());
            getPresenter().destroy();
        }
    }

    protected void displayError(@NonNull String errorMessage) {
        final View view = findViewById(android.R.id.content);
        if (view != null) {
            Snackbar.make(view, errorMessage, Snackbar.LENGTH_LONG).show();
        } else {
            Toast.makeText(this, errorMessage, Toast.LENGTH_LONG).show();
        }
    }
}
