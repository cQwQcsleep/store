package com.android.tools.r8.internal;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class JD extends AbstractC0729Or {
    public static final JD m;
    public static final HD n = new HD();
    public final T7 b;
    public int c;
    public List d;
    public List e;
    public InterfaceC3186zJ f;
    public C2305p00 g;
    public C2048m00 h;
    public List i;
    public List j;
    public byte k;
    public int l;

    static {
        JD jd = new JD();
        m = jd;
        List list = Collections.EMPTY_LIST;
        jd.d = list;
        jd.e = list;
        jd.f = C3017xJ.c;
        jd.g = C2305p00.f;
        jd.h = C2048m00.f;
        jd.i = list;
        jd.j = list;
    }

    public JD(C0638Ld c0638Ld, C0389Bo c0389Bo) {
        this.k = (byte) -1;
        this.l = -1;
        List list = Collections.EMPTY_LIST;
        this.d = list;
        this.e = list;
        this.f = C3017xJ.c;
        this.g = C2305p00.f;
        this.h = C2048m00.f;
        this.i = list;
        this.j = list;
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
                            if (i2 == 10) {
                                int i3 = (i == true ? 1 : 0) & 1;
                                i = i;
                                if (i3 != 1) {
                                    this.d = new ArrayList();
                                    i = (i == true ? 1 : 0) | 1;
                                }
                                this.d.add(c0638Ld.a(MD.q, c0389Bo));
                            } else if (i2 == 18) {
                                int i4 = (i == true ? 1 : 0) & 2;
                                i = i;
                                if (i4 != 2) {
                                    this.e = new ArrayList();
                                    i = (i == true ? 1 : 0) | 2;
                                }
                                this.e.add(c0638Ld.a(MD.q, c0389Bo));
                            } else if (i2 != 26) {
                                C1621h00 c1621h00A = null;
                                C2219o00 c2219o00A = null;
                                if (i2 == 34) {
                                    if ((this.c & 1) == 1) {
                                        C2305p00 c2305p00 = this.g;
                                        c2305p00.getClass();
                                        c2219o00A = new C2219o00().a(c2305p00);
                                    }
                                    C2305p00 c2305p01 = (C2305p00) c0638Ld.a(C2305p00.g, c0389Bo);
                                    this.g = c2305p01;
                                    if (c2219o00A != null) {
                                        c2219o00A.a(c2305p01);
                                        this.g = c2219o00A.e();
                                    }
                                    this.c |= 1;
                                } else if (i2 == 42) {
                                    if ((this.c & 2) == 2) {
                                        C2048m00 c2048m00 = this.h;
                                        c2048m00.getClass();
                                        c1621h00A = new C1621h00().a(c2048m00);
                                    }
                                    C2048m00 c2048m01 = (C2048m00) c0638Ld.a(C2048m00.g, c0389Bo);
                                    this.h = c2048m01;
                                    if (c1621h00A != null) {
                                        c1621h00A.a(c2048m01);
                                        this.h = c1621h00A.e();
                                    }
                                    this.c |= 2;
                                } else if (i2 == 50) {
                                    int i5 = (i == true ? 1 : 0) & 32;
                                    i = i;
                                    if (i5 != 32) {
                                        this.i = new ArrayList();
                                        i = (i == true ? 1 : 0) | 32;
                                    }
                                    this.i.add(c0638Ld.a(C3117yZ.i, c0389Bo));
                                } else if (i2 == 130) {
                                    int i6 = (i == true ? 1 : 0) & 64;
                                    i = i;
                                    if (i6 != 64) {
                                        this.j = new ArrayList();
                                        i = (i == true ? 1 : 0) | 64;
                                    }
                                    this.j.add(c0638Ld.a(CZ.L, c0389Bo));
                                } else if (!c0638Ld.a(i2, c0767Qd)) {
                                }
                            } else {
                                CL clB = c0638Ld.b();
                                int i7 = (i == true ? 1 : 0) & 4;
                                i = i;
                                if (i7 != 4) {
                                    this.f = new C3017xJ();
                                    i = (i == true ? 1 : 0) | 4;
                                }
                                this.f.a(clB);
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
                if (((i == true ? 1 : 0) & 1) == 1) {
                    this.d = Collections.unmodifiableList(this.d);
                }
                if (((i == true ? 1 : 0) & 2) == 2) {
                    this.e = Collections.unmodifiableList(this.e);
                }
                if (((i == true ? 1 : 0) & 4) == 4) {
                    this.f = this.f.f();
                }
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
        if (((i == true ? 1 : 0) & 1) == 1) {
            this.d = Collections.unmodifiableList(this.d);
        }
        if (((i == true ? 1 : 0) & 2) == 2) {
            this.e = Collections.unmodifiableList(this.e);
        }
        if (((i == true ? 1 : 0) & 4) == 4) {
            this.f = this.f.f();
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
        for (int i = 0; i < this.d.size(); i++) {
            c0767Qd.b(1, (L0) this.d.get(i));
        }
        for (int i2 = 0; i2 < this.e.size(); i2++) {
            c0767Qd.b(2, (L0) this.e.get(i2));
        }
        for (int i3 = 0; i3 < this.f.size(); i3++) {
            c0767Qd.a(3, this.f.g(i3));
        }
        if ((this.c & 1) == 1) {
            c0767Qd.b(4, this.g);
        }
        if ((this.c & 2) == 2) {
            c0767Qd.b(5, this.h);
        }
        for (int i4 = 0; i4 < this.i.size(); i4++) {
            c0767Qd.b(6, (L0) this.i.get(i4));
        }
        for (int i5 = 0; i5 < this.j.size(); i5++) {
            c0767Qd.b(16, (L0) this.j.get(i5));
        }
        c0767Qd.a(this.b);
    }

    @Override // com.android.tools.r8.internal.L0
    public final int c() {
        InterfaceC3186zJ interfaceC3186zJ;
        int i = this.l;
        if (i != -1) {
            return i;
        }
        int iA = 0;
        for (int i2 = 0; i2 < this.d.size(); i2++) {
            iA += C0767Qd.a(1, (L0) this.d.get(i2));
        }
        for (int i3 = 0; i3 < this.e.size(); i3++) {
            iA += C0767Qd.a(2, (L0) this.e.get(i3));
        }
        int i4 = 0;
        int size = 0;
        while (true) {
            int size2 = this.f.size();
            interfaceC3186zJ = this.f;
            if (i4 >= size2) {
                break;
            }
            T7 t7G = interfaceC3186zJ.g(i4);
            size += t7G.size() + C0767Qd.b(t7G.size());
            i4++;
        }
        int size3 = interfaceC3186zJ.size() + iA + size;
        if ((this.c & 1) == 1) {
            size3 += C0767Qd.a(4, this.g);
        }
        if ((this.c & 2) == 2) {
            size3 += C0767Qd.a(5, this.h);
        }
        for (int i5 = 0; i5 < this.i.size(); i5++) {
            size3 += C0767Qd.a(6, (L0) this.i.get(i5));
        }
        for (int i6 = 0; i6 < this.j.size(); i6++) {
            size3 += C0767Qd.a(16, (L0) this.j.get(i6));
        }
        int size4 = this.b.size() + size3;
        this.l = size4;
        return size4;
    }

    @Override // com.android.tools.r8.internal.L0
    public final AbstractC0574Ir d() {
        return new ID();
    }

    @Override // com.android.tools.r8.internal.L0
    public final AbstractC0574Ir e() {
        return new ID().a(this);
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
        for (int i = 0; i < this.d.size(); i++) {
            if (!((MD) this.d.get(i)).a()) {
                this.k = (byte) 0;
                return false;
            }
        }
        for (int i2 = 0; i2 < this.e.size(); i2++) {
            if (!((MD) this.e.get(i2)).a()) {
                this.k = (byte) 0;
                return false;
            }
        }
        if ((this.c & 2) == 2 && !this.h.a()) {
            this.k = (byte) 0;
            return false;
        }
        for (int i3 = 0; i3 < this.i.size(); i3++) {
            if (!((C3117yZ) this.i.get(i3)).a()) {
                this.k = (byte) 0;
                return false;
            }
        }
        for (int i4 = 0; i4 < this.j.size(); i4++) {
            if (!((CZ) this.j.get(i4)).a()) {
                this.k = (byte) 0;
                return false;
            }
        }
        this.k = (byte) 1;
        return true;
    }

    public JD() {
        this.k = (byte) -1;
        this.l = -1;
        this.b = T7.b;
    }

    public JD(AbstractC0574Ir abstractC0574Ir) {
        super(0);
        this.k = (byte) -1;
        this.l = -1;
        this.b = abstractC0574Ir.b;
    }
}
