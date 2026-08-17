package com.android.tools.r8.internal;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class B00 extends AbstractC0600Jr {
    public int e;
    public int f;
    public int g;
    public boolean h;
    public C00 i = C00.e;
    public List j;
    public List k;

    public B00() {
        List list = Collections.EMPTY_LIST;
        this.j = list;
        this.k = list;
    }

    @Override // com.android.tools.r8.internal.AbstractC0574Ir
    public final B00 a(D00 d00) {
        if (d00 == D00.n) {
            return this;
        }
        int i = d00.d;
        if ((i & 1) == 1) {
            int i2 = d00.e;
            this.e = 1 | this.e;
            this.f = i2;
        }
        if ((i & 2) == 2) {
            int i3 = d00.f;
            this.e = 2 | this.e;
            this.g = i3;
        }
        if ((i & 4) == 4) {
            boolean z = d00.g;
            this.e = 4 | this.e;
            this.h = z;
        }
        if ((i & 8) == 8) {
            C00 c00 = d00.h;
            c00.getClass();
            this.e = 8 | this.e;
            this.i = c00;
        }
        if (!d00.i.isEmpty()) {
            if (this.j.isEmpty()) {
                this.j = d00.i;
                this.e &= -17;
            } else {
                if ((this.e & 16) != 16) {
                    this.j = new ArrayList(this.j);
                    this.e |= 16;
                }
                this.j.addAll(d00.i);
            }
        }
        if (!d00.j.isEmpty()) {
            if (this.k.isEmpty()) {
                this.k = d00.j;
                this.e &= -33;
            } else {
                if ((this.e & 32) != 32) {
                    this.k = new ArrayList(this.k);
                    this.e |= 32;
                }
                this.k.addAll(d00.j);
            }
        }
        a((Lr) d00);
        this.b = this.b.a(d00.c);
        return this;
    }

    @Override // com.android.tools.r8.internal.AbstractC0574Ir
    public final L0 c() {
        D00 d00E = e();
        if (d00E.a()) {
            return d00E;
        }
        defpackage.bk.a();
        return null;
    }

    public final Object clone() {
        return new B00().a(e());
    }

    @Override // com.android.tools.r8.internal.AbstractC0574Ir
    public final AbstractC0729Or d() {
        return D00.n;
    }

    public final D00 e() {
        D00 d00 = new D00(this);
        int i = this.e;
        int i2 = (i & 1) != 1 ? 0 : 1;
        d00.e = this.f;
        if ((i & 2) == 2) {
            i2 |= 2;
        }
        d00.f = this.g;
        if ((i & 4) == 4) {
            i2 |= 4;
        }
        d00.g = this.h;
        if ((i & 8) == 8) {
            i2 |= 8;
        }
        d00.h = this.i;
        if ((i & 16) == 16) {
            this.j = Collections.unmodifiableList(this.j);
            this.e &= -17;
        }
        d00.i = this.j;
        if ((this.e & 32) == 32) {
            this.k = Collections.unmodifiableList(this.k);
            this.e &= -33;
        }
        d00.j = this.k;
        d00.d = i2;
        return d00;
    }

    /* JADX WARN: Code duplicated, block: B:14:0x001b  */
    @Override // com.android.tools.r8.internal.AbstractC0574Ir
    public final AbstractC0574Ir a(C0638Ld c0638Ld, C0389Bo c0389Bo) throws Throwable {
        D00 d00 = null;
        try {
            try {
                D00.o.getClass();
                a(new D00(c0638Ld, c0389Bo));
                return this;
            } catch (QB e) {
                D00 d01 = (D00) e.b;
                try {
                    throw e;
                } catch (Throwable th) {
                    th = th;
                    d00 = d01;
                    if (d00 != null) {
                        a(d00);
                    }
                    throw th;
                }
            }
        } catch (Throwable th2) {
            th = th2;
            if (d00 != null) {
                a(d00);
            }
            throw th;
        }
    }
}
