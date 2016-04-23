package com.codelabs.codelabsevent.network.handler;


import retrofit2.Response;

/**
 * Created by mexanjuadha on 1/19/16.
 */
public interface GeneralNetworkHandler {

    void onNoInternetConnection();

    void onNetworkProblem();

    void onFailedToProcessRequest(Response response);
}
