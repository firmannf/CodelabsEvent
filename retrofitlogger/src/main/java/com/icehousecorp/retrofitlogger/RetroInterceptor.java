package com.icehousecorp.retrofitlogger;

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

    private Chain chain;

    private Flowable<Response> flowableResponse;

    @Override
    public Response intercept(Chain chain) throws IOException {
        this.chain = chain;
        final Request request = chain.request();
        flowableResponse = getChainRequest(request);
        saveResponseToPreferences();

        return chain.proceed(request);
    }

    private Flowable<Response> getChainRequest(Request request) {
        Flowable flowable;
        try {
            flowable = Flowable.just(chain.proceed(request));
        } catch (IOException e) {
            flowable = Flowable.error(e);
        }
        return flowable;
    }

    public Flowable<ResponseInfo> getResponseInfo() {
        return flowableResponse.map(new Function<Response, ResponseInfo>() {
            @Override
            public ResponseInfo apply(Response response) throws Exception {
                return createResponseInfo(response);
            }
        });
    }

    private ResponseInfo createResponseInfo(Response response) throws IOException {
        ResponseInfo responseInfo = new ResponseInfo();
        responseInfo.setBody(response.body().string());
        responseInfo.setHeaders(response.headers());
        responseInfo.setRequestTime(response.sentRequestAtMillis());
        responseInfo.setResponseTime(response.receivedResponseAtMillis());
        responseInfo.setStatusCode(response.code());
        responseInfo.setMethod(response.request().method());
        return responseInfo;
    }

    private void saveResponseToPreferences() {
        getResponseInfo().doOnNext(new Consumer<ResponseInfo>() {
            @Override
            public void accept(ResponseInfo responseInfo) throws Exception {
                Preferences.getPreference().putString("a", new Gson().toJson(responseInfo));
            }
        });

    }

}
