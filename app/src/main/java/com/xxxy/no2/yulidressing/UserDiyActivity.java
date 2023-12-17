package com.xxxy.no2.yulidressing;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ScaleGestureDetector;
import android.view.View;

import com.xxxy.no2.yulidressing.Listener.MyScaleGestureListener;
import com.xxxy.no2.yulidressing.util.DragViewUtil;


public class UserDiyActivity extends AppCompatActivity {
    private ScaleGestureDetector mScaleGestureDetector = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_diy);

        DragView diy_kou1 = findViewById(R.id.diy_kou1);

        View view = View.inflate(UserDiyActivity.this, R.layout.activity_user_diy, null);
        setContentView(view);
        //这里的view是要设置缩放手势的组件
        mScaleGestureDetector = new ScaleGestureDetector(UserDiyActivity.this, new MyScaleGestureListener(diy_kou1));

        //调用diy自定义view 实现拖拽
        DragViewUtil.registerDragAction(diy_kou1);
        //延迟3秒响应拖曳
        // DragViewUtil.registerDragAction(button,3000);
    }
}