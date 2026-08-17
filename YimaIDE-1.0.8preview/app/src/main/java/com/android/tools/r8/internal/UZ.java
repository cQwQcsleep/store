package com.android.tools.r8.internal;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class UZ extends AbstractC0729Or {
    public static final UZ m;
    public static final RZ n = new RZ();
    public final T7 b;
    public int c;
    public int d;
    public int e;
    public TZ f;
    public C2903w00 g;
    public int h;
    public List i;
    public List j;
    public byte k;
    public int l;

    static {
        UZ uz = new UZ();
        m = uz;
        uz.d = 0;
        uz.e = 0;
        uz.f = TZ.c;
        uz.g = C2903w00.u;
        uz.h = 0;
        List list = Collections.EMPTY_LIST;
        uz.i = list;
        uz.j = list;
    }

    /* JADX WARN: Code duplicated, block: B:114:0x00ed A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:115:0x00e5 A[SYNTHETIC] */
    public UZ(C0638Ld c0638Ld, C0389Bo c0389Bo) {
        TZ tz;
        this.k = (byte) -1;
        this.l = -1;
        boolean z = false;
        this.d = 0;
        this.e = 0;
        this.f = TZ.c;
        this.g = C2903w00.u;
        this.h = 0;
        List list = Collections.EMPTY_LIST;
        this.i = list;
        this.j = list;
        R7 r7 = new R7();
        C0767Qd c0767Qd = new C0767Qd(r7, new byte[1]);
        int i = 0;
        while (!z) {
            try {
                try {
                    int i2 = c0638Ld.i();
                    if (i2 != 0) {
                        if (i2 == 8) {
                            this.c |= 1;
                            this.d = c0638Ld.f();
                        } else if (i2 != 16) {
                            C2817v00 c2817v00A = null;
                            TZ tz2 = null;
                            if (i2 == 24) {
                                int iF = c0638Ld.f();
                                if (iF == 0) {
                                    tz = TZ.c;
                                } else if (iF != 1) {
                                    if (iF == 2) {
                                        tz = TZ.e;
                                    }
                                    if (tz2 == null) {
                                        c0767Qd.g(i2);
                                        c0767Qd.g(iF);
                                    } else {
                                        this.c |= 4;
                                        this.f = tz2;
                                    }
                                } else {
                                    tz = TZ.d;
                                }
                                tz2 = tz;
                                if (tz2 == null) {
                                    c0767Qd.g(i2);
                                    c0767Qd.g(iF);
                                } else {
                                    this.c |= 4;
                                    this.f = tz2;
                                }
                            } else if (i2 == 34) {
                                if ((this.c & 8) == 8) {
                                    C2903w00 c2903w00 = this.g;
                                    c2903w00.getClass();
                                    c2817v00A = C2903w00.a(c2903w00);
                                }
                                C2903w00 c2903w01 = (C2903w00) c0638Ld.a(C2903w00.v, c0389Bo);
                                this.g = c2903w01;
                                if (c2817v00A != null) {
                                    c2817v00A.a(c2903w01);
                                    this.g = c2817v00A.f();
                                }
                                this.c |= 8;
                            } else if (i2 == 40) {
                                this.c |= 16;
                                this.h = c0638Ld.f();
                            } else if (i2 == 50) {
                                int i3 = (i == true ? 1 : 0) & 32;
                                i = i;
                                if (i3 != 32) {
                                    this.i = new ArrayList();
                                    i = (i == true ? 1 : 0) | 32;
                                }
                                this.i.add(c0638Ld.a(n, c0389Bo));
                            } else if (i2 == 58) {
                                int i4 = (i == true ? 1 : 0) & 64;
                                i = i;
                                if (i4 != 64) {
                                    this.j = new ArrayList();
                                    i = (i == true ? 1 : 0) | 64;
                                }
                                this.j.add(c0638Ld.a(n, c0389Bo));
                            } else if (!c0638Ld.a(i2, c0767Qd)) {
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
                } catch (IOException e2) {
                    QB qb = new QB(e2.getMessage());
                    qb.b = this;
                    throw qb;
                }
            } catch (Throwable th) {
                if (((i == true ? 1 : 0) & 32) == 32) {
                    this.i = Collections.unmodifiableList(this.i);
                }
                if (((i == true ? 1 : 0) & 64) == 64) {
                    this.j = Collections.unmodifiableList(this.j);
                }
                try {
                    c0767Qd.a();
                } catch (IOException unused) {
                } finally {
                    this.b = r7.c();
                }
                throw th;
            }
        }
        if (((i == true ? 1 : 0) & 32) == 32) {
            this.i = Collections.unmodifiableList(this.i);
        }
        if (((i == true ? 1 : 0) & 64) == 64) {
            this.j = Collections.unmodifiableList(this.j);
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
            c0767Qd.b(4, this.g);
        }
        if ((this.c & 16) == 16) {
            int i3 = this.h;
            c0767Qd.c(5, 0);
            c0767Qd.d(i3);
        }
        for (int i4 = 0; i4 < this.i.size(); i4++) {
            c0767Qd.b(6, (L0) this.i.get(i4));
        }
        for (int i5 = 0; i5 < this.j.size(); i5++) {
            c0767Qd.b(7, (L0) this.j.get(i5));
        }
        c0767Qd.a(this.b);
    }

    @Override // com.android.tools.r8.internal.L0
    public final int c() {
        int i = this.l;
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
        for (int i2 = 0; i2 < this.i.size(); i2++) {
            iA += C0767Qd.a(6, (L0) this.i.get(i2));
        }
        for (int i3 = 0; i3 < this.j.size(); i3++) {
            iA += C0767Qd.a(7, (L0) this.j.get(i3));
        }
        int size = this.b.size() + iA;
        this.l = size;
        return size;
    }

    @Override // com.android.tools.r8.internal.L0
    public final AbstractC0574Ir d() {
        return new SZ();
    }

    @Override // com.android.tools.r8.internal.L0
    public final AbstractC0574Ir e() {
        return new SZ().a(this);
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
        if ((this.c & 8) == 8 && !this.g.a()) {
            this.k = (byte) 0;
            return false;
        }
        for (int i = 0; i < this.i.size(); i++) {
            if (!((UZ) this.i.get(i)).a()) {
                this.k = (byte) 0;
                return false;
            }
        }
        for (int i2 = 0; i2 < this.j.size(); i2++) {
            if (!((UZ) this.j.get(i2)).a()) {
                this.k = (byte) 0;
                return false;
            }
        }
        this.k = (byte) 1;
        return true;
    }

    public UZ() {
        this.k = (byte) -1;
        this.l = -1;
        this.b = T7.b;
    }

    public UZ(AbstractC0574Ir abstractC0574Ir) {
        super(0);
        this.k = (byte) -1;
        this.l = -1;
        this.b = abstractC0574Ir.b;
    }
}
