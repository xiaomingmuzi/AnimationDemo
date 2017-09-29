package com.lixm.animationdemo.activity;

import android.graphics.Bitmap;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.lixm.animationdemo.R;

import org.xutils.common.util.LogUtil;

public class WebViewActivity extends AppCompatActivity {

    private WebView webView;
    private WebSettings settings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);
        webView = (WebView) findViewById(R.id.webview);
        initVb();
    }

    private void initVb() {
        settings = webView.getSettings();
        settings.setDomStorageEnabled(true);
        settings.setLoadsImagesAutomatically(true);
        settings.setAppCacheEnabled(true);
        settings.setJavaScriptEnabled(true);
        settings.setLoadWithOverviewMode(true);
        settings.setUseWideViewPort(true);
        settings.setBlockNetworkImage(true);
        settings.setDefaultTextEncodingName("utf-8"); // 设置文本编码
        settings.setCacheMode(WebSettings.LOAD_DEFAULT);// 设置
        //        webView.setLayerType(View.LAYER_TYPE_HARDWARE, null);
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP) {
            settings.setMixedContentMode(WebSettings.MIXED_CONTENT_ALWAYS_ALLOW);
        }

        webView.setWebViewClient(new MyWebViewClient());
        webView.setWebChromeClient(new MyChromeClient());

        webView.loadUrl("http://m.3g.cnfol.com/video/frontpage/");

    }

    // webview设置的代理类
    private class MyWebViewClient extends WebViewClient {

        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {

            // return true;// 这里将图片的超链接屏蔽掉，点击图片的时候调用自己的javaScrip显示图片
            return super.shouldOverrideUrlLoading(view, url);

        }

        @Override
        public void onPageFinished(final WebView view, final String url) {

            view.getSettings().setJavaScriptEnabled(true);
            super.onPageFinished(view, url);
            LogUtil.e("详情页加载完成-----" + url);

            view.getSettings().setBlockNetworkImage(false);

        }

        @Override
        public void onPageStarted(WebView view, final String url, Bitmap favicon) {
            LogUtil.e("详情页开始加载-----" + url);
            //传单个值
            super.onPageStarted(view, url, favicon);
        }

        @Override
        public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
            LogUtil.e("详情页加载异常-----" + failingUrl);
            super.onReceivedError(view, errorCode, description, failingUrl);

        }
    }

    private class MyChromeClient extends WebChromeClient {
        @Override
        public void onProgressChanged(WebView view, int newProgress) {
            LogUtil.w("onProgressChanged======="+newProgress);
            super.onProgressChanged(view, newProgress);
        }

        @Override
        public void onReceivedTitle(WebView view, String title) {
            super.onReceivedTitle(view, title);
            LogUtil.i("网页title-----" + title);
        }
    }

}
