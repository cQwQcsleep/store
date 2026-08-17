package gnu.trove.decorator;

import gnu.trove.TFloatIntHashMap;
import gnu.trove.TFloatIntIterator;
import java.util.AbstractMap;
import java.util.AbstractSet;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
public class TFloatIntHashMapDecorator extends AbstractMap<Float, Integer> {
    protected final TFloatIntHashMap _map;

    public TFloatIntHashMapDecorator(TFloatIntHashMap tFloatIntHashMap) {
        this._map = tFloatIntHashMap;
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
    public Set<Map.Entry<Float, Integer>> entrySet() {
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
            if (!(key instanceof Float) || !(value instanceof Integer)) {
                break;
            }
            float fUnwrapKey = unwrapKey(key);
            int iUnwrapValue = unwrapValue(value);
            if (!this._map.containsKey(fUnwrapKey) || iUnwrapValue != this._map.get(fUnwrapKey)) {
                break;
            }
            size = i;
        }
        return false;
    }

    public Integer get(Float f) {
        float fUnwrapKey = unwrapKey(f);
        int i = this._map.get(fUnwrapKey);
        if (i != 0 || this._map.containsKey(fUnwrapKey)) {
            return wrapValue(i);
        }
        return null;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public boolean isEmpty() {
        return size() == 0;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public Integer put(Float f, Integer num) {
        return wrapValue(this._map.put(unwrapKey(f), unwrapValue(num)));
    }

    @Override // java.util.AbstractMap, java.util.Map
    public void putAll(Map<? extends Float, ? extends Integer> map) {
        Iterator<Map.Entry<? extends Float, ? extends Integer>> it2 = map.entrySet().iterator();
        int size = map.size();
        while (true) {
            int i = size - 1;
            if (size <= 0) {
                return;
            }
            Map.Entry<? extends Float, ? extends Integer> next = it2.next();
            put(next.getKey(), next.getValue());
            size = i;
        }
    }

    public Integer remove(Float f) {
        return wrapValue(this._map.remove(unwrapKey(f)));
    }

    @Override // java.util.AbstractMap, java.util.Map
    public int size() {
        return this._map.size();
    }

    public float unwrapKey(Object obj) {
        return ((Float) obj).floatValue();
    }

    public int unwrapValue(Object obj) {
        return ((Integer) obj).intValue();
    }

    public Float wrapKey(float f) {
        return new Float(f);
    }

    public Integer wrapValue(int i) {
        return new Integer(i);
    }

    /* JADX INFO: renamed from: gnu.trove.decorator.TFloatIntHashMapDecorator$1, reason: invalid class name */
    public class AnonymousClass1 extends AbstractSet<Map.Entry<Float, Integer>> {
        public AnonymousClass1() {
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public boolean addAll(Collection<? extends Map.Entry<Float, Integer>> collection) {
            throw new UnsupportedOperationException();
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public void clear() {
            TFloatIntHashMapDecorator.this.clear();
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public boolean contains(Object obj) {
            if (obj instanceof Map.Entry) {
                Map.Entry entry = (Map.Entry) obj;
                Object key = entry.getKey();
                Object value = entry.getValue();
                if (TFloatIntHashMapDecorator.this.containsKey(key) && TFloatIntHashMapDecorator.this.get(key).equals(value)) {
                    return true;
                }
            }
            return false;
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public boolean isEmpty() {
            return TFloatIntHashMapDecorator.this.isEmpty();
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.Set
        public Iterator<Map.Entry<Float, Integer>> iterator() {
            return new Iterator<Map.Entry<Float, Integer>>() { // from class: gnu.trove.decorator.TFloatIntHashMapDecorator.1.1

                /* JADX INFO: renamed from: it, reason: collision with root package name */
                private final TFloatIntIterator f23it;

                {
                    this.f23it = TFloatIntHashMapDecorator.this._map.iterator();
                }

                @Override // java.util.Iterator
                public boolean hasNext() {
                    return this.f23it.hasNext();
                }

                @Override // java.util.Iterator
                public Map.Entry<Float, Integer> next() {
                    this.f23it.advance();
                    return new Map.Entry<Float, Integer>(TFloatIntHashMapDecorator.this.wrapValue(this.f23it.value()), TFloatIntHashMapDecorator.this.wrapKey(this.f23it.key())) { // from class: gnu.trove.decorator.TFloatIntHashMapDecorator.1.1.1
                        private Integer val;
                        final /* synthetic */ Float val$key;
                        final /* synthetic */ Integer val$v;

                        {
                            this.val$v = num;
                            this.val$key = f;
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
                            return TFloatIntHashMapDecorator.this.put(this.val$key, num);
                        }

                        /* JADX WARN: Can't rename method to resolve collision */
                        @Override // java.util.Map.Entry
                        public Float getKey() {
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
                    this.f23it.remove();
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
            return TFloatIntHashMapDecorator.this._map.size();
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public boolean add(Map.Entry<Float, Integer> entry) {
            throw new UnsupportedOperationException();
        }
    }

    @Override // java.util.AbstractMap, java.util.Map
    public Integer remove(Object obj) {
        return remove((Float) obj);
    }

    @Override // java.util.AbstractMap, java.util.Map
    public Integer get(Object obj) {
        return get((Float) obj);
    }
}
