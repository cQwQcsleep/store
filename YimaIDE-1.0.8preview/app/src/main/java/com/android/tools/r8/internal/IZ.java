package com.android.tools.r8.internal;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class IZ extends AbstractC0729Or {
    public static final IZ f;
    public static final GZ g = new GZ();
    public final T7 b;
    public List c;
    public byte d;
    public int e;

    static {
        IZ iz = new IZ();
        f = iz;
        iz.c = Collections.EMPTY_LIST;
    }

    public IZ(C0638Ld c0638Ld, C0389Bo c0389Bo) {
        this.d = (byte) -1;
        this.e = -1;
        this.c = Collections.EMPTY_LIST;
        R7 r7 = new R7();
        C0767Qd c0767Qd = new C0767Qd(r7, new byte[1]);
        boolean z = false;
        boolean z2 = false;
        while (!z) {
            try {
                try {
                    int i = c0638Ld.i();
                    if (i != 0) {
                        if (i == 10) {
                            if (!z2) {
                                this.c = new ArrayList();
                                z2 = true;
                            }
                            this.c.add(c0638Ld.a(NZ.k, c0389Bo));
                        } else if (!c0638Ld.a(i, c0767Qd)) {
                        }
                    }
                    z = true;
                } catch (Throwable th) {
                    if (z2) {
                        this.c = Collections.unmodifiableList(this.c);
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
        if (z2) {
            this.c = Collections.unmodifiableList(this.c);
        }
        try {
            c0767Qd.a();
        } catch (IOException unused2) {
        } finally {
            this.b = r7.c();
        }
    }

    @Override // com.android.tools.r8.internal.UN
    public final boolean a() {
        byte b = this.d;
        if (b == 1) {
            return true;
        }
        if (b == 0) {
            return false;
        }
        for (int i = 0; i < this.c.size(); i++) {
            if (!((NZ) this.c.get(i)).a()) {
                this.d = (byte) 0;
                return false;
            }
        }
        this.d = (byte) 1;
        return true;
    }

    @Override // com.android.tools.r8.internal.L0
    public final int c() {
        int i = this.e;
        if (i != -1) {
            return i;
        }
        int iA = 0;
        for (int i2 = 0; i2 < this.c.size(); i2++) {
            iA += C0767Qd.a(1, (L0) this.c.get(i2));
        }
        int size = this.b.size() + iA;
        this.e = size;
        return size;
    }

    @Override // com.android.tools.r8.internal.L0
    public final AbstractC0574Ir d() {
        return new HZ();
    }

    @Override // com.android.tools.r8.internal.L0
    public final AbstractC0574Ir e() {
        return new HZ().a(this);
    }

    @Override // com.android.tools.r8.internal.L0
    public final void a(C0767Qd c0767Qd) throws IOException {
        c();
        for (int i = 0; i < this.c.size(); i++) {
            c0767Qd.b(1, (L0) this.c.get(i));
        }
        c0767Qd.a(this.b);
    }

    public IZ() {
        this.d = (byte) -1;
        this.e = -1;
        this.b = T7.b;
    }

    public IZ(AbstractC0574Ir abstractC0574Ir) {
        super(0);
        this.d = (byte) -1;
        this.e = -1;
        this.b = abstractC0574Ir.b;
    }
}
