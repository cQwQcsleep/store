package com.android.tools.r8.internal;

import com.sun.jna.platform.linux.Fcntl;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/* JADX INFO: renamed from: com.android.tools.r8.internal.e00, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C1364e00 extends AbstractC0600Jr {
    public int e;
    public int f = 518;
    public int g = 2054;
    public int h;
    public C2903w00 i;
    public int j;
    public List k;
    public C2903w00 l;
    public int m;
    public List n;
    public List o;
    public J00 p;
    public int q;
    public int r;
    public List s;

    public C1364e00() {
        C2903w00 c2903w00 = C2903w00.u;
        this.i = c2903w00;
        List list = Collections.EMPTY_LIST;
        this.k = list;
        this.l = c2903w00;
        this.n = list;
        this.o = list;
        this.p = J00.m;
        this.s = list;
    }

    @Override // com.android.tools.r8.internal.AbstractC0574Ir
    public final C1364e00 a(C1450f00 c1450f00) {
        J00 j00;
        C2903w00 c2903w00;
        C2903w00 c2903w01;
        if (c1450f00 == C1450f00.v) {
            return this;
        }
        int i = c1450f00.d;
        if ((i & 1) == 1) {
            int i2 = c1450f00.e;
            this.e = 1 | this.e;
            this.f = i2;
        }
        if ((i & 2) == 2) {
            int i3 = c1450f00.f;
            this.e = 2 | this.e;
            this.g = i3;
        }
        if ((i & 4) == 4) {
            int i4 = c1450f00.g;
            this.e = 4 | this.e;
            this.h = i4;
        }
        if ((i & 8) == 8) {
            C2903w00 c2903w02 = c1450f00.h;
            if ((this.e & 8) != 8 || (c2903w01 = this.i) == C2903w00.u) {
                this.i = c2903w02;
            } else {
                this.i = C2903w00.a(c2903w01).a(c2903w02).f();
            }
            this.e |= 8;
        }
        if ((c1450f00.d & 16) == 16) {
            int i5 = c1450f00.i;
            this.e = 16 | this.e;
            this.j = i5;
        }
        if (!c1450f00.j.isEmpty()) {
            if (this.k.isEmpty()) {
                this.k = c1450f00.j;
                this.e &= -33;
            } else {
                if ((this.e & 32) != 32) {
                    this.k = new ArrayList(this.k);
                    this.e |= 32;
                }
                this.k.addAll(c1450f00.j);
            }
        }
        if ((c1450f00.d & 32) == 32) {
            C2903w00 c2903w03 = c1450f00.k;
            if ((this.e & 64) != 64 || (c2903w00 = this.l) == C2903w00.u) {
                this.l = c2903w03;
            } else {
                this.l = C2903w00.a(c2903w00).a(c2903w03).f();
            }
            this.e |= 64;
        }
        if ((c1450f00.d & 64) == 64) {
            int i6 = c1450f00.l;
            this.e |= 128;
            this.m = i6;
        }
        if (!c1450f00.m.isEmpty()) {
            if (this.n.isEmpty()) {
                this.n = c1450f00.m;
                this.e &= -257;
            } else {
                if ((this.e & Fcntl.S_IRUSR) != 256) {
                    this.n = new ArrayList(this.n);
                    this.e |= Fcntl.S_IRUSR;
                }
                this.n.addAll(c1450f00.m);
            }
        }
        if (!c1450f00.n.isEmpty()) {
            if (this.o.isEmpty()) {
                this.o = c1450f00.n;
                this.e &= -513;
            } else {
                if ((this.e & 512) != 512) {
                    this.o = new ArrayList(this.o);
                    this.e |= 512;
                }
                this.o.addAll(c1450f00.n);
            }
        }
        if ((c1450f00.d & 128) == 128) {
            J00 j01 = c1450f00.p;
            if ((this.e & Fcntl.S_ISGID) != 1024 || (j00 = this.p) == J00.m) {
                this.p = j01;
            } else {
                this.p = new I00().a(j00).a(j01).e();
            }
            this.e |= Fcntl.S_ISGID;
        }
        int i7 = c1450f00.d;
        if ((i7 & Fcntl.S_IRUSR) == 256) {
            int i8 = c1450f00.q;
            this.e |= Fcntl.S_ISUID;
            this.q = i8;
        }
        if ((i7 & 512) == 512) {
            int i9 = c1450f00.r;
            this.e |= 4096;
            this.r = i9;
        }
        if (!c1450f00.s.isEmpty()) {
            if (this.s.isEmpty()) {
                this.s = c1450f00.s;
                this.e &= -8193;
            } else {
                if ((this.e & 8192) != 8192) {
                    this.s = new ArrayList(this.s);
                    this.e |= 8192;
                }
                this.s.addAll(c1450f00.s);
            }
        }
        a((Lr) c1450f00);
        this.b = this.b.a(c1450f00.c);
        return this;
    }

    @Override // com.android.tools.r8.internal.AbstractC0574Ir
    public final L0 c() {
        C1450f00 c1450f00E = e();
        if (c1450f00E.a()) {
            return c1450f00E;
        }
        defpackage.bk.a();
        return null;
    }

    public final Object clone() {
        return new C1364e00().a(e());
    }

    @Override // com.android.tools.r8.internal.AbstractC0574Ir
    public final AbstractC0729Or d() {
        return C1450f00.v;
    }

    public final C1450f00 e() {
        C1450f00 c1450f00 = new C1450f00(this);
        int i = this.e;
        int i2 = (i & 1) != 1 ? 0 : 1;
        c1450f00.e = this.f;
        if ((i & 2) == 2) {
            i2 |= 2;
        }
        c1450f00.f = this.g;
        if ((i & 4) == 4) {
            i2 |= 4;
        }
        c1450f00.g = this.h;
        if ((i & 8) == 8) {
            i2 |= 8;
        }
        c1450f00.h = this.i;
        if ((i & 16) == 16) {
            i2 |= 16;
        }
        c1450f00.i = this.j;
        if ((i & 32) == 32) {
            this.k = Collections.unmodifiableList(this.k);
            this.e &= -33;
        }
        c1450f00.j = this.k;
        if ((i & 64) == 64) {
            i2 |= 32;
        }
        c1450f00.k = this.l;
        if ((i & 128) == 128) {
            i2 |= 64;
        }
        c1450f00.l = this.m;
        if ((this.e & Fcntl.S_IRUSR) == 256) {
            this.n = Collections.unmodifiableList(this.n);
            this.e &= -257;
        }
        c1450f00.m = this.n;
        if ((this.e & 512) == 512) {
            this.o = Collections.unmodifiableList(this.o);
            this.e &= -513;
        }
        c1450f00.n = this.o;
        if ((i & Fcntl.S_ISGID) == 1024) {
            i2 |= 128;
        }
        c1450f00.p = this.p;
        if ((i & Fcntl.S_ISUID) == 2048) {
            i2 |= Fcntl.S_IRUSR;
        }
        c1450f00.q = this.q;
        if ((i & 4096) == 4096) {
            i2 |= 512;
        }
        c1450f00.r = this.r;
        if ((this.e & 8192) == 8192) {
            this.s = Collections.unmodifiableList(this.s);
            this.e &= -8193;
        }
        c1450f00.s = this.s;
        c1450f00.d = i2;
        return c1450f00;
    }

    /* JADX WARN: Code duplicated, block: B:14:0x001b  */
    @Override // com.android.tools.r8.internal.AbstractC0574Ir
    public final AbstractC0574Ir a(C0638Ld c0638Ld, C0389Bo c0389Bo) throws Throwable {
        C1450f00 c1450f00 = null;
        try {
            try {
                C1450f00.w.getClass();
                a(new C1450f00(c0638Ld, c0389Bo));
                return this;
            } catch (QB e) {
                C1450f00 c1450f01 = (C1450f00) e.b;
                try {
                    throw e;
                } catch (Throwable th) {
                    th = th;
                    c1450f00 = c1450f01;
                    if (c1450f00 != null) {
                        a(c1450f00);
                    }
                    throw th;
                }
            }
        } catch (Throwable th2) {
            th = th2;
            if (c1450f00 != null) {
                a(c1450f00);
            }
            throw th;
        }
    }
}
