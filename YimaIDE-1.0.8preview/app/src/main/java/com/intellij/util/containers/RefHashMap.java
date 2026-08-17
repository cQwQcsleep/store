package com.intellij.util.containers;

import androidx.collection.ScatterMapKt;
import com.intellij.util.IncorrectOperationException;
import com.intellij.util.ObjectUtilsRt;
import it.unimi.dsi.fastutil.HashCommon;
import it.unimi.dsi.fastutil.objects.Object2ObjectOpenHashMap;
import java.lang.ref.ReferenceQueue;
import java.util.AbstractMap;
import java.util.AbstractSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.function.BiConsumer;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
abstract class RefHashMap<K, V> extends AbstractMap<K, V> implements ReferenceQueueable, Map<K, V> {
    private Set<Map.Entry<K, V>> entrySet;
    private final BiConsumer<? super Map<K, V>, ? super V> myEvictionListener;
    private final RefHashMap<K, V>.HardKey myHardKeyInstance;
    private final RefHashMap<K, V>.MyMap myMap;
    private final ReferenceQueue<K> myReferenceQueue;
    private final HashingStrategy<? super K> myStrategy;

    public class EntrySet extends AbstractSet<Map.Entry<K, V>> {
        private final Set<Map.Entry<Key<K>, V>> hashEntrySet;

        private EntrySet() {
            this.hashEntrySet = RefHashMap.this.myMap.entrySet();
        }

        @Override // java.util.AbstractSet, java.util.Collection, java.util.Set
        public int hashCode() {
            int iHashCode = 0;
            for (Map.Entry<Key<K>, V> entry : this.hashEntrySet) {
                Key<K> key = entry.getKey();
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
            return !iterator().hasNext();
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.Set
        public Iterator<Map.Entry<K, V>> iterator() {
            return new Iterator<Map.Entry<K, V>>() { // from class: com.intellij.util.containers.RefHashMap.EntrySet.1
                private final Iterator<Map.Entry<Key<K>, V>> hashIterator;
                private MyEntry<K, V> next;

                {
                    this.hashIterator = EntrySet.this.hashEntrySet.iterator();
                }

                @Override // java.util.Iterator
                public boolean hasNext() {
                    while (this.hashIterator.hasNext()) {
                        Map.Entry<Key<K>, V> next = this.hashIterator.next();
                        Key<K> key = next.getKey();
                        K k = key.get();
                        if (k != null) {
                            this.next = new MyEntry<>(next, k, key.hashCode(), RefHashMap.this.myStrategy);
                            return true;
                        }
                    }
                    return false;
                }

                @Override // java.util.Iterator
                public Map.Entry<K, V> next() {
                    if (this.next == null && !hasNext()) {
                        z0e.a();
                        return null;
                    }
                    MyEntry<K, V> myEntry = this.next;
                    this.next = null;
                    return myEntry;
                }

                @Override // java.util.Iterator
                public void remove() {
                    this.hashIterator.remove();
                }
            };
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public boolean remove(Object obj) {
            RefHashMap.this.processQueue();
            boolean zEquals = false;
            if (!(obj instanceof Map.Entry)) {
                return false;
            }
            Map.Entry entry = (Map.Entry) obj;
            Object value = entry.getValue();
            HardKey hardKey = RefHashMap.this.myHardKeyInstance;
            try {
                hardKey.set(entry.getKey());
                Object obj2 = RefHashMap.this.myMap.get(hardKey);
                if (obj2 != null) {
                    zEquals = obj2.equals(value);
                } else if (value == null && RefHashMap.this.myMap.containsKey(hardKey)) {
                    zEquals = true;
                }
                if (zEquals) {
                    RefHashMap.this.myMap.remove(hardKey);
                }
                return zEquals;
            } finally {
                hardKey.clear();
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

    public class HardKey implements Key<K> {
        private int myHash;
        private K myObject;

        private static /* synthetic */ void $$$reportNull$$$0(int i) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "object", "com/intellij/util/containers/RefHashMap$HardKey", "set"));
        }

        private HardKey() {
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clear() {
            this.myObject = null;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void set(K k) {
            if (k == null) {
                $$$reportNull$$$0(0);
            }
            this.myObject = k;
            this.myHash = RefHashMap.this.myStrategy.hashCode(k);
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj instanceof Key) {
                return RefHashMap.keysEqual(this.myObject, ((Key) obj).get(), RefHashMap.this.myStrategy);
            }
            return false;
        }

        @Override // com.intellij.util.containers.RefHashMap.Key
        public K get() {
            return this.myObject;
        }

        @Override // com.intellij.util.containers.RefHashMap.Key
        public int hashCode() {
            return this.myHash;
        }
    }

    @FunctionalInterface
    public interface Key<T> {
        T get();

        int hashCode();
    }

    public static final class MyEntry<K, V> implements Map.Entry<K, V> {
        private final Map.Entry<?, V> ent;
        private final K key;
        private final int myKeyHashCode;
        private final HashingStrategy<? super K> myStrategy;

        private static /* synthetic */ void $$$reportNull$$$0(int i) {
            Object[] objArr = new Object[3];
            if (i == 1) {
                objArr[0] = "key";
            } else if (i != 2) {
                objArr[0] = "ent";
            } else {
                objArr[0] = "strategy";
            }
            objArr[1] = "com/intellij/util/containers/RefHashMap$MyEntry";
            objArr[2] = "<init>";
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", objArr));
        }

        private MyEntry(Map.Entry<?, V> entry, K k, int i, HashingStrategy<? super K> hashingStrategy) {
            if (entry == null) {
                $$$reportNull$$$0(0);
            }
            if (k == null) {
                $$$reportNull$$$0(1);
            }
            if (hashingStrategy == null) {
                $$$reportNull$$$0(2);
            }
            this.ent = entry;
            this.key = k;
            this.myKeyHashCode = i;
            this.myStrategy = hashingStrategy;
        }

        @Override // java.util.Map.Entry
        public boolean equals(Object obj) {
            if (!(obj instanceof Map.Entry)) {
                return false;
            }
            Map.Entry entry = (Map.Entry) obj;
            return RefHashMap.keysEqual(this.key, entry.getKey(), this.myStrategy) && Objects.equals(getValue(), entry.getValue());
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
            int i = this.myKeyHashCode;
            V value = getValue();
            return (value == null ? 0 : value.hashCode()) ^ i;
        }

        @Override // java.util.Map.Entry
        public V setValue(V v) {
            return this.ent.setValue(v);
        }
    }

    public class MyMap extends Object2ObjectOpenHashMap<Key<K>, V> {
        static final /* synthetic */ boolean $assertionsDisabled = false;

        private MyMap(int i, float f) {
            super(i, f);
        }

        public void rehash(int i) {
            Object[] objArr = ((Object2ObjectOpenHashMap) this).key;
            Object[] objArr2 = ((Object2ObjectOpenHashMap) this).value;
            int i2 = i - 1;
            int i3 = i + 1;
            Key[] keyArr = new Key[i3];
            Object[] objArr3 = new Object[i3];
            int i4 = ((Object2ObjectOpenHashMap) this).size;
            for (int i5 = ((Object2ObjectOpenHashMap) this).n; i5 >= 0 && i4 > 0; i5--) {
                Key key = (Key) objArr[i5];
                if (key != null) {
                    i4--;
                    Object obj = key.get();
                    if (obj == null) {
                        ((Object2ObjectOpenHashMap) this).size--;
                    } else {
                        int iMix = HashCommon.mix(key.hashCode()) & i2;
                        if (keyArr[iMix] != null) {
                            do {
                                iMix = (iMix + 1) & i2;
                            } while (keyArr[iMix] != null);
                        }
                        keyArr[iMix] = key;
                        objArr3[iMix] = objArr2[i5];
                        ObjectUtilsRt.reachabilityFence(obj);
                    }
                }
            }
            objArr3[i] = objArr2[((Object2ObjectOpenHashMap) this).n];
            ((Object2ObjectOpenHashMap) this).n = i;
            ((Object2ObjectOpenHashMap) this).mask = i2;
            ((Object2ObjectOpenHashMap) this).maxFill = HashCommon.maxFill(i, ((Object2ObjectOpenHashMap) this).f);
            ((Object2ObjectOpenHashMap) this).key = keyArr;
            ((Object2ObjectOpenHashMap) this).value = objArr3;
        }
    }

    private static /* synthetic */ void $$$reportNull$$$0(int i) {
        String str = (i == 6 || i == 12) ? "@NotNull method %s.%s must not return null" : "Argument for @NotNull parameter '%s' of %s.%s must not be null";
        Object[] objArr = new Object[(i == 6 || i == 12) ? 2 : 3];
        switch (i) {
            case 2:
                objArr[0] = "hashingStrategy";
                break;
            case 3:
            default:
                objArr[0] = "strategy";
                break;
            case 4:
            case 5:
            case 8:
            case 9:
            case 10:
            case 11:
                objArr[0] = "key";
                break;
            case ScatterMapKt.DefaultScatterCapacity /* 6 */:
            case 12:
                objArr[0] = "com/intellij/util/containers/RefHashMap";
                break;
            case ScatterMapKt.ClonedMetadataCount /* 7 */:
                objArr[0] = "weakKey";
                break;
        }
        if (i == 6) {
            objArr[1] = "createKey";
        } else if (i != 12) {
            objArr[1] = "com/intellij/util/containers/RefHashMap";
        } else {
            objArr[1] = "entrySet";
        }
        switch (i) {
            case 3:
                objArr[2] = "keysEqual";
                break;
            case 4:
                objArr[2] = "removeKey";
                break;
            case 5:
                objArr[2] = "createKey";
                break;
            case ScatterMapKt.DefaultScatterCapacity /* 6 */:
            case 12:
                break;
            case ScatterMapKt.ClonedMetadataCount /* 7 */:
                objArr[2] = "putKey";
                break;
            case 8:
                objArr[2] = "containsKey";
                break;
            case 9:
                objArr[2] = "get";
                break;
            case 10:
                objArr[2] = "put";
                break;
            case 11:
                objArr[2] = "remove";
                break;
            default:
                objArr[2] = "<init>";
                break;
        }
        String str2 = String.format(str, objArr);
        if (i != 6 && i != 12) {
            throw new IllegalArgumentException(str2);
        }
        throw new IllegalStateException(str2);
    }

    public RefHashMap(int i, float f, HashingStrategy<? super K> hashingStrategy, BiConsumer<? super Map<K, V>, ? super V> biConsumer) {
        if (hashingStrategy == null) {
            $$$reportNull$$$0(1);
        }
        this.myReferenceQueue = new ReferenceQueue<>();
        this.myHardKeyInstance = new HardKey();
        this.myStrategy = hashingStrategy;
        this.myMap = new MyMap(i, f);
        this.myEvictionListener = biConsumer;
    }

    public static <K> boolean keysEqual(K k, K k2, HashingStrategy<? super K> hashingStrategy) {
        if (hashingStrategy == null) {
            $$$reportNull$$$0(3);
        }
        return k == k2 || hashingStrategy.equals(k, k2);
    }

    @Override // java.util.AbstractMap, java.util.Map
    public void clear() {
        processQueue();
        this.myMap.clear();
    }

    @Override // java.util.AbstractMap, java.util.Map
    public boolean containsKey(Object obj) {
        if (obj == null) {
            $$$reportNull$$$0(8);
        }
        this.myHardKeyInstance.set(obj);
        try {
            return this.myMap.containsKey(this.myHardKeyInstance);
        } finally {
            this.myHardKeyInstance.clear();
        }
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: com.intellij.util.IncorrectOperationException */
    @Override // java.util.AbstractMap, java.util.Map
    public boolean containsValue(Object obj) throws IncorrectOperationException {
        throw RefValueHashMapUtil.pointlessContainsValue();
    }

    public Key<K> createKey(K k) {
        if (k == null) {
            $$$reportNull$$$0(5);
        }
        Key<K> key = (Key<K>) createKey(k, this.myStrategy, this.myReferenceQueue);
        if (key == null) {
            $$$reportNull$$$0(6);
        }
        return key;
    }

    public abstract <T> Key<T> createKey(T t, HashingStrategy<? super T> hashingStrategy, ReferenceQueue<? super T> referenceQueue);

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

    @Override // java.util.AbstractMap, java.util.Map
    public V get(Object obj) {
        if (obj == null) {
            $$$reportNull$$$0(9);
        }
        this.myHardKeyInstance.set(obj);
        try {
            return (V) this.myMap.get(this.myHardKeyInstance);
        } finally {
            this.myHardKeyInstance.clear();
        }
    }

    @Override // java.util.AbstractMap, java.util.Map
    public boolean isEmpty() {
        return this.myMap.isEmpty() || entrySet().isEmpty();
    }

    @Override // com.intellij.util.containers.ReferenceQueueable
    public boolean processQueue() {
        boolean z = false;
        while (true) {
            Key<K> key = (Key) this.myReferenceQueue.poll();
            if (key == null) {
                return z;
            }
            V vRemoveKey = removeKey(key);
            BiConsumer<? super Map<K, V>, ? super V> biConsumer = this.myEvictionListener;
            if (biConsumer != null) {
                biConsumer.accept(this, vRemoveKey);
            }
            z = true;
        }
    }

    @Override // java.util.AbstractMap, java.util.Map
    public V put(K k, V v) {
        if (k == null) {
            $$$reportNull$$$0(10);
        }
        processQueue();
        return putKey(createKey(k), v);
    }

    public V putKey(Key<K> key, V v) {
        if (key == null) {
            $$$reportNull$$$0(7);
        }
        return (V) this.myMap.put(key, v);
    }

    @Override // java.util.AbstractMap, java.util.Map
    public V remove(Object obj) {
        if (obj == null) {
            $$$reportNull$$$0(11);
        }
        processQueue();
        this.myHardKeyInstance.set(obj);
        try {
            return (V) this.myMap.remove(this.myHardKeyInstance);
        } finally {
            this.myHardKeyInstance.clear();
        }
    }

    public V removeKey(Key<K> key) {
        if (key == null) {
            $$$reportNull$$$0(4);
        }
        return (V) this.myMap.remove(key);
    }

    @Override // java.util.AbstractMap, java.util.Map
    public int size() {
        return entrySet().size();
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public RefHashMap(int i, float f, HashingStrategy<? super K> hashingStrategy) {
        this(i, f, hashingStrategy, null);
        if (hashingStrategy == null) {
            $$$reportNull$$$0(0);
        }
    }
}
