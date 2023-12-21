package com.xxxy.no2.yulidressing;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.xxxy.no2.yulidressing.Fragment.CommunityFragment;
import com.xxxy.no2.yulidressing.Fragment.IndexFragment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class IndexActivity extends AppCompatActivity {

    private IndexFragment indexFragment;
    private CommunityFragment communityFragment;
    private BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_index);

        //点击加号跳加号页面
        ImageView iv_addButton = findViewById(R.id.iv_addButton);
        iv_addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(IndexActivity.this, MorepagesActivity.class);
                startActivity(intent);
            }
        });

        //导航栏控件使用
        bottomNavigationView = findViewById(R.id.bnv_Bottonview);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if (item.getItemId()==R.id.index_fm_btn){
                    selectedFragment(0);
                }else if (item.getItemId()==R.id.comm_fm_btn){
                    selectedFragment(1);
                }

                return true;
            }
        });
        //默认选中实现方法
        selectedFragment(0);
    }


    //默认选中实现方法
    private void selectedFragment(int position){
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        hideFragment(fragmentTransaction);
        //这里已经选中给页面默认
        if (position==0){
            if (indexFragment == null){
                indexFragment = new IndexFragment();
                fragmentTransaction.add(R.id.fragment_zhuye,indexFragment);
            }else {
                fragmentTransaction.show(indexFragment);
            }
        } else if (position == 1){
            if (communityFragment==null){
                communityFragment = new CommunityFragment();
                fragmentTransaction.add(R.id.fragment_zhuye,communityFragment);
            }else {
                fragmentTransaction.show(communityFragment);
            }
        }

        //一定要提交
        fragmentTransaction.commit();
    }

    //点击隐藏其他Fragmen
    private void hideFragment(FragmentTransaction fragmentTransaction){
        if (indexFragment!=null){
            fragmentTransaction.hide(indexFragment);
        }
        if (communityFragment!=null){
            fragmentTransaction.hide(communityFragment);
        }
    }

}