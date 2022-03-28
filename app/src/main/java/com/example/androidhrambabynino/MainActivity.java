package com.example.androidhrambabynino;

import androidx.annotation.ColorInt;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;

import android.annotation.SuppressLint;
import android.content.res.Configuration;
import android.graphics.Color;
import android.os.Bundle;
import android.provider.CalendarContract;
import android.view.MenuItem;
import android.webkit.WebView;

import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity {
    DrawerLayout mainLayout;
    Toolbar toolbar;
    NavigationView navigationView;
    ActionBarDrawerToggle drawerToggle;
    MyWebViewClient myWebViewClient;
    WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mainLayout = (DrawerLayout) findViewById(R.id.main_layout);
        drawerToggle = setupDrawerToggle();

        drawerToggle.setDrawerIndicatorEnabled(true);
        drawerToggle.syncState();

        mainLayout.addDrawerListener(drawerToggle);

        navigationView = (NavigationView) findViewById(R.id.nvView);
        setupDrawerContent(navigationView);

        webView = findViewById(R.id.webview);
        myWebViewClient = new MyWebViewClient(this);
        webView.setWebViewClient(myWebViewClient);
        webView.setBackgroundColor(Color.TRANSPARENT);
        webView.getSettings().setSupportZoom(true);
        webView.getSettings().setJavaScriptEnabled(true);

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
        mainLayout.closeDrawers();
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
        return new ActionBarDrawerToggle(this, mainLayout, toolbar, R.string.drawer_open, R.string.drawer_close);
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

    @Override
    protected void onStop() {
        webView.reload();
        super.onStop();
    }

    @Override
    public void onBackPressed() {
        /*if (webView.canGoBack()) {
            getSupportActionBar().setTitle("");
            webView.goBack();
        } else*/
            super.onBackPressed();
    }
}