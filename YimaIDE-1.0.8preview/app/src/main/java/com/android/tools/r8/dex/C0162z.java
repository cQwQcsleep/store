package com.android.tools.r8.dex;

import com.android.tools.r8.graph.C0294s2;
import com.android.tools.r8.graph.C0301t2;
import com.android.tools.r8.graph.C0306u0;
import com.android.tools.r8.graph.C0308u2;

/* JADX INFO: renamed from: com.android.tools.r8.dex.z, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C0162z {
    public static final C0308u2[] e;
    public static final C0294s2[] f;
    public static final C0301t2[] g = new C0301t2[0];
    public static final C0162z h;
    public final C0306u0 a;
    public final C0294s2[] b;
    public final C0301t2[] c;
    public final C0308u2[] d;

    static {
        C0308u2[] c0308u2Arr = new C0308u2[0];
        e = c0308u2Arr;
        C0294s2[] c0294s2Arr = new C0294s2[0];
        f = c0294s2Arr;
        h = new C0162z(C0306u0.o0(), c0294s2Arr, new C0301t2[0], c0308u2Arr);
    }

    public C0162z(C0306u0 c0306u0, C0294s2[] c0294s2Arr, C0301t2[] c0301t2Arr, C0308u2[] c0308u2Arr) {
        this.a = c0306u0 == null ? C0306u0.o0() : c0306u0;
        this.b = c0294s2Arr == null ? f : c0294s2Arr;
        this.c = c0301t2Arr == null ? g : c0301t2Arr;
        this.d = c0308u2Arr == null ? e : c0308u2Arr;
    }

    public static C0162z a() {
        return h;
    }
}
