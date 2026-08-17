package it.unimi.dsi.fastutil.floats;

import it.unimi.dsi.fastutil.SafeMath;
import java.util.function.DoubleUnaryOperator;
import java.util.function.UnaryOperator;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
@FunctionalInterface
public interface FloatUnaryOperator extends DoubleUnaryOperator, UnaryOperator<Float> {
    float apply(float f);

    @Override // java.util.function.Function
    @Deprecated
    default Float apply(Float f) {
        return Float.valueOf(apply(f.floatValue()));
    }

    @Override // java.util.function.DoubleUnaryOperator
    @Deprecated
    default double applyAsDouble(double d) {
        return apply(SafeMath.safeDoubleToFloat(d));
    }
}
