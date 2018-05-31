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

import com.bumptech.glide.Glide;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class RestListActivity extends AppCompatActivity {
    TextView title;
    String str,url;
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
                restAdapter.clear();
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
                String name=restAdapter.getName(position);
                String genre=restAdapter.getGenre(position);
                int menuId=restAdapter.getMenuId(position);
                url=restAdapter.getImageUrl(position);
                intent3.putExtra("name",name);
                intent3.putExtra("genre",genre);
                intent3.putExtra("menuId",menuId);
                intent3.putExtra("url",url);
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
        public void clear(){
            items.clear();
        }
        @Override
        public int getCount() {
            return items.size();
        }

        @Override
        public Object getItem(int position) {
            return items.get(position);
        }

        public String getName(int position){
            return items.get(position).getName();
        }
        public String getGenre(int position){
            return items.get(position).getGenre();
        }
        public int getMenuId(int position){
            return items.get(position).getMenuId();
        }
        @Override
        public long getItemId(int position) {
            return position;
        }
        public String getImageUrl(int positon){
            return items.get(positon).imageUrl;
        }
        public void addItem(RestItem item) {
            items.add(item);
        }
        @Override // 제일 중요
        public View getView(int position, View correntView, ViewGroup parent) {
            RestListView view = new RestListView(getApplicationContext());
            RestItem item = items.get(position);
            view.setRestName(item.name);
            view.setImageView(restAdapter.getImageUrl(position));
            return view;
        }
    }
}
