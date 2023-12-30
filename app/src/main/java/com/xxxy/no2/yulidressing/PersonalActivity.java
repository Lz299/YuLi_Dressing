package com.xxxy.no2.yulidressing;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.xxxy.no2.yulidressing.Adapter.PersonalAdapter;
import com.xxxy.no2.yulidressing.Adapter.PersonalAdapter2;
import com.xxxy.no2.yulidressing.Adapter.PersonalAdapter3;
import com.xxxy.no2.yulidressing.Adapter.PersonalAdapter4;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PersonalActivity extends AppCompatActivity {

    //coat
    int[] images=new int[]{R.drawable.kou_c1,R.drawable.kou_c2,R.drawable.kou_c3,R.drawable.kou_c4,R.drawable.kou_c5
    };
    RecyclerView coat;

    //trousers
    int[] images2=new int[]{R.drawable.kou_k1,R.drawable.kou_k2,R.drawable.kou_k3,R.drawable.kou_k4
    };
    RecyclerView trousers;

    int[] images3=new int[]{R.drawable.kou_c1,R.drawable.kou_c2,R.drawable.kou_c3,R.drawable.kou_c4,R.drawable.kou_c5
    };
    RecyclerView shoe;


    int[] images4=new int[]{R.drawable.kou_c1,R.drawable.kou_c2,R.drawable.kou_c3,R.drawable.kou_c4,R.drawable.kou_c5
    };
    RecyclerView hat;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal);
        init();
        //返回按钮
        ImageView imageView = findViewById(R.id.imageView);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });


    }
    private  void init(){
        //coat

        coat=findViewById(R.id.rv_coat);
        //2.建立适配器
        PersonalAdapter adapter=new PersonalAdapter(getData(),this);
        LinearLayoutManager layoutManager =  new LinearLayoutManager(this);
        coat.setLayoutManager(layoutManager);
        //3.设置适配器
        coat.setAdapter(adapter);
        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);


        trousers=findViewById(R.id.rv_trousers);
        //2.建立适配器
        PersonalAdapter2 adapter2=new PersonalAdapter2(getData2(),this);
        LinearLayoutManager layoutManager2 =  new LinearLayoutManager(this);
        trousers.setLayoutManager(layoutManager2);
        //3.设置适配器
        trousers.setAdapter(adapter2);
        layoutManager2.setOrientation(LinearLayoutManager.HORIZONTAL);


        shoe=findViewById(R.id.rv_shoe);
        //2.建立适配器
        PersonalAdapter3 adapter3=new PersonalAdapter3(getData3(),this);
        LinearLayoutManager layoutManager3 =  new LinearLayoutManager(this);
        shoe.setLayoutManager(layoutManager3);
        //3.设置适配器
        shoe.setAdapter(adapter3);
        layoutManager3.setOrientation(LinearLayoutManager.HORIZONTAL);


        hat=findViewById(R.id.rv_hat);
        //2.建立适配器
        PersonalAdapter4 adapter4=new PersonalAdapter4(getData4(),this);
        LinearLayoutManager layoutManager4 =  new LinearLayoutManager(this);
        hat.setLayoutManager(layoutManager4);
        //3.设置适配器
        hat.setAdapter(adapter4);
        layoutManager4.setOrientation(LinearLayoutManager.HORIZONTAL);

    }
    //1.建立数据源第二步

    //coat
    private List<Map<String,Object>> getData(){
        List<Map<String,Object>> data=new ArrayList<Map<String,Object>>();
        for (int i=0;i<images.length;i++){
            Map<String,Object> map=new HashMap<>();
            map.put("image",images[i]);
            data.add(map);
        }
        return data;
    }
    private List<Map<String,Object>> getData2(){
        List<Map<String,Object>> data2=new ArrayList<Map<String,Object>>();
        for (int i=0;i<images2.length;i++){
            Map<String,Object> map=new HashMap<>();
            map.put("image",images2[i]);
            data2.add(map);
        }
        return data2;
    }
    private List<Map<String,Object>> getData3(){
        List<Map<String,Object>> data3=new ArrayList<Map<String,Object>>();
        for (int i=0;i<images4.length;i++){
            Map<String,Object> map=new HashMap<>();
            map.put("image",images3[i]);
            data3.add(map);
        }
        return data3;
    }
    private List<Map<String,Object>> getData4(){
        List<Map<String,Object>> data4=new ArrayList<Map<String,Object>>();
        for (int i=0;i<images4.length;i++){
            Map<String,Object> map=new HashMap<>();
            map.put("image",images4[i]);
            data4.add(map);
        }
        return data4;
    }
}