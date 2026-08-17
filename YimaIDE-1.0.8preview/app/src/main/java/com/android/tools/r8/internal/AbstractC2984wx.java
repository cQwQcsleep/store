package com.android.tools.r8.internal;

/* JADX INFO: renamed from: com.android.tools.r8.internal.wx, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public abstract class AbstractC2984wx {
    public int b;
    public int c = -1;
    public int d;
    public boolean e;
    public C0919Vz f;
    public final /* synthetic */ C3153yx g;

    public AbstractC2984wx(C3153yx c3153yx) {
        this.g = c3153yx;
        this.b = c3153yx.g;
        this.d = c3153yx.i;
        this.e = c3153yx.f;
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
            int i2 = this.g.g;
            this.c = i2;
            return i2;
        }
        int[] iArr = this.g.c;
        do {
            i = this.b - 1;
            this.b = i;
            if (i < 0) {
                this.c = Integer.MIN_VALUE;
                int i3 = this.f.i((-i) - 1);
                int iA = AbstractC0938Ws.a(i3);
                int i4 = this.g.e;
                while (true) {
                    int i5 = iA & i4;
                    if (i3 == iArr[i5]) {
                        return i5;
                    }
                    iA = i5 + 1;
                    i4 = this.g.e;
                }
            }
        } while (iArr[i] == 0);
        this.c = i;
        return i;
    }

    public final boolean hasNext() {
        return this.d != 0;
    }

    public void remove() {
        int i;
        int i2 = this.c;
        if (i2 == -1) {
            g33.a();
            return;
        }
        C3153yx c3153yx = this.g;
        if (i2 == c3153yx.g) {
            c3153yx.f = false;
        } else {
            int i3 = this.b;
            if (i3 < 0) {
                c3153yx.remove(this.f.i((-i3) - 1));
                this.c = -1;
                return;
            }
            int[] iArr = c3153yx.c;
            loop0: while (true) {
                int i4 = (i2 + 1) & this.g.e;
                while (true) {
                    i = iArr[i4];
                    if (i != 0) {
                        int iA = AbstractC0938Ws.a(i);
                        int i5 = this.g.e;
                        int i6 = iA & i5;
                        if (i2 > i4) {
                            if (i2 >= i6 && i6 > i4) {
                                break;
                            } else {
                                i4 = (i4 + 1) & i5;
                            }
                        } else if (i2 >= i6 || i6 > i4) {
                            break;
                        } else {
                            i4 = (i4 + 1) & i5;
                        }
                    } else {
                        break loop0;
                    }
                }
                if (i4 < i2) {
                    if (this.f == null) {
                        this.f = new C0919Vz(2);
                    }
                    this.f.add(iArr[i4]);
                }
                iArr[i2] = i;
                int[] iArr2 = this.g.d;
                iArr2[i2] = iArr2[i4];
                i2 = i4;
            }
            iArr[i2] = 0;
        }
        this.g.i--;
        this.c = -1;
    }
}
