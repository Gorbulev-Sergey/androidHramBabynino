package com.example.androidhrambabynino;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.appcompat.widget.Toolbar;
import androidx.preference.PreferenceFragmentCompat;

import java.util.ArrayList;

public class SettingsActivity extends AppCompatActivity {
    SharedPreferences preferences;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings_activity);
        preferences = getSharedPreferences("my_settings", MODE_PRIVATE);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Spinner spinner = findViewById(R.id.spinner_load_page);
        ArrayList<String> list = new ArrayList<String>() {{
            add("объявления");
            add("новости");
            add("видео");
            add("для клироса");
            add("жития святых");
            add("о нашем храме");
            add("о таинствах");
            add("разное");
            add("расписание");
            add("фотографии");
            add("контакты");
        }};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.spinner_item, R.id.title, list);
        spinner.setAdapter(adapter);

        Switch switcher = findViewById(R.id.switch_theme);
        if (AppCompatDelegate.getDefaultNightMode() == AppCompatDelegate.MODE_NIGHT_YES) {
            switcher.setText("Вкючить светлую тему");
            switcher.setChecked(true);
        } else {
            switcher.setText("Вкючить тёмную тему");
            switcher.setChecked(false);
        }
        switcher.setOnCheckedChangeListener((compoundButton, isCheck) -> {
            if (isCheck) {
                preferences.edit().putBoolean("dark_theme", true).apply();
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                Toast.makeText(SettingsActivity.this, "Тёмная тема включена", Toast.LENGTH_SHORT).show();
            } else {
                preferences.edit().putBoolean("dark_theme", false).apply();
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                Toast.makeText(SettingsActivity.this, "Светлая тема включена", Toast.LENGTH_SHORT).show();
            }
        });
    }
}