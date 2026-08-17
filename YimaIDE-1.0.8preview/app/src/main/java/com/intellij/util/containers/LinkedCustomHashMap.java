package com.intellij.util.containers;

import com.intellij.util.containers.LinkedCustomHashMap;
import com.intellij.util.containers.hash.EqualityPolicy;
import it.unimi.dsi.fastutil.HashCommon;
import java.util.AbstractSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
public final class LinkedCustomHashMap<K, V> {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private Entry<K, V> back;
    private Entry<K, V>[] entries;
    private final EqualityPolicy<? super K> hashingStrategy;
    private transient int mask;
    private transient int maxFill;
    private transient int n;
    private final RemoveCallback<K, V> removeEldestEntry;
    private int size;
    private Entry<K, V> top;

    public static final class Entry<K, V> implements Map.Entry<K, V> {
        private Entry<K, V> hashNext;
        private final K key;
        private final int keyHash;
        private Entry<K, V> next;
        private Entry<K, V> previous;
        private V value;

        public Entry(K k, V v, int i) {
            this.key = k;
            this.keyHash = i;
            this.value = v;
        }

        @Override // java.util.Map.Entry
        public K getKey() {
            return this.key;
        }

        @Override // java.util.Map.Entry
        public V getValue() {
            return this.value;
        }

        @Override // java.util.Map.Entry
        public V setValue(V v) {
            V v2 = this.value;
            this.value = v;
            return v2;
        }
    }

    public final class EntrySet extends AbstractSet<Map.Entry<K, V>> {
        private EntrySet() {
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public void clear() {
            LinkedCustomHashMap.this.clear();
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public boolean contains(Object obj) {
            if (!(obj instanceof Map.Entry)) {
                return false;
            }
            Map.Entry entry = (Map.Entry) obj;
            Object obj2 = LinkedCustomHashMap.this.get(entry.getKey());
            return obj2 != null && obj2.equals(entry.getValue());
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.Set
        public Iterator<Map.Entry<K, V>> iterator() {
            return new LinkedHashIterator() { // from class: com.intellij.util.containers.LinkedCustomHashMap.EntrySet.1
                {
                    LinkedCustomHashMap linkedCustomHashMap = LinkedCustomHashMap.this;
                }

                @Override // java.util.Iterator
                public Map.Entry<K, V> next() {
                    return nextEntry();
                }
            };
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public boolean remove(Object obj) {
            return (obj instanceof Map.Entry) && LinkedCustomHashMap.this.remove(((Map.Entry) obj).getKey()) != null;
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public int size() {
            return LinkedCustomHashMap.this.size;
        }
    }

    public final class KeySet extends AbstractSet<K> {
        private KeySet() {
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public void clear() {
            LinkedCustomHashMap.this.clear();
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public boolean contains(Object obj) {
            return LinkedCustomHashMap.this.containsKey(obj);
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.Set
        public Iterator<K> iterator() {
            return new LinkedHashIterator() { // from class: com.intellij.util.containers.LinkedCustomHashMap.KeySet.1
                {
                    LinkedCustomHashMap linkedCustomHashMap = LinkedCustomHashMap.this;
                }

                @Override // java.util.Iterator
                public K next() {
                    return (K) ((Entry) nextEntry()).key;
                }
            };
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public boolean remove(Object obj) {
            return LinkedCustomHashMap.this.remove(obj) != null;
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public int size() {
            return LinkedCustomHashMap.this.size;
        }
    }

    public abstract class LinkedHashIterator<T> implements Iterator<T> {
        private Entry<K, V> e;
        private Entry<K, V> last;

        private LinkedHashIterator() {
            this.e = LinkedCustomHashMap.this.back;
        }

        @Override // java.util.Iterator
        public boolean hasNext() {
            return this.e != null;
        }

        public Entry<K, V> nextEntry() {
            Entry<K, V> entry = this.e;
            this.last = entry;
            this.e = ((Entry) entry).previous;
            return entry;
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // java.util.Iterator
        public void remove() {
            Entry<K, V> entry = this.last;
            if (entry == null) {
                g33.a();
            } else {
                LinkedCustomHashMap.this.remove(((Entry) entry).key);
                this.last = null;
            }
        }
    }

    @FunctionalInterface
    public interface RemoveCallback<K, V> {
        boolean check(int i, Map.Entry<K, V> entry, K k, V v);
    }

    private static /* synthetic */ void $$$reportNull$$$0(int i) {
        Object[] objArr = new Object[3];
        if (i == 1) {
            objArr[0] = "hashingStrategy";
        } else if (i != 3) {
            objArr[0] = "removeEldestEntry";
        } else {
            objArr[0] = "value";
        }
        objArr[1] = "com/intellij/util/containers/LinkedCustomHashMap";
        if (i != 3) {
            objArr[2] = "<init>";
        } else {
            objArr[2] = "put";
        }
        throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", objArr));
    }

    public LinkedCustomHashMap(EqualityPolicy<? super K> equalityPolicy, RemoveCallback<K, V> removeCallback) {
        if (equalityPolicy == null) {
            $$$reportNull$$$0(1);
        }
        if (removeCallback == null) {
            $$$reportNull$$$0(2);
        }
        this.hashingStrategy = equalityPolicy;
        this.removeEldestEntry = removeCallback;
        init();
    }

    public static /* synthetic */ boolean a(int i, Map.Entry entry, Object obj, Object obj2) {
        return false;
    }

    private int hashKey(K k) {
        if (k == null) {
            return 0;
        }
        return HashCommon.mix(this.hashingStrategy.getHashCode(k));
    }

    private void init() {
        int iArraySize = HashCommon.arraySize(16, 0.6f);
        this.n = iArraySize;
        this.mask = iArraySize - 1;
        this.maxFill = HashCommon.maxFill(iArraySize, 0.6f);
        this.entries = new Entry[this.n + 1];
        this.size = 0;
    }

    private void moveToTop(Entry<K, V> entry) {
        Entry<K, V> entry2 = this.top;
        if (entry2 == entry) {
            return;
        }
        Entry<K, V> entry3 = ((Entry) entry).previous;
        Entry entry4 = ((Entry) entry).next;
        ((Entry) entry3).next = entry4;
        if (entry4 != null) {
            entry4.previous = entry3;
        } else {
            this.back = entry3;
        }
        ((Entry) entry2).previous = entry;
        ((Entry) entry).next = entry2;
        ((Entry) entry).previous = null;
        this.top = entry;
    }

    private void rehash(int i) {
        this.mask = i - 1;
        this.n = i;
        this.maxFill = HashCommon.maxFill(i, 0.6f);
        Entry<K, V>[] entryArr = new Entry[i];
        for (Entry<K, V> entry = this.back; entry != null; entry = ((Entry) entry).previous) {
            int i2 = ((Entry) entry).keyHash & this.mask;
            ((Entry) entry).hashNext = entryArr[i2];
            entryArr[i2] = entry;
        }
        this.entries = entryArr;
    }

    private void unlink(Entry<K, V> entry) {
        Entry<K, V> entry2 = ((Entry) entry).previous;
        Entry<K, V> entry3 = ((Entry) entry).next;
        if (entry2 != null) {
            ((Entry) entry2).next = entry3;
        } else {
            this.top = entry3;
        }
        if (entry3 != null) {
            ((Entry) entry3).previous = entry2;
        } else {
            this.back = entry2;
        }
        ((Entry) entry).previous = null;
        ((Entry) entry).next = null;
    }

    public void clear() {
        if (this.size == 0) {
            return;
        }
        this.back = null;
        this.top = null;
        init();
    }

    public boolean containsKey(K k) {
        return get(k) != null;
    }

    public Set<Map.Entry<K, V>> entrySet() {
        return new EntrySet();
    }

    public V get(K k) {
        Object obj;
        Entry<K, V>[] entryArr = this.entries;
        int iHashKey = hashKey(k);
        for (Entry<K, V> entry = entryArr[this.mask & iHashKey]; entry != null; entry = ((Entry) entry).hashNext) {
            if (((Entry) entry).keyHash == iHashKey && ((obj = ((Entry) entry).key) == k || this.hashingStrategy.isEqual(obj, k))) {
                moveToTop(entry);
                return (V) ((Entry) entry).value;
            }
        }
        return null;
    }

    public boolean isEmpty() {
        return this.size == 0;
    }

    public Set<K> keySet() {
        return new KeySet();
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference fix 'apply assigned field type' failed
    java.lang.UnsupportedOperationException: ArgType.getObject(), call class: class jadx.core.dex.instructions.args.ArgType$PrimitiveArg
    	at jadx.core.dex.instructions.args.ArgType.getObject(ArgType.java:596)
    	at jadx.core.dex.attributes.nodes.ClassTypeVarsAttr.getTypeVarsMapFor(ClassTypeVarsAttr.java:35)
    	at jadx.core.dex.nodes.utils.TypeUtils.replaceClassGenerics(TypeUtils.java:177)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.insertExplicitUseCast(FixTypesVisitor.java:397)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryFieldTypeWithNewCasts(FixTypesVisitor.java:359)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.applyFieldType(FixTypesVisitor.java:309)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:94)
     */
    public V put(K k, V v) {
        Object obj;
        if (v == null) {
            $$$reportNull$$$0(3);
        }
        Entry<K, V>[] entryArr = this.entries;
        int iHashKey = hashKey(k);
        int i = this.mask & iHashKey;
        for (Entry<K, V> entry = entryArr[i]; entry != null; entry = ((Entry) entry).hashNext) {
            if (((Entry) entry).keyHash == iHashKey && ((obj = ((Entry) entry).key) == k || this.hashingStrategy.isEqual(obj, k))) {
                moveToTop(entry);
                return entry.setValue(v);
            }
        }
        Entry<K, V> entry2 = new Entry<>(k, v, iHashKey);
        ((Entry) entry2).hashNext = entryArr[i];
        entryArr[i] = entry2;
        Entry<K, V> entry3 = this.top;
        ((Entry) entry2).next = entry3;
        if (entry3 != null) {
            ((Entry) entry3).previous = entry2;
        } else {
            this.back = entry2;
        }
        this.top = entry2;
        int i2 = this.size + 1;
        this.size = i2;
        RemoveCallback<K, V> removeCallback = this.removeEldestEntry;
        Entry<K, V> entry4 = this.back;
        if (removeCallback.check(i2, entry4, ((Entry) entry4).key, ((Entry) this.back).value)) {
            remove(((Entry) this.back).key);
            return null;
        }
        int i3 = this.size;
        if (i3 < this.maxFill) {
            return null;
        }
        rehash(HashCommon.arraySize(i3 + 1, 0.6f));
        return null;
    }

    public V remove(K k) {
        Entry<K, V> entry;
        Object obj;
        Object obj2;
        Entry<K, V>[] entryArr = this.entries;
        int iHashKey = hashKey(k);
        int i = this.mask & iHashKey;
        Entry<K, V> entry2 = entryArr[i];
        if (entry2 == null) {
            return null;
        }
        if (((Entry) entry2).keyHash == iHashKey && ((obj2 = ((Entry) entry2).key) == k || this.hashingStrategy.isEqual(obj2, k))) {
            entryArr[i] = ((Entry) entry2).hashNext;
        } else {
            while (true) {
                entry = ((Entry) entry2).hashNext;
                if (entry != null) {
                    if (((Entry) entry).keyHash == iHashKey && ((obj = ((Entry) entry).key) == k || this.hashingStrategy.isEqual(obj, k))) {
                        break;
                    }
                    entry2 = entry;
                } else {
                    return null;
                }
            }
            ((Entry) entry2).hashNext = ((Entry) entry).hashNext;
            entry2 = entry;
        }
        unlink(entry2);
        this.size--;
        return (V) ((Entry) entry2).value;
    }

    public LinkedCustomHashMap() {
        this(EqualityPolicy.CANONICAL, new RemoveCallback() { // from class: n99
            @Override // com.intellij.util.containers.LinkedCustomHashMap.RemoveCallback
            public final boolean check(int i, Map.Entry entry, Object obj, Object obj2) {
                return LinkedCustomHashMap.a(i, entry, obj, obj2);
            }
        });
    }
}
