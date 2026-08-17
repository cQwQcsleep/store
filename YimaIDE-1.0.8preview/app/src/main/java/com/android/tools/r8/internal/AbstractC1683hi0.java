package com.android.tools.r8.internal;

import com.android.tools.r8.internal.AbstractC1597gi0;
import com.android.tools.r8.internal.AbstractC1683hi0;
import java.util.Iterator;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

/* JADX INFO: renamed from: com.android.tools.r8.internal.hi0, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public abstract class AbstractC1683hi0 {
    public static AbstractC1597gi0 a(Iterable iterable, BiFunction biFunction, Object obj) {
        AbstractC1597gi0 c1512fi0 = new C1512fi0(obj);
        Iterator it = iterable.iterator();
        while (it.hasNext()) {
            c1512fi0 = (AbstractC1597gi0) biFunction.apply(it.next(), c1512fi0.b().f());
            if (c1512fi0.c()) {
                break;
            }
        }
        return c1512fi0;
    }

    public static AbstractC1597gi0 b(C1131bA c1131bA, int i) {
        return AbstractC1597gi0.a(!(c1131bA.d() > i));
    }

    public static boolean a(final int i, Consumer consumer) {
        final C1131bA c1131bA = new C1131bA();
        consumer.accept(C0470Er.a(new Supplier() { // from class: n4h
            @Override // java.util.function.Supplier
            public final Object get() {
                return AbstractC1683hi0.a(c1131bA, i);
            }
        }));
        return c1131bA.a() == i;
    }

    public static AbstractC1597gi0 a(C1131bA c1131bA, int i) {
        return AbstractC1597gi0.a(!(c1131bA.d() > i));
    }

    public static boolean a(Consumer consumer) {
        final C1131bA c1131bA = new C1131bA();
        final int i = 2;
        consumer.accept(C0470Er.a(new Supplier() { // from class: l4h
            @Override // java.util.function.Supplier
            public final Object get() {
                return AbstractC1683hi0.b(c1131bA, i);
            }
        }));
        return c1131bA.a() > 2;
    }

    public static Object a(Function function) {
        return ((AbstractC1597gi0) function.apply(new Function() { // from class: m4h
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return AbstractC1597gi0.a(obj);
            }
        })).a().e();
    }
}
