package com.intellij.util.containers;

import androidx.collection.ScatterMapKt;
import com.intellij.util.IncorrectOperationException;
import java.lang.ref.ReferenceQueue;
import java.util.AbstractMap;
import java.util.AbstractSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.function.BiConsumer;
import java.util.function.Supplier;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
abstract class ConcurrentRefHashMap<K, V> extends AbstractMap<K, V> implements HashingStrategy<K>, ReferenceQueueable, ConcurrentMap<K, V> {
    private Set<Map.Entry<K, V>> entrySet;
    private final BiConsumer<? super ConcurrentMap<K, V>, ? super V> myEvictionListener;
    private final HashingStrategy<? super K> myHashingStrategy;
    private final ConcurrentMap<KeyReference<K>, V> myMap;
    final ReferenceQueue<K> myReferenceQueue;
    static final int DEFAULT_CONCURRENCY_LEVEL = Math.min(Runtime.getRuntime().availableProcessors(), 4);
    private static final HashingStrategy<?> THIS = new HashingStrategy<Object>() { // from class: com.intellij.util.containers.ConcurrentRefHashMap.1
        @Override // com.intellij.util.containers.HashingStrategy
        public boolean equals(Object obj, Object obj2) {
            throw new UnsupportedOperationException();
        }

        @Override // com.intellij.util.containers.HashingStrategy
        public int hashCode(Object obj) {
            throw new UnsupportedOperationException();
        }
    };
    private static final ThreadLocal<HardKey<?>> HARD_KEY = ThreadLocal.withInitial(new Supplier() { // from class: com.intellij.util.containers.a
        @Override // java.util.function.Supplier
        public final Object get() {
            return ConcurrentRefHashMap.e();
        }
    });

    public final class EntrySet extends AbstractSet<Map.Entry<K, V>> {
        private final Set<Map.Entry<KeyReference<K>, V>> hashEntrySet;

        private EntrySet() {
            this.hashEntrySet = ConcurrentRefHashMap.this.myMap.entrySet();
        }

        @Override // java.util.AbstractSet, java.util.Collection, java.util.Set
        public int hashCode() {
            int iHashCode = 0;
            for (Map.Entry<KeyReference<K>, V> entry : this.hashEntrySet) {
                KeyReference<K> key = entry.getKey();
                if (key != null) {
                    int iHashCode2 = key.hashCode();
                    V value = entry.getValue();
                    iHashCode += (value == null ? 0 : value.hashCode()) ^ iHashCode2;
                }
            }
            return iHashCode;
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public boolean isEmpty() {
            Iterator<Map.Entry<KeyReference<K>, V>> it = this.hashEntrySet.iterator();
            while (it.hasNext()) {
                KeyReference<K> key = it.next().getKey();
                if (key == null || key.get() != null) {
                    return false;
                }
            }
            return true;
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.Set
        public Iterator<Map.Entry<K, V>> iterator() {
            return new Iterator<Map.Entry<K, V>>() { // from class: com.intellij.util.containers.ConcurrentRefHashMap.EntrySet.1
                private final Iterator<Map.Entry<KeyReference<K>, V>> hashIterator;
                private RefEntry<K, V> next;

                {
                    this.hashIterator = EntrySet.this.hashEntrySet.iterator();
                }

                @Override // java.util.Iterator
                public boolean hasNext() {
                    K k;
                    while (this.hashIterator.hasNext()) {
                        Map.Entry<KeyReference<K>, V> next = this.hashIterator.next();
                        KeyReference<K> key = next.getKey();
                        if (key != null) {
                            k = key.get();
                            if (k == null) {
                            }
                        } else {
                            k = null;
                        }
                        this.next = new RefEntry<>(next, k);
                        return true;
                    }
                    return false;
                }

                @Override // java.util.Iterator
                public Map.Entry<K, V> next() {
                    if (this.next == null && !hasNext()) {
                        z0e.a();
                        return null;
                    }
                    RefEntry<K, V> refEntry = this.next;
                    this.next = null;
                    return refEntry;
                }

                @Override // java.util.Iterator
                public void remove() {
                    this.hashIterator.remove();
                }
            };
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public boolean remove(Object obj) {
            boolean zEquals = false;
            if (!(obj instanceof Map.Entry)) {
                return false;
            }
            Map.Entry entry = (Map.Entry) obj;
            Object value = entry.getValue();
            HardKey hardKeyCreateHardKey = ConcurrentRefHashMap.this.createHardKey(entry.getKey());
            try {
                Object obj2 = ConcurrentRefHashMap.this.myMap.get(hardKeyCreateHardKey);
                if (obj2 != null) {
                    zEquals = obj2.equals(value);
                } else if (value == null && ConcurrentRefHashMap.this.myMap.containsKey(hardKeyCreateHardKey)) {
                    zEquals = true;
                }
                if (zEquals) {
                    ConcurrentRefHashMap.this.myMap.remove(hardKeyCreateHardKey);
                }
                ConcurrentRefHashMap.this.processQueue();
                return zEquals;
            } finally {
                hardKeyCreateHardKey.clear();
            }
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public int size() {
            Iterator<Map.Entry<K, V>> it = iterator();
            int i = 0;
            while (it.hasNext()) {
                i++;
                it.next();
            }
            return i;
        }
    }

    public static class HardKey<K> implements KeyReference<K> {
        private int myHash;
        private K myKey;

        private HardKey() {
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clear() {
            setKey(null, 0);
        }

        public boolean equals(Object obj) {
            return obj.equals(this);
        }

        @Override // com.intellij.util.containers.ConcurrentRefHashMap.KeyReference
        public K get() {
            return this.myKey;
        }

        @Override // com.intellij.util.containers.ConcurrentRefHashMap.KeyReference
        public int hashCode() {
            return this.myHash;
        }

        public void setKey(K k, int i) {
            this.myKey = k;
            this.myHash = i;
        }
    }

    @FunctionalInterface
    public interface KeyReference<K> {
        K get();

        int hashCode();
    }

    public static final class RefEntry<K, V> implements Map.Entry<K, V> {
        private final Map.Entry<?, V> ent;
        private final K key;

        private static /* synthetic */ void $$$reportNull$$$0(int i) {
            Object[] objArr = new Object[3];
            if (i != 1) {
                objArr[0] = "ent";
            } else {
                objArr[0] = "value";
            }
            objArr[1] = "com/intellij/util/containers/ConcurrentRefHashMap$RefEntry";
            if (i != 1) {
                objArr[2] = "<init>";
            } else {
                objArr[2] = "setValue";
            }
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", objArr));
        }

        public RefEntry(Map.Entry<?, V> entry, K k) {
            if (entry == null) {
                $$$reportNull$$$0(0);
            }
            this.ent = entry;
            this.key = k;
        }

        @Override // java.util.Map.Entry
        public boolean equals(Object obj) {
            if (!(obj instanceof Map.Entry)) {
                return false;
            }
            Map.Entry entry = (Map.Entry) obj;
            return Objects.equals(this.key, entry.getKey()) && Objects.equals(getValue(), entry.getValue());
        }

        @Override // java.util.Map.Entry
        public K getKey() {
            return this.key;
        }

        @Override // java.util.Map.Entry
        public V getValue() {
            return this.ent.getValue();
        }

        @Override // java.util.Map.Entry
        public int hashCode() {
            K k = this.key;
            int iHashCode = k == null ? 0 : k.hashCode();
            V value = getValue();
            return iHashCode ^ (value != null ? value.hashCode() : 0);
        }

        @Override // java.util.Map.Entry
        public V setValue(V v) {
            if (v == null) {
                $$$reportNull$$$0(1);
            }
            return this.ent.setValue(v);
        }
    }

    /* JADX WARN: Code duplicated, block: B:35:0x0053  */
    /* JADX WARN: Code duplicated, block: B:36:0x0059  */
    private static /* synthetic */ void $$$reportNull$$$0(int i) {
        String str = (i == 1 || i == 4 || i == 9) ? "@NotNull method %s.%s must not return null" : "Argument for @NotNull parameter '%s' of %s.%s must not be null";
        Object[] objArr = new Object[(i == 1 || i == 4 || i == 9) ? 2 : 3];
        if (i == 1) {
            objArr[0] = "com/intellij/util/containers/ConcurrentRefHashMap";
        } else if (i == 7) {
            objArr[0] = "value";
        } else if (i == 9) {
            objArr[0] = "com/intellij/util/containers/ConcurrentRefHashMap";
        } else if (i == 11 || i == 13 || i == 18) {
            objArr[0] = "value";
        } else if (i == 3) {
            objArr[0] = "o";
        } else if (i == 4) {
            objArr[0] = "com/intellij/util/containers/ConcurrentRefHashMap";
        } else if (i == 15) {
            objArr[0] = "oldValue";
        } else if (i != 16) {
            objArr[0] = "key";
        } else {
            objArr[0] = "newValue";
        }
        if (i == 1) {
            objArr[1] = "createKeyReference";
        } else if (i == 4) {
            objArr[1] = "createHardKey";
        } else if (i != 9) {
            objArr[1] = "com/intellij/util/containers/ConcurrentRefHashMap";
        } else {
            objArr[1] = "entrySet";
        }
        switch (i) {
            case 1:
            case 4:
            case 9:
                break;
            case 2:
                objArr[2] = "containsKey";
                break;
            case 3:
                objArr[2] = "createHardKey";
                break;
            case 5:
                objArr[2] = "get";
                break;
            case ScatterMapKt.DefaultScatterCapacity /* 6 */:
            case ScatterMapKt.ClonedMetadataCount /* 7 */:
                objArr[2] = "put";
                break;
            case 8:
            case 12:
            case 13:
                objArr[2] = "remove";
                break;
            case 10:
            case 11:
                objArr[2] = "putIfAbsent";
                break;
            case 14:
            case 15:
            case 16:
            case 17:
            case 18:
                objArr[2] = "replace";
                break;
            default:
                objArr[2] = "createKeyReference";
                break;
        }
        String str2 = String.format(str, objArr);
        if (i != 1 && i != 4 && i != 9) {
            throw new IllegalArgumentException(str2);
        }
        throw new IllegalStateException(str2);
    }

    public ConcurrentRefHashMap(int i, float f, int i2, HashingStrategy<? super K> hashingStrategy) {
        this.myReferenceQueue = new ReferenceQueue<>();
        if (hashingStrategy == THIS) {
            hashingStrategy = this;
        } else if (hashingStrategy == null) {
            hashingStrategy = HashingStrategy.canonical();
        }
        this.myHashingStrategy = hashingStrategy;
        this.myMap = new ConcurrentHashMap(i, f, i2);
        this.myEvictionListener = null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public HardKey<K> createHardKey(Object obj) {
        if (obj == null) {
            $$$reportNull$$$0(3);
        }
        HardKey<K> hardKey = (HardKey) HARD_KEY.get();
        hardKey.setKey(obj, this.myHashingStrategy.hashCode(obj));
        return hardKey;
    }

    private KeyReference<K> createKeyReference(K k) {
        if (k == null) {
            $$$reportNull$$$0(0);
        }
        KeyReference<K> keyReferenceCreateKeyReference = createKeyReference(k, this.myHashingStrategy);
        if (keyReferenceCreateKeyReference == null) {
            $$$reportNull$$$0(1);
        }
        return keyReferenceCreateKeyReference;
    }

    public static /* synthetic */ HardKey e() {
        return new HardKey();
    }

    @Override // java.util.AbstractMap, java.util.Map
    public void clear() {
        this.myMap.clear();
        processQueue();
    }

    @Override // java.util.AbstractMap, java.util.Map
    public boolean containsKey(Object obj) {
        if (obj == null) {
            $$$reportNull$$$0(2);
        }
        if (this.myMap.isEmpty()) {
            return false;
        }
        HardKey<K> hardKeyCreateHardKey = createHardKey(obj);
        try {
            return this.myMap.containsKey(hardKeyCreateHardKey);
        } finally {
            hardKeyCreateHardKey.clear();
        }
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: com.intellij.util.IncorrectOperationException */
    @Override // java.util.AbstractMap, java.util.Map
    public boolean containsValue(Object obj) throws IncorrectOperationException {
        throw RefValueHashMapUtil.pointlessContainsValue();
    }

    public abstract KeyReference<K> createKeyReference(K k, HashingStrategy<? super K> hashingStrategy);

    @Override // java.util.AbstractMap, java.util.Map
    public Set<Map.Entry<K, V>> entrySet() {
        Set<Map.Entry<K, V>> set = this.entrySet;
        if (set != null) {
            return set;
        }
        EntrySet entrySet = new EntrySet();
        this.entrySet = entrySet;
        return entrySet;
    }

    @Override // com.intellij.util.containers.HashingStrategy
    public boolean equals(K k, K k2) {
        return Objects.equals(k, k2);
    }

    @Override // java.util.AbstractMap, java.util.Map
    public V get(Object obj) {
        if (obj == null) {
            $$$reportNull$$$0(5);
        }
        if (this.myMap.isEmpty()) {
            return null;
        }
        HardKey<K> hardKeyCreateHardKey = createHardKey(obj);
        try {
            return this.myMap.get(hardKeyCreateHardKey);
        } finally {
            hardKeyCreateHardKey.clear();
        }
    }

    @Override // com.intellij.util.containers.HashingStrategy
    public int hashCode(K k) {
        int iHashCode = k == null ? 0 : k.hashCode();
        int i = iHashCode + (~(iHashCode << 9));
        int i2 = i ^ (i >>> 14);
        int i3 = i2 + (i2 << 4);
        return i3 ^ (i3 >>> 10);
    }

    @Override // java.util.AbstractMap, java.util.Map
    public boolean isEmpty() {
        return this.myMap.isEmpty() || entrySet().isEmpty();
    }

    @Override // com.intellij.util.containers.ReferenceQueueable
    public boolean processQueue() {
        boolean z = false;
        while (true) {
            KeyReference keyReference = (KeyReference) this.myReferenceQueue.poll();
            if (keyReference == null) {
                return z;
            }
            V vRemove = this.myMap.remove(keyReference);
            BiConsumer<? super ConcurrentMap<K, V>, ? super V> biConsumer = this.myEvictionListener;
            if (biConsumer != null) {
                biConsumer.accept(this, vRemove);
            }
            z = true;
        }
    }

    @Override // java.util.AbstractMap, java.util.Map
    public V put(K k, V v) {
        if (k == null) {
            $$$reportNull$$$0(6);
        }
        if (v == null) {
            $$$reportNull$$$0(7);
        }
        V vPut = this.myMap.put(createKeyReference(k), v);
        processQueue();
        return vPut;
    }

    @Override // java.util.Map, java.util.concurrent.ConcurrentMap
    public V putIfAbsent(K k, V v) {
        if (k == null) {
            $$$reportNull$$$0(10);
        }
        if (v == null) {
            $$$reportNull$$$0(11);
        }
        V vPutIfAbsent = this.myMap.putIfAbsent(createKeyReference(k), v);
        processQueue();
        return vPutIfAbsent;
    }

    @Override // java.util.Map, java.util.concurrent.ConcurrentMap
    public boolean remove(Object obj, Object obj2) {
        if (obj == null) {
            $$$reportNull$$$0(12);
        }
        if (obj2 == null) {
            $$$reportNull$$$0(13);
        }
        HardKey<K> hardKeyCreateHardKey = createHardKey(obj);
        try {
            boolean zRemove = this.myMap.remove(hardKeyCreateHardKey, obj2);
            processQueue();
            return zRemove;
        } finally {
            hardKeyCreateHardKey.clear();
        }
    }

    @Override // java.util.Map, java.util.concurrent.ConcurrentMap
    public boolean replace(K k, V v, V v2) {
        if (k == null) {
            $$$reportNull$$$0(14);
        }
        if (v == null) {
            $$$reportNull$$$0(15);
        }
        if (v2 == null) {
            $$$reportNull$$$0(16);
        }
        HardKey<K> hardKeyCreateHardKey = createHardKey(k);
        try {
            boolean zReplace = this.myMap.replace(hardKeyCreateHardKey, v, v2);
            processQueue();
            return zReplace;
        } finally {
            hardKeyCreateHardKey.clear();
        }
    }

    @Override // java.util.AbstractMap, java.util.Map
    public int size() {
        return entrySet().size();
    }

    public ConcurrentRefHashMap(BiConsumer<? super ConcurrentMap<K, V>, ? super V> biConsumer) {
        this.myReferenceQueue = new ReferenceQueue<>();
        this.myHashingStrategy = this;
        this.myMap = new ConcurrentHashMap(16, 0.75f, DEFAULT_CONCURRENCY_LEVEL);
        this.myEvictionListener = biConsumer;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public V remove(Object obj) {
        if (obj == null) {
            $$$reportNull$$$0(8);
        }
        HardKey<K> hardKeyCreateHardKey = createHardKey(obj);
        try {
            return this.myMap.remove(hardKeyCreateHardKey);
        } finally {
            processQueue();
            hardKeyCreateHardKey.clear();
        }
    }

    @Override // java.util.Map, java.util.concurrent.ConcurrentMap
    public V replace(K k, V v) {
        if (k == null) {
            $$$reportNull$$$0(17);
        }
        if (v == null) {
            $$$reportNull$$$0(18);
        }
        HardKey<K> hardKeyCreateHardKey = createHardKey(k);
        try {
            V vReplace = this.myMap.replace(hardKeyCreateHardKey, v);
            processQueue();
            return vReplace;
        } finally {
            hardKeyCreateHardKey.clear();
        }
    }
}
