package com.android.tools.r8.internal;

import java.io.Serializable;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class ZS implements Cloneable, Serializable, Map, InterfaceC2294or {
    public transient Object[] b = AbstractC2771uU.a;
    public transient boolean[] c = D6.a;
    public int d;

    public final boolean a(Object obj, boolean z) {
        int iB = b(obj);
        if (iB != -1) {
            boolean[] zArr = this.c;
            boolean z2 = zArr[iB];
            zArr[iB] = z;
            return z2;
        }
        int i = this.d;
        if (i == this.b.length) {
            Object[] objArr = new Object[i == 0 ? 2 : i * 2];
            boolean[] zArr2 = new boolean[i != 0 ? i * 2 : 2];
            while (true) {
                int i2 = i - 1;
                if (i == 0) {
                    break;
                }
                objArr[i2] = this.b[i2];
                zArr2[i2] = this.c[i2];
                i = i2;
            }
            this.b = objArr;
            this.c = zArr2;
        }
        Object[] objArr2 = this.b;
        int i3 = this.d;
        objArr2[i3] = obj;
        this.c[i3] = z;
        this.d = i3 + 1;
        return false;
    }

    public final int b(Object obj) {
        Object[] objArr = this.b;
        int i = this.d;
        while (true) {
            int i2 = i - 1;
            if (i == 0) {
                return -1;
            }
            Object obj2 = objArr[i2];
            if (obj2 == null) {
                if (obj == null) {
                    return i2;
                }
                i = i2;
            } else {
                if (obj2.equals(obj)) {
                    return i2;
                }
                i = i2;
            }
        }
    }

    public final boolean c(Object obj) {
        int iB = b(obj);
        if (iB == -1) {
            return false;
        }
        boolean z = this.c[iB];
        int i = (this.d - iB) - 1;
        Object[] objArr = this.b;
        int i2 = iB + 1;
        System.arraycopy(objArr, i2, objArr, iB, i);
        boolean[] zArr = this.c;
        System.arraycopy(zArr, i2, zArr, iB, i);
        int i3 = this.d - 1;
        this.d = i3;
        this.b[i3] = null;
        return z;
    }

    @Override // java.util.Map
    public final void clear() {
        int i = this.d;
        while (true) {
            int i2 = i - 1;
            if (i == 0) {
                this.d = 0;
                return;
            } else {
                this.b[i2] = null;
                i = i2;
            }
        }
    }

    public final Object clone() {
        try {
            ZS zs = (ZS) super.clone();
            zs.b = (Object[]) this.b.clone();
            zs.c = (boolean[]) this.c.clone();
            return zs;
        } catch (CloneNotSupportedException unused) {
            uj0.a();
            return null;
        }
    }

    @Override // java.util.Map, com.android.tools.r8.internal.InterfaceC2294or
    public final boolean containsKey(Object obj) {
        return b(obj) != -1;
    }

    @Override // java.util.Map
    public final boolean containsValue(Object obj) {
        if (obj == null) {
            return false;
        }
        boolean zBooleanValue = ((Boolean) obj).booleanValue();
        int i = this.d;
        while (true) {
            int i2 = i - 1;
            if (i == 0) {
                return false;
            }
            if (this.c[i2] == zBooleanValue) {
                return true;
            }
            i = i2;
        }
    }

    @Override // java.util.Map
    public final Set entrySet() {
        return new YS(this);
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
        if (map.size() != this.d) {
            return false;
        }
        return new YS(this).containsAll(map.entrySet());
    }

    @Override // java.util.Map, com.android.tools.r8.internal.InterfaceC2294or
    public final Object get(Object obj) {
        if (containsKey(obj)) {
            return Boolean.valueOf(a(obj));
        }
        return null;
    }

    @Override // java.util.Map
    public final int hashCode() {
        int i = this.d;
        XS xs = new XS(new YS(this));
        int iHashCode = 0;
        while (true) {
            int i2 = i - 1;
            if (i == 0) {
                return iHashCode;
            }
            iHashCode += ((Map.Entry) xs.next()).hashCode();
            i = i2;
        }
    }

    @Override // java.util.Map
    public final boolean isEmpty() {
        return this.d == 0;
    }

    @Override // java.util.Map
    public final Set keySet() {
        return new C2685tU(this.d, this.b);
    }

    @Override // java.util.Map
    public final Object put(Object obj, Object obj2) {
        boolean zContainsKey = containsKey(obj);
        boolean zA = a(obj, ((Boolean) obj2).booleanValue());
        if (zContainsKey) {
            return Boolean.valueOf(zA);
        }
        return null;
    }

    @Override // java.util.Map
    public final void putAll(Map map) {
        int size = map.size();
        Iterator it = map.entrySet().iterator();
        if (map instanceof ZS) {
            while (true) {
                int i = size - 1;
                if (size == 0) {
                    return;
                }
                S0 s0 = (S0) it.next();
                a(s0.b, s0.c);
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

    @Override // java.util.Map
    public final Object remove(Object obj) {
        boolean zContainsKey = containsKey(obj);
        boolean zC = c(obj);
        if (zContainsKey) {
            return Boolean.valueOf(zC);
        }
        return null;
    }

    @Override // java.util.Map, com.android.tools.r8.internal.InterfaceC2294or
    public final int size() {
        return this.d;
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder("{");
        XS xs = new XS(new YS(this));
        int i = this.d;
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
            S0 s0 = (S0) xs.next();
            Object obj = s0.b;
            if (this == obj) {
                sb.append("(this map)");
            } else {
                sb.append(String.valueOf(obj));
            }
            sb.append("=>");
            sb.append(String.valueOf(s0.c));
            i = i2;
        }
    }

    @Override // java.util.Map
    public final Collection values() {
        return new H6(new C6(this.c, this.d));
    }

    public final boolean a(Object obj) {
        Object[] objArr = this.b;
        int i = this.d;
        while (true) {
            int i2 = i - 1;
            if (i == 0) {
                return false;
            }
            Object obj2 = objArr[i2];
            if (obj2 == null) {
                if (obj == null) {
                    return this.c[i2];
                }
                i = i2;
            } else {
                if (obj2.equals(obj)) {
                    return this.c[i2];
                }
                i = i2;
            }
        }
    }
}
