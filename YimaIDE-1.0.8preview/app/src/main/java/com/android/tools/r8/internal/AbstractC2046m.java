package com.android.tools.r8.internal;

import com.android.tools.r8.graph.C0333y;

/* JADX INFO: renamed from: com.android.tools.r8.internal.m, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public abstract class AbstractC2046m {
    public static B1 a(C0333y c0333y, B1 b1, B1 b2) {
        if (b1.D()) {
            return b1;
        }
        if (b2.D()) {
            return b2;
        }
        if ((b1 instanceof C2525rc0) && (b2 instanceof C2525rc0)) {
            return c0333y.t.a(((int) b1.e().b) & ((int) b2.e().b));
        }
        if (b1.z() && b2.z()) {
            return c0333y.t.a(b1.v() & b2.v(), b1.w() | b2.w());
        }
        if (b1.z()) {
            return c0333y.t.a(0, b1.w());
        }
        return b2.z() ? c0333y.t.a(0, b2.w()) : Ak0.a;
    }

    public static B1 b(C0333y c0333y, B1 b1, B1 b2) {
        if (b1.D()) {
            return b2;
        }
        if (b2.D()) {
            return b1;
        }
        if ((b1 instanceof C2525rc0) && (b2 instanceof C2525rc0)) {
            return c0333y.t.a(((int) b1.e().b) | ((int) b2.e().b));
        }
        if (b1.z() && b2.z()) {
            return c0333y.t.a(b1.v() | b2.v(), b1.w() & b2.w());
        }
        if (b1.z()) {
            return c0333y.t.a(b1.v(), 0);
        }
        return b2.z() ? c0333y.t.a(b2.v(), 0) : Ak0.a;
    }

    public static B1 a(C0333y c0333y, B1 b1, int i) {
        if (i == 0) {
            return b1;
        }
        b1.getClass();
        if (b1 instanceof C2525rc0) {
            return c0333y.t.a(((int) b1.e().b) << i);
        }
        if (b1.z() && i > 0) {
            return c0333y.t.a(b1.v() << i, (b1.w() << i) | ((1 << i) - 1));
        }
        return Ak0.a;
    }

    public static B1 b(C0333y c0333y, B1 b1, int i) {
        if (i == 0) {
            return b1;
        }
        b1.getClass();
        if (b1 instanceof C2525rc0) {
            return c0333y.t.a(((int) b1.e().b) >> i);
        }
        if (b1.z()) {
            return c0333y.t.a(b1.v() >> i, b1.w() >> i);
        }
        return Ak0.a;
    }
}
