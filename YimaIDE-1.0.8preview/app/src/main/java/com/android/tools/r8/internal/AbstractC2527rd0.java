package com.android.tools.r8.internal;

/* JADX INFO: renamed from: com.android.tools.r8.internal.rd0, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public abstract class AbstractC2527rd0 {
    public static final boolean a;
    public static final C2014ld0 b;
    public static final C2185nd0 c;
    public static final C2357pd0 d;

    static {
        boolean z;
        try {
            Class.forName("java.sql.Date");
            z = true;
        } catch (ClassNotFoundException unused) {
            z = false;
        }
        a = z;
        if (z) {
            b = C2099md0.b;
            c = C2271od0.b;
            d = C2442qd0.b;
        } else {
            b = null;
            c = null;
            d = null;
        }
    }
}
