package com.sizhuo.xinwen;

import android.app.Application;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

/**
 * Created by My灬xiao7 on 2015/11/23.
 */
public class MyApplication extends Application {
    public static RequestQueue queue;

    @Override
    public void onCreate() {
        super.onCreate();
        queue = Volley.newRequestQueue(getApplicationContext());
    }

    public RequestQueue getHttpRequeQueue(){
        return  queue;
    }
}
