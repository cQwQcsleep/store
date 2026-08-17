package com.android.tools.r8.internal;

import java.io.IOException;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class VD extends AbstractC0729Or {
    public static final VD h;
    public static final TD i = new TD();
    public final T7 b;
    public int c;
    public int d;
    public int e;
    public byte f;
    public int g;

    static {
        VD vd = new VD();
        h = vd;
        vd.d = 0;
        vd.e = 0;
    }

    public VD(C0638Ld c0638Ld) {
        this.f = (byte) -1;
        this.g = -1;
        boolean z = false;
        this.d = 0;
        this.e = 0;
        R7 r7 = new R7();
        C0767Qd c0767Qd = new C0767Qd(r7, new byte[1]);
        while (!z) {
            try {
                try {
                    try {
                        int i2 = c0638Ld.i();
                        if (i2 != 0) {
                            if (i2 == 8) {
                                this.c |= 1;
                                this.d = c0638Ld.f();
                            } else if (i2 == 16) {
                                this.c |= 2;
                                this.e = c0638Ld.f();
                            } else if (!c0638Ld.a(i2, c0767Qd)) {
                            }
                        }
                        z = true;
                    } catch (QB e) {
                        e.b = this;
                        throw e;
                    }
                } catch (IOException e2) {
                    QB qb = new QB(e2.getMessage());
                    qb.b = this;
                    throw qb;
                }
            } catch (Throwable th) {
                try {
                    c0767Qd.a();
                } catch (IOException unused) {
                } finally {
                    this.b = r7.c();
                }
                throw th;
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
            int i2 = this.d;
            c0767Qd.c(1, 0);
            c0767Qd.d(i2);
        }
        if ((this.c & 2) == 2) {
            int i3 = this.e;
            c0767Qd.c(2, 0);
            c0767Qd.d(i3);
        }
        c0767Qd.a(this.b);
    }

    @Override // com.android.tools.r8.internal.L0
    public final int c() {
        int i2 = this.g;
        if (i2 != -1) {
            return i2;
        }
        int iA = (this.c & 1) == 1 ? C0767Qd.a(1, this.d) : 0;
        if ((this.c & 2) == 2) {
            iA += C0767Qd.a(2, this.e);
        }
        int size = this.b.size() + iA;
        this.g = size;
        return size;
    }

    @Override // com.android.tools.r8.internal.L0
    public final AbstractC0574Ir d() {
        return new UD();
    }

    @Override // com.android.tools.r8.internal.L0
    public final AbstractC0574Ir e() {
        return a(this);
    }

    @Override // com.android.tools.r8.internal.UN
    public final boolean a() {
        byte b = this.f;
        if (b == 1) {
            return true;
        }
        if (b == 0) {
            return false;
        }
        this.f = (byte) 1;
        return true;
    }

    public static UD a(VD vd) {
        return new UD().a(vd);
    }

    public VD() {
        this.f = (byte) -1;
        this.g = -1;
        this.b = T7.b;
    }

    public VD(AbstractC0574Ir abstractC0574Ir) {
        super(0);
        this.f = (byte) -1;
        this.g = -1;
        this.b = abstractC0574Ir.b;
    }
}
