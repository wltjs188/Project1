package com.example.ds.project;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {
    Intent intent1,intent2,intent3;
    FirebaseUser user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        intent1 = new Intent(getApplicationContext(),RestListActivity.class);
        intent2 = new Intent(getApplicationContext(),JoinActivity.class);
        intent3 = new Intent(getApplicationContext(),LoginActivity.class);
        intent3.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        user = FirebaseAuth.getInstance().getCurrentUser();


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
        user = FirebaseAuth.getInstance().getCurrentUser();
        getMenuInflater().inflate(R.menu.menu,menu);
        return true;
    }


    //로그인눌러질때
    public boolean onOptionsItemSelected(MenuItem item){
        int id=item.getItemId();
        if(id==R.id.login){
            if (user != null) {
                Toast.makeText(getApplicationContext(),"이미 로그인 상태입니다.",Toast.LENGTH_LONG).show();
                startActivityForResult(intent3,1);
            } else {
                startActivityForResult(intent3,1);
            }
            return true;
        }
        return true;
    }
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        Toast.makeText(getApplicationContext(),"로그인 완료",Toast.LENGTH_LONG).show();
        super.onActivityResult(requestCode, resultCode, data);
    }


    public void a(View view) {
        FirebaseAuth.getInstance().signOut();
    }
}
