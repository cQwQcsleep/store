package it.unimi.dsi.fastutil.shorts;

import it.unimi.dsi.fastutil.SafeMath;
import java.util.function.IntUnaryOperator;
import java.util.function.UnaryOperator;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
@FunctionalInterface
public interface ShortUnaryOperator extends IntUnaryOperator, UnaryOperator<Short> {
    @Override // java.util.function.Function
    @Deprecated
    default Short apply(Short sh) {
        return Short.valueOf(apply(sh.shortValue()));
    }

    short apply(short s);

    @Override // java.util.function.IntUnaryOperator
    @Deprecated
    default int applyAsInt(int i) {
        return apply(SafeMath.safeIntToShort(i));
    }
}
