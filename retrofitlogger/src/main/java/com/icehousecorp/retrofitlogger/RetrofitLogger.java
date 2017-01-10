package com.icehousecorp.retrofitlogger;

import android.content.Context;
import android.util.Log;

import com.icehousecorp.retrofitlogger.tools.Preferences;

import io.reactivex.Flowable;
import io.reactivex.Observable;
import io.reactivex.subscribers.DefaultSubscriber;

/**
 * Created by mexanjuadha on 12/28/16.
 */

public class RetrofitLogger {

    private static final String TAG = RetrofitLogger.class.getSimpleName();

    private static RetrofitLogger instance;


    public RetrofitLogger() {
    }

    public static RetrofitLogger getInstance() {
        if (instance == null) {
            synchronized (RetrofitLogger.class) {
                if (instance == null) {
                    instance = new RetrofitLogger();
                }
            }
        }
        return instance;
    }

    public void init(Context context) {
        Preferences.setPreference(context, "pref");
    }

    public void getResponse(){
//        String respon = Preferences.getPreference().getString("a");
//        Flowable<String> stringFlowable = Flowable.just(respon);
//
//        stringFlowable.subscribe(new DefaultSubscriber<String>() {
//            @Override
//            public void onNext(String s) {
//                Log.e(TAG, "onNext: " + s);
//            }
//
//            @Override
//            public void onError(Throwable t) {
//
//            }
//
//            @Override
//            public void onComplete() {
//
//            }
//        });
    }



}
