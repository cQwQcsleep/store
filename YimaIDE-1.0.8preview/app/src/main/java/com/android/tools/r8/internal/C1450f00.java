package com.android.tools.r8.internal;

import com.android.apksig.internal.util.AndroidSdkVersion;
import com.sun.jna.platform.linux.Fcntl;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/* JADX INFO: renamed from: com.android.tools.r8.internal.f00, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C1450f00 extends Lr {
    public static final C1450f00 v;
    public static final C1280d00 w = new C1280d00();
    public final T7 c;
    public int d;
    public int e;
    public int f;
    public int g;
    public C2903w00 h;
    public int i;
    public List j;
    public C2903w00 k;
    public int l;
    public List m;
    public List n;
    public int o;
    public J00 p;
    public int q;
    public int r;
    public List s;
    public byte t;
    public int u;

    static {
        C1450f00 c1450f00 = new C1450f00();
        v = c1450f00;
        c1450f00.h();
    }

    public C1450f00(C0638Ld c0638Ld, C0389Bo c0389Bo) {
        this.o = -1;
        this.t = (byte) -1;
        this.u = -1;
        h();
        R7 r7 = new R7();
        C0767Qd c0767Qd = new C0767Qd(r7, new byte[1]);
        boolean z = false;
        int i = 0;
        while (!z) {
            try {
                try {
                    int i2 = c0638Ld.i();
                    C2817v00 c2817v00A = null;
                    I00 i00A = null;
                    C2817v00 c2817v00A2 = null;
                    switch (i2) {
                        case 0:
                            break;
                        case 8:
                            this.d |= 2;
                            this.f = c0638Ld.f();
                            continue;
                        case Fcntl.S_IWGRP /* 16 */:
                            this.d |= 4;
                            this.g = c0638Ld.f();
                            continue;
                        case AndroidSdkVersion.O /* 26 */:
                            if ((this.d & 8) == 8) {
                                C2903w00 c2903w00 = this.h;
                                c2903w00.getClass();
                                c2817v00A = C2903w00.a(c2903w00);
                            }
                            C2903w00 c2903w01 = (C2903w00) c0638Ld.a(C2903w00.v, c0389Bo);
                            this.h = c2903w01;
                            if (c2817v00A != null) {
                                c2817v00A.a(c2903w01);
                                this.h = c2817v00A.f();
                            }
                            this.d |= 8;
                            continue;
                        case AndroidSdkVersion.U /* 34 */:
                            int i3 = (i == true ? 1 : 0) & 32;
                            i = i;
                            if (i3 != 32) {
                                this.j = new ArrayList();
                                i = (i == true ? 1 : 0) | 32;
                            }
                            this.j.add(c0638Ld.a(D00.o, c0389Bo));
                            continue;
                        case 42:
                            if ((this.d & 32) == 32) {
                                C2903w00 c2903w02 = this.k;
                                c2903w02.getClass();
                                c2817v00A2 = C2903w00.a(c2903w02);
                            }
                            C2903w00 c2903w03 = (C2903w00) c0638Ld.a(C2903w00.v, c0389Bo);
                            this.k = c2903w03;
                            if (c2817v00A2 != null) {
                                c2817v00A2.a(c2903w03);
                                this.k = c2817v00A2.f();
                            }
                            this.d |= 32;
                            continue;
                        case 50:
                            if ((this.d & 128) == 128) {
                                J00 j00 = this.p;
                                j00.getClass();
                                i00A = new I00().a(j00);
                            }
                            J00 j01 = (J00) c0638Ld.a(J00.n, c0389Bo);
                            this.p = j01;
                            if (i00A != null) {
                                i00A.a(j01);
                                this.p = i00A.e();
                            }
                            this.d |= 128;
                            continue;
                        case Fcntl.S_IRWXG /* 56 */:
                            this.d |= Fcntl.S_IRUSR;
                            this.q = c0638Ld.f();
                            continue;
                        case 64:
                            this.d |= 512;
                            this.r = c0638Ld.f();
                            continue;
                        case 72:
                            this.d |= 16;
                            this.i = c0638Ld.f();
                            continue;
                        case 80:
                            this.d |= 64;
                            this.l = c0638Ld.f();
                            continue;
                        case 88:
                            this.d |= 1;
                            this.e = c0638Ld.f();
                            continue;
                        case 98:
                            int i4 = (i == true ? 1 : 0) & Fcntl.S_IRUSR;
                            i = i;
                            if (i4 != 256) {
                                this.m = new ArrayList();
                                i = (i == true ? 1 : 0) | Fcntl.S_IRUSR;
                            }
                            this.m.add(c0638Ld.a(C2903w00.v, c0389Bo));
                            continue;
                        case 104:
                            int i5 = (i == true ? 1 : 0) & 512;
                            i = i;
                            if (i5 != 512) {
                                this.n = new ArrayList();
                                i = (i == true ? 1 : 0) | 512;
                            }
                            this.n.add(Integer.valueOf(c0638Ld.f()));
                            continue;
                        case 106:
                            int iB = c0638Ld.b(c0638Ld.f());
                            int i6 = (i == true ? 1 : 0) & 512;
                            i = i;
                            if (i6 != 512 && c0638Ld.a() > 0) {
                                i = i;
                                this.n = new ArrayList();
                                i = (i == true ? 1 : 0) | 512;
                            }
                            i = i;
                            while (c0638Ld.a() > 0) {
                                this.n.add(Integer.valueOf(c0638Ld.f()));
                            }
                            c0638Ld.h = iB;
                            c0638Ld.j();
                            continue;
                        case 248:
                            int i7 = (i == true ? 1 : 0) & 8192;
                            i = i;
                            if (i7 != 8192) {
                                this.s = new ArrayList();
                                i = (i == true ? 1 : 0) | 8192;
                            }
                            this.s.add(Integer.valueOf(c0638Ld.f()));
                            continue;
                        case 250:
                            int iB2 = c0638Ld.b(c0638Ld.f());
                            int i8 = (i == true ? 1 : 0) & 8192;
                            i = i;
                            if (i8 != 8192 && c0638Ld.a() > 0) {
                                i = i;
                                this.s = new ArrayList();
                                i = (i == true ? 1 : 0) | 8192;
                            }
                            i = i;
                            while (c0638Ld.a() > 0) {
                                this.s.add(Integer.valueOf(c0638Ld.f()));
                            }
                            c0638Ld.h = iB2;
                            c0638Ld.j();
                            continue;
                        default:
                            if (!a(c0638Ld, c0767Qd, c0389Bo, i2)) {
                                break;
                            }
                            break;
                    }
                    z = true;
                } catch (QB e) {
                    e.b = this;
                    throw e;
                } catch (IOException e2) {
                    QB qb = new QB(e2.getMessage());
                    qb.b = this;
                    throw qb;
                }
            } catch (Throwable th) {
                if (((i == true ? 1 : 0) & 32) == 32) {
                    this.j = Collections.unmodifiableList(this.j);
                }
                if (((i == true ? 1 : 0) & Fcntl.S_IRUSR) == 256) {
                    this.m = Collections.unmodifiableList(this.m);
                }
                if (((i == true ? 1 : 0) & 512) == 512) {
                    this.n = Collections.unmodifiableList(this.n);
                }
                if (((i == true ? 1 : 0) & 8192) == 8192) {
                    this.s = Collections.unmodifiableList(this.s);
                }
                try {
                    c0767Qd.a();
                } catch (IOException unused) {
                } finally {
                    this.c = r7.c();
                }
                this.b.a();
                throw th;
            }
        }
        if (((i == true ? 1 : 0) & 32) == 32) {
            this.j = Collections.unmodifiableList(this.j);
        }
        if (((i == true ? 1 : 0) & Fcntl.S_IRUSR) == 256) {
            this.m = Collections.unmodifiableList(this.m);
        }
        if (((i == true ? 1 : 0) & 512) == 512) {
            this.n = Collections.unmodifiableList(this.n);
        }
        if (((i == true ? 1 : 0) & 8192) == 8192) {
            this.s = Collections.unmodifiableList(this.s);
        }
        try {
            c0767Qd.a();
        } catch (IOException unused2) {
        } finally {
            this.c = r7.c();
        }
        this.b.a();
    }

    @Override // com.android.tools.r8.internal.L0
    public final void a(C0767Qd c0767Qd) throws IOException {
        c();
        C0626Kr c0626Kr = new C0626Kr(this);
        if ((this.d & 2) == 2) {
            int i = this.f;
            c0767Qd.c(1, 0);
            c0767Qd.d(i);
        }
        if ((this.d & 4) == 4) {
            int i2 = this.g;
            c0767Qd.c(2, 0);
            c0767Qd.d(i2);
        }
        if ((this.d & 8) == 8) {
            c0767Qd.b(3, this.h);
        }
        for (int i3 = 0; i3 < this.j.size(); i3++) {
            c0767Qd.b(4, (L0) this.j.get(i3));
        }
        if ((this.d & 32) == 32) {
            c0767Qd.b(5, this.k);
        }
        if ((this.d & 128) == 128) {
            c0767Qd.b(6, this.p);
        }
        if ((this.d & Fcntl.S_IRUSR) == 256) {
            int i4 = this.q;
            c0767Qd.c(7, 0);
            c0767Qd.d(i4);
        }
        if ((this.d & 512) == 512) {
            int i5 = this.r;
            c0767Qd.c(8, 0);
            c0767Qd.d(i5);
        }
        if ((this.d & 16) == 16) {
            int i6 = this.i;
            c0767Qd.c(9, 0);
            c0767Qd.d(i6);
        }
        if ((this.d & 64) == 64) {
            int i7 = this.l;
            c0767Qd.c(10, 0);
            c0767Qd.d(i7);
        }
        if ((this.d & 1) == 1) {
            int i8 = this.e;
            c0767Qd.c(11, 0);
            c0767Qd.d(i8);
        }
        for (int i9 = 0; i9 < this.m.size(); i9++) {
            c0767Qd.b(12, (L0) this.m.get(i9));
        }
        if (this.n.size() > 0) {
            c0767Qd.g(106);
            c0767Qd.g(this.o);
        }
        for (int i10 = 0; i10 < this.n.size(); i10++) {
            c0767Qd.d(((Integer) this.n.get(i10)).intValue());
        }
        for (int i11 = 0; i11 < this.s.size(); i11++) {
            int iIntValue = ((Integer) this.s.get(i11)).intValue();
            c0767Qd.c(31, 0);
            c0767Qd.d(iIntValue);
        }
        c0626Kr.a(19000, c0767Qd);
        c0767Qd.a(this.c);
    }

    @Override // com.android.tools.r8.internal.UN
    public final L0 b() {
        return v;
    }

    @Override // com.android.tools.r8.internal.L0
    public final int c() {
        List list;
        int i = this.u;
        if (i != -1) {
            return i;
        }
        int i2 = 0;
        int iA = (this.d & 2) == 2 ? C0767Qd.a(1, this.f) : 0;
        if ((this.d & 4) == 4) {
            iA += C0767Qd.a(2, this.g);
        }
        if ((this.d & 8) == 8) {
            iA += C0767Qd.a(3, this.h);
        }
        for (int i3 = 0; i3 < this.j.size(); i3++) {
            iA += C0767Qd.a(4, (L0) this.j.get(i3));
        }
        if ((this.d & 32) == 32) {
            iA += C0767Qd.a(5, this.k);
        }
        if ((this.d & 128) == 128) {
            iA += C0767Qd.a(6, this.p);
        }
        if ((this.d & Fcntl.S_IRUSR) == 256) {
            iA += C0767Qd.a(7, this.q);
        }
        if ((this.d & 512) == 512) {
            iA += C0767Qd.a(8, this.r);
        }
        if ((this.d & 16) == 16) {
            iA += C0767Qd.a(9, this.i);
        }
        if ((this.d & 64) == 64) {
            iA += C0767Qd.a(10, this.l);
        }
        if ((this.d & 1) == 1) {
            iA += C0767Qd.a(11, this.e);
        }
        for (int i4 = 0; i4 < this.m.size(); i4++) {
            iA += C0767Qd.a(12, (L0) this.m.get(i4));
        }
        int i5 = 0;
        int iB = 0;
        while (true) {
            int size = this.n.size();
            list = this.n;
            if (i5 >= size) {
                break;
            }
            int iIntValue = ((Integer) list.get(i5)).intValue();
            iB += iIntValue >= 0 ? C0767Qd.b(iIntValue) : 10;
            i5++;
        }
        int iB2 = iA + iB;
        if (!list.isEmpty()) {
            iB2 = iB2 + 1 + (iB >= 0 ? C0767Qd.b(iB) : 10);
        }
        this.o = iB;
        int iB3 = 0;
        while (true) {
            int size2 = this.s.size();
            List list2 = this.s;
            if (i2 >= size2) {
                int size3 = this.c.size() + g() + (list2.size() * 2) + iB2 + iB3;
                this.u = size3;
                return size3;
            }
            int iIntValue2 = ((Integer) list2.get(i2)).intValue();
            iB3 += iIntValue2 >= 0 ? C0767Qd.b(iIntValue2) : 10;
            i2++;
        }
    }

    @Override // com.android.tools.r8.internal.L0
    public final AbstractC0574Ir d() {
        return new C1364e00();
    }

    @Override // com.android.tools.r8.internal.L0
    public final AbstractC0574Ir e() {
        return new C1364e00().a(this);
    }

    public final void h() {
        this.e = 518;
        this.f = 2054;
        this.g = 0;
        C2903w00 c2903w00 = C2903w00.u;
        this.h = c2903w00;
        this.i = 0;
        List list = Collections.EMPTY_LIST;
        this.j = list;
        this.k = c2903w00;
        this.l = 0;
        this.m = list;
        this.n = list;
        this.p = J00.m;
        this.q = 0;
        this.r = 0;
        this.s = list;
    }

    @Override // com.android.tools.r8.internal.UN
    public final boolean a() {
        byte b = this.t;
        if (b == 1) {
            return true;
        }
        if (b == 0) {
            return false;
        }
        int i = this.d;
        if ((i & 4) == 4) {
            if ((i & 8) == 8 && !this.h.a()) {
                this.t = (byte) 0;
                return false;
            }
            for (int i2 = 0; i2 < this.j.size(); i2++) {
                if (!((D00) this.j.get(i2)).a()) {
                    this.t = (byte) 0;
                    return false;
                }
            }
            if ((this.d & 32) == 32 && !this.k.a()) {
                this.t = (byte) 0;
                return false;
            }
            for (int i3 = 0; i3 < this.m.size(); i3++) {
                if (!((C2903w00) this.m.get(i3)).a()) {
                    this.t = (byte) 0;
                    return false;
                }
            }
            if ((this.d & 128) == 128 && !this.p.a()) {
                this.t = (byte) 0;
                return false;
            }
            if (!f()) {
                this.t = (byte) 0;
                return false;
            }
            this.t = (byte) 1;
            return true;
        }
        this.t = (byte) 0;
        return false;
    }

    public C1450f00() {
        this.o = -1;
        this.t = (byte) -1;
        this.u = -1;
        this.c = T7.b;
    }

    public C1450f00(AbstractC0600Jr abstractC0600Jr) {
        super(abstractC0600Jr);
        this.o = -1;
        this.t = (byte) -1;
        this.u = -1;
        this.c = abstractC0600Jr.b;
    }
}
