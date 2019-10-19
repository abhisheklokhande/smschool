package com.example.smschool;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.Toast;


import com.github.sundeepk.compactcalendarview.CompactCalendarView;
import com.github.sundeepk.compactcalendarview.domain.Event;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class holidays extends AppCompatActivity {

    CompactCalendarView compactCalendarView;
    private SimpleDateFormat dateFormatMonth = new SimpleDateFormat("MMMM- YYYY", Locale.getDefault());

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_holidays);

        compactCalendarView = findViewById(R.id.compactcalendar_view);
        compactCalendarView.setUseThreeLetterAbbreviation(true);

        Event ev1 = new Event(Color.RED,1571608800000L, "Teachers professional day");
        compactCalendarView.addEvent(ev1);

        Event ev2 = new Event(Color.RED,1577228400000L, "Christmas day");
        compactCalendarView.addEvent(ev2);

        compactCalendarView.setListener(new CompactCalendarView.CompactCalendarViewListener() {
            @Override
            public void onDayClick(Date dateClicked) {
                Context context = getApplicationContext();

                if(dateClicked.toString().compareTo("1571608800000L")==0) {
                    Toast.makeText(context, "Teachers professional day", Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(context, "No events plan for this date", Toast.LENGTH_SHORT).show();
                }

                    if(dateClicked.toString().compareTo("1577228400000L")==0) {
                        Toast.makeText(context, "christmas day", Toast.LENGTH_SHORT).show();
                    }else {
                        Toast.makeText(context, "", Toast.LENGTH_SHORT).show();
                    }

            }


            @Override
            public void onMonthScroll(Date firstDayOfNewMonth) {

            }


        });
    }


}
