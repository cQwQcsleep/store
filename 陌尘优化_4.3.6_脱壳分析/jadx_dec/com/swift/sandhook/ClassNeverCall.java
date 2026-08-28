package com.swift.sandhook;

import android.util.Log;
import obfuse.NPStringFog;

/* loaded from: /workspace/unpacked/classes.dex */
public class ClassNeverCall {
    private void neverCall() {
    }

    private void neverCall2() {
        Log.e("ClassNeverCall", "ClassNeverCall2");
    }

    private native void neverCallNative();

    private native void neverCallNative2();

    private static void neverCallStatic() {
    }
}
