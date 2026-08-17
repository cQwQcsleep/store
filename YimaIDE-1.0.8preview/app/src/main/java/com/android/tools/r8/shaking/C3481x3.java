package com.android.tools.r8.shaking;

import com.android.tools.r8.internal.AbstractC0551Hu;
import com.android.tools.r8.internal.Bc0;

/* JADX INFO: renamed from: com.android.tools.r8.shaking.x3, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C3481x3 extends A3 {
    public final S3 c;

    public C3481x3() {
        this.c = new S3.b("*");
    }

    @Override // com.android.tools.r8.shaking.A3
    public final Iterable a() {
        S3 s3 = this.c;
        int i = AbstractC0551Hu.c;
        return new Bc0(s3);
    }

    @Override // com.android.tools.r8.shaking.A3
    public final A3 b() {
        return new C3481x3(this.c.f());
    }

    public final String toString() {
        return "*";
    }

    @Override // com.android.tools.r8.shaking.A3
    public final boolean a(String str) {
        this.c.a(str);
        return true;
    }

    public C3481x3(S3 s3) {
        this.c = s3;
    }
}
