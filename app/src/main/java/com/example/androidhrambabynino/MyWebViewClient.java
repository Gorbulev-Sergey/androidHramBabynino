package com.example.androidhrambabynino;

import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;
import android.view.View;
import android.webkit.ConsoleMessage;
import android.webkit.RenderProcessGoneDetail;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;
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
    public boolean shouldOverrideUrlLoading(WebView view, String url) {
        if (url.contains("https://hram-babynino.somee.com")) {
            view.loadUrl(url);
        } else {
            Toast.makeText(context, "Отсутствует подключение к интернет", Toast.LENGTH_LONG).show();
        }
        return true;
    }

    @Override
    public void doUpdateVisitedHistory(WebView view, String url, boolean isReload) {
        if (url.contains("/photos/details/")) {
            hide();
        } else {
            show();
        }
        super.doUpdateVisitedHistory(view, url, isReload);
    }

    @Override
    public void onLoadResource(WebView view, String url) {
        if (view.getTitle().contains("http") || view.getTitle().contains("Храм")) {
            context.getSupportActionBar().setTitle("");
        } else {
            context.getSupportActionBar().setTitle(view.getTitle());
        }
        super.onLoadResource(view, url);
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

    String toUpperFirstChar(String item) {
        return item.substring(0, 1).toUpperCase() + item.substring(1);
    }
}
