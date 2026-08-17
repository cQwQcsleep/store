package com.android.tools.r8.internal;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class B30 implements Cloneable, InterfaceC2294or, Serializable, Map {
    public transient Object[] b;
    public transient long[] c;
    public transient int d;
    public transient boolean e;
    public transient int f;
    public transient int g;
    public int h;
    public transient C3080y30 i;
    public transient C2909w30 j;
    public transient C2652t30 k;

    public B30(int i) {
        if (i < 0) {
            w01.a("The expected number of elements must be nonnegative");
            throw null;
        }
        int iA = AbstractC0938Ws.a(i, 0.75f);
        this.f = iA;
        this.d = iA - 1;
        this.g = AbstractC0938Ws.b(iA, 0.75f);
        int i2 = this.f + 1;
        this.b = new Object[i2];
        this.c = new long[i2];
    }

    /* JADX WARN: Code duplicated, block: B:19:0x0042  */
    public final long a(Object obj, long j) {
        int i;
        int i2;
        int i3;
        if (obj != null) {
            Object[] objArr = this.b;
            int iA = AbstractC0938Ws.a(System.identityHashCode(obj)) & this.d;
            Object obj2 = objArr[iA];
            if (obj2 != null) {
                if (obj2 != obj) {
                    while (true) {
                        iA = (iA + 1) & this.d;
                        Object obj3 = objArr[iA];
                        if (obj3 != null) {
                            if (obj3 == obj) {
                            }
                        }
                    }
                }
                i2 = iA;
            }
            i = iA;
            this.b[i] = obj;
            this.c[i] = j;
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
            this.b[i] = obj;
            this.c[i] = j;
            i3 = this.h;
            this.h = i3 + 1;
            if (i3 >= this.g) {
                d(AbstractC0938Ws.a(i3 + 2, 0.75f));
            }
            i2 = -1;
        }
        if (i2 < 0) {
            return 0L;
        }
        long[] jArr = this.c;
        long j2 = jArr[i2];
        jArr[i2] = j;
        return j2;
    }

    public final long b(Object obj) {
        Object obj2;
        if (obj == null) {
            if (this.e) {
                return this.c[this.f];
            }
            return 0L;
        }
        Object[] objArr = this.b;
        int iA = AbstractC0938Ws.a(System.identityHashCode(obj)) & this.d;
        Object obj3 = objArr[iA];
        if (obj3 == null) {
            return 0L;
        }
        if (obj == obj3) {
            return this.c[iA];
        }
        do {
            iA = (iA + 1) & this.d;
            obj2 = objArr[iA];
            if (obj2 == null) {
                return 0L;
            }
        } while (obj != obj2);
        return this.c[iA];
    }

    public final long c(Object obj) {
        Object obj2;
        if (obj == null) {
            if (!this.e) {
                return 0L;
            }
            this.e = false;
            Object[] objArr = this.b;
            int i = this.f;
            objArr[i] = null;
            long j = this.c[i];
            int i2 = this.h - 1;
            this.h = i2;
            if (i2 < this.g / 4 && i > 16) {
                d(i / 2);
            }
            return j;
        }
        Object[] objArr2 = this.b;
        int iA = AbstractC0938Ws.a(System.identityHashCode(obj)) & this.d;
        Object obj3 = objArr2[iA];
        if (obj3 == null) {
            return 0L;
        }
        if (obj == obj3) {
            return e(iA);
        }
        do {
            iA = (iA + 1) & this.d;
            obj2 = objArr2[iA];
            if (obj2 == null) {
                return 0L;
            }
        } while (obj != obj2);
        return e(iA);
    }

    @Override // java.util.Map
    public final void clear() {
        if (this.h == 0) {
            return;
        }
        this.h = 0;
        this.e = false;
        Arrays.fill(this.b, (Object) null);
    }

    public final Object clone() {
        try {
            B30 b30 = (B30) super.clone();
            b30.j = null;
            b30.k = null;
            b30.i = null;
            b30.e = this.e;
            b30.b = (Object[]) this.b.clone();
            b30.c = (long[]) this.c.clone();
            return b30;
        } catch (CloneNotSupportedException unused) {
            uj0.a();
            return null;
        }
    }

    @Override // com.android.tools.r8.internal.InterfaceC2294or, java.util.Map
    public final boolean containsKey(Object obj) {
        Object obj2;
        if (obj == null) {
            return this.e;
        }
        Object[] objArr = this.b;
        int iA = AbstractC0938Ws.a(System.identityHashCode(obj)) & this.d;
        Object obj3 = objArr[iA];
        if (obj3 == null) {
            return false;
        }
        if (obj == obj3) {
            return true;
        }
        do {
            iA = (iA + 1) & this.d;
            obj2 = objArr[iA];
            if (obj2 == null) {
                return false;
            }
        } while (obj != obj2);
        return true;
    }

    @Override // java.util.Map
    public final boolean containsValue(Object obj) {
        if (obj == null) {
            return false;
        }
        return a(((Long) obj).longValue());
    }

    public final void d(int i) {
        Object obj;
        Object[] objArr = this.b;
        long[] jArr = this.c;
        int i2 = i - 1;
        int i3 = i + 1;
        Object[] objArr2 = new Object[i3];
        long[] jArr2 = new long[i3];
        int i4 = this.f;
        boolean z = this.e;
        int i5 = this.h;
        if (z) {
            i5--;
        }
        while (true) {
            int i6 = i5 - 1;
            if (i5 == 0) {
                jArr2[i] = jArr[this.f];
                this.f = i;
                this.d = i2;
                this.g = AbstractC0938Ws.b(i, 0.75f);
                this.b = objArr2;
                this.c = jArr2;
                return;
            }
            do {
                i4--;
                obj = objArr[i4];
            } while (obj == null);
            int iA = AbstractC0938Ws.a(System.identityHashCode(obj)) & i2;
            if (objArr2[iA] != null) {
                do {
                    iA = (iA + 1) & i2;
                } while (objArr2[iA] != null);
            }
            objArr2[iA] = objArr[i4];
            jArr2[iA] = jArr[i4];
            i5 = i6;
        }
    }

    public final long e(int i) {
        Object obj;
        int i2;
        long j = this.c[i];
        this.h--;
        Object[] objArr = this.b;
        loop0: while (true) {
            int i3 = (i + 1) & this.d;
            while (true) {
                obj = objArr[i3];
                if (obj != null) {
                    int iA = AbstractC0938Ws.a(System.identityHashCode(obj));
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
            objArr[i] = obj;
            long[] jArr = this.c;
            jArr[i] = jArr[i3];
            i = i3;
        }
        objArr[i] = null;
        if (this.h < this.g / 4 && (i2 = this.f) > 16) {
            d(i2 / 2);
        }
        return j;
    }

    @Override // java.util.Map
    public final Set entrySet() {
        if (this.i == null) {
            this.i = new C3080y30(this);
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
            this.i = new C3080y30(this);
        }
        return this.i.containsAll(map.entrySet());
    }

    @Override // com.android.tools.r8.internal.InterfaceC2294or, java.util.Map
    public final Object get(Object obj) {
        if (containsKey(obj)) {
            return Long.valueOf(b(obj));
        }
        return null;
    }

    @Override // java.util.Map
    public final int hashCode() {
        Object obj;
        boolean z = this.e;
        int i = this.h;
        if (z) {
            i--;
        }
        int i2 = 0;
        int iIdentityHashCode = 0;
        int i3 = i;
        int i4 = 0;
        while (true) {
            int i5 = i3 - 1;
            if (i3 == 0) {
                break;
            }
            while (true) {
                obj = this.b[i2];
                if (obj != null) {
                    break;
                }
                i2++;
            }
            if (this != obj) {
                iIdentityHashCode = System.identityHashCode(obj);
            }
            long j = this.c[i2];
            iIdentityHashCode ^= (int) (j ^ (j >>> 32));
            i4 += iIdentityHashCode;
            i2++;
            i3 = i5;
        }
        if (!this.e) {
            return i4;
        }
        long j2 = this.c[this.f];
        return i4 + ((int) (j2 ^ (j2 >>> 32)));
    }

    @Override // java.util.Map
    public final boolean isEmpty() {
        return this.h == 0;
    }

    @Override // java.util.Map
    public final Set keySet() {
        if (this.j == null) {
            this.j = new C2909w30(this);
        }
        return this.j;
    }

    @Override // java.util.Map
    public final Object put(Object obj, Object obj2) {
        boolean zContainsKey = containsKey(obj);
        long jA = a(obj, ((Long) obj2).longValue());
        if (zContainsKey) {
            return Long.valueOf(jA);
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
        boolean zContainsKey = containsKey(obj);
        long jC = c(obj);
        if (zContainsKey) {
            return Long.valueOf(jC);
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
            this.i = new C3080y30(this);
        }
        C2738u30 c2738u30 = new C2738u30(this.i.b);
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
            C2995x30 c2995x30 = (C2995x30) c2738u30.next();
            Object obj = c2995x30.c.b[c2995x30.b];
            if (this == obj) {
                sb.append("(this map)");
            } else {
                sb.append(String.valueOf(obj));
            }
            sb.append("=>");
            sb.append(String.valueOf(c2995x30.c.c[c2995x30.b]));
            i = i2;
        }
    }

    @Override // java.util.Map
    public final Collection values() {
        if (this.k == null) {
            this.k = new C2652t30(this);
        }
        return this.k;
    }

    public final void a(Map map) {
        int size = map.size();
        Iterator it = map.entrySet().iterator();
        if (map instanceof B30) {
            while (true) {
                int i = size - 1;
                if (size == 0) {
                    return;
                }
                C2995x30 c2995x30 = (C2995x30) it.next();
                B30 b30 = c2995x30.c;
                Object[] objArr = b30.b;
                int i2 = c2995x30.b;
                a(objArr[i2], b30.c[i2]);
                size = i;
            }
        } else {
            while (true) {
                int i3 = size - 1;
                if (size == 0) {
                    return;
                }
                Map.Entry entry = (Map.Entry) it.next();
                Object key = entry.getKey();
                Long l = (Long) entry.getValue();
                containsKey(key);
                a(key, l.longValue());
                size = i3;
            }
        }
    }

    public final boolean a(long j) {
        long[] jArr = this.c;
        Object[] objArr = this.b;
        if (this.e && jArr[this.f] == j) {
            return true;
        }
        int i = this.f;
        while (true) {
            int i2 = i - 1;
            if (i == 0) {
                return false;
            }
            if (objArr[i2] != null && jArr[i2] == j) {
                return true;
            }
            i = i2;
        }
    }
}
