package com.example.ds.project;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.TextView;

public class ReviewListView extends LinearLayout {

    TextView userName;
    TextView reviewContent;
    public ReviewListView(Context context) {
        super(context);
        init(context);
    }

    public ReviewListView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public void init(Context context) {
        //food_item.xml을 대상으로 인플레이션하는 코드 작성
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.review_item, this, true);

        userName = (TextView)findViewById(R.id.userName);
        reviewContent = (TextView)findViewById(R.id.reviewContent);
    }


    public void setUserName(String name) {
        userName.setText(name);
    }

    public void setReviewContent(String genre) {
        reviewContent.setText(String.valueOf(genre));
    }
}
