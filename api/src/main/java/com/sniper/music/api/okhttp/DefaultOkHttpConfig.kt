package com.sniper.music.api.okhttp

class DefaultOkHttpConfig : OKHttpConfig {

    override val connectTimeout: Long
        get() = CONNECTION_TIMEOUT

    override val readTimeout: Long
        get() = READ_TIMEOUT

    override val writeTimeout: Long
        get() = WRITE_TIMEOUT

    companion object {

        private const val CONNECTION_TIMEOUT: Long = 30
        private const val READ_TIMEOUT: Long = 30
        private const val WRITE_TIMEOUT: Long = 30
    }
}
