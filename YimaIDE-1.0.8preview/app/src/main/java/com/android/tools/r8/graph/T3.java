package com.android.tools.r8.graph;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public abstract class T3 {
    public static final /* synthetic */ boolean a = true;

    public static boolean a(B3.d dVar, C0306u0 c0306u0) {
        if (!a && dVar == null) {
            x1f.a();
            return false;
        }
        if (!dVar.a() && c0306u0 != null) {
            for (C0285r0 c0285r0 : c0306u0.d) {
                if (!a && c0285r0.o0().f.toString().equals("Ldalvik/annotation/Signature;")) {
                    x1f.a();
                    return false;
                }
            }
        }
        return true;
    }
}
