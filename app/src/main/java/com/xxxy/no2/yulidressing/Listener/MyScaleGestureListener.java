package com.xxxy.no2.yulidressing.Listener;

import android.view.ScaleGestureDetector;
import android.view.View;

public class MyScaleGestureListener implements ScaleGestureDetector.OnScaleGestureListener {

    private float scale;
    private float preScale = 1;// 默认前一次缩放比例为1
    private View view;

    public MyScaleGestureListener(View view) {
        this.view = view;
    }

    @Override
    public boolean onScale(ScaleGestureDetector detector) {
        float previousSpan = detector.getPreviousSpan();
        float currentSpan = detector.getCurrentSpan();
        if (currentSpan < previousSpan) {
            // 缩小
            scale = preScale - (previousSpan - currentSpan) / 1000;
        } else {
            // 放大
            scale = preScale + (currentSpan - previousSpan) / 1000;
        }
        // 缩放view
        // scale表示缩放比，等于0时组件消失，为1时撑满父容器
        if (scale <= 0) {
            scale = 0;
        } else if (scale >= 1) {
            scale = 1;
        }
        view.setScaleX(scale);
        view.setScaleY(scale);
        return false;
    }

    @Override
    public boolean onScaleBegin(ScaleGestureDetector detector) {
        // 一定要返回true才会进入onScale()这个函数
        return true;
    }

    @Override
    public void onScaleEnd(ScaleGestureDetector detector) {
        preScale = scale;//记录本次缩放比例
    }
}

