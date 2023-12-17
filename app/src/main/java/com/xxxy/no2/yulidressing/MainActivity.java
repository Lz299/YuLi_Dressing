package com.xxxy.no2.yulidressing;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.DialogInterface;
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
import com.xxxy.no2.yulidressing.utils.TimeCount;

import java.io.IOException;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {
    EditText editText_phone;

    EditText yzm;
    Users user=new Users();



    //创建handler
    private Handler handler =new Handler(){
        @Override
        public void handleMessage(@NonNull Message msg) {
            switch (msg.what){
                case 1:
                    //拿到json数据
                    String json_shop= (String) msg.obj;
                    user = new Gson().fromJson(json_shop,new TypeToken<Users>(){}.getType());
                    break;
                case 3:
                    String zccg= (String) msg.obj;
                    if(zccg.equals("1")){
                        Toast.makeText(MainActivity.this,"注册成功",Toast.LENGTH_SHORT).show();
                    }
                    break;

            }
        }
    };






    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText_phone=findViewById(R.id.login_usersPhone);
        yzm=findViewById(R.id.login_usersYanzhen);
        TextView btn_yzmButton = findViewById(R.id.btn_yzmButton);


        btn_yzmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TimeCount helper = new TimeCount(MainActivity.this,6000,btn_yzmButton);
                helper.start();
                //获取验证码
            }
        });






        //登录
        Button btn_login = findViewById(R.id.btn_mainlogin);
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this, user.toString(), Toast.LENGTH_SHORT).show();

               /* Intent intent = new Intent(MainActivity.this, IndexActivity.class);
                startActivity(intent);*/
            }
        });




        //注册
        Button btn_zhuce = findViewById(R.id.btn_zhuce);
        btn_zhuce.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                zuceClick();
            }
        });



        //切换登录方式
        TextView login_zhanghaoButton= findViewById(R.id.login_zhanghaoButton);
        login_zhanghaoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });


    }



    public void zuceClick(){

        AlertDialog mDialog = new AlertDialog.Builder(MainActivity.this).show();
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setIcon(R.drawable.logo);
        builder.setTitle("用户注册");
        final View view = getLayoutInflater().inflate(R.layout.zhuce,null);
        mDialog.getWindow().setBackgroundDrawableResource(android.R.color.white); // 修改这里
        builder.setView(view);

        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                EditText et_phone = (EditText) view.findViewById(R.id.editText_phone);
                EditText et_username = (EditText) view.findViewById(R.id.editText_username);
                EditText et_password = (EditText) view.findViewById(R.id.editText_password);
                EditText et_password2 = (EditText) view.findViewById(R.id.editText_password2);
                TextView tv_hqyzm=(TextView) view.findViewById(R.id.tv_yzm);
                String phone = et_phone.getText().toString();
                String username = et_username.getText().toString();
                String password = et_password.getText().toString();
                String password2 = et_password2.getText().toString();
                String hqyzm=tv_hqyzm.getText().toString();

                if (phone.equals("") || username.equals("") || password.equals("") || password2.equals("")) {
                    Toast.makeText(MainActivity.this, "输入有误", Toast.LENGTH_SHORT).show();
                } else if (!phone.matches("^1[3-9]\\d{9}$")) {
                    Toast.makeText(MainActivity.this, "手机号有误", Toast.LENGTH_SHORT).show();
                } else if (!password.equals(password2)) {
                    Toast.makeText(MainActivity.this, "两次密码不一致", Toast.LENGTH_SHORT).show();
                } else if (hqyzm.equals("")) {
                    Toast.makeText(MainActivity.this, "验证码不能为空", Toast.LENGTH_SHORT).show();
                } else {

                    Users users=new Users();
                    users.setUsername(username);
                    users.setPassword(password2);
                    users.setPhone(phone);
                Toast.makeText(MainActivity.this, phone+"-"+username+"-"+password+"-"+password2, Toast.LENGTH_SHORT).show();

                TimeCount helper = new TimeCount(view.getContext(),6000,tv_hqyzm);
                helper.start();
                request_zuce(users);
                dialog.cancel();
            }
                }
        });

        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        builder.show();
    }






    private void request_dengLu(String phone) {
        String url= Constant.WEB_SITE+Constant.SHOP_URL; // 你的请求地址

        RequestBody body=new FormBody.Builder()
                .add("phone",phone)
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
            }
            //成功
            @Override
            public void onResponse(Call call, Response response) throws IOException {

                Message message=new Message();
                message.what=1;
                String shop_json = response.body().string();

                message.obj=shop_json;
                handler.sendMessage(message);
            }
        });
    }


    private void request_zuce(Users user1) {
        String url="http://10.138.24.219:8088/web_user_war/update";

        RequestBody body=new FormBody.Builder()
                .add("username",user1.getUsername())
                .add("password",user1.getPassword())
                .add("phone",user1.getPhone())
                .build();
        Request request=new Request.Builder()
                .url(url)
                .post(body)
                .build();
        OkHttpClient okHttpClient=new OkHttpClient();
        Call call=okHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Message message=new Message();
                message.what=4;
                message.obj=e.getMessage();
                handler.sendMessage(message);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String rep=response.body().string();
                Message message=new Message();
                message.what=3;
                message.obj=rep;
                Log.d("aaaaaaaaaaa",rep);
                handler.sendMessage(message);
            }
        });

    }

}