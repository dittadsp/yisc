package com.yisc;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.webkit.WebView;


public class ResumeActivity extends Activity{

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resume);
        Intent intent = getIntent();
        String content = intent.getStringExtra("content");
//        String encodedHtml = Base64.encodeToString(content.getBytes(),
//                Base64.NO_PADDING);
        WebView webview = (WebView)findViewById(R.id.webviewresume);
        webview.loadData(content, "text/html","UTF-8");
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(getApplicationContext(),Home.class);
        startActivity(intent);
    }
}