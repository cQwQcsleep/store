package com.android.tools.r8.internal;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class ZL implements Cloneable, InterfaceC2294or, Serializable, Map {
    public transient long[] b;
    public transient Object[] c;
    public transient int d;
    public transient boolean e;
    public transient int f;
    public transient int g;
    public int h;
    public transient WL i;
    public transient UL j;
    public transient RL k;

    public ZL() {
        int iA = AbstractC0938Ws.a(16, 0.75f);
        this.f = iA;
        this.d = iA - 1;
        this.g = AbstractC0938Ws.b(iA, 0.75f);
        int i = this.f + 1;
        this.b = new long[i];
        this.c = new Object[i];
    }

    /* JADX WARN: Code duplicated, block: B:21:0x004b  */
    public final Object a(Object obj, long j) {
        int i;
        int i2;
        int i3;
        if (j != 0) {
            long[] jArr = this.b;
            int iA = ((int) AbstractC0938Ws.a(j)) & this.d;
            long j2 = jArr[iA];
            if (j2 != 0) {
                if (j2 != j) {
                    while (true) {
                        iA = (iA + 1) & this.d;
                        long j3 = jArr[iA];
                        if (j3 != 0) {
                            if (j3 == j) {
                            }
                        }
                    }
                }
                i2 = iA;
            }
            i = iA;
            this.b[i] = j;
            this.c[i] = obj;
            i3 = this.h;
            this.h = i3 + 1;
            if (i3 >= this.g) {
                d(AbstractC0938Ws.a(i3 + 2, 0.75f));
            }
            i2 = -1;
        } else if (this.e) {
            i2 = this.f;
        } else {
            this.e = true;
            i = this.f;
            this.b[i] = j;
            this.c[i] = obj;
            i3 = this.h;
            this.h = i3 + 1;
            if (i3 >= this.g) {
                d(AbstractC0938Ws.a(i3 + 2, 0.75f));
            }
            i2 = -1;
        }
        if (i2 < 0) {
            return null;
        }
        Object[] objArr = this.c;
        Object obj2 = objArr[i2];
        objArr[i2] = obj;
        return obj2;
    }

    public final Object b(long j) {
        long j2;
        if (j == 0) {
            if (this.e) {
                return this.c[this.f];
            }
            return null;
        }
        long[] jArr = this.b;
        int iA = ((int) AbstractC0938Ws.a(j)) & this.d;
        long j3 = jArr[iA];
        if (j3 == 0) {
            return null;
        }
        if (j == j3) {
            return this.c[iA];
        }
        do {
            iA = (iA + 1) & this.d;
            j2 = jArr[iA];
            if (j2 == 0) {
                return null;
            }
        } while (j != j2);
        return this.c[iA];
    }

    public final Object c(long j) {
        long j2;
        if (j == 0) {
            if (!this.e) {
                return null;
            }
            this.e = false;
            Object[] objArr = this.c;
            int i = this.f;
            Object obj = objArr[i];
            objArr[i] = null;
            int i2 = this.h - 1;
            this.h = i2;
            if (i2 < this.g / 4 && i > 16) {
                d(i / 2);
            }
            return obj;
        }
        long[] jArr = this.b;
        int iA = ((int) AbstractC0938Ws.a(j)) & this.d;
        long j3 = jArr[iA];
        if (j3 == 0) {
            return null;
        }
        if (j == j3) {
            return e(iA);
        }
        do {
            iA = (iA + 1) & this.d;
            j2 = jArr[iA];
            if (j2 == 0) {
                return null;
            }
        } while (j != j2);
        return e(iA);
    }

    @Override // java.util.Map
    public final void clear() {
        if (this.h == 0) {
            return;
        }
        this.h = 0;
        this.e = false;
        Arrays.fill(this.b, 0L);
        Arrays.fill(this.c, (Object) null);
    }

    public final Object clone() {
        try {
            ZL zl = (ZL) super.clone();
            zl.j = null;
            zl.k = null;
            zl.i = null;
            zl.e = this.e;
            zl.b = (long[]) this.b.clone();
            zl.c = (Object[]) this.c.clone();
            return zl;
        } catch (CloneNotSupportedException unused) {
            uj0.a();
            return null;
        }
    }

    @Override // com.android.tools.r8.internal.InterfaceC2294or, java.util.Map
    public final boolean containsKey(Object obj) {
        if (obj == null) {
            return false;
        }
        return a(((Long) obj).longValue());
    }

    @Override // java.util.Map
    public final boolean containsValue(Object obj) {
        Object[] objArr = this.c;
        long[] jArr = this.b;
        if (this.e && objArr[this.f] == obj) {
            return true;
        }
        int i = this.f;
        while (true) {
            int i2 = i - 1;
            if (i == 0) {
                return false;
            }
            if (jArr[i2] != 0 && objArr[i2] == obj) {
                return true;
            }
            i = i2;
        }
    }

    public final void d(int i) {
        long j;
        long[] jArr = this.b;
        Object[] objArr = this.c;
        int i2 = i - 1;
        int i3 = i + 1;
        long[] jArr2 = new long[i3];
        Object[] objArr2 = new Object[i3];
        int i4 = this.f;
        boolean z = this.e;
        int i5 = this.h;
        if (z) {
            i5--;
        }
        while (true) {
            int i6 = i5 - 1;
            if (i5 == 0) {
                objArr2[i] = objArr[this.f];
                this.f = i;
                this.d = i2;
                this.g = AbstractC0938Ws.b(i, 0.75f);
                this.b = jArr2;
                this.c = objArr2;
                return;
            }
            do {
                i4--;
                j = jArr[i4];
            } while (j == 0);
            int iA = ((int) AbstractC0938Ws.a(j)) & i2;
            if (jArr2[iA] != 0) {
                do {
                    iA = (iA + 1) & i2;
                } while (jArr2[iA] != 0);
            }
            jArr2[iA] = jArr[i4];
            objArr2[iA] = objArr[i4];
            i5 = i6;
        }
    }

    public final Object e(int i) {
        long j;
        int i2;
        Object[] objArr = this.c;
        Object obj = objArr[i];
        objArr[i] = null;
        this.h--;
        long[] jArr = this.b;
        loop0: while (true) {
            int i3 = (i + 1) & this.d;
            while (true) {
                j = jArr[i3];
                if (j != 0) {
                    int iA = (int) AbstractC0938Ws.a(j);
                    int i4 = this.d;
                    int i5 = iA & i4;
                    if (i > i3) {
                        if (i >= i5 && i5 > i3) {
                            break;
                        }
                        i3 = (i3 + 1) & i4;
                    } else {
                        if (i >= i5 || i5 > i3) {
                            break;
                        }
                        i3 = (i3 + 1) & i4;
                    }
                } else {
                    break loop0;
                }
            }
            jArr[i] = j;
            Object[] objArr2 = this.c;
            objArr2[i] = objArr2[i3];
            i = i3;
        }
        jArr[i] = 0;
        this.c[i] = null;
        if (this.h < this.g / 4 && (i2 = this.f) > 16) {
            d(i2 / 2);
        }
        return obj;
    }

    @Override // java.util.Map
    public final Set entrySet() {
        if (this.i == null) {
            this.i = new WL(this);
        }
        return this.i;
    }

    @Override // java.util.Map
    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof Map)) {
            return false;
        }
        Map map = (Map) obj;
        if (map.size() != this.h) {
            return false;
        }
        if (this.i == null) {
            this.i = new WL(this);
        }
        return this.i.containsAll(map.entrySet());
    }

    @Override // com.android.tools.r8.internal.InterfaceC2294or, java.util.Map
    public final Object get(Object obj) {
        if (obj == null) {
            return null;
        }
        long jLongValue = ((Long) obj).longValue();
        if (a(jLongValue)) {
            return b(jLongValue);
        }
        return null;
    }

    @Override // java.util.Map
    public final int hashCode() {
        long j;
        boolean z = this.e;
        int i = this.h;
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
                j = this.b[i2];
                if (j != 0) {
                    break;
                }
                i2++;
            }
            int iIdentityHashCode = (int) (j ^ (j >>> 32));
            Object obj = this.c[i2];
            if (this != obj) {
                iIdentityHashCode ^= obj == null ? 0 : System.identityHashCode(obj);
            }
            i3 += iIdentityHashCode;
            i2++;
            i = i4;
        }
        if (!this.e) {
            return i3;
        }
        Object obj2 = this.c[this.f];
        return i3 + (obj2 != null ? System.identityHashCode(obj2) : 0);
    }

    @Override // java.util.Map
    public final boolean isEmpty() {
        return this.h == 0;
    }

    @Override // java.util.Map
    public final Set keySet() {
        if (this.j == null) {
            this.j = new UL(this);
        }
        return this.j;
    }

    @Override // java.util.Map
    public final Object put(Object obj, Object obj2) {
        long jLongValue = ((Long) obj).longValue();
        boolean zA = a(jLongValue);
        Object objA = a(obj2, jLongValue);
        if (zA) {
            return objA;
        }
        return null;
    }

    @Override // java.util.Map
    public final void putAll(Map map) {
        int iMin = (int) Math.min(1073741824L, Math.max(2L, AbstractC0938Ws.b((long) Math.ceil((map.size() + this.h) / 0.75f))));
        if (iMin > this.f) {
            d(iMin);
        }
        a(map);
    }

    @Override // java.util.Map
    public final Object remove(Object obj) {
        if (obj == null) {
            return null;
        }
        long jLongValue = ((Long) obj).longValue();
        boolean zA = a(jLongValue);
        Object objC = c(jLongValue);
        if (zA) {
            return objC;
        }
        return null;
    }

    @Override // com.android.tools.r8.internal.InterfaceC2294or, java.util.Map
    public final int size() {
        return this.h;
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder("{");
        if (this.i == null) {
            this.i = new WL(this);
        }
        SL sl = new SL(this.i.b);
        int i = this.h;
        boolean z = true;
        while (true) {
            int i2 = i - 1;
            if (i == 0) {
                sb.append("}");
                return sb.toString();
            }
            if (z) {
                z = false;
            } else {
                sb.append(", ");
            }
            VL vl = (VL) sl.next();
            sb.append(String.valueOf(vl.c.b[vl.b]));
            sb.append("=>");
            Object obj = vl.c.c[vl.b];
            if (this == obj) {
                sb.append("(this map)");
            } else {
                sb.append(String.valueOf(obj));
            }
            i = i2;
        }
    }

    @Override // java.util.Map
    public final Collection values() {
        if (this.k == null) {
            this.k = new RL(this);
        }
        return this.k;
    }

    public final void a(Map map) {
        int size = map.size();
        Iterator it = map.entrySet().iterator();
        if (map instanceof ZL) {
            while (true) {
                int i = size - 1;
                if (size == 0) {
                    return;
                }
                VL vl = (VL) it.next();
                ZL zl = vl.c;
                long[] jArr = zl.b;
                int i2 = vl.b;
                a(zl.c[i2], jArr[i2]);
                size = i;
            }
        } else {
            while (true) {
                int i3 = size - 1;
                if (size == 0) {
                    return;
                }
                Map.Entry entry = (Map.Entry) it.next();
                Long l = (Long) entry.getKey();
                Object value = entry.getValue();
                long jLongValue = l.longValue();
                a(jLongValue);
                a(value, jLongValue);
                size = i3;
            }
        }
    }

    public final boolean a(long j) {
        long j2;
        if (j == 0) {
            return this.e;
        }
        long[] jArr = this.b;
        int iA = ((int) AbstractC0938Ws.a(j)) & this.d;
        long j3 = jArr[iA];
        if (j3 == 0) {
            return false;
        }
        if (j == j3) {
            return true;
        }
        do {
            iA = (iA + 1) & this.d;
            j2 = jArr[iA];
            if (j2 == 0) {
                return false;
            }
        } while (j != j2);
        return true;
    }
}
