package com.example.ds.project;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.view.menu.MenuAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class RestInfoActivity extends AppCompatActivity {
    TextView restName, restGenre;
    LinearLayout menuLayout, locationLayout, reviewLayout;
    Intent intent;
    ListView menuListView, reviewListView;
    MenuAdapter menuAdapter;
    ReviewAdapter reviewAdapter;
    FirebaseDatabase database;
    DatabaseReference databaseReference;
    EditText reviewEt;
    ReviewItem reviewItem;
    MenuItem menu;
    int menuId;

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
        database=FirebaseDatabase.getInstance();
        databaseReference = database.getReference();

        intent = getIntent();
        String name, genre;

        name = intent.getStringExtra("restName");
        menuId=intent.getIntExtra("menuId",1);
        userid = "유저";//intent.getStringExtra("userid");
        restName.setText(intent.getStringExtra("name"));
        restGenre.setText(intent.getStringExtra("genre"));
        
        //메뉴 리스트 구현
        menuListView = (ListView)findViewById(R.id.menuList);
        menuAdapter = new MenuAdapter();
        databaseReference.child("menu").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                menuAdapter.clear();
                for(DataSnapshot snapshot : dataSnapshot.getChildren()){
                    menu=snapshot.getValue(MenuItem.class);
                    if(menuId==menu.menuId){
                        menuAdapter.addItem(menu);
                        menuListView.setAdapter(menuAdapter);
                    }

                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });
        //지도 구현

        //리뷰 구현
        reviewEt = (EditText)findViewById(R.id.reviewInput);
        reviewListView = (ListView)findViewById(R.id.listReview) ;
        reviewAdapter = new ReviewAdapter();
        databaseReference.child("review").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                reviewAdapter.clear();
                for(DataSnapshot snapshot : dataSnapshot.getChildren()){
                     reviewItem = snapshot.getValue(ReviewItem.class);
                     if(menuId==reviewItem.menuId) {
                         reviewAdapter.addItem(reviewItem);
                         reviewListView.setAdapter(reviewAdapter);
                     }
                }
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });
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
        //if(userid != null) {
            String rv = reviewEt.getText().toString();
            if(rv=="" || rv==null) {
                Toast.makeText(getApplicationContext(), "내용을 입력해주세요", Toast.LENGTH_LONG).show();
            }
            else {
                Review review = new Review(userid, rv,menuId);
                databaseReference.child("review").push().setValue(review);
            }
       // }
       // else {
           // Toast.makeText(getApplicationContext(),"로그인을 하셔야 리뷰를 등록 할 수 있습니다.", Toast.LENGTH_LONG).show();
       // }

        reviewEt.setText("");
    }

    class MenuAdapter extends BaseAdapter {
        ArrayList<MenuItem> items = new ArrayList<MenuItem>();
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
    class ReviewAdapter extends BaseAdapter {
        ArrayList<ReviewItem> items = new ArrayList<ReviewItem>();
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

        @Override
        public long getItemId(int position) {
            return position;
        }

        public void addItem(ReviewItem item) {
            items.add(item);
        }

        @Override // 제일 중요
        public View getView(int position, View correntView, ViewGroup parent) {
            ReviewListView view = new ReviewListView(getApplicationContext());
            ReviewItem item = items.get(position);
            view.setUserName(item.getUsername());
            view.setReviewContent(item.getReviewContent());
            return view;
        }
    }
}
