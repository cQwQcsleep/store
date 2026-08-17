package com.android.tools.r8.internal;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public abstract class YG {
    public static WG a(String str) {
        WG wg = new VG().a(str).a;
        if (wg != null) {
            return wg;
        }
        defpackage.l0.a("Invalid package pattern: null");
        return null;
    }

    public static XG e() {
        return XG.c;
    }

    public abstract boolean b();

    public abstract boolean c();

    public abstract boolean d();

    public String a() {
        throw new IllegalStateException();
    }
}
