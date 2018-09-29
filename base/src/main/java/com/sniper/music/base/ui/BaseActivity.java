package com.sniper.music.base.ui;


import android.support.annotation.NonNull;
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


    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (isFinishing()) {
            ComponentsManager.get().removeBaseComponent(getComponentKey());
            getPresenter().destroy();
        }
    }

    protected void displayErrors(@NonNull String errorMessage) {
        final View view = findViewById(android.R.id.content);
        if (view != null) {
            Snackbar.make(view, errorMessage, Snackbar.LENGTH_LONG).show();
        } else {
            Toast.makeText(this, errorMessage, Toast.LENGTH_LONG).show();
        }
    }
}
