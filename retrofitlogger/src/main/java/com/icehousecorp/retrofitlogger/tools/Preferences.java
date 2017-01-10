package com.icehousecorp.retrofitlogger.tools;

import android.content.Context;

/**
 * Created by budi on 7/11/16.
 */
public class Preferences {

    private static Preferences instance;

    private PreferenceHelper preferenceHelper;

    private Preferences() {
    }

    public static Preferences getInstance() {
        if (instance == null) {
            synchronized (Preferences.class) {
                if (instance == null) {
                    instance = new Preferences();
                }
            }
        }
        return instance;
    }

    private void setPreferenceHelper(Context context, String preferences) {
        if (preferenceHelper == null) {
            preferenceHelper = new PreferenceHelper(context, preferences);
        }
    }

    private PreferenceHelper getPreferenceHelper() {
        return preferenceHelper;
    }

    public static void setPreference(Context context, String preferences) {
        getInstance().setPreferenceHelper(context, preferences);
    }

    public static PreferenceHelper getPreference() {
        return getInstance().getPreferenceHelper();
    }
}
