package com.sniper.music;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import java.io.InputStream;

public class ResourceToStreamParser {

    @Nullable
    public InputStream fetch(@NonNull String fileNameWithPath) {
        return getClass().getResourceAsStream(fileNameWithPath);
    }

}
