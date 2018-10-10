package com.sniper.music.home.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import com.sniper.music.base.adapter.BaseViewHolder;
import com.sniper.music.base.adapter.EmptyDelegate;
import com.sniper.music.base.adapter.OnItemClickListener;
import com.sniper.music.home.models.HomeAdapterViewModel;

import java.util.ArrayList;
import java.util.List;

public class HomeAdapter extends RecyclerView.Adapter<BaseViewHolder> {

    @NonNull
    private ArtistItemDelegate artistItemDelegate = new ArtistItemDelegate();
    @NonNull
    private EmptyDelegate emptyItemDelegate = new EmptyDelegate();
    @NonNull
    private List<HomeAdapterViewModel> adapterItems = new ArrayList<>();
    @NonNull
    private final OnItemClickListener<HomeAdapterViewModel> listener;

    public HomeAdapter(@NonNull OnItemClickListener<HomeAdapterViewModel> listener) {
        this.listener = listener;
    }

    @NonNull
    @Override
    public BaseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        final RecyclerView.ViewHolder holder;
        switch (viewType) {
            case HomeAdapterType.ARTIST:
                holder = artistItemDelegate.createViewHolder(parent);
                break;
            case HomeAdapterType.ALBUM:
                //Todo need album delegate
                holder = emptyItemDelegate.createViewHolder(parent);
                break;
            case HomeAdapterType.TRACK:
                //Todo need track delegate
                holder = emptyItemDelegate.createViewHolder(parent);
                break;
            default:
                holder = emptyItemDelegate.createViewHolder(parent);
        }
        return (BaseViewHolder) holder;
    }

    @Override
    public void onBindViewHolder(@NonNull BaseViewHolder holder, int position) {
        final HomeAdapterViewModel viewModel = adapterItems.get(position);
        final int itemType = getItemViewType(position);
        switch (itemType) {
            case HomeAdapterType.ARTIST:
                artistItemDelegate.bindViewHolder((ArtistItemDelegate.ArtistItemViewHolder) holder, viewModel, position, listener);
                break;
            case HomeAdapterType.ALBUM:
                emptyItemDelegate.bindViewHolder((EmptyDelegate.EmptyViewHolder) holder, viewModel, position, null);
                break;
            case HomeAdapterType.TRACK:
                emptyItemDelegate.bindViewHolder((EmptyDelegate.EmptyViewHolder) holder, viewModel, position, null);
                break;
            case HomeAdapterType.EMPTY:
            default:
                emptyItemDelegate.bindViewHolder((EmptyDelegate.EmptyViewHolder) holder, viewModel, position, null);
                break;
        }
    }

    @Override
    @HomeAdapterType.Values
    public int getItemViewType(int position) {
        return adapterItems.get(position).getItemType();
    }

    @Override
    public int getItemCount() {
        return adapterItems.size();
    }

    public void setNewItems(List<HomeAdapterViewModel> newItems) {
        adapterItems.clear();
        adapterItems.addAll(newItems);
        notifyDataSetChanged();
    }

}
