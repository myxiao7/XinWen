package com.sizhuo.xinwen;

import android.app.Activity;
import android.graphics.Bitmap;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bm.library.PhotoView;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.listener.ImageLoadingProgressListener;
import com.nostra13.universalimageloader.core.listener.SimpleImageLoadingListener;
import com.sizhuo.xinwen.entity.JianLue;
import com.sizhuo.xinwen.util.SetTitleBar;

import java.util.ArrayList;
import java.util.List;

public class ImageWatch extends Activity {
    private PhotoView photoView;
    private TextView countTxt, cutTxt;//图片数量，当前位置
    private ViewPager viewPager;
    private List<JianLue> list = new ArrayList<JianLue>();//图片url
    private int index=0;//所选图片位置，默认为第一张
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_watch);
        overridePendingTransition(R.anim.fade, R.anim.hold);
        new SetTitleBar(this).init();
        list = (List<JianLue>) this.getIntent().getSerializableExtra("data");
        index = this.getIntent().getIntExtra("index",0);
        initViews();
        PagerAdapter pagerAdapter = new PagerAdapter() {
            @Override
            public Object instantiateItem(ViewGroup container, int position) {
                PhotoView photoView = new PhotoView(ImageWatch.this);
                photoView.enable();
                photoView.setScaleType(ImageView.ScaleType.CENTER);
//                photoView.set
                DisplayImageOptions options = new DisplayImageOptions.Builder()
                        .cacheInMemory(true)
                        .cacheOnDisk(true)
                        .bitmapConfig(Bitmap.Config.RGB_565)
                        .build();
                //使用UIL加载图片
                com.nostra13.universalimageloader.core.ImageLoader.getInstance().displayImage(list.get(position).getThumb(), photoView, options, new SimpleImageLoadingListener(), new ImageLoadingProgressListener() {
                    @Override
                    public void onProgressUpdate(String s, View view, int i, int i1) {
                        Log.d("xinwen", s + "------------");
                    }
                });
                photoView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        ImageWatch.this.finish();
                    }
                });
                container.addView(photoView);
                return photoView;
            }

            @Override
            public void destroyItem(ViewGroup container, int position, Object object) {
               container.removeView((View) object);
            }

            @Override
            public int getCount() {
                return list.size();
            }

            @Override
            public boolean isViewFromObject(View view, Object object) {
                //
                return view==object;
            }
        };
        viewPager.setPageMargin((int)(getResources().getDisplayMetrics().density*15));
        viewPager.setAdapter(pagerAdapter);
        viewPager.setCurrentItem(index);//当前所选图片
        cutTxt.setText(index+"");
        viewPager.addOnPageChangeListener(new OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                position++;
                cutTxt.setText(position+"");
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    private void initViews() {
        viewPager = (ViewPager) findViewById(R.id.image_watch_viewpager);
        countTxt = (TextView) findViewById(R.id.image_watch_count_txt);
        cutTxt = (TextView) findViewById(R.id.image_watch_cut_txt);
//        photoView = (PhotoView) findViewById(R.id.image_watch_photoview);
        countTxt.setText(list.size()+"");
    }
}
