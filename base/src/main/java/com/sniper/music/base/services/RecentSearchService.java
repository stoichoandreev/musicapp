package com.sniper.music.base.services;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

public interface RecentSearchService {
    void storeRecentSearch(@NonNull Context context, @Nullable String query);
}
