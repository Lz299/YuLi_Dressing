package com.xxxy.no2.yulidressing;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.xxxy.no2.yulidressing.Adapter.MyselfAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MyselfActivity extends AppCompatActivity {
   private int[] imgs_myself_img = new int[]{R.drawable.show1,R.drawable.show2,R.drawable.show3,R.drawable.show2,R.drawable.show1};
   private String[] str_myself_title = new String[]{"关于今日的穿搭意见，回头率一定爆表哈哈哈哈哈，整条街最亮的崽","今天的酷酷穿搭","分享今日穿搭","关于今日的穿搭意见，回头率一定爆表哈哈哈哈哈，整条街最亮的崽","今天的酷酷穿搭"};
   private String[] str_myself_name = new String[]{"是吃香菜","韩大设计师","灰原丽香","是烦烦烦","Faiwj"};
   private int[] imgs_myself_touxiang = new int[]{R.drawable.show1,R.drawable.show2,R.drawable.show3,R.drawable.show1,R.drawable.show2};
   private int[] likes_myself_like = new int[]{12,5,7,88,9};

    RecyclerView show_RecyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_myself);
        init();

        //返回上一页
        ImageView user_iv_bankButton = findViewById(R.id.user_iv_bankButton);
        user_iv_bankButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }


    //显示主页列表
    private  void init(){
        show_RecyclerView = findViewById(R.id.show_RecyclerView);
        //2.建立适配器
        MyselfAdapter adapter = new MyselfAdapter(getData(),this);
        LinearLayoutManager layoutManager =  new LinearLayoutManager(this);
        show_RecyclerView.setLayoutManager(new GridLayoutManager(this,2));
        show_RecyclerView.setAdapter(adapter);
    }
    private List<Map<String,Object>> getData(){
        List<Map<String,Object>> myList=new ArrayList<Map<String,Object>>();
        for (int i=0;i<str_myself_title.length;i++){
            Map<String,Object> map=new HashMap<>();
            map.put("itemImg",imgs_myself_img[i]);
            map.put("itemTitle",str_myself_title[i]);
            map.put("itemTouxiang",imgs_myself_touxiang[i]);
            map.put("itemName",str_myself_name[i]);
            map.put("itemLike",likes_myself_like[i]);
            myList.add(map);
        }
        return myList;
    }

}