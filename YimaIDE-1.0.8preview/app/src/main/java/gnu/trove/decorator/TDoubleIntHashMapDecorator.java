package gnu.trove.decorator;

import gnu.trove.TDoubleIntHashMap;
import gnu.trove.TDoubleIntIterator;
import java.util.AbstractMap;
import java.util.AbstractSet;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
public class TDoubleIntHashMapDecorator extends AbstractMap<Double, Integer> {
    protected final TDoubleIntHashMap _map;

    public TDoubleIntHashMapDecorator(TDoubleIntHashMap tDoubleIntHashMap) {
        this._map = tDoubleIntHashMap;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public void clear() {
        this._map.clear();
    }

    @Override // java.util.AbstractMap, java.util.Map
    public boolean containsKey(Object obj) {
        return this._map.containsKey(unwrapKey(obj));
    }

    @Override // java.util.AbstractMap, java.util.Map
    public boolean containsValue(Object obj) {
        return this._map.containsValue(unwrapValue(obj));
    }

    @Override // java.util.AbstractMap, java.util.Map
    public Set<Map.Entry<Double, Integer>> entrySet() {
        return new AnonymousClass1();
    }

    @Override // java.util.AbstractMap, java.util.Map
    public boolean equals(Object obj) {
        if (this._map.equals(obj)) {
            return true;
        }
        if (!(obj instanceof Map)) {
            return false;
        }
        Map map = (Map) obj;
        if (map.size() != this._map.size()) {
            return false;
        }
        Iterator it2 = map.entrySet().iterator();
        int size = map.size();
        while (true) {
            int i = size - 1;
            if (size <= 0) {
                return true;
            }
            Map.Entry entry = (Map.Entry) it2.next();
            Object key = entry.getKey();
            Object value = entry.getValue();
            if (!(key instanceof Double) || !(value instanceof Integer)) {
                break;
            }
            double dUnwrapKey = unwrapKey(key);
            int iUnwrapValue = unwrapValue(value);
            if (!this._map.containsKey(dUnwrapKey) || iUnwrapValue != this._map.get(dUnwrapKey)) {
                break;
            }
            size = i;
        }
        return false;
    }

    public Integer get(Double d) {
        double dUnwrapKey = unwrapKey(d);
        int i = this._map.get(dUnwrapKey);
        if (i != 0 || this._map.containsKey(dUnwrapKey)) {
            return wrapValue(i);
        }
        return null;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public boolean isEmpty() {
        return size() == 0;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public Integer put(Double d, Integer num) {
        return wrapValue(this._map.put(unwrapKey(d), unwrapValue(num)));
    }

    @Override // java.util.AbstractMap, java.util.Map
    public void putAll(Map<? extends Double, ? extends Integer> map) {
        Iterator<Map.Entry<? extends Double, ? extends Integer>> it2 = map.entrySet().iterator();
        int size = map.size();
        while (true) {
            int i = size - 1;
            if (size <= 0) {
                return;
            }
            Map.Entry<? extends Double, ? extends Integer> next = it2.next();
            put(next.getKey(), next.getValue());
            size = i;
        }
    }

    public Integer remove(Double d) {
        return wrapValue(this._map.remove(unwrapKey(d)));
    }

    @Override // java.util.AbstractMap, java.util.Map
    public int size() {
        return this._map.size();
    }

    public double unwrapKey(Object obj) {
        return ((Double) obj).doubleValue();
    }

    public int unwrapValue(Object obj) {
        return ((Integer) obj).intValue();
    }

    public Double wrapKey(double d) {
        return new Double(d);
    }

    public Integer wrapValue(int i) {
        return new Integer(i);
    }

    /* JADX INFO: renamed from: gnu.trove.decorator.TDoubleIntHashMapDecorator$1, reason: invalid class name */
    public class AnonymousClass1 extends AbstractSet<Map.Entry<Double, Integer>> {
        public AnonymousClass1() {
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public boolean addAll(Collection<? extends Map.Entry<Double, Integer>> collection) {
            throw new UnsupportedOperationException();
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public void clear() {
            TDoubleIntHashMapDecorator.this.clear();
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public boolean contains(Object obj) {
            if (obj instanceof Map.Entry) {
                Map.Entry entry = (Map.Entry) obj;
                Object key = entry.getKey();
                Object value = entry.getValue();
                if (TDoubleIntHashMapDecorator.this.containsKey(key) && TDoubleIntHashMapDecorator.this.get(key).equals(value)) {
                    return true;
                }
            }
            return false;
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public boolean isEmpty() {
            return TDoubleIntHashMapDecorator.this.isEmpty();
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.Set
        public Iterator<Map.Entry<Double, Integer>> iterator() {
            return new Iterator<Map.Entry<Double, Integer>>() { // from class: gnu.trove.decorator.TDoubleIntHashMapDecorator.1.1

                /* JADX INFO: renamed from: it, reason: collision with root package name */
                private final TDoubleIntIterator f16it;

                {
                    this.f16it = TDoubleIntHashMapDecorator.this._map.iterator();
                }

                @Override // java.util.Iterator
                public boolean hasNext() {
                    return this.f16it.hasNext();
                }

                @Override // java.util.Iterator
                public Map.Entry<Double, Integer> next() {
                    this.f16it.advance();
                    return new Map.Entry<Double, Integer>(TDoubleIntHashMapDecorator.this.wrapValue(this.f16it.value()), TDoubleIntHashMapDecorator.this.wrapKey(this.f16it.key())) { // from class: gnu.trove.decorator.TDoubleIntHashMapDecorator.1.1.1
                        private Integer val;
                        final /* synthetic */ Double val$key;
                        final /* synthetic */ Integer val$v;

                        {
                            this.val$v = num;
                            this.val$key = d;
                            this.val = num;
                        }

                        @Override // java.util.Map.Entry
                        public boolean equals(Object obj) {
                            if (!(obj instanceof Map.Entry)) {
                                return false;
                            }
                            Map.Entry entry = (Map.Entry) obj;
                            return entry.getKey().equals(this.val$key) && entry.getValue().equals(this.val);
                        }

                        @Override // java.util.Map.Entry
                        public int hashCode() {
                            return this.val$key.hashCode() + this.val.hashCode();
                        }

                        @Override // java.util.Map.Entry
                        public Integer setValue(Integer num) {
                            this.val = num;
                            return TDoubleIntHashMapDecorator.this.put(this.val$key, num);
                        }

                        /* JADX WARN: Can't rename method to resolve collision */
                        @Override // java.util.Map.Entry
                        public Double getKey() {
                            return this.val$key;
                        }

                        /* JADX WARN: Can't rename method to resolve collision */
                        @Override // java.util.Map.Entry
                        public Integer getValue() {
                            return this.val;
                        }
                    };
                }

                @Override // java.util.Iterator
                public void remove() {
                    this.f16it.remove();
                }
            };
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public boolean remove(Object obj) {
            throw new UnsupportedOperationException();
        }

        @Override // java.util.AbstractSet, java.util.AbstractCollection, java.util.Collection, java.util.Set
        public boolean removeAll(Collection<?> collection) {
            throw new UnsupportedOperationException();
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public boolean retainAll(Collection<?> collection) {
            throw new UnsupportedOperationException();
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public int size() {
            return TDoubleIntHashMapDecorator.this._map.size();
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public boolean add(Map.Entry<Double, Integer> entry) {
            throw new UnsupportedOperationException();
        }
    }

    @Override // java.util.AbstractMap, java.util.Map
    public Integer remove(Object obj) {
        return remove((Double) obj);
    }

    @Override // java.util.AbstractMap, java.util.Map
    public Integer get(Object obj) {
        return get((Double) obj);
    }
}
