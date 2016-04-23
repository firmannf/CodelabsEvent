package com.codelabs.codelabsevent.event.view;

import com.codelabs.codelabsevent.R;
import com.codelabs.codelabsevent.event.EventContract;
import com.codelabs.codelabsevent.event.presenter.EventPresenter;
import com.codelabs.codelabsevent.network.model.Event;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by mexanjuadha on 4/22/16.
 */
public class EventActivity extends AppCompatActivity implements EventContract.View {

    private static final String TAG = EventActivity.class.getSimpleName();

    @Nullable
    @Bind(R.id.toolbar)
    Toolbar toolbar;

    @Nullable
    @Bind(R.id.title_toolbar)
    TextView titleBar;

    @Bind(R.id.recycle_country_picker)
    RecyclerView recyclerView;

    private EventContract.Presenter presenter;

    private EventAdapter countryAdapter;

    private LinearLayoutManager linearLayoutManager;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.event_activity);
        ButterKnife.bind(this);
        configToolbar();
        initPresenter();
        presenter.getDataEvent();
        configRecycleView();
    }

    public void configToolbar() {
        if (toolbar != null) {
            setTitleBarToolbar(getString(R.string.event_sample));

            setSupportActionBar(toolbar);
            getSupportActionBar().setHomeButtonEnabled(true);
            getSupportActionBar().setDisplayShowTitleEnabled(false);
        }
    }

    protected void setTitleBarToolbar(String title) {
        if (toolbar != null && title != null) {
            titleBar.setText(title);
        }
    }


    private void initPresenter() {
        presenter = new EventPresenter.Builder().view(this).context(this).build();
    }

    @Override
    public void showEventDatatoList(List<Event> events) {
        countryAdapter = new EventAdapter(this, events);
        recyclerView.swapAdapter(countryAdapter, true);
    }

    private void configRecycleView() {
        linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
    }

}
