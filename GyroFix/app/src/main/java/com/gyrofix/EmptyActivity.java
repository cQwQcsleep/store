package com.gyrofix;

import android.app.Activity;
import android.os.Bundle;

/** 原版 7.0：透明中转页（折叠磁贴面板用） */
public class EmptyActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(android.R.style.Theme_NoDisplay);
        super.onCreate(savedInstanceState);
        finish();
    }
}