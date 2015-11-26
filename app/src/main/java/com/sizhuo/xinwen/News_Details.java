package com.sizhuo.xinwen;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import com.sizhuo.xinwen.util.SetTitleBar;

/**
 * 新闻详情页
 * 2015-11-25 09:00:00
 */
public class News_Details extends AppCompatActivity {

    private String errorHtml = "<html><body><h1>Page not find!</h1></body></html>";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.news__details);
//        new SetTitleBar(this).init();
        Intent intent = this.getIntent();
        Bundle data = intent.getBundleExtra("data");
        String titleStr = data.getString("title");
        String linkStr = data.getString("link");
        Toolbar toolbar = (Toolbar) findViewById(R.id.news_details_toolbar);
        toolbar.setTitle(titleStr);
        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                News_Details.this.finish();
            }
        });

        WebView webView = (WebView) findViewById(R.id.news_details_webview);
        webView.loadUrl(linkStr);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setDefaultTextEncodingName("UTF-8");  //设置默认的显示编码
        webView.setWebViewClient(new MyWebViewClient());
    }

    public class MyWebViewClient extends WebViewClient{
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;
        }

        @Override
        public void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error) {
            super.onReceivedError(view, request, error);
//            Toast.makeText(News_Details.this,"无法访问",Toast.LENGTH_SHORT).show();
            view.loadData(errorHtml, "text/html", "UTF-8");
        }
    }
}
