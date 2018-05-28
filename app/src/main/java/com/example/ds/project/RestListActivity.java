package com.example.ds.project;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class RestListActivity extends AppCompatActivity {

    TextView title;
    String str;
    Intent intent1;
    Intent intent2;

    ListView restListView;
    RestAdapter restAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rest_list);
        title=(TextView)findViewById(R.id.title);
        intent1 =getIntent();
        str=intent1.getStringExtra("genre");
        title.setText(str);
        intent2 = new Intent(getApplicationContext(),RandomActivity.class);

        restListView = (ListView)findViewById(R.id.restListView);

        restAdapter = new RestAdapter();

        if(str.equals("식사류")) {
            restAdapter.addItem(new RestItem("사리원", "국밥", R.drawable.mainds)); // 추후 데이터베이스 연동하면 수정
            restListView.setAdapter(restAdapter);

        }
        else if(str.equals("카페/음료")) {
            restAdapter.addItem(new RestItem("블랙다운", "커피/음료", R.drawable.mainds));
            restListView.setAdapter(restAdapter);
       }
        else if(str.equals("디저트")) {
            restAdapter.addItem(new RestItem("달달", "빵/마카롱", R.drawable.mainds));
            restListView.setAdapter(restAdapter);
        }

    }

    public void onRancomClicked(View view) {
        intent2.putExtra("genre",str);
        startActivity(intent2);
    }
    class RestAdapter extends BaseAdapter {
        ArrayList<RestItem> items = new ArrayList<RestItem>();
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

        public void addItem(RestItem item) {
            items.add(item);
        }
        @Override // 제일 중요
        public View getView(int position, View correntView, ViewGroup parent) {
            RestListView view = new RestListView(getApplicationContext());

            RestItem item = items.get(position);
            view.setRestName(item.getRestName());
            view.setRestGenre(item.getRestGenre());
            view.setImageView(item.getResid());
            return view;
        }
    }

}
