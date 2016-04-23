package com.codelabs.codelabsevent.event;

import com.google.gson.Gson;

import com.codelabs.codelabsevent.R;
import com.codelabs.codelabsevent.network.model.Event;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by mexanjuadha on 4/22/16.
 */
public class EventActivity extends AppCompatActivity implements EventContract.View {

    private static final String TAG = EventActivity.class.getSimpleName();

    @Bind(R.id.recycle_country_picker)
    RecyclerView recyclerView;

    private EventContract.Presenter presenter;

    private CountryAdapter countryAdapter;

    private LinearLayoutManager linearLayoutManager;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.country_picker_activity);
        ButterKnife.bind(this);
        initPresenter();
        presenter.getDataEvent();
        configRecycleView();
    }

    private void initPresenter() {
        presenter = new EventPresenter.Builder().view(this).context(this).build();
    }

    @Override
    public void showEventDatatoList(List<Event> events) {
        Log.d(TAG, "showEventDatatoList: " + events.size());
        countryAdapter = new CountryAdapter(this, events);
        recyclerView.swapAdapter(countryAdapter, true);
    }

    private void configRecycleView() {
        linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
    }

}
