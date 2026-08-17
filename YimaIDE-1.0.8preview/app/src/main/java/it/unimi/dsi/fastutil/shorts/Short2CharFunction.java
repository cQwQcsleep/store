package it.unimi.dsi.fastutil.shorts;

import it.unimi.dsi.fastutil.Function;
import it.unimi.dsi.fastutil.SafeMath;
import java.util.function.IntUnaryOperator;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
@FunctionalInterface
public interface Short2CharFunction extends Function<Short, Character>, IntUnaryOperator {
    @Override // java.util.function.Function
    @Deprecated
    default <T> java.util.function.Function<Short, T> andThen(java.util.function.Function<? super Character, ? extends T> function) {
        return super.andThen(function);
    }

    @Override // java.util.function.IntUnaryOperator
    @Deprecated
    default int applyAsInt(int i) {
        return get(SafeMath.safeIntToShort(i));
    }

    @Override // java.util.function.Function
    @Deprecated
    default <T> java.util.function.Function<T, Character> compose(java.util.function.Function<? super T, ? extends Short> function) {
        return super.compose(function);
    }

    @Override // it.unimi.dsi.fastutil.Function
    @Deprecated
    default boolean containsKey(Object obj) {
        if (obj == null) {
            return false;
        }
        return containsKey(((Short) obj).shortValue());
    }

    default char defaultReturnValue() {
        return (char) 0;
    }

    char get(short s);

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // it.unimi.dsi.fastutil.Function
    @Deprecated
    default Character get(Object obj) {
        if (obj == null) {
            return null;
        }
        short sShortValue = ((Short) obj).shortValue();
        char c = get(sShortValue);
        if (c != defaultReturnValue() || containsKey(sShortValue)) {
            return Character.valueOf(c);
        }
        return null;
    }

    @Deprecated
    default Character put(Short sh, Character ch) {
        short sShortValue = sh.shortValue();
        boolean zContainsKey = containsKey(sShortValue);
        char cPut = put(sShortValue, ch.charValue());
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
        short sShortValue = ((Short) obj).shortValue();
        if (containsKey(sShortValue)) {
            return Character.valueOf(remove(sShortValue));
        }
        return null;
    }

    default boolean containsKey(short s) {
        return true;
    }

    default char put(short s, char c) {
        throw new UnsupportedOperationException();
    }

    default char remove(short s) {
        throw new UnsupportedOperationException();
    }
}
