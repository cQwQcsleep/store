package com.android.tools.r8.internal;

import java.util.Arrays;
import java.util.Collection;
import java.util.Map;
import java.util.Set;

/* JADX INFO: renamed from: com.android.tools.r8.internal.yx, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C3153yx extends J implements Cloneable {
    public transient int[] c;
    public transient int[] d;
    public transient int e;
    public transient boolean f;
    public transient int g;
    public transient int h;
    public int i;
    public transient C2898vx j;
    public transient C2727tx k;
    public transient C2471qx l;

    public C3153yx(int i) {
        if (i < 0) {
            w01.a("The expected number of elements must be nonnegative");
            throw null;
        }
        int iA = AbstractC0938Ws.a(i, 0.75f);
        this.g = iA;
        this.e = iA - 1;
        this.h = AbstractC0938Ws.b(iA, 0.75f);
        int i2 = this.g + 1;
        this.c = new int[i2];
        this.d = new int[i2];
    }

    @Override // com.android.tools.r8.internal.InterfaceC1445ex
    public final boolean a(int i) {
        int i2;
        if (i == 0) {
            return this.f;
        }
        int[] iArr = this.c;
        int iA = AbstractC0938Ws.a(i) & this.e;
        int i3 = iArr[iA];
        if (i3 == 0) {
            return false;
        }
        if (i == i3) {
            return true;
        }
        do {
            iA = (iA + 1) & this.e;
            i2 = iArr[iA];
            if (i2 == 0) {
                return false;
            }
        } while (i != i2);
        return true;
    }

    /* JADX WARN: Code duplicated, block: B:19:0x003e  */
    @Override // com.android.tools.r8.internal.J, com.android.tools.r8.internal.InterfaceC1445ex
    public final int b(int i, int i2) {
        int i3;
        int i4;
        int i5;
        if (i != 0) {
            int[] iArr = this.c;
            int iA = AbstractC0938Ws.a(i) & this.e;
            int i6 = iArr[iA];
            if (i6 != 0) {
                if (i6 != i) {
                    while (true) {
                        iA = (iA + 1) & this.e;
                        int i7 = iArr[iA];
                        if (i7 != 0) {
                            if (i7 == i) {
                            }
                        }
                    }
                }
                i4 = iA;
            }
            i3 = iA;
            this.c[i3] = i;
            this.d[i3] = i2;
            i5 = this.i;
            this.i = i5 + 1;
            if (i5 >= this.h) {
                e(AbstractC0938Ws.a(i5 + 2, 0.75f));
            }
            i4 = -1;
        } else if (this.f) {
            i4 = this.g;
        } else {
            this.f = true;
            i3 = this.g;
            this.c[i3] = i;
            this.d[i3] = i2;
            i5 = this.i;
            this.i = i5 + 1;
            if (i5 >= this.h) {
                e(AbstractC0938Ws.a(i5 + 2, 0.75f));
            }
            i4 = -1;
        }
        if (i4 < 0) {
            return this.b;
        }
        int[] iArr2 = this.d;
        int i8 = iArr2[i4];
        iArr2[i4] = i2;
        return i8;
    }

    @Override // java.util.Map
    public final void clear() {
        if (this.i == 0) {
            return;
        }
        this.i = 0;
        this.f = false;
        Arrays.fill(this.c, 0);
    }

    public final Object clone() {
        try {
            C3153yx c3153yx = (C3153yx) super.clone();
            c3153yx.k = null;
            c3153yx.l = null;
            c3153yx.j = null;
            c3153yx.f = this.f;
            c3153yx.c = (int[]) this.c.clone();
            c3153yx.d = (int[]) this.d.clone();
            return c3153yx;
        } catch (CloneNotSupportedException unused) {
            uj0.a();
            return null;
        }
    }

    @Override // com.android.tools.r8.internal.J
    public final boolean d(int i) {
        int[] iArr = this.d;
        int[] iArr2 = this.c;
        if (this.f && iArr[this.g] == i) {
            return true;
        }
        int i2 = this.g;
        while (true) {
            int i3 = i2 - 1;
            if (i2 == 0) {
                return false;
            }
            if (iArr2[i3] != 0 && iArr[i3] == i) {
                return true;
            }
            i2 = i3;
        }
    }

    public final void e(int i) {
        int i2;
        int[] iArr = this.c;
        int[] iArr2 = this.d;
        int i3 = i - 1;
        int i4 = i + 1;
        int[] iArr3 = new int[i4];
        int[] iArr4 = new int[i4];
        int i5 = this.g;
        boolean z = this.f;
        int i6 = this.i;
        if (z) {
            i6--;
        }
        while (true) {
            int i7 = i6 - 1;
            if (i6 == 0) {
                iArr4[i] = iArr2[this.g];
                this.g = i;
                this.e = i3;
                this.h = AbstractC0938Ws.b(i, 0.75f);
                this.c = iArr3;
                this.d = iArr4;
                return;
            }
            do {
                i5--;
                i2 = iArr[i5];
            } while (i2 == 0);
            int iA = AbstractC0938Ws.a(i2) & i3;
            if (iArr3[iA] != 0) {
                do {
                    iA = (iA + 1) & i3;
                } while (iArr3[iA] != 0);
            }
            iArr3[iA] = iArr[i5];
            iArr4[iA] = iArr2[i5];
            i6 = i7;
        }
    }

    public final int f(int i) {
        int i2;
        int i3;
        int i4 = this.d[i];
        this.i--;
        int[] iArr = this.c;
        loop0: while (true) {
            int i5 = (i + 1) & this.e;
            while (true) {
                i2 = iArr[i5];
                if (i2 != 0) {
                    int iA = AbstractC0938Ws.a(i2);
                    int i6 = this.e;
                    int i7 = iA & i6;
                    if (i > i5) {
                        if (i >= i7 && i7 > i5) {
                            break;
                        }
                        i5 = (i5 + 1) & i6;
                    } else {
                        if (i >= i7 || i7 > i5) {
                            break;
                        }
                        i5 = (i5 + 1) & i6;
                    }
                } else {
                    break loop0;
                }
            }
            iArr[i] = i2;
            int[] iArr2 = this.d;
            iArr2[i] = iArr2[i5];
            i = i5;
        }
        iArr[i] = 0;
        if (this.i < this.h / 4 && (i3 = this.g) > 16) {
            e(i3 / 2);
        }
        return i4;
    }

    @Override // com.android.tools.r8.internal.InterfaceC1445ex
    public final int get(int i) {
        int i2;
        if (i == 0) {
            return this.f ? this.d[this.g] : this.b;
        }
        int[] iArr = this.c;
        int iA = AbstractC0938Ws.a(i) & this.e;
        int i3 = iArr[iA];
        if (i3 == 0) {
            return this.b;
        }
        if (i == i3) {
            return this.d[iA];
        }
        do {
            iA = (iA + 1) & this.e;
            i2 = iArr[iA];
            if (i2 == 0) {
                return this.b;
            }
        } while (i != i2);
        return this.d[iA];
    }

    @Override // com.android.tools.r8.internal.InterfaceC2386px
    public final JU h() {
        if (this.j == null) {
            this.j = new C2898vx(this);
        }
        return this.j;
    }

    @Override // com.android.tools.r8.internal.J, java.util.Map
    public final int hashCode() {
        int i;
        boolean z = this.f;
        int i2 = this.i;
        if (z) {
            i2--;
        }
        int i3 = 0;
        int i4 = i2;
        int i5 = 0;
        while (true) {
            int i6 = i4 - 1;
            if (i4 == 0) {
                break;
            }
            while (true) {
                i = this.c[i5];
                if (i == 0) {
                    i5++;
                }
            }
            i3 += i ^ this.d[i5];
            i5++;
            i4 = i6;
        }
        return this.f ? i3 + this.d[this.g] : i3;
    }

    @Override // java.util.Map
    public final boolean isEmpty() {
        return this.i == 0;
    }

    @Override // java.util.Map
    public final Set keySet() {
        if (this.k == null) {
            this.k = new C2727tx(this);
        }
        return this.k;
    }

    @Override // com.android.tools.r8.internal.J, java.util.Map
    public final void putAll(Map map) {
        int iMin = (int) Math.min(1073741824L, Math.max(2L, AbstractC0938Ws.b((long) Math.ceil((map.size() + this.i) / 0.75f))));
        if (iMin > this.g) {
            e(iMin);
        }
        super.putAll(map);
    }

    @Override // com.android.tools.r8.internal.J, com.android.tools.r8.internal.InterfaceC1445ex
    public final int remove(int i) {
        int i2;
        if (i == 0) {
            if (!this.f) {
                return this.b;
            }
            this.f = false;
            int[] iArr = this.d;
            int i3 = this.g;
            int i4 = iArr[i3];
            int i5 = this.i - 1;
            this.i = i5;
            if (i5 < this.h / 4 && i3 > 16) {
                e(i3 / 2);
            }
            return i4;
        }
        int[] iArr2 = this.c;
        int iA = AbstractC0938Ws.a(i) & this.e;
        int i6 = iArr2[iA];
        if (i6 == 0) {
            return this.b;
        }
        if (i == i6) {
            return f(iA);
        }
        do {
            iA = (iA + 1) & this.e;
            i2 = iArr2[iA];
            if (i2 == 0) {
                return this.b;
            }
        } while (i != i2);
        return f(iA);
    }

    @Override // com.android.tools.r8.internal.InterfaceC2294or, java.util.Map
    public final int size() {
        return this.i;
    }

    @Override // java.util.Map
    public final Collection values() {
        if (this.l == null) {
            this.l = new C2471qx(this);
        }
        return this.l;
    }
}
