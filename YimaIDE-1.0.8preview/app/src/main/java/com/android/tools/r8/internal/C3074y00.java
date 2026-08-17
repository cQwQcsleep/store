package com.android.tools.r8.internal;

import com.sun.jna.platform.linux.Fcntl;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/* JADX INFO: renamed from: com.android.tools.r8.internal.y00, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C3074y00 extends AbstractC0600Jr {
    public int e;
    public int f = 6;
    public int g;
    public List h;
    public C2903w00 i;
    public int j;
    public C2903w00 k;
    public int l;
    public List m;
    public List n;

    public C3074y00() {
        List list = Collections.EMPTY_LIST;
        this.h = list;
        C2903w00 c2903w00 = C2903w00.u;
        this.i = c2903w00;
        this.k = c2903w00;
        this.m = list;
        this.n = list;
    }

    @Override // com.android.tools.r8.internal.AbstractC0574Ir
    public final C3074y00 a(C3158z00 c3158z00) {
        C2903w00 c2903w00;
        C2903w00 c2903w01;
        if (c3158z00 == C3158z00.p) {
            return this;
        }
        int i = c3158z00.d;
        if ((i & 1) == 1) {
            int i2 = c3158z00.e;
            this.e = 1 | this.e;
            this.f = i2;
        }
        if ((i & 2) == 2) {
            int i3 = c3158z00.f;
            this.e = 2 | this.e;
            this.g = i3;
        }
        if (!c3158z00.g.isEmpty()) {
            if (this.h.isEmpty()) {
                this.h = c3158z00.g;
                this.e &= -5;
            } else {
                if ((this.e & 4) != 4) {
                    this.h = new ArrayList(this.h);
                    this.e |= 4;
                }
                this.h.addAll(c3158z00.g);
            }
        }
        if ((c3158z00.d & 4) == 4) {
            C2903w00 c2903w02 = c3158z00.h;
            if ((this.e & 8) != 8 || (c2903w01 = this.i) == C2903w00.u) {
                this.i = c2903w02;
            } else {
                this.i = C2903w00.a(c2903w01).a(c2903w02).f();
            }
            this.e |= 8;
        }
        int i4 = c3158z00.d;
        if ((i4 & 8) == 8) {
            int i5 = c3158z00.i;
            this.e |= 16;
            this.j = i5;
        }
        if ((i4 & 16) == 16) {
            C2903w00 c2903w03 = c3158z00.j;
            if ((this.e & 32) != 32 || (c2903w00 = this.k) == C2903w00.u) {
                this.k = c2903w03;
            } else {
                this.k = C2903w00.a(c2903w00).a(c2903w03).f();
            }
            this.e |= 32;
        }
        if ((c3158z00.d & 32) == 32) {
            int i6 = c3158z00.k;
            this.e |= 64;
            this.l = i6;
        }
        if (!c3158z00.l.isEmpty()) {
            if (this.m.isEmpty()) {
                this.m = c3158z00.l;
                this.e &= -129;
            } else {
                if ((this.e & 128) != 128) {
                    this.m = new ArrayList(this.m);
                    this.e |= 128;
                }
                this.m.addAll(c3158z00.l);
            }
        }
        if (!c3158z00.m.isEmpty()) {
            if (this.n.isEmpty()) {
                this.n = c3158z00.m;
                this.e &= -257;
            } else {
                if ((this.e & Fcntl.S_IRUSR) != 256) {
                    this.n = new ArrayList(this.n);
                    this.e |= Fcntl.S_IRUSR;
                }
                this.n.addAll(c3158z00.m);
            }
        }
        a((Lr) c3158z00);
        this.b = this.b.a(c3158z00.c);
        return this;
    }

    @Override // com.android.tools.r8.internal.AbstractC0574Ir
    public final L0 c() {
        C3158z00 c3158z00E = e();
        if (c3158z00E.a()) {
            return c3158z00E;
        }
        defpackage.bk.a();
        return null;
    }

    public final Object clone() {
        return new C3074y00().a(e());
    }

    @Override // com.android.tools.r8.internal.AbstractC0574Ir
    public final AbstractC0729Or d() {
        return C3158z00.p;
    }

    public final C3158z00 e() {
        C3158z00 c3158z00 = new C3158z00(this);
        int i = this.e;
        int i2 = (i & 1) != 1 ? 0 : 1;
        c3158z00.e = this.f;
        if ((i & 2) == 2) {
            i2 |= 2;
        }
        c3158z00.f = this.g;
        if ((i & 4) == 4) {
            this.h = Collections.unmodifiableList(this.h);
            this.e &= -5;
        }
        c3158z00.g = this.h;
        if ((i & 8) == 8) {
            i2 |= 4;
        }
        c3158z00.h = this.i;
        if ((i & 16) == 16) {
            i2 |= 8;
        }
        c3158z00.i = this.j;
        if ((i & 32) == 32) {
            i2 |= 16;
        }
        c3158z00.j = this.k;
        if ((i & 64) == 64) {
            i2 |= 32;
        }
        c3158z00.k = this.l;
        if ((this.e & 128) == 128) {
            this.m = Collections.unmodifiableList(this.m);
            this.e &= -129;
        }
        c3158z00.l = this.m;
        if ((this.e & Fcntl.S_IRUSR) == 256) {
            this.n = Collections.unmodifiableList(this.n);
            this.e &= -257;
        }
        c3158z00.m = this.n;
        c3158z00.d = i2;
        return c3158z00;
    }

    /* JADX WARN: Code duplicated, block: B:14:0x001b  */
    @Override // com.android.tools.r8.internal.AbstractC0574Ir
    public final AbstractC0574Ir a(C0638Ld c0638Ld, C0389Bo c0389Bo) throws Throwable {
        C3158z00 c3158z00 = null;
        try {
            try {
                C3158z00.q.getClass();
                a(new C3158z00(c0638Ld, c0389Bo));
                return this;
            } catch (QB e) {
                C3158z00 c3158z01 = (C3158z00) e.b;
                try {
                    throw e;
                } catch (Throwable th) {
                    th = th;
                    c3158z00 = c3158z01;
                    if (c3158z00 != null) {
                        a(c3158z00);
                    }
                    throw th;
                }
            }
        } catch (Throwable th2) {
            th = th2;
            if (c3158z00 != null) {
                a(c3158z00);
            }
            throw th;
        }
    }
}
