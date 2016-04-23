package com.codelabs.codelabsevent.base;

import com.codelabs.codelabsevent.network.CodelabsNetworkManager;

import android.content.Context;

/**
 * Created by mexanjuadha on 4/23/16.
 */
public class BaseInteractor {

    CodelabsNetworkManager codelabsNetworkManager;

    public BaseInteractor(Context context) {
        codelabsNetworkManager = new CodelabsNetworkManager(context);
    }

    public CodelabsNetworkManager getCodelabsNetworkManager() {
        return codelabsNetworkManager;
    }
}
