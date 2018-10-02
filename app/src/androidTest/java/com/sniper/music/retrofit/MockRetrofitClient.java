package com.sniper.music.retrofit;

import android.support.annotation.NonNull;
import android.support.test.InstrumentationRegistry;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.sniper.music.api.RetrofitClient;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.DateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.OkHttpClient;
import okhttp3.mockwebserver.Dispatcher;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import okhttp3.mockwebserver.RecordedRequest;
import okhttp3.mockwebserver.SocketPolicy;
import retrofit2.Converter;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;


public class MockRetrofitClient implements RetrofitClient {

    public static final String POST = "POST";
    public static final String GET = "GET";
    private MockWebServer server;
    private Map<String, MockRetrofitResponse> responseMap = new HashMap<>();
    private Retrofit retrofit;

    public MockRetrofitClient(Converter.Factory converterFactory, OkHttpClient okHttpClient) {
        if(converterFactory == null) {
            converterFactory = getDefaultConverterFactory();
        }
        this.server = new MockWebServer();
        retrofit = new Retrofit.Builder()
                .baseUrl(server.url("/"))
                .addConverterFactory(converterFactory)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(okHttpClient)
                .validateEagerly(true)
                .build();

        server.setDispatcher(new MockDispatcher());
    }

    @NonNull
    private Converter.Factory getDefaultConverterFactory() {
        final Gson gson = new GsonBuilder()
                .enableComplexMapKeySerialization()
                .serializeNulls()
                .setDateFormat(DateFormat.LONG)
                .setFieldNamingPolicy(FieldNamingPolicy.UPPER_CAMEL_CASE)
                .setPrettyPrinting()
                .setVersion(1.0)
                .create();
        return GsonConverterFactory.create(gson);
    }

    public void setup() {
        try {
            server.start();
        } catch (IOException | IllegalStateException e) {
            System.out.println("Error starting mocked web server " + e.getMessage());
        }
    }

    public void tearDown() {
        try {
            server.shutdown();
        } catch (IOException | IllegalStateException e) {
            System.out.println("Error starting mocked web server " + e.getMessage());
        }
    }

    public void addResponses(List<MockRetrofitResponse> responses) {
        for (MockRetrofitResponse response : responses) {
            addResponse(response);
        }
    }

    public void addResponse(MockRetrofitResponse response) {
        if (POST.equals(response.getMethod())) {
            responseMap.put(response.getPath() + response.getStringifiedPostRequest(), response);
        } else {
            responseMap.put(response.getPath(), response);
        }
    }

    public void clearResponseMap() {
        responseMap.clear();
    }

    @Override
    public <T> T api(Class<T> service) {
        return retrofit.create(service);
    }

    public class MockDispatcher extends Dispatcher {

        @Override
        public MockResponse dispatch(RecordedRequest request) {
            String path;
            if (POST.equals(request.getMethod())) {
                path = request.getPath() + request.getBody().readUtf8();
            } else {
                path = request.getPath();
            }
            if (path.startsWith("/")){
                path = path.substring(1, path.length());
            }
            MockRetrofitResponse response = responseMap.get(path);

            if (response == null) {
                try {
                    URL url = new URL(path);
                    path = url.getAuthority();
                } catch (MalformedURLException e) {
                    System.out.println("Error processing path " + path);
                }
            }

            if (response != null) {
                final MockResponse mockResponse = new MockResponse();
                int responseCode = response.getResponseCode();
                if (responseCode != 0) {
                    mockResponse.setResponseCode(responseCode);
                }

                final SocketPolicy socketPolicy = response.getSocketPolicy();
                if (socketPolicy != null) {
                    mockResponse.setSocketPolicy(socketPolicy);
                }

                final MockRetrofitResponseThrottle responseThrottle = response.getResponseThrottle();
                if (responseThrottle != null) {
                    mockResponse.throttleBody(responseThrottle.getThrottleBytesPerPeriod(),
                            responseThrottle.getThrottlePeriodAmount(),
                            responseThrottle.getThrottlePeriodUnit());
                }

                try {
                    final String responseBody = RestServiceResourceParser.getJsonStringFromFile(InstrumentationRegistry.getInstrumentation().getContext(), response.getResponseFilePath());
                    mockResponse.setBody(responseBody);
                    System.out.println("Request body set");
                } catch (Exception e) {
                    System.out.println("Problem with io : " + e.getMessage() + " " + response.getResponseFilePath());
                    e.printStackTrace();
                }

                return mockResponse;
            } else {
                System.out.println("Unmapped request - " + path);
                throw new RuntimeException("Unmapped request - " + path);
            }
        }
    }
}
