package com.example.smschool;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;


import java.io.IOException;
import java.util.UUID;

public class registration extends AppCompatActivity {

    EditText emailtext, passwordtext, confirmpasswordtext, textname, textsurname, textcollegeid;
    Button submit;
    RadioButton radioGenderMale, radioGenderFemale;
    private ImageView userprofilepic;
    private FirebaseAuth mAuth;
    DatabaseReference databaseReference;
    String gender = "";
    private FirebaseStorage firebaseStorage;
    private static int PICK_IMAGE=123;
    private StorageReference storageReference;
    private Uri filePath;


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if(requestCode == PICK_IMAGE && resultCode == RESULT_OK
                && data!=null && data.getData()!=null) {

            filePath = data.getData();
            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), filePath);
                userprofilepic.setImageBitmap(bitmap);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
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
        userprofilepic = findViewById(R.id.imageView4);


        databaseReference = FirebaseDatabase.getInstance().getReference();


        userprofilepic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent, "select image"), PICK_IMAGE);
            }
        });







        mAuth = FirebaseAuth.getInstance();
        firebaseStorage = FirebaseStorage.getInstance();
        storageReference = firebaseStorage.getReference();




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

                if(filePath == null) {
                    Toast.makeText(registration.this, "Please select image", Toast.LENGTH_SHORT).show();
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
                                        uploadImage();
                                        student information = new student (
                                             firstname,
                                             surname,
                                             email,
                                             gender,
                                             collegeid
                                        );

                                        FirebaseDatabase.getInstance().getReference()
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
                             Toast.makeText(registration.this, "Successfully Registered, " +
                                      "Verification mail sent", Toast.LENGTH_SHORT).show();
                                 mAuth.signOut();
                                 finish();
                                 startActivity(new Intent(registration.this, MainActivity.class));
                           }
                             else {
                                  Toast.makeText(registration.this,"Verification mail has'nt been sent",
                                  Toast.LENGTH_SHORT).show();
                             }
                        }
                  });
               }
            }

    private void uploadImage() {
        if(filePath!=null) {
            StorageReference reference = storageReference.child(mAuth.getUid()).child("images/").child("profile pic");
            reference.putFile(filePath)
                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            Toast.makeText(registration.this, "Successfully uploaded",Toast.LENGTH_SHORT).show();
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(registration.this, "Error",Toast.LENGTH_SHORT).show();
                }
            });
        }




    }



}
