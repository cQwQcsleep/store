package com.android.tools.r8.internal;

import com.android.tools.r8.internal.C2753uC;
import defpackage.wk8;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Objects;
import java.util.function.BiPredicate;
import java.util.function.Function;
import java.util.function.Predicate;

/* JADX INFO: renamed from: com.android.tools.r8.internal.uC, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public class C2753uC {
    public static boolean a(BiPredicate biPredicate, C2924wC c2924wC, C2924wC c2924wC2) {
        GC gcA = NC.a(c2924wC2.b.iterator(), c2924wC2.c);
        GC gcA2 = NC.a(c2924wC.b.iterator(), c2924wC.c);
        while (gcA2.b.hasNext()) {
            Object objA = gcA2.a(gcA2.b.next());
            if (!gcA.b.hasNext() || !biPredicate.test(objA, gcA.a(gcA.b.next()))) {
                return false;
            }
        }
        return !gcA.b.hasNext();
    }

    public static Object b(Iterable iterable, Predicate predicate) {
        for (Object obj : iterable) {
            if (predicate.test(obj)) {
                return obj;
            }
        }
        return null;
    }

    public static <T> int c(Iterable<T> iterable, Predicate<T> predicate) {
        Iterator<T> it = iterable.iterator();
        int i = 0;
        while (it.hasNext()) {
            if (predicate.test(it.next())) {
                return i;
            }
            i++;
        }
        return -1;
    }

    public static Iterator d(Iterable iterable, Predicate predicate) {
        return new AC(iterable.iterator(), predicate);
    }

    public static ArrayList b(Iterable iterable) {
        ArrayList arrayList = new ArrayList();
        iterable.forEach(new wk8(arrayList));
        return arrayList;
    }

    public static Iterable b(final Object obj) {
        return new Iterable() { // from class: ggi
            @Override // java.lang.Iterable
            public final Iterator iterator() {
                return C2753uC.a(obj);
            }
        };
    }

    public static <T, U> Iterable<U> b(Iterable<T> iterable, final Function<? super T, Iterable<U>> function) {
        Objects.requireNonNull(function);
        return new C0651Lq(AbstractC3179zC.a(iterable, new InterfaceC0392Br() { // from class: hgi
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return (Iterable) function.apply(obj);
            }
        }));
    }

    public static C2924wC c(Iterable iterable, final Function function) {
        Objects.requireNonNull(function);
        return AbstractC3179zC.a(iterable, new InterfaceC0392Br() { // from class: igi
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return function.apply(obj);
            }
        });
    }

    public static boolean a(Iterable iterable, Function function, Predicate predicate) {
        Iterator it = iterable.iterator();
        while (it.hasNext()) {
            if (predicate.test(function.apply(it.next()))) {
                return true;
            }
        }
        return false;
    }

    public static boolean a(LinkedList linkedList, Predicate predicate, Predicate predicate2) {
        for (Object obj : linkedList) {
            if (predicate2.test(obj)) {
                return false;
            }
            if (predicate.test(obj)) {
                return true;
            }
        }
        return false;
    }

    public static Iterable a(final Iterable iterable, final Predicate predicate) {
        return new Iterable() { // from class: fgi
            @Override // java.lang.Iterable
            public final Iterator iterator() {
                return C2753uC.d(iterable, predicate);
            }
        };
    }

    public static <T> int a(Iterable<T> iterable) {
        Iterator<T> it = iterable.iterator();
        int i = 0;
        while (it.hasNext()) {
            it.next();
            i++;
        }
        return i;
    }

    public static Iterable a(AbstractC3066xu abstractC3066xu) {
        return b(abstractC3066xu, Function.identity());
    }

    public static boolean a(Iterable iterable, Function function) {
        Iterator it = iterable.iterator();
        if (!it.hasNext()) {
            return true;
        }
        Object objApply = function.apply(it.next());
        while (it.hasNext()) {
            if (function.apply(it.next()) != objApply) {
                return false;
            }
        }
        return true;
    }

    public static Iterator a(Object obj) {
        return new IC(obj);
    }
}
