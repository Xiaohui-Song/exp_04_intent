# exp_04_intent
intent practice

## intent  
<br>
1.实验要求：一工程用于输入网址，点击后跳转到二工程浏览网页<br>  
2.一工程通过点击事件，将获取到的url网址通过intent.putExtra传递到二工程<br>  

```

 button.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                String url = editText.getText().toString();
                Intent intent = new Intent();
                intent.putExtra("key",url);
                intent.setAction(Intent.ACTION_VIEW);
                startActivity(intent);
            }

        });    
```  

3.二工程通过getStringExtra()方法获取到网址后,利用webview来展示加载网页<br>  
```
 Intent intent = getIntent();
        //通过Extra 来传递url
        if(intent != null){
            value = intent.getStringExtra("key");
        }
        startBrowser(value);
    }

    private void startBrowser(String url){
        //Toast.makeText(this, value, Toast.LENGTH_SHORT).show();
        WebView webView =  (WebView)findViewById(R.id.webview);
        //webview加载web资源
        webView.loadUrl(url);
        webView.setWebViewClient(new WebViewClient(){
            public boolean shouldOverrideUrlLoading(WebView view,String url){
               view.loadUrl(url);
               return true;
           }

        });
```  

4.Android WebView组件加载网页发生证书认证错误时，会调用WebViewClient类的onReceivedSslError方法
，方法内调用handler.proceed()来忽略该证书错误<br>  
```

 webView.setWebViewClient(new WebViewClient() {
            @Override
            public void onReceivedSslError(WebView view,SslErrorHandler handler, SslError error) {
                //等待证书响应
                handler.proceed();
            }
        });
```  

实验最终结果如下图:<br>    
![1](https://github.com/Xiaohui-Song/exp_04_intent/blob/main/pic4/web.png)<br>  
![2](https://github.com/Xiaohui-Song/exp_04_intent/blob/main/pic4/visit.png)<br>  

## 问题及解决
<br>  
1.二工程在AndroidManifest.xml中的<intent-filter>标签下加入<action android:name="android.intent.action.VIEW" />报错如下：  
 
![error](https://github.com/Xiaohui-Song/exp_04_intent/blob/main/pic4/intent_view_error.png)  

在添加以下代码后依旧不成功:  

![solve](https://github.com/Xiaohui-Song/exp_04_intent/blob/main/pic4/solve.png)  

解决办法：<br>  
activity标签下重新写一个<intent-filter>:  
 
```
 <intent-filter>
                <action android:name="android.intent.action.VIEW" />
                <category android:name="android.intent.category.DEFAULT" />
            </intent-filter>
 ```  
 
2.访问百度网页报错：  

![error](https://github.com/Xiaohui-Song/exp_04_intent/blob/main/pic4/error.png)  

解决办法:<br>  
manifest标签下添加访问网络权限:<br>  
```
  <uses-permission android:name="android.permission.INTERNET" />
```  
application标签下添加如下代码，启用明文支持如http（Android的系统上面默认所有http的请求都被阻止了)  
 
```
  android:usesCleartextTraffic="true"
```
  

