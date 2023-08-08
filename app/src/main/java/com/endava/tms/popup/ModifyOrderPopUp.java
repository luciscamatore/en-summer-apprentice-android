package com.endava.tms.popup;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.TaskInfo;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.endava.tms.R;
import com.endava.tms.adapter.OrderAdapter;
import com.endava.tms.apiinterface.ApiClient;
import com.endava.tms.apiinterface.ApiInterface;
import com.endava.tms.model.Order;
import com.endava.tms.model.OrderPatchDTO;
import com.endava.tms.model.TicketCategory;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ModifyOrderPopUp extends DialogFragment {
    ApiInterface apiInterface;
    OrderAdapter orderAdapter;
    Integer orderID;
    List<Order> orderList;
    Spinner spinner;
    String ticketDescription;

    public ModifyOrderPopUp(Integer orderID, List<Order> orderList, OrderAdapter orderAdapter) {
        this.orderAdapter = orderAdapter;
        this.orderID = orderID;
        this.orderList = orderList;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater layoutInflater = getActivity().getLayoutInflater();
        View view = layoutInflater.inflate(R.layout.event_popup, null);

        spinner = view.findViewById(R.id.category_spinner);
        ArrayAdapter<CharSequence> spinnerAdapter = ArrayAdapter.createFromResource(getContext(), R.array.category, android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(spinnerAdapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                ticketDescription = spinner.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        EditText nrTick = view.findViewById(R.id.nr_tickets);


        builder.setView(view)
                .setTitle("Modify your order")
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener(){
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                    }
                })
                .setPositiveButton("Modify", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        int nrTickets =  Integer.parseInt(nrTick.getText().toString());
                        OrderPatchDTO orderPatchDTO = new OrderPatchDTO(orderID, ticketDescription, nrTickets);
                        patchOrder(orderPatchDTO);
                    }
                });
        return builder.create();
    }

    public void patchOrder(OrderPatchDTO orderPatchDTO){
        apiInterface = ApiClient.getClient().create(ApiInterface.class);
        Call<Void> call = apiInterface.patchOrder(orderPatchDTO);

        call.enqueue(new Callback<Void>() {
            @SuppressLint("NotifyDataSetChanged")
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                Log.d("call", "Status code: " + response.code());
                Log.d("call", "Patch sucesfull");
                orderAdapter.notifyDataSetChanged();
            }
            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Log.d("call", "Failed: " + t.toString());
            }
        });
    }

    public Order findOrderByOrderID(List<Order> orders, Integer orderID){
        for(Order o : orders)
            if(o.getOrderID() == orderID)
                return o;
        return null;
    }
    public int findTicketID(List<Order> orders, String description){
        for(Order o : orders)
            if(o.getTicketCategory().getDescription().equals(description))
                return o.getTicketCategory().getTicketCategoryID();
        return -1;
    }
}
