package com.sizhuo.xinwen;

import android.app.Activity;
import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.bm.library.PhotoView;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.listener.ImageLoadingProgressListener;
import com.nostra13.universalimageloader.core.listener.SimpleImageLoadingListener;
import com.sizhuo.xinwen.util.SetTitleBar;

public class ImageWatch extends Activity {
    private PhotoView photoView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_watch);
        overridePendingTransition(R.anim.fade, R.anim.hold);
        new SetTitleBar(this).init();
        String url = this.getIntent().getStringExtra("url");
        Log.d("uil", url);
        photoView = (PhotoView) findViewById(R.id.image_watch_photoview);
        photoView.enable();
        DisplayImageOptions options = new DisplayImageOptions.Builder()
                .cacheInMemory(true)
                .cacheOnDisk(true)
                .bitmapConfig(Bitmap.Config.RGB_565)
                .build();
        //使用UIL加载图片
        com.nostra13.universalimageloader.core.ImageLoader.getInstance().displayImage(url, photoView, options, new SimpleImageLoadingListener(), new ImageLoadingProgressListener() {
            @Override
            public void onProgressUpdate(String s, View view, int i, int i1) {

            }
        });
        photoView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ImageWatch.this.finish();
            }
        });
    }
}
