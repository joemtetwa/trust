package com.mig062.trust;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;

public class ChatActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        WebView webView2 = (WebView)
                findViewById(R.id.webView2);
        webView2.loadUrl("http://trustacademy.3022.n8.nabble.com/");
    }
}
