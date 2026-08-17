package it.unimi.dsi.fastutil.objects;

import it.unimi.dsi.fastutil.Function;
import java.io.Serializable;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
public final class Object2IntFunctions {
    public static final EmptyFunction EMPTY_FUNCTION = new EmptyFunction();

    public static class EmptyFunction<K> extends AbstractObject2IntFunction<K> implements Serializable, Cloneable {
        private static final long serialVersionUID = -7046029254386353129L;

        private Object readResolve() {
            return Object2IntFunctions.EMPTY_FUNCTION;
        }

        @Override // it.unimi.dsi.fastutil.Function, java.util.Map
        public void clear() {
        }

        public Object clone() {
            return Object2IntFunctions.EMPTY_FUNCTION;
        }

        @Override // it.unimi.dsi.fastutil.Function
        public boolean containsKey(Object obj) {
            return false;
        }

        @Override // it.unimi.dsi.fastutil.objects.AbstractObject2IntFunction
        public void defaultReturnValue(int i) {
            throw new UnsupportedOperationException();
        }

        public boolean equals(Object obj) {
            return (obj instanceof Function) && ((Function) obj).size() == 0;
        }

        @Override // it.unimi.dsi.fastutil.objects.Object2IntFunction
        public int getInt(Object obj) {
            return 0;
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

        @Override // it.unimi.dsi.fastutil.objects.AbstractObject2IntFunction, it.unimi.dsi.fastutil.objects.Object2IntFunction
        public int defaultReturnValue() {
            return 0;
        }
    }
}
