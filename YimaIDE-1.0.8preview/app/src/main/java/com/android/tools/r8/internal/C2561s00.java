package com.android.tools.r8.internal;

/* JADX INFO: renamed from: com.android.tools.r8.internal.s00, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C2561s00 extends AbstractC0574Ir implements UN {
    public int c;
    public EnumC2646t00 d = EnumC2646t00.e;
    public C2903w00 e = C2903w00.u;
    public int f;

    @Override // com.android.tools.r8.internal.AbstractC0574Ir
    public final C2561s00 a(C2732u00 c2732u00) {
        C2903w00 c2903w00;
        if (c2732u00 == C2732u00.i) {
            return this;
        }
        if ((c2732u00.c & 1) == 1) {
            EnumC2646t00 enumC2646t00 = c2732u00.d;
            enumC2646t00.getClass();
            this.c = 1 | this.c;
            this.d = enumC2646t00;
        }
        if ((c2732u00.c & 2) == 2) {
            C2903w00 c2903w01 = c2732u00.e;
            if ((this.c & 2) != 2 || (c2903w00 = this.e) == C2903w00.u) {
                this.e = c2903w01;
            } else {
                this.e = C2903w00.a(c2903w00).a(c2903w01).f();
            }
            this.c |= 2;
        }
        if ((c2732u00.c & 4) == 4) {
            int i = c2732u00.f;
            this.c = 4 | this.c;
            this.f = i;
        }
        this.b = this.b.a(c2732u00.b);
        return this;
    }

    @Override // com.android.tools.r8.internal.AbstractC0574Ir
    public final L0 c() {
        C2732u00 c2732u00E = e();
        if (c2732u00E.a()) {
            return c2732u00E;
        }
        defpackage.bk.a();
        return null;
    }

    public final Object clone() {
        return new C2561s00().a(e());
    }

    public final C2732u00 e() {
        C2732u00 c2732u00 = new C2732u00(this);
        int i = this.c;
        int i2 = (i & 1) != 1 ? 0 : 1;
        c2732u00.d = this.d;
        if ((i & 2) == 2) {
            i2 |= 2;
        }
        c2732u00.e = this.e;
        if ((i & 4) == 4) {
            i2 |= 4;
        }
        c2732u00.f = this.f;
        c2732u00.c = i2;
        return c2732u00;
    }

    /* JADX WARN: Code duplicated, block: B:14:0x001b  */
    @Override // com.android.tools.r8.internal.AbstractC0574Ir
    public final AbstractC0574Ir a(C0638Ld c0638Ld, C0389Bo c0389Bo) throws Throwable {
        C2732u00 c2732u00 = null;
        try {
            try {
                C2732u00.j.getClass();
                a(new C2732u00(c0638Ld, c0389Bo));
                return this;
            } catch (QB e) {
                C2732u00 c2732u01 = (C2732u00) e.b;
                try {
                    throw e;
                } catch (Throwable th) {
                    th = th;
                    c2732u00 = c2732u01;
                    if (c2732u00 != null) {
                        a(c2732u00);
                    }
                    throw th;
                }
            }
        } catch (Throwable th2) {
            th = th2;
            if (c2732u00 != null) {
                a(c2732u00);
            }
            throw th;
        }
    }
}
