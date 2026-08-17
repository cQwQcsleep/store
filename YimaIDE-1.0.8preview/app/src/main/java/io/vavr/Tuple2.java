package io.vavr;

import defpackage.bse;
import io.vavr.collection.List;
import io.vavr.collection.Seq;
import java.io.Serializable;
import java.util.AbstractMap;
import java.util.Comparator;
import java.util.Map;
import java.util.Objects;
import java.util.function.BiFunction;
import java.util.function.Function;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
public final class Tuple2<T1, T2> implements Tuple, Comparable<Tuple2<T1, T2>>, Serializable {
    private static final long serialVersionUID = 1;
    public final T1 _1;
    public final T2 _2;

    public Tuple2(T1 t1, T2 t2) {
        this._1 = t1;
        this._2 = t2;
    }

    public static /* synthetic */ int b(Comparator comparator, Comparator comparator2, Tuple2 tuple2, Tuple2 tuple3) {
        int iCompare = comparator.compare(tuple2._1, tuple3._1);
        if (iCompare != 0) {
            return iCompare;
        }
        int iCompare2 = comparator2.compare(tuple2._2, tuple3._2);
        if (iCompare2 != 0) {
            return iCompare2;
        }
        return 0;
    }

    public static <T1, T2> Comparator<Tuple2<T1, T2>> comparator(Comparator<? super T1> comparator, Comparator<? super T2> comparator2) {
        return new bse(comparator, comparator2);
    }

    private static <U1 extends Comparable<? super U1>, U2 extends Comparable<? super U2>> int compareTo(Tuple2<?, ?> tuple2, Tuple2<?, ?> tuple3) {
        int iCompareTo = ((Comparable) tuple2._1).compareTo(tuple3._1);
        if (iCompareTo != 0) {
            return iCompareTo;
        }
        int iCompareTo2 = ((Comparable) tuple2._2).compareTo(tuple3._2);
        if (iCompareTo2 != 0) {
            return iCompareTo2;
        }
        return 0;
    }

    public T1 _1() {
        return this._1;
    }

    public T2 _2() {
        return this._2;
    }

    public <T3> Tuple3<T1, T2, T3> append(T3 t3) {
        return Tuple.of(this._1, this._2, t3);
    }

    public <U> U apply(BiFunction<? super T1, ? super T2, ? extends U> biFunction) {
        Objects.requireNonNull(biFunction, "f is null");
        return biFunction.apply(this._1, this._2);
    }

    @Override // io.vavr.Tuple
    public int arity() {
        return 2;
    }

    public <T3, T4, T5, T6, T7, T8> Tuple8<T1, T2, T3, T4, T5, T6, T7, T8> concat(Tuple6<T3, T4, T5, T6, T7, T8> tuple6) {
        Objects.requireNonNull(tuple6, "tuple is null");
        return Tuple.of(this._1, this._2, tuple6._1, tuple6._2, tuple6._3, tuple6._4, tuple6._5, tuple6._6);
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof Tuple2)) {
            return false;
        }
        Tuple2 tuple2 = (Tuple2) obj;
        return Objects.equals(this._1, tuple2._1) && Objects.equals(this._2, tuple2._2);
    }

    public int hashCode() {
        return Tuple.hash(this._1, this._2);
    }

    public <U1, U2> Tuple2<U1, U2> map(Function<? super T1, ? extends U1> function, Function<? super T2, ? extends U2> function2) {
        Objects.requireNonNull(function, "f1 is null");
        Objects.requireNonNull(function2, "f2 is null");
        return Tuple.of(function.apply(this._1), function2.apply(this._2));
    }

    public <U> Tuple2<U, T2> map1(Function<? super T1, ? extends U> function) {
        Objects.requireNonNull(function, "mapper is null");
        return Tuple.of(function.apply(this._1), this._2);
    }

    public <U> Tuple2<T1, U> map2(Function<? super T2, ? extends U> function) {
        Objects.requireNonNull(function, "mapper is null");
        return Tuple.of(this._1, function.apply(this._2));
    }

    public Tuple2<T2, T1> swap() {
        return Tuple.of(this._2, this._1);
    }

    public Map.Entry<T1, T2> toEntry() {
        return new AbstractMap.SimpleEntry(this._1, this._2);
    }

    @Override // io.vavr.Tuple
    public Seq<?> toSeq() {
        return List.of(this._1, this._2);
    }

    public String toString() {
        return "(" + this._1 + ", " + this._2 + ")";
    }

    public Tuple2<T1, T2> update1(T1 t1) {
        return new Tuple2<>(t1, this._2);
    }

    public Tuple2<T1, T2> update2(T2 t2) {
        return new Tuple2<>(this._1, t2);
    }

    public <T3, T4> Tuple4<T1, T2, T3, T4> concat(Tuple2<T3, T4> tuple2) {
        Objects.requireNonNull(tuple2, "tuple is null");
        return Tuple.of(this._1, this._2, tuple2._1, tuple2._2);
    }

    public <U1, U2> Tuple2<U1, U2> map(BiFunction<? super T1, ? super T2, Tuple2<U1, U2>> biFunction) {
        Objects.requireNonNull(biFunction, "mapper is null");
        return biFunction.apply(this._1, this._2);
    }

    public <T3, T4, T5> Tuple5<T1, T2, T3, T4, T5> concat(Tuple3<T3, T4, T5> tuple3) {
        Objects.requireNonNull(tuple3, "tuple is null");
        return Tuple.of(this._1, this._2, tuple3._1, tuple3._2, tuple3._3);
    }

    @Override // java.lang.Comparable
    public int compareTo(Tuple2<T1, T2> tuple2) {
        return compareTo(this, tuple2);
    }

    public <T3, T4, T5, T6> Tuple6<T1, T2, T3, T4, T5, T6> concat(Tuple4<T3, T4, T5, T6> tuple4) {
        Objects.requireNonNull(tuple4, "tuple is null");
        return Tuple.of(this._1, this._2, tuple4._1, tuple4._2, tuple4._3, tuple4._4);
    }

    public <T3, T4, T5, T6, T7> Tuple7<T1, T2, T3, T4, T5, T6, T7> concat(Tuple5<T3, T4, T5, T6, T7> tuple5) {
        Objects.requireNonNull(tuple5, "tuple is null");
        return Tuple.of(this._1, this._2, tuple5._1, tuple5._2, tuple5._3, tuple5._4, tuple5._5);
    }

    public <T3> Tuple3<T1, T2, T3> concat(Tuple1<T3> tuple1) {
        Objects.requireNonNull(tuple1, "tuple is null");
        return Tuple.of(this._1, this._2, tuple1._1);
    }
}
