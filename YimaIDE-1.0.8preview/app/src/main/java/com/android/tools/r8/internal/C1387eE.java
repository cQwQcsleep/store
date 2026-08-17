package com.android.tools.r8.internal;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.xmlpull.v1.XmlPullParser;

/* JADX INFO: renamed from: com.android.tools.r8.internal.eE, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C1387eE extends AbstractC0729Or {
    public static final C1387eE n;
    public static final C1135bE o = new C1135bE();
    public final T7 b;
    public int c;
    public int d;
    public int e;
    public Object f;
    public EnumC1303dE g;
    public List h;
    public int i;
    public List j;
    public int k;
    public byte l;
    public int m;

    static {
        C1387eE c1387eE = new C1387eE();
        n = c1387eE;
        c1387eE.d = 1;
        c1387eE.e = 0;
        c1387eE.f = XmlPullParser.NO_NAMESPACE;
        c1387eE.g = EnumC1303dE.c;
        List list = Collections.EMPTY_LIST;
        c1387eE.h = list;
        c1387eE.j = list;
    }

    public C1387eE(C0638Ld c0638Ld) {
        this.i = -1;
        this.k = -1;
        this.l = (byte) -1;
        this.m = -1;
        this.d = 1;
        boolean z = false;
        this.e = 0;
        this.f = XmlPullParser.NO_NAMESPACE;
        this.g = EnumC1303dE.c;
        List list = Collections.EMPTY_LIST;
        this.h = list;
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
                        } else if (i2 == 16) {
                            this.c |= 2;
                            this.e = c0638Ld.f();
                        } else if (i2 == 24) {
                            int iF = c0638Ld.f();
                            EnumC1303dE enumC1303dE = iF != 0 ? iF != 1 ? iF != 2 ? null : EnumC1303dE.e : EnumC1303dE.d : EnumC1303dE.c;
                            if (enumC1303dE == null) {
                                c0767Qd.g(i2);
                                c0767Qd.g(iF);
                            } else {
                                this.c |= 8;
                                this.g = enumC1303dE;
                            }
                        } else if (i2 == 32) {
                            if ((i & 16) != 16) {
                                this.h = new ArrayList();
                                i |= 16;
                            }
                            this.h.add(Integer.valueOf(c0638Ld.f()));
                        } else if (i2 == 34) {
                            int iB = c0638Ld.b(c0638Ld.f());
                            if ((i & 16) != 16 && c0638Ld.a() > 0) {
                                this.h = new ArrayList();
                                i |= 16;
                            }
                            while (c0638Ld.a() > 0) {
                                this.h.add(Integer.valueOf(c0638Ld.f()));
                            }
                            c0638Ld.h = iB;
                            c0638Ld.j();
                        } else if (i2 == 40) {
                            if ((i & 32) != 32) {
                                this.j = new ArrayList();
                                i |= 32;
                            }
                            this.j.add(Integer.valueOf(c0638Ld.f()));
                        } else if (i2 == 42) {
                            int iB2 = c0638Ld.b(c0638Ld.f());
                            if ((i & 32) != 32 && c0638Ld.a() > 0) {
                                this.j = new ArrayList();
                                i |= 32;
                            }
                            while (c0638Ld.a() > 0) {
                                this.j.add(Integer.valueOf(c0638Ld.f()));
                            }
                            c0638Ld.h = iB2;
                            c0638Ld.j();
                        } else if (i2 == 50) {
                            CL clB = c0638Ld.b();
                            this.c |= 4;
                            this.f = clB;
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
                if ((i & 16) == 16) {
                    this.h = Collections.unmodifiableList(this.h);
                }
                if ((i & 32) == 32) {
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
        if ((i & 16) == 16) {
            this.h = Collections.unmodifiableList(this.h);
        }
        if ((i & 32) == 32) {
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
        T7 t7A;
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
        if ((this.c & 8) == 8) {
            c0767Qd.b(3, this.g.b);
        }
        if (this.h.size() > 0) {
            c0767Qd.g(34);
            c0767Qd.g(this.i);
        }
        for (int i3 = 0; i3 < this.h.size(); i3++) {
            c0767Qd.d(((Integer) this.h.get(i3)).intValue());
        }
        if (this.j.size() > 0) {
            c0767Qd.g(42);
            c0767Qd.g(this.k);
        }
        for (int i4 = 0; i4 < this.j.size(); i4++) {
            c0767Qd.d(((Integer) this.j.get(i4)).intValue());
        }
        if ((this.c & 4) == 4) {
            Object obj = this.f;
            if (obj instanceof String) {
                t7A = T7.a((String) obj);
                this.f = t7A;
            } else {
                t7A = (T7) obj;
            }
            c0767Qd.a(6, t7A);
        }
        c0767Qd.a(this.b);
    }

    @Override // com.android.tools.r8.internal.L0
    public final int c() {
        List list;
        List list2;
        T7 t7A;
        int i = this.m;
        if (i != -1) {
            return i;
        }
        int i2 = 0;
        int iA = (this.c & 1) == 1 ? C0767Qd.a(1, this.d) : 0;
        if ((this.c & 2) == 2) {
            iA += C0767Qd.a(2, this.e);
        }
        if ((this.c & 8) == 8) {
            iA += C0767Qd.a(this.g.b) + C0767Qd.c(3);
        }
        int i3 = 0;
        int i4 = 0;
        while (true) {
            int size = this.h.size();
            list = this.h;
            int iB = 10;
            if (i3 >= size) {
                break;
            }
            int iIntValue = ((Integer) list.get(i3)).intValue();
            if (iIntValue >= 0) {
                iB = C0767Qd.b(iIntValue);
            }
            i4 += iB;
            i3++;
        }
        int iB2 = iA + i4;
        if (!list.isEmpty()) {
            iB2 = iB2 + 1 + (i4 >= 0 ? C0767Qd.b(i4) : 10);
        }
        this.i = i4;
        int iB3 = 0;
        while (true) {
            int size2 = this.j.size();
            list2 = this.j;
            if (i2 >= size2) {
                break;
            }
            int iIntValue2 = ((Integer) list2.get(i2)).intValue();
            iB3 += iIntValue2 >= 0 ? C0767Qd.b(iIntValue2) : 10;
            i2++;
        }
        int size3 = iB2 + iB3;
        if (!list2.isEmpty()) {
            size3 = size3 + 1 + (iB3 >= 0 ? C0767Qd.b(iB3) : 10);
        }
        this.k = iB3;
        if ((this.c & 4) == 4) {
            Object obj = this.f;
            if (obj instanceof String) {
                t7A = T7.a((String) obj);
                this.f = t7A;
            } else {
                t7A = (T7) obj;
            }
            size3 += t7A.size() + C0767Qd.b(t7A.size()) + C0767Qd.c(6);
        }
        int size4 = this.b.size() + size3;
        this.m = size4;
        return size4;
    }

    @Override // com.android.tools.r8.internal.L0
    public final AbstractC0574Ir d() {
        return new C1219cE();
    }

    @Override // com.android.tools.r8.internal.L0
    public final AbstractC0574Ir e() {
        return new C1219cE().a(this);
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
        this.l = (byte) 1;
        return true;
    }

    public C1387eE() {
        this.i = -1;
        this.k = -1;
        this.l = (byte) -1;
        this.m = -1;
        this.b = T7.b;
    }

    public C1387eE(AbstractC0574Ir abstractC0574Ir) {
        super(0);
        this.i = -1;
        this.k = -1;
        this.l = (byte) -1;
        this.m = -1;
        this.b = abstractC0574Ir.b;
    }
}
