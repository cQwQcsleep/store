package it.unimi.dsi.fastutil.doubles;

import it.unimi.dsi.fastutil.Function;
import java.io.Serializable;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
public final class Double2ObjectFunctions {
    public static final EmptyFunction EMPTY_FUNCTION = new EmptyFunction();

    public static class EmptyFunction<V> extends AbstractDouble2ObjectFunction<V> implements Serializable, Cloneable {
        private static final long serialVersionUID = -7046029254386353129L;

        private Object readResolve() {
            return Double2ObjectFunctions.EMPTY_FUNCTION;
        }

        @Override // it.unimi.dsi.fastutil.Function, java.util.Map
        public void clear() {
        }

        public Object clone() {
            return Double2ObjectFunctions.EMPTY_FUNCTION;
        }

        @Override // it.unimi.dsi.fastutil.doubles.Double2ObjectFunction
        public boolean containsKey(double d) {
            return false;
        }

        @Override // it.unimi.dsi.fastutil.doubles.AbstractDouble2ObjectFunction, it.unimi.dsi.fastutil.doubles.Double2ObjectFunction
        public V defaultReturnValue() {
            return null;
        }

        public boolean equals(Object obj) {
            return (obj instanceof Function) && ((Function) obj).size() == 0;
        }

        @Override // it.unimi.dsi.fastutil.doubles.Double2ObjectFunction
        public V get(double d) {
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
