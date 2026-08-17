package com.android.tools.r8.internal;

import com.android.tools.r8.internal.AbstractC2956we;
import defpackage.rr9;
import defpackage.xei;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: renamed from: com.android.tools.r8.internal.we, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public abstract class AbstractC2956we {
    public static final /* synthetic */ boolean a = true;

    public static int a(InterfaceC2045lz interfaceC2045lz, InterfaceC2045lz interfaceC2045lz2, Comparator comparator) {
        int iCompare = Integer.compare(interfaceC2045lz.size(), interfaceC2045lz2.size());
        if (iCompare != 0) {
            return iCompare;
        }
        if (interfaceC2045lz.isEmpty()) {
            if (!a && !interfaceC2045lz2.isEmpty()) {
                x1f.a();
            }
            return 0;
        }
        Integer numA = a(interfaceC2045lz, interfaceC2045lz2);
        Integer numA2 = a(interfaceC2045lz2, interfaceC2045lz);
        if (numA != null) {
            if (a || numA2 != null) {
                return numA.compareTo(numA2);
            }
            x1f.a();
            return 0;
        }
        ArrayList arrayList = new ArrayList(interfaceC2045lz.keySet());
        arrayList.sort(new rr9());
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            int iIntValue = ((Integer) it.next()).intValue();
            int iCompare2 = comparator.compare(interfaceC2045lz.get(iIntValue), interfaceC2045lz2.get(iIntValue));
            if (iCompare2 != 0) {
                return iCompare2;
            }
        }
        return 0;
    }

    public static Comparator b(final Comparator comparator) {
        return new Comparator() { // from class: uoi
            @Override // java.util.Comparator
            public final int compare(Object obj, Object obj2) {
                return AbstractC2956we.a(comparator, (List) obj, (List) obj2);
            }
        };
    }

    public static int a(Comparator comparator, List list, List list2) {
        int iCompare = Integer.compare(list.size(), list2.size());
        for (int i = 0; i < list.size() && iCompare == 0; i++) {
            iCompare = comparator.compare(list.get(i), list2.get(i));
        }
        return iCompare;
    }

    public static int a(int[] iArr, int[] iArr2) {
        int iCompare = Integer.compare(iArr.length, iArr2.length);
        for (int i = 0; i < iArr.length && iCompare == 0; i++) {
            iCompare = Integer.compare(iArr[i], iArr2[i]);
        }
        return iCompare;
    }

    public static Comparator a(final Comparator comparator) {
        return new Comparator() { // from class: toi
            @Override // java.util.Comparator
            public final int compare(Object obj, Object obj2) {
                return AbstractC2956we.a(comparator, (Object[]) obj, (Object[]) obj2);
            }
        };
    }

    public static /* synthetic */ int a(Comparator comparator, Object[] objArr, Object[] objArr2) {
        int iCompare = Integer.compare(objArr.length, objArr2.length);
        for (int i = 0; i < objArr.length && iCompare == 0; i++) {
            iCompare = comparator.compare(objArr[i], objArr2[i]);
        }
        return iCompare;
    }

    public static int a(List list, List list2) {
        xei xeiVar = new xei();
        int iCompare = Integer.compare(list.size(), list2.size());
        for (int i = 0; i < list.size() && iCompare == 0; i++) {
            iCompare = xeiVar.compare(list.get(i), list2.get(i));
        }
        return iCompare;
    }

    public static Integer a(InterfaceC2045lz interfaceC2045lz, InterfaceC2045lz interfaceC2045lz2) {
        InterfaceC1640hA it = interfaceC2045lz.keySet().iterator();
        boolean z = false;
        int iMin = Integer.MAX_VALUE;
        while (it.hasNext()) {
            int iIntValue = ((Integer) it.next()).intValue();
            if (!interfaceC2045lz2.a(iIntValue)) {
                iMin = z ? Math.min(iMin, iIntValue) : iIntValue;
                z = true;
            }
        }
        if (z) {
            return Integer.valueOf(iMin);
        }
        return null;
    }
}
