package com.codelabs.codelabsevent.network;

import com.codelabs.codelabsevent.network.model.Event;
import com.codelabs.codelabsevent.network.model.User;

import java.util.List;

import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import rx.Observable;

/**
 * Created by mexanjuadha on 4/22/16.
 */
public interface CodelabsNetworkServices {

    @POST("user")
    Observable<User> registerUser(@Body User user);

    @GET("events/sale")
    Observable<List<Event>> getEvents();


}
