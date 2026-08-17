package io.vavr;

import defpackage.ase;
import io.vavr.collection.List;
import io.vavr.collection.Seq;
import java.io.Serializable;
import java.util.Comparator;
import java.util.Objects;
import java.util.function.Function;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
public final class Tuple1<T1> implements Tuple, Comparable<Tuple1<T1>>, Serializable {
    private static final long serialVersionUID = 1;
    public final T1 _1;

    public Tuple1(T1 t1) {
        this._1 = t1;
    }

    public static /* synthetic */ int b(Comparator comparator, Tuple1 tuple1, Tuple1 tuple2) {
        int iCompare = comparator.compare(tuple1._1, tuple2._1);
        if (iCompare != 0) {
            return iCompare;
        }
        return 0;
    }

    public static <T1> Comparator<Tuple1<T1>> comparator(Comparator<? super T1> comparator) {
        return new ase(comparator);
    }

    private static <U1 extends Comparable<? super U1>> int compareTo(Tuple1<?> tuple1, Tuple1<?> tuple2) {
        int iCompareTo = ((Comparable) tuple1._1).compareTo(tuple2._1);
        if (iCompareTo != 0) {
            return iCompareTo;
        }
        return 0;
    }

    public T1 _1() {
        return this._1;
    }

    public <T2> Tuple2<T1, T2> append(T2 t2) {
        return Tuple.of(this._1, t2);
    }

    public <U> U apply(Function<? super T1, ? extends U> function) {
        Objects.requireNonNull(function, "f is null");
        return function.apply(this._1);
    }

    @Override // io.vavr.Tuple
    public int arity() {
        return 1;
    }

    public <T2, T3, T4, T5, T6, T7, T8> Tuple8<T1, T2, T3, T4, T5, T6, T7, T8> concat(Tuple7<T2, T3, T4, T5, T6, T7, T8> tuple7) {
        Objects.requireNonNull(tuple7, "tuple is null");
        return Tuple.of(this._1, tuple7._1, tuple7._2, tuple7._3, tuple7._4, tuple7._5, tuple7._6, tuple7._7);
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof Tuple1) {
            return Objects.equals(this._1, ((Tuple1) obj)._1);
        }
        return false;
    }

    public int hashCode() {
        return Tuple.hash(this._1);
    }

    public <U1> Tuple1<U1> map(Function<? super T1, ? extends U1> function) {
        Objects.requireNonNull(function, "mapper is null");
        return Tuple.of(function.apply(this._1));
    }

    @Override // io.vavr.Tuple
    public Seq<?> toSeq() {
        return List.of(this._1);
    }

    public String toString() {
        return "(" + this._1 + ")";
    }

    public Tuple1<T1> update1(T1 t1) {
        return new Tuple1<>(t1);
    }

    @Override // java.lang.Comparable
    public int compareTo(Tuple1<T1> tuple1) {
        return compareTo(this, tuple1);
    }

    public <T2, T3> Tuple3<T1, T2, T3> concat(Tuple2<T2, T3> tuple2) {
        Objects.requireNonNull(tuple2, "tuple is null");
        return Tuple.of(this._1, tuple2._1, tuple2._2);
    }

    public <T2, T3, T4> Tuple4<T1, T2, T3, T4> concat(Tuple3<T2, T3, T4> tuple3) {
        Objects.requireNonNull(tuple3, "tuple is null");
        return Tuple.of(this._1, tuple3._1, tuple3._2, tuple3._3);
    }

    public <T2, T3, T4, T5> Tuple5<T1, T2, T3, T4, T5> concat(Tuple4<T2, T3, T4, T5> tuple4) {
        Objects.requireNonNull(tuple4, "tuple is null");
        return Tuple.of(this._1, tuple4._1, tuple4._2, tuple4._3, tuple4._4);
    }

    public <T2, T3, T4, T5, T6> Tuple6<T1, T2, T3, T4, T5, T6> concat(Tuple5<T2, T3, T4, T5, T6> tuple5) {
        Objects.requireNonNull(tuple5, "tuple is null");
        return Tuple.of(this._1, tuple5._1, tuple5._2, tuple5._3, tuple5._4, tuple5._5);
    }

    public <T2, T3, T4, T5, T6, T7> Tuple7<T1, T2, T3, T4, T5, T6, T7> concat(Tuple6<T2, T3, T4, T5, T6, T7> tuple6) {
        Objects.requireNonNull(tuple6, "tuple is null");
        return Tuple.of(this._1, tuple6._1, tuple6._2, tuple6._3, tuple6._4, tuple6._5, tuple6._6);
    }

    public <T2> Tuple2<T1, T2> concat(Tuple1<T2> tuple1) {
        Objects.requireNonNull(tuple1, "tuple is null");
        return Tuple.of(this._1, tuple1._1);
    }
}
