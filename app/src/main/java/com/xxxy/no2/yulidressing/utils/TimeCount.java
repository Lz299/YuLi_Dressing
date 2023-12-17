package com.xxxy.no2.yulidressing.utils;

import android.app.AlertDialog;
import android.content.Context;
import android.os.CountDownTimer;
import android.widget.Button;
import android.widget.TextView;

import com.xxxy.no2.yulidressing.R;


public class TimeCount extends CountDownTimer {
    private static final int TIME_TASCK = 1000;
    private TextView button;
    private Context context;
    private AlertDialog.Builder builder;


    public TimeCount(Context context, long millisInFuture, TextView view) {
        super(millisInFuture, TIME_TASCK);
        button = view;
        this.context = context;
    }

    @Override
    public void onFinish() {// 计时完毕
        button.setTextColor(context.getResources().getColor(R.color.white));
        button.setText("再次获取");
        button.setClickable(true);
    }

    @Override
    public void onTick(long millisUntilFinished) {// 计时过程
        button.setTextColor(context.getResources().getColor(R.color.white));
        button.setClickable(false);//防止重复点击
        button.setText(millisUntilFinished / TIME_TASCK+"秒后可重发");
    }
}