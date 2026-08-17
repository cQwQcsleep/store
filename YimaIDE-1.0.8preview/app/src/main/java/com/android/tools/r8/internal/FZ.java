package com.android.tools.r8.internal;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class FZ extends Lr {
    public static final FZ j;
    public static final DZ k = new DZ();
    public final T7 c;
    public int d;
    public int e;
    public List f;
    public List g;
    public byte h;
    public int i;

    static {
        FZ fz = new FZ();
        j = fz;
        fz.e = 6;
        List list = Collections.EMPTY_LIST;
        fz.f = list;
        fz.g = list;
    }

    public FZ(C0638Ld c0638Ld, C0389Bo c0389Bo) {
        this.h = (byte) -1;
        this.i = -1;
        this.e = 6;
        List list = Collections.EMPTY_LIST;
        this.f = list;
        this.g = list;
        R7 r7 = new R7();
        C0767Qd c0767Qd = new C0767Qd(r7, new byte[1]);
        boolean z = false;
        int i = 0;
        while (!z) {
            try {
                try {
                    try {
                        int i2 = c0638Ld.i();
                        if (i2 != 0) {
                            if (i2 == 8) {
                                this.d |= 1;
                                this.e = c0638Ld.f();
                            } else if (i2 == 18) {
                                if ((i & 2) != 2) {
                                    this.f = new ArrayList();
                                    i |= 2;
                                }
                                this.f.add(c0638Ld.a(J00.n, c0389Bo));
                            } else if (i2 == 248) {
                                if ((i & 4) != 4) {
                                    this.g = new ArrayList();
                                    i |= 4;
                                }
                                this.g.add(Integer.valueOf(c0638Ld.f()));
                            } else if (i2 == 250) {
                                int iB = c0638Ld.b(c0638Ld.f());
                                if ((i & 4) != 4 && c0638Ld.a() > 0) {
                                    this.g = new ArrayList();
                                    i |= 4;
                                }
                                while (c0638Ld.a() > 0) {
                                    this.g.add(Integer.valueOf(c0638Ld.f()));
                                }
                                c0638Ld.h = iB;
                                c0638Ld.j();
                            } else if (!a(c0638Ld, c0767Qd, c0389Bo, i2)) {
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
                if ((i & 2) == 2) {
                    this.f = Collections.unmodifiableList(this.f);
                }
                if ((i & 4) == 4) {
                    this.g = Collections.unmodifiableList(this.g);
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
        if ((i & 2) == 2) {
            this.f = Collections.unmodifiableList(this.f);
        }
        if ((i & 4) == 4) {
            this.g = Collections.unmodifiableList(this.g);
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
        for (int i2 = 0; i2 < this.f.size(); i2++) {
            c0767Qd.b(2, (L0) this.f.get(i2));
        }
        for (int i3 = 0; i3 < this.g.size(); i3++) {
            int iIntValue = ((Integer) this.g.get(i3)).intValue();
            c0767Qd.c(31, 0);
            c0767Qd.d(iIntValue);
        }
        c0626Kr.a(19000, c0767Qd);
        c0767Qd.a(this.c);
    }

    @Override // com.android.tools.r8.internal.UN
    public final L0 b() {
        return j;
    }

    @Override // com.android.tools.r8.internal.L0
    public final int c() {
        int i = this.i;
        if (i != -1) {
            return i;
        }
        int i2 = 0;
        int iA = (this.d & 1) == 1 ? C0767Qd.a(1, this.e) : 0;
        for (int i3 = 0; i3 < this.f.size(); i3++) {
            iA += C0767Qd.a(2, (L0) this.f.get(i3));
        }
        int iB = 0;
        while (true) {
            int size = this.g.size();
            List list = this.g;
            if (i2 >= size) {
                int size2 = this.c.size() + g() + (list.size() * 2) + iA + iB;
                this.i = size2;
                return size2;
            }
            int iIntValue = ((Integer) list.get(i2)).intValue();
            iB += iIntValue >= 0 ? C0767Qd.b(iIntValue) : 10;
            i2++;
        }
    }

    @Override // com.android.tools.r8.internal.L0
    public final AbstractC0574Ir d() {
        return new EZ();
    }

    @Override // com.android.tools.r8.internal.L0
    public final AbstractC0574Ir e() {
        return new EZ().a(this);
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
        for (int i = 0; i < this.f.size(); i++) {
            if (!((J00) this.f.get(i)).a()) {
                this.h = (byte) 0;
                return false;
            }
        }
        if (!f()) {
            this.h = (byte) 0;
            return false;
        }
        this.h = (byte) 1;
        return true;
    }

    public FZ() {
        this.h = (byte) -1;
        this.i = -1;
        this.c = T7.b;
    }

    public FZ(AbstractC0600Jr abstractC0600Jr) {
        super(abstractC0600Jr);
        this.h = (byte) -1;
        this.i = -1;
        this.c = abstractC0600Jr.b;
    }
}
