package it.unimi.dsi.fastutil.objects;

import it.unimi.dsi.fastutil.Function;
import java.util.function.ToDoubleFunction;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
@FunctionalInterface
public interface Object2FloatFunction<K> extends Function<K, Float>, ToDoubleFunction<K> {
    /* JADX WARN: Multi-variable type inference failed */
    @Override // java.util.function.Function
    @Deprecated
    default <T> java.util.function.Function<K, T> andThen(java.util.function.Function<? super Float, ? extends T> function) {
        return super.andThen(function);
    }

    @Override // java.util.function.ToDoubleFunction
    default double applyAsDouble(K k) {
        return getFloat(k);
    }

    default float defaultReturnValue() {
        return 0.0f;
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // it.unimi.dsi.fastutil.Function
    @Deprecated
    default Float get(Object obj) {
        float f = getFloat(obj);
        if (f != defaultReturnValue() || containsKey(obj)) {
            return Float.valueOf(f);
        }
        return null;
    }

    float getFloat(Object obj);

    @Deprecated
    default Float put(K k, Float f) {
        boolean zContainsKey = containsKey(k);
        float fPut = put(k, f.floatValue());
        if (zContainsKey) {
            return Float.valueOf(fPut);
        }
        return null;
    }

    @Deprecated
    default Float remove(Object obj) {
        if (containsKey(obj)) {
            return Float.valueOf(removeFloat(obj));
        }
        return null;
    }

    default float removeFloat(Object obj) {
        throw new UnsupportedOperationException();
    }

    default float put(K k, float f) {
        throw new UnsupportedOperationException();
    }
}
