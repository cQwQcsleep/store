package com.android.tools.r8.internal;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public abstract class XL {
    public int b;
    public int c = -1;
    public int d;
    public boolean e;
    public C1143bM f;
    public final /* synthetic */ ZL g;

    public XL(ZL zl) {
        this.g = zl;
        this.b = zl.f;
        this.d = zl.h;
        this.e = zl.e;
    }

    public final int a() {
        int i;
        if (!hasNext()) {
            z0e.a();
            return 0;
        }
        this.d--;
        if (this.e) {
            this.e = false;
            int i2 = this.g.f;
            this.c = i2;
            return i2;
        }
        long[] jArr = this.g.b;
        do {
            i = this.b - 1;
            this.b = i;
            if (i < 0) {
                this.c = Integer.MIN_VALUE;
                long jA = this.f.a((-i) - 1);
                int iA = (int) AbstractC0938Ws.a(jA);
                int i3 = this.g.d;
                while (true) {
                    int i4 = iA & i3;
                    if (jA == jArr[i4]) {
                        return i4;
                    }
                    iA = i4 + 1;
                    i3 = this.g.d;
                }
            }
        } while (jArr[i] == 0);
        this.c = i;
        return i;
    }

    public final boolean hasNext() {
        return this.d != 0;
    }

    public void remove() {
        long j;
        int i = this.c;
        if (i == -1) {
            g33.a();
            return;
        }
        ZL zl = this.g;
        int i2 = zl.f;
        if (i == i2) {
            zl.e = false;
            zl.c[i2] = null;
        } else {
            int i3 = this.b;
            if (i3 < 0) {
                zl.c(this.f.a((-i3) - 1));
                this.c = -1;
                return;
            }
            long[] jArr = zl.b;
            loop0: while (true) {
                int i4 = (i + 1) & this.g.d;
                while (true) {
                    j = jArr[i4];
                    if (j != 0) {
                        int iA = (int) AbstractC0938Ws.a(j);
                        int i5 = this.g.d;
                        int i6 = iA & i5;
                        if (i > i4) {
                            if (i >= i6 && i6 > i4) {
                                break;
                            } else {
                                i4 = (i4 + 1) & i5;
                            }
                        } else if (i >= i6 || i6 > i4) {
                            break;
                        } else {
                            i4 = (i4 + 1) & i5;
                        }
                    } else {
                        break loop0;
                    }
                }
                if (i4 < i) {
                    if (this.f == null) {
                        this.f = new C1143bM(2);
                    }
                    this.f.a(jArr[i4]);
                }
                jArr[i] = j;
                Object[] objArr = this.g.c;
                objArr[i] = objArr[i4];
                i = i4;
            }
            jArr[i] = 0;
            this.g.c[i] = null;
        }
        this.g.h--;
        this.c = -1;
    }
}
