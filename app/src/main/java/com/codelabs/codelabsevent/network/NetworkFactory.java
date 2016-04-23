package com.codelabs.codelabsevent.network;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import android.content.Context;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

import static okhttp3.logging.HttpLoggingInterceptor.*;

/**
 * Created by mexanjuadha on 3/7/16.
 */
public class NetworkFactory {

    private static final String TAG = NetworkFactory.class.getSimpleName();

    public static <T> T createRestAdapter(Gson gson, String baseUrl, Class<T> restClass) {

        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(Level.BODY);

        OkHttpClient client = new OkHttpClient.Builder()
            .addInterceptor(logging)
            .build();

        Retrofit.Builder restBuilder = new Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
            .client(client);

        return restBuilder.build().create(restClass);

    }
}
