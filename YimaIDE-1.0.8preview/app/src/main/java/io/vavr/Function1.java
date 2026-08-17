package io.vavr;

import defpackage.al5;
import defpackage.bl5;
import defpackage.cl5;
import defpackage.el5;
import defpackage.fl5;
import defpackage.gl5;
import defpackage.yk5;
import defpackage.zk5;
import io.vavr.control.Option;
import io.vavr.control.Try;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.locks.ReentrantLock;
import java.util.function.Function;
import java.util.function.Predicate;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
@FunctionalInterface
public interface Function1<T1, R> extends Serializable, Function<T1, R> {
    public static final long serialVersionUID = 1;

    static /* synthetic */ Object O2(Object obj) {
        return obj;
    }

    /* JADX WARN: Multi-variable type inference failed */
    static /* synthetic */ Object c1(Function1 function1, Function function, Object obj) {
        function1.getClass();
        return function1.apply(function.apply(obj));
    }

    static <T1, R> Function1<T1, R> constant(R r) {
        return new fl5(r);
    }

    static /* synthetic */ Object ha(Object obj, Object obj2) {
        return obj;
    }

    static <T> Function1<T, T> identity() {
        return new cl5();
    }

    static <T1, R> Function1<T1, Option<R>> lift(Function<? super T1, ? extends R> function) {
        return new zk5(function);
    }

    static <T1, R> Function1<T1, Try<R>> liftTry(Function<? super T1, ? extends R> function) {
        return new yk5(function);
    }

    /* JADX WARN: Multi-variable type inference failed */
    static <T1, R> Function1<T1, R> narrow(Function1<? super T1, ? extends R> function1) {
        return function1;
    }

    static <T1, R> Function1<T1, R> of(Function1<T1, R> function1) {
        return function1;
    }

    /* JADX WARN: Multi-variable type inference failed */
    static /* synthetic */ Object pa(Function1 function1, Function1 function2, Object obj) {
        function1.getClass();
        return function1.apply(function2.apply(obj));
    }

    static /* synthetic */ Object s2(Function1 function1, Tuple1 tuple1) {
        function1.getClass();
        return function1.apply(tuple1._1);
    }

    static /* synthetic */ Object sa(Function1 function1, ReentrantLock reentrantLock, Map map, Object obj) {
        function1.getClass();
        reentrantLock.lock();
        try {
            if (map.containsKey(obj)) {
                return map.get(obj);
            }
            Object objApply = function1.apply(obj);
            map.put(obj, objApply);
            return objApply;
        } finally {
            reentrantLock.unlock();
        }
    }

    @Override // java.util.function.Function
    default <V> Function1<T1, V> andThen(Function<? super R, ? extends V> function) {
        Objects.requireNonNull(function, "after is null");
        return new gl5(this, function);
    }

    R apply(T1 t1);

    default int arity() {
        return 1;
    }

    @Override // java.util.function.Function
    default <V> Function1<V, R> compose(Function<? super V, ? extends T1> function) {
        Objects.requireNonNull(function, "before is null");
        return new al5(this, function);
    }

    default <S> Function1<S, R> compose1(Function1<? super S, ? extends T1> function1) {
        Objects.requireNonNull(function1, "before is null");
        return new el5(this, function1);
    }

    default Function1<T1, R> curried() {
        return this;
    }

    default boolean isMemoized() {
        return this instanceof Memoized;
    }

    default Function1<T1, R> memoized() {
        if (isMemoized()) {
            return this;
        }
        return new k(this, new ReentrantLock(), new HashMap());
    }

    default PartialFunction<T1, R> partial(final Predicate<? super T1> predicate) {
        Objects.requireNonNull(predicate, "isDefinedAt is null");
        return new PartialFunction<T1, R>() { // from class: io.vavr.Function1.1
            private static final long serialVersionUID = 1;

            @Override // io.vavr.PartialFunction, io.vavr.Function1, java.util.function.Function
            public R apply(T1 t1) {
                return (R) this.apply(t1);
            }

            @Override // io.vavr.PartialFunction
            public boolean isDefinedAt(T1 t1) {
                return predicate.test(t1);
            }
        };
    }

    default Function1<T1, R> reversed() {
        return this;
    }

    default Function1<Tuple1<T1>, R> tupled() {
        return new bl5(this);
    }
}
