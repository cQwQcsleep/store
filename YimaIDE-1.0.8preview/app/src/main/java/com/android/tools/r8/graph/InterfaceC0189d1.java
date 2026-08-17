package com.android.tools.r8.graph;

/* JADX INFO: renamed from: com.android.tools.r8.graph.d1, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public interface InterfaceC0189d1 {
    B1 a();

    default F0 a(C0245l1 c0245l1) {
        C0210g1 c0210g1A;
        E0 e0D = d(c0245l1.w0());
        if (e0D == null || (c0210g1A = e0D.a(c0245l1)) == null) {
            return null;
        }
        return F0.a(e0D, c0210g1A);
    }

    default H0 b(C0322w2 c0322w2) {
        E0 e0D = d(c0322w2.w0());
        if (e0D != null) {
            return e0D.a(c0322w2);
        }
        return null;
    }

    E0 d(I2 i2);

    default boolean f(I2 i2) {
        return d(i2) != null;
    }

    InterfaceC0174b0 g(I2 i2);

    default D2 b(I2 i2) {
        return D2.b(d(i2));
    }

    default E0 a(I2 i2) {
        return d(i2);
    }

    default E0 a(D2 d2, I2 i2) {
        return i2 == d2.e ? d2 : a(i2);
    }

    default E0 a(I2 i2, B5 b5) {
        return a(b5.a(), i2);
    }

    default boolean a(C0322w2 c0322w2) {
        return b(c0322w2) != null;
    }

    default E0 a(AbstractC0287r2 abstractC0287r2) {
        return d(abstractC0287r2.f);
    }
}
