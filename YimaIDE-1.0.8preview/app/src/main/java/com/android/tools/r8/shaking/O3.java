package com.android.tools.r8.shaking;

import com.android.tools.r8.internal.AbstractC0551Hu;
import com.android.tools.r8.internal.Bc0;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class O3 extends K3 {
    public static final O3 c = new O3("**", new S3.b("**"));
    public static final O3 d = new O3("*", new S3.b("*"));
    public static final /* synthetic */ boolean e = true;
    public final String a;
    public final S3 b;

    public O3(String str, S3 s3) {
        if (!e && !str.equals("*") && !str.equals("**")) {
            x1f.a();
            throw null;
        }
        this.a = str;
        this.b = s3;
    }

    @Override // com.android.tools.r8.shaking.K3
    public final K3 a(com.android.tools.r8.graph.B1 b1) {
        return new O3(this.a, this.b.f());
    }

    @Override // com.android.tools.r8.shaking.K3
    public final boolean b(com.android.tools.r8.graph.I2 i2) {
        if (!i2.M0()) {
            return false;
        }
        this.b.a(i2.m0());
        return true;
    }

    @Override // com.android.tools.r8.shaking.K3
    public final Iterable c() {
        S3 s3 = this.b;
        int i = AbstractC0551Hu.c;
        return new Bc0(s3);
    }

    @Override // com.android.tools.r8.shaking.K3
    public final boolean d() {
        return true;
    }

    @Override // com.android.tools.r8.shaking.K3
    public final boolean equals(Object obj) {
        return (obj instanceof O3) && this.a.equals(((O3) obj).a);
    }

    @Override // com.android.tools.r8.shaking.K3
    public final int hashCode() {
        return this.a.hashCode();
    }

    @Override // com.android.tools.r8.shaking.K3
    public final String toString() {
        return this.a;
    }
}
