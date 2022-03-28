package com.example.androidhrambabynino;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowInsets;
import android.webkit.JavascriptInterface;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity {
    DrawerLayout drawerLayout;
    Toolbar toolbar;
    NavigationView navigationView;
    ActionBarDrawerToggle drawerToggle;
    MyJSInterface myJSInterface;
    WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawerToggle = setupDrawerToggle();

        drawerToggle.setDrawerIndicatorEnabled(true);
        drawerToggle.syncState();

        drawerLayout.addDrawerListener(drawerToggle);

        navigationView = (NavigationView) findViewById(R.id.nvView);
        setupDrawerContent(navigationView);

        webView = findViewById(R.id.webview);
        webView.setWebViewClient(new WebViewClient());
        webView.getSettings().setJavaScriptEnabled(true);
        webView.addJavascriptInterface(new MyJSInterface(this), "JSInterface");

        getSupportActionBar().setTitle("Объявления");
        selectDrawerItem(navigationView.getMenu().findItem(R.id.nav_anons));
    }

    private void selectDrawerItem(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.nav_posts:
                toolbar.setTitle("Объявления");
                webView.loadUrl("https://hram-babynino.somee.com/android/posts/");
                break;
            case R.id.nav_anons:
                toolbar.setTitle("Объявления");
                webView.loadUrl("https://hram-babynino.somee.com/android/posts/объявления");
                break;
            case R.id.nav_news:
                toolbar.setTitle("Новости");
                webView.loadUrl("https://hram-babynino.somee.com/android/posts/новости");
                break;
            case R.id.nav_video:
                toolbar.setTitle("Видео");
                webView.loadUrl("https://hram-babynino.somee.com/android/posts/видео");
                break;
            case R.id.nav_for_kliros:
                toolbar.setTitle("Для клироса");
                webView.loadUrl("https://hram-babynino.somee.com/android/posts/для клироса");
                break;
            case R.id.nav_life:
                toolbar.setTitle("Жития святых");
                webView.loadUrl("https://hram-babynino.somee.com/android/posts/жития святых");
                break;
            case R.id.nav_our_chirch:
                toolbar.setTitle("О нашем храме");
                webView.loadUrl("https://hram-babynino.somee.com/android/posts/о нашем храме");
                break;
            case R.id.nav_tainstva:
                toolbar.setTitle("О Таинствах");
                webView.loadUrl("https://hram-babynino.somee.com/android/posts/о таинствах");
                break;
            case R.id.nav_raznoe:
                toolbar.setTitle("Разное");
                webView.loadUrl("https://hram-babynino.somee.com/android/posts/разное");
                break;

            case R.id.nav_schedule:
                toolbar.setTitle("Расписание богослужений");
                webView.loadUrl("https://hram-babynino.somee.com/android/schedule");
                break;
            case R.id.nav_photos:
                toolbar.setTitle("Фотографии");
                webView.loadUrl("https://hram-babynino.somee.com/android/photos");
                break;
            case R.id.nav_contacts:
                toolbar.setTitle("Контакты");
                webView.loadUrl("https://hram-babynino.somee.com/android/contacts");
                break;
            default:
                toolbar.setTitle("Объявления");
                webView.loadUrl("https://hram-babynino.somee.com/android/posts");
        }

        // Выделение выбранного элемента было выполнено с помощью NavigationView
        item.setChecked(true);
        // Закройте навигационный ящик
        drawerLayout.closeDrawers();
    }

    private void setupDrawerContent(NavigationView navigationView) {
        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(MenuItem menuItem) {
                        selectDrawerItem(menuItem);
                        return true;
                    }
                });
    }

    private ActionBarDrawerToggle setupDrawerToggle() {
        return new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.drawer_open, R.string.drawer_close);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (drawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        // Синхронизируйте состояние переключения после того, как произошло onRestoreInstanceState.
        drawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(@NonNull Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        // Передайте любое изменение конфигурации переключателям ящика
        drawerToggle.onConfigurationChanged(newConfig);
    }

    public void hide() {
        if (getSupportActionBar() != null) getSupportActionBar().hide();
        getWindow().getDecorView().setSystemUiVisibility(
                View.INVISIBLE
                        | View.SYSTEM_UI_FLAG_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);
    }

    public void show() {
        if (getSupportActionBar() != null) getSupportActionBar().show();
        getWindow().getDecorView().setSystemUiVisibility(
                View.VISIBLE);
    }
}