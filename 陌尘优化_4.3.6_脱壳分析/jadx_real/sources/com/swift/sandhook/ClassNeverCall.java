package com.swift.sandhook;

import android.util.Log;
import obfuse.NPStringFog;

/* loaded from: /workspace/unpacked/classes.dex */
public class ClassNeverCall {
    private void neverCall() {
    }

    private void neverCall2() {
        Log.e(NPStringFog.decode("2D1C0C121D2F0213171C330C0D02"), "ClassNeverCall2");
    }

    private native void neverCallNative();

    private native void neverCallNative2();

    private static void neverCallStatic() {
    }
}
