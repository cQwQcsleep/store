package com.android.tools.r8.internal;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class UD extends AbstractC0574Ir implements UN {
    public int c;
    public int d;
    public int e;

    @Override // com.android.tools.r8.internal.AbstractC0574Ir
    public final UD a(VD vd) {
        if (vd == VD.h) {
            return this;
        }
        int i = vd.c;
        if ((i & 1) == 1) {
            int i2 = vd.d;
            this.c = 1 | this.c;
            this.d = i2;
        }
        if ((i & 2) == 2) {
            int i3 = vd.e;
            this.c = 2 | this.c;
            this.e = i3;
        }
        this.b = this.b.a(vd.b);
        return this;
    }

    @Override // com.android.tools.r8.internal.AbstractC0574Ir
    public final L0 c() {
        VD vdE = e();
        if (vdE.a()) {
            return vdE;
        }
        defpackage.bk.a();
        return null;
    }

    public final Object clone() {
        return new UD().a(e());
    }

    public final VD e() {
        VD vd = new VD(this);
        int i = this.c;
        int i2 = (i & 1) != 1 ? 0 : 1;
        vd.d = this.d;
        if ((i & 2) == 2) {
            i2 |= 2;
        }
        vd.e = this.e;
        vd.c = i2;
        return vd;
    }

    /* JADX WARN: Code duplicated, block: B:14:0x001b  */
    @Override // com.android.tools.r8.internal.AbstractC0574Ir
    public final AbstractC0574Ir a(C0638Ld c0638Ld, C0389Bo c0389Bo) throws Throwable {
        VD vd = null;
        try {
            try {
                VD.i.getClass();
                a(new VD(c0638Ld));
                return this;
            } catch (QB e) {
                VD vd2 = (VD) e.b;
                try {
                    throw e;
                } catch (Throwable th) {
                    th = th;
                    vd = vd2;
                    if (vd != null) {
                        a(vd);
                    }
                    throw th;
                }
            }
        } catch (Throwable th2) {
            th = th2;
            if (vd != null) {
                a(vd);
            }
            throw th;
        }
    }
}
