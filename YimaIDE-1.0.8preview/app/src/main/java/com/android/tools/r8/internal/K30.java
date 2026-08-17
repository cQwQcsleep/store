package com.android.tools.r8.internal;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class K30 implements Cloneable, InterfaceC2294or, Serializable, Map {
    public transient Object[] b;
    public transient Object[] c;
    public transient int d;
    public transient boolean e;
    public transient int f;
    public transient int g;
    public int h;
    public transient H30 i;
    public transient F30 j;
    public transient C30 k;

    public K30(int i) {
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
        this.c = new Object[i2];
    }

    public final void a(Map map) {
        int size = map.size();
        Iterator it = map.entrySet().iterator();
        if (map instanceof K30) {
            while (true) {
                int i = size - 1;
                if (size == 0) {
                    return;
                }
                G30 g30 = (G30) it.next();
                K30 k30 = g30.c;
                Object[] objArr = k30.b;
                int i2 = g30.b;
                put(objArr[i2], k30.c[i2]);
                size = i;
            }
        } else {
            while (true) {
                int i3 = size - 1;
                if (size == 0) {
                    return;
                }
                Map.Entry entry = (Map.Entry) it.next();
                put(entry.getKey(), entry.getValue());
                size = i3;
            }
        }
    }

    @Override // java.util.Map
    public final void clear() {
        if (this.h == 0) {
            return;
        }
        this.h = 0;
        this.e = false;
        Arrays.fill(this.b, (Object) null);
        Arrays.fill(this.c, (Object) null);
    }

    public final Object clone() {
        try {
            K30 k30 = (K30) super.clone();
            k30.j = null;
            k30.k = null;
            k30.i = null;
            k30.e = this.e;
            k30.b = (Object[]) this.b.clone();
            k30.c = (Object[]) this.c.clone();
            return k30;
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
        Object[] objArr = this.c;
        Object[] objArr2 = this.b;
        if (this.e && objArr[this.f] == obj) {
            return true;
        }
        int i = this.f;
        while (true) {
            int i2 = i - 1;
            if (i == 0) {
                return false;
            }
            if (objArr2[i2] != null && objArr[i2] == obj) {
                return true;
            }
            i = i2;
        }
    }

    public final void d(int i) {
        Object obj;
        Object[] objArr = this.b;
        Object[] objArr2 = this.c;
        int i2 = i - 1;
        int i3 = i + 1;
        Object[] objArr3 = new Object[i3];
        Object[] objArr4 = new Object[i3];
        int i4 = this.f;
        boolean z = this.e;
        int i5 = this.h;
        if (z) {
            i5--;
        }
        while (true) {
            int i6 = i5 - 1;
            if (i5 == 0) {
                objArr4[i] = objArr2[this.f];
                this.f = i;
                this.d = i2;
                this.g = AbstractC0938Ws.b(i, 0.75f);
                this.b = objArr3;
                this.c = objArr4;
                return;
            }
            do {
                i4--;
                obj = objArr[i4];
            } while (obj == null);
            int iA = AbstractC0938Ws.a(System.identityHashCode(obj)) & i2;
            if (objArr3[iA] != null) {
                do {
                    iA = (iA + 1) & i2;
                } while (objArr3[iA] != null);
            }
            objArr3[iA] = objArr[i4];
            objArr4[iA] = objArr2[i4];
            i5 = i6;
        }
    }

    public final Object e(int i) {
        Object obj;
        int i2;
        Object[] objArr = this.c;
        Object obj2 = objArr[i];
        objArr[i] = null;
        this.h--;
        Object[] objArr2 = this.b;
        loop0: while (true) {
            int i3 = (i + 1) & this.d;
            while (true) {
                obj = objArr2[i3];
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
            objArr2[i] = obj;
            Object[] objArr3 = this.c;
            objArr3[i] = objArr3[i3];
            i = i3;
        }
        objArr2[i] = null;
        this.c[i] = null;
        if (this.h < this.g / 4 && (i2 = this.f) > 16) {
            d(i2 / 2);
        }
        return obj2;
    }

    @Override // java.util.Map
    public final Set entrySet() {
        if (this.i == null) {
            this.i = new H30(this);
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
            this.i = new H30(this);
        }
        return this.i.containsAll(map.entrySet());
    }

    @Override // com.android.tools.r8.internal.InterfaceC2294or, java.util.Map
    public final Object get(Object obj) {
        Object obj2;
        if (obj == null) {
            if (this.e) {
                return this.c[this.f];
            }
            return null;
        }
        Object[] objArr = this.b;
        int iA = AbstractC0938Ws.a(System.identityHashCode(obj)) & this.d;
        Object obj3 = objArr[iA];
        if (obj3 == null) {
            return null;
        }
        if (obj == obj3) {
            return this.c[iA];
        }
        do {
            iA = (iA + 1) & this.d;
            obj2 = objArr[iA];
            if (obj2 == null) {
                return null;
            }
        } while (obj != obj2);
        return this.c[iA];
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
        int i3 = 0;
        int iIdentityHashCode = 0;
        while (true) {
            int i4 = i - 1;
            if (i == 0) {
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
            Object obj2 = this.c[i2];
            if (this != obj2) {
                iIdentityHashCode = (obj2 == null ? 0 : System.identityHashCode(obj2)) ^ iIdentityHashCode;
            }
            i3 += iIdentityHashCode;
            i2++;
            i = i4;
        }
        if (!this.e) {
            return i3;
        }
        Object obj3 = this.c[this.f];
        return i3 + (obj3 != null ? System.identityHashCode(obj3) : 0);
    }

    @Override // java.util.Map
    public final boolean isEmpty() {
        return this.h == 0;
    }

    @Override // java.util.Map
    public final Set keySet() {
        if (this.j == null) {
            this.j = new F30(this);
        }
        return this.j;
    }

    /* JADX WARN: Code duplicated, block: B:19:0x0042  */
    @Override // java.util.Map
    public final Object put(Object obj, Object obj2) {
        int i;
        int i2;
        int i3;
        if (obj != null) {
            Object[] objArr = this.b;
            int iA = AbstractC0938Ws.a(System.identityHashCode(obj)) & this.d;
            Object obj3 = objArr[iA];
            if (obj3 != null) {
                if (obj3 != obj) {
                    while (true) {
                        iA = (iA + 1) & this.d;
                        Object obj4 = objArr[iA];
                        if (obj4 != null) {
                            if (obj4 == obj) {
                            }
                        }
                    }
                }
                i2 = iA;
            }
            i = iA;
            this.b[i] = obj;
            this.c[i] = obj2;
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
            this.c[i] = obj2;
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
        Object[] objArr2 = this.c;
        Object obj5 = objArr2[i2];
        objArr2[i2] = obj2;
        return obj5;
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
        Object obj2;
        if (obj != null) {
            Object[] objArr = this.b;
            int iA = AbstractC0938Ws.a(System.identityHashCode(obj)) & this.d;
            Object obj3 = objArr[iA];
            if (obj3 == null) {
                return null;
            }
            if (obj == obj3) {
                return e(iA);
            }
            do {
                iA = (iA + 1) & this.d;
                obj2 = objArr[iA];
                if (obj2 == null) {
                    return null;
                }
            } while (obj != obj2);
            return e(iA);
        }
        if (!this.e) {
            return null;
        }
        this.e = false;
        Object[] objArr2 = this.b;
        int i = this.f;
        objArr2[i] = null;
        Object[] objArr3 = this.c;
        Object obj4 = objArr3[i];
        objArr3[i] = null;
        int i2 = this.h - 1;
        this.h = i2;
        if (i2 < this.g / 4 && i > 16) {
            d(i / 2);
        }
        return obj4;
    }

    @Override // com.android.tools.r8.internal.InterfaceC2294or, java.util.Map
    public final int size() {
        return this.h;
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder("{");
        if (this.i == null) {
            this.i = new H30(this);
        }
        D30 d30 = new D30(this.i.b);
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
            G30 g30 = (G30) d30.next();
            Object obj = g30.c.b[g30.b];
            if (this == obj) {
                sb.append("(this map)");
            } else {
                sb.append(String.valueOf(obj));
            }
            sb.append("=>");
            Object obj2 = g30.c.c[g30.b];
            if (this == obj2) {
                sb.append("(this map)");
            } else {
                sb.append(String.valueOf(obj2));
            }
            i = i2;
        }
    }

    @Override // java.util.Map
    public final Collection values() {
        if (this.k == null) {
            this.k = new C30(this);
        }
        return this.k;
    }
}
