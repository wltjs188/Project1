package com.example.ds.project;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
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
    Intent intent1;
    String str;
    FirebaseDatabase database;
    DatabaseReference mDatabase;
    List<Rest> rests= new ArrayList<Rest>(); //받아온데이터리스트

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_random);
        database=FirebaseDatabase.getInstance();
        mDatabase=database.getReference();
        title = (TextView)findViewById(R.id.title);
        restName=(TextView)findViewById(R.id.restName);
        intent1 = getIntent();
        str=intent1.getStringExtra("genre");
        title.setText(str);
        mDatabase.child("rest").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                rests.clear();
                for(DataSnapshot snapshot : dataSnapshot.getChildren()){
                    Rest rest = snapshot.getValue(Rest.class);
                    rests.add(rest);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }

    public void random(View view) {
        double randomValue = Math.random();
        int ran = (int)(randomValue * rests.size()) -1;
        String get_rest = rests.get(ran).name;
        restName.setText("오늘 점심은"+get_rest+"이닷!");
    }
}
