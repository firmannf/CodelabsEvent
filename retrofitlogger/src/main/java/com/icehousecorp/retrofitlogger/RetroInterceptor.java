package com.icehousecorp.retrofitlogger;

import android.util.Log;

import com.google.gson.Gson;
import com.icehousecorp.retrofitlogger.tools.Preferences;

import java.io.IOException;

import io.reactivex.Flowable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by mexanjuadha on 12/7/16.
 */

public class RetroInterceptor implements Interceptor {

    private static final String TAG = RetroInterceptor.class.getSimpleName();

    private Response response;

    private ResponseInfo responseInfo;

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        response = chain.proceed(request);


        responseInfo = createResponseInfo(response);
//        saveResponseToPreferences();
        return response;
    }

    private ResponseInfo createResponseInfo(Response response) {
        ResponseInfo responseInfo = new ResponseInfo();
        try {
            responseInfo.setHeaders(response.headers());
            responseInfo.setRequestTime(response.sentRequestAtMillis());
            responseInfo.setResponseTime(response.receivedResponseAtMillis());
            responseInfo.setStatusCode(response.code());
            responseInfo.setMethod(response.request().method());
            responseInfo.setBody(response.body().string());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e){
            e.printStackTrace();
        }

        return responseInfo;
    }

    private void saveResponseToPreferences() {
        if (responseInfo != null) {
            Preferences.getPreference().putString("tempResponse", new Gson().toJson(responseInfo));
        }
    }

}
