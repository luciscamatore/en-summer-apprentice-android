package com.endava.tms;


import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.SearchView;
import android.widget.Spinner;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.endava.tms.adapter.EventAdapter;
import com.endava.tms.model.Event;

import java.time.LocalDate;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    public List<Event> eventList;
    public EventAdapter eventAdapter;
    public ArrayAdapter<Event> adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setUpSearch();
        setUpSpinner();

        this.setTitle("Events");
        //getSupportActionBar().hide();

        RecyclerView recyclerView = findViewById(R.id.reciclerview);


        eventList = new ArrayList<>();
        eventList.add(new Event("Electric Castle","Muzica electronica si nu numai","Bontida Castle","Festival", LocalDate.now(), LocalDate.now(),R.drawable.ec));
        eventList.add(new Event("Untold","Muzica electronica si nu numai","la untold","Festival", LocalDate.now(), LocalDate.now(),R.drawable.untold));
        eventList.add(new Event("Wine Festival","Vin","Central Park Cluj-Napoca","Beutura", LocalDate.now(), LocalDate.now(),R.drawable.wine));
        eventList.add(new Event("Maieru vs Sangeorz","Folbal","Sintetic Sangeorz","Sport", LocalDate.now(), LocalDate.now(),R.drawable.fb));
        eventList.add(new Event("SYF","Sangeorz Youth Fest","Sangeorz-Bai Central Park","Muzica", LocalDate.now(), LocalDate.now(),R.drawable.syf));

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
//        recyclerView.setAdapter(new EventAdapter(getApplicationContext(),eventList));

        this.adapter = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_list_item_1, eventList);

        this.eventAdapter = new EventAdapter(this, this.eventList);
        recyclerView.setAdapter(this.eventAdapter);

    }

    public void setUpSpinner(){
        Spinner spinner = findViewById(R.id.event_spinner);
        ArrayAdapter<CharSequence> spinnerAdapter = ArrayAdapter.createFromResource(this, R.array.event_sort, android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(spinnerAdapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @SuppressLint("NotifyDataSetChanged")
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if(i == 1) {
                    eventList.sort((e1, e2) -> e1.getEventName().compareTo(e2.getEventName()));
                    Log.v("HERE", eventList.toString());
                }
                eventAdapter.notifyDataSetChanged();
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    public void setUpSearch(){
        SearchView searchView = findViewById(R.id.searchbar);
        searchView.setSubmitButtonEnabled(true);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                adapter.getFilter().filter(s);
                Log.v("HERE", s);
                return false;
            }
            @Override
            public boolean onQueryTextChange(String s) {
                adapter.getFilter().filter(s);
                return false;
            }
        });
    }
}
