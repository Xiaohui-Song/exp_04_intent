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
        <br>

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
<br>  
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
<br>  
## 实验结果  
[1]


