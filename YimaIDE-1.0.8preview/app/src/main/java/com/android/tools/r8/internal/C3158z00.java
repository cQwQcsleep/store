package com.android.tools.r8.internal;

import com.android.apksig.internal.util.AndroidSdkVersion;
import com.sun.jna.platform.linux.Fcntl;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/* JADX INFO: renamed from: com.android.tools.r8.internal.z00, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C3158z00 extends Lr {
    public static final C3158z00 p;
    public static final C2989x00 q = new C2989x00();
    public final T7 c;
    public int d;
    public int e;
    public int f;
    public List g;
    public C2903w00 h;
    public int i;
    public C2903w00 j;
    public int k;
    public List l;
    public List m;
    public byte n;
    public int o;

    static {
        C3158z00 c3158z00 = new C3158z00();
        p = c3158z00;
        c3158z00.e = 6;
        c3158z00.f = 0;
        List list = Collections.EMPTY_LIST;
        c3158z00.g = list;
        C2903w00 c2903w00 = C2903w00.u;
        c3158z00.h = c2903w00;
        c3158z00.i = 0;
        c3158z00.j = c2903w00;
        c3158z00.k = 0;
        c3158z00.l = list;
        c3158z00.m = list;
    }

    public C3158z00(C0638Ld c0638Ld, C0389Bo c0389Bo) {
        this.n = (byte) -1;
        this.o = -1;
        this.e = 6;
        boolean z = false;
        this.f = 0;
        List list = Collections.EMPTY_LIST;
        this.g = list;
        C2903w00 c2903w00 = C2903w00.u;
        this.h = c2903w00;
        this.i = 0;
        this.j = c2903w00;
        this.k = 0;
        this.l = list;
        this.m = list;
        R7 r7 = new R7();
        C0767Qd c0767Qd = new C0767Qd(r7, new byte[1]);
        int i = 0;
        while (!z) {
            try {
                try {
                    int i2 = c0638Ld.i();
                    C2817v00 c2817v00A = null;
                    switch (i2) {
                        case 0:
                            break;
                        case 8:
                            this.d |= 1;
                            this.e = c0638Ld.f();
                            continue;
                        case Fcntl.S_IWGRP /* 16 */:
                            this.d |= 2;
                            this.f = c0638Ld.f();
                            continue;
                        case AndroidSdkVersion.O /* 26 */:
                            if ((i & 4) != 4) {
                                this.g = new ArrayList();
                                i |= 4;
                            }
                            this.g.add(c0638Ld.a(D00.o, c0389Bo));
                            continue;
                        case AndroidSdkVersion.U /* 34 */:
                            if ((this.d & 4) == 4) {
                                C2903w00 c2903w01 = this.h;
                                c2903w01.getClass();
                                c2817v00A = C2903w00.a(c2903w01);
                            }
                            C2903w00 c2903w02 = (C2903w00) c0638Ld.a(C2903w00.v, c0389Bo);
                            this.h = c2903w02;
                            if (c2817v00A != null) {
                                c2817v00A.a(c2903w02);
                                this.h = c2817v00A.f();
                            }
                            this.d |= 4;
                            continue;
                        case 40:
                            this.d |= 8;
                            this.i = c0638Ld.f();
                            continue;
                        case 50:
                            if ((this.d & 16) == 16) {
                                C2903w00 c2903w03 = this.j;
                                c2903w03.getClass();
                                c2817v00A = C2903w00.a(c2903w03);
                            }
                            C2903w00 c2903w04 = (C2903w00) c0638Ld.a(C2903w00.v, c0389Bo);
                            this.j = c2903w04;
                            if (c2817v00A != null) {
                                c2817v00A.a(c2903w04);
                                this.j = c2817v00A.f();
                            }
                            this.d |= 16;
                            continue;
                        case Fcntl.S_IRWXG /* 56 */:
                            this.d |= 32;
                            this.k = c0638Ld.f();
                            continue;
                        case 66:
                            if ((i & 128) != 128) {
                                this.l = new ArrayList();
                                i |= 128;
                            }
                            this.l.add(c0638Ld.a(C3117yZ.i, c0389Bo));
                            continue;
                        case 248:
                            if ((i & Fcntl.S_IRUSR) != 256) {
                                this.m = new ArrayList();
                                i |= Fcntl.S_IRUSR;
                            }
                            this.m.add(Integer.valueOf(c0638Ld.f()));
                            continue;
                        case 250:
                            int iB = c0638Ld.b(c0638Ld.f());
                            if ((i & Fcntl.S_IRUSR) != 256 && c0638Ld.a() > 0) {
                                this.m = new ArrayList();
                                i |= Fcntl.S_IRUSR;
                            }
                            while (c0638Ld.a() > 0) {
                                this.m.add(Integer.valueOf(c0638Ld.f()));
                            }
                            c0638Ld.h = iB;
                            c0638Ld.j();
                            continue;
                        default:
                            if (!a(c0638Ld, c0767Qd, c0389Bo, i2)) {
                                break;
                            }
                            break;
                    }
                    z = true;
                } catch (Throwable th) {
                    if ((i & 4) == 4) {
                        this.g = Collections.unmodifiableList(this.g);
                    }
                    if ((i & 128) == 128) {
                        this.l = Collections.unmodifiableList(this.l);
                    }
                    if ((i & Fcntl.S_IRUSR) == 256) {
                        this.m = Collections.unmodifiableList(this.m);
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
            } catch (QB e) {
                e.b = this;
                throw e;
            } catch (IOException e2) {
                QB qb = new QB(e2.getMessage());
                qb.b = this;
                throw qb;
            }
        }
        if ((i & 4) == 4) {
            this.g = Collections.unmodifiableList(this.g);
        }
        if ((i & 128) == 128) {
            this.l = Collections.unmodifiableList(this.l);
        }
        if ((i & Fcntl.S_IRUSR) == 256) {
            this.m = Collections.unmodifiableList(this.m);
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
        if ((this.d & 1) == 1) {
            int i = this.e;
            c0767Qd.c(1, 0);
            c0767Qd.d(i);
        }
        if ((this.d & 2) == 2) {
            int i2 = this.f;
            c0767Qd.c(2, 0);
            c0767Qd.d(i2);
        }
        for (int i3 = 0; i3 < this.g.size(); i3++) {
            c0767Qd.b(3, (L0) this.g.get(i3));
        }
        if ((this.d & 4) == 4) {
            c0767Qd.b(4, this.h);
        }
        if ((this.d & 8) == 8) {
            int i4 = this.i;
            c0767Qd.c(5, 0);
            c0767Qd.d(i4);
        }
        if ((this.d & 16) == 16) {
            c0767Qd.b(6, this.j);
        }
        if ((this.d & 32) == 32) {
            int i5 = this.k;
            c0767Qd.c(7, 0);
            c0767Qd.d(i5);
        }
        for (int i6 = 0; i6 < this.l.size(); i6++) {
            c0767Qd.b(8, (L0) this.l.get(i6));
        }
        for (int i7 = 0; i7 < this.m.size(); i7++) {
            int iIntValue = ((Integer) this.m.get(i7)).intValue();
            c0767Qd.c(31, 0);
            c0767Qd.d(iIntValue);
        }
        c0626Kr.a(200, c0767Qd);
        c0767Qd.a(this.c);
    }

    @Override // com.android.tools.r8.internal.UN
    public final L0 b() {
        return p;
    }

    @Override // com.android.tools.r8.internal.L0
    public final int c() {
        int i = this.o;
        if (i != -1) {
            return i;
        }
        int i2 = 0;
        int iA = (this.d & 1) == 1 ? C0767Qd.a(1, this.e) : 0;
        if ((this.d & 2) == 2) {
            iA += C0767Qd.a(2, this.f);
        }
        for (int i3 = 0; i3 < this.g.size(); i3++) {
            iA += C0767Qd.a(3, (L0) this.g.get(i3));
        }
        if ((this.d & 4) == 4) {
            iA += C0767Qd.a(4, this.h);
        }
        if ((this.d & 8) == 8) {
            iA += C0767Qd.a(5, this.i);
        }
        if ((this.d & 16) == 16) {
            iA += C0767Qd.a(6, this.j);
        }
        if ((this.d & 32) == 32) {
            iA += C0767Qd.a(7, this.k);
        }
        for (int i4 = 0; i4 < this.l.size(); i4++) {
            iA += C0767Qd.a(8, (L0) this.l.get(i4));
        }
        int iB = 0;
        while (true) {
            int size = this.m.size();
            List list = this.m;
            if (i2 >= size) {
                int size2 = this.c.size() + g() + (list.size() * 2) + iA + iB;
                this.o = size2;
                return size2;
            }
            int iIntValue = ((Integer) list.get(i2)).intValue();
            iB += iIntValue >= 0 ? C0767Qd.b(iIntValue) : 10;
            i2++;
        }
    }

    @Override // com.android.tools.r8.internal.L0
    public final AbstractC0574Ir d() {
        return new C3074y00();
    }

    @Override // com.android.tools.r8.internal.L0
    public final AbstractC0574Ir e() {
        return new C3074y00().a(this);
    }

    @Override // com.android.tools.r8.internal.UN
    public final boolean a() {
        byte b = this.n;
        if (b == 1) {
            return true;
        }
        if (b == 0) {
            return false;
        }
        if ((this.d & 2) == 2) {
            for (int i = 0; i < this.g.size(); i++) {
                if (!((D00) this.g.get(i)).a()) {
                    this.n = (byte) 0;
                    return false;
                }
            }
            if ((this.d & 4) == 4 && !this.h.a()) {
                this.n = (byte) 0;
                return false;
            }
            if ((this.d & 16) == 16 && !this.j.a()) {
                this.n = (byte) 0;
                return false;
            }
            for (int i2 = 0; i2 < this.l.size(); i2++) {
                if (!((C3117yZ) this.l.get(i2)).a()) {
                    this.n = (byte) 0;
                    return false;
                }
            }
            if (!f()) {
                this.n = (byte) 0;
                return false;
            }
            this.n = (byte) 1;
            return true;
        }
        this.n = (byte) 0;
        return false;
    }

    public C3158z00() {
        this.n = (byte) -1;
        this.o = -1;
        this.c = T7.b;
    }

    public C3158z00(AbstractC0600Jr abstractC0600Jr) {
        super(abstractC0600Jr);
        this.n = (byte) -1;
        this.o = -1;
        this.c = abstractC0600Jr.b;
    }
}
