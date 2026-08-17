package com.android.tools.r8.internal;

import defpackage.x0g;

/* JADX INFO: renamed from: com.android.tools.r8.internal.qS, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public class C2427qS {
    public static final C2427qS a = new C2427qS();
    public static final C2427qS b = new C2427qS();
    public static final C2427qS c = new C2427qS();
    public static final C2427qS d = new C2427qS();

    public static C2427qS b() {
        return b;
    }

    public static C2427qS c() {
        return a;
    }

    public static C2427qS h() {
        return c;
    }

    public final C2427qS a(C2427qS c2427qS) {
        C2427qS c2427qS2 = d;
        if (this == c2427qS2) {
            return c2427qS;
        }
        return (c2427qS == c2427qS2 || this == c2427qS) ? this : c;
    }

    public boolean d() {
        return this == b;
    }

    public boolean e() {
        return this == a;
    }

    public boolean f() {
        return this == c;
    }

    public final boolean g() {
        return f() || e();
    }

    public final String toString() {
        if (this == c) {
            return "@Nullable";
        }
        if (this == a) {
            return "@Null";
        }
        if (this == b) {
            return "@NotNull";
        }
        if (this == d) {
            return "@Bottom";
        }
        x0g.a("Unknown Nullability.");
        return null;
    }

    public static C2427qS a() {
        return d;
    }
}
