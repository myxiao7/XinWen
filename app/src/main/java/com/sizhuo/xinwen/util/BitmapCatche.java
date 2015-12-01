package com.sizhuo.xinwen.util;

import android.graphics.Bitmap;
import android.util.LruCache;

import com.android.volley.toolbox.ImageLoader;

/**
 * Created by My灬xiao7 on 2015/11/23.
 */
public class BitmapCatche implements ImageLoader.ImageCache {
    public int maxSize = 10 * 1024 * 1024;//缓存大小
    public LruCache<String, Bitmap> mLruCache;

    public BitmapCatche() {
        if (mLruCache == null) {
            mLruCache = new LruCache<String, Bitmap>(maxSize) {
                @Override
                protected int sizeOf(String key, Bitmap value) {
                    return value.getRowBytes() * value.getHeight();
                }
            };
        }
    }

    @Override
    public Bitmap getBitmap(String s) {
        return mLruCache.get(s);
    }

    @Override
    public void putBitmap(String s, Bitmap bitmap) {
        mLruCache.put(s, bitmap);
    }
}
