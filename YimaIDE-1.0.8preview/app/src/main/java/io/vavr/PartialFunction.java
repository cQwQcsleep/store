package io.vavr;

import defpackage.dza;
import io.vavr.control.Option;
import java.util.function.Function;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
public interface PartialFunction<T, R> extends Function1<T, R> {
    public static final long serialVersionUID = 1;

    static <T, V extends Value<T>> PartialFunction<V, T> getIfDefined() {
        return (PartialFunction<V, T>) new PartialFunction<V, T>() { // from class: io.vavr.PartialFunction.2
            private static final long serialVersionUID = 1;

            /* JADX WARN: Incorrect types in method signature: (TV;)TT; */
            @Override // io.vavr.PartialFunction, io.vavr.Function1, java.util.function.Function
            public Object apply(Value value) {
                return value.get();
            }

            /* JADX WARN: Incorrect types in method signature: (TV;)Z */
            @Override // io.vavr.PartialFunction
            public boolean isDefinedAt(Value value) {
                return !value.isEmpty();
            }
        };
    }

    static <T, R> PartialFunction<T, R> unlift(final Function<? super T, ? extends Option<? extends R>> function) {
        return new PartialFunction<T, R>() { // from class: io.vavr.PartialFunction.1
            private static final long serialVersionUID = 1;

            @Override // io.vavr.PartialFunction, io.vavr.Function1, java.util.function.Function
            public R apply(T t) {
                return (R) ((Option) function.apply(t)).get();
            }

            @Override // io.vavr.PartialFunction
            public boolean isDefinedAt(T t) {
                return ((Option) function.apply(t)).isDefined();
            }
        };
    }

    @Override // io.vavr.Function1, java.util.function.Function
    R apply(T t);

    boolean isDefinedAt(T t);

    default Function1<T, Option<R>> lift() {
        return new dza(this);
    }
}
