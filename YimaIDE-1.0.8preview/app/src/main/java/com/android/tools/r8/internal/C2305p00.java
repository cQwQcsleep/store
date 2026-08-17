package com.android.tools.r8.internal;

import java.io.IOException;

/* JADX INFO: renamed from: com.android.tools.r8.internal.p00, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C2305p00 extends AbstractC0729Or {
    public static final C2305p00 f;
    public static final C2133n00 g = new C2133n00();
    public final T7 b;
    public InterfaceC3186zJ c;
    public byte d;
    public int e;

    static {
        C2305p00 c2305p00 = new C2305p00();
        f = c2305p00;
        c2305p00.c = C3017xJ.c;
    }

    public C2305p00(C0638Ld c0638Ld) {
        this.d = (byte) -1;
        this.e = -1;
        this.c = C3017xJ.c;
        R7 r7 = new R7();
        C0767Qd c0767Qd = new C0767Qd(r7, new byte[1]);
        boolean z = false;
        boolean z2 = false;
        while (!z) {
            try {
                try {
                    int i = c0638Ld.i();
                    if (i != 0) {
                        if (i == 10) {
                            CL clB = c0638Ld.b();
                            if (!z2) {
                                this.c = new C3017xJ();
                                z2 = true;
                            }
                            this.c.a(clB);
                        } else if (!c0638Ld.a(i, c0767Qd)) {
                        }
                    }
                    z = true;
                } catch (Throwable th) {
                    if (z2) {
                        this.c = this.c.f();
                    }
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
        if (z2) {
            this.c = this.c.f();
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
        for (int i = 0; i < this.c.size(); i++) {
            c0767Qd.a(1, this.c.g(i));
        }
        c0767Qd.a(this.b);
    }

    @Override // com.android.tools.r8.internal.L0
    public final int c() {
        int i = this.e;
        if (i != -1) {
            return i;
        }
        int i2 = 0;
        int size = 0;
        while (true) {
            int size2 = this.c.size();
            InterfaceC3186zJ interfaceC3186zJ = this.c;
            if (i2 >= size2) {
                int size3 = this.b.size() + interfaceC3186zJ.size() + size;
                this.e = size3;
                return size3;
            }
            T7 t7G = interfaceC3186zJ.g(i2);
            size += t7G.size() + C0767Qd.b(t7G.size());
            i2++;
        }
    }

    @Override // com.android.tools.r8.internal.L0
    public final AbstractC0574Ir d() {
        return new C2219o00();
    }

    @Override // com.android.tools.r8.internal.L0
    public final AbstractC0574Ir e() {
        return new C2219o00().a(this);
    }

    @Override // com.android.tools.r8.internal.UN
    public final boolean a() {
        byte b = this.d;
        if (b == 1) {
            return true;
        }
        if (b == 0) {
            return false;
        }
        this.d = (byte) 1;
        return true;
    }

    public C2305p00() {
        this.d = (byte) -1;
        this.e = -1;
        this.b = T7.b;
    }

    public C2305p00(AbstractC0574Ir abstractC0574Ir) {
        super(0);
        this.d = (byte) -1;
        this.e = -1;
        this.b = abstractC0574Ir.b;
    }
}
