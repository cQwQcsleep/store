package com.intellij.util.containers;

import androidx.collection.ScatterMapKt;
import com.intellij.util.ArrayUtilRt;
import com.intellij.util.containers.UnmodifiableHashMap;
import it.unimi.dsi.fastutil.Hash;
import it.unimi.dsi.fastutil.objects.Object2ObjectOpenCustomHashMap;
import it.unimi.dsi.fastutil.objects.Object2ObjectOpenHashMap;
import java.util.AbstractCollection;
import java.util.AbstractMap;
import java.util.AbstractSet;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
public final class UnmodifiableHashMap<K, V> implements Map<K, V> {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private static final UnmodifiableHashMap<Object, Object> EMPTY = new UnmodifiableHashMap<>(HashingStrategy.canonical(), ArrayUtilRt.EMPTY_OBJECT_ARRAY, null, null, null, null, null, null);
    private final Object[] data;
    private final K k1;
    private final K k2;
    private final K k3;
    private Set<K> keySet;
    private final int size;
    private final HashingStrategy<K> strategy;
    private final V v1;
    private final V v2;
    private final V v3;
    private Collection<V> values;

    public abstract class MyIterator<E> implements Iterator<E> {
        int pos;

        public MyIterator() {
            if (UnmodifiableHashMap.this.k1 != null) {
                this.pos = UnmodifiableHashMap.this.k2 != null ? UnmodifiableHashMap.this.k3 == null ? -2 : -3 : -1;
            } else {
                this.pos = -1;
                advance();
            }
        }

        private void advance() {
            int i = this.pos;
            if (i < 0) {
                this.pos = i + 1;
            } else {
                this.pos = i + 2;
            }
            if (this.pos >= 0) {
                while (this.pos < UnmodifiableHashMap.this.data.length) {
                    Object[] objArr = UnmodifiableHashMap.this.data;
                    int i2 = this.pos;
                    if (objArr[i2] != null) {
                        return;
                    } else {
                        this.pos = i2 + 1;
                    }
                }
            }
        }

        public abstract E fieldElement(int i);

        @Override // java.util.Iterator
        public boolean hasNext() {
            return this.pos < UnmodifiableHashMap.this.data.length;
        }

        @Override // java.util.Iterator
        public E next() {
            if (!hasNext()) {
                z0e.a();
                return null;
            }
            int i = this.pos;
            if (i >= 0) {
                advance();
                return tableElement(i);
            }
            int i2 = ~i;
            advance();
            return fieldElement(i2);
        }

        public abstract E tableElement(int i);
    }

    private static /* synthetic */ void $$$reportNull$$$0(int i) {
        String str;
        int i2;
        switch (i) {
            case 1:
            case 2:
            case 3:
            case 4:
            case ScatterMapKt.DefaultScatterCapacity /* 6 */:
            case ScatterMapKt.ClonedMetadataCount /* 7 */:
            case 8:
            case 10:
            case 12:
            case 15:
            case 16:
                str = "Argument for @NotNull parameter '%s' of %s.%s must not be null";
                break;
            case 5:
            case 9:
            case 11:
            case 13:
            case 14:
            default:
                str = "@NotNull method %s.%s must not return null";
                break;
        }
        switch (i) {
            case 1:
            case 2:
            case 3:
            case 4:
            case ScatterMapKt.DefaultScatterCapacity /* 6 */:
            case ScatterMapKt.ClonedMetadataCount /* 7 */:
            case 8:
            case 10:
            case 12:
            case 15:
            case 16:
                i2 = 3;
                break;
            case 5:
            case 9:
            case 11:
            case 13:
            case 14:
            default:
                i2 = 2;
                break;
        }
        Object[] objArr = new Object[i2];
        switch (i) {
            case 1:
            case 3:
            case ScatterMapKt.DefaultScatterCapacity /* 6 */:
            case 15:
                objArr[0] = "strategy";
                break;
            case 2:
            case 4:
            case 12:
                objArr[0] = "map";
                break;
            case 5:
            case 9:
            case 11:
            case 13:
            case 14:
            default:
                objArr[0] = "com/intellij/util/containers/UnmodifiableHashMap";
                break;
            case ScatterMapKt.ClonedMetadataCount /* 7 */:
                objArr[0] = "data";
                break;
            case 8:
            case 10:
                objArr[0] = "key";
                break;
            case 16:
                objArr[0] = "m";
                break;
        }
        switch (i) {
            case 1:
            case 2:
            case 3:
            case 4:
            case ScatterMapKt.DefaultScatterCapacity /* 6 */:
            case ScatterMapKt.ClonedMetadataCount /* 7 */:
            case 8:
            case 10:
            case 12:
            case 15:
            case 16:
                objArr[1] = "com/intellij/util/containers/UnmodifiableHashMap";
                break;
            case 5:
                objArr[1] = "fromMap";
                break;
            case 9:
                objArr[1] = "without";
                break;
            case 11:
                objArr[1] = "with";
                break;
            case 13:
            case 14:
                objArr[1] = "withAll";
                break;
            case 17:
                objArr[1] = "keySet";
                break;
            case 18:
                objArr[1] = "values";
                break;
            default:
                objArr[1] = "empty";
                break;
        }
        switch (i) {
            case 1:
                objArr[2] = "empty";
                break;
            case 2:
            case 3:
            case 4:
                objArr[2] = "fromMap";
                break;
            case ScatterMapKt.DefaultScatterCapacity /* 6 */:
            case ScatterMapKt.ClonedMetadataCount /* 7 */:
                objArr[2] = "<init>";
                break;
            case 8:
                objArr[2] = "without";
                break;
            case 10:
                objArr[2] = "with";
                break;
            case 12:
                objArr[2] = "withAll";
                break;
            case 15:
                objArr[2] = "getFastutilHashingStrategy";
                break;
            case 16:
                objArr[2] = "putAll";
                break;
        }
        String str2 = String.format(str, objArr);
        switch (i) {
            case 1:
            case 2:
            case 3:
            case 4:
            case ScatterMapKt.DefaultScatterCapacity /* 6 */:
            case ScatterMapKt.ClonedMetadataCount /* 7 */:
            case 8:
            case 10:
            case 12:
            case 15:
            case 16:
                throw new IllegalArgumentException(str2);
            case 5:
            case 9:
            case 11:
            case 13:
            case 14:
            default:
                throw new IllegalStateException(str2);
        }
    }

    private UnmodifiableHashMap(HashingStrategy<K> hashingStrategy, Object[] objArr, K k, V v, K k2, V v2, K k3, V v3) {
        if (hashingStrategy == null) {
            $$$reportNull$$$0(6);
        }
        if (objArr == null) {
            $$$reportNull$$$0(7);
        }
        this.strategy = hashingStrategy;
        this.data = objArr;
        this.k1 = k;
        this.k2 = k2;
        this.k3 = k3;
        this.v1 = v;
        this.v2 = v2;
        this.v3 = v3;
        this.size = (objArr.length / 4) + (k == null ? 0 : k2 == null ? 1 : k3 == null ? 2 : 3);
    }

    public static /* synthetic */ void a(HashingStrategy hashingStrategy, Object[] objArr, Object obj, Object obj2) {
        Objects.requireNonNull(obj);
        insert(hashingStrategy, objArr, obj, obj2);
    }

    public static /* synthetic */ void b(StringBuilder sb, Object obj, Object obj2) {
        if (sb.length() > 1) {
            sb.append(", ");
        }
        sb.append(obj);
        sb.append('=');
        sb.append(obj2);
    }

    public static <K, V> UnmodifiableHashMap<K, V> empty(HashingStrategy<K> hashingStrategy) {
        if (hashingStrategy == null) {
            $$$reportNull$$$0(1);
        }
        return hashingStrategy == HashingStrategy.canonical() ? empty() : new UnmodifiableHashMap<>(hashingStrategy, ArrayUtilRt.EMPTY_OBJECT_ARRAY, null, null, null, null, null, null);
    }

    public static <K, V> UnmodifiableHashMap<K, V> fromMap(final HashingStrategy<K> hashingStrategy, Map<? extends K, ? extends V> map) {
        V v;
        K k;
        V v2;
        K k2;
        Object value;
        if (hashingStrategy == null) {
            $$$reportNull$$$0(3);
        }
        if (map == null) {
            $$$reportNull$$$0(4);
        }
        if (map instanceof UnmodifiableHashMap) {
            UnmodifiableHashMap<K, V> unmodifiableHashMap = (UnmodifiableHashMap) map;
            if (((UnmodifiableHashMap) unmodifiableHashMap).strategy == hashingStrategy) {
                return unmodifiableHashMap;
            }
        }
        if (map.isEmpty()) {
            return empty(hashingStrategy);
        }
        if (map.size() > 3) {
            final Object[] objArr = new Object[map.size() * 4];
            map.forEach(new BiConsumer() { // from class: u0f
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    UnmodifiableHashMap.a(hashingStrategy, objArr, obj, obj2);
                }
            });
            return new UnmodifiableHashMap<>(hashingStrategy, objArr, null, null, null, null, null, null);
        }
        Iterator<Map.Entry<? extends K, ? extends V>> it = map.entrySet().iterator();
        K k3 = null;
        if (it.hasNext()) {
            Map.Entry<? extends K, ? extends V> next = it.next();
            K key = next.getKey();
            V value2 = next.getValue();
            if (it.hasNext()) {
                Map.Entry<? extends K, ? extends V> next2 = it.next();
                K key2 = next2.getKey();
                V value3 = next2.getValue();
                if (it.hasNext()) {
                    Map.Entry<? extends K, ? extends V> next3 = it.next();
                    K key3 = next3.getKey();
                    v2 = value3;
                    k = key2;
                    value = next3.getValue();
                    k2 = key3;
                } else {
                    v2 = value3;
                    k = key2;
                    k2 = null;
                }
                k3 = key;
                v = value2;
            } else {
                k = null;
                v2 = null;
                k2 = null;
            }
            value = k2;
            k3 = key;
            v = value2;
        } else {
            v = null;
            k = null;
            v2 = null;
            k2 = null;
            value = null;
        }
        return new UnmodifiableHashMap<>(hashingStrategy, ArrayUtilRt.EMPTY_OBJECT_ARRAY, k3, v, k, v2, k2, value);
    }

    private static <K> Hash.Strategy<K> getFastutilHashingStrategy(final HashingStrategy<K> hashingStrategy) {
        if (hashingStrategy == null) {
            $$$reportNull$$$0(15);
        }
        return new Hash.Strategy<K>() { // from class: com.intellij.util.containers.UnmodifiableHashMap.1
            public boolean equals(K k, K k2) {
                if (k != k2) {
                    return (k == null || k2 == null || !hashingStrategy.equals(k, k2)) ? false : true;
                }
                return true;
            }

            public int hashCode(K k) {
                if (k == null) {
                    return 0;
                }
                return hashingStrategy.hashCode(k);
            }
        };
    }

    private static <K> void insert(HashingStrategy<K> hashingStrategy, Object[] objArr, K k, Object obj) {
        int i = ~tablePos(hashingStrategy, objArr, k);
        objArr[i] = k;
        objArr[i + 1] = obj;
    }

    /* JADX WARN: Multi-variable type inference failed */
    private static <K> int tablePos(HashingStrategy<K> hashingStrategy, Object[] objArr, K k) {
        int iFloorMod = Math.floorMod(hashingStrategy.hashCode(k), objArr.length / 2) * 2;
        while (true) {
            Object obj = objArr[iFloorMod];
            if (obj == null) {
                return ~iFloorMod;
            }
            if (hashingStrategy.equals(obj, k)) {
                return iFloorMod;
            }
            iFloorMod += 2;
            if (iFloorMod == objArr.length) {
                iFloorMod = 0;
            }
        }
    }

    @Override // java.util.Map
    @Deprecated
    public void clear() {
        throw new UnsupportedOperationException();
    }

    @Override // java.util.Map
    public boolean containsKey(Object obj) {
        if (obj == null) {
            return false;
        }
        Object[] objArr = this.data;
        if (objArr.length > 0 && tablePos(this.strategy, objArr, obj) >= 0) {
            return true;
        }
        K k = this.k1;
        if (k != null) {
            if (this.strategy.equals(k, obj)) {
                return true;
            }
            K k2 = this.k2;
            if (k2 != null) {
                if (this.strategy.equals(k2, obj)) {
                    return true;
                }
                K k3 = this.k3;
                if (k3 != null) {
                    return this.strategy.equals(k3, obj);
                }
            }
        }
        return false;
    }

    @Override // java.util.Map
    public boolean containsValue(Object obj) {
        if (this.k1 != null) {
            if (Objects.equals(this.v1, obj)) {
                return true;
            }
            if (this.k2 != null) {
                if (Objects.equals(this.v2, obj)) {
                    return true;
                }
                if (this.k3 != null && Objects.equals(this.v3, obj)) {
                    return true;
                }
            }
        }
        int i = 0;
        while (true) {
            Object[] objArr = this.data;
            if (i >= objArr.length) {
                return false;
            }
            if (objArr[i] != null && Objects.equals(objArr[i + 1], obj)) {
                return true;
            }
            i += 2;
        }
    }

    @Override // java.util.Map
    public Set<Map.Entry<K, V>> entrySet() {
        return new AbstractSet<Map.Entry<K, V>>() { // from class: com.intellij.util.containers.UnmodifiableHashMap.4
            @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.Set
            public Iterator<Map.Entry<K, V>> iterator() {
                return new MyIterator() { // from class: com.intellij.util.containers.UnmodifiableHashMap.4.1
                    {
                        UnmodifiableHashMap unmodifiableHashMap = UnmodifiableHashMap.this;
                    }

                    @Override // com.intellij.util.containers.UnmodifiableHashMap.MyIterator
                    public Map.Entry<K, V> fieldElement(int i) {
                        if (i == 0) {
                            return new AbstractMap.SimpleImmutableEntry(UnmodifiableHashMap.this.k1, UnmodifiableHashMap.this.v1);
                        }
                        return i == 1 ? new AbstractMap.SimpleImmutableEntry(UnmodifiableHashMap.this.k2, UnmodifiableHashMap.this.v2) : new AbstractMap.SimpleImmutableEntry(UnmodifiableHashMap.this.k3, UnmodifiableHashMap.this.v3);
                    }

                    @Override // com.intellij.util.containers.UnmodifiableHashMap.MyIterator
                    public Map.Entry<K, V> tableElement(int i) {
                        return new AbstractMap.SimpleImmutableEntry(UnmodifiableHashMap.this.data[i], UnmodifiableHashMap.this.data[i + 1]);
                    }
                };
            }

            @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
            public int size() {
                return UnmodifiableHashMap.this.size();
            }
        };
    }

    @Override // java.util.Map
    public boolean equals(Object obj) {
        if (!(obj instanceof Map)) {
            return false;
        }
        Map map = (Map) obj;
        if (size() != map.size()) {
            return false;
        }
        K k = this.k1;
        if (k != null) {
            if (!Objects.equals(map.get(k), this.v1)) {
                return false;
            }
            K k2 = this.k2;
            if (k2 != null) {
                if (!Objects.equals(map.get(k2), this.v2)) {
                    return false;
                }
                K k3 = this.k3;
                if (k3 != null && !Objects.equals(map.get(k3), this.v3)) {
                    return false;
                }
            }
        }
        int i = 0;
        while (true) {
            Object[] objArr = this.data;
            if (i >= objArr.length) {
                return true;
            }
            Object obj2 = objArr[i];
            if (obj2 != null && !Objects.equals(map.get(obj2), this.data[i + 1])) {
                return false;
            }
            i += 2;
        }
    }

    @Override // java.util.Map
    public void forEach(BiConsumer<? super K, ? super V> biConsumer) {
        if (this.k1 != null) {
            if (this.k2 != null) {
                K k = this.k3;
                if (k != null) {
                    biConsumer.accept(k, this.v3);
                }
                biConsumer.accept(this.k2, this.v2);
            }
            biConsumer.accept(this.k1, this.v1);
        }
        int i = 0;
        while (true) {
            Object[] objArr = this.data;
            if (i >= objArr.length) {
                return;
            }
            Object obj = objArr[i];
            if (obj != null) {
                biConsumer.accept(obj, objArr[i + 1]);
            }
            i += 2;
        }
    }

    @Override // java.util.Map
    public V get(Object obj) {
        return getOrDefault(obj, null);
    }

    @Override // java.util.Map
    public V getOrDefault(Object obj, V v) {
        int iTablePos;
        if (obj != null) {
            K k = this.k1;
            if (k != null) {
                if (this.strategy.equals(k, obj)) {
                    return this.v1;
                }
                K k2 = this.k2;
                if (k2 != null) {
                    if (this.strategy.equals(k2, obj)) {
                        return this.v2;
                    }
                    K k3 = this.k3;
                    if (k3 != null && this.strategy.equals(k3, obj)) {
                        return this.v3;
                    }
                }
            }
            Object[] objArr = this.data;
            if (objArr.length != 0 && (iTablePos = tablePos(this.strategy, objArr, obj)) >= 0) {
                return (V) this.data[iTablePos + 1];
            }
        }
        return v;
    }

    @Override // java.util.Map
    public int hashCode() {
        int iHashCode;
        K k = this.k1;
        int i = 0;
        if (k != null) {
            iHashCode = this.strategy.hashCode(k) ^ Objects.hashCode(this.v1);
            K k2 = this.k2;
            if (k2 != null) {
                iHashCode += this.strategy.hashCode(k2) ^ Objects.hashCode(this.v2);
                K k3 = this.k3;
                if (k3 != null) {
                    iHashCode += this.strategy.hashCode(k3) ^ Objects.hashCode(this.v3);
                }
            }
        } else {
            iHashCode = 0;
        }
        while (true) {
            Object[] objArr = this.data;
            if (i >= objArr.length) {
                return iHashCode;
            }
            Object obj = objArr[i];
            if (obj != null) {
                iHashCode += this.strategy.hashCode((K) obj) ^ Objects.hashCode(this.data[i + 1]);
            }
            i += 2;
        }
    }

    @Override // java.util.Map
    public boolean isEmpty() {
        return this.size == 0;
    }

    @Override // java.util.Map
    public Set<K> keySet() {
        if (this.keySet == null) {
            this.keySet = new AbstractSet<K>() { // from class: com.intellij.util.containers.UnmodifiableHashMap.2
                @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
                public boolean contains(Object obj) {
                    return UnmodifiableHashMap.this.containsKey(obj);
                }

                @Override // java.lang.Iterable
                public void forEach(Consumer<? super K> consumer) {
                    if (UnmodifiableHashMap.this.k1 != null) {
                        if (UnmodifiableHashMap.this.k2 != null) {
                            if (UnmodifiableHashMap.this.k3 != null) {
                                consumer.accept((Object) UnmodifiableHashMap.this.k3);
                            }
                            consumer.accept((Object) UnmodifiableHashMap.this.k2);
                        }
                        consumer.accept((Object) UnmodifiableHashMap.this.k1);
                    }
                    for (int i = 0; i < UnmodifiableHashMap.this.data.length; i += 2) {
                        if (UnmodifiableHashMap.this.data[i] != null) {
                            consumer.accept(UnmodifiableHashMap.this.data[i]);
                        }
                    }
                }

                @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.Set
                public Iterator<K> iterator() {
                    return new MyIterator() { // from class: com.intellij.util.containers.UnmodifiableHashMap.2.1
                        {
                            UnmodifiableHashMap unmodifiableHashMap = UnmodifiableHashMap.this;
                        }

                        @Override // com.intellij.util.containers.UnmodifiableHashMap.MyIterator
                        public K fieldElement(int i) {
                            AnonymousClass2 anonymousClass2 = AnonymousClass2.this;
                            if (i == 0) {
                                return (K) UnmodifiableHashMap.this.k1;
                            }
                            UnmodifiableHashMap unmodifiableHashMap = UnmodifiableHashMap.this;
                            return i == 1 ? (K) unmodifiableHashMap.k2 : (K) unmodifiableHashMap.k3;
                        }

                        @Override // com.intellij.util.containers.UnmodifiableHashMap.MyIterator
                        public K tableElement(int i) {
                            return (K) UnmodifiableHashMap.this.data[i];
                        }
                    };
                }

                @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
                public int size() {
                    return UnmodifiableHashMap.this.size();
                }
            };
        }
        Set<K> set = this.keySet;
        if (set == null) {
            $$$reportNull$$$0(17);
        }
        return set;
    }

    @Override // java.util.Map
    @Deprecated
    public V put(K k, V v) {
        throw new UnsupportedOperationException();
    }

    @Override // java.util.Map
    @Deprecated
    public void putAll(Map<? extends K, ? extends V> map) {
        if (map == null) {
            $$$reportNull$$$0(16);
        }
        throw new UnsupportedOperationException();
    }

    @Override // java.util.Map
    @Deprecated
    public V remove(Object obj) {
        throw new UnsupportedOperationException();
    }

    @Override // java.util.Map
    public int size() {
        return this.size;
    }

    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append('{');
        forEach(new BiConsumer() { // from class: t0f
            @Override // java.util.function.BiConsumer
            public final void accept(Object obj, Object obj2) {
                UnmodifiableHashMap.b(sb, obj, obj2);
            }
        });
        sb.append('}');
        return sb.toString();
    }

    @Override // java.util.Map
    public Collection<V> values() {
        if (this.values == null) {
            this.values = new AbstractCollection<V>() { // from class: com.intellij.util.containers.UnmodifiableHashMap.3
                @Override // java.util.AbstractCollection, java.util.Collection
                public boolean contains(Object obj) {
                    return UnmodifiableHashMap.this.containsValue(obj);
                }

                @Override // java.lang.Iterable
                public void forEach(Consumer<? super V> consumer) {
                    if (UnmodifiableHashMap.this.k1 != null) {
                        if (UnmodifiableHashMap.this.k2 != null) {
                            if (UnmodifiableHashMap.this.k3 != null) {
                                consumer.accept((Object) UnmodifiableHashMap.this.v3);
                            }
                            consumer.accept((Object) UnmodifiableHashMap.this.v2);
                        }
                        consumer.accept((Object) UnmodifiableHashMap.this.v1);
                    }
                    for (int i = 0; i < UnmodifiableHashMap.this.data.length; i += 2) {
                        if (UnmodifiableHashMap.this.data[i] != null) {
                            consumer.accept(UnmodifiableHashMap.this.data[i + 1]);
                        }
                    }
                }

                @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable
                public Iterator<V> iterator() {
                    return new MyIterator() { // from class: com.intellij.util.containers.UnmodifiableHashMap.3.1
                        {
                            UnmodifiableHashMap unmodifiableHashMap = UnmodifiableHashMap.this;
                        }

                        @Override // com.intellij.util.containers.UnmodifiableHashMap.MyIterator
                        public V fieldElement(int i) {
                            AnonymousClass3 anonymousClass3 = AnonymousClass3.this;
                            if (i == 0) {
                                return (V) UnmodifiableHashMap.this.v1;
                            }
                            UnmodifiableHashMap unmodifiableHashMap = UnmodifiableHashMap.this;
                            return i == 1 ? (V) unmodifiableHashMap.v2 : (V) unmodifiableHashMap.v3;
                        }

                        @Override // com.intellij.util.containers.UnmodifiableHashMap.MyIterator
                        public V tableElement(int i) {
                            return (V) UnmodifiableHashMap.this.data[i + 1];
                        }
                    };
                }

                @Override // java.util.AbstractCollection, java.util.Collection
                public int size() {
                    return UnmodifiableHashMap.this.size();
                }
            };
        }
        Collection<V> collection = this.values;
        if (collection == null) {
            $$$reportNull$$$0(18);
        }
        return collection;
    }

    public UnmodifiableHashMap<K, V> with(K k, V v) {
        if (k == null) {
            $$$reportNull$$$0(10);
        }
        Object[] objArr = this.data;
        int iTablePos = objArr.length == 0 ? -1 : tablePos(this.strategy, objArr, k);
        if (iTablePos >= 0) {
            Object[] objArr2 = this.data;
            int i = iTablePos + 1;
            if (objArr2[i] != v) {
                Object[] objArr3 = (Object[]) objArr2.clone();
                objArr3[i] = v;
                return new UnmodifiableHashMap<>(this.strategy, objArr3, this.k1, this.v1, this.k2, this.v2, this.k3, this.v3);
            }
        } else {
            K k2 = this.k1;
            HashingStrategy<K> hashingStrategy = this.strategy;
            if (k2 == null) {
                return new UnmodifiableHashMap<>(hashingStrategy, this.data, k, v, null, null, null, null);
            }
            if (!hashingStrategy.equals(k2, k)) {
                K k3 = this.k2;
                HashingStrategy<K> hashingStrategy2 = this.strategy;
                if (k3 == null) {
                    return new UnmodifiableHashMap<>(hashingStrategy2, this.data, this.k1, this.v1, k, v, null, null);
                }
                if (!hashingStrategy2.equals(k3, k)) {
                    K k4 = this.k3;
                    HashingStrategy<K> hashingStrategy3 = this.strategy;
                    if (k4 == null) {
                        return new UnmodifiableHashMap<>(hashingStrategy3, this.data, this.k1, this.v1, this.k2, this.v2, k, v);
                    }
                    if (!hashingStrategy3.equals(k4, k)) {
                        Object[] objArr4 = new Object[(this.size + 1) * 4];
                        int i2 = 0;
                        while (true) {
                            Object[] objArr5 = this.data;
                            if (i2 >= objArr5.length) {
                                insert(this.strategy, objArr4, this.k1, this.v1);
                                insert(this.strategy, objArr4, this.k2, this.v2);
                                insert(this.strategy, objArr4, this.k3, this.v3);
                                insert(this.strategy, objArr4, k, v);
                                return new UnmodifiableHashMap<>(this.strategy, objArr4, null, null, null, null, null, null);
                            }
                            Object obj = objArr5[i2];
                            if (obj != null) {
                                insert(this.strategy, objArr4, obj, objArr5[i2 + 1]);
                            }
                            i2 += 2;
                        }
                    } else if (v != this.v3) {
                        return new UnmodifiableHashMap<>(this.strategy, this.data, this.k1, this.v1, this.k2, this.v2, this.k3, v);
                    }
                } else if (v != this.v2) {
                    return new UnmodifiableHashMap<>(this.strategy, this.data, this.k1, this.v1, this.k2, v, this.k3, this.v3);
                }
            } else if (v != this.v1) {
                return new UnmodifiableHashMap<>(this.strategy, this.data, this.k1, v, this.k2, this.v2, this.k3, this.v3);
            }
        }
        return this;
    }

    public UnmodifiableHashMap<K, V> withAll(Map<? extends K, ? extends V> map) {
        if (map == null) {
            $$$reportNull$$$0(12);
        }
        if (isEmpty()) {
            return fromMap(this.strategy, map);
        }
        int size = map.size();
        if (size == 0) {
            return this;
        }
        if (size == 1) {
            Map.Entry<? extends K, ? extends V> next = map.entrySet().iterator().next();
            UnmodifiableHashMap<K, V> unmodifiableHashMapWith = with(next.getKey(), next.getValue());
            if (unmodifiableHashMapWith == null) {
                $$$reportNull$$$0(14);
            }
            return unmodifiableHashMapWith;
        }
        HashingStrategy<K> hashingStrategy = this.strategy;
        HashingStrategy<K> hashingStrategyCanonical = HashingStrategy.canonical();
        int i = this.size;
        Object2ObjectOpenHashMap object2ObjectOpenHashMap = hashingStrategy == hashingStrategyCanonical ? new Object2ObjectOpenHashMap(size + i) : new Object2ObjectOpenCustomHashMap(size + i, getFastutilHashingStrategy(this.strategy));
        object2ObjectOpenHashMap.putAll(this);
        object2ObjectOpenHashMap.putAll(map);
        return fromMap(this.strategy, object2ObjectOpenHashMap);
    }

    public UnmodifiableHashMap<K, V> without(K k) {
        Object obj;
        if (k == null) {
            $$$reportNull$$$0(8);
        }
        Object[] objArr = this.data;
        int iTablePos = objArr.length == 0 ? -1 : tablePos(this.strategy, objArr, k);
        if (iTablePos < 0) {
            K k2 = this.k1;
            if (k2 != null) {
                if (this.strategy.equals(k2, k)) {
                    return new UnmodifiableHashMap<>(this.strategy, this.data, this.k2, this.v2, this.k3, this.v3, null, null);
                }
                K k3 = this.k2;
                if (k3 != null) {
                    if (this.strategy.equals(k3, k)) {
                        return new UnmodifiableHashMap<>(this.strategy, this.data, this.k1, this.v1, this.k3, this.v3, null, null);
                    }
                    K k4 = this.k3;
                    if (k4 != null && this.strategy.equals(k4, k)) {
                        return new UnmodifiableHashMap<>(this.strategy, this.data, this.k1, this.v1, this.k2, this.v2, null, null);
                    }
                }
            }
            return this;
        }
        Object[] objArr2 = new Object[(this.size - 1) * 4];
        int i = 0;
        while (true) {
            Object[] objArr3 = this.data;
            if (i >= objArr3.length) {
                break;
            }
            if (i != iTablePos && (obj = objArr3[i]) != null) {
                insert(this.strategy, objArr2, obj, objArr3[i + 1]);
            }
            i += 2;
        }
        K k5 = this.k1;
        if (k5 != null) {
            insert(this.strategy, objArr2, k5, this.v1);
            K k6 = this.k2;
            if (k6 != null) {
                insert(this.strategy, objArr2, k6, this.v2);
                K k7 = this.k3;
                if (k7 != null) {
                    insert(this.strategy, objArr2, k7, this.v3);
                }
            }
        }
        return new UnmodifiableHashMap<>(this.strategy, objArr2, null, null, null, null, null, null);
    }

    public static <K, V> UnmodifiableHashMap<K, V> empty() {
        UnmodifiableHashMap<K, V> unmodifiableHashMap = (UnmodifiableHashMap<K, V>) EMPTY;
        if (unmodifiableHashMap == null) {
            $$$reportNull$$$0(0);
        }
        return unmodifiableHashMap;
    }
}
