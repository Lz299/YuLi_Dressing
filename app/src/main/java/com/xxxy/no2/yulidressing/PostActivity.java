package com.xxxy.no2.yulidressing;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.xxxy.no2.yulidressing.Adapter.MyselfAdapter;
import com.xxxy.no2.yulidressing.Adapter.PostAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PostActivity extends AppCompatActivity {
    private int[] post_imgs = new int[]{R.drawable.show2,R.drawable.show1,R.drawable.show3};
    RecyclerView post_rv_imgs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post);
        init();


        //下拉列表选择
        Spinner sp_type = findViewById(R.id.sp_type);
        sp_type.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String str = adapterView.getSelectedItem().toString();
                Toast.makeText(PostActivity.this,str, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        //返回上一个页面
        ImageView post_iv_bankButton = findViewById(R.id.post_iv_bankButton);
        post_iv_bankButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    private  void init(){
        post_rv_imgs = findViewById(R.id.post_rv_imgs);
        PostAdapter adapter = new PostAdapter(getData(),this);
        LinearLayoutManager layoutManager =  new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        post_rv_imgs.setLayoutManager(layoutManager);
        post_rv_imgs.setAdapter(adapter);
    }


    private List<Map<String,Object>> getData(){
        List<Map<String,Object>> myList=new ArrayList<Map<String,Object>>();
        for (int i=0;i<post_imgs.length;i++){
            Map<String,Object> map=new HashMap<>();
            map.put("addImgs",post_imgs[i]);

            myList.add(map);
        }

        return myList;
    }
}