package it.unimi.dsi.fastutil.doubles;

import java.util.function.UnaryOperator;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
@FunctionalInterface
public interface DoubleUnaryOperator extends java.util.function.DoubleUnaryOperator, UnaryOperator<Double> {
    double apply(double d);

    @Override // java.util.function.Function
    @Deprecated
    default Double apply(Double d) {
        return Double.valueOf(apply(d.doubleValue()));
    }

    @Override // java.util.function.DoubleUnaryOperator
    @Deprecated
    default double applyAsDouble(double d) {
        return apply(d);
    }
}
