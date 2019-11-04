package com.example.smschool;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.github.barteksc.pdfviewer.PDFView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class attendance extends AppCompatActivity {

    private TextView text1;
    private PDFView pdfView;
    private FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference mref = database.getReference("attendance");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attendance);
        pdfView = findViewById(R.id.pdfView);
        text1 = findViewById(R.id.text1);
        mref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String value=dataSnapshot.getValue(String.class);
                text1.setText(value);
                Toast.makeText(attendance.this,"updated",Toast.LENGTH_SHORT).show();
                String url = text1.getText().toString();
                new RetrivePdfStream().execute(url);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(attendance.this,"failed to load",Toast.LENGTH_SHORT).show();
            }
        });}
        class RetrivePdfStream extends AsyncTask<String, Void, InputStream> {

            @Override
            protected InputStream doInBackground(String... strings) {
                InputStream inputStream=null;
                try {
                    URL url=new URL (strings[0]);
                    HttpURLConnection urlConnection = (HttpURLConnection)url.openConnection();
                    if(urlConnection.getResponseCode()==200)
                    {
                        inputStream=new BufferedInputStream(urlConnection.getInputStream());
                    }
                }catch (IOException e) {
                    return null;
                }
                return inputStream;
            }

            @Override
            protected void onPostExecute(InputStream inputStream) {
                pdfView.fromStream(inputStream).load();
            }
        }
    }

