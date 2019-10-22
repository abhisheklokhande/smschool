package com.example.smschool;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Picasso;


public class dashboard extends AppCompatActivity {

    public ImageButton image;
    private ImageView imageView;
    private FirebaseAuth mAuth;
    FirebaseDatabase firebaseDatabase;
    FirebaseStorage firebaseStorage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        imageView = findViewById(R.id.imageView18);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(dashboard.this, profile.class);
                startActivity(intent);
                mAuth = FirebaseAuth.getInstance();
                firebaseDatabase = FirebaseDatabase.getInstance();
                firebaseStorage = FirebaseStorage.getInstance();

                DatabaseReference databaseReference = firebaseDatabase.getReference(mAuth.getUid());

                StorageReference storageReference = firebaseStorage.getReference();
                storageReference.child(mAuth.getUid()).child("images/profile pic").getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                    @Override
                    public void onSuccess(Uri uri) {
                        Picasso.get().load(uri).fit().centerCrop().into(imageView);
                    }
                });
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
