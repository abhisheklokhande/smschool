package com.example.smschool;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class forgetpassword extends AppCompatActivity {

    private EditText passwordemail;
    private Button resetpassword;
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgetpassword);

        passwordemail = (EditText) findViewById(R.id.editText3);
        resetpassword = (Button) findViewById(R.id.button7);
        firebaseAuth = FirebaseAuth.getInstance();

        resetpassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String useremail = passwordemail.getText().toString().trim();

                if(useremail.equals("")) {
                    Toast.makeText(forgetpassword.this, "Please enter your registered email ID", Toast.LENGTH_LONG).show();
                }
                else
                {
                    firebaseAuth.sendPasswordResetEmail(useremail).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if(task.isSuccessful()) {
                                Toast.makeText(forgetpassword.this, "Link is send to your mail id to reset password",
                                        Toast.LENGTH_SHORT).show();
                                finish();
                                startActivity(new Intent(forgetpassword.this, MainActivity.class));
                            }
                            else
                                Toast.makeText(forgetpassword.this, "Error in sending password Reset",
                                        Toast.LENGTH_LONG).show();
                        }
                    });
                }

            }
        });
    }
}
