package com.android.tools.r8.internal;

import java.util.Collection;
import java.util.Comparator;

/* JADX INFO: renamed from: com.android.tools.r8.internal.dU, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C1318dU extends W0 {
    public final Object c;
    public final Object d;
    public final boolean e;
    public final boolean f;
    public transient WT g;
    public transient YT h;
    public transient XT i;
    public final /* synthetic */ C1574gU j;

    public C1318dU(C1574gU c1574gU, Object obj, boolean z, Object obj2, boolean z2) {
        this.j = c1574gU;
        if (!z && !z2) {
            c1574gU.getClass();
            if (((Comparable) obj).compareTo(obj2) > 0) {
                zba.a("Start key (", obj, ") is larger than end key (", obj2, ")");
                throw null;
            }
        }
        this.c = obj;
        this.e = z;
        this.d = obj2;
        this.f = z2;
        this.b = c1574gU.b;
    }

    /* JADX WARN: Code duplicated, block: B:14:0x002c  */
    /* JADX WARN: Code duplicated, block: B:21:0x0046  */
    @Override // com.android.tools.r8.internal.W0
    /* JADX INFO: renamed from: b */
    public final W0 subMap(Object obj, Object obj2) {
        Object obj3;
        Object obj4;
        boolean z = this.f;
        if (z && this.e) {
            return new C1318dU(this.j, obj, false, obj2, false);
        }
        if (z) {
            obj3 = obj2;
        } else {
            C1574gU c1574gU = this.j;
            Object obj5 = this.d;
            c1574gU.getClass();
            if (((Comparable) obj2).compareTo(obj5) < 0) {
                obj3 = obj2;
            } else {
                obj3 = this.d;
            }
        }
        if (this.e) {
            obj4 = obj;
        } else {
            C1574gU c1574gU2 = this.j;
            Object obj6 = this.c;
            c1574gU2.getClass();
            if (((Comparable) obj).compareTo(obj6) > 0) {
                obj4 = obj;
            } else {
                obj4 = this.c;
            }
        }
        return (this.f || this.e || obj4 != this.c || obj3 != this.d) ? new C1318dU(this.j, obj4, false, obj3, false) : this;
    }

    @Override // com.android.tools.r8.internal.W0
    /* JADX INFO: renamed from: c */
    public final W0 tailMap(Object obj) {
        boolean z = this.e;
        C1574gU c1574gU = this.j;
        if (z) {
            return new C1318dU(c1574gU, obj, false, this.d, this.f);
        }
        Object obj2 = this.c;
        c1574gU.getClass();
        return ((Comparable) obj).compareTo(obj2) > 0 ? new C1318dU(this.j, obj, false, this.d, this.f) : this;
    }

    @Override // com.android.tools.r8.internal.W0, java.util.Map
    public final void clear() {
        C1065aU c1065aU = new C1065aU(this);
        while (c1065aU.hasNext()) {
            c1065aU.a();
            c1065aU.remove();
        }
    }

    @Override // com.android.tools.r8.internal.W0, java.util.SortedMap
    public final Comparator comparator() {
        this.j.getClass();
        return null;
    }

    @Override // com.android.tools.r8.internal.W0, java.util.Map, com.android.tools.r8.internal.InterfaceC2294or
    public final boolean containsKey(Object obj) {
        return d(obj) && this.j.containsKey(obj);
    }

    @Override // java.util.Map
    public final boolean containsValue(Object obj) {
        C1065aU c1065aU = new C1065aU(this);
        while (c1065aU.hasNext()) {
            Object obj2 = c1065aU.a().c;
            if (obj2 == null) {
                if (obj == null) {
                    return true;
                }
            } else if (obj2.equals(obj)) {
                return true;
            }
        }
        return false;
    }

    public final boolean d(Object obj) {
        if (!this.e) {
            C1574gU c1574gU = this.j;
            Object obj2 = this.c;
            c1574gU.getClass();
            if (((Comparable) obj).compareTo(obj2) < 0) {
                return false;
            }
        }
        if (this.f) {
            return true;
        }
        C1574gU c1574gU2 = this.j;
        Object obj3 = this.d;
        c1574gU2.getClass();
        return ((Comparable) obj).compareTo(obj3) < 0;
    }

    @Override // com.android.tools.r8.internal.W0
    /* JADX INFO: renamed from: e */
    public final NU keySet() {
        if (this.h == null) {
            this.h = new YT(this);
        }
        return this.h;
    }

    @Override // java.util.SortedMap
    public final Object firstKey() {
        ST stJ = j();
        if (stJ != null) {
            return stJ.b;
        }
        z0e.a();
        return null;
    }

    @Override // java.util.Map, com.android.tools.r8.internal.InterfaceC2294or
    public final Object get(Object obj) {
        ST stD;
        return (!d(obj) || (stD = this.j.d(obj)) == null) ? this.b : stD.c;
    }

    @Override // com.android.tools.r8.internal.W0
    public final NU i() {
        if (this.g == null) {
            this.g = new WT(this);
        }
        return this.g;
    }

    @Override // java.util.Map
    public final boolean isEmpty() {
        ST st = this.j.e;
        return !(j() != null);
    }

    /* JADX WARN: Code duplicated, block: B:20:0x0033  */
    public final ST j() {
        ST stC;
        ST st;
        C1574gU c1574gU = this.j;
        ST st2 = c1574gU.c;
        if (st2 == null) {
            return null;
        }
        if (this.e) {
            stC = c1574gU.e;
        } else {
            Object obj = this.c;
            int iCompareTo = 0;
            ST st3 = st2;
            while (st2 != null) {
                iCompareTo = ((Comparable) obj).compareTo(st2.b);
                if (iCompareTo == 0) {
                    break;
                }
                int i = st2.f;
                if (iCompareTo < 0) {
                    if ((i & 1073741824) != 0) {
                        st = null;
                    } else {
                        st = st2.d;
                    }
                } else if ((i & Integer.MIN_VALUE) != 0) {
                    st = null;
                } else {
                    st = st2.e;
                }
                ST st4 = st;
                st3 = st2;
                st2 = st4;
            }
            stC = iCompareTo == 0 ? st2 : st3;
            C1574gU c1574gU2 = this.j;
            Object obj2 = stC.b;
            Object obj3 = this.c;
            c1574gU2.getClass();
            if (((Comparable) obj2).compareTo(obj3) < 0) {
                stC = stC.c();
            }
        }
        if (stC != null) {
            if (!this.f) {
                C1574gU c1574gU3 = this.j;
                Object obj4 = stC.b;
                Object obj5 = this.d;
                c1574gU3.getClass();
                if (((Comparable) obj4).compareTo(obj5) >= 0) {
                }
            }
            return stC;
        }
        return null;
    }

    /* JADX WARN: Code duplicated, block: B:20:0x0033  */
    public final ST k() {
        ST stE;
        ST st;
        C1574gU c1574gU = this.j;
        ST st2 = c1574gU.c;
        if (st2 == null) {
            return null;
        }
        if (this.f) {
            stE = c1574gU.f;
        } else {
            Object obj = this.d;
            int iCompareTo = 0;
            ST st3 = st2;
            while (st2 != null) {
                iCompareTo = ((Comparable) obj).compareTo(st2.b);
                if (iCompareTo == 0) {
                    break;
                }
                int i = st2.f;
                if (iCompareTo < 0) {
                    if ((i & 1073741824) != 0) {
                        st = null;
                    } else {
                        st = st2.d;
                    }
                } else if ((i & Integer.MIN_VALUE) != 0) {
                    st = null;
                } else {
                    st = st2.e;
                }
                ST st4 = st;
                st3 = st2;
                st2 = st4;
            }
            stE = iCompareTo == 0 ? st2 : st3;
            C1574gU c1574gU2 = this.j;
            Object obj2 = stE.b;
            Object obj3 = this.d;
            c1574gU2.getClass();
            if (((Comparable) obj2).compareTo(obj3) >= 0) {
                stE = stE.e();
            }
        }
        if (stE != null) {
            if (!this.e) {
                C1574gU c1574gU3 = this.j;
                Object obj4 = stE.b;
                Object obj5 = this.c;
                c1574gU3.getClass();
                if (((Comparable) obj4).compareTo(obj5) < 0) {
                }
            }
            return stE;
        }
        return null;
    }

    @Override // java.util.SortedMap
    public final Object lastKey() {
        ST stK = k();
        if (stK != null) {
            return stK.b;
        }
        z0e.a();
        return null;
    }

    @Override // com.android.tools.r8.internal.W0, java.util.Map
    public final Object put(Object obj, Object obj2) {
        this.j.j = false;
        if (d(obj)) {
            return this.j.j ? this.b : this.j.put(obj, obj2);
        }
        StringBuilder sb = new StringBuilder("Key (");
        sb.append(obj);
        sb.append(") out of range [");
        sb.append(this.e ? "-" : String.valueOf(this.c));
        sb.append(", ");
        sb.append(this.f ? "-" : String.valueOf(this.d));
        sb.append(")");
        throw new IllegalArgumentException(sb.toString());
    }

    @Override // java.util.Map
    public final Object remove(Object obj) {
        this.j.j = false;
        if (d(obj)) {
            return this.j.j ? this.j.remove(obj) : this.b;
        }
        return this.b;
    }

    @Override // java.util.Map, com.android.tools.r8.internal.InterfaceC2294or
    public final int size() {
        C1065aU c1065aU = new C1065aU(this);
        int i = 0;
        while (c1065aU.hasNext()) {
            i++;
            c1065aU.a();
        }
        return i;
    }

    @Override // java.util.SortedMap, java.util.Map
    public final Collection values() {
        if (this.i == null) {
            this.i = new XT(this);
        }
        return this.i;
    }

    @Override // com.android.tools.r8.internal.W0
    /* JADX INFO: renamed from: b */
    public final W0 headMap(Object obj) {
        boolean z = this.f;
        C1574gU c1574gU = this.j;
        if (z) {
            return new C1318dU(c1574gU, this.c, this.e, obj, false);
        }
        Object obj2 = this.d;
        c1574gU.getClass();
        return ((Comparable) obj).compareTo(obj2) < 0 ? new C1318dU(this.j, this.c, this.e, obj, false) : this;
    }
}
