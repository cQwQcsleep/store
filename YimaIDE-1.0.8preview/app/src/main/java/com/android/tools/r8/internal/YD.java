package com.android.tools.r8.internal;

import java.io.IOException;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class YD extends AbstractC0729Or {
    public static final YD k;
    public static final WD l = new WD();
    public final T7 b;
    public int c;
    public SD d;
    public VD e;
    public VD f;
    public VD g;
    public VD h;
    public byte i;
    public int j;

    static {
        YD yd = new YD();
        k = yd;
        yd.d = SD.h;
        VD vd = VD.h;
        yd.e = vd;
        yd.f = vd;
        yd.g = vd;
        yd.h = vd;
    }

    public YD(C0638Ld c0638Ld, C0389Bo c0389Bo) {
        this.i = (byte) -1;
        this.j = -1;
        this.d = SD.h;
        VD vd = VD.h;
        this.e = vd;
        this.f = vd;
        this.g = vd;
        this.h = vd;
        R7 r7 = new R7();
        C0767Qd c0767Qd = new C0767Qd(r7, new byte[1]);
        boolean z = false;
        while (!z) {
            try {
                try {
                    int i = c0638Ld.i();
                    if (i != 0) {
                        UD udA = null;
                        RD rdA = null;
                        UD udA2 = null;
                        UD udA3 = null;
                        UD udA4 = null;
                        if (i == 10) {
                            if ((this.c & 1) == 1) {
                                SD sd = this.d;
                                sd.getClass();
                                rdA = new RD().a(sd);
                            }
                            SD sd2 = (SD) c0638Ld.a(SD.i, c0389Bo);
                            this.d = sd2;
                            if (rdA != null) {
                                rdA.a(sd2);
                                this.d = rdA.e();
                            }
                            this.c |= 1;
                        } else if (i == 18) {
                            if ((this.c & 2) == 2) {
                                VD vd2 = this.e;
                                vd2.getClass();
                                udA2 = VD.a(vd2);
                            }
                            VD vd3 = (VD) c0638Ld.a(VD.i, c0389Bo);
                            this.e = vd3;
                            if (udA2 != null) {
                                udA2.a(vd3);
                                this.e = udA2.e();
                            }
                            this.c |= 2;
                        } else if (i == 26) {
                            if ((this.c & 4) == 4) {
                                VD vd4 = this.f;
                                vd4.getClass();
                                udA3 = VD.a(vd4);
                            }
                            VD vd5 = (VD) c0638Ld.a(VD.i, c0389Bo);
                            this.f = vd5;
                            if (udA3 != null) {
                                udA3.a(vd5);
                                this.f = udA3.e();
                            }
                            this.c |= 4;
                        } else if (i == 34) {
                            if ((this.c & 8) == 8) {
                                VD vd6 = this.g;
                                vd6.getClass();
                                udA4 = VD.a(vd6);
                            }
                            VD vd7 = (VD) c0638Ld.a(VD.i, c0389Bo);
                            this.g = vd7;
                            if (udA4 != null) {
                                udA4.a(vd7);
                                this.g = udA4.e();
                            }
                            this.c |= 8;
                        } else if (i == 42) {
                            if ((this.c & 16) == 16) {
                                VD vd8 = this.h;
                                vd8.getClass();
                                udA = VD.a(vd8);
                            }
                            VD vd9 = (VD) c0638Ld.a(VD.i, c0389Bo);
                            this.h = vd9;
                            if (udA != null) {
                                udA.a(vd9);
                                this.h = udA.e();
                            }
                            this.c |= 16;
                        } else if (!c0638Ld.a(i, c0767Qd)) {
                        }
                    }
                    z = true;
                } catch (Throwable th) {
                    try {
                        c0767Qd.a();
                    } catch (IOException unused) {
                    } finally {
                        this.b = r7.c();
                    }
                    throw th;
                }
            } catch (QB e) {
                e.b = this;
                throw e;
            } catch (IOException e2) {
                QB qb = new QB(e2.getMessage());
                qb.b = this;
                throw qb;
            }
        }
        try {
            c0767Qd.a();
        } catch (IOException unused2) {
        } finally {
            this.b = r7.c();
        }
    }

    @Override // com.android.tools.r8.internal.L0
    public final void a(C0767Qd c0767Qd) throws IOException {
        c();
        if ((this.c & 1) == 1) {
            c0767Qd.b(1, this.d);
        }
        if ((this.c & 2) == 2) {
            c0767Qd.b(2, this.e);
        }
        if ((this.c & 4) == 4) {
            c0767Qd.b(3, this.f);
        }
        if ((this.c & 8) == 8) {
            c0767Qd.b(4, this.g);
        }
        if ((this.c & 16) == 16) {
            c0767Qd.b(5, this.h);
        }
        c0767Qd.a(this.b);
    }

    @Override // com.android.tools.r8.internal.L0
    public final int c() {
        int i = this.j;
        if (i != -1) {
            return i;
        }
        int iA = (this.c & 1) == 1 ? C0767Qd.a(1, this.d) : 0;
        if ((this.c & 2) == 2) {
            iA += C0767Qd.a(2, this.e);
        }
        if ((this.c & 4) == 4) {
            iA += C0767Qd.a(3, this.f);
        }
        if ((this.c & 8) == 8) {
            iA += C0767Qd.a(4, this.g);
        }
        if ((this.c & 16) == 16) {
            iA += C0767Qd.a(5, this.h);
        }
        int size = this.b.size() + iA;
        this.j = size;
        return size;
    }

    @Override // com.android.tools.r8.internal.L0
    public final AbstractC0574Ir d() {
        return new XD();
    }

    @Override // com.android.tools.r8.internal.L0
    public final AbstractC0574Ir e() {
        return new XD().a(this);
    }

    @Override // com.android.tools.r8.internal.UN
    public final boolean a() {
        byte b = this.i;
        if (b == 1) {
            return true;
        }
        if (b == 0) {
            return false;
        }
        this.i = (byte) 1;
        return true;
    }

    public YD() {
        this.i = (byte) -1;
        this.j = -1;
        this.b = T7.b;
    }

    public YD(AbstractC0574Ir abstractC0574Ir) {
        super(0);
        this.i = (byte) -1;
        this.j = -1;
        this.b = abstractC0574Ir.b;
    }
}
