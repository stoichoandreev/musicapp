package com.sniper.music.home.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.sniper.music.base.adapter.BaseAdapterDelegate;
import com.sniper.music.base.adapter.BaseViewHolder;
import com.sniper.music.base.adapter.OnItemClickListener;
import com.sniper.music.base.wrappers.ImageLoaderWrapper;
import com.sniper.music.home.R;
import com.sniper.music.home.models.HomeAdapterViewModel;

public class ArtistItemDelegate extends BaseAdapterDelegate<HomeAdapterViewModel, ArtistItemDelegate.ArtistItemViewHolder> {

    @NonNull
    @Override
    public RecyclerView.ViewHolder createViewHolder(ViewGroup parent) {
        final LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        return new ArtistItemDelegate.ArtistItemViewHolder(inflater.inflate(R.layout.item_home_list, parent, false));
    }

    @Override
    public void bindViewHolder(ArtistItemViewHolder holder,
                               HomeAdapterViewModel model,
                               int position,
                               OnItemClickListener<HomeAdapterViewModel> listener) {

        ImageLoaderWrapper.loadImageFromUrl(holder.image, model.getImageUrl());

        holder.parent.setOnClickListener(view -> listener.onItemClick(model));
        holder.title.setText(model.getName());
        holder.additionalInformation.setText(model.getAdditionalInformation());

        holder.subTitle.setText(Html.fromHtml(model.getUrl()));
        holder.subTitle.setMovementMethod(LinkMovementMethod.getInstance());

        super.bindViewHolder(holder, model, position, listener);
    }

    static class ArtistItemViewHolder extends BaseViewHolder {
        @NonNull
        View parent;
        @NonNull
        ImageView image;
        @NonNull
        TextView title;
        @NonNull
        TextView subTitle;
        @NonNull
        TextView additionalInformation;

        ArtistItemViewHolder(View view) {
            super(view);
            parent = view;
            image = view.findViewById(R.id.home_item_image_view);
            title = view.findViewById(R.id.home_item_title_view);
            subTitle = view.findViewById(R.id.home_item_sub_title_view);
            additionalInformation = view.findViewById(R.id.home_item_additional_information_view);
        }
    }
}
