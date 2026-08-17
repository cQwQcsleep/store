package com.android.tools.r8.internal;

import com.android.tools.r8.internal.Sm0;
import defpackage.v36;
import java.util.ArrayDeque;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public class Sm0<T> {
    public final ArrayDeque a;
    public final Set b;

    public Sm0(int i) {
        this(i == 1 ? new HashSet() : AbstractC2780ub0.c());
    }

    public static <T> Sm0<T> c(Iterable<T> iterable) {
        Sm0<T> sm0 = new Sm0<>(1);
        sm0.b((Iterable) iterable);
        return sm0;
    }

    public final AbstractC1597gi0 a(BiFunction biFunction) {
        while (b()) {
            AbstractC1597gi0 abstractC1597gi0 = (AbstractC1597gi0) biFunction.apply(d(), this);
            if (abstractC1597gi0.c()) {
                return abstractC1597gi0;
            }
        }
        return C1512fi0.c;
    }

    public boolean b(T t) {
        if (!this.b.add(t)) {
            return false;
        }
        this.a.addLast(t);
        return true;
    }

    public final boolean d(Object obj) {
        return this.b.add(obj);
    }

    public final Object e() {
        return this.a.removeLast();
    }

    public T d() {
        return (T) this.a.removeFirst();
    }

    public static Sm0 c() {
        return new Sm0(2);
    }

    public final void c(Object obj) {
        this.a.addLast(obj);
    }

    public Sm0(Set set) {
        this.a = new ArrayDeque();
        this.b = set;
    }

    public void b(Iterable<? extends T> iterable) {
        iterable.forEach(new v36(this));
    }

    public boolean b() {
        return !this.a.isEmpty();
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final void a(Object[] objArr) {
        for (Object obj : objArr) {
            b(obj);
        }
    }

    public final boolean a(Object obj) {
        if (!this.b.add(obj)) {
            return false;
        }
        this.a.addFirst(obj);
        return true;
    }

    public final void a(final Consumer consumer) {
        a(new BiConsumer() { // from class: qgd
            @Override // java.util.function.BiConsumer
            public final void accept(Object obj, Object obj2) {
                consumer.accept(obj);
            }
        });
    }

    public final Sm0 a(BiConsumer biConsumer) {
        while (b()) {
            biConsumer.accept(d(), this);
        }
        return this;
    }

    public static /* synthetic */ AbstractC1597gi0 a(Function function, Object obj, Sm0 sm0) {
        return (AbstractC1597gi0) function.apply(obj);
    }

    public final AbstractC1597gi0 a(final Function function) {
        return a(new BiFunction() { // from class: ngd
            @Override // java.util.function.BiFunction
            public final Object apply(Object obj, Object obj2) {
                return Sm0.a(function, obj, (Sm0) obj2);
            }
        });
    }

    public final void a(Iterable iterable) {
        final ArrayDeque arrayDeque = this.a;
        Objects.requireNonNull(arrayDeque);
        iterable.forEach(new Consumer() { // from class: ogd
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                arrayDeque.addLast(obj);
            }
        });
    }

    public final void a(Set set) {
        set.forEach(new Consumer() { // from class: pgd
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                this.b.d(obj);
            }
        });
    }

    public Set<T> a() {
        return C1755ib0.a(this.b);
    }
}
