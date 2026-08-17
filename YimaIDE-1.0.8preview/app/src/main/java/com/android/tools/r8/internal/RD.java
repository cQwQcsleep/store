package com.android.tools.r8.internal;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class RD extends AbstractC0574Ir implements UN {
    public int c;
    public int d;
    public int e;

    @Override // com.android.tools.r8.internal.AbstractC0574Ir
    public final RD a(SD sd) {
        if (sd == SD.h) {
            return this;
        }
        int i = sd.c;
        if ((i & 1) == 1) {
            int i2 = sd.d;
            this.c = 1 | this.c;
            this.d = i2;
        }
        if ((i & 2) == 2) {
            int i3 = sd.e;
            this.c = 2 | this.c;
            this.e = i3;
        }
        this.b = this.b.a(sd.b);
        return this;
    }

    @Override // com.android.tools.r8.internal.AbstractC0574Ir
    public final L0 c() {
        SD sdE = e();
        if (sdE.a()) {
            return sdE;
        }
        defpackage.bk.a();
        return null;
    }

    public final Object clone() {
        return new RD().a(e());
    }

    public final SD e() {
        SD sd = new SD(this);
        int i = this.c;
        int i2 = (i & 1) != 1 ? 0 : 1;
        sd.d = this.d;
        if ((i & 2) == 2) {
            i2 |= 2;
        }
        sd.e = this.e;
        sd.c = i2;
        return sd;
    }

    /* JADX WARN: Code duplicated, block: B:14:0x001b  */
    @Override // com.android.tools.r8.internal.AbstractC0574Ir
    public final AbstractC0574Ir a(C0638Ld c0638Ld, C0389Bo c0389Bo) throws Throwable {
        SD sd = null;
        try {
            try {
                SD.i.getClass();
                a(new SD(c0638Ld));
                return this;
            } catch (QB e) {
                SD sd2 = (SD) e.b;
                try {
                    throw e;
                } catch (Throwable th) {
                    th = th;
                    sd = sd2;
                    if (sd != null) {
                        a(sd);
                    }
                    throw th;
                }
            }
        } catch (Throwable th2) {
            th = th2;
            if (sd != null) {
                a(sd);
            }
            throw th;
        }
    }
}
