package com.android.tools.r8.internal;

import com.android.apksig.internal.util.AndroidSdkVersion;
import com.sun.jna.platform.linux.Fcntl;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/* JADX INFO: renamed from: com.android.tools.r8.internal.w00, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C2903w00 extends Lr {
    public static final C2903w00 u;
    public static final C2391q00 v = new C2391q00();
    public final T7 c;
    public int d;
    public List e;
    public boolean f;
    public int g;
    public C2903w00 h;
    public int i;
    public int j;
    public int k;
    public int l;
    public int m;
    public C2903w00 n;
    public int o;
    public C2903w00 p;
    public int q;
    public int r;
    public byte s;
    public int t;

    static {
        C2903w00 c2903w00 = new C2903w00();
        u = c2903w00;
        c2903w00.h();
    }

    public C2903w00(C0638Ld c0638Ld, C0389Bo c0389Bo) {
        this.s = (byte) -1;
        this.t = -1;
        h();
        R7 r7 = new R7();
        C0767Qd c0767Qd = new C0767Qd(r7, new byte[1]);
        boolean z = false;
        boolean z2 = false;
        while (!z) {
            try {
                try {
                    int i = c0638Ld.i();
                    C2817v00 c2817v00A = null;
                    switch (i) {
                        case 0:
                            break;
                        case 8:
                            this.d |= 4096;
                            this.r = c0638Ld.f();
                            continue;
                        case AndroidSdkVersion.JELLY_BEAN_MR2 /* 18 */:
                            if (!z2) {
                                this.e = new ArrayList();
                                z2 = true;
                            }
                            this.e.add(c0638Ld.a(C2732u00.j, c0389Bo));
                            continue;
                        case AndroidSdkVersion.N /* 24 */:
                            this.d |= 1;
                            this.f = c0638Ld.g() != 0;
                            continue;
                        case 32:
                            this.d |= 2;
                            this.g = c0638Ld.f();
                            continue;
                        case 42:
                            if ((this.d & 4) == 4) {
                                C2903w00 c2903w00 = this.h;
                                c2903w00.getClass();
                                c2817v00A = a(c2903w00);
                            }
                            C2903w00 c2903w01 = (C2903w00) c0638Ld.a(v, c0389Bo);
                            this.h = c2903w01;
                            if (c2817v00A != null) {
                                c2817v00A.a(c2903w01);
                                this.h = c2817v00A.f();
                            }
                            this.d |= 4;
                            continue;
                        case 48:
                            this.d |= 16;
                            this.j = c0638Ld.f();
                            continue;
                        case Fcntl.S_IRWXG /* 56 */:
                            this.d |= 32;
                            this.k = c0638Ld.f();
                            continue;
                        case 64:
                            this.d |= 8;
                            this.i = c0638Ld.f();
                            continue;
                        case 72:
                            this.d |= 64;
                            this.l = c0638Ld.f();
                            continue;
                        case 82:
                            if ((this.d & Fcntl.S_IRUSR) == 256) {
                                C2903w00 c2903w02 = this.n;
                                c2903w02.getClass();
                                c2817v00A = a(c2903w02);
                            }
                            C2903w00 c2903w03 = (C2903w00) c0638Ld.a(v, c0389Bo);
                            this.n = c2903w03;
                            if (c2817v00A != null) {
                                c2817v00A.a(c2903w03);
                                this.n = c2817v00A.f();
                            }
                            this.d |= Fcntl.S_IRUSR;
                            continue;
                        case 88:
                            this.d |= 512;
                            this.o = c0638Ld.f();
                            continue;
                        case 96:
                            this.d |= 128;
                            this.m = c0638Ld.f();
                            continue;
                        case 106:
                            if ((this.d & Fcntl.S_ISGID) == 1024) {
                                C2903w00 c2903w04 = this.p;
                                c2903w04.getClass();
                                c2817v00A = a(c2903w04);
                            }
                            C2903w00 c2903w05 = (C2903w00) c0638Ld.a(v, c0389Bo);
                            this.p = c2903w05;
                            if (c2817v00A != null) {
                                c2817v00A.a(c2903w05);
                                this.p = c2817v00A.f();
                            }
                            this.d |= Fcntl.S_ISGID;
                            continue;
                        case 112:
                            this.d |= Fcntl.S_ISUID;
                            this.q = c0638Ld.f();
                            continue;
                        default:
                            if (!a(c0638Ld, c0767Qd, c0389Bo, i)) {
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
                if (z2) {
                    this.e = Collections.unmodifiableList(this.e);
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
        if (z2) {
            this.e = Collections.unmodifiableList(this.e);
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
        if ((this.d & 4096) == 4096) {
            int i = this.r;
            c0767Qd.c(1, 0);
            c0767Qd.d(i);
        }
        for (int i2 = 0; i2 < this.e.size(); i2++) {
            c0767Qd.b(2, (L0) this.e.get(i2));
        }
        if ((this.d & 1) == 1) {
            boolean z = this.f;
            c0767Qd.c(3, 0);
            c0767Qd.e(z ? 1 : 0);
        }
        if ((this.d & 2) == 2) {
            int i3 = this.g;
            c0767Qd.c(4, 0);
            c0767Qd.d(i3);
        }
        if ((this.d & 4) == 4) {
            c0767Qd.b(5, this.h);
        }
        if ((this.d & 16) == 16) {
            int i4 = this.j;
            c0767Qd.c(6, 0);
            c0767Qd.d(i4);
        }
        if ((this.d & 32) == 32) {
            int i5 = this.k;
            c0767Qd.c(7, 0);
            c0767Qd.d(i5);
        }
        if ((this.d & 8) == 8) {
            int i6 = this.i;
            c0767Qd.c(8, 0);
            c0767Qd.d(i6);
        }
        if ((this.d & 64) == 64) {
            int i7 = this.l;
            c0767Qd.c(9, 0);
            c0767Qd.d(i7);
        }
        if ((this.d & Fcntl.S_IRUSR) == 256) {
            c0767Qd.b(10, this.n);
        }
        if ((this.d & 512) == 512) {
            int i8 = this.o;
            c0767Qd.c(11, 0);
            c0767Qd.d(i8);
        }
        if ((this.d & 128) == 128) {
            int i9 = this.m;
            c0767Qd.c(12, 0);
            c0767Qd.d(i9);
        }
        if ((this.d & Fcntl.S_ISGID) == 1024) {
            c0767Qd.b(13, this.p);
        }
        if ((this.d & Fcntl.S_ISUID) == 2048) {
            int i10 = this.q;
            c0767Qd.c(14, 0);
            c0767Qd.d(i10);
        }
        c0626Kr.a(200, c0767Qd);
        c0767Qd.a(this.c);
    }

    @Override // com.android.tools.r8.internal.UN
    public final L0 b() {
        return u;
    }

    @Override // com.android.tools.r8.internal.L0
    public final int c() {
        int i = this.t;
        if (i != -1) {
            return i;
        }
        int iA = (this.d & 4096) == 4096 ? C0767Qd.a(1, this.r) : 0;
        for (int i2 = 0; i2 < this.e.size(); i2++) {
            iA += C0767Qd.a(2, (L0) this.e.get(i2));
        }
        if ((this.d & 1) == 1) {
            iA += C0767Qd.c(3) + 1;
        }
        if ((this.d & 2) == 2) {
            iA += C0767Qd.a(4, this.g);
        }
        if ((this.d & 4) == 4) {
            iA += C0767Qd.a(5, this.h);
        }
        if ((this.d & 16) == 16) {
            iA += C0767Qd.a(6, this.j);
        }
        if ((this.d & 32) == 32) {
            iA += C0767Qd.a(7, this.k);
        }
        if ((this.d & 8) == 8) {
            iA += C0767Qd.a(8, this.i);
        }
        if ((this.d & 64) == 64) {
            iA += C0767Qd.a(9, this.l);
        }
        if ((this.d & Fcntl.S_IRUSR) == 256) {
            iA += C0767Qd.a(10, this.n);
        }
        if ((this.d & 512) == 512) {
            iA += C0767Qd.a(11, this.o);
        }
        if ((this.d & 128) == 128) {
            iA += C0767Qd.a(12, this.m);
        }
        if ((this.d & Fcntl.S_ISGID) == 1024) {
            iA += C0767Qd.a(13, this.p);
        }
        if ((this.d & Fcntl.S_ISUID) == 2048) {
            iA += C0767Qd.a(14, this.q);
        }
        int size = this.c.size() + g() + iA;
        this.t = size;
        return size;
    }

    @Override // com.android.tools.r8.internal.L0
    public final AbstractC0574Ir d() {
        return new C2817v00();
    }

    public final void h() {
        this.e = Collections.EMPTY_LIST;
        this.f = false;
        this.g = 0;
        C2903w00 c2903w00 = u;
        this.h = c2903w00;
        this.i = 0;
        this.j = 0;
        this.k = 0;
        this.l = 0;
        this.m = 0;
        this.n = c2903w00;
        this.o = 0;
        this.p = c2903w00;
        this.q = 0;
        this.r = 0;
    }

    @Override // com.android.tools.r8.internal.L0
    /* JADX INFO: renamed from: i, reason: merged with bridge method [inline-methods] */
    public final C2817v00 e() {
        return a(this);
    }

    @Override // com.android.tools.r8.internal.UN
    public final boolean a() {
        byte b = this.s;
        if (b == 1) {
            return true;
        }
        if (b == 0) {
            return false;
        }
        for (int i = 0; i < this.e.size(); i++) {
            if (!((C2732u00) this.e.get(i)).a()) {
                this.s = (byte) 0;
                return false;
            }
        }
        if ((this.d & 4) == 4 && !this.h.a()) {
            this.s = (byte) 0;
            return false;
        }
        if ((this.d & Fcntl.S_IRUSR) == 256 && !this.n.a()) {
            this.s = (byte) 0;
            return false;
        }
        if ((this.d & Fcntl.S_ISGID) == 1024 && !this.p.a()) {
            this.s = (byte) 0;
            return false;
        }
        if (!f()) {
            this.s = (byte) 0;
            return false;
        }
        this.s = (byte) 1;
        return true;
    }

    public static C2817v00 a(C2903w00 c2903w00) {
        return new C2817v00().a(c2903w00);
    }

    public C2903w00() {
        this.s = (byte) -1;
        this.t = -1;
        this.c = T7.b;
    }

    public C2903w00(AbstractC0600Jr abstractC0600Jr) {
        super(abstractC0600Jr);
        this.s = (byte) -1;
        this.t = -1;
        this.c = abstractC0600Jr.b;
    }
}
