package com.example.ds.project;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

public class RestInfoActivity extends AppCompatActivity {
    TextView restName, restGenre;
    LinearLayout menuLayout, locationLayout, reviewLayout;
    Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rest_info);

        restName = (TextView)findViewById(R.id.restName);
        restGenre = (TextView)findViewById(R.id.restGenre);

        intent = getIntent();
        String name, genre;
        name = intent.getStringExtra("restname");

        if(name.equals("사리원")) {
            genre = "국밥";
            restName.setText(name);
            restGenre.setText(genre);
        } else if(name.equals("블랙다운")) {
            genre = "커피/음료";
            restName.setText(name);
            restGenre.setText(genre);
        } else if(name.equals("달달")) {
            genre = "빵/마카롱";
            restName.setText(name);
            restGenre.setText(genre);
        } //이부분도 데베 연동 후 수정 예정
    }

    public void onMenuBtnClicked(View view) {
    }

    public void onLocationBtnClicked(View view) {
    }

    public void onReviewBtnClicked(View view) {
    }
}
