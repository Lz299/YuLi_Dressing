package com.xxxy.no2.yulidressing.utils;

import android.content.Context;
import android.content.SharedPreferences;

import com.xxxy.no2.yulidressing.model.Users;

import java.util.HashMap;
import java.util.Map;

public class SharedPreferencesUtils {

    public boolean saveUserInfo(Context context, Users users){
        SharedPreferences sp=context.getSharedPreferences("data",Context.MODE_PRIVATE);
        SharedPreferences.Editor editor=sp.edit();
        editor.putString("users_id",users.getUsers_id()+"");
        editor.putString("username",users.getUsername());
        editor.putString("password",users.getPassword());
        editor.putString("phone",users.getPhone());
        editor.putString("users_img",users.getUsers_img());
        editor.putBoolean("logined",true);
        editor.commit();
        return true;
    }
    public  Map<String, String> getUser(Context context){
    SharedPreferences sp=context.getSharedPreferences("data",Context.MODE_PRIVATE);
        String users_id=sp.getString("users_id","");
        String username=sp.getString("username","");
        String password=sp.getString("password","");
        String phone=sp.getString("phone","");
        String users_img=sp.getString("users_img","");
        boolean logined=sp.getBoolean("logined",false);
        Map<String, String> map = new HashMap<>();
        map.put("users_id", users_id);
        map.put("username", username);
        map.put("password", password);
        map.put("phone", phone);
        map.put("users_img", users_img);
        map.put("logined", String.valueOf(logined));
        return map;

    }


}
