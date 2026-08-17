package com.android.tools.r8.graph;

import com.android.tools.r8.internal.AbstractC2554rv;
import com.android.tools.r8.internal.C1870jv;

/* JADX INFO: renamed from: com.android.tools.r8.graph.y0, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C0334y0 {
    public boolean a;
    public boolean b;
    public final C1870jv c;
    public final C1870jv d;
    public final C1870jv e;

    public C0334y0() {
        int i = AbstractC2554rv.c;
        this.c = new C1870jv();
        this.d = new C1870jv();
        this.e = new C1870jv();
    }

    public final void a(I2 i2) {
        synchronized (this.e) {
            this.e.a(i2);
        }
    }

    public final void b(I2 i2) {
        synchronized (this.d) {
            this.d.a(i2);
        }
    }
}
