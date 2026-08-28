package com.swift.sandhook;

import android.util.Log;
import obfuse.NPStringFog;

/* loaded from: /workspace/unpacked/classes.dex */
public class HookLog {
    public static boolean DEBUG = SandHookConfig.DEBUG;
    public static final String TAG = "SandHook";

    public static int d(String str) {
        return Log.d("SandHook", str);
    }

    public static int e(String str) {
        return Log.e("SandHook", str);
    }

    public static int e(String str, Throwable th) {
        return Log.e("SandHook", str, th);
    }

    public static int i(String str) {
        return Log.i("SandHook", str);
    }

    public static int v(String str) {
        return Log.v("SandHook", str);
    }

    public static int w(String str) {
        return Log.w("SandHook", str);
    }
}
