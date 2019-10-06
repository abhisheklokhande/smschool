package com.example.smschool;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class registration extends AppCompatActivity {

    EditText emailtext, passwordtext, confirmpasswordtext, textname, textsurname, textcollegeid;
    Button submit;
    RadioButton radioGenderMale, radioGenderFemale;
    private FirebaseAuth mAuth;
    DatabaseReference databaseReference;
    String gender = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        textname = findViewById(R.id.editText15);
        textsurname = findViewById(R.id.editText14);
        textcollegeid = findViewById(R.id.editText16);
        radioGenderMale = findViewById(R.id.radioButton);
        radioGenderFemale = findViewById(R.id.radioButton2);
        emailtext = findViewById(R.id.editText11);
        passwordtext = findViewById(R.id.editText12);
        confirmpasswordtext = findViewById(R.id.editText13);
        submit = findViewById(R.id.button9);


        databaseReference = FirebaseDatabase.getInstance().getReference("Student");

        mAuth = FirebaseAuth.getInstance();

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                final String email = emailtext.getText().toString().trim();
                final String firstname = textname.getText().toString();
                final String surname = textsurname.getText().toString();
                final String collegeid = textcollegeid.getText().toString();
                String password = passwordtext.getText().toString().trim();
                String confirmpassword = confirmpasswordtext.getText().toString().trim();

                if(radioGenderMale.isChecked())  {
                    gender="Male" ;
                }
                if(radioGenderFemale.isChecked()) {
                    gender="Female";
                }

                if(TextUtils.isEmpty(firstname)) {
                    Toast.makeText(registration.this, "Please Enter Name", Toast.LENGTH_SHORT).show();
                    return;
                }

                if(TextUtils.isEmpty(surname)) {
                    Toast.makeText(registration.this, "Please Enter Surname", Toast.LENGTH_SHORT).show();
                    return;
                }

                if(TextUtils.isEmpty(collegeid)) {
                    Toast.makeText(registration.this, "Please Enter College Id", Toast.LENGTH_SHORT).show();
                    return;
                }

                if(TextUtils.isEmpty(email)) {
                    Toast.makeText(registration.this, "Please Enter Email", Toast.LENGTH_SHORT).show();
                    return;
                }

                if(TextUtils.isEmpty(password)) {
                    Toast.makeText(registration.this, "Please Enter Password", Toast.LENGTH_SHORT).show();
                    return;
                }

                if(TextUtils.isEmpty(confirmpassword)) {
                    Toast.makeText(registration.this, "Please Enter ConfirmPassword", Toast.LENGTH_SHORT).show();
                    return;
                }

                if(password.length()<6) {
                    Toast.makeText(registration.this, "Password too short", Toast.LENGTH_SHORT).show();

                }

                if(password.equals(confirmpassword)) {

                    mAuth.createUserWithEmailAndPassword(email, password)
                            .addOnCompleteListener(registration.this, new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()) {

                                        student information = new student (
                                             firstname,
                                             surname,
                                             email,
                                             gender,
                                             collegeid
                                        );

                                        FirebaseDatabase.getInstance().getReference("Student")
                                                .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                                .setValue(information).addOnCompleteListener(new OnCompleteListener<Void>() {
                                            @Override
                                            public void onComplete(@NonNull Task<Void> task) {

                                               sendEmailVerification();

                                            }
                                        });


                                    } else {

                                        Toast.makeText(registration.this, "Authentication Failed", Toast.LENGTH_SHORT).show();

                                    }

                                    // ...
                                }
                            });







                }

            }
        });

    }

    private void sendEmailVerification() {
        FirebaseUser firebaseUser = mAuth.getInstance().getCurrentUser();
        if(firebaseUser!=null) {
            firebaseUser.sendEmailVerification().addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    if(task.isSuccessful()) {
                        Toast.makeText(registration.this, "Successfully Registered, Verification mail sent",Toast.LENGTH_SHORT).show();
                        mAuth.signOut();
                        finish();
                        startActivity(new Intent(registration.this, MainActivity.class));
                    }
                    else {
                        Toast.makeText(registration.this,"Verification mail has'nt been sent",Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }
}
