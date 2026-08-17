package com.android.tools.r8.internal;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class NZ extends AbstractC0729Or {
    public static final NZ j;
    public static final JZ k = new JZ();
    public final T7 b;
    public int c;
    public LZ d;
    public List e;
    public UZ f;
    public MZ g;
    public byte h;
    public int i;

    static {
        NZ nz = new NZ();
        j = nz;
        nz.d = LZ.c;
        nz.e = Collections.EMPTY_LIST;
        nz.f = UZ.m;
        nz.g = MZ.c;
    }

    public NZ(C0638Ld c0638Ld, C0389Bo c0389Bo) {
        this.h = (byte) -1;
        this.i = -1;
        this.d = LZ.c;
        this.e = Collections.EMPTY_LIST;
        this.f = UZ.m;
        this.g = MZ.c;
        R7 r7 = new R7();
        C0767Qd c0767Qd = new C0767Qd(r7, new byte[1]);
        boolean z = false;
        char c = 0;
        while (!z) {
            try {
                try {
                    int i = c0638Ld.i();
                    if (i != 0) {
                        MZ mz = null;
                        LZ lz = null;
                        SZ szA = null;
                        if (i == 8) {
                            int iF = c0638Ld.f();
                            if (iF == 0) {
                                lz = LZ.c;
                            } else if (iF == 1) {
                                lz = LZ.d;
                            } else if (iF == 2) {
                                lz = LZ.e;
                            }
                            if (lz == null) {
                                c0767Qd.g(i);
                                c0767Qd.g(iF);
                            } else {
                                this.c |= 1;
                                this.d = lz;
                            }
                        } else if (i == 18) {
                            int i2 = (c == true ? 1 : 0) & 2;
                            c = c;
                            if (i2 != 2) {
                                this.e = new ArrayList();
                                c = 2;
                            }
                            this.e.add(c0638Ld.a(UZ.n, c0389Bo));
                        } else if (i == 26) {
                            if ((this.c & 2) == 2) {
                                UZ uz = this.f;
                                uz.getClass();
                                szA = new SZ().a(uz);
                            }
                            UZ uz2 = (UZ) c0638Ld.a(UZ.n, c0389Bo);
                            this.f = uz2;
                            if (szA != null) {
                                szA.a(uz2);
                                this.f = szA.e();
                            }
                            this.c |= 2;
                        } else if (i == 32) {
                            int iF2 = c0638Ld.f();
                            if (iF2 == 0) {
                                mz = MZ.c;
                            } else if (iF2 == 1) {
                                mz = MZ.d;
                            } else if (iF2 == 2) {
                                mz = MZ.e;
                            }
                            if (mz == null) {
                                c0767Qd.g(i);
                                c0767Qd.g(iF2);
                            } else {
                                this.c |= 4;
                                this.g = mz;
                            }
                        } else if (!c0638Ld.a(i, c0767Qd)) {
                        }
                    }
                    z = true;
                } catch (Throwable th) {
                    if (((c == true ? 1 : 0) & 2) == 2) {
                        this.e = Collections.unmodifiableList(this.e);
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
        if (((c == true ? 1 : 0) & 2) == 2) {
            this.e = Collections.unmodifiableList(this.e);
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
        for (int i = 0; i < this.e.size(); i++) {
            c0767Qd.b(2, (L0) this.e.get(i));
        }
        if ((this.c & 2) == 2) {
            c0767Qd.b(3, this.f);
        }
        if ((this.c & 4) == 4) {
            c0767Qd.b(4, this.g.b);
        }
        c0767Qd.a(this.b);
    }

    @Override // com.android.tools.r8.internal.L0
    public final int c() {
        int i = this.i;
        if (i != -1) {
            return i;
        }
        int iA = (this.c & 1) == 1 ? C0767Qd.a(this.d.b) + C0767Qd.c(1) : 0;
        for (int i2 = 0; i2 < this.e.size(); i2++) {
            iA += C0767Qd.a(2, (L0) this.e.get(i2));
        }
        if ((this.c & 2) == 2) {
            iA += C0767Qd.a(3, this.f);
        }
        if ((this.c & 4) == 4) {
            iA += C0767Qd.a(this.g.b) + C0767Qd.c(4);
        }
        int size = this.b.size() + iA;
        this.i = size;
        return size;
    }

    @Override // com.android.tools.r8.internal.L0
    public final AbstractC0574Ir d() {
        return new KZ();
    }

    @Override // com.android.tools.r8.internal.L0
    public final AbstractC0574Ir e() {
        return new KZ().a(this);
    }

    @Override // com.android.tools.r8.internal.UN
    public final boolean a() {
        byte b = this.h;
        if (b == 1) {
            return true;
        }
        if (b == 0) {
            return false;
        }
        for (int i = 0; i < this.e.size(); i++) {
            if (!((UZ) this.e.get(i)).a()) {
                this.h = (byte) 0;
                return false;
            }
        }
        if ((this.c & 2) == 2 && !this.f.a()) {
            this.h = (byte) 0;
            return false;
        }
        this.h = (byte) 1;
        return true;
    }

    public NZ() {
        this.h = (byte) -1;
        this.i = -1;
        this.b = T7.b;
    }

    public NZ(AbstractC0574Ir abstractC0574Ir) {
        super(0);
        this.h = (byte) -1;
        this.i = -1;
        this.b = abstractC0574Ir.b;
    }
}
