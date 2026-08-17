package it.unimi.dsi.fastutil.bytes;

import it.unimi.dsi.fastutil.Function;
import it.unimi.dsi.fastutil.SafeMath;
import java.util.function.IntPredicate;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
@FunctionalInterface
public interface Byte2BooleanFunction extends Function<Byte, Boolean>, IntPredicate {
    @Override // java.util.function.Function
    @Deprecated
    default <T> java.util.function.Function<Byte, T> andThen(java.util.function.Function<? super Boolean, ? extends T> function) {
        return super.andThen(function);
    }

    @Override // java.util.function.Function
    @Deprecated
    default <T> java.util.function.Function<T, Boolean> compose(java.util.function.Function<? super T, ? extends Byte> function) {
        return super.compose(function);
    }

    @Override // it.unimi.dsi.fastutil.Function
    @Deprecated
    default boolean containsKey(Object obj) {
        if (obj == null) {
            return false;
        }
        return containsKey(((Byte) obj).byteValue());
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
        byte bByteValue = ((Byte) obj).byteValue();
        boolean z = get(bByteValue);
        if (z != defaultReturnValue() || containsKey(bByteValue)) {
            return Boolean.valueOf(z);
        }
        return null;
    }

    boolean get(byte b);

    @Deprecated
    default Boolean put(Byte b, Boolean bool) {
        byte bByteValue = b.byteValue();
        boolean zContainsKey = containsKey(bByteValue);
        boolean zPut = put(bByteValue, bool.booleanValue());
        if (zContainsKey) {
            return Boolean.valueOf(zPut);
        }
        return null;
    }

    @Deprecated
    default Boolean remove(Object obj) {
        if (obj == null) {
            return null;
        }
        byte bByteValue = ((Byte) obj).byteValue();
        if (containsKey(bByteValue)) {
            return Boolean.valueOf(remove(bByteValue));
        }
        return null;
    }

    @Override // java.util.function.IntPredicate
    @Deprecated
    default boolean test(int i) {
        return get(SafeMath.safeIntToByte(i));
    }

    default boolean containsKey(byte b) {
        return true;
    }

    default boolean put(byte b, boolean z) {
        throw new UnsupportedOperationException();
    }

    default boolean remove(byte b) {
        throw new UnsupportedOperationException();
    }
}
