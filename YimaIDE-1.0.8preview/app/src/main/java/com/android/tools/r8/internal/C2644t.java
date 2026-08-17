package com.android.tools.r8.internal;

/* JADX INFO: renamed from: com.android.tools.r8.internal.t, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C2644t {
    public static final C2644t c;
    public static final C2644t d;
    public final boolean a;
    public final Throwable b;

    static {
        if (C.e) {
            d = null;
            c = null;
        } else {
            d = new C2644t(false, null);
            c = new C2644t(true, null);
        }
    }

    public C2644t(boolean z, RuntimeException runtimeException) {
        this.a = z;
        this.b = runtimeException;
    }
}
