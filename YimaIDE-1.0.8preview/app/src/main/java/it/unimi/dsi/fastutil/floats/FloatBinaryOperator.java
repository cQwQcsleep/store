package it.unimi.dsi.fastutil.floats;

import it.unimi.dsi.fastutil.SafeMath;
import java.util.function.BinaryOperator;
import java.util.function.DoubleBinaryOperator;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
@FunctionalInterface
public interface FloatBinaryOperator extends BinaryOperator<Float>, DoubleBinaryOperator {
    float apply(float f, float f2);

    @Override // java.util.function.BiFunction
    @Deprecated
    default Float apply(Float f, Float f2) {
        return Float.valueOf(apply(f.floatValue(), f2.floatValue()));
    }

    @Override // java.util.function.DoubleBinaryOperator
    @Deprecated
    default double applyAsDouble(double d, double d2) {
        return apply(SafeMath.safeDoubleToFloat(d), SafeMath.safeDoubleToFloat(d2));
    }
}
