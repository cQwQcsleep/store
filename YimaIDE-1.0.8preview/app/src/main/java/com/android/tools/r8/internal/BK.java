package com.android.tools.r8.internal;

import java.io.Serializable;
import java.util.AbstractMap;
import java.util.Comparator;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class BK extends AbstractMap implements Serializable {
    public static final C2761uK j = new C2761uK();
    public static final /* synthetic */ boolean k = true;
    public final Comparator b;
    public final boolean c;
    public AK d;
    public int e;
    public int f;
    public final AK g;
    public C2932wK h;
    public C3102yK i;

    public BK(boolean z) {
        C2761uK c2761uK = j;
        this.e = 0;
        this.f = 0;
        this.b = c2761uK;
        this.c = z;
        this.g = new AK(z);
    }

    public final void a(AK ak, boolean z) {
        while (ak != null) {
            AK ak2 = ak.c;
            AK ak3 = ak.d;
            int i = ak2 != null ? ak2.j : 0;
            int i2 = ak3 != null ? ak3.j : 0;
            int i3 = i - i2;
            if (i3 == -2) {
                AK ak4 = ak3.c;
                AK ak5 = ak3.d;
                int i4 = (ak4 != null ? ak4.j : 0) - (ak5 != null ? ak5.j : 0);
                if (i4 == -1 || (i4 == 0 && !z)) {
                    a(ak);
                } else if (!k && i4 != 1) {
                    x1f.a();
                    return;
                } else {
                    b(ak3);
                    a(ak);
                }
                if (z) {
                    return;
                }
            } else if (i3 == 2) {
                AK ak6 = ak2.c;
                AK ak7 = ak2.d;
                int i5 = (ak6 != null ? ak6.j : 0) - (ak7 != null ? ak7.j : 0);
                if (i5 == 1 || (i5 == 0 && !z)) {
                    b(ak);
                } else if (!k && i5 != -1) {
                    x1f.a();
                    return;
                } else {
                    a(ak2);
                    b(ak);
                }
                if (z) {
                    return;
                }
            } else if (i3 == 0) {
                ak.j = i + 1;
                if (z) {
                    return;
                }
            } else if (!k && i3 != -1 && i3 != 1) {
                x1f.a();
                return;
            } else {
                ak.j = Math.max(i, i2) + 1;
                if (!z) {
                    return;
                }
            }
            ak = ak.b;
        }
    }

    public final void b(AK ak, boolean z) {
        AK ak2;
        AK ak3;
        int i;
        if (z) {
            AK ak4 = ak.f;
            ak4.e = ak.e;
            ak.e.f = ak4;
        }
        AK ak5 = ak.c;
        AK ak6 = ak.d;
        AK ak7 = ak.b;
        int i2 = 0;
        if (ak5 == null || ak6 == null) {
            if (ak5 != null) {
                a(ak, ak5);
                ak.c = null;
            } else if (ak6 != null) {
                a(ak, ak6);
                ak.d = null;
            } else {
                a(ak, (AK) null);
            }
            a(ak7, false);
            this.e--;
            this.f++;
            return;
        }
        if (ak5.j > ak6.j) {
            AK ak8 = ak5.d;
            while (true) {
                AK ak9 = ak8;
                ak3 = ak5;
                ak5 = ak9;
                if (ak5 == null) {
                    break;
                } else {
                    ak8 = ak5.d;
                }
            }
        } else {
            AK ak10 = ak6.c;
            while (true) {
                ak2 = ak6;
                ak6 = ak10;
                if (ak6 == null) {
                    break;
                } else {
                    ak10 = ak6.c;
                }
            }
            ak3 = ak2;
        }
        b(ak3, false);
        AK ak11 = ak.c;
        if (ak11 != null) {
            i = ak11.j;
            ak3.c = ak11;
            ak11.b = ak3;
            ak.c = null;
        } else {
            i = 0;
        }
        AK ak12 = ak.d;
        if (ak12 != null) {
            i2 = ak12.j;
            ak3.d = ak12;
            ak12.b = ak3;
            ak.d = null;
        }
        ak3.j = Math.max(i, i2) + 1;
        a(ak, ak3);
    }

    @Override // java.util.AbstractMap, java.util.Map
    public final void clear() {
        this.d = null;
        this.e = 0;
        this.f++;
        AK ak = this.g;
        ak.f = ak;
        ak.e = ak;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public final boolean containsKey(Object obj) {
        return a(obj) != null;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public final Set entrySet() {
        C2932wK c2932wK = this.h;
        if (c2932wK != null) {
            return c2932wK;
        }
        C2932wK c2932wK2 = new C2932wK(this);
        this.h = c2932wK2;
        return c2932wK2;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public final Object get(Object obj) {
        AK akA = a(obj);
        if (akA != null) {
            return akA.i;
        }
        return null;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public final Set keySet() {
        C3102yK c3102yK = this.i;
        if (c3102yK != null) {
            return c3102yK;
        }
        C3102yK c3102yK2 = new C3102yK(this);
        this.i = c3102yK2;
        return c3102yK2;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public final Object put(Object obj, Object obj2) {
        if (obj == null) {
            x0e.a("key == null");
            return null;
        }
        if (obj2 == null && !this.c) {
            x0e.a("value == null");
            return null;
        }
        AK akA = a(obj, true);
        Object obj3 = akA.i;
        akA.i = obj2;
        return obj3;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public final Object remove(Object obj) {
        AK akA = a(obj);
        if (akA != null) {
            b(akA, true);
        }
        if (akA != null) {
            return akA.i;
        }
        return null;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public final int size() {
        return this.e;
    }

    public final void b(AK ak) {
        AK ak2 = ak.c;
        AK ak3 = ak.d;
        AK ak4 = ak2.c;
        AK ak5 = ak2.d;
        ak.c = ak5;
        if (ak5 != null) {
            ak5.b = ak;
        }
        a(ak, ak2);
        ak2.d = ak;
        ak.b = ak2;
        int iMax = Math.max(ak3 != null ? ak3.j : 0, ak5 != null ? ak5.j : 0) + 1;
        ak.j = iMax;
        ak2.j = Math.max(iMax, ak4 != null ? ak4.j : 0) + 1;
    }

    public final AK a(Object obj) {
        if (obj != null) {
            try {
                return a(obj, false);
            } catch (ClassCastException unused) {
            }
        }
        return null;
    }

    public final AK a(Map.Entry entry) {
        AK akA = a(entry.getKey());
        if (akA == null || !Objects.equals(akA.i, entry.getValue())) {
            return null;
        }
        return akA;
    }

    public final void a(AK ak, AK ak2) {
        AK ak3 = ak.b;
        ak.b = null;
        if (ak2 != null) {
            ak2.b = ak3;
        }
        if (ak3 != null) {
            if (ak3.c == ak) {
                ak3.c = ak2;
                return;
            } else if (k || ak3.d == ak) {
                ak3.d = ak2;
                return;
            } else {
                x1f.a();
                return;
            }
        }
        this.d = ak2;
    }

    public final AK a(Object obj, boolean z) {
        int iCompare;
        AK ak;
        Comparator comparator = this.b;
        AK ak2 = this.d;
        if (ak2 != null) {
            Comparable comparable = comparator == j ? (Comparable) obj : null;
            while (true) {
                Object obj2 = ak2.g;
                if (comparable != null) {
                    iCompare = comparable.compareTo(obj2);
                } else {
                    iCompare = comparator.compare(obj, obj2);
                }
                if (iCompare == 0) {
                    return ak2;
                }
                AK ak3 = iCompare < 0 ? ak2.c : ak2.d;
                if (ak3 == null) {
                    break;
                }
                ak2 = ak3;
            }
        } else {
            iCompare = 0;
        }
        AK ak4 = ak2;
        if (!z) {
            return null;
        }
        AK ak5 = this.g;
        if (ak4 == null) {
            if (comparator == j && !(obj instanceof Comparable)) {
                throw new ClassCastException(obj.getClass().getName().concat(" is not Comparable"));
            }
            ak = new AK(this.c, ak4, obj, ak5, ak5.f);
            this.d = ak;
        } else {
            ak = new AK(this.c, ak4, obj, ak5, ak5.f);
            if (iCompare < 0) {
                ak4.c = ak;
            } else {
                ak4.d = ak;
            }
            a(ak4, true);
        }
        this.e++;
        this.f++;
        return ak;
    }

    public final void a(AK ak) {
        AK ak2 = ak.c;
        AK ak3 = ak.d;
        AK ak4 = ak3.c;
        AK ak5 = ak3.d;
        ak.d = ak4;
        if (ak4 != null) {
            ak4.b = ak;
        }
        a(ak, ak3);
        ak3.c = ak;
        ak.b = ak3;
        int iMax = Math.max(ak2 != null ? ak2.j : 0, ak4 != null ? ak4.j : 0) + 1;
        ak.j = iMax;
        ak3.j = Math.max(iMax, ak5 != null ? ak5.j : 0) + 1;
    }
}
