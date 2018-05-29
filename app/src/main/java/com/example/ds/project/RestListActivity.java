package com.example.ds.project;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class RestListActivity extends AppCompatActivity {
    TextView title;
    String str;
    Intent intent1;
    Intent intent2;
    Intent intent3;
    RestItem restItem;
    ListView restListView;
    RestAdapter restAdapter;
    FirebaseDatabase database;
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
        database=FirebaseDatabase.getInstance();
        database.getReference().child("rest").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()){
                    restItem=snapshot.getValue(RestItem.class);
                    if(str.equals(restItem.getGenre())) {
                        restAdapter.addItem(restItem);
                        restListView.setAdapter(restAdapter);
                    }
                }
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });



        restListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                intent3 = new Intent(getApplicationContext(), RestInfoActivity.class);
                if (str.equals("식사류")) {
                    intent3.putExtra("restName", "사리원");
                } else if (str.equals("카페/음료")) {
                    intent3.putExtra("restName", "블랙다운");
                } else if (str.equals("디저트")) {
                    intent3.putExtra("restName", "달달");
                } // 이부분도 추후 데이터베이스 연동하면 수정, 지금은 RestInfoActivity확인을 위함
                startActivityForResult(intent3, 18);
            }
        });

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
            view.setRestName(item.name);
            view.setRestGenre(item.genre);
//            view.setImageView(item.getResid());
            return view;
        }
    }
}
