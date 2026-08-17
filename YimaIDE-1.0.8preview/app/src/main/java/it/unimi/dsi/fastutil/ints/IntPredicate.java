package it.unimi.dsi.fastutil.ints;

import it.unimi.dsi.fastutil.ints.IntPredicate;
import java.util.Objects;
import java.util.function.Predicate;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
@FunctionalInterface
public interface IntPredicate extends java.util.function.IntPredicate, Predicate<Integer> {
    static /* synthetic */ boolean F0(IntPredicate intPredicate, int i) {
        return !intPredicate.test(i);
    }

    static /* synthetic */ boolean G1(IntPredicate intPredicate, java.util.function.IntPredicate intPredicate2, int i) {
        return intPredicate.test(i) || intPredicate2.test(i);
    }

    static /* synthetic */ boolean t(IntPredicate intPredicate, java.util.function.IntPredicate intPredicate2, int i) {
        return intPredicate.test(i) && intPredicate2.test(i);
    }

    @Override // java.util.function.IntPredicate
    default IntPredicate and(final java.util.function.IntPredicate intPredicate) {
        Objects.requireNonNull(intPredicate);
        return new IntPredicate() { // from class: es6
            @Override // java.util.function.IntPredicate
            public final boolean test(int i) {
                return IntPredicate.t(this.b, intPredicate, i);
            }
        };
    }

    @Override // java.util.function.IntPredicate, java.util.function.Predicate
    default IntPredicate negate() {
        return new IntPredicate() { // from class: ds6
            @Override // java.util.function.IntPredicate
            public final boolean test(int i) {
                return IntPredicate.F0(this.b, i);
            }
        };
    }

    @Override // java.util.function.IntPredicate
    default IntPredicate or(final java.util.function.IntPredicate intPredicate) {
        Objects.requireNonNull(intPredicate);
        return new IntPredicate() { // from class: fs6
            @Override // java.util.function.IntPredicate
            public final boolean test(int i) {
                return IntPredicate.G1(this.b, intPredicate, i);
            }
        };
    }

    @Override // java.util.function.Predicate
    @Deprecated
    default boolean test(Integer num) {
        return test(num.intValue());
    }

    @Override // java.util.function.Predicate
    @Deprecated
    default Predicate<Integer> and(Predicate<? super Integer> predicate) {
        return super.and(predicate);
    }

    @Override // java.util.function.Predicate
    @Deprecated
    default Predicate<Integer> or(Predicate<? super Integer> predicate) {
        return super.or(predicate);
    }
}
