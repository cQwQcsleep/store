package com.android.tools.r8.internal;

import java.util.AbstractCollection;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class V20 extends AbstractC2392q1 implements Cloneable, J20 {
    public transient Object[] b;
    public transient boolean[] c;
    public transient int d;
    public transient boolean e;
    public transient int f;
    public transient int g;
    public int h;
    public transient S20 i;
    public transient Q20 j;
    public transient N20 k;

    public V20() {
        int iA = AbstractC0938Ws.a(16, 0.75f);
        this.f = iA;
        this.d = iA - 1;
        this.g = AbstractC0938Ws.b(iA, 0.75f);
        int i = this.f + 1;
        this.b = new Object[i];
        this.c = new boolean[i];
    }

    /* JADX WARN: Code duplicated, block: B:19:0x0042  */
    @Override // com.android.tools.r8.internal.AbstractC2392q1
    public final boolean a(Object obj, boolean z) {
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
            this.c[i] = z;
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
            this.c[i] = z;
            i3 = this.h;
            this.h = i3 + 1;
            if (i3 >= this.g) {
                d(AbstractC0938Ws.a(i3 + 2, 0.75f));
            }
            i2 = -1;
        }
        if (i2 < 0) {
            return false;
        }
        boolean[] zArr = this.c;
        boolean z2 = zArr[i2];
        zArr[i2] = z;
        return z2;
    }

    @Override // com.android.tools.r8.internal.AbstractC2392q1
    public final boolean b(Object obj) {
        Object obj2;
        if (obj == null) {
            if (!this.e) {
                return false;
            }
            this.e = false;
            Object[] objArr = this.b;
            int i = this.f;
            objArr[i] = null;
            boolean z = this.c[i];
            int i2 = this.h - 1;
            this.h = i2;
            if (i2 < this.g / 4 && i > 16) {
                d(i / 2);
            }
            return z;
        }
        Object[] objArr2 = this.b;
        int iA = AbstractC0938Ws.a(System.identityHashCode(obj)) & this.d;
        Object obj3 = objArr2[iA];
        if (obj3 == null) {
            return false;
        }
        if (obj == obj3) {
            return e(iA);
        }
        do {
            iA = (iA + 1) & this.d;
            obj2 = objArr2[iA];
            if (obj2 == null) {
                return false;
            }
        } while (obj != obj2);
        return e(iA);
    }

    @Override // com.android.tools.r8.internal.AbstractC2392q1, java.util.Map
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
            V20 v20 = (V20) super.clone();
            v20.j = null;
            v20.k = null;
            v20.i = null;
            v20.e = this.e;
            v20.b = (Object[]) this.b.clone();
            v20.c = (boolean[]) this.c.clone();
            return v20;
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
        boolean zBooleanValue = ((Boolean) obj).booleanValue();
        boolean[] zArr = this.c;
        Object[] objArr = this.b;
        if (this.e && zArr[this.f] == zBooleanValue) {
            return true;
        }
        int i = this.f;
        while (true) {
            int i2 = i - 1;
            if (i == 0) {
                return false;
            }
            if (objArr[i2] != null && zArr[i2] == zBooleanValue) {
                return true;
            }
            i = i2;
        }
    }

    public final void d(int i) {
        Object obj;
        Object[] objArr = this.b;
        boolean[] zArr = this.c;
        int i2 = i - 1;
        int i3 = i + 1;
        Object[] objArr2 = new Object[i3];
        boolean[] zArr2 = new boolean[i3];
        int i4 = this.f;
        boolean z = this.e;
        int i5 = this.h;
        if (z) {
            i5--;
        }
        while (true) {
            int i6 = i5 - 1;
            if (i5 == 0) {
                zArr2[i] = zArr[this.f];
                this.f = i;
                this.d = i2;
                this.g = AbstractC0938Ws.b(i, 0.75f);
                this.b = objArr2;
                this.c = zArr2;
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
            zArr2[iA] = zArr[i4];
            i5 = i6;
        }
    }

    public final boolean e(int i) {
        Object obj;
        int i2;
        boolean z = this.c[i];
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
            boolean[] zArr = this.c;
            zArr[i] = zArr[i3];
            i = i3;
        }
        objArr[i] = null;
        if (this.h < this.g / 4 && (i2 = this.f) > 16) {
            d(i2 / 2);
        }
        return z;
    }

    @Override // java.util.Map
    public final Set entrySet() {
        return g();
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
        return ((AbstractCollection) g()).containsAll(map.entrySet());
    }

    @Override // com.android.tools.r8.internal.J20
    public final JU g() {
        if (this.i == null) {
            this.i = new S20(this);
        }
        return this.i;
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
            int i6 = 1237;
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
            if (this.c[i2]) {
                i6 = 1231;
            }
            iIdentityHashCode ^= i6;
            i4 += iIdentityHashCode;
            i2++;
            i3 = i5;
        }
        if (this.e) {
            return i4 + (this.c[this.f] ? 1231 : 1237);
        }
        return i4;
    }

    @Override // java.util.Map
    public final boolean isEmpty() {
        return this.h == 0;
    }

    @Override // java.util.Map
    public final V30 keySet() {
        if (this.j == null) {
            this.j = new Q20(this);
        }
        return this.j;
    }

    @Override // java.util.Map
    public final void putAll(Map map) {
        int iMin = (int) Math.min(1073741824L, Math.max(2L, AbstractC0938Ws.b((long) Math.ceil((map.size() + this.h) / 0.75f))));
        if (iMin > this.f) {
            d(iMin);
        }
        a(map);
    }

    @Override // com.android.tools.r8.internal.InterfaceC2294or, java.util.Map
    public final int size() {
        return this.h;
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder("{");
        O20 o20 = new O20(((S20) g()).b);
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
            I20 i20 = (I20) o20.next();
            if (this == i20.getKey()) {
                sb.append("(this map)");
            } else {
                sb.append(String.valueOf(i20.getKey()));
            }
            sb.append("=>");
            sb.append(String.valueOf(i20.getBooleanValue()));
            i = i2;
        }
    }

    @Override // java.util.Map
    public final Collection values() {
        if (this.k == null) {
            this.k = new N20(this);
        }
        return this.k;
    }

    public final void a(Map map) {
        int size = map.size();
        Iterator it = map.entrySet().iterator();
        if (map instanceof J20) {
            while (true) {
                int i = size - 1;
                if (size == 0) {
                    return;
                }
                I20 i20 = (I20) it.next();
                a(i20.getKey(), i20.getBooleanValue());
                size = i;
            }
        } else {
            while (true) {
                int i2 = size - 1;
                if (size == 0) {
                    return;
                }
                Map.Entry entry = (Map.Entry) it.next();
                Object key = entry.getKey();
                Boolean bool = (Boolean) entry.getValue();
                containsKey(key);
                a(key, bool.booleanValue());
                size = i2;
            }
        }
    }

    @Override // com.android.tools.r8.internal.H20
    public final boolean a(Object obj) {
        Object obj2;
        if (obj == null) {
            if (this.e) {
                return this.c[this.f];
            }
            return false;
        }
        Object[] objArr = this.b;
        int iA = AbstractC0938Ws.a(System.identityHashCode(obj)) & this.d;
        Object obj3 = objArr[iA];
        if (obj3 == null) {
            return false;
        }
        if (obj == obj3) {
            return this.c[iA];
        }
        do {
            iA = (iA + 1) & this.d;
            obj2 = objArr[iA];
            if (obj2 == null) {
                return false;
            }
        } while (obj != obj2);
        return this.c[iA];
    }
}
