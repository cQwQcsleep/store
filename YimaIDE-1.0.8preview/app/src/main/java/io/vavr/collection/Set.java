package io.vavr.collection;

import io.vavr.Function1;
import io.vavr.PartialFunction;
import io.vavr.Tuple2;
import io.vavr.Tuple3;
import io.vavr.collection.Set;
import io.vavr.control.Option;
import java.io.Serializable;
import java.util.Comparator;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
public interface Set<T> extends Traversable<T>, Function1<T, Boolean>, Serializable {
    public static final long serialVersionUID = 1;

    static /* synthetic */ Object U5(Object obj, Object obj2) {
        return obj;
    }

    /* JADX WARN: Multi-variable type inference failed */
    static <T> Set<T> narrow(Set<? extends T> set) {
        return set;
    }

    static /* synthetic */ Void p9(Object obj) {
        return null;
    }

    Set<T> add(T t);

    Set<T> addAll(Iterable<? extends T> iterable);

    @Override // io.vavr.Function1, java.util.function.Function
    @Deprecated
    default Boolean apply(T t) {
        return Boolean.valueOf(contains(t));
    }

    @Override // io.vavr.collection.Traversable
    <R> Set<R> collect(PartialFunction<? super T, ? extends R> partialFunction);

    boolean contains(T t);

    Set<T> diff(Set<? extends T> set);

    @Override // io.vavr.collection.Traversable
    Set<T> distinct();

    @Override // io.vavr.collection.Traversable
    Set<T> distinctBy(Comparator<? super T> comparator);

    @Override // io.vavr.collection.Traversable
    <U> Set<T> distinctBy(Function<? super T, ? extends U> function);

    @Override // io.vavr.collection.Traversable
    Set<T> drop(int i);

    @Override // io.vavr.collection.Traversable
    Set<T> dropRight(int i);

    @Override // io.vavr.collection.Traversable
    Set<T> dropUntil(Predicate<? super T> predicate);

    @Override // io.vavr.collection.Traversable
    Set<T> dropWhile(Predicate<? super T> predicate);

    @Override // io.vavr.collection.Traversable
    Set<T> filter(Predicate<? super T> predicate);

    @Override // io.vavr.collection.Traversable
    <U> Set<U> flatMap(Function<? super T, ? extends Iterable<? extends U>> function);

    @Override // io.vavr.collection.Traversable
    <C> Map<C, ? extends Set<T>> groupBy(Function<? super T, ? extends C> function);

    @Override // io.vavr.collection.Traversable
    Iterator<? extends Set<T>> grouped(int i);

    @Override // io.vavr.collection.Traversable
    Set<T> init();

    @Override // io.vavr.collection.Traversable
    Option<? extends Set<T>> initOption();

    Set<T> intersect(Set<? extends T> set);

    @Override // io.vavr.collection.Traversable
    default boolean isDistinct() {
        return true;
    }

    @Override // io.vavr.collection.Traversable, io.vavr.Value, java.lang.Iterable
    Iterator<T> iterator();

    @Override // io.vavr.collection.Traversable
    int length();

    @Override // io.vavr.collection.Traversable, io.vavr.Value
    <U> Set<U> map(Function<? super T, ? extends U> function);

    @Override // io.vavr.collection.Traversable, io.vavr.Value
    default <U> Set<U> mapTo(final U u) {
        return map((Function) new Function() { // from class: k8d
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return Set.U5(u, obj);
            }
        });
    }

    @Override // io.vavr.collection.Traversable, io.vavr.Value
    default Set<Void> mapToVoid() {
        return map((Function) new Function() { // from class: l8d
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return Set.p9(obj);
            }
        });
    }

    @Override // io.vavr.collection.Traversable
    Set<T> orElse(Iterable<? extends T> iterable);

    @Override // io.vavr.collection.Traversable
    Set<T> orElse(Supplier<? extends Iterable<? extends T>> supplier);

    @Override // io.vavr.collection.Traversable
    Tuple2<? extends Set<T>, ? extends Set<T>> partition(Predicate<? super T> predicate);

    @Override // io.vavr.collection.Traversable, io.vavr.Value
    Set<T> peek(Consumer<? super T> consumer);

    @Override // io.vavr.collection.Traversable
    Set<T> reject(Predicate<? super T> predicate);

    Set<T> remove(T t);

    Set<T> removeAll(Iterable<? extends T> iterable);

    @Override // io.vavr.collection.Traversable
    Set<T> replace(T t, T t2);

    @Override // io.vavr.collection.Traversable
    Set<T> replaceAll(T t, T t2);

    @Override // io.vavr.collection.Traversable
    Set<T> retainAll(Iterable<? extends T> iterable);

    @Override // io.vavr.collection.Traversable
    Set<T> scan(T t, BiFunction<? super T, ? super T, ? extends T> biFunction);

    /* JADX WARN: Multi-variable type inference failed */
    @Override // io.vavr.collection.Traversable
    /* bridge */ /* synthetic */ default Traversable scan(Object obj, BiFunction biFunction) {
        return scan(obj, (BiFunction<? super Object, ? super Object, ? extends Object>) biFunction);
    }

    @Override // io.vavr.collection.Traversable
    <U> Set<U> scanLeft(U u, BiFunction<? super U, ? super T, ? extends U> biFunction);

    @Override // io.vavr.collection.Traversable
    /* bridge */ /* synthetic */ default Traversable scanLeft(Object obj, BiFunction biFunction) {
        return scanLeft(obj, (BiFunction<? super Object, ? super T, ? extends Object>) biFunction);
    }

    @Override // io.vavr.collection.Traversable
    <U> Set<U> scanRight(U u, BiFunction<? super T, ? super U, ? extends U> biFunction);

    @Override // io.vavr.collection.Traversable
    /* bridge */ /* synthetic */ default Traversable scanRight(Object obj, BiFunction biFunction) {
        return scanRight(obj, (BiFunction<? super T, ? super Object, ? extends Object>) biFunction);
    }

    @Override // io.vavr.collection.Traversable
    Iterator<? extends Set<T>> slideBy(Function<? super T, ?> function);

    @Override // io.vavr.collection.Traversable
    Iterator<? extends Set<T>> sliding(int i);

    @Override // io.vavr.collection.Traversable
    Iterator<? extends Set<T>> sliding(int i, int i2);

    @Override // io.vavr.collection.Traversable
    Tuple2<? extends Set<T>, ? extends Set<T>> span(Predicate<? super T> predicate);

    @Override // io.vavr.collection.Traversable
    Set<T> tail();

    @Override // io.vavr.collection.Traversable
    Option<? extends Set<T>> tailOption();

    @Override // io.vavr.collection.Traversable
    Set<T> take(int i);

    @Override // io.vavr.collection.Traversable
    Set<T> takeRight(int i);

    @Override // io.vavr.collection.Traversable
    Set<T> takeUntil(Predicate<? super T> predicate);

    @Override // io.vavr.collection.Traversable
    Set<T> takeWhile(Predicate<? super T> predicate);

    java.util.Set<T> toJavaSet();

    Set<T> union(Set<? extends T> set);

    @Override // io.vavr.collection.Traversable
    <T1, T2> Tuple2<? extends Set<T1>, ? extends Set<T2>> unzip(Function<? super T, Tuple2<? extends T1, ? extends T2>> function);

    @Override // io.vavr.collection.Traversable
    <T1, T2, T3> Tuple3<? extends Set<T1>, ? extends Set<T2>, ? extends Set<T3>> unzip3(Function<? super T, Tuple3<? extends T1, ? extends T2, ? extends T3>> function);

    @Override // io.vavr.collection.Traversable
    <U> Set<Tuple2<T, U>> zip(Iterable<? extends U> iterable);

    @Override // io.vavr.collection.Traversable
    <U> Set<Tuple2<T, U>> zipAll(Iterable<? extends U> iterable, T t, U u);

    /* JADX WARN: Multi-variable type inference failed */
    @Override // io.vavr.collection.Traversable
    /* bridge */ /* synthetic */ default Traversable zipAll(Iterable iterable, Object obj, Object obj2) {
        return zipAll((Iterable<? extends Object>) iterable, obj, obj2);
    }

    @Override // io.vavr.collection.Traversable
    <U, R> Set<R> zipWith(Iterable<? extends U> iterable, BiFunction<? super T, ? super U, ? extends R> biFunction);

    @Override // io.vavr.collection.Traversable
    Set<Tuple2<T, Integer>> zipWithIndex();

    @Override // io.vavr.collection.Traversable
    <U> Set<U> zipWithIndex(BiFunction<? super T, ? super Integer, ? extends U> biFunction);
}
