package com.xxxy.no2.yulidressing;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.xxxy.no2.yulidressing.fragment.IndexAiFragment;
import com.xxxy.no2.yulidressing.fragment.IndexFxFragment;
import com.xxxy.no2.yulidressing.model.IndexListViewModel;
import com.xxxy.no2.yulidressing.model.ListVewListViewModel;
import com.xxxy.no2.yulidressing.utils.Constant;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class IndexActivity extends AppCompatActivity implements View.OnClickListener{
IndexAiFragment aifragment;
IndexFxFragment fxfragment;
private  TextView ai;
private  TextView fx;

List<IndexListViewModel> list;
    private Handler handler =new Handler(){
        @Override
        public void handleMessage(@NonNull Message msg) {
            switch (msg.what){
                case 1:
                    //拿到json数据
                    String json_date= (String) msg.obj;
                    //把对象转换成List<JsonRootBean>
                    list = new Gson().fromJson(json_date,new TypeToken<List<IndexListViewModel>>(){}.getType());

                    if(list!=null){
                        //把数据利用Bundle传给fragment
                        ininView(list);

                    }
            }
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);

        setContentView(R.layout.activity_index);
        request_date();

    }

    private void request_date() {
        String url= Constant.WEB_SITE+Constant.SHOP_URL; // 你的请求地址

        // 在这里实现你的请求逻辑，例如使用OkHttp库发起网络请求获取数据，然后将数据设置到ListView中
        Request request=new Request.Builder().url(url).build();
        OkHttpClient okHttpClient=new OkHttpClient();
        Call call=okHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                Message message=new Message();
                message.what=1;
                String date_json = response.body().string();
                message.obj=date_json;
                handler.sendMessage(message);
            }
        });


    }


    private void ininView(List<IndexListViewModel> list1) {
        ai=findViewById(R.id.tv_ai);
        fx=findViewById(R.id.tv_fx);
        aifragment=new IndexAiFragment().addDate(list1);
        ai.setOnClickListener(this::onClick);
        fx.setOnClickListener(this::onClick);
        setDefaultFragment();
    }

    private void setDefaultFragment() {
        FragmentManager manager=getFragmentManager();
        FragmentTransaction fragmentTransaction=manager.beginTransaction();
        fragmentTransaction.replace(R.id.fl_fragment1, aifragment);
        fragmentTransaction.commit();
    }

    @Override
    public void onClick(View view) {
        FragmentManager fm = getFragmentManager();
        // 开启Fragment事务
        FragmentTransaction transaction = fm.beginTransaction();
        if (view.getId()==R.id.tv_ai){
           if (aifragment==null){
               aifragment=new IndexAiFragment();
           }
           transaction.replace(R.id.tv_fx,fxfragment);
        } else if (view.getId()==R.id.tv_fx) {
            if (fxfragment==null){
                fxfragment=new IndexFxFragment();
            }
            transaction.replace(R.id.tv_fx,fxfragment);
        }
        transaction.commit();
    }
}