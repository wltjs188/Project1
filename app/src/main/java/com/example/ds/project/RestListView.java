package com.example.ds.project;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class RestListView extends LinearLayout {
    ImageView imageView;
    TextView restName;
    TextView restGenre;

    public RestListView(Context context) {
        super(context);
        init(context);
    }

    public RestListView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public void init(Context context) {
        //food_item.xml을 대상으로 인플레이션하는 코드 작성
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.rest_item, this, true);

        restName = (TextView) findViewById(R.id.textRestName);
        restGenre = (TextView)findViewById(R.id.textRestGenre);
        imageView = (ImageView) findViewById(R.id.imgview);
    }

    public void setImageView(int resId) {
        imageView.setImageResource(resId);
    }

    public void setRestName(String name) {
        restName.setText(name);
    }

    public void setRestGenre(String genre) {
        restGenre.setText(String.valueOf(genre));
    }
}
