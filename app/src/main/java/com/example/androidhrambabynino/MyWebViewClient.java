package com.example.androidhrambabynino;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

public class MyWebViewClient extends WebViewClient {
    SharedPreferences preferences;
    MainActivity context;

    public MyWebViewClient(Context context) {
        this.context = (MainActivity) context;
        preferences = context.getSharedPreferences("my_settings", 0);
    }

    @Override
    public boolean shouldOverrideUrlLoading(WebView view, String url) {
        if (url.contains("https://hram-babynino.somee.com")) {
            view.loadUrl(url.replace("backurl=/posts", "backurl=/android/posts"));
        }
        return true;
    }

    @Override
    public void onPageStarted(WebView view, String url, Bitmap favicon) {
        if (!hasInternet(context)) {
            context.getSupportActionBar().setTitle("");
            view.setVisibility(View.GONE);
            context.findViewById(R.id.layout_error).setVisibility(View.VISIBLE);
            context.findViewById(R.id.buttonReloadWebview).setOnClickListener(v -> view.reload());
        } else {
            view.setVisibility(View.VISIBLE);
            context.findViewById(R.id.layout_error).setVisibility(View.GONE);
        }
        super.onPageStarted(view, url, favicon);
    }

    @Override
    public void onPageFinished(WebView webview, String url) {
        if (preferences.getBoolean("dark_theme", false) == false) {
            webview.loadUrl("javascript:setLightTheme()");
        } else {
            webview.loadUrl("javascript:setDarkTheme()");
        }

        ((SwipeRefreshLayout) context.findViewById(R.id.webview_refresher)).setRefreshing(false);
        super.onPageFinished(webview, url);
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
        if (view.getTitle().contains("http")
                || view.getTitle().contains("Храм")
                || view.getTitle().contains("Не удалось")
                || view.getTitle().contains("not")) {
            context.getSupportActionBar().setTitle("");
        } else
            context.getSupportActionBar().setTitle(view.getTitle());
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

    public boolean hasInternet(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivityManager != null) {
            NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
            if (networkInfo != null)
                if (networkInfo.isConnected())
                    return true;
        }
        return false;
    }
}
