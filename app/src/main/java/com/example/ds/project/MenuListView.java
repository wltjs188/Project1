package com.example.ds.project;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MenuListView extends LinearLayout {
    TextView menuName;
    TextView menuPrice;
    public MenuListView(Context context) {
        super(context);
        init(context);
    }

    public MenuListView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public void init(Context context) {
        //food_item.xml을 대상으로 인플레이션하는 코드 작성
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.menu_item, this, true);

        menuName = (TextView)findViewById(R.id.menuName);
        menuPrice = (TextView)findViewById(R.id.menuPrice);
    }

    public void setMenuName(String name) {
        menuName.setText(name);
    }

    public void setMenuPrice(String genre) {
        menuPrice.setText(String.valueOf(genre));
    }

}
