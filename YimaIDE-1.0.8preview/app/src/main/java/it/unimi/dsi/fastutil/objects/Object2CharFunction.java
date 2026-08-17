package it.unimi.dsi.fastutil.objects;

import it.unimi.dsi.fastutil.Function;
import java.util.function.ToIntFunction;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
@FunctionalInterface
public interface Object2CharFunction<K> extends Function<K, Character>, ToIntFunction<K> {
    /* JADX WARN: Multi-variable type inference failed */
    @Override // java.util.function.Function
    @Deprecated
    default <T> java.util.function.Function<K, T> andThen(java.util.function.Function<? super Character, ? extends T> function) {
        return super.andThen(function);
    }

    @Override // java.util.function.ToIntFunction
    default int applyAsInt(K k) {
        return getChar(k);
    }

    default char defaultReturnValue() {
        return (char) 0;
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // it.unimi.dsi.fastutil.Function
    @Deprecated
    default Character get(Object obj) {
        char c = getChar(obj);
        if (c != defaultReturnValue() || containsKey(obj)) {
            return Character.valueOf(c);
        }
        return null;
    }

    char getChar(Object obj);

    @Deprecated
    default Character put(K k, Character ch) {
        boolean zContainsKey = containsKey(k);
        char cPut = put(k, ch.charValue());
        if (zContainsKey) {
            return Character.valueOf(cPut);
        }
        return null;
    }

    @Deprecated
    default Character remove(Object obj) {
        if (containsKey(obj)) {
            return Character.valueOf(removeChar(obj));
        }
        return null;
    }

    default char removeChar(Object obj) {
        throw new UnsupportedOperationException();
    }

    default char put(K k, char c) {
        throw new UnsupportedOperationException();
    }
}
