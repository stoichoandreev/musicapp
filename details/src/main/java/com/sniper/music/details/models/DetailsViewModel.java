package com.sniper.music.details.models;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

public class DetailsViewModel {

    @NonNull
    private String mbID;
    @Nullable
    private String name;
    @Nullable
    private String url;
    @Nullable
    private String imageUrl;
    @Nullable
    private String summary;
    @Nullable
    private String listeners;
    @Nullable
    private String playCount;
    @Nullable
    private String[] tags;


    private DetailsViewModel() {
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
    public String getListeners() {
        return listeners;
    }

    @NonNull
    public String getPlayCount() {
        return playCount;
    }

    @Nullable
    public String getSummary() {
        return summary;
    }

    @Nullable
    public String[] getTags() {
        return tags;
    }

    public static class Builder {

        private String mbID;
        private String name;
        private String url;
        private String imageUrl;
        private String listeners;
        private String playCount;
        private String summary;
        private String[] tags;

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

        public Builder listeners(String listeners) {
            this.listeners = listeners;
            return this;
        }

        public Builder summary(String summary) {
            this.summary = summary;
            return this;
        }

        public Builder playCount(String playCount) {
            this.playCount = playCount;
            return this;
        }

        public Builder tags(String[] tags) {
            this.tags = tags;
            return this;
        }

        public DetailsViewModel build() {
            final DetailsViewModel viewModelData = new DetailsViewModel();
            viewModelData.mbID = this.mbID;
            viewModelData.name = this.name;
            viewModelData.url = this.url;
            viewModelData.imageUrl = this.imageUrl;
            viewModelData.listeners = this.listeners;
            viewModelData.playCount = this.playCount;
            viewModelData.summary = this.summary;
            viewModelData.tags = this.tags;

            return viewModelData;
        }
    }
}
