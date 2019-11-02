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

public class matviewfile extends AppCompatActivity {

    ListView myPDfListView;
    DatabaseReference databaseReference;
    List<uploadPDFM> uploadPDFS;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_matviewfile);
        myPDfListView = findViewById(R.id.myListView);
        uploadPDFS = new ArrayList<>();

        viewAllFiles();

        myPDfListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                uploadPDFM uploadPDFM = uploadPDFS.get(position);

                Intent intent = new Intent();
                intent.setType(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(uploadPDFM.getLink()));
                startActivity(intent);
            }
        });



    }

    private void viewAllFiles() {

        databaseReference = FirebaseDatabase.getInstance().getReference("MathematicsPDF");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for(DataSnapshot postsnapshot: dataSnapshot.getChildren()){

                    uploadPDFM uploadPDFM = postsnapshot.getValue(com.example.smschool.uploadPDFM.class);
                    uploadPDFS.add(uploadPDFM);

                }

                String[] uploads = new String[uploadPDFS.size()];
                for(int i=0;i<uploads.length;i++) {

                    uploads[i] = uploadPDFS.get(i).getName();

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

