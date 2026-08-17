package com.android.tools.r8.internal;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/* JADX INFO: renamed from: com.android.tools.r8.internal.Wi, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C0928Wi extends AbstractC0963Xr {
    public int g;
    public List h;
    public C2401q50 i;

    public C0928Wi() {
        this.h = Collections.EMPTY_LIST;
    }

    public final C0928Wi a(C0954Xi c0954Xi) {
        if (c0954Xi == C0954Xi.h) {
            return this;
        }
        if (this.i == null) {
            if (!c0954Xi.f.isEmpty()) {
                if (this.h.isEmpty()) {
                    this.h = c0954Xi.f;
                    this.g &= -2;
                } else {
                    if ((this.g & 1) == 0) {
                        this.h = new ArrayList(this.h);
                        this.g |= 1;
                    }
                    this.h.addAll(c0954Xi.f);
                }
                p();
            }
        } else if (!c0954Xi.f.isEmpty()) {
            boolean zIsEmpty = this.i.b.isEmpty();
            C2401q50 c2401q50 = this.i;
            if (zIsEmpty) {
                c2401q50.a = null;
                this.h = c0954Xi.f;
                this.g &= -2;
                this.i = null;
            } else {
                c2401q50.a(c0954Xi.f);
            }
        }
        a((AbstractC1102as) c0954Xi);
        p();
        return this;
    }

    @Override // com.android.tools.r8.internal.H0
    public final H0 b(J0 j0) {
        if (j0 instanceof C0954Xi) {
            return a((C0954Xi) j0);
        }
        super.b(j0);
        return this;
    }

    @Override // com.android.tools.r8.internal.SN
    public final TN build() {
        C0954Xi c0954XiI = i();
        if (c0954XiI.a()) {
            return c0954XiI;
        }
        throw H0.c(c0954XiI);
    }

    /* JADX WARN: Code duplicated, block: B:15:0x001d  */
    @Override // com.android.tools.r8.internal.H0
    /* JADX INFO: renamed from: c, reason: merged with bridge method [inline-methods] */
    public final C0928Wi a(AbstractC0663Md abstractC0663Md, C0415Co c0415Co) throws Throwable {
        C0954Xi c0954Xi = null;
        try {
            try {
                a((C0954Xi) C0954Xi.i.a(abstractC0663Md, c0415Co));
                return this;
            } catch (RB e) {
                C0954Xi c0954Xi2 = (C0954Xi) e.b;
                try {
                    throw e.a();
                } catch (Throwable th) {
                    th = th;
                    c0954Xi = c0954Xi2;
                    if (c0954Xi != null) {
                        a(c0954Xi);
                    }
                    throw th;
                }
            }
        } catch (Throwable th2) {
            th = th2;
            if (c0954Xi != null) {
                a(c0954Xi);
            }
            throw th;
        }
    }

    public final Object clone() {
        return (C0928Wi) k();
    }

    @Override // com.android.tools.r8.internal.AbstractC0911Vr
    public final AbstractC0911Vr d(C2712tk0 c2712tk0) {
        this.e = c2712tk0;
        p();
        return this;
    }

    @Override // com.android.tools.r8.internal.H0, com.android.tools.r8.internal.WN
    public final C0955Xj e() {
        return AbstractC0877Uj.i;
    }

    @Override // com.android.tools.r8.internal.H0
    public final J0 h() {
        C0954Xi c0954XiI = i();
        if (c0954XiI.a()) {
            return c0954XiI;
        }
        throw H0.c(c0954XiI);
    }

    @Override // com.android.tools.r8.internal.AbstractC0911Vr
    public final C2123ms n() {
        return AbstractC0877Uj.j.a(C0954Xi.class, C0928Wi.class);
    }

    @Override // com.android.tools.r8.internal.H0
    /* JADX INFO: renamed from: q, reason: merged with bridge method [inline-methods] */
    public final C0954Xi i() {
        C0954Xi c0954Xi = new C0954Xi(this);
        int i = this.g;
        C2401q50 c2401q50 = this.i;
        if (c2401q50 == null) {
            if ((i & 1) != 0) {
                this.h = Collections.unmodifiableList(this.h);
                this.g &= -2;
            }
            c0954Xi.f = this.h;
        } else {
            c0954Xi.f = c2401q50.b();
        }
        o();
        return c0954Xi;
    }

    public C0928Wi(C0859Tr c0859Tr) {
        super(c0859Tr);
        this.h = Collections.EMPTY_LIST;
    }

    @Override // com.android.tools.r8.internal.H0
    public final H0 b(C1856jk c1856jk, Object obj) {
        return (C0928Wi) d(c1856jk, obj);
    }

    @Override // com.android.tools.r8.internal.H0
    public final H0 b(C2712tk0 c2712tk0) {
        this.e = c2712tk0;
        p();
        return this;
    }

    @Override // com.android.tools.r8.internal.WN
    public final J0 b() {
        return C0954Xi.h;
    }

    @Override // com.android.tools.r8.internal.H0
    public final H0 a(C1856jk c1856jk, Object obj) {
        return (C0928Wi) c(c1856jk, obj);
    }

    @Override // com.android.tools.r8.internal.H0
    /* JADX INFO: renamed from: a */
    public final H0 b(J0 j0) {
        if (j0 instanceof C0954Xi) {
            return a((C0954Xi) j0);
        }
        super.b(j0);
        return this;
    }

    @Override // com.android.tools.r8.internal.H0
    public final H0 a(C2712tk0 c2712tk0) {
        return (C0928Wi) c(c2712tk0);
    }
}
