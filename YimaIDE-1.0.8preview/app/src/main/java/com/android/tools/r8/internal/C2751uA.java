package com.android.tools.r8.internal;

/* JADX INFO: renamed from: com.android.tools.r8.internal.uA, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C2751uA extends W {
    public int b;
    public int c = -1;
    public int d;
    public boolean e;
    public C0919Vz f;
    public final /* synthetic */ C2836vA g;

    public C2751uA(C2836vA c2836vA) {
        this.g = c2836vA;
        this.b = c2836vA.e;
        this.d = c2836vA.g;
        this.e = c2836vA.d;
    }

    @Override // java.util.Iterator
    public final boolean hasNext() {
        return this.d != 0;
    }

    @Override // com.android.tools.r8.internal.InterfaceC1640hA
    public final int q() {
        int i;
        int i2;
        if (!hasNext()) {
            z0e.a();
            return 0;
        }
        this.d--;
        if (this.e) {
            this.e = false;
            C2836vA c2836vA = this.g;
            int i3 = c2836vA.e;
            this.c = i3;
            return c2836vA.b[i3];
        }
        int[] iArr = this.g.b;
        do {
            i = this.b - 1;
            this.b = i;
            if (i < 0) {
                this.c = Integer.MIN_VALUE;
                return this.f.i((-i) - 1);
            }
            i2 = iArr[i];
        } while (i2 == 0);
        this.c = i;
        return i2;
    }

    @Override // com.android.tools.r8.internal.W, java.util.Iterator, java.util.ListIterator
    public final void remove() {
        int i;
        int i2 = this.c;
        if (i2 == -1) {
            g33.a();
            return;
        }
        C2836vA c2836vA = this.g;
        int i3 = c2836vA.e;
        if (i2 == i3) {
            c2836vA.d = false;
            c2836vA.b[i3] = 0;
        } else {
            int i4 = this.b;
            if (i4 < 0) {
                c2836vA.k(this.f.i((-i4) - 1));
                this.c = -1;
                return;
            }
            int[] iArr = c2836vA.b;
            loop0: while (true) {
                int i5 = (i2 + 1) & this.g.c;
                while (true) {
                    i = iArr[i5];
                    if (i != 0) {
                        int iA = AbstractC0938Ws.a(i);
                        int i6 = this.g.c;
                        int i7 = iA & i6;
                        if (i2 > i5) {
                            if (i2 >= i7 && i7 > i5) {
                                break;
                            } else {
                                i5 = (i5 + 1) & i6;
                            }
                        } else if (i2 >= i7 || i7 > i5) {
                            break;
                        } else {
                            i5 = (i5 + 1) & i6;
                        }
                    } else {
                        break loop0;
                    }
                }
                if (i5 < i2) {
                    if (this.f == null) {
                        this.f = new C0919Vz(2);
                    }
                    this.f.add(iArr[i5]);
                }
                iArr[i2] = i;
                i2 = i5;
            }
            iArr[i2] = 0;
        }
        this.g.g--;
        this.c = -1;
    }
}
