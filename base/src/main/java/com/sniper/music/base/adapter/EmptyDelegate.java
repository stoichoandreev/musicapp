package com.sniper.music.base.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sniper.music.base.R;

public class EmptyDelegate extends BaseAdapterDelegate<Object, EmptyDelegate.EmptyViewHolder> {

    @NonNull
    @Override
    public RecyclerView.ViewHolder createViewHolder(@NonNull ViewGroup parent) {
        final LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        return new EmptyViewHolder(inflater.inflate(R.layout.item_empty_view, parent, false));
    }

    public static class EmptyViewHolder extends BaseViewHolder {
        EmptyViewHolder(View view) {
            super(view);
        }
    }

}
