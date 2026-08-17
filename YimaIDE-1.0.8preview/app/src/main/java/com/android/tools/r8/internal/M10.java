package com.android.tools.r8.internal;

import java.io.UTFDataFormatException;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class M10 extends AbstractC2193nh0 {
    public final com.android.tools.r8.graph.G2 a;

    public M10(com.android.tools.r8.graph.H2 h2) {
        h2.getClass();
        this.a = new com.android.tools.r8.graph.G2(h2);
    }

    @Override // com.android.tools.r8.internal.AbstractC2193nh0
    public final boolean a() {
        return this.a.a();
    }

    @Override // com.android.tools.r8.internal.AbstractC2193nh0
    public final int b() throws N10 {
        if (!this.a.a()) {
            z0e.a();
            return 0;
        }
        int i = 0;
        int i2 = 0;
        do {
            try {
                char cB = this.a.b();
                if (cB >= 55296 && cB < 57344) {
                    throw new N10();
                }
                if (cB < 55296) {
                    return (cB << i2) | i;
                }
                i |= (cB & 8191) << i2;
                i2 += 13;
            } catch (UTFDataFormatException unused) {
                throw new N10();
            }
        } while (this.a.a());
        throw new N10();
    }
}
