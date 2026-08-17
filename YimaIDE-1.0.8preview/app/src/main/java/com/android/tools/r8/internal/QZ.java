package com.android.tools.r8.internal;

import java.io.IOException;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class QZ extends Lr {
    public static final QZ h;
    public static final OZ i = new OZ();
    public final T7 c;
    public int d;
    public int e;
    public byte f;
    public int g;

    static {
        QZ qz = new QZ();
        h = qz;
        qz.e = 0;
    }

    public QZ(C0638Ld c0638Ld, C0389Bo c0389Bo) {
        this.f = (byte) -1;
        this.g = -1;
        boolean z = false;
        this.e = 0;
        R7 r7 = new R7();
        C0767Qd c0767Qd = new C0767Qd(r7, new byte[1]);
        while (!z) {
            try {
                try {
                    int i2 = c0638Ld.i();
                    if (i2 != 0) {
                        if (i2 == 8) {
                            this.d |= 1;
                            this.e = c0638Ld.f();
                        } else if (!a(c0638Ld, c0767Qd, c0389Bo, i2)) {
                        }
                    }
                    z = true;
                } catch (QB e) {
                    e.b = this;
                    throw e;
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
                    this.c = r7.c();
                }
                this.b.a();
                throw th;
            }
        }
        try {
            c0767Qd.a();
        } catch (IOException unused2) {
        } finally {
            this.c = r7.c();
        }
        this.b.a();
    }

    @Override // com.android.tools.r8.internal.L0
    public final void a(C0767Qd c0767Qd) throws IOException {
        c();
        C0626Kr c0626Kr = new C0626Kr(this);
        if ((this.d & 1) == 1) {
            int i2 = this.e;
            c0767Qd.c(1, 0);
            c0767Qd.d(i2);
        }
        c0626Kr.a(200, c0767Qd);
        c0767Qd.a(this.c);
    }

    @Override // com.android.tools.r8.internal.UN
    public final L0 b() {
        return h;
    }

    @Override // com.android.tools.r8.internal.L0
    public final int c() {
        int i2 = this.g;
        if (i2 != -1) {
            return i2;
        }
        int size = this.c.size() + g() + ((this.d & 1) == 1 ? C0767Qd.a(1, this.e) : 0);
        this.g = size;
        return size;
    }

    @Override // com.android.tools.r8.internal.L0
    public final AbstractC0574Ir d() {
        return new PZ();
    }

    @Override // com.android.tools.r8.internal.L0
    public final AbstractC0574Ir e() {
        return new PZ().a(this);
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
        if (!f()) {
            this.f = (byte) 0;
            return false;
        }
        this.f = (byte) 1;
        return true;
    }

    public QZ() {
        this.f = (byte) -1;
        this.g = -1;
        this.c = T7.b;
    }

    public QZ(AbstractC0600Jr abstractC0600Jr) {
        super(abstractC0600Jr);
        this.f = (byte) -1;
        this.g = -1;
        this.c = abstractC0600Jr.b;
    }
}
