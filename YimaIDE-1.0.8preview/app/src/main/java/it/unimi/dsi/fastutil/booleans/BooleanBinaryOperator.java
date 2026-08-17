package it.unimi.dsi.fastutil.booleans;

import java.util.function.BinaryOperator;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
@FunctionalInterface
public interface BooleanBinaryOperator extends BinaryOperator<Boolean> {
    @Override // java.util.function.BiFunction
    @Deprecated
    default Boolean apply(Boolean bool, Boolean bool2) {
        return Boolean.valueOf(apply(bool.booleanValue(), bool2.booleanValue()));
    }

    boolean apply(boolean z, boolean z2);
}
