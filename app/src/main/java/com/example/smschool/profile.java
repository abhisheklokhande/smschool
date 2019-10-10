package com.example.smschool;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.facebook.Profile;
import com.facebook.login.LoginManager;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Picasso;

import java.util.UUID;

public class profile extends AppCompatActivity {

    private Button mLogoutBtn, edit;
    private FirebaseAuth mAuth;
    private ImageView profilepic;
    GoogleSignInClient mGoogleSignInClient;
    private TextView nameprofile, emailprofile, genderprofile, surnameprofile, collegeidprofile;
    private FirebaseDatabase firebaseDatabase;
    private FirebaseStorage firebaseStorage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        nameprofile = findViewById(R.id.nameprofile);
        emailprofile = findViewById(R.id.emailprofile);
        genderprofile = findViewById(R.id.genderprofile);
        surnameprofile = findViewById(R.id.surnameprofile);
        collegeidprofile = findViewById(R.id.collegeidprofile);
        edit = findViewById(R.id.button8);
        profilepic = findViewById(R.id.imageView3);

        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(profile.this, editprofile.class);
                startActivity(intent);
            }
        });

        mAuth = FirebaseAuth.getInstance();
        firebaseDatabase = FirebaseDatabase.getInstance();
        firebaseStorage = FirebaseStorage.getInstance();

        DatabaseReference databaseReference = firebaseDatabase.getReference(mAuth.getUid());

        StorageReference storageReference = firebaseStorage.getReference();
        storageReference.child(mAuth.getUid()).child("images/profile pic").getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                Picasso.get().load(uri).fit().centerCrop().into(profilepic);
            }
        });

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                student student = dataSnapshot.getValue(student.class);
                try {
                    nameprofile.setText(student.getFirstname());
                    surnameprofile.setText(student.getSurname());
                    collegeidprofile.setText(student.getCollegeid());
                    emailprofile.setText(student.getEmail());
                    genderprofile.setText(student.getGender());
                }
                    catch(NullPointerException ignored){
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(profile.this, databaseError.getCode(), Toast.LENGTH_SHORT).show();
            }
        });

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();
        mGoogleSignInClient = GoogleSignIn.getClient(this,gso);

        mLogoutBtn = (Button) findViewById(R.id.button2);

        // Initialize Firebase Auth


        mLogoutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mAuth.signOut();
                LoginManager.getInstance().logOut();

                updateUI();

            }
        });
    }

    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();

        if(currentUser == null) {
            updateUI();
        }
    }

    private void updateUI() {

        Toast.makeText(profile.this, "you are logged out" , Toast.LENGTH_LONG).show();
        Intent dashboardIntent = new Intent(profile.this, MainActivity.class);
        startActivity(dashboardIntent);
        finish();
    }
    private void signOut() {
        mGoogleSignInClient.signOut()
                .addOnCompleteListener(this, new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull com.google.android.gms.tasks.Task<Void> task) {
                      Toast.makeText(profile.this,"signed out successfully", Toast.LENGTH_LONG).show();
                      finish();
                    }

                });

    }
}
