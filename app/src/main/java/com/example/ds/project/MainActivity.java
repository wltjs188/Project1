package com.example.ds.project;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class MainActivity extends AppCompatActivity {
    Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        intent = new Intent(getApplicationContext(),RestListActivity.class);
    }

    public void onFood1Clicked(View view) {
        intent.putExtra("genre","식사류");
        startActivity(intent);
    }

    public void onFood2Clicked(View view) {
        intent.putExtra("genre","카페/음료");
        startActivity(intent);
    }

    public void onFood3Clicked(View view) {
        intent.putExtra("genre","디저트");
        startActivity(intent);
    }
}
