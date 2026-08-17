package io.vavr.collection;

import io.vavr.control.Option;
import java.util.Objects;
import java.util.function.BiFunction;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
public interface Foldable<T> {
    /* JADX WARN: Multi-variable type inference failed */
    default T fold(T t, BiFunction<? super T, ? super T, ? extends T> biFunction) {
        Objects.requireNonNull(biFunction, "combine is null");
        return (T) foldLeft(t, biFunction);
    }

    <U> U foldLeft(U u, BiFunction<? super U, ? super T, ? extends U> biFunction);

    <U> U foldRight(U u, BiFunction<? super T, ? super U, ? extends U> biFunction);

    default T reduce(BiFunction<? super T, ? super T, ? extends T> biFunction) {
        Objects.requireNonNull(biFunction, "op is null");
        return reduceLeft(biFunction);
    }

    T reduceLeft(BiFunction<? super T, ? super T, ? extends T> biFunction);

    Option<T> reduceLeftOption(BiFunction<? super T, ? super T, ? extends T> biFunction);

    default Option<T> reduceOption(BiFunction<? super T, ? super T, ? extends T> biFunction) {
        Objects.requireNonNull(biFunction, "op is null");
        return reduceLeftOption(biFunction);
    }

    T reduceRight(BiFunction<? super T, ? super T, ? extends T> biFunction);

    Option<T> reduceRightOption(BiFunction<? super T, ? super T, ? extends T> biFunction);
}
