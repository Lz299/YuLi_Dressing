package com.xxxy.no2.yulidressing;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import com.xxxy.no2.yulidressing.utils.RemoveUtils;
import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
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
import com.xxxy.no2.yulidressing.utils.TimeCount;

import java.io.IOException;
import java.util.Map;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;


import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity implements  View.OnClickListener{
    EditText editText_phone;
    String randomCode;
    EditText login_usersYanzhen;
    Users user=new Users();
    Integer integer;
    TextView btn_yzmButton;
    Button btn_login;
    String flag;
    Timer timer;
    int count = 60;
    @SuppressLint("HandlerLeak")
    Handler handler = new Handler(){
        public void handleMessage(Message msg) {
            int tag = msg.what;
            switch (tag){
                case 1:
                    int arg = msg.arg1;
                    if(arg==1){
                        btn_yzmButton.setText("重新获取");
//计时结束停止计时把值恢复
                        count = 60;
                        timer.cancel();
                        btn_yzmButton.setEnabled(true);
                    }else{
                        btn_yzmButton.setText(count+"");
                    }
                    break;
                case 2:
                    Toast.makeText(MainActivity.this,"获取短信验证码成功",Toast.LENGTH_LONG).show();
                    break;
                case 3:
                    Log.i("Codr","获取短信验证码失败");
                    Toast.makeText(MainActivity.this,"获取短信验证码失败",Toast.LENGTH_LONG).show();
                    break;
                default:
                    break;
            }

        };
    };




    //创建handler
    private Handler handler1 =new Handler(){
        @Override
        public void handleMessage(@NonNull Message msg) {
            switch (msg.what){
                case 1:
                    //拿到json数据
                    String json_shop= (String) msg.obj;
                    user = new Gson().fromJson(json_shop,new TypeToken<Users>(){}.getType());
                    if (user!=null) {
                        Toast.makeText(MainActivity.this,"登录成功", Toast.LENGTH_SHORT).show();
                        SharedPreferencesUtils share=new SharedPreferencesUtils();
                        share.saveUserInfo(MainActivity.this,user);

                        Intent intent = new Intent(MainActivity.this, IndexActivity.class);
                        startActivity(intent);
                    }
                    break;
                case 3:
                    String zccg= (String) msg.obj;
                    if(zccg.equals("1")){
                        Toast.makeText(MainActivity.this,"注册成功",Toast.LENGTH_SHORT).show();
                    }
                    break;
                case 5:

                    String str= (String) msg.obj;
                    //将 JSON 对象或 JSON 数组转化为字符
                    flag=new Gson().fromJson(str,String.class);
                    Toast.makeText(MainActivity.this, "获取验证码成功", Toast.LENGTH_SHORT).show();
                    Log.d( "tttttttttttthandleMessage: ",flag);

                    break;

            }
        }
    };




    TextView tv_hqyzm;

    Handler handler2 = new Handler(){
        public void handleMessage(Message msg) {
            int tag = msg.what;
            switch (tag){
                case 1:
                    int arg = msg.arg1;
                    if(arg==1){
                        tv_hqyzm.setText("重新获取");
//计时结束停止计时把值恢复
                        count = 60;
                        timer.cancel();
                        tv_hqyzm.setEnabled(true);
                    }else{
                        tv_hqyzm.setText(count+"");
                    }
                    break;
                case 2:
                    Toast.makeText(MainActivity.this,"获取短信验证码成功",Toast.LENGTH_LONG).show();
                    break;
                case 3:
                    Log.i("Codr","获取短信验证码失败");
                    Toast.makeText(MainActivity.this,"获取短信验证码失败",Toast.LENGTH_LONG).show();
                    break;
                default:
                    break;
            }

        };
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SharedPreferencesUtils utils=new SharedPreferencesUtils();
        Map<String, String> map= utils.getUser(MainActivity.this);
        if (map.get("logined").toString().equals("true")){
            startActivity(new Intent(this,IndexActivity.class));
            finish();
        }
        init();

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
                finish();
            }
        });
    }


    private void init(){
        editText_phone = findViewById(R.id.login_usersPhone);
        login_usersYanzhen = findViewById(R.id.login_usersYanzhen);
        btn_yzmButton = findViewById(R.id.btn_yzmButton);
        btn_yzmButton.setOnClickListener(MainActivity.this::onClick);
        btn_login = findViewById(R.id.btn_mainlogin);
        btn_login.setOnClickListener(MainActivity.this::onClick);
    }
    @Override
    public void onClick(View view) {
        String phone = "";
        String code = "";
        int id = view.getId();
        if(id==R.id.btn_yzmButton) {
            phone = editText_phone.getText().toString().trim();
            if (!TextUtils.isEmpty(phone)) {
//倒计时
                DlCountdownStart();
                request_yzm(phone);
            } else {
                Toast.makeText(this, "请输入手机号码", Toast.LENGTH_LONG).show();
            }
        }
        if (id==R.id.btn_mainlogin) {
            phone = editText_phone.getText().toString().trim();
            code = login_usersYanzhen.getText().toString().trim();
            if (TextUtils.isEmpty(phone)) {
                Toast.makeText(this, "请输入手机号码", Toast.LENGTH_LONG).show();
            } else if (TextUtils.isEmpty(code)) {
                Toast.makeText(this, "请输入验证码", Toast.LENGTH_LONG).show();
            } else {
                if (code.equals(flag)) {
                    request_dengLu(phone, code);
                }else {
                    Toast.makeText(this, "验证码错误", Toast.LENGTH_SHORT).show();
                }
            }
        }

    }


    //倒计时函数
    private void DlCountdownStart(){
        btn_yzmButton.setEnabled(false);
        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                Message message = new Message();
                message.what = 1;
                message.arg1 = count;
                if(count!=0){
                    count--;
                }else {
                    return;
                }
                handler.sendMessage(message);
            }
        }, 1000,1000);
    }

    private void ZcCountdownStart(){
        btn_yzmButton.setEnabled(false);
        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                Message message = new Message();
                message.what = 1;
                message.arg1 = count;
                if(count!=0){
                    count--;
                }else {
                    return;
                }
                handler2.sendMessage(message);
            }
        }, 1000,1000);
    }





    Integer  integer2;
    public void zuceClick(){

        AlertDialog mDialog = new AlertDialog.Builder(MainActivity.this).show();
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setIcon(R.drawable.logo);
        builder.setTitle("用户注册");
        final View view = getLayoutInflater().inflate(R.layout.zhuce,null);
        mDialog.getWindow().setBackgroundDrawableResource(android.R.color.white); // 修改这里
        builder.setView(view);
        EditText et_phone = (EditText) view.findViewById(R.id.editText_phone);
        EditText et_username = (EditText) view.findViewById(R.id.editText_username);
        EditText et_password = (EditText) view.findViewById(R.id.editText_password);
        EditText et_password2 = (EditText) view.findViewById(R.id.editText_password2);
        EditText editText_yanzheng=(EditText) view.findViewById(R.id.editText_yanzheng);

        tv_hqyzm = (TextView) view.findViewById(R.id.tv_yzm);


        tv_hqyzm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (et_phone.getText().toString().equals("")){
                    Toast.makeText(MainActivity.this, "请填写手机号", Toast.LENGTH_SHORT).show();
                }else {
                    ZcCountdownStart();
                    request_yzm(et_phone.getText().toString());
                    Log.d( "ttttttttttttttonClick: ",et_phone.getText().toString());
                }
            }
        });

        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                String phone = et_phone.getText().toString();
                String username = et_username.getText().toString();
                String password = et_password.getText().toString();
                String password2 = et_password2.getText().toString();
                String yanzheng =editText_yanzheng.getText().toString();



                if (phone.equals("") || username.equals("") || password.equals("") || password2.equals("")) {
                    Toast.makeText(MainActivity.this, "输入有误", Toast.LENGTH_SHORT).show();
                } else if (!phone.matches("^1[3-9]\\d{9}$")) {
                    Toast.makeText(MainActivity.this, "手机号有误", Toast.LENGTH_SHORT).show();
                } else if (!password.equals(password2)) {
                    Toast.makeText(MainActivity.this, "两次密码不一致", Toast.LENGTH_SHORT).show();
                } else if (yanzheng.equals("")) {
                    Toast.makeText(MainActivity.this, "验证码不能为空", Toast.LENGTH_SHORT).show();
                } else {


                    if (yanzheng.equals(flag)) {
                        Users users=new Users();
                        users.setUsername(username);
                        users.setPassword(password2);
                        users.setPhone(phone);
                        Toast.makeText(MainActivity.this, phone+"-"+username+"-"+password+"-"+password2, Toast.LENGTH_SHORT).show();
                        request_zuce(users);
                    }else {
                        Toast.makeText(MainActivity.this, "验证码错误", Toast.LENGTH_SHORT).show();
                    }


                   mDialog.dismiss();

            }
                }
        });

        builder.setNegativeButton("取消", null);
        builder.show();
    }



    private void request_yzm(String phone) {
        String url= Constant.WEB_SITE+"UserServlet"; // 你的请求地址
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
                Log.d("onResponse: aaaaaaaaaaaaaaaaaaaaa","失败");
            }
            //成功
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                Message message=new Message();
                message.what=5;
                String shop_json = response.body().string();
                message.obj=shop_json;
                handler1.sendMessage(message);
            }
        });
    }




    private void request_dengLu(String phone,String code) {
        String url= Constant.WEB_SITE+"UserServlet"; // 你的请求地址
        RequestBody body=new FormBody.Builder()
                .add("phone",phone)
                .add("code",code)
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
                handler1.sendMessage(message);
            }
        });
    }


    private void request_zuce(Users user1) {
        String url=Constant.WEB_SITE+"ZhuCeServlet";

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
                handler1.sendMessage(message);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String rep=response.body().string();
                Message message=new Message();
                message.what=3;
                message.obj=rep;
                Log.d("aaaaaaaaaaa",rep);
                handler1.sendMessage(message);
            }
        });

    }

}