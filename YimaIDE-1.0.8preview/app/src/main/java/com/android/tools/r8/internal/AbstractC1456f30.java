package com.android.tools.r8.internal;

/* JADX INFO: renamed from: com.android.tools.r8.internal.f30, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public abstract class AbstractC1456f30 {
    public int c;
    public int e;
    public final /* synthetic */ C1627h30 f;
    public int b = -1;
    public int d = -1;

    public AbstractC1456f30(C1627h30 c1627h30) {
        this.f = c1627h30;
        this.c = -1;
        this.e = -1;
        this.c = c1627h30.g;
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
        C1627h30 c1627h30 = this.f;
        if (i == -1) {
            this.e = c1627h30.l;
            return;
        }
        int i2 = c1627h30.g;
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
        Object obj;
        C1627h30 c1627h30;
        a();
        int i = this.d;
        if (i == -1) {
            g33.a();
            return;
        }
        if (i == this.b) {
            this.e--;
            this.b = (int) (this.f.i[i] >>> 32);
        } else {
            this.c = (int) this.f.i[i];
        }
        C1627h30 c1627h31 = this.f;
        c1627h31.l--;
        int i2 = this.b;
        if (i2 == -1) {
            c1627h31.g = this.c;
        } else {
            long[] jArr = c1627h31.i;
            long j = jArr[i2];
            jArr[i2] = j ^ (((((long) this.c) & 4294967295L) ^ j) & 4294967295L);
        }
        int i3 = this.c;
        if (i3 == -1) {
            c1627h31.h = i2;
        } else {
            long[] jArr2 = c1627h31.i;
            long j2 = jArr2[i3];
            jArr2[i3] = ((((((long) i2) & 4294967295L) << 32) ^ j2) & (-4294967296L)) ^ j2;
        }
        this.d = -1;
        int i4 = c1627h31.j;
        if (i == i4) {
            c1627h31.f = false;
            c1627h31.c[i4] = null;
            return;
        }
        Object[] objArr = c1627h31.c;
        while (true) {
            int i5 = (i + 1) & this.f.e;
            while (true) {
                obj = objArr[i5];
                if (obj != null) {
                    int iA = AbstractC0938Ws.a(System.identityHashCode(obj));
                    c1627h30 = this.f;
                    int i6 = c1627h30.e;
                    int i7 = iA & i6;
                    if (i > i5) {
                        if (i >= i7 && i7 > i5) {
                            break;
                        } else {
                            i5 = (i5 + 1) & i6;
                        }
                    } else if (i >= i7 || i7 > i5) {
                        break;
                    } else {
                        i5 = (i5 + 1) & i6;
                    }
                } else {
                    objArr[i] = null;
                    return;
                }
            }
            objArr[i] = obj;
            int[] iArr = c1627h30.d;
            iArr[i] = iArr[i5];
            if (this.c == i5) {
                this.c = i;
            }
            if (this.b == i5) {
                this.b = i;
            }
            c1627h30.c(i5, i);
            i = i5;
        }
    }
}
