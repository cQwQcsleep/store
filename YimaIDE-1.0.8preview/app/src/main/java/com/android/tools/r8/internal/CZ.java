package com.android.tools.r8.internal;

import com.android.apksig.internal.util.AndroidSdkVersion;
import com.sun.jna.platform.linux.Fcntl;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class CZ extends Lr {
    public static final CZ K;
    public static final C3202zZ L = new C3202zZ();
    public List A;
    public int B;
    public List C;
    public List D;
    public int E;
    public G00 F;
    public List G;
    public R00 H;
    public byte I;
    public int J;
    public final T7 c;
    public int d;
    public int e;
    public int f;
    public int g;
    public List h;
    public List i;
    public List j;
    public int k;
    public List l;
    public int m;
    public List n;
    public List o;
    public int p;
    public List q;
    public List r;
    public List s;
    public List t;
    public List u;
    public List v;
    public int w;
    public int x;
    public C2903w00 y;
    public int z;

    static {
        CZ cz = new CZ();
        K = cz;
        cz.h();
    }

    /* JADX WARN: Multi-variable type inference failed */
    public CZ(C0638Ld c0638Ld, C0389Bo c0389Bo) throws Throwable {
        this.k = -1;
        this.m = -1;
        this.p = -1;
        this.w = -1;
        this.B = -1;
        this.E = -1;
        this.I = (byte) -1;
        this.J = -1;
        h();
        R7 r7 = new R7();
        boolean z = true;
        C0767Qd c0767Qd = new C0767Qd(r7, new byte[1]);
        boolean z2 = false;
        int i = 0;
        while (true) {
            boolean z3 = z;
            if (z2) {
                if ((i & 32) == 32) {
                    this.j = Collections.unmodifiableList(this.j);
                }
                if ((i & 8) == 8) {
                    this.h = Collections.unmodifiableList(this.h);
                }
                if ((i & 16) == 16) {
                    this.i = Collections.unmodifiableList(this.i);
                }
                if ((i & 64) == 64) {
                    this.l = Collections.unmodifiableList(this.l);
                }
                if ((i & 512) == 512) {
                    this.q = Collections.unmodifiableList(this.q);
                }
                if ((i & Fcntl.S_ISGID) == 1024) {
                    this.r = Collections.unmodifiableList(this.r);
                }
                if ((i & Fcntl.S_ISUID) == 2048) {
                    this.s = Collections.unmodifiableList(this.s);
                }
                if ((i & 4096) == 4096) {
                    this.t = Collections.unmodifiableList(this.t);
                }
                if ((i & 8192) == 8192) {
                    this.u = Collections.unmodifiableList(this.u);
                }
                if ((i & 16384) == 16384) {
                    this.v = Collections.unmodifiableList(this.v);
                }
                if ((i & 128) == 128) {
                    this.n = Collections.unmodifiableList(this.n);
                }
                if ((i & Fcntl.S_IRUSR) == 256) {
                    this.o = Collections.unmodifiableList(this.o);
                }
                if ((i & 262144) == 262144) {
                    this.A = Collections.unmodifiableList(this.A);
                }
                if ((i & 524288) == 524288) {
                    this.C = Collections.unmodifiableList(this.C);
                }
                if ((i & 1048576) == 1048576) {
                    this.D = Collections.unmodifiableList(this.D);
                }
                if ((i & 4194304) == 4194304) {
                    this.G = Collections.unmodifiableList(this.G);
                }
                try {
                    c0767Qd.a();
                } catch (IOException unused) {
                } finally {
                    this.c = r7.c();
                }
                this.b.a();
                return;
            }
            try {
                try {
                    int i2 = c0638Ld.i();
                    Cloneable cloneableA = null;
                    switch (i2) {
                        case 0:
                            z = z3;
                            z2 = z;
                            break;
                        case 8:
                            this.d |= 1;
                            this.e = c0638Ld.c();
                            z = z3;
                            break;
                        case Fcntl.S_IWGRP /* 16 */:
                            if ((i & 32) != 32) {
                                this.j = new ArrayList();
                                i |= 32;
                            }
                            this.j.add(Integer.valueOf(c0638Ld.c()));
                            z = z3;
                            break;
                        case AndroidSdkVersion.JELLY_BEAN_MR2 /* 18 */:
                            int iB = c0638Ld.b(c0638Ld.f());
                            if ((i & 32) != 32 && c0638Ld.a() > 0) {
                                this.j = new ArrayList();
                                i |= 32;
                            }
                            while (c0638Ld.a() > 0) {
                                this.j.add(Integer.valueOf(c0638Ld.c()));
                            }
                            c0638Ld.a(iB);
                            z = z3;
                            break;
                        case AndroidSdkVersion.N /* 24 */:
                            this.d |= 2;
                            this.f = c0638Ld.c();
                            z = z3;
                            break;
                        case 32:
                            this.d |= 4;
                            this.g = c0638Ld.c();
                            z = z3;
                            break;
                        case 42:
                            if ((i & 8) != 8) {
                                this.h = new ArrayList();
                                i |= 8;
                            }
                            this.h.add(c0638Ld.a(D00.o, c0389Bo));
                            z = z3;
                            break;
                        case 50:
                            if ((i & 16) != 16) {
                                this.i = new ArrayList();
                                i |= 16;
                            }
                            this.i.add(c0638Ld.a(C2903w00.v, c0389Bo));
                            z = z3;
                            break;
                        case Fcntl.S_IRWXG /* 56 */:
                            if ((i & 64) != 64) {
                                this.l = new ArrayList();
                                i |= 64;
                            }
                            this.l.add(Integer.valueOf(c0638Ld.c()));
                            z = z3;
                            break;
                        case 58:
                            int iB2 = c0638Ld.b(c0638Ld.f());
                            if ((i & 64) != 64 && c0638Ld.a() > 0) {
                                this.l = new ArrayList();
                                i |= 64;
                            }
                            while (c0638Ld.a() > 0) {
                                this.l.add(Integer.valueOf(c0638Ld.c()));
                            }
                            c0638Ld.a(iB2);
                            z = z3;
                            break;
                        case 66:
                            if ((i & 512) != 512) {
                                this.q = new ArrayList();
                                i |= 512;
                            }
                            this.q.add(c0638Ld.a(FZ.k, c0389Bo));
                            z = z3;
                            break;
                        case 74:
                            if ((i & Fcntl.S_ISGID) != 1024) {
                                this.r = new ArrayList();
                                i |= Fcntl.S_ISGID;
                            }
                            this.r.add(c0638Ld.a(XZ.w, c0389Bo));
                            z = z3;
                            break;
                        case 82:
                            if ((i & Fcntl.S_ISUID) != 2048) {
                                this.s = new ArrayList();
                                i |= Fcntl.S_ISUID;
                            }
                            this.s.add(c0638Ld.a(C1450f00.w, c0389Bo));
                            z = z3;
                            break;
                        case 90:
                            if ((i & 4096) != 4096) {
                                this.t = new ArrayList();
                                i |= 4096;
                            }
                            this.t.add(c0638Ld.a(C3158z00.q, c0389Bo));
                            z = z3;
                            break;
                        case 106:
                            if ((i & 8192) != 8192) {
                                this.u = new ArrayList();
                                i |= 8192;
                            }
                            this.u.add(c0638Ld.a(QZ.i, c0389Bo));
                            z = z3;
                            break;
                        case 128:
                            if ((i & 16384) != 16384) {
                                this.v = new ArrayList();
                                i |= 16384;
                            }
                            this.v.add(Integer.valueOf(c0638Ld.c()));
                            z = z3;
                            break;
                        case 130:
                            int iB3 = c0638Ld.b(c0638Ld.f());
                            if ((i & 16384) != 16384 && c0638Ld.a() > 0) {
                                this.v = new ArrayList();
                                i |= 16384;
                            }
                            while (c0638Ld.a() > 0) {
                                this.v.add(Integer.valueOf(c0638Ld.c()));
                            }
                            c0638Ld.a(iB3);
                            z = z3;
                            break;
                        case 136:
                            this.d |= 8;
                            this.x = c0638Ld.c();
                            z = z3;
                            break;
                        case 146:
                            C2817v00 c2817v00E = (this.d & 16) == 16 ? this.y.e() : null;
                            C2903w00 c2903w00 = (C2903w00) c0638Ld.a(C2903w00.v, c0389Bo);
                            this.y = c2903w00;
                            if (c2817v00E != 0) {
                                c2817v00E.a(c2903w00);
                                this.y = c2817v00E.f();
                            }
                            this.d |= 16;
                            z = z3;
                            break;
                        case 152:
                            this.d |= 32;
                            this.z = c0638Ld.c();
                            z = z3;
                            break;
                        case 162:
                            if ((i & 128) != 128) {
                                this.n = new ArrayList();
                                i |= 128;
                            }
                            this.n.add(c0638Ld.a(C2903w00.v, c0389Bo));
                            z = z3;
                            break;
                        case 168:
                            if ((i & Fcntl.S_IRUSR) != 256) {
                                this.o = new ArrayList();
                                i |= Fcntl.S_IRUSR;
                            }
                            this.o.add(Integer.valueOf(c0638Ld.c()));
                            z = z3;
                            break;
                        case 170:
                            int iB4 = c0638Ld.b(c0638Ld.f());
                            if ((i & Fcntl.S_IRUSR) != 256 && c0638Ld.a() > 0) {
                                this.o = new ArrayList();
                                i |= Fcntl.S_IRUSR;
                            }
                            while (c0638Ld.a() > 0) {
                                this.o.add(Integer.valueOf(c0638Ld.c()));
                            }
                            c0638Ld.a(iB4);
                            z = z3;
                            break;
                        case 176:
                            if ((i & 262144) != 262144) {
                                this.A = new ArrayList();
                                i |= 262144;
                            }
                            this.A.add(Integer.valueOf(c0638Ld.c()));
                            z = z3;
                            break;
                        case 178:
                            int iB5 = c0638Ld.b(c0638Ld.f());
                            if ((i & 262144) != 262144 && c0638Ld.a() > 0) {
                                this.A = new ArrayList();
                                i |= 262144;
                            }
                            while (c0638Ld.a() > 0) {
                                this.A.add(Integer.valueOf(c0638Ld.c()));
                            }
                            c0638Ld.a(iB5);
                            z = z3;
                            break;
                        case 186:
                            if ((i & 524288) != 524288) {
                                this.C = new ArrayList();
                                i |= 524288;
                            }
                            this.C.add(c0638Ld.a(C2903w00.v, c0389Bo));
                            z = z3;
                            break;
                        case 192:
                            if ((i & 1048576) != 1048576) {
                                this.D = new ArrayList();
                                i |= 1048576;
                            }
                            this.D.add(Integer.valueOf(c0638Ld.c()));
                            z = z3;
                            break;
                        case 194:
                            int iB6 = c0638Ld.b(c0638Ld.f());
                            if ((i & 1048576) != 1048576 && c0638Ld.a() > 0) {
                                this.D = new ArrayList();
                                i |= 1048576;
                            }
                            while (c0638Ld.a() > 0) {
                                this.D.add(Integer.valueOf(c0638Ld.c()));
                            }
                            c0638Ld.a(iB6);
                            z = z3;
                            break;
                        case 242:
                            if ((this.d & 64) == 64) {
                                G00 g00 = this.F;
                                g00.getClass();
                                cloneableA = G00.a(g00);
                            }
                            F00 f00 = cloneableA;
                            G00 g01 = (G00) c0638Ld.a(G00.i, c0389Bo);
                            this.F = g01;
                            if (f00 != 0) {
                                f00.a(g01);
                                this.F = f00.e();
                            }
                            this.d |= 64;
                            z = z3;
                            break;
                        case 248:
                            if ((i & 4194304) != 4194304) {
                                this.G = new ArrayList();
                                i |= 4194304;
                            }
                            this.G.add(Integer.valueOf(c0638Ld.c()));
                            z = z3;
                            break;
                        case 250:
                            int iB7 = c0638Ld.b(c0638Ld.f());
                            if ((i & 4194304) != 4194304 && c0638Ld.a() > 0) {
                                this.G = new ArrayList();
                                i |= 4194304;
                            }
                            while (c0638Ld.a() > 0) {
                                this.G.add(Integer.valueOf(c0638Ld.c()));
                            }
                            c0638Ld.a(iB7);
                            z = z3;
                            break;
                        case 258:
                            try {
                                if ((this.d & 128) == 128) {
                                    R00 r00 = this.H;
                                    r00.getClass();
                                    cloneableA = new Q00().a(r00);
                                }
                                Q00 q00 = cloneableA;
                                R00 r01 = (R00) c0638Ld.a(R00.g, c0389Bo);
                                this.H = r01;
                                if (q00 != 0) {
                                    q00.a(r01);
                                    this.H = q00.e();
                                }
                                this.d |= 128;
                                z = z3;
                            } catch (QB e) {
                                e = e;
                                e.b = this;
                                throw e;
                            } catch (IOException e2) {
                                e = e2;
                                QB qb = new QB(e.getMessage());
                                qb.b = this;
                                throw qb;
                            } catch (Throwable th) {
                                th = th;
                                if ((i & 32) == 32) {
                                    this.j = Collections.unmodifiableList(this.j);
                                }
                                if ((i & 8) == 8) {
                                    this.h = Collections.unmodifiableList(this.h);
                                }
                                if ((i & 16) == 16) {
                                    this.i = Collections.unmodifiableList(this.i);
                                }
                                if ((i & 64) == 64) {
                                    this.l = Collections.unmodifiableList(this.l);
                                }
                                if ((i & 512) == 512) {
                                    this.q = Collections.unmodifiableList(this.q);
                                }
                                if ((i & Fcntl.S_ISGID) == 1024) {
                                    this.r = Collections.unmodifiableList(this.r);
                                }
                                if ((i & Fcntl.S_ISUID) == 2048) {
                                    this.s = Collections.unmodifiableList(this.s);
                                }
                                if ((i & 4096) == 4096) {
                                    this.t = Collections.unmodifiableList(this.t);
                                }
                                if ((i & 8192) == 8192) {
                                    this.u = Collections.unmodifiableList(this.u);
                                }
                                if ((i & 16384) == 16384) {
                                    this.v = Collections.unmodifiableList(this.v);
                                }
                                if ((i & 128) == 128) {
                                    this.n = Collections.unmodifiableList(this.n);
                                }
                                if ((i & Fcntl.S_IRUSR) == 256) {
                                    this.o = Collections.unmodifiableList(this.o);
                                }
                                if ((i & 262144) == 262144) {
                                    this.A = Collections.unmodifiableList(this.A);
                                }
                                if ((i & 524288) == 524288) {
                                    this.C = Collections.unmodifiableList(this.C);
                                }
                                if ((i & 1048576) == 1048576) {
                                    this.D = Collections.unmodifiableList(this.D);
                                }
                                if ((i & 4194304) == 4194304) {
                                    this.G = Collections.unmodifiableList(this.G);
                                }
                                try {
                                    c0767Qd.a();
                                } catch (IOException unused2) {
                                } finally {
                                    this.c = r7.c();
                                }
                                this.b.a();
                                throw th;
                            }
                            break;
                        default:
                            if (!a(c0638Ld, c0767Qd, c0389Bo, i2)) {
                                z = z3;
                                z2 = z;
                            } else {
                                z = z3;
                            }
                            break;
                    }
                } catch (Throwable th2) {
                    th = th2;
                }
            } catch (QB e3) {
                e = e3;
            } catch (IOException e4) {
                e = e4;
            }
        }
    }

    @Override // com.android.tools.r8.internal.L0
    public final void a(C0767Qd c0767Qd) throws IOException {
        c();
        C0626Kr c0626Kr = new C0626Kr(this);
        if ((this.d & 1) == 1) {
            int i = this.e;
            c0767Qd.c(1, 0);
            c0767Qd.d(i);
        }
        if (this.j.size() > 0) {
            c0767Qd.g(18);
            c0767Qd.g(this.k);
        }
        for (int i2 = 0; i2 < this.j.size(); i2++) {
            c0767Qd.d(((Integer) this.j.get(i2)).intValue());
        }
        if ((this.d & 2) == 2) {
            int i3 = this.f;
            c0767Qd.c(3, 0);
            c0767Qd.d(i3);
        }
        if ((this.d & 4) == 4) {
            int i4 = this.g;
            c0767Qd.c(4, 0);
            c0767Qd.d(i4);
        }
        for (int i5 = 0; i5 < this.h.size(); i5++) {
            c0767Qd.b(5, (L0) this.h.get(i5));
        }
        for (int i6 = 0; i6 < this.i.size(); i6++) {
            c0767Qd.b(6, (L0) this.i.get(i6));
        }
        if (this.l.size() > 0) {
            c0767Qd.g(58);
            c0767Qd.g(this.m);
        }
        for (int i7 = 0; i7 < this.l.size(); i7++) {
            c0767Qd.d(((Integer) this.l.get(i7)).intValue());
        }
        for (int i8 = 0; i8 < this.q.size(); i8++) {
            c0767Qd.b(8, (L0) this.q.get(i8));
        }
        for (int i9 = 0; i9 < this.r.size(); i9++) {
            c0767Qd.b(9, (L0) this.r.get(i9));
        }
        for (int i10 = 0; i10 < this.s.size(); i10++) {
            c0767Qd.b(10, (L0) this.s.get(i10));
        }
        for (int i11 = 0; i11 < this.t.size(); i11++) {
            c0767Qd.b(11, (L0) this.t.get(i11));
        }
        for (int i12 = 0; i12 < this.u.size(); i12++) {
            c0767Qd.b(13, (L0) this.u.get(i12));
        }
        if (this.v.size() > 0) {
            c0767Qd.g(130);
            c0767Qd.g(this.w);
        }
        for (int i13 = 0; i13 < this.v.size(); i13++) {
            c0767Qd.d(((Integer) this.v.get(i13)).intValue());
        }
        if ((this.d & 8) == 8) {
            int i14 = this.x;
            c0767Qd.c(17, 0);
            c0767Qd.d(i14);
        }
        if ((this.d & 16) == 16) {
            c0767Qd.b(18, this.y);
        }
        if ((this.d & 32) == 32) {
            int i15 = this.z;
            c0767Qd.c(19, 0);
            c0767Qd.d(i15);
        }
        for (int i16 = 0; i16 < this.n.size(); i16++) {
            c0767Qd.b(20, (L0) this.n.get(i16));
        }
        if (this.o.size() > 0) {
            c0767Qd.g(170);
            c0767Qd.g(this.p);
        }
        for (int i17 = 0; i17 < this.o.size(); i17++) {
            c0767Qd.d(((Integer) this.o.get(i17)).intValue());
        }
        if (this.A.size() > 0) {
            c0767Qd.g(178);
            c0767Qd.g(this.B);
        }
        for (int i18 = 0; i18 < this.A.size(); i18++) {
            c0767Qd.d(((Integer) this.A.get(i18)).intValue());
        }
        for (int i19 = 0; i19 < this.C.size(); i19++) {
            c0767Qd.b(23, (L0) this.C.get(i19));
        }
        if (this.D.size() > 0) {
            c0767Qd.g(194);
            c0767Qd.g(this.E);
        }
        for (int i20 = 0; i20 < this.D.size(); i20++) {
            c0767Qd.d(((Integer) this.D.get(i20)).intValue());
        }
        if ((this.d & 64) == 64) {
            c0767Qd.b(30, this.F);
        }
        for (int i21 = 0; i21 < this.G.size(); i21++) {
            int iIntValue = ((Integer) this.G.get(i21)).intValue();
            c0767Qd.c(31, 0);
            c0767Qd.d(iIntValue);
        }
        if ((this.d & 128) == 128) {
            c0767Qd.b(32, this.H);
        }
        c0626Kr.a(19000, c0767Qd);
        c0767Qd.a(this.c);
    }

    @Override // com.android.tools.r8.internal.UN
    public final L0 b() {
        return K;
    }

    @Override // com.android.tools.r8.internal.L0
    public final int c() {
        List list;
        List list2;
        List list3;
        List list4;
        List list5;
        List list6;
        List list7;
        int i = this.J;
        if (i != -1) {
            return i;
        }
        int i2 = 0;
        int iA = (this.d & 1) == 1 ? C0767Qd.a(1, this.e) : 0;
        int i3 = 0;
        int i4 = 0;
        while (true) {
            int size = this.j.size();
            list = this.j;
            int iB = 10;
            if (i3 >= size) {
                break;
            }
            int iIntValue = ((Integer) list.get(i3)).intValue();
            if (iIntValue >= 0) {
                iB = C0767Qd.b(iIntValue);
            }
            i4 += iB;
            i3++;
        }
        int iA2 = iA + i4;
        if (!list.isEmpty()) {
            iA2 = iA2 + 1 + (i4 >= 0 ? C0767Qd.b(i4) : 10);
        }
        this.k = i4;
        if ((this.d & 2) == 2) {
            iA2 += C0767Qd.a(3, this.f);
        }
        if ((this.d & 4) == 4) {
            iA2 += C0767Qd.a(4, this.g);
        }
        for (int i5 = 0; i5 < this.h.size(); i5++) {
            iA2 += C0767Qd.a(5, (L0) this.h.get(i5));
        }
        for (int i6 = 0; i6 < this.i.size(); i6++) {
            iA2 += C0767Qd.a(6, (L0) this.i.get(i6));
        }
        int i7 = 0;
        int iB2 = 0;
        while (true) {
            int size2 = this.l.size();
            list2 = this.l;
            if (i7 >= size2) {
                break;
            }
            int iIntValue2 = ((Integer) list2.get(i7)).intValue();
            iB2 += iIntValue2 >= 0 ? C0767Qd.b(iIntValue2) : 10;
            i7++;
        }
        int iA3 = iA2 + iB2;
        if (!list2.isEmpty()) {
            iA3 = iA3 + 1 + (iB2 >= 0 ? C0767Qd.b(iB2) : 10);
        }
        this.m = iB2;
        for (int i8 = 0; i8 < this.q.size(); i8++) {
            iA3 += C0767Qd.a(8, (L0) this.q.get(i8));
        }
        for (int i9 = 0; i9 < this.r.size(); i9++) {
            iA3 += C0767Qd.a(9, (L0) this.r.get(i9));
        }
        for (int i10 = 0; i10 < this.s.size(); i10++) {
            iA3 += C0767Qd.a(10, (L0) this.s.get(i10));
        }
        for (int i11 = 0; i11 < this.t.size(); i11++) {
            iA3 += C0767Qd.a(11, (L0) this.t.get(i11));
        }
        for (int i12 = 0; i12 < this.u.size(); i12++) {
            iA3 += C0767Qd.a(13, (L0) this.u.get(i12));
        }
        int i13 = 0;
        int iB3 = 0;
        while (true) {
            int size3 = this.v.size();
            list3 = this.v;
            if (i13 >= size3) {
                break;
            }
            int iIntValue3 = ((Integer) list3.get(i13)).intValue();
            iB3 += iIntValue3 >= 0 ? C0767Qd.b(iIntValue3) : 10;
            i13++;
        }
        int iA4 = iA3 + iB3;
        if (!list3.isEmpty()) {
            iA4 = iA4 + 2 + (iB3 >= 0 ? C0767Qd.b(iB3) : 10);
        }
        this.w = iB3;
        if ((this.d & 8) == 8) {
            iA4 += C0767Qd.a(17, this.x);
        }
        if ((this.d & 16) == 16) {
            iA4 += C0767Qd.a(18, this.y);
        }
        if ((this.d & 32) == 32) {
            iA4 += C0767Qd.a(19, this.z);
        }
        for (int i14 = 0; i14 < this.n.size(); i14++) {
            iA4 += C0767Qd.a(20, (L0) this.n.get(i14));
        }
        int i15 = 0;
        int iB4 = 0;
        while (true) {
            int size4 = this.o.size();
            list4 = this.o;
            if (i15 >= size4) {
                break;
            }
            int iIntValue4 = ((Integer) list4.get(i15)).intValue();
            iB4 += iIntValue4 >= 0 ? C0767Qd.b(iIntValue4) : 10;
            i15++;
        }
        int iB5 = iA4 + iB4;
        if (!list4.isEmpty()) {
            iB5 = iB5 + 2 + (iB4 >= 0 ? C0767Qd.b(iB4) : 10);
        }
        this.p = iB4;
        int i16 = 0;
        int iB6 = 0;
        while (true) {
            int size5 = this.A.size();
            list5 = this.A;
            if (i16 >= size5) {
                break;
            }
            int iIntValue5 = ((Integer) list5.get(i16)).intValue();
            iB6 += iIntValue5 >= 0 ? C0767Qd.b(iIntValue5) : 10;
            i16++;
        }
        int iA5 = iB5 + iB6;
        if (!list5.isEmpty()) {
            iA5 = iA5 + 2 + (iB6 >= 0 ? C0767Qd.b(iB6) : 10);
        }
        this.B = iB6;
        for (int i17 = 0; i17 < this.C.size(); i17++) {
            iA5 += C0767Qd.a(23, (L0) this.C.get(i17));
        }
        int i18 = 0;
        int iB7 = 0;
        while (true) {
            int size6 = this.D.size();
            list6 = this.D;
            if (i18 >= size6) {
                break;
            }
            int iIntValue6 = ((Integer) list6.get(i18)).intValue();
            iB7 += iIntValue6 >= 0 ? C0767Qd.b(iIntValue6) : 10;
            i18++;
        }
        int iA6 = iA5 + iB7;
        if (!list6.isEmpty()) {
            iA6 = iA6 + 2 + (iB7 >= 0 ? C0767Qd.b(iB7) : 10);
        }
        this.E = iB7;
        if ((this.d & 64) == 64) {
            iA6 += C0767Qd.a(30, this.F);
        }
        int iB8 = 0;
        while (true) {
            int size7 = this.G.size();
            list7 = this.G;
            if (i2 >= size7) {
                break;
            }
            int iIntValue7 = ((Integer) list7.get(i2)).intValue();
            iB8 += iIntValue7 >= 0 ? C0767Qd.b(iIntValue7) : 10;
            i2++;
        }
        int size8 = (list7.size() * 2) + iA6 + iB8;
        if ((this.d & 128) == 128) {
            size8 += C0767Qd.a(32, this.H);
        }
        int size9 = this.c.size() + g() + size8;
        this.J = size9;
        return size9;
    }

    @Override // com.android.tools.r8.internal.L0
    public final AbstractC0574Ir d() {
        return new AZ();
    }

    @Override // com.android.tools.r8.internal.L0
    public final AbstractC0574Ir e() {
        return new AZ().a(this);
    }

    public final void h() {
        this.e = 6;
        this.f = 0;
        this.g = 0;
        List list = Collections.EMPTY_LIST;
        this.h = list;
        this.i = list;
        this.j = list;
        this.l = list;
        this.n = list;
        this.o = list;
        this.q = list;
        this.r = list;
        this.s = list;
        this.t = list;
        this.u = list;
        this.v = list;
        this.x = 0;
        this.y = C2903w00.u;
        this.z = 0;
        this.A = list;
        this.C = list;
        this.D = list;
        this.F = G00.h;
        this.G = list;
        this.H = R00.f;
    }

    @Override // com.android.tools.r8.internal.UN
    public final boolean a() {
        byte b = this.I;
        if (b == 1) {
            return true;
        }
        if (b == 0) {
            return false;
        }
        if ((this.d & 2) == 2) {
            for (int i = 0; i < this.h.size(); i++) {
                if (!((D00) this.h.get(i)).a()) {
                    this.I = (byte) 0;
                    return false;
                }
            }
            for (int i2 = 0; i2 < this.i.size(); i2++) {
                if (!((C2903w00) this.i.get(i2)).a()) {
                    this.I = (byte) 0;
                    return false;
                }
            }
            for (int i3 = 0; i3 < this.n.size(); i3++) {
                if (!((C2903w00) this.n.get(i3)).a()) {
                    this.I = (byte) 0;
                    return false;
                }
            }
            for (int i4 = 0; i4 < this.q.size(); i4++) {
                if (!((FZ) this.q.get(i4)).a()) {
                    this.I = (byte) 0;
                    return false;
                }
            }
            for (int i5 = 0; i5 < this.r.size(); i5++) {
                if (!((XZ) this.r.get(i5)).a()) {
                    this.I = (byte) 0;
                    return false;
                }
            }
            for (int i6 = 0; i6 < this.s.size(); i6++) {
                if (!((C1450f00) this.s.get(i6)).a()) {
                    this.I = (byte) 0;
                    return false;
                }
            }
            for (int i7 = 0; i7 < this.t.size(); i7++) {
                if (!((C3158z00) this.t.get(i7)).a()) {
                    this.I = (byte) 0;
                    return false;
                }
            }
            for (int i8 = 0; i8 < this.u.size(); i8++) {
                if (!((QZ) this.u.get(i8)).a()) {
                    this.I = (byte) 0;
                    return false;
                }
            }
            if ((this.d & 16) == 16 && !this.y.a()) {
                this.I = (byte) 0;
                return false;
            }
            for (int i9 = 0; i9 < this.C.size(); i9++) {
                if (!((C2903w00) this.C.get(i9)).a()) {
                    this.I = (byte) 0;
                    return false;
                }
            }
            if ((this.d & 64) == 64 && !this.F.a()) {
                this.I = (byte) 0;
                return false;
            }
            if (!f()) {
                this.I = (byte) 0;
                return false;
            }
            this.I = (byte) 1;
            return true;
        }
        this.I = (byte) 0;
        return false;
    }

    public CZ() {
        this.k = -1;
        this.m = -1;
        this.p = -1;
        this.w = -1;
        this.B = -1;
        this.E = -1;
        this.I = (byte) -1;
        this.J = -1;
        this.c = T7.b;
    }

    public CZ(AbstractC0600Jr abstractC0600Jr) {
        super(abstractC0600Jr);
        this.k = -1;
        this.m = -1;
        this.p = -1;
        this.w = -1;
        this.B = -1;
        this.E = -1;
        this.I = (byte) -1;
        this.J = -1;
        this.c = abstractC0600Jr.b;
    }
}
