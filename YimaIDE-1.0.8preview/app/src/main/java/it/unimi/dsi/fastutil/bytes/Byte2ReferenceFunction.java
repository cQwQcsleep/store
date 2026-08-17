package it.unimi.dsi.fastutil.bytes;

import it.unimi.dsi.fastutil.Function;
import it.unimi.dsi.fastutil.SafeMath;
import java.util.function.IntFunction;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
@FunctionalInterface
public interface Byte2ReferenceFunction<V> extends Function<Byte, V>, IntFunction<V> {
    @Override // java.util.function.IntFunction
    @Deprecated
    default V apply(int i) {
        return get(SafeMath.safeIntToByte(i));
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // java.util.function.Function
    @Deprecated
    default <T> java.util.function.Function<T, V> compose(java.util.function.Function<? super T, ? extends Byte> function) {
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

    default V defaultReturnValue() {
        return null;
    }

    V get(byte b);

    @Override // it.unimi.dsi.fastutil.Function
    @Deprecated
    default V get(Object obj) {
        if (obj == null) {
            return null;
        }
        byte bByteValue = ((Byte) obj).byteValue();
        V v = get(bByteValue);
        if (v != defaultReturnValue() || containsKey(bByteValue)) {
            return v;
        }
        return null;
    }

    @Deprecated
    default V put(Byte b, V v) {
        byte bByteValue = b.byteValue();
        boolean zContainsKey = containsKey(bByteValue);
        V vPut = put(bByteValue, v);
        if (zContainsKey) {
            return vPut;
        }
        return null;
    }

    @Deprecated
    default V remove(Object obj) {
        if (obj == null) {
            return null;
        }
        byte bByteValue = ((Byte) obj).byteValue();
        if (containsKey(bByteValue)) {
            return remove(bByteValue);
        }
        return null;
    }

    default boolean containsKey(byte b) {
        return true;
    }

    default V put(byte b, V v) {
        throw new UnsupportedOperationException();
    }

    default V remove(byte b) {
        throw new UnsupportedOperationException();
    }
}
