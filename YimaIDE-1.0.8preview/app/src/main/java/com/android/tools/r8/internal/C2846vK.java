package com.android.tools.r8.internal;

/* JADX INFO: renamed from: com.android.tools.r8.internal.vK, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C2846vK extends AbstractC3187zK {
    public C2846vK(C2932wK c2932wK) {
        super(c2932wK.b);
    }

    @Override // java.util.Iterator
    public final Object next() {
        AK ak = this.b;
        BK bk = this.e;
        if (ak == bk.g) {
            z0e.a();
            return null;
        }
        if (bk.f != this.d) {
            a1e.a();
            return null;
        }
        this.b = ak.e;
        this.c = ak;
        return ak;
    }
}
