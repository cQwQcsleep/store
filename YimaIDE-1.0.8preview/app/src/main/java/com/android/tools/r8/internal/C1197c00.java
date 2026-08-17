package com.android.tools.r8.internal;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/* JADX INFO: renamed from: com.android.tools.r8.internal.c00, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C1197c00 extends Lr {
    public static final C1197c00 l;
    public static final C1026a00 m = new C1026a00();
    public final T7 c;
    public int d;
    public List e;
    public List f;
    public List g;
    public G00 h;
    public R00 i;
    public byte j;
    public int k;

    static {
        C1197c00 c1197c00 = new C1197c00();
        l = c1197c00;
        List list = Collections.EMPTY_LIST;
        c1197c00.e = list;
        c1197c00.f = list;
        c1197c00.g = list;
        c1197c00.h = G00.h;
        c1197c00.i = R00.f;
    }

    public C1197c00(C0638Ld c0638Ld, C0389Bo c0389Bo) {
        this.j = (byte) -1;
        this.k = -1;
        List list = Collections.EMPTY_LIST;
        this.e = list;
        this.f = list;
        this.g = list;
        this.h = G00.h;
        this.i = R00.f;
        R7 r7 = new R7();
        C0767Qd c0767Qd = new C0767Qd(r7, new byte[1]);
        boolean z = false;
        int i = 0;
        while (!z) {
            try {
                try {
                    int i2 = c0638Ld.i();
                    if (i2 != 0) {
                        if (i2 == 26) {
                            int i3 = (i == true ? 1 : 0) & 1;
                            i = i;
                            if (i3 != 1) {
                                this.e = new ArrayList();
                                i = (i == true ? 1 : 0) | 1;
                            }
                            this.e.add(c0638Ld.a(XZ.w, c0389Bo));
                        } else if (i2 == 34) {
                            int i4 = (i == true ? 1 : 0) & 2;
                            i = i;
                            if (i4 != 2) {
                                this.f = new ArrayList();
                                i = (i == true ? 1 : 0) | 2;
                            }
                            this.f.add(c0638Ld.a(C1450f00.w, c0389Bo));
                        } else if (i2 != 42) {
                            Q00 q00A = null;
                            F00 f00A = null;
                            if (i2 == 242) {
                                if ((this.d & 1) == 1) {
                                    G00 g00 = this.h;
                                    g00.getClass();
                                    f00A = G00.a(g00);
                                }
                                G00 g01 = (G00) c0638Ld.a(G00.i, c0389Bo);
                                this.h = g01;
                                if (f00A != null) {
                                    f00A.a(g01);
                                    this.h = f00A.e();
                                }
                                this.d |= 1;
                            } else if (i2 == 258) {
                                if ((this.d & 2) == 2) {
                                    R00 r00 = this.i;
                                    r00.getClass();
                                    q00A = new Q00().a(r00);
                                }
                                R00 r01 = (R00) c0638Ld.a(R00.g, c0389Bo);
                                this.i = r01;
                                if (q00A != null) {
                                    q00A.a(r01);
                                    this.i = q00A.e();
                                }
                                this.d |= 2;
                            } else if (!a(c0638Ld, c0767Qd, c0389Bo, i2)) {
                            }
                        } else {
                            int i5 = (i == true ? 1 : 0) & 4;
                            i = i;
                            if (i5 != 4) {
                                this.g = new ArrayList();
                                i = (i == true ? 1 : 0) | 4;
                            }
                            this.g.add(c0638Ld.a(C3158z00.q, c0389Bo));
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
                if (((i == true ? 1 : 0) & 1) == 1) {
                    this.e = Collections.unmodifiableList(this.e);
                }
                if (((i == true ? 1 : 0) & 2) == 2) {
                    this.f = Collections.unmodifiableList(this.f);
                }
                if (((i == true ? 1 : 0) & 4) == 4) {
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
        if (((i == true ? 1 : 0) & 1) == 1) {
            this.e = Collections.unmodifiableList(this.e);
        }
        if (((i == true ? 1 : 0) & 2) == 2) {
            this.f = Collections.unmodifiableList(this.f);
        }
        if (((i == true ? 1 : 0) & 4) == 4) {
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

    @Override // com.android.tools.r8.internal.UN
    public final boolean a() {
        byte b = this.j;
        if (b == 1) {
            return true;
        }
        if (b == 0) {
            return false;
        }
        for (int i = 0; i < this.e.size(); i++) {
            if (!((XZ) this.e.get(i)).a()) {
                this.j = (byte) 0;
                return false;
            }
        }
        for (int i2 = 0; i2 < this.f.size(); i2++) {
            if (!((C1450f00) this.f.get(i2)).a()) {
                this.j = (byte) 0;
                return false;
            }
        }
        for (int i3 = 0; i3 < this.g.size(); i3++) {
            if (!((C3158z00) this.g.get(i3)).a()) {
                this.j = (byte) 0;
                return false;
            }
        }
        if ((this.d & 1) == 1 && !this.h.a()) {
            this.j = (byte) 0;
            return false;
        }
        if (f()) {
            this.j = (byte) 1;
            return true;
        }
        this.j = (byte) 0;
        return false;
    }

    @Override // com.android.tools.r8.internal.UN
    public final L0 b() {
        return l;
    }

    @Override // com.android.tools.r8.internal.L0
    public final int c() {
        int i = this.k;
        if (i != -1) {
            return i;
        }
        int iA = 0;
        for (int i2 = 0; i2 < this.e.size(); i2++) {
            iA += C0767Qd.a(3, (L0) this.e.get(i2));
        }
        for (int i3 = 0; i3 < this.f.size(); i3++) {
            iA += C0767Qd.a(4, (L0) this.f.get(i3));
        }
        for (int i4 = 0; i4 < this.g.size(); i4++) {
            iA += C0767Qd.a(5, (L0) this.g.get(i4));
        }
        if ((this.d & 1) == 1) {
            iA += C0767Qd.a(30, this.h);
        }
        if ((this.d & 2) == 2) {
            iA += C0767Qd.a(32, this.i);
        }
        int size = this.c.size() + g() + iA;
        this.k = size;
        return size;
    }

    @Override // com.android.tools.r8.internal.L0
    public final AbstractC0574Ir d() {
        return new C1112b00();
    }

    @Override // com.android.tools.r8.internal.L0
    public final AbstractC0574Ir e() {
        return new C1112b00().a(this);
    }

    @Override // com.android.tools.r8.internal.L0
    public final void a(C0767Qd c0767Qd) throws IOException {
        c();
        C0626Kr c0626Kr = new C0626Kr(this);
        for (int i = 0; i < this.e.size(); i++) {
            c0767Qd.b(3, (L0) this.e.get(i));
        }
        for (int i2 = 0; i2 < this.f.size(); i2++) {
            c0767Qd.b(4, (L0) this.f.get(i2));
        }
        for (int i3 = 0; i3 < this.g.size(); i3++) {
            c0767Qd.b(5, (L0) this.g.get(i3));
        }
        if ((this.d & 1) == 1) {
            c0767Qd.b(30, this.h);
        }
        if ((this.d & 2) == 2) {
            c0767Qd.b(32, this.i);
        }
        c0626Kr.a(200, c0767Qd);
        c0767Qd.a(this.c);
    }

    public C1197c00() {
        this.j = (byte) -1;
        this.k = -1;
        this.c = T7.b;
    }

    public C1197c00(AbstractC0600Jr abstractC0600Jr) {
        super(abstractC0600Jr);
        this.j = (byte) -1;
        this.k = -1;
        this.c = abstractC0600Jr.b;
    }
}
