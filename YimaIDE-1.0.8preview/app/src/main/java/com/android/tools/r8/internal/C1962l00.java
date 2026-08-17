package com.android.tools.r8.internal;

import java.io.IOException;

/* JADX INFO: renamed from: com.android.tools.r8.internal.l00, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C1962l00 extends AbstractC0729Or {
    public static final C1962l00 i;
    public static final C1707i00 j = new C1707i00();
    public final T7 b;
    public int c;
    public int d;
    public int e;
    public EnumC1876k00 f;
    public byte g;
    public int h;

    static {
        C1962l00 c1962l00 = new C1962l00();
        i = c1962l00;
        c1962l00.d = -1;
        c1962l00.e = 0;
        c1962l00.f = EnumC1876k00.d;
    }

    public C1962l00(C0638Ld c0638Ld) {
        this.g = (byte) -1;
        this.h = -1;
        this.d = -1;
        boolean z = false;
        this.e = 0;
        this.f = EnumC1876k00.d;
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
                            } else if (i2 == 24) {
                                int iF = c0638Ld.f();
                                EnumC1876k00 enumC1876k00 = iF != 0 ? iF != 1 ? iF != 2 ? null : EnumC1876k00.e : EnumC1876k00.d : EnumC1876k00.c;
                                if (enumC1876k00 == null) {
                                    c0767Qd.g(i2);
                                    c0767Qd.g(iF);
                                } else {
                                    this.c |= 4;
                                    this.f = enumC1876k00;
                                }
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
        if ((this.c & 4) == 4) {
            c0767Qd.b(3, this.f.b);
        }
        c0767Qd.a(this.b);
    }

    @Override // com.android.tools.r8.internal.L0
    public final int c() {
        int i2 = this.h;
        if (i2 != -1) {
            return i2;
        }
        int iA = (this.c & 1) == 1 ? C0767Qd.a(1, this.d) : 0;
        if ((this.c & 2) == 2) {
            iA += C0767Qd.a(2, this.e);
        }
        if ((this.c & 4) == 4) {
            iA += C0767Qd.a(this.f.b) + C0767Qd.c(3);
        }
        int size = this.b.size() + iA;
        this.h = size;
        return size;
    }

    @Override // com.android.tools.r8.internal.L0
    public final AbstractC0574Ir d() {
        return new C1791j00();
    }

    @Override // com.android.tools.r8.internal.L0
    public final AbstractC0574Ir e() {
        return new C1791j00().a(this);
    }

    @Override // com.android.tools.r8.internal.UN
    public final boolean a() {
        byte b = this.g;
        if (b == 1) {
            return true;
        }
        if (b == 0) {
            return false;
        }
        if ((this.c & 2) == 2) {
            this.g = (byte) 1;
            return true;
        }
        this.g = (byte) 0;
        return false;
    }

    public C1962l00() {
        this.g = (byte) -1;
        this.h = -1;
        this.b = T7.b;
    }

    public C1962l00(AbstractC0574Ir abstractC0574Ir) {
        super(0);
        this.g = (byte) -1;
        this.h = -1;
        this.b = abstractC0574Ir.b;
    }
}
