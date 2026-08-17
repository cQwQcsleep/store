package com.android.tools.r8.internal;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public abstract class T20 {
    public int b;
    public int c = -1;
    public int d;
    public boolean e;
    public M30 f;
    public final /* synthetic */ V20 g;

    public T20(V20 v20) {
        this.g = v20;
        this.b = v20.f;
        this.d = v20.h;
        this.e = v20.e;
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
        Object[] objArr = this.g.b;
        do {
            i = this.b - 1;
            this.b = i;
            if (i < 0) {
                this.c = Integer.MIN_VALUE;
                Object obj = this.f.get((-i) - 1);
                int iA = AbstractC0938Ws.a(System.identityHashCode(obj));
                int i3 = this.g.d;
                while (true) {
                    int i4 = iA & i3;
                    if (obj == objArr[i4]) {
                        return i4;
                    }
                    iA = i4 + 1;
                    i3 = this.g.d;
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
        V20 v20 = this.g;
        int i2 = v20.f;
        if (i == i2) {
            v20.e = false;
            v20.b[i2] = null;
        } else {
            int i3 = this.b;
            if (i3 < 0) {
                v20.b(this.f.set((-i3) - 1, null));
                this.c = -1;
                return;
            }
            Object[] objArr = v20.b;
            loop0: while (true) {
                int i4 = (i + 1) & this.g.d;
                while (true) {
                    obj = objArr[i4];
                    if (obj != null) {
                        int iA = AbstractC0938Ws.a(System.identityHashCode(obj));
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
                        this.f = new M30(2);
                    }
                    this.f.add(objArr[i4]);
                }
                objArr[i] = obj;
                boolean[] zArr = this.g.c;
                zArr[i] = zArr[i4];
                i = i4;
            }
            objArr[i] = null;
        }
        this.g.h--;
        this.c = -1;
    }
}
