package com.example.androidhrambabynino;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

public class TestActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        hide();
    }

    void hide() {
        if (getSupportActionBar() != null) getSupportActionBar().hide();
        findViewById(R.id.containerTest).setSystemUiVisibility(
                View.INVISIBLE
                        | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);
    }

    void show() {
        if (getSupportActionBar() != null) getSupportActionBar().show();
        findViewById(R.id.containerTest).setSystemUiVisibility(
                View.VISIBLE);
    }

    public void buttonHideOnClick(View view) {
        hide();
    }

    public void buttonShowOnClick(View view) {
        show();
    }
}