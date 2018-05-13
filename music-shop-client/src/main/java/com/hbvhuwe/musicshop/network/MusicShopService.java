package com.hbvhuwe.musicshop.network;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MusicShopService {
    private static final OkHttpClient.Builder httpClientBuilder = new OkHttpClient.Builder();
    private static MusicShopApi api = null;

    private static void createClient() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://localhost:8080/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(httpClientBuilder.build())
                .build();
        api = retrofit.create(MusicShopApi.class);
    }

    public static MusicShopApi getApi() {
        if (api == null) {
            createClient();
        }
        return api;
    }
}
