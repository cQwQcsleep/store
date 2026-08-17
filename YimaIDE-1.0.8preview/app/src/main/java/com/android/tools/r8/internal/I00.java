package com.android.tools.r8.internal;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class I00 extends AbstractC0600Jr {
    public int e;
    public int f;
    public int g;
    public C2903w00 h;
    public int i;
    public C2903w00 j;
    public int k;

    public I00() {
        C2903w00 c2903w00 = C2903w00.u;
        this.h = c2903w00;
        this.j = c2903w00;
    }

    @Override // com.android.tools.r8.internal.AbstractC0574Ir
    public final I00 a(J00 j00) {
        C2903w00 c2903w00;
        C2903w00 c2903w01;
        if (j00 == J00.m) {
            return this;
        }
        int i = j00.d;
        if ((i & 1) == 1) {
            int i2 = j00.e;
            this.e = 1 | this.e;
            this.f = i2;
        }
        if ((i & 2) == 2) {
            int i3 = j00.f;
            this.e = 2 | this.e;
            this.g = i3;
        }
        if ((i & 4) == 4) {
            C2903w00 c2903w02 = j00.g;
            if ((this.e & 4) != 4 || (c2903w01 = this.h) == C2903w00.u) {
                this.h = c2903w02;
            } else {
                this.h = C2903w00.a(c2903w01).a(c2903w02).f();
            }
            this.e |= 4;
        }
        int i4 = j00.d;
        if ((i4 & 8) == 8) {
            int i5 = j00.h;
            this.e = 8 | this.e;
            this.i = i5;
        }
        if ((i4 & 16) == 16) {
            C2903w00 c2903w03 = j00.i;
            if ((this.e & 16) != 16 || (c2903w00 = this.j) == C2903w00.u) {
                this.j = c2903w03;
            } else {
                this.j = C2903w00.a(c2903w00).a(c2903w03).f();
            }
            this.e |= 16;
        }
        if ((j00.d & 32) == 32) {
            int i6 = j00.j;
            this.e = 32 | this.e;
            this.k = i6;
        }
        a((Lr) j00);
        this.b = this.b.a(j00.c);
        return this;
    }

    @Override // com.android.tools.r8.internal.AbstractC0574Ir
    public final L0 c() {
        J00 j00E = e();
        if (j00E.a()) {
            return j00E;
        }
        defpackage.bk.a();
        return null;
    }

    public final Object clone() {
        return new I00().a(e());
    }

    @Override // com.android.tools.r8.internal.AbstractC0574Ir
    public final AbstractC0729Or d() {
        return J00.m;
    }

    public final J00 e() {
        J00 j00 = new J00(this);
        int i = this.e;
        int i2 = (i & 1) != 1 ? 0 : 1;
        j00.e = this.f;
        if ((i & 2) == 2) {
            i2 |= 2;
        }
        j00.f = this.g;
        if ((i & 4) == 4) {
            i2 |= 4;
        }
        j00.g = this.h;
        if ((i & 8) == 8) {
            i2 |= 8;
        }
        j00.h = this.i;
        if ((i & 16) == 16) {
            i2 |= 16;
        }
        j00.i = this.j;
        if ((i & 32) == 32) {
            i2 |= 32;
        }
        j00.j = this.k;
        j00.d = i2;
        return j00;
    }

    /* JADX WARN: Code duplicated, block: B:14:0x001b  */
    @Override // com.android.tools.r8.internal.AbstractC0574Ir
    public final AbstractC0574Ir a(C0638Ld c0638Ld, C0389Bo c0389Bo) throws Throwable {
        J00 j00 = null;
        try {
            try {
                J00.n.getClass();
                a(new J00(c0638Ld, c0389Bo));
                return this;
            } catch (QB e) {
                J00 j01 = (J00) e.b;
                try {
                    throw e;
                } catch (Throwable th) {
                    th = th;
                    j00 = j01;
                    if (j00 != null) {
                        a(j00);
                    }
                    throw th;
                }
            }
        } catch (Throwable th2) {
            th = th2;
            if (j00 != null) {
                a(j00);
            }
            throw th;
        }
    }
}
