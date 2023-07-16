package com.example.lyrical;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitServiceGenerator {

    private final static String BASE_API_URL = "https://api.lyrics.ovh/v1/";
    private static Retrofit retrofit;
    private static final Gson gson = new GsonBuilder().create();

    private static final OkHttpClient.Builder okHttpClientBuilder = new OkHttpClient.Builder();
    private static final OkHttpClient okHttpClient = okHttpClientBuilder.build();

    public static <T> T createRetrofitService(Class<T> serviceClass) {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .client(okHttpClient)
                    .baseUrl(BASE_API_URL)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .build();
        }

        return retrofit.create(serviceClass);
    }
}
