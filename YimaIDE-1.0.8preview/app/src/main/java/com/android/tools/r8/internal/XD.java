package com.android.tools.r8.internal;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class XD extends AbstractC0574Ir implements UN {
    public int c;
    public SD d = SD.h;
    public VD e;
    public VD f;
    public VD g;
    public VD h;

    public XD() {
        VD vd = VD.h;
        this.e = vd;
        this.f = vd;
        this.g = vd;
        this.h = vd;
    }

    @Override // com.android.tools.r8.internal.AbstractC0574Ir
    public final XD a(YD yd) {
        VD vd;
        VD vd2;
        VD vd3;
        VD vd4;
        SD sd;
        if (yd == YD.k) {
            return this;
        }
        if ((yd.c & 1) == 1) {
            SD sd2 = yd.d;
            if ((this.c & 1) != 1 || (sd = this.d) == SD.h) {
                this.d = sd2;
            } else {
                this.d = new RD().a(sd).a(sd2).e();
            }
            this.c |= 1;
        }
        if ((yd.c & 2) == 2) {
            VD vd5 = yd.e;
            if ((this.c & 2) != 2 || (vd4 = this.e) == VD.h) {
                this.e = vd5;
            } else {
                this.e = VD.a(vd4).a(vd5).e();
            }
            this.c |= 2;
        }
        if ((yd.c & 4) == 4) {
            VD vd6 = yd.f;
            if ((this.c & 4) != 4 || (vd3 = this.f) == VD.h) {
                this.f = vd6;
            } else {
                this.f = VD.a(vd3).a(vd6).e();
            }
            this.c |= 4;
        }
        if ((yd.c & 8) == 8) {
            VD vd7 = yd.g;
            if ((this.c & 8) != 8 || (vd2 = this.g) == VD.h) {
                this.g = vd7;
            } else {
                this.g = VD.a(vd2).a(vd7).e();
            }
            this.c |= 8;
        }
        if ((yd.c & 16) == 16) {
            VD vd8 = yd.h;
            if ((this.c & 16) != 16 || (vd = this.h) == VD.h) {
                this.h = vd8;
            } else {
                this.h = VD.a(vd).a(vd8).e();
            }
            this.c |= 16;
        }
        this.b = this.b.a(yd.b);
        return this;
    }

    @Override // com.android.tools.r8.internal.AbstractC0574Ir
    public final L0 c() {
        YD ydE = e();
        if (ydE.a()) {
            return ydE;
        }
        defpackage.bk.a();
        return null;
    }

    public final Object clone() {
        return new XD().a(e());
    }

    public final YD e() {
        YD yd = new YD(this);
        int i = this.c;
        int i2 = (i & 1) != 1 ? 0 : 1;
        yd.d = this.d;
        if ((i & 2) == 2) {
            i2 |= 2;
        }
        yd.e = this.e;
        if ((i & 4) == 4) {
            i2 |= 4;
        }
        yd.f = this.f;
        if ((i & 8) == 8) {
            i2 |= 8;
        }
        yd.g = this.g;
        if ((i & 16) == 16) {
            i2 |= 16;
        }
        yd.h = this.h;
        yd.c = i2;
        return yd;
    }

    /* JADX WARN: Code duplicated, block: B:14:0x001b  */
    @Override // com.android.tools.r8.internal.AbstractC0574Ir
    public final AbstractC0574Ir a(C0638Ld c0638Ld, C0389Bo c0389Bo) throws Throwable {
        YD yd = null;
        try {
            try {
                YD.l.getClass();
                a(new YD(c0638Ld, c0389Bo));
                return this;
            } catch (QB e) {
                YD yd2 = (YD) e.b;
                try {
                    throw e;
                } catch (Throwable th) {
                    th = th;
                    yd = yd2;
                    if (yd != null) {
                        a(yd);
                    }
                    throw th;
                }
            }
        } catch (Throwable th2) {
            th = th2;
            if (yd != null) {
                a(yd);
            }
            throw th;
        }
    }
}
