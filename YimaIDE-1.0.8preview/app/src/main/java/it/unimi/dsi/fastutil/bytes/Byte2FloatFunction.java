package it.unimi.dsi.fastutil.bytes;

import it.unimi.dsi.fastutil.Function;
import it.unimi.dsi.fastutil.SafeMath;
import java.util.function.IntToDoubleFunction;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
@FunctionalInterface
public interface Byte2FloatFunction extends Function<Byte, Float>, IntToDoubleFunction {
    @Override // java.util.function.Function
    @Deprecated
    default <T> java.util.function.Function<Byte, T> andThen(java.util.function.Function<? super Float, ? extends T> function) {
        return super.andThen(function);
    }

    @Override // java.util.function.IntToDoubleFunction
    @Deprecated
    default double applyAsDouble(int i) {
        return get(SafeMath.safeIntToByte(i));
    }

    @Override // java.util.function.Function
    @Deprecated
    default <T> java.util.function.Function<T, Float> compose(java.util.function.Function<? super T, ? extends Byte> function) {
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

    default float defaultReturnValue() {
        return 0.0f;
    }

    float get(byte b);

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // it.unimi.dsi.fastutil.Function
    @Deprecated
    default Float get(Object obj) {
        if (obj == null) {
            return null;
        }
        byte bByteValue = ((Byte) obj).byteValue();
        float f = get(bByteValue);
        if (f != defaultReturnValue() || containsKey(bByteValue)) {
            return Float.valueOf(f);
        }
        return null;
    }

    @Deprecated
    default Float put(Byte b, Float f) {
        byte bByteValue = b.byteValue();
        boolean zContainsKey = containsKey(bByteValue);
        float fPut = put(bByteValue, f.floatValue());
        if (zContainsKey) {
            return Float.valueOf(fPut);
        }
        return null;
    }

    @Deprecated
    default Float remove(Object obj) {
        if (obj == null) {
            return null;
        }
        byte bByteValue = ((Byte) obj).byteValue();
        if (containsKey(bByteValue)) {
            return Float.valueOf(remove(bByteValue));
        }
        return null;
    }

    default boolean containsKey(byte b) {
        return true;
    }

    default float put(byte b, float f) {
        throw new UnsupportedOperationException();
    }

    default float remove(byte b) {
        throw new UnsupportedOperationException();
    }
}
