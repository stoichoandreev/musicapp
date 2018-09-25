package com.sniper.music.api;


public interface OKHttpConfig {

    long getConnectTimeout();

    long getReadTimeout();

    long getWriteTimeout();
}