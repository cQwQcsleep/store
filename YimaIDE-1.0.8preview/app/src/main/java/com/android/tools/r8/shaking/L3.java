package com.android.tools.r8.shaking;

import com.android.tools.r8.internal.AbstractC0551Hu;
import com.android.tools.r8.internal.Bc0;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class L3 extends K3 {
    public static final L3 b = new L3();
    public final S3 a;

    public L3() {
        this.a = new S3.b("***");
    }

    @Override // com.android.tools.r8.shaking.K3
    public final K3 a(com.android.tools.r8.graph.B1 b1) {
        return new L3(this.a.f());
    }

    @Override // com.android.tools.r8.shaking.K3
    public final boolean b(com.android.tools.r8.graph.I2 i2) {
        this.a.a(i2.m0());
        return true;
    }

    @Override // com.android.tools.r8.shaking.K3
    public final Iterable c() {
        S3 s3 = this.a;
        int i = AbstractC0551Hu.c;
        return new Bc0(s3);
    }

    @Override // com.android.tools.r8.shaking.K3
    public final boolean equals(Object obj) {
        return obj instanceof L3;
    }

    @Override // com.android.tools.r8.shaking.K3
    public final int hashCode() {
        return L3.class.hashCode();
    }

    @Override // com.android.tools.r8.shaking.K3
    public final String toString() {
        return "***";
    }

    public L3(S3 s3) {
        this.a = s3;
    }
}
