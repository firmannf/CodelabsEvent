package com.codelabs.codelabsevent.network.handler;


import retrofit2.Response;

/**
 * Created by mexanjuadha on 1/25/16.
 */
public abstract class NetworkResponseHandler extends NetworkHandler {

    @Override
    public void onFailedToProcessRequest(Response response) {
        super.onFailedToProcessRequest(response);
        consumeStatusCode(response.code());

    }

    protected abstract void consumeStatusCode(int errorCode);
}
