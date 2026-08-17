package com.android.tools.r8.internal;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class D00 extends Lr {
    public static final D00 n;
    public static final A00 o = new A00();
    public final T7 c;
    public int d;
    public int e;
    public int f;
    public boolean g;
    public C00 h;
    public List i;
    public List j;
    public int k;
    public byte l;
    public int m;

    static {
        D00 d00 = new D00();
        n = d00;
        d00.e = 0;
        d00.f = 0;
        d00.g = false;
        d00.h = C00.e;
        List list = Collections.EMPTY_LIST;
        d00.i = list;
        d00.j = list;
    }

    public D00(C0638Ld c0638Ld, C0389Bo c0389Bo) {
        this.k = -1;
        this.l = (byte) -1;
        this.m = -1;
        this.e = 0;
        this.f = 0;
        this.g = false;
        this.h = C00.e;
        List list = Collections.EMPTY_LIST;
        this.i = list;
        this.j = list;
        R7 r7 = new R7();
        C0767Qd c0767Qd = new C0767Qd(r7, new byte[1]);
        boolean z = false;
        int i = 0;
        while (!z) {
            try {
                try {
                    int i2 = c0638Ld.i();
                    if (i2 != 0) {
                        if (i2 == 8) {
                            this.d |= 1;
                            this.e = c0638Ld.f();
                        } else if (i2 == 16) {
                            this.d |= 2;
                            this.f = c0638Ld.f();
                        } else if (i2 == 24) {
                            this.d |= 4;
                            this.g = c0638Ld.g() != 0;
                        } else if (i2 == 32) {
                            int iF = c0638Ld.f();
                            C00 c00 = iF != 0 ? iF != 1 ? iF != 2 ? null : C00.e : C00.d : C00.c;
                            if (c00 == null) {
                                c0767Qd.g(i2);
                                c0767Qd.g(iF);
                            } else {
                                this.d |= 8;
                                this.h = c00;
                            }
                        } else if (i2 == 42) {
                            if ((i & 16) != 16) {
                                this.i = new ArrayList();
                                i |= 16;
                            }
                            this.i.add(c0638Ld.a(C2903w00.v, c0389Bo));
                        } else if (i2 == 48) {
                            if ((i & 32) != 32) {
                                this.j = new ArrayList();
                                i |= 32;
                            }
                            this.j.add(Integer.valueOf(c0638Ld.f()));
                        } else if (i2 == 50) {
                            int iB = c0638Ld.b(c0638Ld.f());
                            if ((i & 32) != 32 && c0638Ld.a() > 0) {
                                this.j = new ArrayList();
                                i |= 32;
                            }
                            while (c0638Ld.a() > 0) {
                                this.j.add(Integer.valueOf(c0638Ld.f()));
                            }
                            c0638Ld.h = iB;
                            c0638Ld.j();
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
                if ((i & 16) == 16) {
                    this.i = Collections.unmodifiableList(this.i);
                }
                if ((i & 32) == 32) {
                    this.j = Collections.unmodifiableList(this.j);
                }
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
        if ((i & 16) == 16) {
            this.i = Collections.unmodifiableList(this.i);
        }
        if ((i & 32) == 32) {
            this.j = Collections.unmodifiableList(this.j);
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
            boolean z = this.g;
            c0767Qd.c(3, 0);
            c0767Qd.e(z ? 1 : 0);
        }
        if ((this.d & 8) == 8) {
            c0767Qd.b(4, this.h.b);
        }
        for (int i3 = 0; i3 < this.i.size(); i3++) {
            c0767Qd.b(5, (L0) this.i.get(i3));
        }
        if (this.j.size() > 0) {
            c0767Qd.g(50);
            c0767Qd.g(this.k);
        }
        for (int i4 = 0; i4 < this.j.size(); i4++) {
            c0767Qd.d(((Integer) this.j.get(i4)).intValue());
        }
        c0626Kr.a(1000, c0767Qd);
        c0767Qd.a(this.c);
    }

    @Override // com.android.tools.r8.internal.UN
    public final L0 b() {
        return n;
    }

    @Override // com.android.tools.r8.internal.L0
    public final int c() {
        List list;
        int i = this.m;
        if (i != -1) {
            return i;
        }
        int i2 = 0;
        int iA = (this.d & 1) == 1 ? C0767Qd.a(1, this.e) : 0;
        if ((this.d & 2) == 2) {
            iA += C0767Qd.a(2, this.f);
        }
        if ((this.d & 4) == 4) {
            iA += C0767Qd.c(3) + 1;
        }
        if ((this.d & 8) == 8) {
            iA += C0767Qd.a(this.h.b) + C0767Qd.c(4);
        }
        for (int i3 = 0; i3 < this.i.size(); i3++) {
            iA += C0767Qd.a(5, (L0) this.i.get(i3));
        }
        int i4 = 0;
        while (true) {
            int size = this.j.size();
            list = this.j;
            int iB = 10;
            if (i2 >= size) {
                break;
            }
            int iIntValue = ((Integer) list.get(i2)).intValue();
            if (iIntValue >= 0) {
                iB = C0767Qd.b(iIntValue);
            }
            i4 += iB;
            i2++;
        }
        int iB2 = iA + i4;
        if (!list.isEmpty()) {
            iB2 = iB2 + 1 + (i4 >= 0 ? C0767Qd.b(i4) : 10);
        }
        this.k = i4;
        int size2 = this.c.size() + g() + iB2;
        this.m = size2;
        return size2;
    }

    @Override // com.android.tools.r8.internal.L0
    public final AbstractC0574Ir d() {
        return new B00();
    }

    @Override // com.android.tools.r8.internal.L0
    public final AbstractC0574Ir e() {
        return new B00().a(this);
    }

    @Override // com.android.tools.r8.internal.UN
    public final boolean a() {
        byte b = this.l;
        if (b == 1) {
            return true;
        }
        if (b == 0) {
            return false;
        }
        int i = this.d;
        if ((i & 1) != 1) {
            this.l = (byte) 0;
            return false;
        }
        if ((i & 2) == 2) {
            for (int i2 = 0; i2 < this.i.size(); i2++) {
                if (!((C2903w00) this.i.get(i2)).a()) {
                    this.l = (byte) 0;
                    return false;
                }
            }
            if (!f()) {
                this.l = (byte) 0;
                return false;
            }
            this.l = (byte) 1;
            return true;
        }
        this.l = (byte) 0;
        return false;
    }

    public D00() {
        this.k = -1;
        this.l = (byte) -1;
        this.m = -1;
        this.c = T7.b;
    }

    public D00(AbstractC0600Jr abstractC0600Jr) {
        super(abstractC0600Jr);
        this.k = -1;
        this.l = (byte) -1;
        this.m = -1;
        this.c = abstractC0600Jr.b;
    }
}
