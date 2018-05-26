package com.example.ds.project;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class RandomActivity extends AppCompatActivity {
    TextView title;
    Intent intent1;
    String str;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_random);
        title = (TextView)findViewById(R.id.title);
        intent1 = getIntent();
        str=intent1.getStringExtra("genre");
        title.setText(str);
    }
}
