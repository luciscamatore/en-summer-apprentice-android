package com.endava.tms.viewholder;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.endava.tms.R;

public class OrderViewHolder extends RecyclerView.ViewHolder {

    public TextView orderEventName, nrTickets, price;

    public static CardView cardView;

    public Button deleteButton, modifyButton;

    public OrderViewHolder(@NonNull View itemView) {
        super(itemView);

        orderEventName = itemView.findViewById(R.id.orderEventName);
        nrTickets = itemView.findViewById(R.id.nrTickets);
        price = itemView.findViewById(R.id.price);

        cardView = itemView.findViewById(R.id.cardViewOrderID);

        deleteButton = itemView.findViewById(R.id.deleteButton);
        modifyButton = itemView.findViewById(R.id.modifyButton);
    }
}
