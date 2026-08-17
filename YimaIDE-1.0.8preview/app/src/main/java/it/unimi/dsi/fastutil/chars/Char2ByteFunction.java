package it.unimi.dsi.fastutil.chars;

import it.unimi.dsi.fastutil.Function;
import it.unimi.dsi.fastutil.SafeMath;
import java.util.function.IntUnaryOperator;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
@FunctionalInterface
public interface Char2ByteFunction extends Function<Character, Byte>, IntUnaryOperator {
    @Override // java.util.function.Function
    @Deprecated
    default <T> java.util.function.Function<Character, T> andThen(java.util.function.Function<? super Byte, ? extends T> function) {
        return super.andThen(function);
    }

    @Override // java.util.function.IntUnaryOperator
    @Deprecated
    default int applyAsInt(int i) {
        return get(SafeMath.safeIntToChar(i));
    }

    @Override // java.util.function.Function
    @Deprecated
    default <T> java.util.function.Function<T, Byte> compose(java.util.function.Function<? super T, ? extends Character> function) {
        return super.compose(function);
    }

    @Override // it.unimi.dsi.fastutil.Function
    @Deprecated
    default boolean containsKey(Object obj) {
        if (obj == null) {
            return false;
        }
        return containsKey(((Character) obj).charValue());
    }

    default byte defaultReturnValue() {
        return (byte) 0;
    }

    byte get(char c);

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // it.unimi.dsi.fastutil.Function
    @Deprecated
    default Byte get(Object obj) {
        if (obj == null) {
            return null;
        }
        char cCharValue = ((Character) obj).charValue();
        byte b = get(cCharValue);
        if (b != defaultReturnValue() || containsKey(cCharValue)) {
            return Byte.valueOf(b);
        }
        return null;
    }

    @Deprecated
    default Byte put(Character ch, Byte b) {
        char cCharValue = ch.charValue();
        boolean zContainsKey = containsKey(cCharValue);
        byte bPut = put(cCharValue, b.byteValue());
        if (zContainsKey) {
            return Byte.valueOf(bPut);
        }
        return null;
    }

    @Deprecated
    default Byte remove(Object obj) {
        if (obj == null) {
            return null;
        }
        char cCharValue = ((Character) obj).charValue();
        if (containsKey(cCharValue)) {
            return Byte.valueOf(remove(cCharValue));
        }
        return null;
    }

    default boolean containsKey(char c) {
        return true;
    }

    default byte put(char c, byte b) {
        throw new UnsupportedOperationException();
    }

    default byte remove(char c) {
        throw new UnsupportedOperationException();
    }
}
