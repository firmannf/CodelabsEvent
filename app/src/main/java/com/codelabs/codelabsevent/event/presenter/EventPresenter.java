package com.codelabs.codelabsevent.event.presenter;

import com.codelabs.codelabsevent.event.EventContract;
import com.codelabs.codelabsevent.event.interactor.EventInteractor;
import com.codelabs.codelabsevent.network.handler.NetworkHandler;
import com.codelabs.codelabsevent.network.model.Event;

import android.content.Context;

import java.util.List;

/**
 * Created by mexanjuadha on 4/22/16.
 */
public class EventPresenter implements EventContract.Presenter {

    private Context context;

    private EventContract.View view;

    private EventContract.Interactor interactor;

    public static class Builder {

        private Context context;

        private EventContract.View view;

        public Builder() {
        }

        public Builder context(Context context) {
            this.context = context;
            return this;
        }

        public Builder view(EventContract.View view) {
            this.view = view;
            return this;
        }

        public EventPresenter build() {
            return new EventPresenter(this);
        }
    }

    private EventPresenter(Builder builder) {
        context = builder.context;
        view = builder.view;
        initInteractor();
    }

    private void initInteractor() {
        interactor = new EventInteractor
            .Builder()
            .context(context)
            .presenter(this)
            .build();
    }

    @Override
    public void getDataEvent() {
        interactor.requestDataEventFromNetwork(new NetworkHandler());
    }

    @Override
    public void populateDataEvent(List<Event> events) {
        view.showEventDatatoList(events);
    }
}
