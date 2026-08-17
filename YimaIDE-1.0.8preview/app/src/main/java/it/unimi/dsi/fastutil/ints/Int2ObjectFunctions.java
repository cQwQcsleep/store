package it.unimi.dsi.fastutil.ints;

import it.unimi.dsi.fastutil.Function;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
public final class Int2ObjectFunctions {
    public static final EmptyFunction EMPTY_FUNCTION = new EmptyFunction();

    public static class EmptyFunction<V> extends AbstractInt2ObjectFunction<V> implements Serializable, Cloneable {
        private static final long serialVersionUID = -7046029254386353129L;

        private Object readResolve() {
            return Int2ObjectFunctions.EMPTY_FUNCTION;
        }

        @Override // it.unimi.dsi.fastutil.Function, java.util.Map
        public void clear() {
        }

        public Object clone() {
            return Int2ObjectFunctions.EMPTY_FUNCTION;
        }

        @Override // it.unimi.dsi.fastutil.ints.Int2ObjectFunction, it.unimi.dsi.fastutil.ints.Int2ObjectMap
        public boolean containsKey(int i) {
            return false;
        }

        @Override // it.unimi.dsi.fastutil.ints.AbstractInt2ObjectFunction
        public void defaultReturnValue(V v) {
            throw new UnsupportedOperationException();
        }

        public boolean equals(Object obj) {
            return (obj instanceof Function) && ((Function) obj).size() == 0;
        }

        @Override // it.unimi.dsi.fastutil.ints.Int2ObjectFunction
        public V get(int i) {
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

        @Override // it.unimi.dsi.fastutil.ints.AbstractInt2ObjectFunction, it.unimi.dsi.fastutil.ints.Int2ObjectFunction
        public V defaultReturnValue() {
            return null;
        }
    }

    public static class SynchronizedFunction<V> implements Int2ObjectFunction<V>, Serializable {
        private static final long serialVersionUID = -7046029254386353129L;
        protected final Int2ObjectFunction<V> function;
        protected final Object sync;

        public SynchronizedFunction(Int2ObjectFunction<V> int2ObjectFunction) {
            int2ObjectFunction.getClass();
            this.function = int2ObjectFunction;
            this.sync = this;
        }

        private void writeObject(ObjectOutputStream objectOutputStream) throws IOException {
            synchronized (this.sync) {
                objectOutputStream.defaultWriteObject();
            }
        }

        @Override // it.unimi.dsi.fastutil.ints.Int2ObjectFunction, java.util.function.IntFunction
        public V apply(int i) {
            V vApply;
            synchronized (this.sync) {
                vApply = this.function.apply(i);
            }
            return vApply;
        }

        @Override // it.unimi.dsi.fastutil.Function, java.util.Map
        public void clear() {
            synchronized (this.sync) {
                this.function.clear();
            }
        }

        @Override // it.unimi.dsi.fastutil.ints.Int2ObjectFunction, it.unimi.dsi.fastutil.ints.Int2ObjectMap
        public boolean containsKey(int i) {
            boolean zContainsKey;
            synchronized (this.sync) {
                zContainsKey = this.function.containsKey(i);
            }
            return zContainsKey;
        }

        @Override // it.unimi.dsi.fastutil.ints.Int2ObjectFunction
        public V defaultReturnValue() {
            V vDefaultReturnValue;
            synchronized (this.sync) {
                vDefaultReturnValue = this.function.defaultReturnValue();
            }
            return vDefaultReturnValue;
        }

        public boolean equals(Object obj) {
            boolean zEquals;
            if (obj == this) {
                return true;
            }
            synchronized (this.sync) {
                zEquals = this.function.equals(obj);
            }
            return zEquals;
        }

        @Override // it.unimi.dsi.fastutil.ints.Int2ObjectFunction
        public V get(int i) {
            V v;
            synchronized (this.sync) {
                v = this.function.get(i);
            }
            return v;
        }

        @Override // it.unimi.dsi.fastutil.ints.Int2ObjectFunction
        @Deprecated
        public V getOrDefault(Object obj, V v) {
            V orDefault;
            synchronized (this.sync) {
                orDefault = this.function.getOrDefault(obj, v);
            }
            return orDefault;
        }

        public int hashCode() {
            int iHashCode;
            synchronized (this.sync) {
                iHashCode = this.function.hashCode();
            }
            return iHashCode;
        }

        @Override // it.unimi.dsi.fastutil.ints.Int2ObjectFunction
        public V put(int i, V v) {
            V vPut;
            synchronized (this.sync) {
                vPut = this.function.put(i, v);
            }
            return vPut;
        }

        @Override // it.unimi.dsi.fastutil.ints.Int2ObjectFunction
        public V remove(int i) {
            V vRemove;
            synchronized (this.sync) {
                vRemove = this.function.remove(i);
            }
            return vRemove;
        }

        @Override // it.unimi.dsi.fastutil.Function
        public int size() {
            int size;
            synchronized (this.sync) {
                size = this.function.size();
            }
            return size;
        }

        public String toString() {
            String string;
            synchronized (this.sync) {
                string = this.function.toString();
            }
            return string;
        }

        @Override // it.unimi.dsi.fastutil.ints.Int2ObjectFunction, it.unimi.dsi.fastutil.Function
        @Deprecated
        public boolean containsKey(Object obj) {
            boolean zContainsKey;
            synchronized (this.sync) {
                zContainsKey = this.function.containsKey(obj);
            }
            return zContainsKey;
        }

        @Override // it.unimi.dsi.fastutil.ints.Int2ObjectFunction, it.unimi.dsi.fastutil.Function
        @Deprecated
        public V get(Object obj) {
            V v;
            synchronized (this.sync) {
                v = this.function.get(obj);
            }
            return v;
        }

        @Override // it.unimi.dsi.fastutil.ints.Int2ObjectFunction
        @Deprecated
        public V remove(Object obj) {
            V vRemove;
            synchronized (this.sync) {
                vRemove = this.function.remove(obj);
            }
            return vRemove;
        }

        @Override // it.unimi.dsi.fastutil.Function, java.util.function.Function
        @Deprecated
        public V apply(Integer num) {
            V vApply;
            synchronized (this.sync) {
                vApply = this.function.apply(num);
            }
            return vApply;
        }

        @Override // it.unimi.dsi.fastutil.ints.Int2ObjectFunction
        @Deprecated
        public V put(Integer num, V v) {
            V vPut;
            synchronized (this.sync) {
                vPut = this.function.put(num, v);
            }
            return vPut;
        }
    }
}
