package com.android.tools.r8.internal;

import java.util.logging.Logger;

/* JADX INFO: renamed from: com.android.tools.r8.internal.pk, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public abstract class AbstractC2370pk {
    public static final Logger a = Logger.getLogger(AbstractC2370pk.class.getName());
    public static final int[] b = new int[0];
    public static final C0955Xj[] c = new C0955Xj[0];
    public static final C1856jk[] d = new C1856jk[0];
    public static final C1260ck[] e = new C1260ck[0];
    public static final C2284ok[] f = new C2284ok[0];
    public static final C2198nk[] g = new C2198nk[0];

    public static String a(C1941kk c1941kk, C0955Xj c0955Xj, String str) {
        if (c0955Xj != null) {
            return c0955Xj.c + '.' + str;
        }
        String strM = c1941kk.b.m();
        if (strM.isEmpty()) {
            return str;
        }
        return strM + '.' + str;
    }
}
