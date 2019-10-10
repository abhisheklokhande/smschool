package com.example.smschool;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class editprofile extends AppCompatActivity {

    private EditText newusername, newsurname, newemail, newcollegeid, newgender;
    private Button save, passwordchange;
    private FirebaseAuth mAuth;
    private FirebaseDatabase firebaseDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editprofile);

        newusername = findViewById(R.id.editText4);
        newsurname = findViewById(R.id.editText5);
        newemail = findViewById(R.id.editText6);
        newcollegeid = findViewById(R.id.editText7);
        newgender = findViewById(R.id.editText8);
        save = findViewById(R.id.button10);
        passwordchange = findViewById(R.id.button11);

        passwordchange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(editprofile.this,forgetpassword.class);
                startActivity(intent);
            }
        });

        mAuth = FirebaseAuth.getInstance();
        firebaseDatabase = FirebaseDatabase.getInstance();

        final DatabaseReference databaseReference = firebaseDatabase.getReference(mAuth.getUid());

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                student student = dataSnapshot.getValue(student.class);
                try {
                    newusername.setText(student.getFirstname());
                    newsurname.setText(student.getSurname());
                    newcollegeid.setText(student.getCollegeid());
                    newemail.setText(student.getEmail());
                    newgender.setText(student.getGender());
                }
                catch(NullPointerException ignored){
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(editprofile.this, databaseError.getCode(), Toast.LENGTH_SHORT).show();
            }
        });

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = newusername.getText().toString();
                String surname = newsurname.getText().toString();
                String email = newemail.getText().toString();
                String collegeid = newcollegeid.getText().toString();
                String gender = newgender.getText().toString();

                student student = new student(name, surname, email, collegeid, gender);

                databaseReference.setValue(student);
                finish();
            }
        });
    }
}
