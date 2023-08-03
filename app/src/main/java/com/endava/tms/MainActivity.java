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
import com.endava.tms.apiinterface.ApiClient;
import com.endava.tms.apiinterface.ApiInterface;
import com.endava.tms.model.Event;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    public List<Event> eventList = new ArrayList<>();
    public EventAdapter eventAdapter;
    public ApiInterface apiInterface;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.setTitle("Events");
        //getSupportActionBar().hide();

        getAllEvents();

        setUpSearch();
        setUpSpinner();
        setUpRecyclerView();

    }
    public void getAllEvents(){
        apiInterface = ApiClient.getClient().create(ApiInterface.class);
        Call<List<Event>> call = apiInterface.getAllEvents();
        call.enqueue(new Callback<List<Event>>() {
            @SuppressLint("NotifyDataSetChanged")
            @Override
            public void onResponse(Call<List<Event>> call, Response<List<Event>> response) {
                Log.d("call", "Status code: "+response.code());
                eventList.clear();
                if(response.body() != null)
                    eventList.addAll(response.body());
                eventAdapter.notifyDataSetChanged();
            }
            @Override
            public void onFailure(Call<List<Event>> call, Throwable t) {
                Log.d("call", "Failed: "+t.toString());
            }
        });
    }
    public void setUpRecyclerView(){
        RecyclerView recyclerView = findViewById(R.id.reciclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
//      recyclerView.setAdapter(new EventAdapter(getApplicationContext(),eventList));

        eventAdapter = new EventAdapter(this, this.eventList);

        recyclerView.setAdapter(this.eventAdapter);
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
                eventAdapter.notifyDataSetChanged();
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
                eventAdapter.getFilter().filter(s);
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
    public void addImages(){
//        eventList = new ArrayList<>();
//        eventList.add(new Event("Electric Castle","Muzica electronica si nu numai","Bontida Castle","Festival", LocalDate.now(), LocalDate.now(),R.drawable.ec));
//        eventList.add(new Event("Untold","Muzica electronica si nu numai","la untold","Festival", LocalDate.now(), LocalDate.now(),R.drawable.untold));
//        eventList.add(new Event("Wine Festival","Vin","Central Park Cluj-Napoca","Beutura", LocalDate.now(), LocalDate.now(),R.drawable.wine));
//        eventList.add(new Event("Maieru vs Sangeorz","Folbal","Sintetic Sangeorz","Sport", LocalDate.now(), LocalDate.now(),R.drawable.fb));
//        eventList.add(new Event("SYF","Sangeorz Youth Fest","Sangeorz-Bai Central Park","Muzica", LocalDate.now(), LocalDate.now(),R.drawable.syf));
        //for(Event ev : eventList)
            //ev.setImage()
    }
}
