package com.endava.tms.popup;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.endava.tms.R;
import com.endava.tms.adapter.EventAdapter;
import com.endava.tms.adapter.OrderAdapter;
import com.endava.tms.apiinterface.ApiClient;
import com.endava.tms.apiinterface.ApiInterface;
import com.endava.tms.model.OrderPostDTO;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EventPopUp extends DialogFragment {
    ApiInterface apiInterface;
    EventAdapter eventAdapter;
    Spinner spinner;
    String ticketDescription;
    Integer eventID;

    public EventPopUp(EventAdapter eventAdapter, Integer eventID) {
        this.eventAdapter = eventAdapter;
        this.eventID = eventID;
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
                .setTitle("Place order")
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener(){
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                })
                .setPositiveButton("Place order", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        int nrTickets =  Integer.parseInt(nrTick.getText().toString());
                        OrderPostDTO orderPostDTO = new OrderPostDTO(1, eventID,ticketDescription,nrTickets);
                        placeOrder(orderPostDTO);
                    }
                });
        return builder.create();
    }
    public void placeOrder(OrderPostDTO orderPostDTO){
        apiInterface = ApiClient.getClient().create(ApiInterface.class);
        Call<Void> call = apiInterface.placeOrder(orderPostDTO);

        call.enqueue(new Callback<Void>() {
            @SuppressLint("NotifyDataSetChanged")
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                Log.d("call", "Status code: " + response.code());
                Log.d("call", "Post sucesfull");
                eventAdapter.notifyDataSetChanged();
            }
            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Log.d("call", "Failed: " + t.toString());
            }
        });
    }
}
