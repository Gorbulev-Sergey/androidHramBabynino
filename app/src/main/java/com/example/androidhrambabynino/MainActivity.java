package com.example.androidhrambabynino;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
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

        //AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        setContentView(R.layout.activity_main);

        toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        drawer = findViewById(R.id.main_layout);
        drawer.addDrawerListener(toggler);

        toggler = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.drawer_open, R.string.drawer_close);
        toggler.setDrawerIndicatorEnabled(true);
        toggler.syncState();

        navigation = findViewById(R.id.navigation);
        setupDrawerContent(navigation);

        webview = findViewById(R.id.webview);
        myWebViewClient = new MyWebViewClient(this);
        webview.setWebViewClient(myWebViewClient);
        webview.setBackgroundColor(Color.TRANSPARENT);
        webview.getSettings().setSupportZoom(true);
        webview.getSettings().setDomStorageEnabled(true);
        webview.getSettings().setJavaScriptEnabled(true);

        selectDrawerItem(navigation.getMenu().findItem(R.id.nav_schedule));
    }

    private void selectDrawerItem(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.nav_posts:
                webview.loadUrl("https://hram-babynino.somee.com/android/posts/");
                break;
            case R.id.nav_anons:
                webview.loadUrl("https://hram-babynino.somee.com/android/posts/объявления");
                break;
            case R.id.nav_news:
                webview.loadUrl("https://hram-babynino.somee.com/android/posts/новости");
                break;
            case R.id.nav_video:
                webview.loadUrl("https://hram-babynino.somee.com/android/posts/видео");
                break;
            case R.id.nav_for_kliros:
                webview.loadUrl("https://hram-babynino.somee.com/android/posts/для клироса");
                break;
            case R.id.nav_life:
                webview.loadUrl("https://hram-babynino.somee.com/android/posts/жития святых");
                break;
            case R.id.nav_our_chirch:
                webview.loadUrl("https://hram-babynino.somee.com/android/posts/о нашем храме");
                break;
            case R.id.nav_tainstva:
                webview.loadUrl("https://hram-babynino.somee.com/android/posts/о таинствах");
                break;
            case R.id.nav_raznoe:
                webview.loadUrl("https://hram-babynino.somee.com/android/posts/разное");
                break;

            case R.id.nav_schedule:
                webview.loadUrl("https://hram-babynino.somee.com/android/schedule");
                break;
            case R.id.nav_photos:
                webview.loadUrl("https://hram-babynino.somee.com/android/photos");
                break;
            case R.id.nav_contacts:
                webview.loadUrl("https://hram-babynino.somee.com/android/contacts");
                break;
            default:
                webview.loadUrl("https://hram-babynino.somee.com/android/posts");
                break;
        }

        toolbar.setTitle(item.getTitle().toString().replace("  ",""));
        item.setChecked(true);
        drawer.closeDrawers();
    }

    private void setupDrawerContent(NavigationView navigationView) {
        navigationView.setNavigationItemSelectedListener(
                menuItem -> {
                    selectDrawerItem(menuItem);
                    return true;
                });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.additional_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.action_settings:
                startActivity(new Intent(this, SettingsActivity.class));
                break;
        }
        return super.onOptionsItemSelected(item);
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