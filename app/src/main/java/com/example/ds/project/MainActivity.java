package com.example.ds.project;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;

public class MainActivity extends AppCompatActivity {
    Intent intent1;
    Intent intent2;
    Intent intent3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        intent1 = new Intent(getApplicationContext(),RestListActivity.class);
        intent2 = new Intent(getApplicationContext(),JoinActivity.class);
        intent3 = new Intent(getApplicationContext(),LoginActivity.class);
    }

    public void onFood1Clicked(View view) {
        intent1.putExtra("genre","식사류");
        startActivity(intent1);
    }

    public void onFood2Clicked(View view) {
        intent1.putExtra("genre","카페/음료");
        startActivity(intent1);
    }

    public void onFood3Clicked(View view) {
        intent1.putExtra("genre","디저트");
        startActivity(intent1);
    }

    public void onJoinClicked(View view) {
        startActivity(intent2);
    }
    //타이틀바에 로그인버튼 넣기
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu,menu);
        return true;
    }

    //로그인눌러질때
    public boolean onOptionsItemSelected(MenuItem item){
        int id=item.getItemId();
        if(id==R.id.login){
            startActivity(intent3);
            return true;
        }
        return true;
    }
    //액션바 숨기기
    private void hideActionBar(){
        ActionBar actionBar = getSupportActionBar();

    }
}
