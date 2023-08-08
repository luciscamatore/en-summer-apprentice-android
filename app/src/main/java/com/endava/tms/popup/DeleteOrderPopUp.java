package com.endava.tms.popup;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.endava.tms.R;
import com.endava.tms.adapter.OrderAdapter;
import com.endava.tms.apiinterface.ApiClient;
import com.endava.tms.apiinterface.ApiInterface;
import com.endava.tms.model.Order;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DeleteOrderPopUp extends DialogFragment {
    ApiInterface apiInterface;
    OrderAdapter orderAdapter;
    public Integer orderID;
    List<Order> orderList;

    public DeleteOrderPopUp(Integer orderID, List<Order> orderList, OrderAdapter orderAdapter){
        this.orderID = orderID;
        this.orderList = orderList;
        this.orderAdapter = orderAdapter;
    }
    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater layoutInflater = getActivity().getLayoutInflater();
        View view = layoutInflater.inflate(R.layout.delete_popup, null);

        builder.setView(view)
                //.setTitle("Are you sure?")
                .setMessage("Are you sure you want to delete this order?")
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener(){
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                })
                .setPositiveButton("Delete", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        deleteOrder();
                    }
                });
        return builder.create();
    }

    public void deleteOrder(){
        apiInterface = ApiClient.getClient().create(ApiInterface.class);
        Call<Void> call = apiInterface.deleteOrder(orderID);
        call.enqueue(new Callback<Void>() {
            @SuppressLint("NotifyDataSetChanged")
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                Log.d("call", "Status code: " + response.code());
                for(int i =0; i<orderList.size();i++)
                    if(orderList.get(i).getOrderID() == orderID)
                        orderList.remove(i);
                orderAdapter.notifyDataSetChanged();
            }
            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Log.d("call", "Failed: " + t.toString());
            }
        });
    }
}
