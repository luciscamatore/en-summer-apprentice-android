package com.endava.tms.viewholder;

import android.content.Intent;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.endava.tms.R;

public class EventViewHolder extends RecyclerView.ViewHolder {
    public TextView eventNameView, eventDescriptionView, venueLocationView, eventTypeView, startDateView, endDateView;
    public ImageView eventImage;

    public static CardView cardView;

    public EventViewHolder(@NonNull View itemView) {
        super(itemView);

        eventNameView = itemView.findViewById(R.id.eventName);
        eventDescriptionView = itemView.findViewById(R.id.eventDescription);
        venueLocationView  = itemView.findViewById(R.id.venueLocation);
        eventTypeView = itemView.findViewById(R.id.eventType);
        startDateView = itemView.findViewById(R.id.startDate);
        endDateView = itemView.findViewById(R.id.endDate);
        eventImage = itemView.findViewById(R.id.imageView);
        cardView=itemView.findViewById(R.id.cardViewID);

    }
}
