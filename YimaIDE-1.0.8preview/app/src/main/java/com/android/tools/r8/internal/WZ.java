package com.android.tools.r8.internal;

import com.sun.jna.platform.linux.Fcntl;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class WZ extends AbstractC0600Jr {
    public int e;
    public int f = 6;
    public int g = 6;
    public int h;
    public C2903w00 i;
    public int j;
    public List k;
    public C2903w00 l;
    public int m;
    public List n;
    public List o;
    public List p;
    public G00 q;
    public List r;
    public IZ s;

    public WZ() {
        C2903w00 c2903w00 = C2903w00.u;
        this.i = c2903w00;
        List list = Collections.EMPTY_LIST;
        this.k = list;
        this.l = c2903w00;
        this.n = list;
        this.o = list;
        this.p = list;
        this.q = G00.h;
        this.r = list;
        this.s = IZ.f;
    }

    @Override // com.android.tools.r8.internal.AbstractC0574Ir
    public final WZ a(XZ xz) {
        IZ iz;
        G00 g00;
        C2903w00 c2903w00;
        C2903w00 c2903w01;
        if (xz == XZ.v) {
            return this;
        }
        int i = xz.d;
        if ((i & 1) == 1) {
            int i2 = xz.e;
            this.e = 1 | this.e;
            this.f = i2;
        }
        if ((i & 2) == 2) {
            int i3 = xz.f;
            this.e = 2 | this.e;
            this.g = i3;
        }
        if ((i & 4) == 4) {
            int i4 = xz.g;
            this.e = 4 | this.e;
            this.h = i4;
        }
        if ((i & 8) == 8) {
            C2903w00 c2903w02 = xz.h;
            if ((this.e & 8) != 8 || (c2903w01 = this.i) == C2903w00.u) {
                this.i = c2903w02;
            } else {
                this.i = C2903w00.a(c2903w01).a(c2903w02).f();
            }
            this.e |= 8;
        }
        if ((xz.d & 16) == 16) {
            int i5 = xz.i;
            this.e = 16 | this.e;
            this.j = i5;
        }
        if (!xz.j.isEmpty()) {
            if (this.k.isEmpty()) {
                this.k = xz.j;
                this.e &= -33;
            } else {
                if ((this.e & 32) != 32) {
                    this.k = new ArrayList(this.k);
                    this.e |= 32;
                }
                this.k.addAll(xz.j);
            }
        }
        if ((xz.d & 32) == 32) {
            C2903w00 c2903w03 = xz.k;
            if ((this.e & 64) != 64 || (c2903w00 = this.l) == C2903w00.u) {
                this.l = c2903w03;
            } else {
                this.l = C2903w00.a(c2903w00).a(c2903w03).f();
            }
            this.e |= 64;
        }
        if ((xz.d & 64) == 64) {
            int i6 = xz.l;
            this.e |= 128;
            this.m = i6;
        }
        if (!xz.m.isEmpty()) {
            if (this.n.isEmpty()) {
                this.n = xz.m;
                this.e &= -257;
            } else {
                if ((this.e & Fcntl.S_IRUSR) != 256) {
                    this.n = new ArrayList(this.n);
                    this.e |= Fcntl.S_IRUSR;
                }
                this.n.addAll(xz.m);
            }
        }
        if (!xz.n.isEmpty()) {
            if (this.o.isEmpty()) {
                this.o = xz.n;
                this.e &= -513;
            } else {
                if ((this.e & 512) != 512) {
                    this.o = new ArrayList(this.o);
                    this.e |= 512;
                }
                this.o.addAll(xz.n);
            }
        }
        if (!xz.p.isEmpty()) {
            if (this.p.isEmpty()) {
                this.p = xz.p;
                this.e &= -1025;
            } else {
                if ((this.e & Fcntl.S_ISGID) != 1024) {
                    this.p = new ArrayList(this.p);
                    this.e |= Fcntl.S_ISGID;
                }
                this.p.addAll(xz.p);
            }
        }
        if ((xz.d & 128) == 128) {
            G00 g01 = xz.q;
            if ((this.e & Fcntl.S_ISUID) != 2048 || (g00 = this.q) == G00.h) {
                this.q = g01;
            } else {
                this.q = G00.a(g00).a(g01).e();
            }
            this.e |= Fcntl.S_ISUID;
        }
        if (!xz.r.isEmpty()) {
            if (this.r.isEmpty()) {
                this.r = xz.r;
                this.e &= -4097;
            } else {
                if ((this.e & 4096) != 4096) {
                    this.r = new ArrayList(this.r);
                    this.e |= 4096;
                }
                this.r.addAll(xz.r);
            }
        }
        if ((xz.d & Fcntl.S_IRUSR) == 256) {
            IZ iz2 = xz.s;
            if ((this.e & 8192) != 8192 || (iz = this.s) == IZ.f) {
                this.s = iz2;
            } else {
                this.s = new HZ().a(iz).a(iz2).e();
            }
            this.e |= 8192;
        }
        a((Lr) xz);
        this.b = this.b.a(xz.c);
        return this;
    }

    @Override // com.android.tools.r8.internal.AbstractC0574Ir
    public final L0 c() {
        XZ xzE = e();
        if (xzE.a()) {
            return xzE;
        }
        defpackage.bk.a();
        return null;
    }

    public final Object clone() {
        return new WZ().a(e());
    }

    @Override // com.android.tools.r8.internal.AbstractC0574Ir
    public final AbstractC0729Or d() {
        return XZ.v;
    }

    public final XZ e() {
        XZ xz = new XZ(this);
        int i = this.e;
        int i2 = (i & 1) != 1 ? 0 : 1;
        xz.e = this.f;
        if ((i & 2) == 2) {
            i2 |= 2;
        }
        xz.f = this.g;
        if ((i & 4) == 4) {
            i2 |= 4;
        }
        xz.g = this.h;
        if ((i & 8) == 8) {
            i2 |= 8;
        }
        xz.h = this.i;
        if ((i & 16) == 16) {
            i2 |= 16;
        }
        xz.i = this.j;
        if ((i & 32) == 32) {
            this.k = Collections.unmodifiableList(this.k);
            this.e &= -33;
        }
        xz.j = this.k;
        if ((i & 64) == 64) {
            i2 |= 32;
        }
        xz.k = this.l;
        if ((i & 128) == 128) {
            i2 |= 64;
        }
        xz.l = this.m;
        if ((this.e & Fcntl.S_IRUSR) == 256) {
            this.n = Collections.unmodifiableList(this.n);
            this.e &= -257;
        }
        xz.m = this.n;
        if ((this.e & 512) == 512) {
            this.o = Collections.unmodifiableList(this.o);
            this.e &= -513;
        }
        xz.n = this.o;
        if ((this.e & Fcntl.S_ISGID) == 1024) {
            this.p = Collections.unmodifiableList(this.p);
            this.e &= -1025;
        }
        xz.p = this.p;
        if ((i & Fcntl.S_ISUID) == 2048) {
            i2 |= 128;
        }
        xz.q = this.q;
        if ((this.e & 4096) == 4096) {
            this.r = Collections.unmodifiableList(this.r);
            this.e &= -4097;
        }
        xz.r = this.r;
        if ((i & 8192) == 8192) {
            i2 |= Fcntl.S_IRUSR;
        }
        xz.s = this.s;
        xz.d = i2;
        return xz;
    }

    /* JADX WARN: Code duplicated, block: B:14:0x001b  */
    @Override // com.android.tools.r8.internal.AbstractC0574Ir
    public final AbstractC0574Ir a(C0638Ld c0638Ld, C0389Bo c0389Bo) throws Throwable {
        XZ xz = null;
        try {
            try {
                XZ.w.getClass();
                a(new XZ(c0638Ld, c0389Bo));
                return this;
            } catch (QB e) {
                XZ xz2 = (XZ) e.b;
                try {
                    throw e;
                } catch (Throwable th) {
                    th = th;
                    xz = xz2;
                    if (xz != null) {
                        a(xz);
                    }
                    throw th;
                }
            }
        } catch (Throwable th2) {
            th = th2;
            if (xz != null) {
                a(xz);
            }
            throw th;
        }
    }
}
