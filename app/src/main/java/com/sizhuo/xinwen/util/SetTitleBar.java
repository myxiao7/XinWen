package com.sizhuo.xinwen.util;

import android.annotation.TargetApi;
import android.app.Activity;
import android.os.Build;
import android.view.Window;
import android.view.WindowManager;

import com.sizhuo.xinwen.R;

/**
 * 沉浸式状态栏
 * Created by My灬xiao7 on 2015/11/25.
 */
public class SetTitleBar {
    public Activity activity;

    public SetTitleBar(Activity activity) {
        this.activity = activity;
    }

    /**
     * 沉浸式状态栏
     */
    public void init(){
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT){
            setTranslucentStatus(true);
            SystemBarTintManager tintManager = new SystemBarTintManager(activity);
            tintManager.setStatusBarTintEnabled(true);
            //保证和状态栏同色
            tintManager.setStatusBarTintResource(R.color.holo_red_light);
        }
    }


    @TargetApi(19)
    private void setTranslucentStatus(boolean on) {
        Window win = activity.getWindow();
        WindowManager.LayoutParams winParams = win.getAttributes();
        final int bits = WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS;
        if (on) {
            winParams.flags |= bits;
        } else {
            winParams.flags &= ~bits;
        }
        win.setAttributes(winParams);
    }
}
