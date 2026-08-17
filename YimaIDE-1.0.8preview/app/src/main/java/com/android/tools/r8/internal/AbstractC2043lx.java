package com.android.tools.r8.internal;

/* JADX INFO: renamed from: com.android.tools.r8.internal.lx, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public abstract class AbstractC2043lx {
    public int c;
    public int e;
    public final /* synthetic */ C2214nx f;
    public int b = -1;
    public int d = -1;

    public AbstractC2043lx(C2214nx c2214nx) {
        this.f = c2214nx;
        this.c = -1;
        this.e = -1;
        this.c = c2214nx.g;
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
        C2214nx c2214nx = this.f;
        if (i == -1) {
            this.e = c2214nx.l;
            return;
        }
        int i2 = c2214nx.g;
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
        C2214nx c2214nx;
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
        C2214nx c2214nx2 = this.f;
        c2214nx2.l--;
        int i3 = this.b;
        if (i3 == -1) {
            c2214nx2.g = this.c;
        } else {
            long[] jArr = c2214nx2.i;
            long j = jArr[i3];
            jArr[i3] = j ^ (((((long) this.c) & 4294967295L) ^ j) & 4294967295L);
        }
        int i4 = this.c;
        if (i4 == -1) {
            c2214nx2.h = i3;
        } else {
            long[] jArr2 = c2214nx2.i;
            long j2 = jArr2[i4];
            jArr2[i4] = ((((((long) i3) & 4294967295L) << 32) ^ j2) & (-4294967296L)) ^ j2;
        }
        this.d = -1;
        if (i2 == c2214nx2.j) {
            c2214nx2.f = false;
            return;
        }
        int[] iArr = c2214nx2.c;
        while (true) {
            int i5 = (i2 + 1) & this.f.e;
            while (true) {
                i = iArr[i5];
                if (i != 0) {
                    int iA = AbstractC0938Ws.a(i);
                    c2214nx = this.f;
                    int i6 = c2214nx.e;
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
                    iArr[i2] = 0;
                    return;
                }
            }
            iArr[i2] = i;
            int[] iArr2 = c2214nx.d;
            iArr2[i2] = iArr2[i5];
            if (this.c == i5) {
                this.c = i2;
            }
            if (this.b == i5) {
                this.b = i2;
            }
            c2214nx.c(i5, i2);
            i2 = i5;
        }
    }
}
