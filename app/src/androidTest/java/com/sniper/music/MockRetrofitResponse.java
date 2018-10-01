package com.sniper.music;


import okhttp3.mockwebserver.SocketPolicy;

public class MockRetrofitResponse {
    private String path;
    private String responseFilePath;
    private String method;
    private int responseCode;
    private String stringifiedPostRequest;
    private SocketPolicy socketPolicy;
    private MockRetrofitResponseThrottle responseThrottle;

    public String getPath() {
        return path;
    }

    public String getResponseFilePath() {
        return responseFilePath;
    }

    public String getMethod() {
        return method;
    }

    public int getResponseCode() {
        return responseCode;
    }

    public String getStringifiedPostRequest() {
        return stringifiedPostRequest;
    }

    public SocketPolicy getSocketPolicy() {
        return socketPolicy;
    }

    public MockRetrofitResponseThrottle getResponseThrottle() {
        return responseThrottle;
    }

    public static class Builder {

        private MockRetrofitResponse mr2r = new MockRetrofitResponse();

        public Builder path(String path) {
            mr2r.path = path;
            return this;
        }

        public Builder responseFilePath(String responseFilePath) {
            mr2r.responseFilePath = responseFilePath;
            return this;
        }

        public Builder method(String method) {
            mr2r.method = method;
            return this;
        }

        public Builder responseCode(int responseCode) {
            mr2r.responseCode = responseCode;
            return this;
        }

        public Builder stringifiedPostRequest(String stringifiedPostRequest) {
            mr2r.stringifiedPostRequest = stringifiedPostRequest;
            return this;
        }

        public Builder socketPolicy(SocketPolicy socketPolicy) {
            mr2r.socketPolicy = socketPolicy;
            return this;
        }

        public Builder responseThrottle(MockRetrofitResponseThrottle responseThrottle) {
            mr2r.responseThrottle = responseThrottle;
            return this;
        }

        public MockRetrofitResponse build() {
            return mr2r;
        }
    }
}
