package com.endava.tms;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.endava.tms.adapter.EventAdapter;
import com.endava.tms.model.Event;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView recyclerView = findViewById(R.id.reciclerview);

        List<Event> eventList = new ArrayList<>();
        eventList.add(new Event("Electric Castle","Muzica electronica si nu numai","Bontida Castle","Festival", LocalDateTime.now(), LocalDateTime.now(),R.drawable.ec));
        eventList.add(new Event("Untold","Muzica electronica si nu numai","la untold","Festival", LocalDateTime.now(), LocalDateTime.now(),R.drawable.untold));
        eventList.add(new Event("Wine Festival","Vin","Central Park Cluj-Napoca","Beutura", LocalDateTime.now(), LocalDateTime.now(),R.drawable.wine));
        eventList.add(new Event("Maieru vs Sangeorz","Folbal","Sintetic Sangeorz","Sport", LocalDateTime.now(), LocalDateTime.now(),R.drawable.fb));
        eventList.add(new Event("SYF","Sangeorz Youth Fest","Sangeorz-Bai Central Park","Muzica", LocalDateTime.now(), LocalDateTime.now(),R.drawable.syf));


        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new EventAdapter(getApplicationContext(),eventList));

    }
}
