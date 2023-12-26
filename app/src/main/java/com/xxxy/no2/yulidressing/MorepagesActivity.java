package com.xxxy.no2.yulidressing;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

public class MorepagesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_morepages);


        //点击回到退回上一个页面
        ImageView iv_close = findViewById(R.id.iv_close);
        iv_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });


        //点击切换状态
        RelativeLayout btn_main_uploadImage = findViewById(R.id.btn_main_uploadImage);
        RelativeLayout btn_main_diy = findViewById(R.id.btn_main_diy);
        RelativeLayout btn_main_share = findViewById(R.id.btn_main_share);
        RelativeLayout btn_main_wardrobe = findViewById(R.id.btn_main_wardrobe);
        RelativeLayout btn_main_outfits = findViewById(R.id.btn_main_outfits);
        //
        btn_main_uploadImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent = new Intent(MorepagesActivity.this, FashionCirclesActivity.class);
//                startActivity(intent);
            }
        });

        btn_main_diy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MorepagesActivity.this, UserDiyActivity.class);
                startActivity(intent);
            }
        });

        btn_main_share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MorepagesActivity.this, PostActivity.class);
                startActivity(intent);
            }
        });

        btn_main_wardrobe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MorepagesActivity.this, PersonalActivity.class);
                startActivity(intent);
            }
        });

        btn_main_outfits.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MorepagesActivity.this, MyselfActivity.class);
                startActivity(intent);
            }
        });
    }
    public void onButtonClick(View view) {
        RelativeLayout relativeLayout = (RelativeLayout) view;
        String color = "#66CCFF";
        relativeLayout.setBackgroundColor(Color.parseColor(color));
    }
}