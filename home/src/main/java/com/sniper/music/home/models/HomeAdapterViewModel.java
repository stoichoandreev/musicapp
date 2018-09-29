package com.sniper.music.home.models;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.sniper.music.home.adapter.HomeAdapterType;

public class HomeAdapterViewModel {

    @NonNull
    private String mbID;
    @Nullable
    private String name;
    @Nullable
    private String url;
    @Nullable
    private String imageUrl;

    @NonNull
    @HomeAdapterType.Values
    private int itemType;

    private HomeAdapterViewModel() {
        //no direct initialization
    }

    @NonNull
    public String getMbID() {
        return mbID;
    }

    @Nullable
    public String getName() {
        return name;
    }

    @Nullable
    public String getUrl() {
        return url;
    }

    @Nullable
    public String getImageUrl() {
        return imageUrl;
    }

    @NonNull
    @HomeAdapterType.Values
    public int getItemType() {
        return itemType;
    }

    public static class Builder {

        private String mbID;
        private String name;
        private String url;
        private String imageUrl;
        private int itemType;

        public Builder(@NonNull String mbID) {
            this.mbID = mbID;
        }

        public Builder name(@Nullable String name) {
            this.name = name;
            return this;
        }

        public Builder url(@Nullable String url) {
            this.url = url;
            return this;
        }

        public Builder imageUrl(@Nullable String imageUrl) {
            this.imageUrl = imageUrl;
            return this;
        }

        public Builder itemType(@HomeAdapterType.Values int itemType) {
            this.itemType = itemType;
            return this;
        }

        public HomeAdapterViewModel build() {
            final HomeAdapterViewModel viewModelData = new HomeAdapterViewModel();
            viewModelData.mbID = this.mbID;
            viewModelData.name = this.name;
            viewModelData.url = this.url;
            viewModelData.imageUrl = this.imageUrl;
            viewModelData.itemType = this.itemType;

            return viewModelData;
        }
    }
}
