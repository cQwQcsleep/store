package com.android.tools.r8.internal;

/* JADX INFO: renamed from: com.android.tools.r8.internal.p30, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public abstract class AbstractC2311p30 {
    public int b;
    public int c = -1;
    public int d;
    public boolean e;
    public M30 f;
    public final /* synthetic */ C2481r30 g;

    public AbstractC2311p30(C2481r30 c2481r30) {
        this.g = c2481r30;
        this.b = c2481r30.g;
        this.d = c2481r30.i;
        this.e = c2481r30.f;
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
        Object[] objArr = this.g.c;
        do {
            i = this.b - 1;
            this.b = i;
            if (i < 0) {
                this.c = Integer.MIN_VALUE;
                Object obj = this.f.get((-i) - 1);
                int iA = AbstractC0938Ws.a(System.identityHashCode(obj));
                int i3 = this.g.e;
                while (true) {
                    int i4 = iA & i3;
                    if (obj == objArr[i4]) {
                        return i4;
                    }
                    iA = i4 + 1;
                    i3 = this.g.e;
                }
            }
        } while (objArr[i] == null);
        this.c = i;
        return i;
    }

    public final boolean hasNext() {
        return this.d != 0;
    }

    public void remove() {
        Object obj;
        int i = this.c;
        if (i == -1) {
            g33.a();
            return;
        }
        C2481r30 c2481r30 = this.g;
        int i2 = c2481r30.g;
        if (i == i2) {
            c2481r30.f = false;
            c2481r30.c[i2] = null;
        } else {
            int i3 = this.b;
            if (i3 < 0) {
                c2481r30.c(this.f.set((-i3) - 1, null));
                this.c = -1;
                return;
            }
            Object[] objArr = c2481r30.c;
            loop0: while (true) {
                int i4 = (i + 1) & this.g.e;
                while (true) {
                    obj = objArr[i4];
                    if (obj != null) {
                        int iA = AbstractC0938Ws.a(System.identityHashCode(obj));
                        int i5 = this.g.e;
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
                        this.f = new M30(2);
                    }
                    this.f.add(objArr[i4]);
                }
                objArr[i] = obj;
                int[] iArr = this.g.d;
                iArr[i] = iArr[i4];
                i = i4;
            }
            objArr[i] = null;
        }
        this.g.i--;
        this.c = -1;
    }
}
