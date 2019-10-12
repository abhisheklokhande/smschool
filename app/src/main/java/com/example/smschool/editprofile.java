package com.example.smschool;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

import java.io.IOException;

public class editprofile extends AppCompatActivity {

    private EditText newusername, newsurname, newemail, newcollegeid, newgender;
    private Button save, passwordchange;
    private FirebaseAuth mAuth;
    private FirebaseDatabase firebaseDatabase;
    private ImageView updateprofilepic;
    private static int PICK_IMAGE=123;
    private StorageReference storageReference;
    private Uri filePath;
    private FirebaseStorage firebaseStorage;


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if(requestCode == PICK_IMAGE && resultCode == RESULT_OK
                && data!=null && data.getData()!=null) {

            filePath = data.getData();
            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), filePath);
                updateprofilepic.setImageBitmap(bitmap);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

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
        updateprofilepic = findViewById(R.id.imageView7);

        passwordchange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(editprofile.this,forgetpassword.class);
                startActivity(intent);
            }
        });

        mAuth = FirebaseAuth.getInstance();
        firebaseDatabase = FirebaseDatabase.getInstance();
        firebaseStorage = FirebaseStorage.getInstance();

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

        final StorageReference storageReference = firebaseStorage.getReference();
        storageReference.child(mAuth.getUid()).child("images/profile pic").getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                Picasso.get().load(uri).fit().centerCrop().into(updateprofilepic);
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

                StorageReference reference = storageReference.child(mAuth.getUid()).child("images/").child("profile pic");
                reference.putFile(filePath)
                        .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                            @Override
                            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                                Toast.makeText(editprofile.this, "Successfully uploaded",Toast.LENGTH_SHORT).show();
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(editprofile.this, "Error",Toast.LENGTH_SHORT).show();
                    }
                });


                finish();
            }
        });

        updateprofilepic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent, "select image"), PICK_IMAGE);
            }
        });
    }


}
