package com.android.tools.r8.dex;

import com.android.tools.r8.dex.p0;
import com.android.tools.r8.graph.D2;
import com.android.tools.r8.internal.Vd0;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class p0 {
    public final List a;
    public final List b;

    public p0(ArrayList arrayList, ArrayList arrayList2) {
        this.a = arrayList;
        this.b = arrayList2;
    }

    public static p0 a(Collection collection, Map map, Vd0 vd0) {
        Comparator comparatorA = a(map);
        Predicate predicateA = a(vd0);
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList(collection.size());
        Iterator it = collection.iterator();
        while (it.hasNext()) {
            D2 d2 = (D2) it.next();
            if (predicateA.test(d2)) {
                arrayList.add(d2);
            } else {
                arrayList2.add(d2);
            }
        }
        arrayList.sort(comparatorA);
        arrayList2.sort(comparatorA);
        return new p0(arrayList, arrayList2);
    }

    public static Comparator a(final Map map) {
        return new Comparator() { // from class: szh
            @Override // java.util.Comparator
            public final int compare(Object obj, Object obj2) {
                return p0.a(map, (D2) obj, (D2) obj2);
            }
        };
    }

    public static /* synthetic */ int a(Map map, D2 d2, D2 d3) {
        String str = (String) map.get(d2);
        String str2 = (String) map.get(d3);
        int iLastIndexOf = str.lastIndexOf(46);
        int iLastIndexOf2 = str2.lastIndexOf(46);
        if (iLastIndexOf == -1 && iLastIndexOf2 == -1) {
            return str.compareTo(str2);
        }
        if (iLastIndexOf == -1) {
            return -1;
        }
        if (iLastIndexOf2 == -1) {
            return 1;
        }
        int iCompareTo = str.substring(0, iLastIndexOf).compareTo(str2.substring(0, iLastIndexOf2));
        return iCompareTo != 0 ? iCompareTo : str.compareTo(str2);
    }

    public static Predicate a(final Vd0 vd0) {
        return new Predicate() { // from class: uzh
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                return vd0.b(((D2) obj).getType());
            }
        };
    }
}
