package it.unimi.dsi.fastutil.longs;

import java.util.function.BinaryOperator;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
@FunctionalInterface
public interface LongBinaryOperator extends BinaryOperator<Long>, java.util.function.LongBinaryOperator {
    long apply(long j, long j2);

    @Override // java.util.function.BiFunction
    @Deprecated
    default Long apply(Long l, Long l2) {
        return Long.valueOf(apply(l.longValue(), l2.longValue()));
    }

    @Override // java.util.function.LongBinaryOperator
    @Deprecated
    default long applyAsLong(long j, long j2) {
        return apply(j, j2);
    }
}
