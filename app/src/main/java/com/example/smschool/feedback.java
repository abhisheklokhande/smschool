package com.example.smschool;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.hsalf.smilerating.SmileRating;

public class feedback extends AppCompatActivity {

    private FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    FirebaseAuth mAuth;
    private EditText feedbacktxt, titletxt;
    private Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback);

        feedbacktxt = findViewById(R.id.editText18);
        titletxt = findViewById(R.id.editText17);
        button = findViewById(R.id.button15);
        mAuth = FirebaseAuth.getInstance();
    databaseReference = FirebaseDatabase.getInstance().getReference(mAuth.getUid()).child("Feedback");

    button.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            String title = titletxt.getText().toString();
            String feedback = feedbacktxt.getText().toString();

            if(!TextUtils.isEmpty(title)) {
                databaseReference.push().getKey();

                feedbackhelper feedbackhelper = new feedbackhelper(title, feedback);
                databaseReference.setValue(feedbackhelper);

                Toast.makeText(feedback.this,"Thanks for your valueable feedback", Toast.LENGTH_SHORT).show();

            }else {
                Toast.makeText(feedback.this,"You should enter Title", Toast.LENGTH_SHORT).show();
            }
        }
    });


        SmileRating smileRating = findViewById(R.id.smile_rating);
        smileRating.setOnSmileySelectionListener(new SmileRating.OnSmileySelectionListener() {
            @Override
            public void onSmileySelected(int smiley, boolean reselected) {

                switch (smiley) {
                    case SmileRating.BAD:
                        Toast.makeText(feedback.this,"BAD", Toast.LENGTH_SHORT).show();
                        break;
                    case SmileRating.GOOD:
                        Toast.makeText(feedback.this,"GOOD", Toast.LENGTH_SHORT).show();

                        break;
                    case SmileRating.GREAT:
                        Toast.makeText(feedback.this,"GREAT", Toast.LENGTH_SHORT).show();

                        break;
                    case SmileRating.OKAY:
                        Toast.makeText(feedback.this,"OKAY", Toast.LENGTH_SHORT).show();

                        break;
                    case SmileRating.TERRIBLE:
                        Toast.makeText(feedback.this,"TERRIBLE", Toast.LENGTH_SHORT).show();

                        break;
                }
            }
        });

        smileRating.setOnRatingSelectedListener(new SmileRating.OnRatingSelectedListener() {
            @Override
            public void onRatingSelected(int level, boolean reselected) {
                Toast.makeText(feedback.this,"Selected rating" + level, Toast.LENGTH_SHORT).show();
            }
        });
    }
}
