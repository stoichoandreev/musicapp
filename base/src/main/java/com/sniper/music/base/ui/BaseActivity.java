package com.sniper.music.base.ui;


import android.support.v7.app.AppCompatActivity;

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
}
