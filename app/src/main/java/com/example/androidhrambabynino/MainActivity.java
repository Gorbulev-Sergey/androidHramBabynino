package com.example.androidhrambabynino;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.res.Configuration;
import android.graphics.Color;
import android.os.Bundle;
import android.view.MenuItem;
import android.webkit.WebView;

import com.google.android.material.navigation.NavigationView;


public class MainActivity extends AppCompatActivity {
    DrawerLayout drawer;
    Toolbar toolbar;
    NavigationView navigation;
    ActionBarDrawerToggle toggler;
    MyWebViewClient myWebViewClient;
    WebView webview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        setContentView(R.layout.activity_main);

        toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        drawer = findViewById(R.id.main_layout);
        drawer.addDrawerListener(toggler);

        toggler = setupDrawerToggle();
        toggler.setDrawerIndicatorEnabled(true);
        toggler.syncState();

        navigation = findViewById(R.id.navigation);
        setupDrawerContent(navigation);

        webview = findViewById(R.id.webview);
        myWebViewClient = new MyWebViewClient(this);
        webview.setWebViewClient(myWebViewClient);
        webview.setBackgroundColor(Color.TRANSPARENT);
        webview.getSettings().setSupportZoom(true);
        webview.getSettings().setJavaScriptEnabled(true);

        selectDrawerItem(navigation.getMenu().findItem(R.id.nav_schedule));
    }

    private void selectDrawerItem(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.nav_posts:
                //toolbar.setTitle("Объявления");
                webview.loadUrl("https://hram-babynino.somee.com/android/posts/");
                break;
            case R.id.nav_anons:
                //toolbar.setTitle("Объявления");
                webview.loadUrl("https://hram-babynino.somee.com/android/posts/объявления");
                break;
            case R.id.nav_news:
                //toolbar.setTitle("Новости");
                webview.loadUrl("https://hram-babynino.somee.com/android/posts/новости");
                break;
            case R.id.nav_video:
                //toolbar.setTitle("Видео");
                webview.loadUrl("https://hram-babynino.somee.com/android/posts/видео");
                break;
            case R.id.nav_for_kliros:
                //toolbar.setTitle("Для клироса");
                webview.loadUrl("https://hram-babynino.somee.com/android/posts/для клироса");
                break;
            case R.id.nav_life:
                //toolbar.setTitle("Жития святых");
                webview.loadUrl("https://hram-babynino.somee.com/android/posts/жития святых");
                break;
            case R.id.nav_our_chirch:
                //toolbar.setTitle("О нашем храме");
                webview.loadUrl("https://hram-babynino.somee.com/android/posts/о нашем храме");
                break;
            case R.id.nav_tainstva:
                //toolbar.setTitle("О таинствах");
                webview.loadUrl("https://hram-babynino.somee.com/android/posts/о таинствах");
                break;
            case R.id.nav_raznoe:
                //toolbar.setTitle("Разное");
                webview.loadUrl("https://hram-babynino.somee.com/android/posts/разное");
                break;

            case R.id.nav_schedule:
                //toolbar.setTitle("Расписание богослужений");
                webview.loadUrl("https://hram-babynino.somee.com/android/schedule");
                break;
            case R.id.nav_photos:
                //toolbar.setTitle("Фотографии");
                webview.loadUrl("https://hram-babynino.somee.com/android/photos");
                break;
            case R.id.nav_contacts:
                //toolbar.setTitle("Контакты");
                webview.loadUrl("https://hram-babynino.somee.com/android/contacts");
                break;
            default:
                //toolbar.setTitle("Объявления");
                webview.loadUrl("https://hram-babynino.somee.com/android/posts");
        }

        // Выделение выбранного элемента было выполнено с помощью NavigationView
        item.setChecked(true);
        // Закройте навигационный ящик
        drawer.closeDrawers();
    }

    private void setupDrawerContent(NavigationView navigationView) {
        navigationView.setNavigationItemSelectedListener(
                menuItem -> {
                    selectDrawerItem(menuItem);
                    return true;
                });
    }

    private ActionBarDrawerToggle setupDrawerToggle() {
        return new ActionBarDrawerToggle(this, drawer, toolbar, R.string.drawer_open, R.string.drawer_close);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (toggler.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        // Синхронизируйте состояние переключения после того, как произошло onRestoreInstanceState.
        toggler.syncState();
    }

    @Override
    public void onConfigurationChanged(@NonNull Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        // Передайте любое изменение конфигурации переключателям ящика
        toggler.onConfigurationChanged(newConfig);
    }

    @Override
    protected void onStop() {
        webview.reload();
        super.onStop();
    }

    @Override
    public void onBackPressed() {
        if (webview.canGoBack()) {
            webview.goBack();
        } else
            super.onBackPressed();
    }
}