package com.android.tools.r8.internal;

import com.sun.jna.platform.linux.Fcntl;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.xmlpull.v1.XmlPullParser;

/* JADX INFO: renamed from: com.android.tools.r8.internal.kj, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C1939kj extends AbstractC0963Xr {
    public Object A;
    public List B;
    public C2401q50 C;
    public int g;
    public Object h;
    public Object i;
    public boolean j;
    public boolean k;
    public boolean l;
    public int m;
    public Object n;
    public boolean o;
    public boolean p;
    public boolean q;
    public boolean r;
    public boolean s;
    public boolean t;
    public Object u;
    public Object v;
    public Object w;
    public Object x;
    public Object y;
    public Object z;

    public C1939kj() {
        this.h = XmlPullParser.NO_NAMESPACE;
        this.i = XmlPullParser.NO_NAMESPACE;
        this.m = 1;
        this.n = XmlPullParser.NO_NAMESPACE;
        this.t = true;
        this.u = XmlPullParser.NO_NAMESPACE;
        this.v = XmlPullParser.NO_NAMESPACE;
        this.w = XmlPullParser.NO_NAMESPACE;
        this.x = XmlPullParser.NO_NAMESPACE;
        this.y = XmlPullParser.NO_NAMESPACE;
        this.z = XmlPullParser.NO_NAMESPACE;
        this.A = XmlPullParser.NO_NAMESPACE;
        this.B = Collections.EMPTY_LIST;
    }

    public final C1939kj a(C2110mj c2110mj) {
        char c;
        if (c2110mj == C2110mj.C) {
            return this;
        }
        int i = 1;
        if ((c2110mj.f & 1) != 0) {
            this.g |= 1;
            this.h = c2110mj.g;
            p();
        }
        if ((c2110mj.f & 2) != 0) {
            this.g |= 2;
            this.i = c2110mj.h;
            p();
        }
        if ((c2110mj.f & 4) != 0) {
            boolean z = c2110mj.i;
            this.g |= 4;
            this.j = z;
            p();
        }
        if ((c2110mj.f & 8) != 0) {
            boolean z2 = c2110mj.j;
            this.g |= 8;
            this.k = z2;
            p();
        }
        if ((c2110mj.f & 16) != 0) {
            boolean z3 = c2110mj.k;
            this.g |= 16;
            this.l = z3;
            p();
        }
        if ((c2110mj.f & 32) != 0) {
            int i2 = c2110mj.l;
            if (i2 == 1) {
                c = 1;
            } else if (i2 != 2) {
                c = i2 != 3 ? (char) 0 : (char) 3;
            } else {
                c = 2;
            }
            if (c == 0) {
                c = 1;
            }
            this.g |= 32;
            if (c != 1) {
                if (c == 2) {
                    i = 2;
                } else {
                    if (c != 3) {
                        throw null;
                    }
                    i = 3;
                }
            }
            this.m = i;
            p();
        }
        if ((c2110mj.f & 64) != 0) {
            this.g |= 64;
            this.n = c2110mj.m;
            p();
        }
        if ((c2110mj.f & 128) != 0) {
            boolean z4 = c2110mj.n;
            this.g |= 128;
            this.o = z4;
            p();
        }
        if ((c2110mj.f & Fcntl.S_IRUSR) != 0) {
            boolean z5 = c2110mj.o;
            this.g |= Fcntl.S_IRUSR;
            this.p = z5;
            p();
        }
        if ((c2110mj.f & 512) != 0) {
            boolean z6 = c2110mj.p;
            this.g |= 512;
            this.q = z6;
            p();
        }
        if ((c2110mj.f & Fcntl.S_ISGID) != 0) {
            boolean z7 = c2110mj.q;
            this.g |= Fcntl.S_ISGID;
            this.r = z7;
            p();
        }
        if ((c2110mj.f & Fcntl.S_ISUID) != 0) {
            boolean z8 = c2110mj.r;
            this.g |= Fcntl.S_ISUID;
            this.s = z8;
            p();
        }
        if ((c2110mj.f & 4096) != 0) {
            boolean z9 = c2110mj.s;
            this.g |= 4096;
            this.t = z9;
            p();
        }
        if ((c2110mj.f & 8192) != 0) {
            this.g |= 8192;
            this.u = c2110mj.t;
            p();
        }
        if ((c2110mj.f & 16384) != 0) {
            this.g |= 16384;
            this.v = c2110mj.u;
            p();
        }
        if ((c2110mj.f & 32768) != 0) {
            this.g |= 32768;
            this.w = c2110mj.v;
            p();
        }
        if ((c2110mj.f & 65536) != 0) {
            this.g |= 65536;
            this.x = c2110mj.w;
            p();
        }
        if ((c2110mj.f & 131072) != 0) {
            this.g |= 131072;
            this.y = c2110mj.x;
            p();
        }
        if ((c2110mj.f & 262144) != 0) {
            this.g |= 262144;
            this.z = c2110mj.y;
            p();
        }
        if ((c2110mj.f & 524288) != 0) {
            this.g |= 524288;
            this.A = c2110mj.z;
            p();
        }
        C2401q50 c2401q50 = this.C;
        List list = c2110mj.A;
        if (c2401q50 == null) {
            if (!list.isEmpty()) {
                if (this.B.isEmpty()) {
                    this.B = c2110mj.A;
                    this.g &= -1048577;
                } else {
                    if ((this.g & 1048576) == 0) {
                        this.B = new ArrayList(this.B);
                        this.g |= 1048576;
                    }
                    this.B.addAll(c2110mj.A);
                }
                p();
            }
        } else if (!list.isEmpty()) {
            boolean zIsEmpty = this.C.b.isEmpty();
            C2401q50 c2401q51 = this.C;
            if (zIsEmpty) {
                c2401q51.a = null;
                this.B = c2110mj.A;
                this.g &= -1048577;
                this.C = null;
            } else {
                c2401q51.a(c2110mj.A);
            }
        }
        a((AbstractC1102as) c2110mj);
        p();
        return this;
    }

    @Override // com.android.tools.r8.internal.H0
    public final H0 b(J0 j0) {
        if (j0 instanceof C2110mj) {
            return a((C2110mj) j0);
        }
        super.b(j0);
        return this;
    }

    @Override // com.android.tools.r8.internal.SN
    public final TN build() {
        C2110mj c2110mjI = i();
        if (c2110mjI.a()) {
            return c2110mjI;
        }
        throw H0.c(c2110mjI);
    }

    /* JADX WARN: Code duplicated, block: B:15:0x001d  */
    @Override // com.android.tools.r8.internal.H0
    /* JADX INFO: renamed from: c, reason: merged with bridge method [inline-methods] */
    public final C1939kj a(AbstractC0663Md abstractC0663Md, C0415Co c0415Co) throws Throwable {
        C2110mj c2110mj = null;
        try {
            try {
                a((C2110mj) C2110mj.D.a(abstractC0663Md, c0415Co));
                return this;
            } catch (RB e) {
                C2110mj c2110mj2 = (C2110mj) e.b;
                try {
                    throw e.a();
                } catch (Throwable th) {
                    th = th;
                    c2110mj = c2110mj2;
                    if (c2110mj != null) {
                        a(c2110mj);
                    }
                    throw th;
                }
            }
        } catch (Throwable th2) {
            th = th2;
            if (c2110mj != null) {
                a(c2110mj);
            }
            throw th;
        }
    }

    public final Object clone() {
        return (C1939kj) k();
    }

    @Override // com.android.tools.r8.internal.AbstractC0911Vr
    public final AbstractC0911Vr d(C2712tk0 c2712tk0) {
        this.e = c2712tk0;
        p();
        return this;
    }

    @Override // com.android.tools.r8.internal.H0, com.android.tools.r8.internal.WN
    public final C0955Xj e() {
        return AbstractC0877Uj.y;
    }

    @Override // com.android.tools.r8.internal.H0
    public final J0 h() {
        C2110mj c2110mjI = i();
        if (c2110mjI.a()) {
            return c2110mjI;
        }
        throw H0.c(c2110mjI);
    }

    @Override // com.android.tools.r8.internal.AbstractC0911Vr
    public final C2123ms n() {
        return AbstractC0877Uj.z.a(C2110mj.class, C1939kj.class);
    }

    @Override // com.android.tools.r8.internal.H0
    /* JADX INFO: renamed from: q, reason: merged with bridge method [inline-methods] */
    public final C2110mj i() {
        C2110mj c2110mj = new C2110mj(this);
        int i = this.g;
        int i2 = (i & 1) != 0 ? 1 : 0;
        c2110mj.g = this.h;
        if ((i & 2) != 0) {
            i2 |= 2;
        }
        c2110mj.h = this.i;
        if ((i & 4) != 0) {
            c2110mj.i = this.j;
            i2 |= 4;
        }
        if ((i & 8) != 0) {
            c2110mj.j = this.k;
            i2 |= 8;
        }
        if ((i & 16) != 0) {
            c2110mj.k = this.l;
            i2 |= 16;
        }
        if ((i & 32) != 0) {
            i2 |= 32;
        }
        c2110mj.l = this.m;
        if ((i & 64) != 0) {
            i2 |= 64;
        }
        c2110mj.m = this.n;
        if ((i & 128) != 0) {
            c2110mj.n = this.o;
            i2 |= 128;
        }
        if ((i & Fcntl.S_IRUSR) != 0) {
            c2110mj.o = this.p;
            i2 |= Fcntl.S_IRUSR;
        }
        if ((i & 512) != 0) {
            c2110mj.p = this.q;
            i2 |= 512;
        }
        if ((i & Fcntl.S_ISGID) != 0) {
            c2110mj.q = this.r;
            i2 |= Fcntl.S_ISGID;
        }
        if ((i & Fcntl.S_ISUID) != 0) {
            c2110mj.r = this.s;
            i2 |= Fcntl.S_ISUID;
        }
        if ((i & 4096) != 0) {
            i2 |= 4096;
        }
        c2110mj.s = this.t;
        if ((i & 8192) != 0) {
            i2 |= 8192;
        }
        c2110mj.t = this.u;
        if ((i & 16384) != 0) {
            i2 |= 16384;
        }
        c2110mj.u = this.v;
        if ((i & 32768) != 0) {
            i2 |= 32768;
        }
        c2110mj.v = this.w;
        if ((i & 65536) != 0) {
            i2 |= 65536;
        }
        c2110mj.w = this.x;
        if ((i & 131072) != 0) {
            i2 |= 131072;
        }
        c2110mj.x = this.y;
        if ((i & 262144) != 0) {
            i2 |= 262144;
        }
        c2110mj.y = this.z;
        if ((i & 524288) != 0) {
            i2 |= 524288;
        }
        c2110mj.z = this.A;
        C2401q50 c2401q50 = this.C;
        if (c2401q50 == null) {
            if ((this.g & 1048576) != 0) {
                this.B = Collections.unmodifiableList(this.B);
                this.g &= -1048577;
            }
            c2110mj.A = this.B;
        } else {
            c2110mj.A = c2401q50.b();
        }
        c2110mj.f = i2;
        o();
        return c2110mj;
    }

    @Override // com.android.tools.r8.internal.H0
    public final H0 b(C1856jk c1856jk, Object obj) {
        return (C1939kj) d(c1856jk, obj);
    }

    @Override // com.android.tools.r8.internal.H0
    public final H0 b(C2712tk0 c2712tk0) {
        this.e = c2712tk0;
        p();
        return this;
    }

    @Override // com.android.tools.r8.internal.WN
    public final J0 b() {
        return C2110mj.C;
    }

    public C1939kj(C0859Tr c0859Tr) {
        super(c0859Tr);
        this.h = XmlPullParser.NO_NAMESPACE;
        this.i = XmlPullParser.NO_NAMESPACE;
        this.m = 1;
        this.n = XmlPullParser.NO_NAMESPACE;
        this.t = true;
        this.u = XmlPullParser.NO_NAMESPACE;
        this.v = XmlPullParser.NO_NAMESPACE;
        this.w = XmlPullParser.NO_NAMESPACE;
        this.x = XmlPullParser.NO_NAMESPACE;
        this.y = XmlPullParser.NO_NAMESPACE;
        this.z = XmlPullParser.NO_NAMESPACE;
        this.A = XmlPullParser.NO_NAMESPACE;
        this.B = Collections.EMPTY_LIST;
    }

    @Override // com.android.tools.r8.internal.H0
    public final H0 a(C1856jk c1856jk, Object obj) {
        return (C1939kj) c(c1856jk, obj);
    }

    @Override // com.android.tools.r8.internal.H0
    /* JADX INFO: renamed from: a */
    public final H0 b(J0 j0) {
        if (j0 instanceof C2110mj) {
            return a((C2110mj) j0);
        }
        super.b(j0);
        return this;
    }

    @Override // com.android.tools.r8.internal.H0
    public final H0 a(C2712tk0 c2712tk0) {
        return (C1939kj) c(c2712tk0);
    }
}
