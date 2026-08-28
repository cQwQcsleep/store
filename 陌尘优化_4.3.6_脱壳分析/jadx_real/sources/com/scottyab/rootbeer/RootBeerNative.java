package com.scottyab.rootbeer;

import core.pro.android.notify.g;

/* loaded from: /workspace/unpacked/classes2.dex */
public class RootBeerNative {
    private static boolean libraryLoaded;

    static {
        try {
            System.loadLibrary("toolChecker");
            libraryLoaded = true;
        } catch (UnsatisfiedLinkError e) {
            g.b(e);
        }
    }

    public native int checkForRoot(Object[] objArr);

    public native int setLogDebugMessages(boolean z);

    public boolean wasNativeLibraryLoaded() {
        return libraryLoaded;
    }
}
