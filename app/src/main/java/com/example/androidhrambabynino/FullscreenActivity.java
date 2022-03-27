package com.example.androidhrambabynino;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.webkit.CookieManager;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;

public class FullscreenActivity extends AppCompatActivity {
    WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fullscreen);

        if (getSupportActionBar() != null) getSupportActionBar().hide();
        findViewById(R.id.containerFullscreen).setSystemUiVisibility(View.SYSTEM_UI_FLAG_LOW_PROFILE
                | View.SYSTEM_UI_FLAG_FULLSCREEN
                | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);

        webView = findViewById(R.id.webviewFullscreen);
        webView.setWebViewClient(new WebViewClient());
        webView.getSettings().setJavaScriptEnabled(true);
        webView.addJavascriptInterface(new MyJSInterface(this),"JSInterface");
        //CookieManager.getInstance().setAcceptCookie(true);
        //webView.getSettings().setDomStorageEnabled(true);
        Bundle bundle = new Bundle();
        bundle = getIntent().getExtras();
        webView.loadUrl(bundle.getString("url"));
    }

    public void returnBack(View view){
        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra("url","https://hram-babynino.somee.com/android/photos");
        startActivity(intent);
    }

    @Override
    public void onBackPressed() {
        returnBack(webView);
    }
}