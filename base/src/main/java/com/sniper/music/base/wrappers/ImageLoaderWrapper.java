package com.sniper.music.base.wrappers;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

public class ImageLoaderWrapper {

    public static void loadImageFromUrl(@NonNull ImageView imageView, @Nullable String url) {
        if (url != null) {
            Picasso.get().load(url).into(imageView);
        }
    }
}
