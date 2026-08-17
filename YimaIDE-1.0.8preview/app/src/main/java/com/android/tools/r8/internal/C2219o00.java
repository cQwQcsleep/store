package com.android.tools.r8.internal;

/* JADX INFO: renamed from: com.android.tools.r8.internal.o00, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C2219o00 extends AbstractC0574Ir implements UN {
    public int c;
    public InterfaceC3186zJ d = C3017xJ.c;

    @Override // com.android.tools.r8.internal.AbstractC0574Ir
    public final C2219o00 a(C2305p00 c2305p00) {
        if (c2305p00 == C2305p00.f) {
            return this;
        }
        if (!c2305p00.c.isEmpty()) {
            if (this.d.isEmpty()) {
                this.d = c2305p00.c;
                this.c &= -2;
            } else {
                if ((this.c & 1) != 1) {
                    this.d = new C3017xJ(this.d);
                    this.c |= 1;
                }
                this.d.addAll(c2305p00.c);
            }
        }
        this.b = this.b.a(c2305p00.b);
        return this;
    }

    @Override // com.android.tools.r8.internal.AbstractC0574Ir
    public final L0 c() {
        C2305p00 c2305p00E = e();
        if (c2305p00E.a()) {
            return c2305p00E;
        }
        defpackage.bk.a();
        return null;
    }

    public final Object clone() {
        return new C2219o00().a(e());
    }

    public final C2305p00 e() {
        C2305p00 c2305p00 = new C2305p00(this);
        if ((this.c & 1) == 1) {
            this.d = this.d.f();
            this.c &= -2;
        }
        c2305p00.c = this.d;
        return c2305p00;
    }

    /* JADX WARN: Code duplicated, block: B:14:0x001b  */
    @Override // com.android.tools.r8.internal.AbstractC0574Ir
    public final AbstractC0574Ir a(C0638Ld c0638Ld, C0389Bo c0389Bo) throws Throwable {
        C2305p00 c2305p00 = null;
        try {
            try {
                C2305p00.g.getClass();
                a(new C2305p00(c0638Ld));
                return this;
            } catch (QB e) {
                C2305p00 c2305p01 = (C2305p00) e.b;
                try {
                    throw e;
                } catch (Throwable th) {
                    th = th;
                    c2305p00 = c2305p01;
                    if (c2305p00 != null) {
                        a(c2305p00);
                    }
                    throw th;
                }
            }
        } catch (Throwable th2) {
            th = th2;
            if (c2305p00 != null) {
                a(c2305p00);
            }
            throw th;
        }
    }
}
