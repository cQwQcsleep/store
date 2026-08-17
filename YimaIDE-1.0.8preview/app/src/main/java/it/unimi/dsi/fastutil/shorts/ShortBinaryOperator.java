package it.unimi.dsi.fastutil.shorts;

import it.unimi.dsi.fastutil.SafeMath;
import java.util.function.BinaryOperator;
import java.util.function.IntBinaryOperator;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
@FunctionalInterface
public interface ShortBinaryOperator extends BinaryOperator<Short>, IntBinaryOperator {
    @Override // java.util.function.BiFunction
    @Deprecated
    default Short apply(Short sh, Short sh2) {
        return Short.valueOf(apply(sh.shortValue(), sh2.shortValue()));
    }

    short apply(short s, short s2);

    @Override // java.util.function.IntBinaryOperator
    @Deprecated
    default int applyAsInt(int i, int i2) {
        return apply(SafeMath.safeIntToShort(i), SafeMath.safeIntToShort(i2));
    }
}
