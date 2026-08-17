package com.android.tools.r8.internal;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

/* JADX INFO: renamed from: com.android.tools.r8.internal.he, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public class C1674he {
    public static String[] a(List list, Function function) {
        String[] strArr = new String[list.size()];
        Iterator it = list.iterator();
        int i = 0;
        while (it.hasNext()) {
            strArr[i] = (String) function.apply(it.next());
            i++;
        }
        return strArr;
    }

    public static void a(UY uy, Consumer consumer, Predicate predicate) {
        for (Object obj : uy) {
            if (predicate.test(obj)) {
                return;
            } else {
                consumer.accept(obj);
            }
        }
    }

    public static <T> Collection<T> a(Collection<T> collection, Comparator<T> comparator) {
        ArrayList arrayList = new ArrayList(collection);
        arrayList.sort(comparator);
        return arrayList;
    }

    public static void a(Collection collection, Consumer consumer, Object obj) {
        for (Object obj2 : collection) {
            if (obj2.equals(obj)) {
                return;
            } else {
                consumer.accept(obj2);
            }
        }
    }
}
