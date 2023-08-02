package com.endava.tms;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.endava.tms.adapter.EventAdapter;
import com.endava.tms.model.Event;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.setTitle("Events");
        //getSupportActionBar().hide();

        RecyclerView recyclerView = findViewById(R.id.reciclerview);

        Spinner spinner = (Spinner)findViewById(R.id.event_spinner);
        ArrayAdapter<CharSequence> spinnerAdapter = ArrayAdapter.createFromResource(this, R.array.event_sort, android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(spinnerAdapter);

        List<Event> eventList = new ArrayList<>();
        eventList.add(new Event("Electric Castle","Muzica electronica si nu numai","Bontida Castle","Festival", LocalDate.now(), LocalDate.now(),R.drawable.ec));
        eventList.add(new Event("Untold","Muzica electronica si nu numai","la untold","Festival", LocalDate.now(), LocalDate.now(),R.drawable.untold));
        eventList.add(new Event("Wine Festival","Vin","Central Park Cluj-Napoca","Beutura", LocalDate.now(), LocalDate.now(),R.drawable.wine));
        eventList.add(new Event("Maieru vs Sangeorz","Folbal","Sintetic Sangeorz","Sport", LocalDate.now(), LocalDate.now(),R.drawable.fb));
        eventList.add(new Event("SYF","Sangeorz Youth Fest","Sangeorz-Bai Central Park","Muzica", LocalDate.now(), LocalDate.now(),R.drawable.syf));


        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new EventAdapter(getApplicationContext(),eventList));

    }
}
