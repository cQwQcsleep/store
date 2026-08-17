package it.unimi.dsi.fastutil.booleans;

import it.unimi.dsi.fastutil.Function;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
@FunctionalInterface
public interface Boolean2BooleanFunction extends Function<Boolean, Boolean> {
    @Override // java.util.function.Function
    @Deprecated
    default <T> java.util.function.Function<Boolean, T> andThen(java.util.function.Function<? super Boolean, ? extends T> function) {
        return super.andThen(function);
    }

    @Override // java.util.function.Function
    @Deprecated
    default <T> java.util.function.Function<T, Boolean> compose(java.util.function.Function<? super T, ? extends Boolean> function) {
        return super.compose(function);
    }

    @Override // it.unimi.dsi.fastutil.Function
    @Deprecated
    default boolean containsKey(Object obj) {
        if (obj == null) {
            return false;
        }
        return containsKey(((Boolean) obj).booleanValue());
    }

    default boolean defaultReturnValue() {
        return false;
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // it.unimi.dsi.fastutil.Function
    @Deprecated
    default Boolean get(Object obj) {
        if (obj == null) {
            return null;
        }
        boolean zBooleanValue = ((Boolean) obj).booleanValue();
        boolean z = get(zBooleanValue);
        if (z != defaultReturnValue() || containsKey(zBooleanValue)) {
            return Boolean.valueOf(z);
        }
        return null;
    }

    boolean get(boolean z);

    default boolean containsKey(boolean z) {
        return true;
    }
}
