package com.codelabs.codelabsevent;

import android.app.Application;

import com.icehousecorp.retrofitlogger.RetrofitLogger;
import com.icehousecorp.retrofitlogger.tools.Preferences;

/**
 * Created by mexanjuadha on 12/28/16.
 */

public class CodelabsApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        RetrofitLogger.getInstance().init(this);
        Preferences.setPreference(this, "pref");
        Preferences.getPreference().putString("tempValue", "123456");

    }
}
