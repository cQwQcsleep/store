package it.unimi.dsi.fastutil.ints;

import it.unimi.dsi.fastutil.Function;
import java.io.Serializable;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
public final class Int2IntFunctions {
    public static final EmptyFunction EMPTY_FUNCTION = new EmptyFunction();

    public static class EmptyFunction extends AbstractInt2IntFunction implements Serializable, Cloneable {
        private static final long serialVersionUID = -7046029254386353129L;

        private Object readResolve() {
            return Int2IntFunctions.EMPTY_FUNCTION;
        }

        @Override // it.unimi.dsi.fastutil.Function, java.util.Map
        public void clear() {
        }

        public Object clone() {
            return Int2IntFunctions.EMPTY_FUNCTION;
        }

        @Override // it.unimi.dsi.fastutil.ints.Int2IntFunction
        public boolean containsKey(int i) {
            return false;
        }

        @Override // it.unimi.dsi.fastutil.ints.AbstractInt2IntFunction, it.unimi.dsi.fastutil.ints.Int2IntFunction
        public int defaultReturnValue() {
            return 0;
        }

        public boolean equals(Object obj) {
            return (obj instanceof Function) && ((Function) obj).size() == 0;
        }

        @Override // it.unimi.dsi.fastutil.ints.Int2IntFunction
        public int get(int i) {
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
    }
}
