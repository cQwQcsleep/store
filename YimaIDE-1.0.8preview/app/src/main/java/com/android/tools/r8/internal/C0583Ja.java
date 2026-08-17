package com.android.tools.r8.internal;

/* JADX INFO: renamed from: com.android.tools.r8.internal.Ja, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C0583Ja {
    public static final /* synthetic */ boolean e = true;
    public final int a;
    public final El0 b;
    public final com.android.tools.r8.graph.I2 c;
    public final AbstractC0660Ma d;

    public C0583Ja(int i, AbstractC0660Ma abstractC0660Ma) {
        this.a = i;
        this.d = abstractC0660Ma;
        this.b = abstractC0660Ma.a();
        this.c = abstractC0660Ma.b();
    }

    public static boolean a(int i) {
        return i >= 100000;
    }

    public static int b(int i) {
        boolean z = e;
        if (!z && !a(i)) {
            x1f.a();
            return 0;
        }
        if (z || i >= 100000) {
            return i - 100000;
        }
        x1f.a();
        return 0;
    }

    public final String toString() {
        int i = this.a;
        AbstractC0660Ma abstractC0660Ma = this.d;
        if (i < 100000) {
            return i + "=" + abstractC0660Ma;
        }
        return "s" + (i - 100000) + "=" + abstractC0660Ma;
    }

    public final boolean a() {
        AbstractC0660Ma abstractC0660Ma = this.d;
        abstractC0660Ma.getClass();
        return abstractC0660Ma instanceof C0635La;
    }
}
