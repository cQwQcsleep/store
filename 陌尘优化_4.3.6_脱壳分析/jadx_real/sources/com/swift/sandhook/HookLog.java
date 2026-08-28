package com.swift.sandhook;

import android.util.Log;
import obfuse.NPStringFog;

/* loaded from: /workspace/unpacked/classes.dex */
public class HookLog {
    public static boolean DEBUG = SandHookConfig.DEBUG;
    public static final String TAG = "SandHook";

    public static int d(String str) {
        return Log.d(NPStringFog.decode("3D110305260E080E"), str);
    }

    public static int e(String str) {
        return Log.e(NPStringFog.decode("3D110305260E080E"), str);
    }

    public static int e(String str, Throwable th) {
        return Log.e(NPStringFog.decode("3D110305260E080E"), str, th);
    }

    public static int i(String str) {
        return Log.i(NPStringFog.decode("3D110305260E080E"), str);
    }

    public static int v(String str) {
        return Log.v(NPStringFog.decode("3D110305260E080E"), str);
    }

    public static int w(String str) {
        return Log.w(NPStringFog.decode("3D110305260E080E"), str);
    }
}
