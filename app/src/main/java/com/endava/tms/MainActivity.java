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
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    public List<Event> eventList;
    public EventAdapter sortAdapter;
    public EventAdapter searchAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.setTitle("Events");
        //getSupportActionBar().hide();

        setUpSearch();
        setUpSpinner();
        fillList();
        setUpReciclerView();


    }
    public void setUpReciclerView(){
        RecyclerView recyclerView = findViewById(R.id.reciclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
//      recyclerView.setAdapter(new EventAdapter(getApplicationContext(),eventList));

        searchAdapter = new EventAdapter(this, this.eventList);

        recyclerView.setAdapter(this.searchAdapter);
    }

    public void setUpSpinner(){
        Spinner spinner = findViewById(R.id.event_spinner);
        ArrayAdapter<CharSequence> spinnerAdapter = ArrayAdapter.createFromResource(this, R.array.event_sort, android.R.layout.simple_spinner_dropdown_item);
        spinner.setPrompt("Sort by");
        spinner.setAdapter(spinnerAdapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @SuppressLint("NotifyDataSetChanged")
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                sorter(i);
                searchAdapter.notifyDataSetChanged();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    public void setUpSearch(){
        SearchView searchView = findViewById(R.id.searchbar);
        //searchView.setSubmitButtonEnabled(true);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }
            @Override
            public boolean onQueryTextChange(String s) {
                searchAdapter.getFilter().filter(s);
                return false;
            }
        });
    }

    public void sorter(int position){
        switch (position){
            case 0: //aici trebe dupa pret (vedem cand ne bagam la conectare la backend)
                eventList.sort((e1, e2) -> e1.getEventType().compareTo(e2.getEventType()));
                break;
            case 1: //la fel ca la 0
                eventList.sort((e1,e2) -> e1.getEventName().compareTo(e2.getEventName()));
                Collections.reverse(eventList);
                break;
            case 2:
                eventList.sort((e1,e2) -> e1.getEventName().compareTo(e2.getEventName()));
                break;
            case 3:
                eventList.sort(Comparator.comparing(Event::getEventName));
                Collections.reverse(eventList);
                break;
            default:
                break;
        }
    }
    public void fillList(){
        eventList = new ArrayList<>();
        eventList.add(new Event("Electric Castle","Muzica electronica si nu numai","Bontida Castle","Festival", LocalDate.now(), LocalDate.now(),R.drawable.ec));
        eventList.add(new Event("Untold","Muzica electronica si nu numai","la untold","Festival", LocalDate.now(), LocalDate.now(),R.drawable.untold));
        eventList.add(new Event("Wine Festival","Vin","Central Park Cluj-Napoca","Beutura", LocalDate.now(), LocalDate.now(),R.drawable.wine));
        eventList.add(new Event("Maieru vs Sangeorz","Folbal","Sintetic Sangeorz","Sport", LocalDate.now(), LocalDate.now(),R.drawable.fb));
        eventList.add(new Event("SYF","Sangeorz Youth Fest","Sangeorz-Bai Central Park","Muzica", LocalDate.now(), LocalDate.now(),R.drawable.syf));

    }
}
