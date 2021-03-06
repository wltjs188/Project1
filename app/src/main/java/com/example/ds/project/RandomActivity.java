package com.example.ds.project;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class RandomActivity extends AppCompatActivity {
    TextView title,restName;
    CheckBox cbSchool,cb419,cbPark;
    ImageView imageView;
    Button random;
    Intent intent1,intent2;
    String str;
    FirebaseDatabase database;
    Rest rest;
    int index;
    List<Rest> rests=new ArrayList<>(); //받아온데이터리스트
    List<Integer> num = new ArrayList<>();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_random);
        database=FirebaseDatabase.getInstance();
        title = (TextView)findViewById(R.id.title);
        random=(Button)findViewById(R.id.random);
        restName=(TextView)findViewById(R.id.restName);
        intent1 = getIntent();
        intent2=new Intent(getApplicationContext(),RestInfoActivity.class);
        str=intent1.getStringExtra("genre");
        title.setText(str);
        cbSchool=(CheckBox)findViewById(R.id.cbSchool);
        cb419=(CheckBox)findViewById(R.id.cb419);
        cbPark=(CheckBox)findViewById(R.id.cbPark);
        imageView=(ImageView)findViewById(R.id.ranImg);
        imageView.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

            }
        });

        database.getReference().child("rest").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()){
                    rest=snapshot.getValue(Rest.class);
                    if(str.equals(rest.genre)) {
                        rests.add(rest);
                    }
                }
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        random.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if(cbSchool.isChecked()==true) {
                    for(int i=0;i<rests.size();i++){
                        if((rests.get(i).getLocation()).equals("학교정문")){
                            num.add(i);
                        }
                    }
                }
                if(cb419.isChecked()==true) {
                    for(int i=0;i<rests.size();i++){
                        if((rests.get(i).getLocation()).equals("419거리")){
                            num.add(i);
                        }
                    }
                }
                if(cbPark.isChecked()==true) {
                    for (int i = 0; i < rests.size(); i++) {
                        if ((rests.get(i).getLocation()).equals("솔밭공원")) {
                            num.add(i);
                        }
                    }
                }
                double randomValue = Math.random();
                int ran = (int)(randomValue * num.size()) -1;
                index = num.get(ran);
                if(rests !=null) {
                    Glide.with(getApplicationContext()).load(rests.get(index).imageUrl).into(imageView);
                    restName.setText("오늘 점심은 " + rests.get(index).name + " 이닷!");
                }

            }
        });

        imageView.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                intent2.putExtra("url",rests.get(index).imageUrl);
                intent2.putExtra("menuId",rests.get(index).menuId);
                intent2.putExtra("name",rests.get(index).name);
                intent2.putExtra("genre",rests.get(index).genre);
                intent2.putExtra("latitude",rests.get(index).latitude);
                intent2.putExtra("longitude",rests.get(index).longitude);
                startActivity(intent2);
            }
        });
    }

}
