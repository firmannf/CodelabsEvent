package com.codelabs.codelabsevent.network;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.TypeAdapter;
import com.google.gson.TypeAdapterFactory;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

import com.codelabs.codelabsevent.BuildConfig;
import com.codelabs.codelabsevent.network.handler.GeneralNetworkHandler;
import com.codelabs.codelabsevent.network.model.Event;

import android.content.Context;

import java.io.IOException;
import java.util.List;

import rx.Observable;

/**
 * Created by mexanjuadha on 4/22/16.
 */
public class CodelabsNetworkManager extends BaseNetwork<CodelabsNetworkServices> {

    private static final String RESPONSE_OBJECT = "data";

    private static final String MIME_TYPE_IMAGE = "image/png";

    public CodelabsNetworkManager(Context context) {
        super(context);
    }

    @Override
    protected String getBaseUrl() {
        return BuildConfig.SERVER_URL;
    }

    @Override
    protected Class<CodelabsNetworkServices> getRestClass() {
        return CodelabsNetworkServices.class;
    }

    public Observable<List<Event>> requestEvent(GeneralNetworkHandler generalNetworkHandler) {
        return addInterceptor(getNetworkServices().getEvents(), generalNetworkHandler);
    }

    @Override
    protected GsonBuilder gsonHandler(GsonBuilder builder) {
        builder.registerTypeAdapterFactory(new ResponseTypeAdapterFactory());
        return builder;
    }

    private class ResponseTypeAdapterFactory implements TypeAdapterFactory {

        @Override
        public <T> TypeAdapter<T> create(Gson gson, TypeToken<T> type) {
            final TypeAdapter<T> delegate = gson.getDelegateAdapter(this, type);
            final TypeAdapter<JsonElement> elementAdapter = gson.getAdapter(JsonElement.class);

            return new TypeAdapter<T>() {
                @Override
                public void write(JsonWriter out, T value) throws IOException {
                    delegate.write(out, value);
                }

                @Override
                public T read(JsonReader in) throws IOException {

                    JsonElement jsonElement = elementAdapter.read(in);
                    if (jsonElement.isJsonObject()) {
                        JsonObject jsonObject = jsonElement.getAsJsonObject();
                        if (jsonObject.has(RESPONSE_OBJECT) && jsonObject.get(RESPONSE_OBJECT)
                            .isJsonObject()) {
                            jsonElement = jsonObject.get(RESPONSE_OBJECT);
                        } else if (jsonObject.has(RESPONSE_OBJECT) && jsonObject
                            .get(RESPONSE_OBJECT).isJsonArray()) {
                            jsonElement = jsonObject.get(RESPONSE_OBJECT).getAsJsonArray();
                        }
                    }
                    return delegate.fromJsonTree(jsonElement);
                }
            }.nullSafe();
        }
    }
}
