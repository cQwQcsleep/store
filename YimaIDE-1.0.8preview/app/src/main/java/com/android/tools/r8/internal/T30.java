package com.android.tools.r8.internal;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class T30 extends Y0 {
    public int b;
    public int c = -1;
    public int d;
    public boolean e;
    public M30 f;
    public final /* synthetic */ U30 g;

    public T30(U30 u30) {
        this.g = u30;
        this.b = u30.e;
        this.d = u30.g;
        this.e = u30.d;
    }

    @Override // java.util.Iterator
    public final boolean hasNext() {
        return this.d != 0;
    }

    @Override // java.util.Iterator
    public final Object next() {
        int i;
        Object obj;
        if (!hasNext()) {
            z0e.a();
            return null;
        }
        this.d--;
        if (this.e) {
            this.e = false;
            U30 u30 = this.g;
            int i2 = u30.e;
            this.c = i2;
            return u30.b[i2];
        }
        Object[] objArr = this.g.b;
        do {
            i = this.b - 1;
            this.b = i;
            if (i < 0) {
                this.c = Integer.MIN_VALUE;
                return this.f.get((-i) - 1);
            }
            obj = objArr[i];
        } while (obj == null);
        this.c = i;
        return obj;
    }

    @Override // com.android.tools.r8.internal.Y0, java.util.Iterator, java.util.ListIterator
    public final void remove() {
        Object obj;
        int i = this.c;
        if (i == -1) {
            g33.a();
            return;
        }
        U30 u30 = this.g;
        int i2 = u30.e;
        if (i == i2) {
            u30.d = false;
            u30.b[i2] = null;
        } else {
            int i3 = this.b;
            if (i3 < 0) {
                u30.remove(this.f.set((-i3) - 1, null));
                this.c = -1;
                return;
            }
            Object[] objArr = u30.b;
            loop0: while (true) {
                int i4 = (i + 1) & this.g.c;
                while (true) {
                    obj = objArr[i4];
                    if (obj != null) {
                        int iA = AbstractC0938Ws.a(System.identityHashCode(obj));
                        int i5 = this.g.c;
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
                i = i4;
            }
            objArr[i] = null;
        }
        this.g.g--;
        this.c = -1;
    }
}
