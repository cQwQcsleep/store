package com.android.tools.r8.internal;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/* JADX INFO: renamed from: com.android.tools.r8.internal.fE, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C1473fE extends AbstractC0729Or {
    public static final C1473fE h;
    public static final ZD i = new ZD();
    public final T7 b;
    public List c;
    public List d;
    public int e;
    public byte f;
    public int g;

    static {
        C1473fE c1473fE = new C1473fE();
        h = c1473fE;
        List list = Collections.EMPTY_LIST;
        c1473fE.c = list;
        c1473fE.d = list;
    }

    public C1473fE(C0638Ld c0638Ld, C0389Bo c0389Bo) {
        this.e = -1;
        this.f = (byte) -1;
        this.g = -1;
        List list = Collections.EMPTY_LIST;
        this.c = list;
        this.d = list;
        R7 r7 = new R7();
        C0767Qd c0767Qd = new C0767Qd(r7, new byte[1]);
        boolean z = false;
        int i2 = 0;
        while (!z) {
            try {
                try {
                    int i3 = c0638Ld.i();
                    if (i3 != 0) {
                        if (i3 == 10) {
                            if ((i2 & 1) != 1) {
                                this.c = new ArrayList();
                                i2 |= 1;
                            }
                            this.c.add(c0638Ld.a(C1387eE.o, c0389Bo));
                        } else if (i3 == 40) {
                            if ((i2 & 2) != 2) {
                                this.d = new ArrayList();
                                i2 |= 2;
                            }
                            this.d.add(Integer.valueOf(c0638Ld.f()));
                        } else if (i3 == 42) {
                            int iB = c0638Ld.b(c0638Ld.f());
                            if ((i2 & 2) != 2 && c0638Ld.a() > 0) {
                                this.d = new ArrayList();
                                i2 |= 2;
                            }
                            while (c0638Ld.a() > 0) {
                                this.d.add(Integer.valueOf(c0638Ld.f()));
                            }
                            c0638Ld.h = iB;
                            c0638Ld.j();
                        } else if (!c0638Ld.a(i3, c0767Qd)) {
                        }
                    }
                    z = true;
                } catch (Throwable th) {
                    if ((i2 & 1) == 1) {
                        this.c = Collections.unmodifiableList(this.c);
                    }
                    if ((i2 & 2) == 2) {
                        this.d = Collections.unmodifiableList(this.d);
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
        if ((i2 & 1) == 1) {
            this.c = Collections.unmodifiableList(this.c);
        }
        if ((i2 & 2) == 2) {
            this.d = Collections.unmodifiableList(this.d);
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
        for (int i2 = 0; i2 < this.c.size(); i2++) {
            c0767Qd.b(1, (L0) this.c.get(i2));
        }
        if (this.d.size() > 0) {
            c0767Qd.g(42);
            c0767Qd.g(this.e);
        }
        for (int i3 = 0; i3 < this.d.size(); i3++) {
            c0767Qd.d(((Integer) this.d.get(i3)).intValue());
        }
        c0767Qd.a(this.b);
    }

    @Override // com.android.tools.r8.internal.L0
    public final int c() {
        List list;
        int i2 = this.g;
        if (i2 != -1) {
            return i2;
        }
        int i3 = 0;
        int iA = 0;
        for (int i4 = 0; i4 < this.c.size(); i4++) {
            iA += C0767Qd.a(1, (L0) this.c.get(i4));
        }
        int i5 = 0;
        while (true) {
            int size = this.d.size();
            list = this.d;
            int iB = 10;
            if (i3 >= size) {
                break;
            }
            int iIntValue = ((Integer) list.get(i3)).intValue();
            if (iIntValue >= 0) {
                iB = C0767Qd.b(iIntValue);
            }
            i5 += iB;
            i3++;
        }
        int iB2 = iA + i5;
        if (!list.isEmpty()) {
            iB2 = iB2 + 1 + (i5 >= 0 ? C0767Qd.b(i5) : 10);
        }
        this.e = i5;
        int size2 = this.b.size() + iB2;
        this.g = size2;
        return size2;
    }

    @Override // com.android.tools.r8.internal.L0
    public final AbstractC0574Ir d() {
        return new C1049aE();
    }

    @Override // com.android.tools.r8.internal.L0
    public final AbstractC0574Ir e() {
        return new C1049aE().a(this);
    }

    @Override // com.android.tools.r8.internal.UN
    public final boolean a() {
        byte b = this.f;
        if (b == 1) {
            return true;
        }
        if (b == 0) {
            return false;
        }
        this.f = (byte) 1;
        return true;
    }

    public C1473fE() {
        this.e = -1;
        this.f = (byte) -1;
        this.g = -1;
        this.b = T7.b;
    }

    public C1473fE(AbstractC0574Ir abstractC0574Ir) {
        super(0);
        this.e = -1;
        this.f = (byte) -1;
        this.g = -1;
        this.b = abstractC0574Ir.b;
    }
}
