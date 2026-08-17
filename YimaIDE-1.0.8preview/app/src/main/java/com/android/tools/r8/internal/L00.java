package com.android.tools.r8.internal;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class L00 extends AbstractC0574Ir implements UN {
    public int c;
    public int d;
    public int e;
    public int g;
    public int h;
    public M00 f = M00.d;
    public N00 i = N00.c;

    @Override // com.android.tools.r8.internal.AbstractC0574Ir
    public final L00 a(O00 o00) {
        if (o00 == O00.l) {
            return this;
        }
        int i = o00.c;
        if ((i & 1) == 1) {
            int i2 = o00.d;
            this.c = 1 | this.c;
            this.d = i2;
        }
        if ((i & 2) == 2) {
            int i3 = o00.e;
            this.c = 2 | this.c;
            this.e = i3;
        }
        if ((i & 4) == 4) {
            M00 m00 = o00.f;
            m00.getClass();
            this.c = 4 | this.c;
            this.f = m00;
        }
        int i4 = o00.c;
        if ((i4 & 8) == 8) {
            int i5 = o00.g;
            this.c = 8 | this.c;
            this.g = i5;
        }
        if ((i4 & 16) == 16) {
            int i6 = o00.h;
            this.c = 16 | this.c;
            this.h = i6;
        }
        if ((i4 & 32) == 32) {
            N00 n00 = o00.i;
            n00.getClass();
            this.c = 32 | this.c;
            this.i = n00;
        }
        this.b = this.b.a(o00.b);
        return this;
    }

    @Override // com.android.tools.r8.internal.AbstractC0574Ir
    public final L0 c() {
        O00 o00E = e();
        if (o00E.a()) {
            return o00E;
        }
        defpackage.bk.a();
        return null;
    }

    public final Object clone() {
        return new L00().a(e());
    }

    public final O00 e() {
        O00 o00 = new O00(this);
        int i = this.c;
        int i2 = (i & 1) != 1 ? 0 : 1;
        o00.d = this.d;
        if ((i & 2) == 2) {
            i2 |= 2;
        }
        o00.e = this.e;
        if ((i & 4) == 4) {
            i2 |= 4;
        }
        o00.f = this.f;
        if ((i & 8) == 8) {
            i2 |= 8;
        }
        o00.g = this.g;
        if ((i & 16) == 16) {
            i2 |= 16;
        }
        o00.h = this.h;
        if ((i & 32) == 32) {
            i2 |= 32;
        }
        o00.i = this.i;
        o00.c = i2;
        return o00;
    }

    /* JADX WARN: Code duplicated, block: B:14:0x001b  */
    @Override // com.android.tools.r8.internal.AbstractC0574Ir
    public final AbstractC0574Ir a(C0638Ld c0638Ld, C0389Bo c0389Bo) throws Throwable {
        O00 o00 = null;
        try {
            try {
                O00.m.getClass();
                a(new O00(c0638Ld));
                return this;
            } catch (QB e) {
                O00 o01 = (O00) e.b;
                try {
                    throw e;
                } catch (Throwable th) {
                    th = th;
                    o00 = o01;
                    if (o00 != null) {
                        a(o00);
                    }
                    throw th;
                }
            }
        } catch (Throwable th2) {
            th = th2;
            if (o00 != null) {
                a(o00);
            }
            throw th;
        }
    }
}
