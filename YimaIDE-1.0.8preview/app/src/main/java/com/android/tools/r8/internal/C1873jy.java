package com.android.tools.r8.internal;

import java.util.Arrays;
import java.util.Map;
import java.util.Set;

/* JADX INFO: renamed from: com.android.tools.r8.internal.jy, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C1873jy extends M implements Cloneable {
    public transient int[] c;
    public transient Object[] d;
    public transient int e;
    public transient boolean f;
    public transient int g;
    public transient int h;
    public int i;
    public transient C1617gy j;
    public transient C1446ey k;
    public transient C1108ay l;

    public C1873jy(int i) {
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
        this.d = new Object[i2];
    }

    /* JADX WARN: Code duplicated, block: B:19:0x003e  */
    @Override // com.android.tools.r8.internal.K, com.android.tools.r8.internal.InterfaceC0917Vx
    public final Object a(int i, Object obj) {
        int i2;
        int i3;
        int i4;
        if (i != 0) {
            int[] iArr = this.c;
            int iA = AbstractC0938Ws.a(i) & this.e;
            int i5 = iArr[iA];
            if (i5 != 0) {
                if (i5 != i) {
                    while (true) {
                        iA = (iA + 1) & this.e;
                        int i6 = iArr[iA];
                        if (i6 != 0) {
                            if (i6 == i) {
                            }
                        }
                    }
                }
                i3 = iA;
            }
            i2 = iA;
            this.c[i2] = i;
            this.d[i2] = obj;
            i4 = this.i;
            this.i = i4 + 1;
            if (i4 >= this.h) {
                d(AbstractC0938Ws.a(i4 + 2, 0.75f));
            }
            i3 = -1;
        } else if (this.f) {
            i3 = this.g;
        } else {
            this.f = true;
            i2 = this.g;
            this.c[i2] = i;
            this.d[i2] = obj;
            i4 = this.i;
            this.i = i4 + 1;
            if (i4 >= this.h) {
                d(AbstractC0938Ws.a(i4 + 2, 0.75f));
            }
            i3 = -1;
        }
        if (i3 < 0) {
            return this.b;
        }
        Object[] objArr = this.d;
        Object obj2 = objArr[i3];
        objArr[i3] = obj;
        return obj2;
    }

    @Override // com.android.tools.r8.internal.InterfaceC0969Xx, com.android.tools.r8.internal.InterfaceC0425Cy
    public final JU b() {
        if (this.j == null) {
            this.j = new C1617gy(this);
        }
        return this.j;
    }

    @Override // com.android.tools.r8.internal.K, java.util.Map
    public final void clear() {
        if (this.i == 0) {
            return;
        }
        this.i = 0;
        this.f = false;
        Arrays.fill(this.c, 0);
        Arrays.fill(this.d, (Object) null);
    }

    public final Object clone() {
        try {
            C1873jy c1873jy = (C1873jy) super.clone();
            c1873jy.k = null;
            c1873jy.l = null;
            c1873jy.j = null;
            c1873jy.f = this.f;
            c1873jy.c = (int[]) this.c.clone();
            c1873jy.d = (Object[]) this.d.clone();
            return c1873jy;
        } catch (CloneNotSupportedException unused) {
            uj0.a();
            return null;
        }
    }

    @Override // java.util.Map
    public final boolean containsValue(Object obj) {
        Object obj2;
        Object[] objArr = this.d;
        int[] iArr = this.c;
        if (this.f && ((obj2 = objArr[this.g]) != null ? obj2.equals(obj) : obj == null)) {
            return true;
        }
        int i = this.g;
        while (true) {
            int i2 = i - 1;
            if (i == 0) {
                return false;
            }
            if (iArr[i2] != 0) {
                Object obj3 = objArr[i2];
                if (obj3 == null) {
                    if (obj == null) {
                        return true;
                    }
                } else if (obj3.equals(obj)) {
                    return true;
                }
            }
            i = i2;
        }
    }

    public final void d(int i) {
        int i2;
        int[] iArr = this.c;
        Object[] objArr = this.d;
        int i3 = i - 1;
        int i4 = i + 1;
        int[] iArr2 = new int[i4];
        Object[] objArr2 = new Object[i4];
        int i5 = this.g;
        boolean z = this.f;
        int i6 = this.i;
        if (z) {
            i6--;
        }
        while (true) {
            int i7 = i6 - 1;
            if (i6 == 0) {
                objArr2[i] = objArr[this.g];
                this.g = i;
                this.e = i3;
                this.h = AbstractC0938Ws.b(i, 0.75f);
                this.c = iArr2;
                this.d = objArr2;
                return;
            }
            do {
                i5--;
                i2 = iArr[i5];
            } while (i2 == 0);
            int iA = AbstractC0938Ws.a(i2) & i3;
            if (iArr2[iA] != 0) {
                do {
                    iA = (iA + 1) & i3;
                } while (iArr2[iA] != 0);
            }
            iArr2[iA] = iArr[i5];
            objArr2[iA] = objArr[i5];
            i6 = i7;
        }
    }

    public final Object e(int i) {
        int i2;
        int i3;
        Object[] objArr = this.d;
        Object obj = objArr[i];
        objArr[i] = null;
        this.i--;
        int[] iArr = this.c;
        loop0: while (true) {
            int i4 = (i + 1) & this.e;
            while (true) {
                i2 = iArr[i4];
                if (i2 != 0) {
                    int iA = AbstractC0938Ws.a(i2);
                    int i5 = this.e;
                    int i6 = iA & i5;
                    if (i > i4) {
                        if (i >= i6 && i6 > i4) {
                            break;
                        }
                        i4 = (i4 + 1) & i5;
                    } else {
                        if (i >= i6 || i6 > i4) {
                            break;
                        }
                        i4 = (i4 + 1) & i5;
                    }
                } else {
                    break loop0;
                }
            }
            iArr[i] = i2;
            Object[] objArr2 = this.d;
            objArr2[i] = objArr2[i4];
            i = i4;
        }
        iArr[i] = 0;
        this.d[i] = null;
        if (this.i < this.h / 4 && (i3 = this.g) > 16) {
            d(i3 / 2);
        }
        return obj;
    }

    @Override // com.android.tools.r8.internal.InterfaceC0917Vx
    public final Object get(int i) {
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

    @Override // com.android.tools.r8.internal.M, java.util.Map
    public final int hashCode() {
        int iHashCode;
        boolean z = this.f;
        int i = this.i;
        if (z) {
            i--;
        }
        int i2 = 0;
        int i3 = 0;
        while (true) {
            int i4 = i - 1;
            if (i == 0) {
                break;
            }
            while (true) {
                iHashCode = this.c[i2];
                if (iHashCode != 0) {
                    break;
                }
                i2++;
            }
            Object obj = this.d[i2];
            if (this != obj) {
                iHashCode ^= obj == null ? 0 : obj.hashCode();
            }
            i3 += iHashCode;
            i2++;
            i = i4;
        }
        if (!this.f) {
            return i3;
        }
        Object obj2 = this.d[this.g];
        return i3 + (obj2 != null ? obj2.hashCode() : 0);
    }

    @Override // java.util.Map
    public final boolean isEmpty() {
        return this.i == 0;
    }

    @Override // java.util.Map
    public final Set keySet() {
        if (this.k == null) {
            this.k = new C1446ey(this);
        }
        return this.k;
    }

    @Override // com.android.tools.r8.internal.M, java.util.Map
    public final void putAll(Map map) {
        int iMin = (int) Math.min(1073741824L, Math.max(2L, AbstractC0938Ws.b((long) Math.ceil((map.size() + this.i) / 0.75f))));
        if (iMin > this.g) {
            d(iMin);
        }
        super.putAll(map);
    }

    @Override // com.android.tools.r8.internal.K
    public final Object remove(int i) {
        int i2;
        if (i == 0) {
            if (!this.f) {
                return this.b;
            }
            this.f = false;
            Object[] objArr = this.d;
            int i3 = this.g;
            Object obj = objArr[i3];
            objArr[i3] = null;
            int i4 = this.i - 1;
            this.i = i4;
            if (i4 < this.h / 4 && i3 > 16) {
                d(i3 / 2);
            }
            return obj;
        }
        int[] iArr = this.c;
        int iA = AbstractC0938Ws.a(i) & this.e;
        int i5 = iArr[iA];
        if (i5 == 0) {
            return this.b;
        }
        if (i == i5) {
            return e(iA);
        }
        do {
            iA = (iA + 1) & this.e;
            i2 = iArr[iA];
            if (i2 == 0) {
                return this.b;
            }
        } while (i != i2);
        return e(iA);
    }

    @Override // com.android.tools.r8.internal.InterfaceC2294or, java.util.Map
    public final int size() {
        return this.i;
    }

    @Override // com.android.tools.r8.internal.M, com.android.tools.r8.internal.InterfaceC0969Xx, java.util.Map, com.android.tools.r8.internal.InterfaceC0425Cy, java.util.SortedMap
    public final InterfaceC3028xU values() {
        if (this.l == null) {
            this.l = new C1108ay(this);
        }
        return this.l;
    }

    public C1873jy(C1873jy c1873jy) {
        this(c1873jy.i);
        putAll(c1873jy);
    }

    @Override // com.android.tools.r8.internal.M, com.android.tools.r8.internal.InterfaceC0917Vx
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
}
