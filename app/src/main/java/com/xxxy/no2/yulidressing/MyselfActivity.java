package com.xxxy.no2.yulidressing;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import com.xxxy.no2.yulidressing.adapter.MyselfAdapter;
import com.xxxy.no2.yulidressing.model.Posts;
import com.xxxy.no2.yulidressing.utils.Constant;
import com.xxxy.no2.yulidressing.utils.SharedPreferencesUtils;


import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class MyselfActivity extends AppCompatActivity {

    RecyclerView show_RecyclerView;
    List<Posts> postsList = new ArrayList<>();
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            //展示个人信息中的的衣库
            if (msg.what == 1){
                String rep = (String) msg.obj;//获取json串
                Gson gson = new Gson();
                postsList = gson.fromJson(rep,new TypeToken<List<Posts>>(){}.getType());
                Log.d("aaaaaaaaaaaahandleMessage: ",postsList+"");
                init();
            }
            if (msg.what == 2){
                Log.d("222222handleMessage: ","失败");
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_myself);
        request_postlist();
        //返回上一页
        ImageView user_iv_bankButton = findViewById(R.id.user_iv_bankButton);
        user_iv_bankButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }
    SharedPreferencesUtils utils=new SharedPreferencesUtils();

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
        Log.d( "ccccccccccccgetData: ",postsList+"");
        for (int i=0;i<postsList.size();i++){
            Map<String,Object> map=new HashMap<>();
            map.put("itemImg",postsList.get(i).getPosts_content());
            map.put("itemTitle",postsList.get(i).getPosts_title());
            map.put("itemTouxiang",utils.getUser(MyselfActivity.this).get("users_img"));//头像网络请求
            map.put("itemName",postsList.get(i).getUsers().getUsername());
            map.put("itemLike",postsList.get(i).getUsers().getUsers_id());
            myList.add(map);
        }

        return myList;
    }

    private void request_postlist(){
        String strURL = Constant.WEB_SITE+"/PostsServlet";
        RequestBody body=new FormBody.Builder()
                .add("find_posts_id",utils.getUser(MyselfActivity.this).get("users_id"))
                .build();
        Request request = new Request.Builder().url(strURL).post(body).build();
        OkHttpClient okHttpClient = new OkHttpClient();
        Call call = okHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Message message = new Message();
                message.what = 2;
                message.obj = e.getMessage();
                //通过handler向主线程发送json数据
                handler.sendMessage(message);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String rep = response.body().string();
                Message message = new Message();
                message.what = 1;
                message.obj = rep;
                handler.sendMessage(message);
            }
        });
    }

}