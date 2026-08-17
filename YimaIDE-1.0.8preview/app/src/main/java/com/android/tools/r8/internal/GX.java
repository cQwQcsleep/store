package com.android.tools.r8.internal;

import com.android.tools.r8.internal.GX;
import java.util.function.Function;
import java.util.function.Predicate;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public class GX {
    public static Object a(Object[] objArr, Predicate predicate) {
        for (Object obj : objArr) {
            if (predicate.test(obj)) {
                return obj;
            }
        }
        return null;
    }

    public static /* synthetic */ boolean a(Predicate predicate, Object obj) {
        return !predicate.test(obj);
    }

    public static <T> Predicate<T> a(final Predicate<T> predicate) {
        return new Predicate() { // from class: xx5
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                return GX.a(predicate, obj);
            }
        };
    }

    public static Predicate a(final Function function) {
        return new Predicate() { // from class: wx5
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                return GX.a(function, obj);
            }
        };
    }

    public static /* synthetic */ boolean a(Function function, Object obj) {
        return function.apply(obj) == null;
    }
}
