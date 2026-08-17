package com.android.tools.r8.internal;

import java.util.Collection;
import java.util.Iterator;

/* JADX INFO: renamed from: com.android.tools.r8.internal.zC, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public abstract class AbstractC3179zC {
    public static Object a(Iterable iterable) {
        Iterator it = iterable.iterator();
        Object next = it.next();
        if (!it.hasNext()) {
            return next;
        }
        StringBuilder sb = new StringBuilder("expected one element but was: <");
        sb.append(next);
        for (int i = 0; i < 4 && it.hasNext(); i++) {
            sb.append(", ");
            sb.append(it.next());
        }
        if (it.hasNext()) {
            sb.append(", ...");
        }
        sb.append('>');
        throw new IllegalArgumentException(sb.toString());
    }

    /* JADX WARN: Code duplicated, block: B:13:0x0020 A[RETURN] */
    /* JADX WARN: Code duplicated, block: B:15:0x0022 A[RETURN] */
    public static boolean b(Iterable iterable, EX ex) {
        Iterator it = iterable.iterator();
        if (ex == null) {
            x0e.a("predicate");
            return false;
        }
        int i = 0;
        while (it.hasNext()) {
            if (ex.apply(it.next())) {
                if (i != -1) {
                    return true;
                }
                return false;
            }
            i++;
        }
        i = -1;
        if (i != -1) {
            return true;
        }
        return false;
    }

    public static int c(Iterable iterable) {
        if (iterable instanceof Collection) {
            return ((Collection) iterable).size();
        }
        Iterator it = iterable.iterator();
        long j = 0;
        while (it.hasNext()) {
            it.next();
            j++;
        }
        return MB.a(j);
    }

    public static Object d(Iterable iterable, EX ex) {
        Iterator it = iterable.iterator();
        it.getClass();
        ex.getClass();
        while (it.hasNext()) {
            Object next = it.next();
            if (ex.apply(next)) {
                return next;
            }
        }
        return null;
    }

    public static C3094yC d(Iterable iterable) {
        iterable.getClass();
        return new C3094yC(iterable);
    }

    public static C2838vC c(Iterable iterable, EX ex) {
        iterable.getClass();
        ex.getClass();
        return new C2838vC(iterable, ex);
    }

    public static boolean b(Iterable iterable) {
        if (iterable instanceof Collection) {
            return ((Collection) iterable).isEmpty();
        }
        return !iterable.iterator().hasNext();
    }

    public static C0702Nq a(Iterable iterable, Iterable iterable2) {
        return AbstractC0728Oq.a(iterable, iterable2);
    }

    public static void a(Iterable iterable, Collection collection) {
        if (iterable instanceof Collection) {
            collection.addAll((Collection) iterable);
        } else {
            iterable.getClass();
            NC.a(collection, iterable.iterator());
        }
    }

    public static boolean a(Iterable iterable, EX ex) {
        Iterator it = iterable.iterator();
        ex.getClass();
        while (it.hasNext()) {
            if (!ex.apply(it.next())) {
                return false;
            }
        }
        return true;
    }

    public static C2924wC a(Iterable iterable, InterfaceC0392Br interfaceC0392Br) {
        iterable.getClass();
        interfaceC0392Br.getClass();
        return new C2924wC(iterable, interfaceC0392Br);
    }
}
