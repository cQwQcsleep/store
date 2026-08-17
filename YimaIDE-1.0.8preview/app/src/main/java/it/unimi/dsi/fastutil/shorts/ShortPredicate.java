package it.unimi.dsi.fastutil.shorts;

import defpackage.oad;
import it.unimi.dsi.fastutil.SafeMath;
import it.unimi.dsi.fastutil.shorts.ShortPredicate;
import java.util.Objects;
import java.util.function.IntPredicate;
import java.util.function.Predicate;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
@FunctionalInterface
public interface ShortPredicate extends IntPredicate, Predicate<Short> {
    static /* synthetic */ boolean F1(ShortPredicate shortPredicate, ShortPredicate shortPredicate2, short s) {
        return shortPredicate.test(s) || shortPredicate2.test(s);
    }

    static /* synthetic */ boolean H(ShortPredicate shortPredicate, short s) {
        return !shortPredicate.test(s);
    }

    static /* synthetic */ boolean y1(ShortPredicate shortPredicate, ShortPredicate shortPredicate2, short s) {
        return shortPredicate.test(s) && shortPredicate2.test(s);
    }

    @Override // java.util.function.IntPredicate
    default ShortPredicate and(IntPredicate intPredicate) {
        ShortPredicate oadVar;
        if (intPredicate instanceof ShortPredicate) {
            oadVar = (ShortPredicate) intPredicate;
        } else {
            Objects.requireNonNull(intPredicate);
            oadVar = new oad(intPredicate);
        }
        return and(oadVar);
    }

    @Override // java.util.function.IntPredicate, java.util.function.Predicate
    default ShortPredicate negate() {
        return new ShortPredicate() { // from class: pad
            @Override // it.unimi.dsi.fastutil.shorts.ShortPredicate
            public final boolean test(short s) {
                return ShortPredicate.H(this.b, s);
            }
        };
    }

    @Override // java.util.function.IntPredicate
    default ShortPredicate or(IntPredicate intPredicate) {
        ShortPredicate oadVar;
        if (intPredicate instanceof ShortPredicate) {
            oadVar = (ShortPredicate) intPredicate;
        } else {
            Objects.requireNonNull(intPredicate);
            oadVar = new oad(intPredicate);
        }
        return or(oadVar);
    }

    @Override // java.util.function.IntPredicate
    @Deprecated
    default boolean test(int i) {
        return test(SafeMath.safeIntToShort(i));
    }

    boolean test(short s);

    @Override // java.util.function.Predicate
    @Deprecated
    default boolean test(Short sh) {
        return test(sh.shortValue());
    }

    default ShortPredicate and(final ShortPredicate shortPredicate) {
        Objects.requireNonNull(shortPredicate);
        return new ShortPredicate() { // from class: nad
            @Override // it.unimi.dsi.fastutil.shorts.ShortPredicate
            public final boolean test(short s) {
                return ShortPredicate.y1(this.b, shortPredicate, s);
            }
        };
    }

    default ShortPredicate or(final ShortPredicate shortPredicate) {
        Objects.requireNonNull(shortPredicate);
        return new ShortPredicate() { // from class: mad
            @Override // it.unimi.dsi.fastutil.shorts.ShortPredicate
            public final boolean test(short s) {
                return ShortPredicate.F1(this.b, shortPredicate, s);
            }
        };
    }

    @Override // java.util.function.Predicate
    @Deprecated
    default Predicate<Short> and(Predicate<? super Short> predicate) {
        return super.and(predicate);
    }

    @Override // java.util.function.Predicate
    @Deprecated
    default Predicate<Short> or(Predicate<? super Short> predicate) {
        return super.or(predicate);
    }
}
