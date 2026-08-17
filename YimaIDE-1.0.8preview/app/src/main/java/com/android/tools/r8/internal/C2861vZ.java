package com.android.tools.r8.internal;

import com.android.apksig.internal.util.AndroidSdkVersion;
import com.sun.jna.platform.linux.Fcntl;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/* JADX INFO: renamed from: com.android.tools.r8.internal.vZ, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C2861vZ extends AbstractC0729Or {
    public static final C2861vZ q;
    public static final C2604sZ r = new C2604sZ();
    public final T7 b;
    public int c;
    public EnumC2776uZ d;
    public long e;
    public float f;
    public double g;
    public int h;
    public int i;
    public int j;
    public C3117yZ k;
    public List l;
    public int m;
    public int n;
    public byte o;
    public int p;

    static {
        C2861vZ c2861vZ = new C2861vZ();
        q = c2861vZ;
        c2861vZ.d = EnumC2776uZ.c;
        c2861vZ.e = 0L;
        c2861vZ.f = 0.0f;
        c2861vZ.g = 0.0d;
        c2861vZ.h = 0;
        c2861vZ.i = 0;
        c2861vZ.j = 0;
        c2861vZ.k = C3117yZ.h;
        c2861vZ.l = Collections.EMPTY_LIST;
        c2861vZ.m = 0;
        c2861vZ.n = 0;
    }

    public C2861vZ(C0638Ld c0638Ld, C0389Bo c0389Bo) {
        C3033xZ c3033xZA;
        this.o = (byte) -1;
        this.p = -1;
        this.d = EnumC2776uZ.c;
        this.e = 0L;
        this.f = 0.0f;
        this.g = 0.0d;
        boolean z = false;
        this.h = 0;
        this.i = 0;
        this.j = 0;
        this.k = C3117yZ.h;
        this.l = Collections.EMPTY_LIST;
        this.m = 0;
        this.n = 0;
        R7 r7 = new R7();
        C0767Qd c0767Qd = new C0767Qd(r7, new byte[1]);
        char c = 0;
        while (!z) {
            try {
                try {
                    int i = c0638Ld.i();
                    switch (i) {
                        case 0:
                            break;
                        case 8:
                            int iF = c0638Ld.f();
                            EnumC2776uZ enumC2776uZB = EnumC2776uZ.b(iF);
                            if (enumC2776uZB == null) {
                                c0767Qd.g(i);
                                c0767Qd.g(iF);
                            } else {
                                this.c |= 1;
                                this.d = enumC2776uZB;
                                continue;
                            }
                            break;
                        case Fcntl.S_IWGRP /* 16 */:
                            this.c |= 2;
                            long jG = c0638Ld.g();
                            this.e = (-(jG & 1)) ^ (jG >>> 1);
                            continue;
                        case AndroidSdkVersion.Q /* 29 */:
                            this.c |= 4;
                            this.f = Float.intBitsToFloat(c0638Ld.d());
                            continue;
                        case AndroidSdkVersion.T /* 33 */:
                            this.c |= 8;
                            this.g = Double.longBitsToDouble(c0638Ld.e());
                            continue;
                        case 40:
                            this.c |= 16;
                            this.h = c0638Ld.f();
                            continue;
                        case 48:
                            this.c |= 32;
                            this.i = c0638Ld.f();
                            continue;
                        case Fcntl.S_IRWXG /* 56 */:
                            this.c |= 64;
                            this.j = c0638Ld.f();
                            continue;
                        case 66:
                            if ((this.c & 128) == 128) {
                                C3117yZ c3117yZ = this.k;
                                c3117yZ.getClass();
                                c3033xZA = new C3033xZ().a(c3117yZ);
                            } else {
                                c3033xZA = null;
                            }
                            C3117yZ c3117yZ2 = (C3117yZ) c0638Ld.a(C3117yZ.i, c0389Bo);
                            this.k = c3117yZ2;
                            if (c3033xZA != null) {
                                c3033xZA.a(c3117yZ2);
                                this.k = c3033xZA.e();
                            }
                            this.c |= 128;
                            continue;
                        case 74:
                            if ((c & 256) != 256) {
                                this.l = new ArrayList();
                                c = 256;
                            }
                            this.l.add(c0638Ld.a(r, c0389Bo));
                            continue;
                        case 80:
                            this.c |= 512;
                            this.n = c0638Ld.f();
                            continue;
                        case 88:
                            this.c |= Fcntl.S_IRUSR;
                            this.m = c0638Ld.f();
                            continue;
                        default:
                            if (!c0638Ld.a(i, c0767Qd)) {
                                break;
                            }
                            break;
                    }
                    z = true;
                } catch (Throwable th) {
                    if ((c & 256) == 256) {
                        this.l = Collections.unmodifiableList(this.l);
                    }
                    try {
                        c0767Qd.a();
                    } catch (IOException unused) {
                    } finally {
                        this.b = r7.c();
                    }
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
        if ((c & 256) == 256) {
            this.l = Collections.unmodifiableList(this.l);
        }
        try {
            c0767Qd.a();
        } catch (IOException unused2) {
        } finally {
            this.b = r7.c();
        }
    }

    @Override // com.android.tools.r8.internal.L0
    public final void a(C0767Qd c0767Qd) throws IOException {
        c();
        if ((this.c & 1) == 1) {
            c0767Qd.b(1, this.d.b);
        }
        if ((this.c & 2) == 2) {
            long j = this.e;
            c0767Qd.c(2, 0);
            c0767Qd.d(C0767Qd.b(j));
        }
        if ((this.c & 4) == 4) {
            float f = this.f;
            c0767Qd.c(3, 5);
            c0767Qd.f(Float.floatToRawIntBits(f));
        }
        if ((this.c & 8) == 8) {
            double d = this.g;
            c0767Qd.c(4, 1);
            c0767Qd.c(Double.doubleToRawLongBits(d));
        }
        if ((this.c & 16) == 16) {
            int i = this.h;
            c0767Qd.c(5, 0);
            c0767Qd.d(i);
        }
        if ((this.c & 32) == 32) {
            int i2 = this.i;
            c0767Qd.c(6, 0);
            c0767Qd.d(i2);
        }
        if ((this.c & 64) == 64) {
            int i3 = this.j;
            c0767Qd.c(7, 0);
            c0767Qd.d(i3);
        }
        if ((this.c & 128) == 128) {
            c0767Qd.b(8, this.k);
        }
        for (int i4 = 0; i4 < this.l.size(); i4++) {
            c0767Qd.b(9, (L0) this.l.get(i4));
        }
        if ((this.c & 512) == 512) {
            int i5 = this.n;
            c0767Qd.c(10, 0);
            c0767Qd.d(i5);
        }
        if ((this.c & Fcntl.S_IRUSR) == 256) {
            int i6 = this.m;
            c0767Qd.c(11, 0);
            c0767Qd.d(i6);
        }
        c0767Qd.a(this.b);
    }

    @Override // com.android.tools.r8.internal.L0
    public final int c() {
        int i = this.p;
        if (i != -1) {
            return i;
        }
        int iA = (this.c & 1) == 1 ? C0767Qd.a(this.d.b) + C0767Qd.c(1) : 0;
        if ((this.c & 2) == 2) {
            iA += C0767Qd.a(C0767Qd.b(this.e)) + C0767Qd.c(2);
        }
        if ((this.c & 4) == 4) {
            iA += C0767Qd.c(3) + 4;
        }
        if ((this.c & 8) == 8) {
            iA += C0767Qd.c(4) + 8;
        }
        if ((this.c & 16) == 16) {
            iA += C0767Qd.a(5, this.h);
        }
        if ((this.c & 32) == 32) {
            iA += C0767Qd.a(6, this.i);
        }
        if ((this.c & 64) == 64) {
            iA += C0767Qd.a(7, this.j);
        }
        if ((this.c & 128) == 128) {
            iA += C0767Qd.a(8, this.k);
        }
        for (int i2 = 0; i2 < this.l.size(); i2++) {
            iA += C0767Qd.a(9, (L0) this.l.get(i2));
        }
        if ((this.c & 512) == 512) {
            iA += C0767Qd.a(10, this.n);
        }
        if ((this.c & Fcntl.S_IRUSR) == 256) {
            iA += C0767Qd.a(11, this.m);
        }
        int size = this.b.size() + iA;
        this.p = size;
        return size;
    }

    @Override // com.android.tools.r8.internal.L0
    public final AbstractC0574Ir d() {
        return new C2690tZ();
    }

    @Override // com.android.tools.r8.internal.L0
    public final AbstractC0574Ir e() {
        return new C2690tZ().a(this);
    }

    @Override // com.android.tools.r8.internal.UN
    public final boolean a() {
        byte b = this.o;
        if (b == 1) {
            return true;
        }
        if (b == 0) {
            return false;
        }
        if ((this.c & 128) == 128 && !this.k.a()) {
            this.o = (byte) 0;
            return false;
        }
        for (int i = 0; i < this.l.size(); i++) {
            if (!((C2861vZ) this.l.get(i)).a()) {
                this.o = (byte) 0;
                return false;
            }
        }
        this.o = (byte) 1;
        return true;
    }

    public C2861vZ() {
        this.o = (byte) -1;
        this.p = -1;
        this.b = T7.b;
    }

    public C2861vZ(AbstractC0574Ir abstractC0574Ir) {
        super(0);
        this.o = (byte) -1;
        this.p = -1;
        this.b = abstractC0574Ir.b;
    }
}
