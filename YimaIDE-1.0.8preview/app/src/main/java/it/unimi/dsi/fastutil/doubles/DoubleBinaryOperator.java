package it.unimi.dsi.fastutil.doubles;

import java.util.function.BinaryOperator;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
@FunctionalInterface
public interface DoubleBinaryOperator extends BinaryOperator<Double>, java.util.function.DoubleBinaryOperator {
    double apply(double d, double d2);

    @Override // java.util.function.BiFunction
    @Deprecated
    default Double apply(Double d, Double d2) {
        return Double.valueOf(apply(d.doubleValue(), d2.doubleValue()));
    }

    @Override // java.util.function.DoubleBinaryOperator
    @Deprecated
    default double applyAsDouble(double d, double d2) {
        return apply(d, d2);
    }
}
