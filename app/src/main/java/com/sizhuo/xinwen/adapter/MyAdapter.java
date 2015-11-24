package com.sizhuo.xinwen.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.sizhuo.xinwen.R;
import com.sizhuo.xinwen.entity.JianLue;
import com.sizhuo.xinwen.util.BitmapCatche;

import java.util.List;

/**
 * Created by My灬xiao7 on 2015/11/23.
 */
public class MyAdapter extends BaseAdapter {
    public List<JianLue> list;
    public ImageLoader imageLoader;
    public Context context;
    public RequestQueue queue;

    public MyAdapter(Context context, List<JianLue> list, RequestQueue queue) {
        this.context =context;
        this.list = list;
        this.queue = queue;
        imageLoader = new ImageLoader(queue,new BitmapCatche());
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if(convertView == null){
            LayoutInflater inflater = LayoutInflater.from(parent.getContext());
            convertView = inflater.inflate(R.layout.main_list_item,parent, false);
            holder = new ViewHolder();
            holder.imageView = (ImageView) convertView.findViewById(R.id.main_list_item_img);
            holder.tv01 = (TextView) convertView.findViewById(R.id.main_list_item_tv01);
            holder.tv02 = (TextView) convertView.findViewById(R.id.main_list_item_tv02);
            convertView.setTag(holder);
        }else{
            holder = (ViewHolder) convertView.getTag();
        }
        JianLue lue = list.get(position);
        holder.tv01.setText(lue.getTitle());
        holder.tv02.setText(lue.getDescr());

        ImageLoader.ImageListener imageListener = ImageLoader.getImageListener(holder.imageView,R.mipmap.img_default,R.mipmap.img_error);
        imageLoader.get(lue.getThumb(), imageListener);

        Log.d("xinwen",lue.getId()+"---------------------------");
        return convertView;
    }

    public class ViewHolder{
        public ImageView imageView;
        public TextView tv01;
        public TextView tv02;
    }

    public void notifyDataSetChanged(List<JianLue> list) {
        this.list = list;
        notifyDataSetChanged();
    }
}
