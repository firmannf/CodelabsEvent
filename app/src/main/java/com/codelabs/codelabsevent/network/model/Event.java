package com.codelabs.codelabsevent.network.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by mexanjuadha on 4/22/16.
 */
public class Event {

    @SerializedName("event_id")
    private int eventId;

    private String name;

    @SerializedName("image")
    private String urlImage;

    private String description;

    public Event(int eventId, String name, String urlImage, String description) {
        this.eventId = eventId;
        this.name = name;
        this.urlImage = urlImage;
        this.description = description;
    }

    public int getEventId() {
        return eventId;
    }

    public String getName() {
        return name;
    }

    public String getUrlImage() {
        return urlImage;
    }

    public String getDescription() {
        return description;
    }
}
