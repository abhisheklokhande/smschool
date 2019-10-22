package com.example.smschool;


import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.CalendarView;
import android.widget.Toast;


import com.github.sundeepk.compactcalendarview.CompactCalendarView;
import com.github.sundeepk.compactcalendarview.domain.Event;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class holidays extends AppCompatActivity {

    CalendarView calendarView;
    private SimpleDateFormat dateFormatMonth = new SimpleDateFormat("MMMM- YYYY", Locale.getDefault());

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_holidays);

        calendarView = findViewById(R.id.calendarView);

        Event ev1 = new Event(Color.RED,1571608800000L, "Teachers professional day");
        

        Event ev2 = new Event(Color.RED,1577228400000L, "Christmas day");





    }


}
