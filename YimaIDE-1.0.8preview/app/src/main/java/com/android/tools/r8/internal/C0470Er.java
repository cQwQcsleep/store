package com.android.tools.r8.internal;

import java.util.Iterator;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

/* JADX INFO: renamed from: com.android.tools.r8.internal.Er, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public class C0470Er {
    public static void a(Iterable iterable, Function function, Object obj) {
        Iterator it = iterable.iterator();
        while (it.hasNext()) {
            ((Consumer) function.apply(it.next())).accept(obj);
        }
    }

    public static Object a(Object obj, Object obj2, Object obj3) {
        return obj;
    }

    public static Object a(Object obj, Function function) {
        if (obj != null) {
            return function.apply(obj);
        }
        return null;
    }

    public static Object a(Object obj, Object obj2) {
        return obj;
    }

    public static <T, R> Function<T, R> a(final Supplier<R> supplier) {
        return new Function() { // from class: oc4
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return supplier.get();
            }
        };
    }
}
