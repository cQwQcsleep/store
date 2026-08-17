package com.android.tools.r8.internal;

import java.util.Arrays;
import java.util.Map;

/* JADX INFO: renamed from: com.android.tools.r8.internal.wz, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C2986wz extends S implements Cloneable {
    public transient int[] c;
    public transient Object[] d;
    public transient int e;
    public transient boolean f;
    public transient int g;
    public transient int h;
    public int i;
    public final float j;
    public transient C2729tz k;
    public transient C2558rz l;
    public transient C2302oz m;

    public C2986wz(int i) {
        if (i < 0) {
            w01.a("The expected number of elements must be nonnegative");
            throw null;
        }
        this.j = 0.75f;
        int iA = AbstractC0938Ws.a(i, 0.75f);
        this.g = iA;
        this.e = iA - 1;
        this.h = AbstractC0938Ws.b(iA, 0.75f);
        int i2 = this.g + 1;
        this.c = new int[i2];
        this.d = new Object[i2];
    }

    /* JADX WARN: Code duplicated, block: B:19:0x003e  */
    @Override // com.android.tools.r8.internal.P, com.android.tools.r8.internal.InterfaceC1109az
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
                d(AbstractC0938Ws.a(i4 + 2, this.j));
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
                d(AbstractC0938Ws.a(i4 + 2, this.j));
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

    @Override // com.android.tools.r8.internal.InterfaceC2045lz
    public final JU c() {
        if (this.k == null) {
            this.k = new C2729tz(this);
        }
        return this.k;
    }

    @Override // com.android.tools.r8.internal.P, java.util.Map
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
            C2986wz c2986wz = (C2986wz) super.clone();
            c2986wz.l = null;
            c2986wz.m = null;
            c2986wz.k = null;
            c2986wz.f = this.f;
            c2986wz.c = (int[]) this.c.clone();
            c2986wz.d = (Object[]) this.d.clone();
            return c2986wz;
        } catch (CloneNotSupportedException unused) {
            uj0.a();
            return null;
        }
    }

    @Override // java.util.Map
    public final boolean containsValue(Object obj) {
        Object[] objArr = this.d;
        int[] iArr = this.c;
        if (this.f && objArr[this.g] == obj) {
            return true;
        }
        int i = this.g;
        while (true) {
            int i2 = i - 1;
            if (i == 0) {
                return false;
            }
            if (iArr[i2] != 0 && objArr[i2] == obj) {
                return true;
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
                this.h = AbstractC0938Ws.b(i, this.j);
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

    @Override // com.android.tools.r8.internal.InterfaceC1109az
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

    @Override // com.android.tools.r8.internal.S, java.util.Map
    public final int hashCode() {
        int iIdentityHashCode;
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
                iIdentityHashCode = this.c[i2];
                if (iIdentityHashCode != 0) {
                    break;
                }
                i2++;
            }
            Object obj = this.d[i2];
            if (this != obj) {
                iIdentityHashCode ^= obj == null ? 0 : System.identityHashCode(obj);
            }
            i3 += iIdentityHashCode;
            i2++;
            i = i4;
        }
        if (!this.f) {
            return i3;
        }
        Object obj2 = this.d[this.g];
        return i3 + (obj2 != null ? System.identityHashCode(obj2) : 0);
    }

    @Override // java.util.Map
    public final boolean isEmpty() {
        return this.i == 0;
    }

    @Override // java.util.Map
    public final InterfaceC3177zA keySet() {
        if (this.l == null) {
            this.l = new C2558rz(this);
        }
        return this.l;
    }

    @Override // com.android.tools.r8.internal.S, java.util.Map
    public final void putAll(Map map) {
        if (this.j <= 0.5d) {
            int iA = AbstractC0938Ws.a(map.size(), this.j);
            if (iA > this.g) {
                d(iA);
            }
        } else {
            int iMin = (int) Math.min(1073741824L, Math.max(2L, AbstractC0938Ws.b((long) Math.ceil((map.size() + this.i) / this.j))));
            if (iMin > this.g) {
                d(iMin);
            }
        }
        super.putAll(map);
    }

    @Override // com.android.tools.r8.internal.P, com.android.tools.r8.internal.InterfaceC1109az
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

    @Override // com.android.tools.r8.internal.S, com.android.tools.r8.internal.InterfaceC2045lz, java.util.Map, com.android.tools.r8.internal.InterfaceC0763Pz, java.util.SortedMap
    public final P30 values() {
        if (this.m == null) {
            this.m = new C2302oz(this);
        }
        return this.m;
    }

    public C2986wz() {
        this(16);
    }

    public C2986wz(InterfaceC2045lz interfaceC2045lz) {
        this(interfaceC2045lz.size());
        putAll(interfaceC2045lz);
    }

    @Override // com.android.tools.r8.internal.S, com.android.tools.r8.internal.InterfaceC1109az
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
