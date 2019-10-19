package com.example.smschool;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;


public class dashboard extends AppCompatActivity {

    public ImageButton image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        ImageButton image = (ImageButton) findViewById(R.id.imageButton18);
        image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(dashboard.this, profile.class);
                startActivity(intent);
            }
        });

        image = (ImageButton) findViewById(R.id.imageButton6);
        image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(dashboard.this, holidays.class);
                startActivity(intent);
            }
        });
        image = (ImageButton) findViewById(R.id.imageButton15);
        image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(dashboard.this, socialActivity.class);
                startActivity(intent);
            }
        });

        image = (ImageButton) findViewById(R.id.imageButton12);
        image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(dashboard.this, bustrackinglive.class);
                startActivity(intent);
            }
        });

        image = (ImageButton) findViewById(R.id.imageButton13);
        image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(dashboard.this, canteen.class);
                startActivity(intent);
            }
        });

        image = (ImageButton) findViewById(R.id.imageButton5);
        image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(dashboard.this, sports.class);
                startActivity(intent);
            }
        });

        image = (ImageButton) findViewById(R.id.imageButton11);
        image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(dashboard.this, fee.class);
                startActivity(intent);
            }
        });

        image = (ImageButton) findViewById(R.id.imageButton8);
        image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(dashboard.this, attendance.class);
                startActivity(intent);
            }
        });

        image = (ImageButton) findViewById(R.id.imageButton10);
        image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(dashboard.this, elearning.class);
                startActivity(intent);
            }
        });

        image = (ImageButton) findViewById(R.id.imageButton16);
        image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(dashboard.this, testschedule.class);
                startActivity(intent);
            }
        });

        image = (ImageButton) findViewById(R.id.imageButton4);
        image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(dashboard.this, events.class);
                startActivity(intent);
            }
        });

        image = (ImageButton) findViewById(R.id.imageButton14);
        image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(dashboard.this, performance.class);
                startActivity(intent);
            }
        });

        image = (ImageButton) findViewById(R.id.imageButton17);
        image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(dashboard.this, aboutus.class);
                startActivity(intent);
            }
        });

        image = (ImageButton) findViewById(R.id.imageButton7);
        image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(dashboard.this, feedback.class);
                startActivity(intent);
            }
        });

        image = (ImageButton) findViewById(R.id.imageButton22);
        image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(dashboard.this, searching.class);
                startActivity(intent);
            }
        });

    }
}
