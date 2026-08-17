package com.android.tools.r8.internal;

/* JADX INFO: renamed from: com.android.tools.r8.internal.tT, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public abstract class AbstractC2684tT {
    public int b;
    public int c = -1;
    public int d;
    public boolean e;
    public C2514rU f;
    public final /* synthetic */ C2855vT g;

    public AbstractC2684tT(C2855vT c2855vT) {
        this.g = c2855vT;
        this.b = c2855vT.g;
        this.d = c2855vT.i;
        this.e = c2855vT.f;
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
                int iA = AbstractC0938Ws.a(obj.hashCode());
                int i3 = this.g.e;
                while (true) {
                    int i4 = iA & i3;
                    if (obj.equals(objArr[i4])) {
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
        C2855vT c2855vT = this.g;
        int i2 = c2855vT.g;
        if (i == i2) {
            c2855vT.f = false;
            c2855vT.c[i2] = null;
        } else {
            int i3 = this.b;
            if (i3 < 0) {
                c2855vT.c(this.f.set((-i3) - 1, null));
                this.c = -1;
                return;
            }
            Object[] objArr = c2855vT.c;
            loop0: while (true) {
                int i4 = (i + 1) & this.g.e;
                while (true) {
                    obj = objArr[i4];
                    if (obj != null) {
                        int iA = AbstractC0938Ws.a(obj.hashCode());
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
                        this.f = new C2514rU(2);
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
