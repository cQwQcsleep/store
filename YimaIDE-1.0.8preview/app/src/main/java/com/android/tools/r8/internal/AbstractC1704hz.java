package com.android.tools.r8.internal;

/* JADX INFO: renamed from: com.android.tools.r8.internal.hz, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public abstract class AbstractC1704hz {
    public int c;
    public int e;
    public final /* synthetic */ C1874jz f;
    public int b = -1;
    public int d = -1;

    public AbstractC1704hz(C1874jz c1874jz) {
        this.f = c1874jz;
        this.c = -1;
        this.e = -1;
        this.c = c1874jz.g;
        this.e = 0;
    }

    public final void a() {
        if (this.e >= 0) {
            return;
        }
        if (this.b == -1) {
            this.e = 0;
            return;
        }
        int i = this.c;
        C1874jz c1874jz = this.f;
        if (i == -1) {
            this.e = c1874jz.l;
            return;
        }
        int i2 = c1874jz.g;
        this.e = 1;
        while (i2 != this.b) {
            i2 = (int) this.f.i[i2];
            this.e++;
        }
    }

    public final int b() {
        if (!hasNext()) {
            z0e.a();
            return 0;
        }
        int i = this.c;
        this.d = i;
        this.c = (int) this.f.i[i];
        this.b = i;
        int i2 = this.e;
        if (i2 >= 0) {
            this.e = i2 + 1;
        }
        return i;
    }

    public final int d() {
        if (!hasPrevious()) {
            z0e.a();
            return 0;
        }
        int i = this.b;
        this.d = i;
        this.b = (int) (this.f.i[i] >>> 32);
        this.c = i;
        int i2 = this.e;
        if (i2 >= 0) {
            this.e = i2 - 1;
        }
        return i;
    }

    public final boolean hasNext() {
        return this.c != -1;
    }

    public final boolean hasPrevious() {
        return this.b != -1;
    }

    public final int nextIndex() {
        a();
        return this.e;
    }

    public final int previousIndex() {
        a();
        return this.e - 1;
    }

    public void remove() {
        int i;
        C1874jz c1874jz;
        a();
        int i2 = this.d;
        if (i2 == -1) {
            g33.a();
            return;
        }
        if (i2 == this.b) {
            this.e--;
            this.b = (int) (this.f.i[i2] >>> 32);
        } else {
            this.c = (int) this.f.i[i2];
        }
        C1874jz c1874jz2 = this.f;
        c1874jz2.l--;
        int i3 = this.b;
        if (i3 == -1) {
            c1874jz2.g = this.c;
        } else {
            long[] jArr = c1874jz2.i;
            long j = jArr[i3];
            jArr[i3] = j ^ (((((long) this.c) & 4294967295L) ^ j) & 4294967295L);
        }
        int i4 = this.c;
        if (i4 == -1) {
            c1874jz2.h = i3;
        } else {
            long[] jArr2 = c1874jz2.i;
            long j2 = jArr2[i4];
            jArr2[i4] = ((((((long) i3) & 4294967295L) << 32) ^ j2) & (-4294967296L)) ^ j2;
        }
        this.d = -1;
        int i5 = c1874jz2.j;
        if (i2 == i5) {
            c1874jz2.f = false;
            c1874jz2.d[i5] = null;
            return;
        }
        int[] iArr = c1874jz2.c;
        while (true) {
            int i6 = (i2 + 1) & this.f.e;
            while (true) {
                i = iArr[i6];
                if (i != 0) {
                    int iA = AbstractC0938Ws.a(i);
                    c1874jz = this.f;
                    int i7 = c1874jz.e;
                    int i8 = iA & i7;
                    if (i2 > i6) {
                        if (i2 >= i8 && i8 > i6) {
                            break;
                        } else {
                            i6 = (i6 + 1) & i7;
                        }
                    } else if (i2 >= i8 || i8 > i6) {
                        break;
                    } else {
                        i6 = (i6 + 1) & i7;
                    }
                } else {
                    iArr[i2] = 0;
                    this.f.d[i2] = null;
                    return;
                }
            }
            iArr[i2] = i;
            Object[] objArr = c1874jz.d;
            objArr[i2] = objArr[i6];
            if (this.c == i6) {
                this.c = i2;
            }
            if (this.b == i6) {
                this.b = i2;
            }
            c1874jz.c(i6, i2);
            i2 = i6;
        }
    }
}
