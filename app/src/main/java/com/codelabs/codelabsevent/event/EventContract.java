package com.codelabs.codelabsevent.event;

import com.codelabs.codelabsevent.network.handler.GeneralNetworkHandler;
import com.codelabs.codelabsevent.network.model.Event;

import java.util.List;

/**
 * Created by mexanjuadha on 4/22/16.
 */
public interface EventContract {

    interface View {

        void showEventDatatoList(List<Event> events);
    }

    interface Presenter {

        void getDataEvent();

        void populateDataEvent(List<Event> events);
    }

    interface Interactor {

        void requestDataEventFromNetwork(GeneralNetworkHandler generalNetworkHandler);
    }

}
