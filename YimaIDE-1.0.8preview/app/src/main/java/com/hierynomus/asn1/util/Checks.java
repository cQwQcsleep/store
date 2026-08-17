package com.hierynomus.asn1.util;

/* JADX INFO: loaded from: /workspace/dex_all/classes6.dex */
public class Checks {
    private Checks() {
    }

    public static void checkArgument(boolean z, String str, Object... objArr) {
        if (!z) {
            throw new IllegalArgumentException(String.format(str, objArr));
        }
    }

    public static void checkState(boolean z, String str, Object... objArr) {
        if (!z) {
            throw new IllegalStateException(String.format(str, objArr));
        }
    }
}
