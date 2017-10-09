package com.mig062.trust;


import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;

public class LibraryActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_library);

        WebView webView1 = (WebView)
                findViewById(R.id.webView1);
        webView1.loadUrl("https://www.trustacademy.org/librarysystem/");

    }
}


