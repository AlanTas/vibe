package com.vibe.bibliotecagothan.apis;

import com.vibe.bibliotecagothan.Constantes;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ClienteApi {
    private static Retrofit retrofit;
    public static Retrofit getRetrofitInstance() {
            retrofit = new retrofit2.Retrofit.Builder()
                    .baseUrl(Constantes.Base_url)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        return retrofit;
    }
}
