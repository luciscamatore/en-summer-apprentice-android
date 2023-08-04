package com.endava.tms;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.endava.tms.adapter.OrderAdapter;
import com.endava.tms.apiinterface.ApiClient;
import com.endava.tms.apiinterface.ApiInterface;
import com.endava.tms.model.Event;
import com.endava.tms.model.Order;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class OrderActivity extends AppCompatActivity {
    public List<Order> orderList = new ArrayList<>();
    public OrderAdapter orderAdapter;
    public ApiInterface apiInterface;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);

        this.setTitle("Orders");
        getAllOrders();
    }
    public void getAllOrders(){
        apiInterface = ApiClient.getClient().create(ApiInterface.class);
        Call<List<Order>> call = apiInterface.getAllOrders();

        call.enqueue(new Callback<List<Order>>() {
            @SuppressLint("NotifyDataSetChanged")
            @Override
            public void onResponse(Call<List<Order>> call, Response<List<Order>> response) {
                Log.d("call", ""+response.code());

                orderList.clear();
                if(response.body() != null)
                    orderList.addAll(response.body());

                setUpRecyclerView();
                orderAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<List<Order>> call, Throwable t) {
                Log.d("call", t.toString());
            }
        });
    }
    public void setUpRecyclerView(){
        RecyclerView recyclerView = findViewById(R.id.reciclervieworders);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        orderAdapter = new OrderAdapter(this, this.orderList);
        recyclerView.setAdapter(this.orderAdapter);
    }
}
