package com.example.androidhrambabynino;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.webkit.JavascriptInterface;
import android.widget.Button;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

public class MyJSInterface{
    Context context;

    public MyJSInterface(Context c){
        context = c;
    }

    @JavascriptInterface
    public void activateDarkTheme(){

    }

    @JavascriptInterface
    public void activateLightTheme(){

    }
}
