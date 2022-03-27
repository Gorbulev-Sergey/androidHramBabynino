package com.example.androidhrambabynino;

import android.app.Activity;
import android.content.Intent;
import android.webkit.JavascriptInterface;

public class MyJSInterface {
    private Activity activity;

    public  MyJSInterface(Activity activity){
        this.activity = activity;
    }

    @JavascriptInterface
    public void goToFullscreen(String albumId){
        Intent intent = new Intent(activity, FullscreenActivity.class);
        intent.putExtra("url","https://hram-babynino.somee.com/android/photos/" + albumId);
        activity.startActivity(intent);
    }

    @JavascriptInterface
    public void returnBack(){
        Intent intent = new Intent(activity, MainActivity.class);
        intent.putExtra("url","https://hram-babynino.somee.com/android/photos");
        activity.startActivity(intent);
    }
}
