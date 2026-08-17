package gnu.trove.decorator;

import gnu.trove.TDoubleDoubleHashMap;
import gnu.trove.TDoubleDoubleIterator;
import java.util.AbstractMap;
import java.util.AbstractSet;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
public class TDoubleDoubleHashMapDecorator extends AbstractMap<Double, Double> {
    protected final TDoubleDoubleHashMap _map;

    public TDoubleDoubleHashMapDecorator(TDoubleDoubleHashMap tDoubleDoubleHashMap) {
        this._map = tDoubleDoubleHashMap;
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
    public Set<Map.Entry<Double, Double>> entrySet() {
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
            if (!(key instanceof Double) || !(value instanceof Double)) {
                break;
            }
            double dUnwrapKey = unwrapKey(key);
            double dUnwrapValue = unwrapValue(value);
            if (!this._map.containsKey(dUnwrapKey) || dUnwrapValue != this._map.get(dUnwrapKey)) {
                break;
            }
            size = i;
        }
        return false;
    }

    public Double get(Double d) {
        double dUnwrapKey = unwrapKey(d);
        double d2 = this._map.get(dUnwrapKey);
        if (d2 != 0.0d || this._map.containsKey(dUnwrapKey)) {
            return wrapValue(d2);
        }
        return null;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public boolean isEmpty() {
        return size() == 0;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public Double put(Double d, Double d2) {
        return wrapValue(this._map.put(unwrapKey(d), unwrapValue(d2)));
    }

    @Override // java.util.AbstractMap, java.util.Map
    public void putAll(Map<? extends Double, ? extends Double> map) {
        Iterator<Map.Entry<? extends Double, ? extends Double>> it2 = map.entrySet().iterator();
        int size = map.size();
        while (true) {
            int i = size - 1;
            if (size <= 0) {
                return;
            }
            Map.Entry<? extends Double, ? extends Double> next = it2.next();
            put(next.getKey(), next.getValue());
            size = i;
        }
    }

    public Double remove(Double d) {
        return wrapValue(this._map.remove(unwrapKey(d)));
    }

    @Override // java.util.AbstractMap, java.util.Map
    public int size() {
        return this._map.size();
    }

    public double unwrapKey(Object obj) {
        return ((Double) obj).doubleValue();
    }

    public double unwrapValue(Object obj) {
        return ((Double) obj).doubleValue();
    }

    public Double wrapKey(double d) {
        return new Double(d);
    }

    public Double wrapValue(double d) {
        return new Double(d);
    }

    /* JADX INFO: renamed from: gnu.trove.decorator.TDoubleDoubleHashMapDecorator$1, reason: invalid class name */
    public class AnonymousClass1 extends AbstractSet<Map.Entry<Double, Double>> {
        public AnonymousClass1() {
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public boolean addAll(Collection<? extends Map.Entry<Double, Double>> collection) {
            throw new UnsupportedOperationException();
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public void clear() {
            TDoubleDoubleHashMapDecorator.this.clear();
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public boolean contains(Object obj) {
            if (obj instanceof Map.Entry) {
                Map.Entry entry = (Map.Entry) obj;
                Object key = entry.getKey();
                Object value = entry.getValue();
                if (TDoubleDoubleHashMapDecorator.this.containsKey(key) && TDoubleDoubleHashMapDecorator.this.get(key).equals(value)) {
                    return true;
                }
            }
            return false;
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public boolean isEmpty() {
            return TDoubleDoubleHashMapDecorator.this.isEmpty();
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.Set
        public Iterator<Map.Entry<Double, Double>> iterator() {
            return new Iterator<Map.Entry<Double, Double>>() { // from class: gnu.trove.decorator.TDoubleDoubleHashMapDecorator.1.1

                /* JADX INFO: renamed from: it, reason: collision with root package name */
                private final TDoubleDoubleIterator f13it;

                {
                    this.f13it = TDoubleDoubleHashMapDecorator.this._map.iterator();
                }

                @Override // java.util.Iterator
                public boolean hasNext() {
                    return this.f13it.hasNext();
                }

                @Override // java.util.Iterator
                public Map.Entry<Double, Double> next() {
                    this.f13it.advance();
                    return new Map.Entry<Double, Double>(TDoubleDoubleHashMapDecorator.this.wrapValue(this.f13it.value()), TDoubleDoubleHashMapDecorator.this.wrapKey(this.f13it.key())) { // from class: gnu.trove.decorator.TDoubleDoubleHashMapDecorator.1.1.1
                        private Double val;
                        final /* synthetic */ Double val$key;
                        final /* synthetic */ Double val$v;

                        {
                            this.val$v = d;
                            this.val$key = d;
                            this.val = d;
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
                        public Double setValue(Double d) {
                            this.val = d;
                            return TDoubleDoubleHashMapDecorator.this.put(this.val$key, d);
                        }

                        /* JADX WARN: Can't rename method to resolve collision */
                        @Override // java.util.Map.Entry
                        public Double getKey() {
                            return this.val$key;
                        }

                        /* JADX WARN: Can't rename method to resolve collision */
                        @Override // java.util.Map.Entry
                        public Double getValue() {
                            return this.val;
                        }
                    };
                }

                @Override // java.util.Iterator
                public void remove() {
                    this.f13it.remove();
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
            return TDoubleDoubleHashMapDecorator.this._map.size();
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public boolean add(Map.Entry<Double, Double> entry) {
            throw new UnsupportedOperationException();
        }
    }

    @Override // java.util.AbstractMap, java.util.Map
    public Double remove(Object obj) {
        return remove((Double) obj);
    }

    @Override // java.util.AbstractMap, java.util.Map
    public Double get(Object obj) {
        return get((Double) obj);
    }
}
