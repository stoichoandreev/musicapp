package com.sniper.music.base.adapter;

import android.support.annotation.CallSuper;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

public abstract class BaseAdapterDelegate<T, VH extends BaseViewHolder> {

    @NonNull
    public abstract RecyclerView.ViewHolder createViewHolder(ViewGroup parent);

    @CallSuper
    public void bindViewHolder(VH holder,
                               T model,
                               int position,
                               OnItemClickListener<T> listener) {
        //unused
    }
}
