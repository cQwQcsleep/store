package it.unimi.dsi.fastutil.doubles;

import it.unimi.dsi.fastutil.doubles.DoublePredicate;
import java.util.Objects;
import java.util.function.Predicate;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
@FunctionalInterface
public interface DoublePredicate extends java.util.function.DoublePredicate, Predicate<Double> {
    static /* synthetic */ boolean V(DoublePredicate doublePredicate, java.util.function.DoublePredicate doublePredicate2, double d) {
        return doublePredicate.test(d) && doublePredicate2.test(d);
    }

    static /* synthetic */ boolean g(DoublePredicate doublePredicate, double d) {
        return !doublePredicate.test(d);
    }

    static /* synthetic */ boolean i(DoublePredicate doublePredicate, java.util.function.DoublePredicate doublePredicate2, double d) {
        return doublePredicate.test(d) || doublePredicate2.test(d);
    }

    @Override // java.util.function.DoublePredicate
    default DoublePredicate and(final java.util.function.DoublePredicate doublePredicate) {
        Objects.requireNonNull(doublePredicate);
        return new DoublePredicate() { // from class: gw3
            @Override // java.util.function.DoublePredicate
            public final boolean test(double d) {
                return DoublePredicate.V(this.b, doublePredicate, d);
            }
        };
    }

    @Override // java.util.function.DoublePredicate, java.util.function.Predicate
    default DoublePredicate negate() {
        return new DoublePredicate() { // from class: hw3
            @Override // java.util.function.DoublePredicate
            public final boolean test(double d) {
                return DoublePredicate.g(this.b, d);
            }
        };
    }

    @Override // java.util.function.DoublePredicate
    default DoublePredicate or(final java.util.function.DoublePredicate doublePredicate) {
        Objects.requireNonNull(doublePredicate);
        return new DoublePredicate() { // from class: fw3
            @Override // java.util.function.DoublePredicate
            public final boolean test(double d) {
                return DoublePredicate.i(this.b, doublePredicate, d);
            }
        };
    }

    @Override // java.util.function.Predicate
    @Deprecated
    default boolean test(Double d) {
        return test(d.doubleValue());
    }

    @Override // java.util.function.Predicate
    @Deprecated
    default Predicate<Double> and(Predicate<? super Double> predicate) {
        return super.and(predicate);
    }

    @Override // java.util.function.Predicate
    @Deprecated
    default Predicate<Double> or(Predicate<? super Double> predicate) {
        return super.or(predicate);
    }
}
