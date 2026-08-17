package com.android.tools.r8.internal;

import java.io.IOException;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class J00 extends Lr {
    public static final J00 m;
    public static final H00 n = new H00();
    public final T7 c;
    public int d;
    public int e;
    public int f;
    public C2903w00 g;
    public int h;
    public C2903w00 i;
    public int j;
    public byte k;
    public int l;

    static {
        J00 j00 = new J00();
        m = j00;
        j00.e = 0;
        j00.f = 0;
        C2903w00 c2903w00 = C2903w00.u;
        j00.g = c2903w00;
        j00.h = 0;
        j00.i = c2903w00;
        j00.j = 0;
    }

    public J00(C0638Ld c0638Ld, C0389Bo c0389Bo) {
        this.k = (byte) -1;
        this.l = -1;
        boolean z = false;
        this.e = 0;
        this.f = 0;
        C2903w00 c2903w00 = C2903w00.u;
        this.g = c2903w00;
        this.h = 0;
        this.i = c2903w00;
        this.j = 0;
        R7 r7 = new R7();
        C0767Qd c0767Qd = new C0767Qd(r7, new byte[1]);
        while (!z) {
            try {
                try {
                    int i = c0638Ld.i();
                    if (i != 0) {
                        if (i == 8) {
                            this.d |= 1;
                            this.e = c0638Ld.f();
                        } else if (i != 16) {
                            C2817v00 c2817v00A = null;
                            if (i == 26) {
                                if ((this.d & 4) == 4) {
                                    C2903w00 c2903w01 = this.g;
                                    c2903w01.getClass();
                                    c2817v00A = C2903w00.a(c2903w01);
                                }
                                C2903w00 c2903w02 = (C2903w00) c0638Ld.a(C2903w00.v, c0389Bo);
                                this.g = c2903w02;
                                if (c2817v00A != null) {
                                    c2817v00A.a(c2903w02);
                                    this.g = c2817v00A.f();
                                }
                                this.d |= 4;
                            } else if (i == 34) {
                                if ((this.d & 16) == 16) {
                                    C2903w00 c2903w03 = this.i;
                                    c2903w03.getClass();
                                    c2817v00A = C2903w00.a(c2903w03);
                                }
                                C2903w00 c2903w04 = (C2903w00) c0638Ld.a(C2903w00.v, c0389Bo);
                                this.i = c2903w04;
                                if (c2817v00A != null) {
                                    c2817v00A.a(c2903w04);
                                    this.i = c2817v00A.f();
                                }
                                this.d |= 16;
                            } else if (i == 40) {
                                this.d |= 8;
                                this.h = c0638Ld.f();
                            } else if (i == 48) {
                                this.d |= 32;
                                this.j = c0638Ld.f();
                            } else if (!a(c0638Ld, c0767Qd, c0389Bo, i)) {
                            }
                        } else {
                            this.d |= 2;
                            this.f = c0638Ld.f();
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
            int i = this.e;
            c0767Qd.c(1, 0);
            c0767Qd.d(i);
        }
        if ((this.d & 2) == 2) {
            int i2 = this.f;
            c0767Qd.c(2, 0);
            c0767Qd.d(i2);
        }
        if ((this.d & 4) == 4) {
            c0767Qd.b(3, this.g);
        }
        if ((this.d & 16) == 16) {
            c0767Qd.b(4, this.i);
        }
        if ((this.d & 8) == 8) {
            int i3 = this.h;
            c0767Qd.c(5, 0);
            c0767Qd.d(i3);
        }
        if ((this.d & 32) == 32) {
            int i4 = this.j;
            c0767Qd.c(6, 0);
            c0767Qd.d(i4);
        }
        c0626Kr.a(200, c0767Qd);
        c0767Qd.a(this.c);
    }

    @Override // com.android.tools.r8.internal.UN
    public final L0 b() {
        return m;
    }

    @Override // com.android.tools.r8.internal.L0
    public final int c() {
        int i = this.l;
        if (i != -1) {
            return i;
        }
        int iA = (this.d & 1) == 1 ? C0767Qd.a(1, this.e) : 0;
        if ((this.d & 2) == 2) {
            iA += C0767Qd.a(2, this.f);
        }
        if ((this.d & 4) == 4) {
            iA += C0767Qd.a(3, this.g);
        }
        if ((this.d & 16) == 16) {
            iA += C0767Qd.a(4, this.i);
        }
        if ((this.d & 8) == 8) {
            iA += C0767Qd.a(5, this.h);
        }
        if ((this.d & 32) == 32) {
            iA += C0767Qd.a(6, this.j);
        }
        int size = this.c.size() + g() + iA;
        this.l = size;
        return size;
    }

    @Override // com.android.tools.r8.internal.L0
    public final AbstractC0574Ir d() {
        return new I00();
    }

    @Override // com.android.tools.r8.internal.L0
    public final AbstractC0574Ir e() {
        return new I00().a(this);
    }

    @Override // com.android.tools.r8.internal.UN
    public final boolean a() {
        byte b = this.k;
        if (b == 1) {
            return true;
        }
        if (b == 0) {
            return false;
        }
        int i = this.d;
        if ((i & 2) == 2) {
            if ((i & 4) == 4 && !this.g.a()) {
                this.k = (byte) 0;
                return false;
            }
            if ((this.d & 16) == 16 && !this.i.a()) {
                this.k = (byte) 0;
                return false;
            }
            if (!f()) {
                this.k = (byte) 0;
                return false;
            }
            this.k = (byte) 1;
            return true;
        }
        this.k = (byte) 0;
        return false;
    }

    public J00() {
        this.k = (byte) -1;
        this.l = -1;
        this.c = T7.b;
    }

    public J00(AbstractC0600Jr abstractC0600Jr) {
        super(abstractC0600Jr);
        this.k = (byte) -1;
        this.l = -1;
        this.c = abstractC0600Jr.b;
    }
}
