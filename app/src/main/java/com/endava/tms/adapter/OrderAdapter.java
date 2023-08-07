package com.endava.tms.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.endava.tms.R;
import com.endava.tms.apiinterface.ApiClient;
import com.endava.tms.apiinterface.ApiInterface;
import com.endava.tms.model.Order;
import com.endava.tms.model.OrderPatchDTO;
import com.endava.tms.viewholder.OrderViewHolder;

import java.util.List;
import java.util.Locale;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class OrderAdapter extends RecyclerView.Adapter<OrderViewHolder> {
    public List<Order> orderList;
    Context context;
    ApiInterface apiInterface;

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

        orderID = orderList.get(position).getOrderID();
        holder.deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                deleteOrder();
            }
        });

        holder.modifyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                patchOrder();
            }
        });

    }

    @Override
    public int getItemCount() {
        return orderList.size();
    }

    public void deleteOrder(){
        apiInterface = ApiClient.getClient().create(ApiInterface.class);
        Call<Void> call = apiInterface.deleteOrder(orderID);
        call.enqueue(new Callback<Void>() {
            @SuppressLint("NotifyDataSetChanged")
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                Log.d("call", "Status code: " + response.code());
                Log.d("call", "Delete sucesfull");
//                for(Order o : orderList)
//                    if(o.getOrderID() == orderID)
//                        orderList.remove(o);
                notifyDataSetChanged();
            }
            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Log.d("call", "Failed: " + t.toString());
            }
        });
    }

    public void patchOrder(){
        OrderPatchDTO orderPatchDTO = new OrderPatchDTO(3,3,5);

        apiInterface = ApiClient.getClient().create(ApiInterface.class);
        Call<Void> call = apiInterface.patchOrder(orderPatchDTO);

        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                Log.d("call", "Status code: " + response.code());
                Log.d("call", "Patch sucesfull");
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Log.d("call", "Failed: " + t.toString());
            }
        });
    }
}
