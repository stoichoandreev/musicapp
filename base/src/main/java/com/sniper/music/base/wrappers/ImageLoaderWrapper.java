package com.sniper.music.base.wrappers;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.sniper.music.base.R;
import com.squareup.picasso.Picasso;

public class ImageLoaderWrapper {

    public static void loadImageFromUrl(@NonNull ImageView imageView, @Nullable String url) {
        if (url != null && url.trim().length() > 2) {
            Picasso.with(imageView.getContext())
                    .load(url)
                    .placeholder(R.drawable.ic_place_holder)
                    .error(R.drawable.ic_place_holder)
                    .into(imageView);
        }
    }
}
