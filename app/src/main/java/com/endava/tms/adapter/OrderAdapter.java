package com.endava.tms.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.endava.tms.R;
import com.endava.tms.model.Order;
import com.endava.tms.viewholder.OrderViewHolder;

import java.util.List;

public class OrderAdapter extends RecyclerView.Adapter<OrderViewHolder> {
    public List<Order> orderList;
    Context context;

    public OrderAdapter(Context context,List<Order> orderList) {
        this.orderList = orderList;
        this.context = context;
        Log.d("debug", orderList.toString());
    }

    @NonNull
    @Override
    public OrderViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new OrderViewHolder(LayoutInflater.from(context).inflate(R.layout.orders_view, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull OrderViewHolder holder, int position) {
        holder.eventName.setText(orderList.get(position).getEventName());
        holder.nrTickets.setText(orderList.get(position).getNrTickets());
        holder.price.setText(orderList.get(position).getPrice());
    }

    @Override
    public int getItemCount() {
        return 0;
    }
}
