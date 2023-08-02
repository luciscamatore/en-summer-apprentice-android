package com.endava.tms.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.endava.tms.R;
import com.endava.tms.model.Event;
import com.endava.tms.viewholder.EventViewHolder;

import java.util.ArrayList;
import java.util.List;

public class EventAdapter extends RecyclerView.Adapter<EventViewHolder> {
    Context context;
    List<Event> events;
    List<Event> eventsFull;

    public EventAdapter(Context context, List<Event> events) {
        this.context = context;
        this.events = events;
        eventsFull = new ArrayList<>(events);
    }
    public EventAdapter(List<Event> events){
        this.events = events;
        eventsFull = new ArrayList<>(events);
    }
    @NonNull
    @Override
    public EventViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new EventViewHolder(LayoutInflater.from(context).inflate(R.layout.event_view,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull EventViewHolder holder, int position) {
        holder.eventNameView.setText(events.get(position).getEventName());
        holder.eventDescriptionView.setText(events.get(position).getEventDescription());
        holder.venueLocationView.setText(events.get(position).getVenueLocation());
        holder.eventTypeView.setText(events.get(position).getEventType());
        holder.startDateView.setText(events.get(position).getStartDate().toString());
        holder.endDateView.setText(events.get(position).getEndDate().toString());
        holder.eventImage.setImageResource(events.get(position).getEventImage());
        EventViewHolder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context,"msj",Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return events.size();
    }

    public Filter getFilter(){
        return eventFilter;
    }
    private Filter eventFilter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence charSequence) {
            List<Event> filteredEvents = new ArrayList<>();

            if(charSequence == null || charSequence.length() == 0)
                filteredEvents.addAll(eventsFull);
            else{
                String filterPattern = charSequence.toString().toLowerCase().trim();

                for(Event ev : eventsFull)
                    if(ev.getEventName().toLowerCase().contains(filterPattern))
                        filteredEvents.add(ev);
            }
            FilterResults results = new FilterResults();
            results.values = filteredEvents;
            return results;
        }

        @SuppressLint("NotifyDataSetChanged")
        @Override
        protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
            events.clear();
            events.addAll((List)filterResults.values);
            notifyDataSetChanged();
        }
    };

}
