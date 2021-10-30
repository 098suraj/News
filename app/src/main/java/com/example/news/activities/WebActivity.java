package com.example.news.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.webkit.WebView;

import com.example.news.R;

public class WebActivity extends AppCompatActivity {
WebView webView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web);
        webView=findViewById(R.id.webview);
        Intent intent=getIntent();
        String uri=intent.getStringExtra("url");
        webView.loadUrl(uri);
    }
}