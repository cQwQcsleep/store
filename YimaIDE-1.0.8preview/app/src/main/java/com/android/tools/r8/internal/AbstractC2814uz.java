package com.android.tools.r8.internal;

/* JADX INFO: renamed from: com.android.tools.r8.internal.uz, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public abstract class AbstractC2814uz {
    public int b;
    public int c = -1;
    public int d;
    public boolean e;
    public C0919Vz f;
    public final /* synthetic */ C2986wz g;

    public AbstractC2814uz(C2986wz c2986wz) {
        this.g = c2986wz;
        this.b = c2986wz.g;
        this.d = c2986wz.i;
        this.e = c2986wz.f;
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
        C2986wz c2986wz = this.g;
        int i3 = c2986wz.g;
        if (i2 == i3) {
            c2986wz.f = false;
            c2986wz.d[i3] = null;
        } else {
            int i4 = this.b;
            if (i4 < 0) {
                c2986wz.remove(this.f.i((-i4) - 1));
                this.c = -1;
                return;
            }
            int[] iArr = c2986wz.c;
            loop0: while (true) {
                int i5 = (i2 + 1) & this.g.e;
                while (true) {
                    i = iArr[i5];
                    if (i != 0) {
                        int iA = AbstractC0938Ws.a(i);
                        int i6 = this.g.e;
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
                Object[] objArr = this.g.d;
                objArr[i2] = objArr[i5];
                i2 = i5;
            }
            iArr[i2] = 0;
            this.g.d[i2] = null;
        }
        this.g.i--;
        this.c = -1;
    }
}
