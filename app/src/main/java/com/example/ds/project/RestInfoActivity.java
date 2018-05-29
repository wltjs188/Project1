package com.example.ds.project;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class RestInfoActivity extends AppCompatActivity {
    TextView restName, restGenre;
    LinearLayout menuLayout, locationLayout, reviewLayout;
    Intent intent;
    ListView menuListView;
    MenuAdapter menuAdapter;

    EditText reviewEt;

    String userid;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rest_info);
        restName = (TextView)findViewById(R.id.restName);
        restGenre = (TextView)findViewById(R.id.restGenre);
        menuLayout = (LinearLayout)findViewById(R.id.menuLayout);
        locationLayout = (LinearLayout)findViewById(R.id.locationLayout);
        reviewLayout = (LinearLayout) findViewById(R.id.reviewLayout); 

        intent = getIntent();
        String name, genre;
        name = intent.getStringExtra("restName");
        userid = intent.getStringExtra("userid");

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
        
        //메뉴 리스트 구현
        menuListView = (ListView)findViewById(R.id.menuList);
        menuAdapter = new MenuAdapter();

        menuAdapter.addItem(new MenuItem("메뉴1", "6000원"));
        menuAdapter.addItem(new MenuItem("메뉴2", "7000원"));
        menuAdapter.addItem(new MenuItem("메뉴3", "5000원"));
        
        menuListView.setAdapter(menuAdapter);

        //지도 구현

        //리뷰 구현
        reviewEt = (EditText)findViewById(R.id.reviewInput);



    }

    public void onMenuBtnClicked(View view) {
        menuLayout.setVisibility(View.VISIBLE);
        locationLayout.setVisibility(View.INVISIBLE);
        reviewLayout.setVisibility(View.INVISIBLE);
    }

    public void onLocationBtnClicked(View view) {
        menuLayout.setVisibility(View.INVISIBLE);
        locationLayout.setVisibility(View.VISIBLE);
        reviewLayout.setVisibility(View.INVISIBLE);
    }

    public void onReviewBtnClicked(View view) {
        menuLayout.setVisibility(View.INVISIBLE);
        locationLayout.setVisibility(View.INVISIBLE);
        reviewLayout.setVisibility(View.VISIBLE);
    }

    public void onReviewUpdateBtnClicked(View view) {
        if(userid != null) {
            String rv = reviewEt.getText().toString();


        }
        else {
            Toast.makeText(getApplicationContext(),"로그인을 하셔야 리뷰를 등록 할 수 있습니다.", Toast.LENGTH_LONG).show();
        }
    }

    class MenuAdapter extends BaseAdapter {
        ArrayList<MenuItem> items = new ArrayList<MenuItem>();
        @Override
        public int getCount() {
            return items.size();
        }

        @Override
        public Object getItem(int position) {
            return items.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        public void addItem(MenuItem item) {
            items.add(item);
        }

        @Override // 제일 중요
        public View getView(int position, View correntView, ViewGroup parent) {
            MenuListView view = new MenuListView(getApplicationContext());

            MenuItem item = items.get(position);
            view.setMenuName(item.getMenuName());
            view.setMenuPrice(item.getMenuPrice());
            return view;
        }
    }
}
