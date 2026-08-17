package com.android.tools.r8.internal;

import com.android.tools.r8.graph.C0245l1;
import com.android.tools.r8.graph.C0322w2;
import java.util.function.Consumer;

/* JADX INFO: renamed from: com.android.tools.r8.internal.Xo, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C0960Xo {
    public static final /* synthetic */ boolean e = true;
    public C0245l1 a;
    public AbstractC2173nV b;
    public AbstractC2173nV c;
    public C0322w2 d;

    public C0960Xo() {
        AbstractC2173nV abstractC2173nV = AbstractC2173nV.c;
        this.b = abstractC2173nV;
        this.c = abstractC2173nV;
    }

    public final com.android.tools.r8.graph.G a() {
        int iMax;
        boolean z = e;
        if (!z) {
            if (!z && this.a == null) {
                x1f.a();
                return null;
            }
            if (!z && this.b.e()) {
                x1f.a();
                return null;
            }
            if (!z && this.c.e()) {
                x1f.a();
                return null;
            }
            if (!z && this.d == null) {
                x1f.a();
                return null;
            }
        }
        C0473Eu c0473EuG = AbstractC0551Hu.g();
        int iC = 0;
        if (this.b.d()) {
            c0473EuG.a(new P9(El0.b, 0));
            iMax = 1;
            iC = 1;
        } else {
            iMax = 0;
        }
        if (this.c.d()) {
            El0 el0A = El0.a(this.a.getType());
            c0473EuG.a(new P9(el0A, iC));
            iMax += el0A.c();
            iC += el0A.c();
        }
        int i = iC;
        int iA = Y6.a(this.c.d()) + 178 + (this.b.f() << 1);
        C0245l1 c0245l1 = this.a;
        c0473EuG.a(AbstractC1638h9.a(iA, c0245l1, c0245l1));
        if (this.c.d()) {
            c0473EuG.a(new C3034xa());
        } else {
            El0 el0A2 = El0.a(this.a.getType());
            iMax = Math.max(el0A2.c(), iMax);
            c0473EuG.a(new C2948wa(el0A2));
        }
        int i2 = iMax;
        P40 p40 = P40.e;
        return new com.android.tools.r8.graph.G(this.d.w0(), i2, i, c0473EuG.a(), p40, p40);
    }

    public final C0960Xo b() {
        this.c = AbstractC2173nV.b;
        return this;
    }

    public final C0960Xo c() {
        this.c = AbstractC2173nV.a;
        return this;
    }

    public final C0960Xo a(boolean z, Consumer consumer, Consumer consumer2) {
        if (!z) {
            consumer = consumer2;
        }
        consumer.accept(this);
        return this;
    }
}
