package it.unimi.dsi.fastutil.bytes;

import it.unimi.dsi.fastutil.Function;
import it.unimi.dsi.fastutil.SafeMath;
import java.util.function.IntUnaryOperator;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
@FunctionalInterface
public interface Byte2CharFunction extends Function<Byte, Character>, IntUnaryOperator {
    @Override // java.util.function.Function
    @Deprecated
    default <T> java.util.function.Function<Byte, T> andThen(java.util.function.Function<? super Character, ? extends T> function) {
        return super.andThen(function);
    }

    @Override // java.util.function.IntUnaryOperator
    @Deprecated
    default int applyAsInt(int i) {
        return get(SafeMath.safeIntToByte(i));
    }

    @Override // java.util.function.Function
    @Deprecated
    default <T> java.util.function.Function<T, Character> compose(java.util.function.Function<? super T, ? extends Byte> function) {
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

    default char defaultReturnValue() {
        return (char) 0;
    }

    char get(byte b);

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // it.unimi.dsi.fastutil.Function
    @Deprecated
    default Character get(Object obj) {
        if (obj == null) {
            return null;
        }
        byte bByteValue = ((Byte) obj).byteValue();
        char c = get(bByteValue);
        if (c != defaultReturnValue() || containsKey(bByteValue)) {
            return Character.valueOf(c);
        }
        return null;
    }

    @Deprecated
    default Character put(Byte b, Character ch) {
        byte bByteValue = b.byteValue();
        boolean zContainsKey = containsKey(bByteValue);
        char cPut = put(bByteValue, ch.charValue());
        if (zContainsKey) {
            return Character.valueOf(cPut);
        }
        return null;
    }

    @Deprecated
    default Character remove(Object obj) {
        if (obj == null) {
            return null;
        }
        byte bByteValue = ((Byte) obj).byteValue();
        if (containsKey(bByteValue)) {
            return Character.valueOf(remove(bByteValue));
        }
        return null;
    }

    default boolean containsKey(byte b) {
        return true;
    }

    default char put(byte b, char c) {
        throw new UnsupportedOperationException();
    }

    default char remove(byte b) {
        throw new UnsupportedOperationException();
    }
}
