package it.unimi.dsi.fastutil.objects;

import it.unimi.dsi.fastutil.Function;
import java.util.function.ToIntFunction;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
@FunctionalInterface
public interface Object2ByteFunction<K> extends Function<K, Byte>, ToIntFunction<K> {
    /* JADX WARN: Multi-variable type inference failed */
    @Override // java.util.function.Function
    @Deprecated
    default <T> java.util.function.Function<K, T> andThen(java.util.function.Function<? super Byte, ? extends T> function) {
        return super.andThen(function);
    }

    @Override // java.util.function.ToIntFunction
    default int applyAsInt(K k) {
        return getByte(k);
    }

    default byte defaultReturnValue() {
        return (byte) 0;
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // it.unimi.dsi.fastutil.Function
    @Deprecated
    default Byte get(Object obj) {
        byte b = getByte(obj);
        if (b != defaultReturnValue() || containsKey(obj)) {
            return Byte.valueOf(b);
        }
        return null;
    }

    byte getByte(Object obj);

    @Deprecated
    default Byte put(K k, Byte b) {
        boolean zContainsKey = containsKey(k);
        byte bPut = put(k, b.byteValue());
        if (zContainsKey) {
            return Byte.valueOf(bPut);
        }
        return null;
    }

    @Deprecated
    default Byte remove(Object obj) {
        if (containsKey(obj)) {
            return Byte.valueOf(removeByte(obj));
        }
        return null;
    }

    default byte removeByte(Object obj) {
        throw new UnsupportedOperationException();
    }

    default byte put(K k, byte b) {
        throw new UnsupportedOperationException();
    }
}
