package com.android.tools.r8.internal;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public abstract class T50 {
    public static final M70 a(M70 m70, List list, boolean z) {
        I0 i0;
        KB.c(m70, "<this>");
        KB.c(list, "ids");
        if (list.isEmpty()) {
            return m70;
        }
        List listB = AbstractC1760ie.b(list);
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        LinkedHashMap linkedHashMap2 = new LinkedHashMap();
        ArrayList arrayList = new ArrayList();
        Iterator it = listB.iterator();
        int i = -1;
        int i2 = -1;
        while (it.hasNext()) {
            int iIntValue = ((Number) it.next()).intValue();
            int i3 = iIntValue >> 24;
            int i4 = (16711680 & iIntValue) >> 16;
            int i5 = iIntValue & 65535;
            if (i3 != i) {
                linkedHashMap2 = new LinkedHashMap();
                linkedHashMap.put(Integer.valueOf(i3), linkedHashMap2);
                i2 = -1;
                i = i3;
            }
            if (i4 != i2) {
                arrayList = new ArrayList();
                linkedHashMap2.put(Integer.valueOf(i4), arrayList);
                i2 = i4;
            }
            arrayList.add(Integer.valueOf(i5));
        }
        L70 l70 = m70 == M70.k ? new L70() : new L70().a(m70);
        if (l70.i == null) {
            l70.i = new C2401q50(l70.h, (l70.f & 1) != 0, l70.m(), l70.d);
            l70.h = null;
        }
        C2401q50 c2401q50 = l70.i;
        if (c2401q50.f == null) {
            c2401q50.f = new C2315p50(c2401q50);
        }
        C2315p50<C1721i70> c2315p50 = c2401q50.f;
        KB.b(c2315p50, "tableBuilder.packageBuilderList");
        for (C1721i70 c1721i70 : c2315p50) {
            C2062m70 c2062m70 = c1721i70.g;
            if (c2062m70 == null) {
                c2062m70 = C2062m70.g;
            }
            Map map = (Map) linkedHashMap.get(Integer.valueOf(c2062m70.e));
            if (map != null) {
                if (c1721i70.j == null) {
                    c1721i70.j = new C2401q50(c1721i70.i, (c1721i70.f & 1) != 0, c1721i70.m(), c1721i70.d);
                    c1721i70.i = null;
                }
                C2401q50 c2401q51 = c1721i70.j;
                if (c2401q51.f == null) {
                    c2401q51.f = new C2315p50(c2401q51);
                }
                C2315p50<E80> c2315p51 = c2401q51.f;
                KB.b(c2315p51, "it.typeBuilderList");
                for (E80 e80 : c2315p51) {
                    I80 i80 = e80.g;
                    if (i80 == null) {
                        i80 = I80.g;
                    }
                    List list2 = (List) map.get(Integer.valueOf(i80.e));
                    if (list2 != null) {
                        if (e80.j == null) {
                            e80.j = new C2401q50(e80.i, (e80.f & 1) != 0, e80.m(), e80.d);
                            e80.i = null;
                        }
                        C2401q50 c2401q52 = e80.j;
                        if (c2401q52.f == null) {
                            c2401q52.f = new C2315p50(c2401q52);
                        }
                        C2315p50<D60> c2315p52 = c2401q52.f;
                        KB.b(c2315p52, "type.entryBuilderList");
                        for (D60 d60 : c2315p52) {
                            H60 h60 = d60.g;
                            if (h60 == null) {
                                h60 = H60.g;
                            }
                            if (list2.contains(Integer.valueOf(h60.e))) {
                                C2401q50 c2401q53 = d60.m;
                                if (c2401q53 == null) {
                                    d60.l = Collections.EMPTY_LIST;
                                    d60.f &= -2;
                                    d60.p();
                                } else {
                                    c2401q53.b = Collections.EMPTY_LIST;
                                    c2401q53.c = false;
                                    ArrayList<C2183nc0> arrayList2 = c2401q53.d;
                                    if (arrayList2 != null) {
                                        for (C2183nc0 c2183nc0 : arrayList2) {
                                            if (c2183nc0 != null) {
                                                c2183nc0.a = null;
                                            }
                                        }
                                        c2401q53.d = null;
                                    }
                                    if (c2401q53.e && (i0 = c2401q53.a) != null) {
                                        i0.a();
                                        c2401q53.e = false;
                                    }
                                    C2315p50 c2315p53 = c2401q53.f;
                                    if (c2315p53 != null) {
                                        c2315p53.a();
                                    }
                                }
                                if (z) {
                                    d60.h = E60.m.m();
                                    d60.p();
                                }
                                if (d60.k != null) {
                                    d60.k = null;
                                    d60.p();
                                }
                            }
                        }
                    }
                }
            }
        }
        M70 m70Q = l70.i();
        if (m70Q.a()) {
            return m70Q;
        }
        throw H0.c(m70Q);
    }

    public static final M70 a(M70 m70, List list) {
        KB.c(m70, "<this>");
        KB.c(list, "ids");
        return a(m70, list, false);
    }
}
