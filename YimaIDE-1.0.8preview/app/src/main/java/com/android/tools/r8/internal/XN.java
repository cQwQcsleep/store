package com.android.tools.r8.internal;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class XN implements ZN {
    public final H0 a;

    public XN(H0 h0) {
        this.a = h0;
    }

    @Override // com.android.tools.r8.internal.ZN
    public final J0 a(AbstractC0663Md abstractC0663Md, C0415Co c0415Co, C1856jk c1856jk) {
        J0 j0;
        H0 h0C = this.a.c(c1856jk);
        if (!c1856jk.m() && (j0 = (J0) this.a.a(c1856jk)) != null) {
            h0C.b(j0);
        }
        abstractC0663Md.a(c1856jk.c.g, h0C, c0415Co);
        return h0C.i();
    }

    @Override // com.android.tools.r8.internal.ZN
    public final J0 b(AbstractC0663Md abstractC0663Md, C0415Co c0415Co, C1856jk c1856jk) {
        J0 j0;
        H0 h0C = this.a.c(c1856jk);
        if (!c1856jk.m() && (j0 = (J0) this.a.a(c1856jk)) != null) {
            h0C.b(j0);
        }
        abstractC0663Md.a(h0C, c0415Co);
        return h0C.i();
    }

    @Override // com.android.tools.r8.internal.ZN
    public final ZN b(C1856jk c1856jk, Object obj) {
        this.a.a(c1856jk, obj);
        return this;
    }

    @Override // com.android.tools.r8.internal.ZN
    public final void a(C3144yo c3144yo, C0955Xj c0955Xj, int i) {
        if (c3144yo.d.get(new C3060xo(c0955Xj, i)) != null) {
            throw new ClassCastException();
        }
    }

    @Override // com.android.tools.r8.internal.ZN
    public final ZN a(C1856jk c1856jk, Object obj) {
        this.a.b(c1856jk, obj);
        return this;
    }

    @Override // com.android.tools.r8.internal.ZN
    public final int a() {
        return 1;
    }

    @Override // com.android.tools.r8.internal.ZN
    public final int a(C1856jk c1856jk) {
        if (c1856jk.n()) {
            return 2;
        }
        c1856jk.m();
        return 1;
    }
}
