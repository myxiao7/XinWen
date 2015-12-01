package com.sizhuo.xinwen;

import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Build;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ListView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.sizhuo.xinwen.adapter.MyAdapter;
import com.sizhuo.xinwen.entity.JianLue;
import com.sizhuo.xinwen.util.ACache;
import com.sizhuo.xinwen.util.SystemBarTintManager;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    private Toolbar toolbar;
    private ListView listView;
    private MyAdapter adapter;
    private RequestQueue queue;
    private final String URL = "http://apis.baidu.com/cd_boco/chinanews/testnewsapi";
    private String httpArg = "query=%7B%27device%27%3A%27android%27%2C%27catid%27%3A1%2C%27pagesize%27%3A10%2C%27sid%27%3A%2711142%27%7D";
    private final String APIKEY = "4d49efb059a423dd040ea184ef594a87";
    private List<JianLue> datas = new ArrayList<JianLue>();
    private List<JianLue> cacheData = new ArrayList<JianLue>();
    ACache aCache;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            setTranslucentStatus(true);
            SystemBarTintManager tintManager = new SystemBarTintManager(this);
            tintManager.setStatusBarTintEnabled(true);
            //保证和状态栏同色
            tintManager.setStatusBarTintResource(R.color.holo_red_light);
        }
        initViews();
        toolbar.setTitle("新闻");
        setSupportActionBar(toolbar);
        aCache = ACache.get(this);

        queue = Volley.newRequestQueue(this);
//        {'device':'android','catid':1,'pagesize':10,'sid':'11142'}
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, URL + "?" + httpArg, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject jsonObject) {
//                Log.d("xinwen",jsonObject.toString());
                try {
                    String res = jsonObject.getString("data");
                    datas = new Gson().fromJson(res, new TypeToken<List<JianLue>>() {
                    }.getType());
                    Log.d("xinwen", "size" + datas.size());
                    Log.d("xinwen", res);
                    adapter.notifyDataSetChanged(datas);
                    aCache.remove("cacahe");
                    aCache.put("cacahe", res);
//                    Log.d("xinwen", "remove" + aCache.remove("datas"));
                 /*   aCache.clear();
                    aCache.put("datas",res);*/
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                Log.d("xinwen", volleyError.toString());

            }
        }) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> header = new HashMap<String, String>();
                header.put("apikey", APIKEY);
                return header;
            }
        };

       /* if(datas != null && !datas.equals("")){
            cacheData = new Gson().fromJson(aCache.getAsString("datas"), new TypeToken<List<JianLue>>(){}.getType());
        }*/
        Log.d("xinwen", aCache.getAsString("cacahe") + "11111111111111111111");
        if (aCache.getAsString("cacahe") == null || aCache.getAsString("cacahe").equals("")) {
            JianLue lue = new JianLue("", "测试", "测试", "测试", "测试", "", "测试");
            datas.add(lue);
        } else {
            datas = new Gson().fromJson(aCache.getAsString("cacahe"), new TypeToken<List<JianLue>>() {
            }.getType());
        }
        adapter = new MyAdapter(this, datas, queue);
        listView.setAdapter(adapter);
        /*listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(MainActivity.this,News_Details.class);
                Bundle bundle = new Bundle();
                bundle.putString("title",datas.get(position).getTitle());
                bundle.putString("link",datas.get(position).getLink());
                intent.putExtra("data", bundle);
                MainActivity.this.startActivity(intent);
            }
        });*/
        queue.add(request);
        request.setTag("infos");
    }

    private void initViews() {
        toolbar = (Toolbar) findViewById(R.id.main_toolbar);
        listView = (ListView) findViewById(R.id.main_listview);
    }


    @TargetApi(19)
    private void setTranslucentStatus(boolean on) {
        Window win = getWindow();
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
