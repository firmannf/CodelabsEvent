package com.codelabs.codelabsevent.event;

import com.codelabs.codelabsevent.base.BaseInteractor;
import com.codelabs.codelabsevent.network.handler.GeneralNetworkHandler;
import com.codelabs.codelabsevent.network.model.Event;

import android.content.Context;

import java.util.List;

import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/**
 * Created by mexanjuadha on 4/22/16.
 */
public class EventInteractor extends BaseInteractor implements EventContract.Interactor {

    private Context context;

    private EventContract.Presenter presenter;

    public static class Builder {

        private Context context;

        private EventContract.Presenter presenter;

        public Builder() {
        }

        public Builder context(Context context) {
            this.context = context;
            return this;
        }

        public Builder presenter(EventContract.Presenter presenter) {
            this.presenter = presenter;
            return this;
        }

        public EventInteractor build() {
            return new EventInteractor(this);
        }
    }

    public EventInteractor(Builder builder) {
        super(builder.context);
        this.context = builder.context;
        this.presenter = builder.presenter;

    }

    @Override
    public void requestDataEventFromNetwork(GeneralNetworkHandler generalNetworkHandler) {
        getCodelabsNetworkManager().requestEvent(generalNetworkHandler)
            .subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(new Action1<List<Event>>() {
                @Override
                public void call(List<Event> events) {
                    presenter.populateDataEvent(events);
                }
            });
    }
}
