package it.unimi.dsi.fastutil.ints;

import java.util.function.BinaryOperator;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
@FunctionalInterface
public interface IntBinaryOperator extends BinaryOperator<Integer>, java.util.function.IntBinaryOperator {
    int apply(int i, int i2);

    @Override // java.util.function.BiFunction
    @Deprecated
    default Integer apply(Integer num, Integer num2) {
        return Integer.valueOf(apply(num.intValue(), num2.intValue()));
    }

    @Override // java.util.function.IntBinaryOperator
    @Deprecated
    default int applyAsInt(int i, int i2) {
        return apply(i, i2);
    }
}
