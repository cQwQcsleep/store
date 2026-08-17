package org.eclipse.tm4e.core.internal.utils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import org.eclipse.tm4e.core.internal.utils.MoreCollections;

/* JADX INFO: loaded from: /workspace/dex_all/classes10.dex */
public final class MoreCollections {
    private MoreCollections() {
    }

    public static /* synthetic */ boolean a(Object obj) {
        return obj != null;
    }

    public static <T> List<T> asArrayList(T t, List<T> list) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(t);
        arrayList.addAll(list);
        return arrayList;
    }

    public static <T> T findFirstMatching(List<T> list, Predicate<T> predicate) {
        for (T t : list) {
            if (predicate.test(t)) {
                return t;
            }
        }
        return null;
    }

    public static <T> T findLastElement(List<T> list) {
        if (list.isEmpty()) {
            return null;
        }
        return (T) getLastElement(list);
    }

    public static <T> T getElementAt(List<T> list, int i) {
        return i < 0 ? list.get(list.size() + i) : list.get(i);
    }

    public static <T> T getLastElement(List<T> list) {
        return list.get(list.size() - 1);
    }

    public static <T> List<T> noNulls(List<T> list) {
        return (list == null || list.isEmpty()) ? Collections.EMPTY_LIST : (List) list.stream().filter(new Predicate() { // from class: y6a
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                return MoreCollections.a(obj);
            }
        }).collect(Collectors.toList());
    }

    public static <T> List<T> nullToEmpty(List<T> list) {
        return list == null ? Collections.EMPTY_LIST : list;
    }

    public static <T> T removeLastElement(List<T> list) {
        return list.remove(list.size() - 1);
    }

    public static String toStringWithIndex(List<?> list) {
        if (list.isEmpty()) {
            return "[]";
        }
        StringBuilder sb = new StringBuilder("[");
        int size = list.size();
        for (int i = 0; i < size; i++) {
            Object obj = list.get(i);
            sb.append(i);
            sb.append(':');
            if (obj == list) {
                obj = "(this List)";
            }
            sb.append(obj);
            if (i == size - 1) {
                break;
            }
            sb.append(", ");
        }
        sb.append(']');
        return sb.toString();
    }

    public static <T> List<T> asArrayList(T t) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(t);
        return arrayList;
    }
}
