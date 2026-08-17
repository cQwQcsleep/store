package com.android.tools.r8.internal;

/* JADX INFO: renamed from: com.android.tools.r8.internal.rZ, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C2519rZ extends AbstractC0574Ir implements UN {
    public int c;
    public int d;
    public C2861vZ e = C2861vZ.q;

    @Override // com.android.tools.r8.internal.AbstractC0574Ir
    public final C2519rZ a(C2947wZ c2947wZ) {
        C2861vZ c2861vZ;
        if (c2947wZ == C2947wZ.h) {
            return this;
        }
        int i = c2947wZ.c;
        if ((i & 1) == 1) {
            int i2 = c2947wZ.d;
            this.c = 1 | this.c;
            this.d = i2;
        }
        if ((i & 2) == 2) {
            C2861vZ c2861vZ2 = c2947wZ.e;
            if ((this.c & 2) != 2 || (c2861vZ = this.e) == C2861vZ.q) {
                this.e = c2861vZ2;
            } else {
                this.e = new C2690tZ().a(c2861vZ).a(c2861vZ2).e();
            }
            this.c |= 2;
        }
        this.b = this.b.a(c2947wZ.b);
        return this;
    }

    @Override // com.android.tools.r8.internal.AbstractC0574Ir
    public final L0 c() {
        C2947wZ c2947wZE = e();
        if (c2947wZE.a()) {
            return c2947wZE;
        }
        defpackage.bk.a();
        return null;
    }

    public final Object clone() {
        return new C2519rZ().a(e());
    }

    public final C2947wZ e() {
        C2947wZ c2947wZ = new C2947wZ(this);
        int i = this.c;
        int i2 = (i & 1) != 1 ? 0 : 1;
        c2947wZ.d = this.d;
        if ((i & 2) == 2) {
            i2 |= 2;
        }
        c2947wZ.e = this.e;
        c2947wZ.c = i2;
        return c2947wZ;
    }

    /* JADX WARN: Code duplicated, block: B:14:0x001b  */
    @Override // com.android.tools.r8.internal.AbstractC0574Ir
    public final AbstractC0574Ir a(C0638Ld c0638Ld, C0389Bo c0389Bo) throws Throwable {
        C2947wZ c2947wZ = null;
        try {
            try {
                C2947wZ.i.getClass();
                a(new C2947wZ(c0638Ld, c0389Bo));
                return this;
            } catch (QB e) {
                C2947wZ c2947wZ2 = (C2947wZ) e.b;
                try {
                    throw e;
                } catch (Throwable th) {
                    th = th;
                    c2947wZ = c2947wZ2;
                    if (c2947wZ != null) {
                        a(c2947wZ);
                    }
                    throw th;
                }
            }
        } catch (Throwable th2) {
            th = th2;
            if (c2947wZ != null) {
                a(c2947wZ);
            }
            throw th;
        }
    }
}
