package it.unimi.dsi.fastutil.booleans;

import java.util.function.UnaryOperator;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
@FunctionalInterface
public interface BooleanUnaryOperator extends UnaryOperator<Boolean> {
    @Override // java.util.function.Function
    @Deprecated
    default Boolean apply(Boolean bool) {
        return Boolean.valueOf(apply(bool.booleanValue()));
    }

    boolean apply(boolean z);
}
