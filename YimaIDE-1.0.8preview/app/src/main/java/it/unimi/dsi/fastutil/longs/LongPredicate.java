package it.unimi.dsi.fastutil.longs;

import it.unimi.dsi.fastutil.longs.LongPredicate;
import java.util.Objects;
import java.util.function.Predicate;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
@FunctionalInterface
public interface LongPredicate extends java.util.function.LongPredicate, Predicate<Long> {
    static /* synthetic */ boolean A0(LongPredicate longPredicate, long j) {
        return !longPredicate.test(j);
    }

    static /* synthetic */ boolean c0(LongPredicate longPredicate, java.util.function.LongPredicate longPredicate2, long j) {
        return longPredicate.test(j) && longPredicate2.test(j);
    }

    static /* synthetic */ boolean o0(LongPredicate longPredicate, java.util.function.LongPredicate longPredicate2, long j) {
        return longPredicate.test(j) || longPredicate2.test(j);
    }

    @Override // java.util.function.LongPredicate
    default LongPredicate and(final java.util.function.LongPredicate longPredicate) {
        Objects.requireNonNull(longPredicate);
        return new LongPredicate() { // from class: yh9
            @Override // java.util.function.LongPredicate
            public final boolean test(long j) {
                return LongPredicate.c0(this.b, longPredicate, j);
            }
        };
    }

    @Override // java.util.function.LongPredicate, java.util.function.Predicate
    default LongPredicate negate() {
        return new LongPredicate() { // from class: xh9
            @Override // java.util.function.LongPredicate
            public final boolean test(long j) {
                return LongPredicate.A0(this.b, j);
            }
        };
    }

    @Override // java.util.function.LongPredicate
    default LongPredicate or(final java.util.function.LongPredicate longPredicate) {
        Objects.requireNonNull(longPredicate);
        return new LongPredicate() { // from class: wh9
            @Override // java.util.function.LongPredicate
            public final boolean test(long j) {
                return LongPredicate.o0(this.b, longPredicate, j);
            }
        };
    }

    @Override // java.util.function.Predicate
    @Deprecated
    default boolean test(Long l) {
        return test(l.longValue());
    }

    @Override // java.util.function.Predicate
    @Deprecated
    default Predicate<Long> and(Predicate<? super Long> predicate) {
        return super.and(predicate);
    }

    @Override // java.util.function.Predicate
    @Deprecated
    default Predicate<Long> or(Predicate<? super Long> predicate) {
        return super.or(predicate);
    }
}
