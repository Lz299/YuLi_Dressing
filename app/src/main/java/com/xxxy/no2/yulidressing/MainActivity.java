package com.xxxy.no2.yulidressing;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btn_login = findViewById(R.id.btn_login);
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, IndexActivity.class);
                startActivity(intent);
            }
        });


        Button btn_zhuce = findViewById(R.id.btn_zhuce);
        btn_zhuce.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loginClick();
            }
        });

    }



    public void loginClick(){

        AlertDialog mDialog = new AlertDialog.Builder(MainActivity.this).show();
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setIcon(R.drawable.logo);
        builder.setTitle("用户注册");
        final View view = getLayoutInflater().inflate(R.layout.zhuce,null);
        mDialog.getWindow().setBackgroundDrawableResource(android.R.color.white); // 修改这里
        builder.setView(view);

        builder.setPositiveButton("登录", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                EditText et_phone = (EditText) view.findViewById(R.id.editText_phone);
                EditText et_username = (EditText) view.findViewById(R.id.editText_username);
                EditText et_password = (EditText) view.findViewById(R.id.editText_password);
                EditText et_password2 = (EditText) view.findViewById(R.id.editText_password2);
                String phone = et_phone.getText().toString();
                String username = et_username.getText().toString();
                String password = et_password.getText().toString();
                String password2 = et_password2.getText().toString();

                Toast.makeText(MainActivity.this, phone+"-"+username+"-"+password+"-"+password2, Toast.LENGTH_SHORT).show();
                dialog.cancel();
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

}