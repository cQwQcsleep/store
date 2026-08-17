package com.android.tools.r8.internal;

import org.xmlpull.v1.XmlPullParser;

/* JADX INFO: renamed from: com.android.tools.r8.internal.Lj, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C0644Lj extends AbstractC0911Vr {
    public int f;
    public InterfaceC1216cB g;
    public InterfaceC1216cB h;
    public Object i;
    public Object j;
    public AJ k;

    public C0644Lj() {
        super(null);
        C0945Wz c0945Wz = C0945Wz.e;
        this.g = c0945Wz;
        this.h = c0945Wz;
        this.i = XmlPullParser.NO_NAMESPACE;
        this.j = XmlPullParser.NO_NAMESPACE;
        this.k = C3101yJ.d;
    }

    public final C0644Lj a(C0669Mj c0669Mj) {
        if (c0669Mj == C0669Mj.n) {
            return this;
        }
        if (!c0669Mj.f.isEmpty()) {
            if (this.g.isEmpty()) {
                this.g = c0669Mj.f;
                this.f &= -2;
            } else {
                if ((this.f & 1) == 0) {
                    this.g = AbstractC2209ns.a(this.g);
                    this.f |= 1;
                }
                ((C0945Wz) this.g).addAll(c0669Mj.f);
            }
            p();
        }
        if (!c0669Mj.h.isEmpty()) {
            if (this.h.isEmpty()) {
                this.h = c0669Mj.h;
                this.f &= -3;
            } else {
                if ((this.f & 2) == 0) {
                    this.h = AbstractC2209ns.a(this.h);
                    this.f |= 2;
                }
                ((C0945Wz) this.h).addAll(c0669Mj.h);
            }
            p();
        }
        if ((c0669Mj.e & 1) != 0) {
            this.f |= 4;
            this.i = c0669Mj.j;
            p();
        }
        if ((c0669Mj.e & 2) != 0) {
            this.f |= 8;
            this.j = c0669Mj.k;
            p();
        }
        if (!c0669Mj.l.isEmpty()) {
            if (this.k.isEmpty()) {
                this.k = c0669Mj.l;
                this.f &= -17;
            } else {
                if ((this.f & 16) == 0) {
                    this.k = new C3101yJ(this.k);
                    this.f |= 16;
                }
                this.k.addAll(c0669Mj.l);
            }
            p();
        }
        p();
        return this;
    }

    @Override // com.android.tools.r8.internal.H0
    public final H0 b(J0 j0) {
        if (j0 instanceof C0669Mj) {
            return a((C0669Mj) j0);
        }
        super.b(j0);
        return this;
    }

    @Override // com.android.tools.r8.internal.SN
    public final TN build() {
        C0669Mj c0669MjI = i();
        if (c0669MjI.a()) {
            return c0669MjI;
        }
        throw H0.c(c0669MjI);
    }

    /* JADX WARN: Code duplicated, block: B:15:0x001d  */
    @Override // com.android.tools.r8.internal.H0
    /* JADX INFO: renamed from: c, reason: merged with bridge method [inline-methods] */
    public final C0644Lj a(AbstractC0663Md abstractC0663Md, C0415Co c0415Co) throws Throwable {
        C0669Mj c0669Mj = null;
        try {
            try {
                a((C0669Mj) C0669Mj.o.a(abstractC0663Md, c0415Co));
                return this;
            } catch (RB e) {
                C0669Mj c0669Mj2 = (C0669Mj) e.b;
                try {
                    throw e.a();
                } catch (Throwable th) {
                    th = th;
                    c0669Mj = c0669Mj2;
                    if (c0669Mj != null) {
                        a(c0669Mj);
                    }
                    throw th;
                }
            }
        } catch (Throwable th2) {
            th = th2;
            if (c0669Mj != null) {
                a(c0669Mj);
            }
            throw th;
        }
    }

    public final Object clone() {
        return (C0644Lj) k();
    }

    @Override // com.android.tools.r8.internal.AbstractC0911Vr
    public final AbstractC0911Vr d(C2712tk0 c2712tk0) {
        this.e = c2712tk0;
        p();
        return this;
    }

    @Override // com.android.tools.r8.internal.H0, com.android.tools.r8.internal.WN
    public final C0955Xj e() {
        return AbstractC0877Uj.U;
    }

    @Override // com.android.tools.r8.internal.H0
    public final J0 h() {
        C0669Mj c0669MjI = i();
        if (c0669MjI.a()) {
            return c0669MjI;
        }
        throw H0.c(c0669MjI);
    }

    @Override // com.android.tools.r8.internal.AbstractC0911Vr
    public final C2123ms n() {
        return AbstractC0877Uj.V.a(C0669Mj.class, C0644Lj.class);
    }

    @Override // com.android.tools.r8.internal.H0
    /* JADX INFO: renamed from: q, reason: merged with bridge method [inline-methods] */
    public final C0669Mj i() {
        C0669Mj c0669Mj = new C0669Mj(this);
        int i = this.f;
        if ((i & 1) != 0) {
            ((AbstractC2220o1) this.g).b = false;
            this.f = i & (-2);
        }
        c0669Mj.f = this.g;
        int i2 = this.f;
        if ((i2 & 2) != 0) {
            ((AbstractC2220o1) this.h).b = false;
            this.f = i2 & (-3);
        }
        c0669Mj.h = this.h;
        int i3 = (i & 4) != 0 ? 1 : 0;
        c0669Mj.j = this.i;
        if ((i & 8) != 0) {
            i3 |= 2;
        }
        c0669Mj.k = this.j;
        if ((this.f & 16) != 0) {
            this.k = this.k.f();
            this.f &= -17;
        }
        c0669Mj.l = this.k;
        c0669Mj.e = i3;
        o();
        return c0669Mj;
    }

    @Override // com.android.tools.r8.internal.H0
    public final H0 b(C2712tk0 c2712tk0) {
        this.e = c2712tk0;
        p();
        return this;
    }

    @Override // com.android.tools.r8.internal.H0
    public final H0 b(C1856jk c1856jk, Object obj) {
        C2123ms.a(n(), c1856jk).b(this, obj);
        return this;
    }

    @Override // com.android.tools.r8.internal.WN
    public final J0 b() {
        return C0669Mj.n;
    }

    public C0644Lj(C0859Tr c0859Tr) {
        super(c0859Tr);
        C0945Wz c0945Wz = C0945Wz.e;
        this.g = c0945Wz;
        this.h = c0945Wz;
        this.i = XmlPullParser.NO_NAMESPACE;
        this.j = XmlPullParser.NO_NAMESPACE;
        this.k = C3101yJ.d;
    }

    @Override // com.android.tools.r8.internal.H0
    /* JADX INFO: renamed from: a */
    public final H0 b(J0 j0) {
        if (j0 instanceof C0669Mj) {
            return a((C0669Mj) j0);
        }
        super.b(j0);
        return this;
    }

    @Override // com.android.tools.r8.internal.H0
    public final H0 a(C2712tk0 c2712tk0) {
        return (C0644Lj) c(c2712tk0);
    }

    @Override // com.android.tools.r8.internal.H0
    public final H0 a(C1856jk c1856jk, Object obj) {
        C2123ms.a(n(), c1856jk).a(this, obj);
        return this;
    }
}
