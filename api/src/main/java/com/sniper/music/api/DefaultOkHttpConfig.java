package com.sniper.music.api;

public class DefaultOkHttpConfig implements OKHttpConfig {

    private static final long CONNECTION_TIMEOUT = 30;
    private static final long READ_TIMEOUT = 30;
    private static final long WRITE_TIMEOUT = 30;

    @Override
    public long getConnectTimeout() {
        return CONNECTION_TIMEOUT;
    }

    @Override
    public long getReadTimeout() {
        return READ_TIMEOUT;
    }

    @Override
    public long getWriteTimeout() {
        return WRITE_TIMEOUT;
    }
}
