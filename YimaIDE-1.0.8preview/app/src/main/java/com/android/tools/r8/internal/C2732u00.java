package com.android.tools.r8.internal;

import java.io.IOException;

/* JADX INFO: renamed from: com.android.tools.r8.internal.u00, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C2732u00 extends AbstractC0729Or {
    public static final C2732u00 i;
    public static final C2475r00 j = new C2475r00();
    public final T7 b;
    public int c;
    public EnumC2646t00 d;
    public C2903w00 e;
    public int f;
    public byte g;
    public int h;

    static {
        C2732u00 c2732u00 = new C2732u00();
        i = c2732u00;
        c2732u00.d = EnumC2646t00.e;
        c2732u00.e = C2903w00.u;
        c2732u00.f = 0;
    }

    public C2732u00(C0638Ld c0638Ld, C0389Bo c0389Bo) {
        this.g = (byte) -1;
        this.h = -1;
        this.d = EnumC2646t00.e;
        this.e = C2903w00.u;
        boolean z = false;
        this.f = 0;
        R7 r7 = new R7();
        C0767Qd c0767Qd = new C0767Qd(r7, new byte[1]);
        while (!z) {
            try {
                try {
                    int i2 = c0638Ld.i();
                    if (i2 != 0) {
                        C2817v00 c2817v00A = null;
                        EnumC2646t00 enumC2646t00 = null;
                        if (i2 == 8) {
                            int iF = c0638Ld.f();
                            if (iF == 0) {
                                enumC2646t00 = EnumC2646t00.c;
                            } else if (iF == 1) {
                                enumC2646t00 = EnumC2646t00.d;
                            } else if (iF == 2) {
                                enumC2646t00 = EnumC2646t00.e;
                            } else if (iF == 3) {
                                enumC2646t00 = EnumC2646t00.f;
                            }
                            if (enumC2646t00 == null) {
                                c0767Qd.g(i2);
                                c0767Qd.g(iF);
                            } else {
                                this.c |= 1;
                                this.d = enumC2646t00;
                            }
                        } else if (i2 == 18) {
                            if ((this.c & 2) == 2) {
                                C2903w00 c2903w00 = this.e;
                                c2903w00.getClass();
                                c2817v00A = C2903w00.a(c2903w00);
                            }
                            C2903w00 c2903w01 = (C2903w00) c0638Ld.a(C2903w00.v, c0389Bo);
                            this.e = c2903w01;
                            if (c2817v00A != null) {
                                c2817v00A.a(c2903w01);
                                this.e = c2817v00A.f();
                            }
                            this.c |= 2;
                        } else if (i2 == 24) {
                            this.c |= 4;
                            this.f = c0638Ld.f();
                        } else if (!c0638Ld.a(i2, c0767Qd)) {
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
            c0767Qd.b(1, this.d.b);
        }
        if ((this.c & 2) == 2) {
            c0767Qd.b(2, this.e);
        }
        if ((this.c & 4) == 4) {
            int i2 = this.f;
            c0767Qd.c(3, 0);
            c0767Qd.d(i2);
        }
        c0767Qd.a(this.b);
    }

    @Override // com.android.tools.r8.internal.L0
    public final int c() {
        int iA;
        int i2 = this.h;
        if (i2 != -1) {
            return i2;
        }
        if ((this.c & 1) == 1) {
            int i3 = this.d.b;
            iA = C0767Qd.a(i3) + C0767Qd.c(1);
        } else {
            iA = 0;
        }
        if ((this.c & 2) == 2) {
            iA += C0767Qd.a(2, this.e);
        }
        if ((this.c & 4) == 4) {
            iA += C0767Qd.a(3, this.f);
        }
        int size = this.b.size() + iA;
        this.h = size;
        return size;
    }

    @Override // com.android.tools.r8.internal.L0
    public final AbstractC0574Ir d() {
        return new C2561s00();
    }

    @Override // com.android.tools.r8.internal.L0
    public final AbstractC0574Ir e() {
        return new C2561s00().a(this);
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
        if ((this.c & 2) == 2 && !this.e.a()) {
            this.g = (byte) 0;
            return false;
        }
        this.g = (byte) 1;
        return true;
    }

    public C2732u00() {
        this.g = (byte) -1;
        this.h = -1;
        this.b = T7.b;
    }

    public C2732u00(AbstractC0574Ir abstractC0574Ir) {
        super(0);
        this.g = (byte) -1;
        this.h = -1;
        this.b = abstractC0574Ir.b;
    }
}
