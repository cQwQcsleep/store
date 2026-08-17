package com.android.tools.r8.internal;

import java.io.IOException;

/* JADX INFO: renamed from: com.android.tools.r8.internal.wZ, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C2947wZ extends AbstractC0729Or {
    public static final C2947wZ h;
    public static final C2434qZ i = new C2434qZ();
    public final T7 b;
    public int c;
    public int d;
    public C2861vZ e;
    public byte f;
    public int g;

    static {
        C2947wZ c2947wZ = new C2947wZ();
        h = c2947wZ;
        c2947wZ.d = 0;
        c2947wZ.e = C2861vZ.q;
    }

    public C2947wZ(C0638Ld c0638Ld, C0389Bo c0389Bo) {
        C2690tZ c2690tZA;
        this.f = (byte) -1;
        this.g = -1;
        boolean z = false;
        this.d = 0;
        this.e = C2861vZ.q;
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
                            } else if (i2 == 18) {
                                if ((this.c & 2) == 2) {
                                    C2861vZ c2861vZ = this.e;
                                    c2861vZ.getClass();
                                    c2690tZA = new C2690tZ().a(c2861vZ);
                                } else {
                                    c2690tZA = null;
                                }
                                C2861vZ c2861vZ2 = (C2861vZ) c0638Ld.a(C2861vZ.r, c0389Bo);
                                this.e = c2861vZ2;
                                if (c2690tZA != null) {
                                    c2690tZA.a(c2861vZ2);
                                    this.e = c2690tZA.e();
                                }
                                this.c |= 2;
                            } else if (!c0638Ld.a(i2, c0767Qd)) {
                            }
                        }
                        z = true;
                    } catch (IOException e) {
                        QB qb = new QB(e.getMessage());
                        qb.b = this;
                        throw qb;
                    }
                } catch (QB e2) {
                    e2.b = this;
                    throw e2;
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

    @Override // com.android.tools.r8.internal.UN
    public final boolean a() {
        byte b = this.f;
        if (b == 1) {
            return true;
        }
        if (b == 0) {
            return false;
        }
        int i2 = this.c;
        if ((i2 & 1) != 1) {
            this.f = (byte) 0;
            return false;
        }
        if ((i2 & 2) != 2) {
            this.f = (byte) 0;
            return false;
        }
        if (this.e.a()) {
            this.f = (byte) 1;
            return true;
        }
        this.f = (byte) 0;
        return false;
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
        return new C2519rZ();
    }

    @Override // com.android.tools.r8.internal.L0
    public final AbstractC0574Ir e() {
        return new C2519rZ().a(this);
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
            c0767Qd.b(2, this.e);
        }
        c0767Qd.a(this.b);
    }

    public C2947wZ() {
        this.f = (byte) -1;
        this.g = -1;
        this.b = T7.b;
    }

    public C2947wZ(AbstractC0574Ir abstractC0574Ir) {
        super(0);
        this.f = (byte) -1;
        this.g = -1;
        this.b = abstractC0574Ir.b;
    }
}
