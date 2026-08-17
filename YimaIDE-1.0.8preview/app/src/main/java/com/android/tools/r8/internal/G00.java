package com.android.tools.r8.internal;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class G00 extends AbstractC0729Or {
    public static final G00 h;
    public static final E00 i = new E00();
    public final T7 b;
    public int c;
    public List d;
    public int e;
    public byte f;
    public int g;

    static {
        G00 g00 = new G00();
        h = g00;
        g00.d = Collections.EMPTY_LIST;
        g00.e = -1;
    }

    public G00(C0638Ld c0638Ld, C0389Bo c0389Bo) {
        this.f = (byte) -1;
        this.g = -1;
        this.d = Collections.EMPTY_LIST;
        this.e = -1;
        R7 r7 = new R7();
        C0767Qd c0767Qd = new C0767Qd(r7, new byte[1]);
        boolean z = false;
        boolean z2 = false;
        while (!z) {
            try {
                try {
                    try {
                        int i2 = c0638Ld.i();
                        if (i2 != 0) {
                            if (i2 == 10) {
                                if (!z2) {
                                    this.d = new ArrayList();
                                    z2 = true;
                                }
                                this.d.add(c0638Ld.a(C2903w00.v, c0389Bo));
                            } else if (i2 == 16) {
                                this.c |= 1;
                                this.e = c0638Ld.f();
                            } else if (!c0638Ld.a(i2, c0767Qd)) {
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
                if (z2) {
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
        }
        if (z2) {
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
        for (int i2 = 0; i2 < this.d.size(); i2++) {
            c0767Qd.b(1, (L0) this.d.get(i2));
        }
        if ((this.c & 1) == 1) {
            int i3 = this.e;
            c0767Qd.c(2, 0);
            c0767Qd.d(i3);
        }
        c0767Qd.a(this.b);
    }

    @Override // com.android.tools.r8.internal.L0
    public final int c() {
        int i2 = this.g;
        if (i2 != -1) {
            return i2;
        }
        int iA = 0;
        for (int i3 = 0; i3 < this.d.size(); i3++) {
            iA += C0767Qd.a(1, (L0) this.d.get(i3));
        }
        if ((this.c & 1) == 1) {
            iA += C0767Qd.a(2, this.e);
        }
        int size = this.b.size() + iA;
        this.g = size;
        return size;
    }

    @Override // com.android.tools.r8.internal.L0
    public final AbstractC0574Ir d() {
        return new F00();
    }

    @Override // com.android.tools.r8.internal.L0
    public final AbstractC0574Ir e() {
        return a(this);
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
        for (int i2 = 0; i2 < this.d.size(); i2++) {
            if (!((C2903w00) this.d.get(i2)).a()) {
                this.f = (byte) 0;
                return false;
            }
        }
        this.f = (byte) 1;
        return true;
    }

    public static F00 a(G00 g00) {
        return new F00().a(g00);
    }

    public G00() {
        this.f = (byte) -1;
        this.g = -1;
        this.b = T7.b;
    }

    public G00(AbstractC0574Ir abstractC0574Ir) {
        super(0);
        this.f = (byte) -1;
        this.g = -1;
        this.b = abstractC0574Ir.b;
    }
}
