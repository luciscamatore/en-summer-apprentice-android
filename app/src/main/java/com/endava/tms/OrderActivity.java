package com.endava.tms;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.endava.tms.adapter.OrderAdapter;
import com.endava.tms.apiinterface.ApiClient;
import com.endava.tms.apiinterface.ApiInterface;
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
    public Intent intent;
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.order_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if( id == R.id.mybutton){
            intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        }

        return super.onOptionsItemSelected(item);
    }
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
                Log.d("call", ""+response.body());

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
        RecyclerView recyclerView = findViewById(R.id.recyclervieworders);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        orderAdapter = new OrderAdapter(this, this.orderList);
        recyclerView.setAdapter(this.orderAdapter);
    }
}
