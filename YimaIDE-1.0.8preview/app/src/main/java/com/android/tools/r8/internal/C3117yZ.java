package com.android.tools.r8.internal;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/* JADX INFO: renamed from: com.android.tools.r8.internal.yZ, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C3117yZ extends AbstractC0729Or {
    public static final C3117yZ h;
    public static final C2349pZ i = new C2349pZ();
    public final T7 b;
    public int c;
    public int d;
    public List e;
    public byte f;
    public int g;

    static {
        C3117yZ c3117yZ = new C3117yZ();
        h = c3117yZ;
        c3117yZ.d = 0;
        c3117yZ.e = Collections.EMPTY_LIST;
    }

    public C3117yZ(C0638Ld c0638Ld, C0389Bo c0389Bo) {
        this.f = (byte) -1;
        this.g = -1;
        boolean z = false;
        this.d = 0;
        this.e = Collections.EMPTY_LIST;
        R7 r7 = new R7();
        C0767Qd c0767Qd = new C0767Qd(r7, new byte[1]);
        char c = 0;
        while (!z) {
            try {
                try {
                    int i2 = c0638Ld.i();
                    if (i2 != 0) {
                        if (i2 == 8) {
                            this.c |= 1;
                            this.d = c0638Ld.f();
                        } else if (i2 == 18) {
                            if ((c & 2) != 2) {
                                this.e = new ArrayList();
                                c = 2;
                            }
                            this.e.add(c0638Ld.a(C2947wZ.i, c0389Bo));
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
                if ((c & 2) == 2) {
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
        }
        if ((c & 2) == 2) {
            this.e = Collections.unmodifiableList(this.e);
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
        byte b = this.f;
        if (b == 1) {
            return true;
        }
        if (b == 0) {
            return false;
        }
        if ((this.c & 1) != 1) {
            this.f = (byte) 0;
            return false;
        }
        for (int i2 = 0; i2 < this.e.size(); i2++) {
            if (!((C2947wZ) this.e.get(i2)).a()) {
                this.f = (byte) 0;
                return false;
            }
        }
        this.f = (byte) 1;
        return true;
    }

    @Override // com.android.tools.r8.internal.L0
    public final int c() {
        int i2 = this.g;
        if (i2 != -1) {
            return i2;
        }
        int iA = (this.c & 1) == 1 ? C0767Qd.a(1, this.d) : 0;
        for (int i3 = 0; i3 < this.e.size(); i3++) {
            iA += C0767Qd.a(2, (L0) this.e.get(i3));
        }
        int size = this.b.size() + iA;
        this.g = size;
        return size;
    }

    @Override // com.android.tools.r8.internal.L0
    public final AbstractC0574Ir d() {
        return new C3033xZ();
    }

    @Override // com.android.tools.r8.internal.L0
    public final AbstractC0574Ir e() {
        return new C3033xZ().a(this);
    }

    @Override // com.android.tools.r8.internal.L0
    public final void a(C0767Qd c0767Qd) throws IOException {
        c();
        if ((this.c & 1) == 1) {
            int i2 = this.d;
            c0767Qd.c(1, 0);
            c0767Qd.d(i2);
        }
        for (int i3 = 0; i3 < this.e.size(); i3++) {
            c0767Qd.b(2, (L0) this.e.get(i3));
        }
        c0767Qd.a(this.b);
    }

    public C3117yZ() {
        this.f = (byte) -1;
        this.g = -1;
        this.b = T7.b;
    }

    public C3117yZ(AbstractC0574Ir abstractC0574Ir) {
        super(0);
        this.f = (byte) -1;
        this.g = -1;
        this.b = abstractC0574Ir.b;
    }
}
