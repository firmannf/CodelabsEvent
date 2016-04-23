package com.codelabs.codelabsevent.event;

import com.bumptech.glide.Glide;
import com.codelabs.codelabsevent.R;
import com.codelabs.codelabsevent.network.model.Event;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by mexanjuadha on 1/27/16.
 */
public class CountryAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final String TAG = CountryAdapter.class.getSimpleName();

    private Context context;

    private List<Event> events;

    public CountryAdapter(Context context, List<Event> events) {
        this.context = context;
        this.events = events;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(context).inflate(
            R.layout.item_event, parent, false);
        return new CountryViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ((CountryViewHolder) holder).bindCountryData(events.get(position));
    }

    @Override
    public int getItemCount() {
        return events.size();
    }

    class CountryViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {


        @Bind(R.id.event_image)
        ImageView eventImage;

        @Bind(R.id.title_event)
        TextView titleEvent;

        @Bind(R.id.desc_event)
        TextView description;

        public CountryViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(this);
        }

        public void bindCountryData(Event event) {
            titleEvent.setText(event.getName());
            Glide.with(context).load(event.getUrlImage()).into(eventImage);
            description.setText(event.getDescription());
        }

        @Override
        public void onClick(View v) {
        }
    }
}
