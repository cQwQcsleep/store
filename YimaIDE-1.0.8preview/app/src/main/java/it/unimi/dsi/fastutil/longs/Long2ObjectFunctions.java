package it.unimi.dsi.fastutil.longs;

import it.unimi.dsi.fastutil.Function;
import java.io.Serializable;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
public final class Long2ObjectFunctions {
    public static final EmptyFunction EMPTY_FUNCTION = new EmptyFunction();

    public static class EmptyFunction<V> extends AbstractLong2ObjectFunction<V> implements Serializable, Cloneable {
        private static final long serialVersionUID = -7046029254386353129L;

        private Object readResolve() {
            return Long2ObjectFunctions.EMPTY_FUNCTION;
        }

        @Override // it.unimi.dsi.fastutil.Function, java.util.Map
        public void clear() {
        }

        public Object clone() {
            return Long2ObjectFunctions.EMPTY_FUNCTION;
        }

        @Override // it.unimi.dsi.fastutil.longs.Long2ObjectFunction
        public boolean containsKey(long j) {
            return false;
        }

        @Override // it.unimi.dsi.fastutil.longs.AbstractLong2ObjectFunction, it.unimi.dsi.fastutil.longs.Long2ObjectFunction
        public V defaultReturnValue() {
            return null;
        }

        public boolean equals(Object obj) {
            return (obj instanceof Function) && ((Function) obj).size() == 0;
        }

        @Override // it.unimi.dsi.fastutil.longs.Long2ObjectFunction
        public V get(long j) {
            return null;
        }

        public int hashCode() {
            return 0;
        }

        @Override // it.unimi.dsi.fastutil.Function
        public int size() {
            return 0;
        }

        public String toString() {
            return "{}";
        }
    }
}
