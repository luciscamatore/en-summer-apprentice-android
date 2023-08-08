package com.endava.tms.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.endava.tms.R;
import com.endava.tms.model.Order;
import com.endava.tms.popup.DeleteOrderPopUp;
import com.endava.tms.popup.ModifyOrderPopUp;
import com.endava.tms.viewholder.OrderViewHolder;

import java.util.List;
import java.util.Locale;

public class OrderAdapter extends RecyclerView.Adapter<OrderViewHolder> {
    public List<Order> orderList;
    Context context;

    Integer orderID = 0;
    public OrderAdapter(Context context,List<Order> orderList) {
        this.orderList = orderList;
        this.context = context;
    }

    @NonNull
    @Override
    public OrderViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new OrderViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.orders_view, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull OrderViewHolder holder, int position) {
        holder.orderEventName.setText(orderList.get(position).getOrderEventName());
        holder.nrTickets.setText(String.format(Locale.ENGLISH, "%d", orderList.get(position).getNrTickets()));
        holder.price.setText(String.format(Locale.ENGLISH, "%d", orderList.get(position).getPrice()));

        holder.deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                orderID = orderList.get(position).getOrderID();
                popUpDelete();
            }
        });

        holder.modifyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                orderID = orderList.get(position).getOrderID();
                popUpModify();
            }
        });
    }

    @Override
    public int getItemCount() {
        return orderList.size();
    }



    public void popUpDelete(){
        DeleteOrderPopUp deleteOrderPopUp = new DeleteOrderPopUp(orderID,orderList,this);
        deleteOrderPopUp.show(((AppCompatActivity)context).getSupportFragmentManager(), "Dialog");
    }

    public void popUpModify(){
        ModifyOrderPopUp modifyOrderPopUp = new ModifyOrderPopUp(orderID, orderList,this);
        modifyOrderPopUp.show(((AppCompatActivity)context).getSupportFragmentManager(), "Dialog");
    }

}
