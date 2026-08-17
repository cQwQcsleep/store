package com.android.tools.r8.internal;

import com.sun.jna.platform.linux.Fcntl;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class AZ extends AbstractC0600Jr {
    public G00 A;
    public List B;
    public R00 C;
    public int e;
    public int f = 6;
    public int g;
    public int h;
    public List i;
    public List j;
    public List k;
    public List l;
    public List m;
    public List n;
    public List o;
    public List p;
    public List q;
    public List r;
    public List s;
    public List t;
    public int u;
    public C2903w00 v;
    public int w;
    public List x;
    public List y;
    public List z;

    public AZ() {
        List list = Collections.EMPTY_LIST;
        this.i = list;
        this.j = list;
        this.k = list;
        this.l = list;
        this.m = list;
        this.n = list;
        this.o = list;
        this.p = list;
        this.q = list;
        this.r = list;
        this.s = list;
        this.t = list;
        this.v = C2903w00.u;
        this.x = list;
        this.y = list;
        this.z = list;
        this.A = G00.h;
        this.B = list;
        this.C = R00.f;
    }

    @Override // com.android.tools.r8.internal.AbstractC0574Ir
    public final AZ a(CZ cz) {
        R00 r00;
        G00 g00;
        C2903w00 c2903w00;
        if (cz == CZ.K) {
            return this;
        }
        int i = cz.d;
        if ((i & 1) == 1) {
            int i2 = cz.e;
            this.e = 1 | this.e;
            this.f = i2;
        }
        if ((i & 2) == 2) {
            int i3 = cz.f;
            this.e = 2 | this.e;
            this.g = i3;
        }
        if ((i & 4) == 4) {
            int i4 = cz.g;
            this.e = 4 | this.e;
            this.h = i4;
        }
        if (!cz.h.isEmpty()) {
            if (this.i.isEmpty()) {
                this.i = cz.h;
                this.e &= -9;
            } else {
                if ((this.e & 8) != 8) {
                    this.i = new ArrayList(this.i);
                    this.e |= 8;
                }
                this.i.addAll(cz.h);
            }
        }
        if (!cz.i.isEmpty()) {
            if (this.j.isEmpty()) {
                this.j = cz.i;
                this.e &= -17;
            } else {
                if ((this.e & 16) != 16) {
                    this.j = new ArrayList(this.j);
                    this.e |= 16;
                }
                this.j.addAll(cz.i);
            }
        }
        if (!cz.j.isEmpty()) {
            if (this.k.isEmpty()) {
                this.k = cz.j;
                this.e &= -33;
            } else {
                if ((this.e & 32) != 32) {
                    this.k = new ArrayList(this.k);
                    this.e |= 32;
                }
                this.k.addAll(cz.j);
            }
        }
        if (!cz.l.isEmpty()) {
            if (this.l.isEmpty()) {
                this.l = cz.l;
                this.e &= -65;
            } else {
                if ((this.e & 64) != 64) {
                    this.l = new ArrayList(this.l);
                    this.e |= 64;
                }
                this.l.addAll(cz.l);
            }
        }
        if (!cz.n.isEmpty()) {
            if (this.m.isEmpty()) {
                this.m = cz.n;
                this.e &= -129;
            } else {
                if ((this.e & 128) != 128) {
                    this.m = new ArrayList(this.m);
                    this.e |= 128;
                }
                this.m.addAll(cz.n);
            }
        }
        if (!cz.o.isEmpty()) {
            if (this.n.isEmpty()) {
                this.n = cz.o;
                this.e &= -257;
            } else {
                if ((this.e & Fcntl.S_IRUSR) != 256) {
                    this.n = new ArrayList(this.n);
                    this.e |= Fcntl.S_IRUSR;
                }
                this.n.addAll(cz.o);
            }
        }
        if (!cz.q.isEmpty()) {
            if (this.o.isEmpty()) {
                this.o = cz.q;
                this.e &= -513;
            } else {
                if ((this.e & 512) != 512) {
                    this.o = new ArrayList(this.o);
                    this.e |= 512;
                }
                this.o.addAll(cz.q);
            }
        }
        if (!cz.r.isEmpty()) {
            if (this.p.isEmpty()) {
                this.p = cz.r;
                this.e &= -1025;
            } else {
                if ((this.e & Fcntl.S_ISGID) != 1024) {
                    this.p = new ArrayList(this.p);
                    this.e |= Fcntl.S_ISGID;
                }
                this.p.addAll(cz.r);
            }
        }
        if (!cz.s.isEmpty()) {
            if (this.q.isEmpty()) {
                this.q = cz.s;
                this.e &= -2049;
            } else {
                if ((this.e & Fcntl.S_ISUID) != 2048) {
                    this.q = new ArrayList(this.q);
                    this.e |= Fcntl.S_ISUID;
                }
                this.q.addAll(cz.s);
            }
        }
        if (!cz.t.isEmpty()) {
            if (this.r.isEmpty()) {
                this.r = cz.t;
                this.e &= -4097;
            } else {
                if ((this.e & 4096) != 4096) {
                    this.r = new ArrayList(this.r);
                    this.e |= 4096;
                }
                this.r.addAll(cz.t);
            }
        }
        if (!cz.u.isEmpty()) {
            if (this.s.isEmpty()) {
                this.s = cz.u;
                this.e &= -8193;
            } else {
                if ((this.e & 8192) != 8192) {
                    this.s = new ArrayList(this.s);
                    this.e |= 8192;
                }
                this.s.addAll(cz.u);
            }
        }
        if (!cz.v.isEmpty()) {
            if (this.t.isEmpty()) {
                this.t = cz.v;
                this.e &= -16385;
            } else {
                if ((this.e & 16384) != 16384) {
                    this.t = new ArrayList(this.t);
                    this.e |= 16384;
                }
                this.t.addAll(cz.v);
            }
        }
        int i5 = cz.d;
        if ((i5 & 8) == 8) {
            int i6 = cz.x;
            this.e |= 32768;
            this.u = i6;
        }
        if ((i5 & 16) == 16) {
            C2903w00 c2903w01 = cz.y;
            if ((this.e & 65536) != 65536 || (c2903w00 = this.v) == C2903w00.u) {
                this.v = c2903w01;
            } else {
                this.v = C2903w00.a(c2903w00).a(c2903w01).f();
            }
            this.e |= 65536;
        }
        if ((cz.d & 32) == 32) {
            int i7 = cz.z;
            this.e |= 131072;
            this.w = i7;
        }
        if (!cz.A.isEmpty()) {
            if (this.x.isEmpty()) {
                this.x = cz.A;
                this.e &= -262145;
            } else {
                if ((this.e & 262144) != 262144) {
                    this.x = new ArrayList(this.x);
                    this.e |= 262144;
                }
                this.x.addAll(cz.A);
            }
        }
        if (!cz.C.isEmpty()) {
            if (this.y.isEmpty()) {
                this.y = cz.C;
                this.e &= -524289;
            } else {
                if ((this.e & 524288) != 524288) {
                    this.y = new ArrayList(this.y);
                    this.e |= 524288;
                }
                this.y.addAll(cz.C);
            }
        }
        if (!cz.D.isEmpty()) {
            if (this.z.isEmpty()) {
                this.z = cz.D;
                this.e &= -1048577;
            } else {
                if ((this.e & 1048576) != 1048576) {
                    this.z = new ArrayList(this.z);
                    this.e |= 1048576;
                }
                this.z.addAll(cz.D);
            }
        }
        if ((cz.d & 64) == 64) {
            G00 g01 = cz.F;
            if ((this.e & 2097152) != 2097152 || (g00 = this.A) == G00.h) {
                this.A = g01;
            } else {
                this.A = G00.a(g00).a(g01).e();
            }
            this.e |= 2097152;
        }
        if (!cz.G.isEmpty()) {
            if (this.B.isEmpty()) {
                this.B = cz.G;
                this.e &= -4194305;
            } else {
                if ((this.e & 4194304) != 4194304) {
                    this.B = new ArrayList(this.B);
                    this.e |= 4194304;
                }
                this.B.addAll(cz.G);
            }
        }
        if ((cz.d & 128) == 128) {
            R00 r01 = cz.H;
            if ((this.e & 8388608) != 8388608 || (r00 = this.C) == R00.f) {
                this.C = r01;
            } else {
                this.C = new Q00().a(r00).a(r01).e();
            }
            this.e |= 8388608;
        }
        a((Lr) cz);
        this.b = this.b.a(cz.c);
        return this;
    }

    @Override // com.android.tools.r8.internal.AbstractC0574Ir
    public final L0 c() {
        CZ czE = e();
        if (czE.a()) {
            return czE;
        }
        defpackage.bk.a();
        return null;
    }

    public final Object clone() {
        return new AZ().a(e());
    }

    @Override // com.android.tools.r8.internal.AbstractC0574Ir
    public final AbstractC0729Or d() {
        return CZ.K;
    }

    public final CZ e() {
        CZ cz = new CZ(this);
        int i = this.e;
        int i2 = (i & 1) != 1 ? 0 : 1;
        cz.e = this.f;
        if ((i & 2) == 2) {
            i2 |= 2;
        }
        cz.f = this.g;
        if ((i & 4) == 4) {
            i2 |= 4;
        }
        cz.g = this.h;
        if ((i & 8) == 8) {
            this.i = Collections.unmodifiableList(this.i);
            this.e &= -9;
        }
        cz.h = this.i;
        if ((this.e & 16) == 16) {
            this.j = Collections.unmodifiableList(this.j);
            this.e &= -17;
        }
        cz.i = this.j;
        if ((this.e & 32) == 32) {
            this.k = Collections.unmodifiableList(this.k);
            this.e &= -33;
        }
        cz.j = this.k;
        if ((this.e & 64) == 64) {
            this.l = Collections.unmodifiableList(this.l);
            this.e &= -65;
        }
        cz.l = this.l;
        if ((this.e & 128) == 128) {
            this.m = Collections.unmodifiableList(this.m);
            this.e &= -129;
        }
        cz.n = this.m;
        if ((this.e & Fcntl.S_IRUSR) == 256) {
            this.n = Collections.unmodifiableList(this.n);
            this.e &= -257;
        }
        cz.o = this.n;
        if ((this.e & 512) == 512) {
            this.o = Collections.unmodifiableList(this.o);
            this.e &= -513;
        }
        cz.q = this.o;
        if ((this.e & Fcntl.S_ISGID) == 1024) {
            this.p = Collections.unmodifiableList(this.p);
            this.e &= -1025;
        }
        cz.r = this.p;
        if ((this.e & Fcntl.S_ISUID) == 2048) {
            this.q = Collections.unmodifiableList(this.q);
            this.e &= -2049;
        }
        cz.s = this.q;
        if ((this.e & 4096) == 4096) {
            this.r = Collections.unmodifiableList(this.r);
            this.e &= -4097;
        }
        cz.t = this.r;
        if ((this.e & 8192) == 8192) {
            this.s = Collections.unmodifiableList(this.s);
            this.e &= -8193;
        }
        cz.u = this.s;
        if ((this.e & 16384) == 16384) {
            this.t = Collections.unmodifiableList(this.t);
            this.e &= -16385;
        }
        cz.v = this.t;
        if ((i & 32768) == 32768) {
            i2 |= 8;
        }
        cz.x = this.u;
        if ((i & 65536) == 65536) {
            i2 |= 16;
        }
        cz.y = this.v;
        if ((i & 131072) == 131072) {
            i2 |= 32;
        }
        cz.z = this.w;
        if ((this.e & 262144) == 262144) {
            this.x = Collections.unmodifiableList(this.x);
            this.e &= -262145;
        }
        cz.A = this.x;
        if ((this.e & 524288) == 524288) {
            this.y = Collections.unmodifiableList(this.y);
            this.e &= -524289;
        }
        cz.C = this.y;
        if ((this.e & 1048576) == 1048576) {
            this.z = Collections.unmodifiableList(this.z);
            this.e &= -1048577;
        }
        cz.D = this.z;
        if ((i & 2097152) == 2097152) {
            i2 |= 64;
        }
        cz.F = this.A;
        if ((this.e & 4194304) == 4194304) {
            this.B = Collections.unmodifiableList(this.B);
            this.e &= -4194305;
        }
        cz.G = this.B;
        if ((i & 8388608) == 8388608) {
            i2 |= 128;
        }
        cz.H = this.C;
        cz.d = i2;
        return cz;
    }

    /* JADX WARN: Code duplicated, block: B:14:0x0019  */
    @Override // com.android.tools.r8.internal.AbstractC0574Ir
    public final AbstractC0574Ir a(C0638Ld c0638Ld, C0389Bo c0389Bo) throws Throwable {
        CZ cz = null;
        try {
            try {
                a((CZ) CZ.L.a(c0638Ld, c0389Bo));
                return this;
            } catch (QB e) {
                CZ cz2 = (CZ) e.b;
                try {
                    throw e;
                } catch (Throwable th) {
                    th = th;
                    cz = cz2;
                    if (cz != null) {
                        a(cz);
                    }
                    throw th;
                }
            }
        } catch (Throwable th2) {
            th = th2;
            if (cz != null) {
                a(cz);
            }
            throw th;
        }
    }
}
