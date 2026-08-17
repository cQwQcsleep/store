package com.android.tools.r8.graph;

import com.android.tools.r8.graph.C0231j1;
import com.android.tools.r8.graph.I4;
import com.android.tools.r8.internal.AbstractC1597gi0;
import com.android.tools.r8.internal.C1512fi0;
import java.util.Collection;
import java.util.Set;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public abstract class I4 {
    public static /* synthetic */ AbstractC1597gi0 a(Predicate predicate, Consumer consumer, C0231j1 c0231j1) {
        if (predicate.test(c0231j1)) {
            consumer.accept(c0231j1);
        }
        return C1512fi0.c;
    }

    public abstract I4 a(Function function);

    public abstract C0231j1 a(E2 e2, H2 h2);

    public abstract C0231j1 a(C0322w2 c0322w2);

    public abstract C0231j1 a(C0322w2 c0322w2, Function function);

    public abstract C0231j1 a(Predicate predicate);

    public abstract void a();

    public abstract void a(C0231j1 c0231j1);

    public abstract void a(Collection collection);

    public abstract void a(Set set);

    public abstract void a(C0231j1[] c0231j1Arr);

    public abstract C0231j1 b(C0322w2 c0322w2);

    public abstract C0231j1 b(C0322w2 c0322w2, Function function);

    public abstract C0231j1 b(Predicate predicate);

    public abstract void b();

    public abstract void b(C0231j1 c0231j1);

    public abstract void b(Collection collection);

    public final void b(Consumer consumer) {
        a(consumer, new Predicate() { // from class: qe6
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                return this.b.e((C0231j1) obj);
            }
        });
    }

    public abstract void b(Function function);

    public abstract void b(C0231j1[] c0231j1Arr);

    public abstract C0231j1 c(C0322w2 c0322w2);

    public abstract Iterable c();

    public abstract void c(C0231j1 c0231j1);

    public abstract void c(Function function);

    public abstract String d();

    public abstract void d(Function function);

    public final boolean d(C0231j1 c0231j1) {
        return c0231j1.g.F();
    }

    public abstract Iterable e();

    public abstract void e(Function function);

    public final boolean e(C0231j1 c0231j1) {
        return c0231j1.M0();
    }

    public abstract int f();

    public abstract void f(Function function);

    public abstract int g();

    public abstract AbstractC1597gi0 g(Function function);

    public abstract int h();

    public abstract void i();

    public abstract Iterable j();

    public final void a(final Consumer consumer, final Predicate predicate) {
        g(new Function() { // from class: pe6
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return I4.a(predicate, consumer, (C0231j1) obj);
            }
        });
    }

    public final void a(Consumer consumer) {
        a(consumer, new Predicate() { // from class: oe6
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                return this.b.d((C0231j1) obj);
            }
        });
    }
}
