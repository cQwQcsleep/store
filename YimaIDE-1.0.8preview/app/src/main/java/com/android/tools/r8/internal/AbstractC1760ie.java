package com.android.tools.r8.internal;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import org.xmlpull.v1.XmlPullParser;

/* JADX INFO: renamed from: com.android.tools.r8.internal.ie, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public abstract class AbstractC1760ie extends AbstractC2358pe {
    public static String a(Iterable iterable, String str, String str2, String str3, InterfaceC1439er interfaceC1439er, int i) {
        if ((i & 1) != 0) {
            str = ", ";
        }
        if ((i & 2) != 0) {
            str2 = XmlPullParser.NO_NAMESPACE;
        }
        if ((i & 4) != 0) {
            str3 = XmlPullParser.NO_NAMESPACE;
        }
        if ((i & 32) != 0) {
            interfaceC1439er = null;
        }
        KB.c(iterable, "<this>");
        KB.c(str, "separator");
        KB.c(str2, "prefix");
        KB.c(str3, "postfix");
        StringBuilder sb = new StringBuilder();
        sb.append((CharSequence) str2);
        Iterator it = iterable.iterator();
        int i2 = 0;
        while (it.hasNext()) {
            Object next = it.next();
            i2++;
            if (i2 > 1) {
                sb.append((CharSequence) str);
            }
            if (interfaceC1439er != null) {
                sb.append((CharSequence) interfaceC1439er.b(next));
            } else {
                if (next != null ? next instanceof CharSequence : true) {
                    sb.append((CharSequence) next);
                } else if (next instanceof Character) {
                    sb.append(((Character) next).charValue());
                } else {
                    sb.append((CharSequence) String.valueOf(next));
                }
            }
        }
        sb.append((CharSequence) str3);
        String string = sb.toString();
        KB.b(string, "toString(...)");
        return string;
    }

    public static List b(Iterable iterable) {
        ArrayList arrayList;
        KB.c(iterable, "<this>");
        boolean z = iterable instanceof Collection;
        if (z) {
            Collection collection = (Collection) iterable;
            if (collection.size() <= 1) {
                return c(iterable);
            }
            Object[] array = collection.toArray(new Comparable[0]);
            Comparable[] comparableArr = (Comparable[]) array;
            KB.c(comparableArr, "<this>");
            if (comparableArr.length > 1) {
                Arrays.sort(comparableArr);
            }
            return T3.a(array);
        }
        if (z) {
            arrayList = new ArrayList((Collection) iterable);
        } else {
            arrayList = new ArrayList();
            Iterator it = iterable.iterator();
            while (it.hasNext()) {
                arrayList.add(it.next());
            }
        }
        if (arrayList.size() > 1) {
            Collections.sort(arrayList);
        }
        return arrayList;
    }

    public static List c(Iterable iterable) {
        ArrayList arrayList;
        KB.c(iterable, "<this>");
        boolean z = iterable instanceof Collection;
        if (z) {
            Collection collection = (Collection) iterable;
            int size = collection.size();
            if (size == 0) {
                return C0984Ym.b;
            }
            if (size != 1) {
                return new ArrayList(collection);
            }
            List listSingletonList = Collections.singletonList(iterable instanceof List ? ((List) iterable).get(0) : iterable.iterator().next());
            KB.b(listSingletonList, "singletonList(...)");
            return listSingletonList;
        }
        if (z) {
            arrayList = new ArrayList((Collection) iterable);
        } else {
            arrayList = new ArrayList();
            Iterator it = iterable.iterator();
            while (it.hasNext()) {
                arrayList.add(it.next());
            }
        }
        int size2 = arrayList.size();
        if (size2 == 0) {
            return C0984Ym.b;
        }
        if (size2 != 1) {
            return arrayList;
        }
        List listSingletonList2 = Collections.singletonList(arrayList.get(0));
        KB.b(listSingletonList2, "singletonList(...)");
        return listSingletonList2;
    }

    public static List a(Collection collection, Comparator comparator) {
        if (collection.size() <= 1) {
            return c(collection);
        }
        Object[] array = collection.toArray(new Object[0]);
        KB.c(array, "<this>");
        if (array.length > 1) {
            Arrays.sort(array, comparator);
        }
        return T3.a(array);
    }

    public static C2443qe a(List list) {
        return new C2443qe(list);
    }
}
