package com.example.smschool;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class spaviewfile extends AppCompatActivity {

    ListView myPDfListView;
    DatabaseReference databaseReference;
    List<uploadPDFS> uploadPDFs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spaviewfile);
        myPDfListView = findViewById(R.id.myListView);
        uploadPDFs = new ArrayList<>();

        viewAllFiles();

        myPDfListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                uploadPDFS uploadPDFS = uploadPDFs.get(position);

                Intent intent = new Intent();
                intent.setType(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(uploadPDFS.getUrl()));
                startActivity(intent);
            }
        });



    }

    private void viewAllFiles() {

        databaseReference = FirebaseDatabase.getInstance().getReference("SPANISHPDF");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for(DataSnapshot postsnapshot: dataSnapshot.getChildren()){

                    uploadPDFS uploadPDFS = postsnapshot.getValue(com.example.smschool.uploadPDFS.class);
                    uploadPDFs.add(uploadPDFS);

                }

                String[] uploads = new String[uploadPDFs.size()];
                for(int i=0;i<uploads.length;i++) {

                    uploads[i] = uploadPDFs.get(i).getName();

                }

                ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext(),android.R.layout.simple_list_item_1,uploads);
                myPDfListView.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}



