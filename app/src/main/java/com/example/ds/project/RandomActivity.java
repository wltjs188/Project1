package com.example.ds.project;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class RandomActivity extends AppCompatActivity {
    TextView title,restName;
    Button random;
    Intent intent1;
    String str;
    FirebaseDatabase database;
    DatabaseReference mDatabase;
    Rest rest;
    List<Rest> rests; //받아온데이터리스트


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_random);
        database=FirebaseDatabase.getInstance();
        mDatabase=database.getReference();
        title = (TextView)findViewById(R.id.title);
        random=(Button)findViewById(R.id.random);
        rests= new ArrayList<Rest>();
        rest=new Rest();
        restName=(TextView)findViewById(R.id.restName);
        intent1 = getIntent();
        str=intent1.getStringExtra("genre");
        title.setText(str);

        mDatabase.child("rest").addValueEventListener(new ValueEventListener() {
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot snapshot : dataSnapshot.getChildren()){
                    rest = snapshot.getValue(Rest.class);
                    rests.add(rest);
                }
                Log.d("안쪽",rests.get(1).name);

            }
            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        random.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                restName.setText("오늘 점심은이닷!");
            }
        });

    }

}
