package com.codelabs.codelabsevent.network.handler;


import android.content.Context;

import retrofit2.Response;

/**
 * Created by mexanjuadha on 1/25/16.
 */
public class NetworkHandler implements GeneralNetworkHandler {

    Context context;

    public NetworkHandler() {

    }

    @Override
    public void onNoInternetConnection() {
    }

    @Override
    public void onNetworkProblem() {
    }

    @Override
    public void onFailedToProcessRequest(Response response) {

    }
}
