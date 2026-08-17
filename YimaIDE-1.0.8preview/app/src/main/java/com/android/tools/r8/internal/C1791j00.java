package com.android.tools.r8.internal;

/* JADX INFO: renamed from: com.android.tools.r8.internal.j00, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C1791j00 extends AbstractC0574Ir implements UN {
    public int c;
    public int e;
    public int d = -1;
    public EnumC1876k00 f = EnumC1876k00.d;

    @Override // com.android.tools.r8.internal.AbstractC0574Ir
    public final C1791j00 a(C1962l00 c1962l00) {
        if (c1962l00 == C1962l00.i) {
            return this;
        }
        int i = c1962l00.c;
        if ((i & 1) == 1) {
            int i2 = c1962l00.d;
            this.c = 1 | this.c;
            this.d = i2;
        }
        if ((i & 2) == 2) {
            int i3 = c1962l00.e;
            this.c = 2 | this.c;
            this.e = i3;
        }
        if ((i & 4) == 4) {
            EnumC1876k00 enumC1876k00 = c1962l00.f;
            enumC1876k00.getClass();
            this.c = 4 | this.c;
            this.f = enumC1876k00;
        }
        this.b = this.b.a(c1962l00.b);
        return this;
    }

    @Override // com.android.tools.r8.internal.AbstractC0574Ir
    public final L0 c() {
        C1962l00 c1962l00E = e();
        if (c1962l00E.a()) {
            return c1962l00E;
        }
        defpackage.bk.a();
        return null;
    }

    public final Object clone() {
        return new C1791j00().a(e());
    }

    public final C1962l00 e() {
        C1962l00 c1962l00 = new C1962l00(this);
        int i = this.c;
        int i2 = (i & 1) != 1 ? 0 : 1;
        c1962l00.d = this.d;
        if ((i & 2) == 2) {
            i2 |= 2;
        }
        c1962l00.e = this.e;
        if ((i & 4) == 4) {
            i2 |= 4;
        }
        c1962l00.f = this.f;
        c1962l00.c = i2;
        return c1962l00;
    }

    /* JADX WARN: Code duplicated, block: B:14:0x001b  */
    @Override // com.android.tools.r8.internal.AbstractC0574Ir
    public final AbstractC0574Ir a(C0638Ld c0638Ld, C0389Bo c0389Bo) throws Throwable {
        C1962l00 c1962l00 = null;
        try {
            try {
                C1962l00.j.getClass();
                a(new C1962l00(c0638Ld));
                return this;
            } catch (QB e) {
                C1962l00 c1962l01 = (C1962l00) e.b;
                try {
                    throw e;
                } catch (Throwable th) {
                    th = th;
                    c1962l00 = c1962l01;
                    if (c1962l00 != null) {
                        a(c1962l00);
                    }
                    throw th;
                }
            }
        } catch (Throwable th2) {
            th = th2;
            if (c1962l00 != null) {
                a(c1962l00);
            }
            throw th;
        }
    }
}
