package com.codelabs.codelabsevent.network;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import com.codelabs.codelabsevent.network.handler.GeneralNetworkHandler;

import android.content.Context;

import java.net.UnknownHostException;

import retrofit2.adapter.rxjava.HttpException;
import rx.Observable;
import rx.functions.Action1;

/**
 * Created by mexanjuadha on 3/7/16.
 */
public abstract class BaseNetwork<T> {

    private static final String TAG = BaseNetwork.class.getSimpleName();

    private T networkServices;

    private Context context;


    public BaseNetwork(Context context) {
        this.context = context;

        Gson gson = gsonHandler(new GsonBuilder().setPrettyPrinting()).create();
        networkServices = NetworkFactory.createRestAdapter(gson, getBaseUrl(), getRestClass());
    }

    protected <T> Observable<T> addInterceptor(final Observable<T> originObservable,
        final GeneralNetworkHandler generalNetworkHandler) {
        return originObservable
            .doOnError(new Action1<Throwable>() {
                @Override
                public void call(Throwable throwable) {
                    onRetrofitErrorNetwork(throwable, generalNetworkHandler);
                }
            });
    }

    private void onRetrofitErrorNetwork(Throwable throwable,
        GeneralNetworkHandler generalNetworkHandler) {
        if (throwable instanceof UnknownHostException) {
            generalNetworkHandler.onNoInternetConnection();
        } else if (throwable instanceof HttpException) {
            HttpException httpResponse = (HttpException) throwable;
            generalNetworkHandler.onFailedToProcessRequest(httpResponse.response());
        }
    }

    protected GsonBuilder gsonHandler(GsonBuilder builder) {
        return builder;
    }

    public T getNetworkServices() {
        return networkServices;
    }

    protected abstract String getBaseUrl();

    protected abstract Class<T> getRestClass();

}
