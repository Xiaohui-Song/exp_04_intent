package com.example.exp_04;

import android.content.Intent;
import android.net.http.SslError;
import android.os.Bundle;


import androidx.appcompat.app.AppCompatActivity;

import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import android.webkit.*;

public class MainActivity extends AppCompatActivity {
    public String value;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.exp1);
        Intent intent = getIntent();
        //通过Extra 来传递url
        if(intent != null){
            value = intent.getStringExtra("key");
        }
        startBrowser(value);
    }

    private void startBrowser(String url){
        Toast.makeText(this, value, Toast.LENGTH_SHORT).show();
        WebView webView =  (WebView)findViewById(R.id.webview);
        //wenview加载web资源
        webView.loadUrl(url);
        webView.setWebViewClient(new WebViewClient(){
            public boolean shouldOverrideUrlLoading(WebView view,String url){
               view.loadUrl(url);
               return true;
           }

        });

        webView.setWebViewClient(new WebViewClient() {
            @Override
            public void onReceivedSslError(WebView view,SslErrorHandler handler, SslError error) {
                //等待证书响应
                handler.proceed();
            }
        });
    }

}
