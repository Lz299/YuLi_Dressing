package com.xxxy.no2.yulidressing;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.xxxy.no2.yulidressing.model.Users;
import com.xxxy.no2.yulidressing.utils.Constant;
import com.xxxy.no2.yulidressing.utils.SharedPreferencesUtils;

import java.io.IOException;
import java.util.Map;

import okhttp3.Call;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class LoginActivity extends AppCompatActivity {

    Users user=new Users();
    private Handler handler1 =new Handler(){
        @Override
        public void handleMessage(@NonNull Message msg) {
            switch (msg.what){
                case 1:
                    //拿到json数据
                    String json_shop= (String) msg.obj;
                    user = new Gson().fromJson(json_shop,new TypeToken<Users>(){}.getType());

                    if (user!=null) {
                        Toast.makeText(LoginActivity.this,user.toString(), Toast.LENGTH_SHORT).show();
                        Toast.makeText(LoginActivity.this, "登录成功", Toast.LENGTH_SHORT).show();
                        SharedPreferencesUtils share=new SharedPreferencesUtils();
                        share.saveUserInfo(LoginActivity.this,user);
                        Intent intent = new Intent(LoginActivity.this, IndexActivity.class);
                        startActivity(intent);
                        finish();
                    }else if (json_shop==null){
                        Toast.makeText(LoginActivity.this, "账号密码错误", Toast.LENGTH_SHORT).show();
                    }
                    break;
                case 3:
                    Toast.makeText(LoginActivity.this, "账号密码错误", Toast.LENGTH_SHORT).show();
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        SharedPreferencesUtils utils=new SharedPreferencesUtils();
        Map<String, String> map= utils.getUser(LoginActivity.this);
        if (map.get("logined").toString().equals("true")){
            startActivity(new Intent(this,IndexActivity.class));
            finish();
        }

        EditText editText=findViewById(R.id.login_username);
        EditText password=findViewById(R.id.login_pwd);
        //  切换登录方式
        TextView login_phoneButton= findViewById(R.id.login_phoneButton);
        Button button=findViewById(R.id.btn_login);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dengLu(editText.getText().toString(),password.getText().toString());
            }
        });


        login_phoneButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

    }

    private void dengLu(String username,String password) {
        String url= Constant.WEB_SITE+"UserServlet"; // 你的请求地址
        RequestBody body=new FormBody.Builder()
                .add("username",username)
                .add("password",password)
                .build();
        Request request=new Request.Builder()
                .url(url)
                .post(body)
                .build();
        OkHttpClient okHttpClient=new OkHttpClient();
        Call call=okHttpClient.newCall(request);
        //请求回调
        call.enqueue(new okhttp3.Callback() {

            //失败时的方法
            @Override
            public void onFailure(Call call, IOException e) {
                Message message=new Message();
                message.what=3;
                handler1.sendMessage(message);
            }
            //成功
            @Override
            public void onResponse(Call call, Response response) throws IOException {

                Message message=new Message();

                String shop_json = response.body().string();
                message.obj=shop_json;
                if (shop_json.equals("")){
                    message.what=3;
                } else {message.what=1;}
                handler1.sendMessage(message);
            }
        });
    }





}