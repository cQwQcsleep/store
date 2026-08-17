package com.android.tools.r8.internal;

import java.io.IOException;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class O00 extends AbstractC0729Or {
    public static final O00 l;
    public static final K00 m = new K00();
    public final T7 b;
    public int c;
    public int d;
    public int e;
    public M00 f;
    public int g;
    public int h;
    public N00 i;
    public byte j;
    public int k;

    static {
        O00 o00 = new O00();
        l = o00;
        o00.d = 0;
        o00.e = 0;
        o00.f = M00.d;
        o00.g = 0;
        o00.h = 0;
        o00.i = N00.c;
    }

    /* JADX WARN: Code duplicated, block: B:91:0x0077 A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:92:0x0070 A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:96:0x00b6 A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:97:0x00ae A[SYNTHETIC] */
    public O00(C0638Ld c0638Ld) {
        N00 n00;
        M00 m00;
        this.j = (byte) -1;
        this.k = -1;
        boolean z = false;
        this.d = 0;
        this.e = 0;
        this.f = M00.d;
        this.g = 0;
        this.h = 0;
        this.i = N00.c;
        R7 r7 = new R7();
        C0767Qd c0767Qd = new C0767Qd(r7, new byte[1]);
        while (!z) {
            try {
                try {
                    try {
                        int i = c0638Ld.i();
                        if (i != 0) {
                            if (i == 8) {
                                this.c |= 1;
                                this.d = c0638Ld.f();
                            } else if (i != 16) {
                                N00 n01 = null;
                                M00 m01 = null;
                                if (i == 24) {
                                    int iF = c0638Ld.f();
                                    if (iF == 0) {
                                        m00 = M00.c;
                                    } else if (iF != 1) {
                                        if (iF == 2) {
                                            m00 = M00.e;
                                        }
                                        if (m01 == null) {
                                            c0767Qd.g(i);
                                            c0767Qd.g(iF);
                                        } else {
                                            this.c |= 4;
                                            this.f = m01;
                                        }
                                    } else {
                                        m00 = M00.d;
                                    }
                                    m01 = m00;
                                    if (m01 == null) {
                                        c0767Qd.g(i);
                                        c0767Qd.g(iF);
                                    } else {
                                        this.c |= 4;
                                        this.f = m01;
                                    }
                                } else if (i == 32) {
                                    this.c |= 8;
                                    this.g = c0638Ld.f();
                                } else if (i == 40) {
                                    this.c |= 16;
                                    this.h = c0638Ld.f();
                                } else if (i == 48) {
                                    int iF2 = c0638Ld.f();
                                    if (iF2 == 0) {
                                        n00 = N00.c;
                                    } else if (iF2 != 1) {
                                        if (iF2 == 2) {
                                            n00 = N00.e;
                                        }
                                        if (n01 == null) {
                                            c0767Qd.g(i);
                                            c0767Qd.g(iF2);
                                        } else {
                                            this.c |= 32;
                                            this.i = n01;
                                        }
                                    } else {
                                        n00 = N00.d;
                                    }
                                    n01 = n00;
                                    if (n01 == null) {
                                        c0767Qd.g(i);
                                        c0767Qd.g(iF2);
                                    } else {
                                        this.c |= 32;
                                        this.i = n01;
                                    }
                                } else if (!c0638Ld.a(i, c0767Qd)) {
                                }
                            } else {
                                this.c |= 2;
                                this.e = c0638Ld.f();
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
            int i = this.d;
            c0767Qd.c(1, 0);
            c0767Qd.d(i);
        }
        if ((this.c & 2) == 2) {
            int i2 = this.e;
            c0767Qd.c(2, 0);
            c0767Qd.d(i2);
        }
        if ((this.c & 4) == 4) {
            c0767Qd.b(3, this.f.b);
        }
        if ((this.c & 8) == 8) {
            int i3 = this.g;
            c0767Qd.c(4, 0);
            c0767Qd.d(i3);
        }
        if ((this.c & 16) == 16) {
            int i4 = this.h;
            c0767Qd.c(5, 0);
            c0767Qd.d(i4);
        }
        if ((this.c & 32) == 32) {
            c0767Qd.b(6, this.i.b);
        }
        c0767Qd.a(this.b);
    }

    @Override // com.android.tools.r8.internal.L0
    public final int c() {
        int i = this.k;
        if (i != -1) {
            return i;
        }
        int iA = (this.c & 1) == 1 ? C0767Qd.a(1, this.d) : 0;
        if ((this.c & 2) == 2) {
            iA += C0767Qd.a(2, this.e);
        }
        if ((this.c & 4) == 4) {
            iA += C0767Qd.a(this.f.b) + C0767Qd.c(3);
        }
        if ((this.c & 8) == 8) {
            iA += C0767Qd.a(4, this.g);
        }
        if ((this.c & 16) == 16) {
            iA += C0767Qd.a(5, this.h);
        }
        if ((this.c & 32) == 32) {
            iA += C0767Qd.a(this.i.b) + C0767Qd.c(6);
        }
        int size = this.b.size() + iA;
        this.k = size;
        return size;
    }

    @Override // com.android.tools.r8.internal.L0
    public final AbstractC0574Ir d() {
        return new L00();
    }

    @Override // com.android.tools.r8.internal.L0
    public final AbstractC0574Ir e() {
        return new L00().a(this);
    }

    @Override // com.android.tools.r8.internal.UN
    public final boolean a() {
        byte b = this.j;
        if (b == 1) {
            return true;
        }
        if (b == 0) {
            return false;
        }
        this.j = (byte) 1;
        return true;
    }

    public O00() {
        this.j = (byte) -1;
        this.k = -1;
        this.b = T7.b;
    }

    public O00(AbstractC0574Ir abstractC0574Ir) {
        super(0);
        this.j = (byte) -1;
        this.k = -1;
        this.b = abstractC0574Ir.b;
    }
}
