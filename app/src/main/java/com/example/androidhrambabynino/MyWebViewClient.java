package com.example.androidhrambabynino;

import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;
import android.view.View;
import android.webkit.ConsoleMessage;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toolbar;

import java.io.Console;
import java.net.MalformedURLException;
import java.net.URL;

public class MyWebViewClient extends WebViewClient {
    MainActivity context;

    public MyWebViewClient(Context context) {
        this.context = (MainActivity) context;
    }

    @Override
    public void doUpdateVisitedHistory(WebView view, String url, boolean isReload) {
        String newUrl = url.replace("somee.com/posts", "somee.com/android/posts");
        if (newUrl.contains("/photos/details/")) {
            hide();
        }
        else{
            show();
        }
        super.doUpdateVisitedHistory(view, newUrl, isReload);
    }

    public void hide() {
        if (context.getSupportActionBar() != null) context.getSupportActionBar().hide();
        context.getWindow().getDecorView().setSystemUiVisibility(
                View.INVISIBLE
                        | View.SYSTEM_UI_FLAG_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);
    }

    public void show() {
        if (context.getSupportActionBar() != null) context.getSupportActionBar().show();
        context.getWindow().getDecorView().setSystemUiVisibility(
                View.VISIBLE);
    }
}
