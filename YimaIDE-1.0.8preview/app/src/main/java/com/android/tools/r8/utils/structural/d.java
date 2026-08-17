package com.android.tools.r8.utils.structural;

import com.android.tools.r8.graph.C0245l1;
import com.android.tools.r8.graph.C0322w2;
import com.android.tools.r8.graph.H2;
import com.android.tools.r8.graph.I2;
import com.android.tools.r8.naming.AbstractC3345r0;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public class d extends c {
    public final AbstractC3345r0 b;

    public d(AbstractC3345r0 abstractC3345r0) {
        this.b = abstractC3345r0;
    }

    @Override // com.android.tools.r8.utils.structural.AbstractC3519a
    public int a(C0245l1 c0245l1, C0245l1 c0245l2) {
        if (c0245l1 == c0245l2) {
            return 0;
        }
        I2 i2 = c0245l1.f;
        I2 i3 = c0245l2.f;
        i2.getClass();
        int iA = a(i2, i3);
        if (iA != 0) {
            return iA;
        }
        H2 h2A = this.b.a(c0245l1);
        H2 h2A2 = this.b.a(c0245l2);
        h2A.getClass();
        int iA2 = a(h2A, h2A2);
        if (iA2 != 0) {
            return iA2;
        }
        I2 i4 = c0245l1.i;
        I2 i5 = c0245l2.i;
        i4.getClass();
        return a(i4, i5);
    }

    @Override // com.android.tools.r8.utils.structural.AbstractC3519a
    public int a(I2 i2, I2 i3) {
        if (i2 == i3) {
            return 0;
        }
        H2 h2C = this.b.c(i2);
        H2 h2C2 = this.b.c(i3);
        h2C.getClass();
        return a(h2C, h2C2);
    }

    @Override // com.android.tools.r8.utils.structural.AbstractC3519a
    public int a(C0322w2 c0322w2, C0322w2 c0322w3) {
        if (c0322w2 == c0322w3) {
            return 0;
        }
        I2 i2 = c0322w2.f;
        I2 i3 = c0322w3.f;
        i2.getClass();
        int iA = a(i2, i3);
        if (iA != 0) {
            return iA;
        }
        H2 h2A = this.b.a(c0322w2);
        H2 h2A2 = this.b.a(c0322w3);
        h2A.getClass();
        int iA2 = a(h2A, h2A2);
        return iA2 != 0 ? iA2 : c0322w2.i.a(c0322w3.i, (AbstractC3519a) this);
    }
}
