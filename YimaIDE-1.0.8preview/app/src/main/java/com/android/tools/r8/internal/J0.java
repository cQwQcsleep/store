package com.android.tools.r8.internal;

import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public abstract class J0 implements TN, WN {
    public int b = 0;
    public int c = -1;

    public static int a(int i, Map map) {
        int iHashCode;
        int iHashCode2;
        int i2;
        int iHashCode3;
        for (Map.Entry entry : map.entrySet()) {
            C1856jk c1856jk = (C1856jk) entry.getKey();
            Object value = entry.getValue();
            int i3 = (i * 37) + c1856jk.c.g;
            int iA = 1;
            if (c1856jk.j()) {
                int i4 = i3 * 53;
                Map mapA = a((List) value);
                int i5 = EM.c;
                int i6 = 0;
                for (Map.Entry entry2 : mapA.entrySet()) {
                    Object key = entry2.getKey();
                    if (key instanceof byte[]) {
                        byte[] bArr = (byte[]) key;
                        Charset charset = AbstractC1556gB.a;
                        iHashCode = bArr.length;
                        for (byte b : bArr) {
                            iHashCode = (iHashCode * 31) + b;
                        }
                        if (iHashCode == 0) {
                            iHashCode = 1;
                        }
                    } else {
                        if (key instanceof InterfaceC1046aB) {
                            a9g.a();
                            return 0;
                        }
                        iHashCode = key.hashCode();
                    }
                    Object value2 = entry2.getValue();
                    if (value2 instanceof byte[]) {
                        byte[] bArr2 = (byte[]) value2;
                        Charset charset2 = AbstractC1556gB.a;
                        iHashCode2 = bArr2.length;
                        for (byte b2 : bArr2) {
                            iHashCode2 = (iHashCode2 * 31) + b2;
                        }
                        if (iHashCode2 == 0) {
                            iHashCode2 = 1;
                        }
                    } else {
                        if (value2 instanceof InterfaceC1046aB) {
                            a9g.a();
                            return 0;
                        }
                        iHashCode2 = value2.hashCode();
                    }
                    i6 += iHashCode ^ iHashCode2;
                }
                i = i4 + i6;
            } else {
                if (c1856jk.h != EnumC1771ik.g) {
                    i2 = i3 * 53;
                    iHashCode3 = value.hashCode();
                } else if (c1856jk.m()) {
                    int i7 = i3 * 53;
                    Iterator it = ((List) value).iterator();
                    while (it.hasNext()) {
                        iA = (iA * 31) + ((InterfaceC1046aB) it.next()).a();
                    }
                    i = i7 + iA;
                } else {
                    i2 = i3 * 53;
                    iHashCode3 = ((InterfaceC1046aB) value).a();
                }
                i = i2 + iHashCode3;
            }
        }
        return i;
    }

    public boolean equals(Object obj) {
        U7 q7;
        Object q8;
        boolean zEquals;
        U7 q9;
        Object q10;
        boolean zEquals2;
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof J0)) {
            return false;
        }
        J0 j0 = (J0) obj;
        if (e() != j0.e()) {
            return false;
        }
        Map mapF = f();
        Map mapF2 = j0.f();
        if (mapF.size() == mapF2.size()) {
            for (C1856jk c1856jk : mapF.keySet()) {
                if (mapF2.containsKey(c1856jk)) {
                    Object obj2 = mapF.get(c1856jk);
                    Object obj3 = mapF2.get(c1856jk);
                    if (c1856jk.h == EnumC1771ik.f) {
                        if (c1856jk.m()) {
                            List list = (List) obj2;
                            List list2 = (List) obj3;
                            if (list.size() == list2.size()) {
                                for (int i = 0; i < list.size(); i++) {
                                    Object obj4 = list.get(i);
                                    Object obj5 = list2.get(i);
                                    boolean z = obj4 instanceof byte[];
                                    if (z && (obj5 instanceof byte[])) {
                                        zEquals = Arrays.equals((byte[]) obj4, (byte[]) obj5);
                                    } else {
                                        if (z) {
                                            byte[] bArr = (byte[]) obj4;
                                            int length = bArr.length;
                                            U7.a(0, length, bArr.length);
                                            q7 = new Q7(U7.d.a(bArr, 0, length));
                                        } else {
                                            q7 = (U7) obj4;
                                        }
                                        if (obj5 instanceof byte[]) {
                                            byte[] bArr2 = (byte[]) obj5;
                                            int length2 = bArr2.length;
                                            U7.a(0, length2, bArr2.length);
                                            q8 = new Q7(U7.d.a(bArr2, 0, length2));
                                        } else {
                                            q8 = (U7) obj5;
                                        }
                                        zEquals = q7.equals(q8);
                                    }
                                    if (zEquals) {
                                    }
                                }
                            }
                        } else {
                            boolean z2 = obj2 instanceof byte[];
                            if (z2 && (obj3 instanceof byte[])) {
                                zEquals2 = Arrays.equals((byte[]) obj2, (byte[]) obj3);
                            } else {
                                if (z2) {
                                    byte[] bArr3 = (byte[]) obj2;
                                    int length3 = bArr3.length;
                                    U7.a(0, length3, bArr3.length);
                                    q9 = new Q7(U7.d.a(bArr3, 0, length3));
                                } else {
                                    q9 = (U7) obj2;
                                }
                                if (obj3 instanceof byte[]) {
                                    byte[] bArr4 = (byte[]) obj3;
                                    int length4 = bArr4.length;
                                    U7.a(0, length4, bArr4.length);
                                    q10 = new Q7(U7.d.a(bArr4, 0, length4));
                                } else {
                                    q10 = (U7) obj3;
                                }
                                zEquals2 = q9.equals(q10);
                            }
                            if (!zEquals2) {
                            }
                        }
                    } else if (c1856jk.j()) {
                        if (!EM.a(a((List) obj2), a((List) obj3))) {
                        }
                    } else if (!obj2.equals(obj3)) {
                    }
                }
            }
            if (g().equals(j0.g())) {
                return true;
            }
        }
        return false;
    }

    public abstract H0 h();

    public int hashCode() {
        int i = this.b;
        if (i != 0) {
            return i;
        }
        int iHashCode = g().hashCode() + (a(e().hashCode() + 779, f()) * 29);
        this.b = iHashCode;
        return iHashCode;
    }

    public final String toString() {
        Logger logger = Sg0.a;
        return Qg0.b.a(this);
    }

    public static Map a(List list) {
        if (list.isEmpty()) {
            return Collections.EMPTY_MAP;
        }
        HashMap map = new HashMap();
        Iterator it = list.iterator();
        J0 j0 = (J0) it.next();
        C0955Xj c0955XjE = j0.e();
        AbstractC2027lk abstractC2027lkA = c0955XjE.d.h.a(3, c0955XjE.c + ".key");
        C1856jk c1856jk = abstractC2027lkA instanceof C1856jk ? (C1856jk) abstractC2027lkA : null;
        AbstractC2027lk abstractC2027lkA2 = c0955XjE.d.h.a(3, c0955XjE.c + ".value");
        C1856jk c1856jk2 = abstractC2027lkA2 instanceof C1856jk ? (C1856jk) abstractC2027lkA2 : null;
        Object objA = j0.a(c1856jk2);
        if (objA instanceof C1515fk) {
            objA = Integer.valueOf(((C1515fk) objA).b.g);
        }
        map.put(j0.a(c1856jk), objA);
        while (it.hasNext()) {
            J0 j1 = (J0) it.next();
            Object objA2 = j1.a(c1856jk2);
            if (objA2 instanceof C1515fk) {
                objA2 = Integer.valueOf(((C1515fk) objA2).b.g);
            }
            map.put(j1.a(c1856jk), objA2);
        }
        return map;
    }

    public H0 a(I0 i0) {
        throw new UnsupportedOperationException("Nested builder is not supported for this type.");
    }
}
