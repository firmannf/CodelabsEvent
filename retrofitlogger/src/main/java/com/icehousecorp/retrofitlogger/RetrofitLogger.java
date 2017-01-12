package com.icehousecorp.retrofitlogger;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.util.Log;
import android.widget.Toast;

import com.icehousecorp.retrofitlogger.tools.Preferences;
import com.icehousecorp.retrofitlogger.tools.Shaker;

import io.reactivex.Flowable;
import io.reactivex.subscribers.DefaultSubscriber;

import static android.content.Context.SENSOR_SERVICE;

/**
 * Created by mexanjuadha on 12/28/16.
 */

public class RetrofitLogger {

    private static final String TAG = RetrofitLogger.class.getSimpleName();

    private static RetrofitLogger instance;

    private Context context;

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
        this.context = context;
        Preferences.setPreference(context, "pref");
        addListenerSensor();
    }


    private void addListenerSensor() {
        new Shaker(context, 2.5d, 0, new Shaker.Callback() {
            @Override
            public void shakingStarted() {
//                getResponse();
            }

            @Override
            public void shakingStopped() {

            }
        });
    }

    public void getResponse() {
        String respon = Preferences.getPreference().getString("tempResponse");
        Flowable<String> stringFlowable = Flowable.just(respon);

        stringFlowable.subscribe(new DefaultSubscriber<String>() {
            @Override
            public void onNext(String s) {
                Log.e(TAG, "Response : " + s);
            }

            @Override
            public void onError(Throwable t) {

            }

            @Override
            public void onComplete() {

            }
        });
    }


}
