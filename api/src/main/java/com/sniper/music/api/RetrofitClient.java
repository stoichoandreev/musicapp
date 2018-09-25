package com.sniper.music.api;

public interface RetrofitClient {
    <T> T api(Class<T> service);
}
