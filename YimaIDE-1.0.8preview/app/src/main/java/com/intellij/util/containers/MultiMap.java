package com.intellij.util.containers;

import androidx.collection.ScatterMapKt;
import com.intellij.util.SmartList;
import java.io.Serializable;
import java.util.AbstractCollection;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
public class MultiMap<K, V> implements Serializable {
    private static final MultiMap<?, ?> EMPTY = new EmptyMap(null);
    private static final long serialVersionUID = -2632269270151455493L;
    protected final Map<K, Collection<V>> myMap;
    private Collection<V> values;

    /* JADX INFO: renamed from: com.intellij.util.containers.MultiMap$1, reason: invalid class name */
    public class AnonymousClass1 extends AbstractCollection<V> {
        final /* synthetic */ MultiMap this$0;

        @Override // java.util.AbstractCollection, java.util.Collection
        public boolean contains(Object obj) {
            Iterator<Collection<V>> it = this.this$0.myMap.values().iterator();
            while (it.hasNext()) {
                if (it.next().contains(obj)) {
                    return true;
                }
            }
            return false;
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable
        public Iterator<V> iterator() {
            return new Iterator<V>() { // from class: com.intellij.util.containers.MultiMap.1.1
                private Iterator<V> itr = Collections.emptyIterator();
                private final Iterator<Collection<V>> mapIterator;

                {
                    this.mapIterator = AnonymousClass1.this.this$0.myMap.values().iterator();
                }

                @Override // java.util.Iterator
                public boolean hasNext() {
                    while (!this.itr.hasNext()) {
                        if (!this.mapIterator.hasNext()) {
                            return false;
                        }
                        this.itr = this.mapIterator.next().iterator();
                    }
                    return true;
                }

                @Override // java.util.Iterator
                public V next() {
                    while (!this.itr.hasNext()) {
                        if (!this.mapIterator.hasNext()) {
                            z0e.a();
                            return null;
                        }
                        this.itr = this.mapIterator.next().iterator();
                    }
                    return this.itr.next();
                }

                @Override // java.util.Iterator
                public void remove() {
                    this.itr.remove();
                }
            };
        }

        @Override // java.util.AbstractCollection, java.util.Collection
        public int size() {
            Iterator<Collection<V>> it = this.this$0.myMap.values().iterator();
            int size = 0;
            while (it.hasNext()) {
                size += it.next().size();
            }
            return size;
        }
    }

    /* JADX WARN: Code duplicated, block: B:8:0x0010  */
    private static /* synthetic */ void $$$reportNull$$$0(int i) {
        String str;
        int i2;
        if (i != 2 && i != 4 && i != 17) {
            switch (i) {
                case ScatterMapKt.ClonedMetadataCount /* 7 */:
                case 8:
                case 9:
                case 10:
                case 11:
                case 12:
                case 13:
                case 14:
                case 15:
                    str = "@NotNull method %s.%s must not return null";
                    break;
                default:
                    str = "Argument for @NotNull parameter '%s' of %s.%s must not be null";
                    break;
            }
        } else {
            str = "@NotNull method %s.%s must not return null";
        }
        if (i != 2 && i != 4 && i != 17) {
            switch (i) {
                case ScatterMapKt.ClonedMetadataCount /* 7 */:
                case 8:
                case 9:
                case 10:
                case 11:
                case 12:
                case 13:
                case 14:
                case 15:
                    i2 = 2;
                    break;
                default:
                    i2 = 3;
                    break;
            }
        } else {
            i2 = 2;
        }
        Object[] objArr = new Object[i2];
        switch (i) {
            case 1:
                objArr[0] = "toCopy";
                break;
            case 2:
            case 4:
            case ScatterMapKt.ClonedMetadataCount /* 7 */:
            case 8:
            case 9:
            case 10:
            case 11:
            case 12:
            case 13:
            case 14:
            case 15:
            case 17:
                objArr[0] = "com/intellij/util/containers/MultiMap";
                break;
            case 3:
            case 5:
                objArr[0] = "from";
                break;
            case ScatterMapKt.DefaultScatterCapacity /* 6 */:
                objArr[0] = "values";
                break;
            case 16:
            default:
                objArr[0] = "map";
                break;
        }
        if (i == 2) {
            objArr[1] = "createEmptyCollection";
        } else if (i == 4) {
            objArr[1] = "toHashMap";
        } else if (i != 17) {
            switch (i) {
                case ScatterMapKt.ClonedMetadataCount /* 7 */:
                    objArr[1] = "entrySet";
                    break;
                case 8:
                case 9:
                    objArr[1] = "freezeValues";
                    break;
                case 10:
                    objArr[1] = "get";
                    break;
                case 11:
                    objArr[1] = "getOrPut";
                    break;
                case 12:
                    objArr[1] = "getModifiable";
                    break;
                case 13:
                    objArr[1] = "keySet";
                    break;
                case 14:
                case 15:
                    objArr[1] = "values";
                    break;
                default:
                    objArr[1] = "com/intellij/util/containers/MultiMap";
                    break;
            }
        } else {
            objArr[1] = "empty";
        }
        switch (i) {
            case 2:
            case 4:
            case ScatterMapKt.ClonedMetadataCount /* 7 */:
            case 8:
            case 9:
            case 10:
            case 11:
            case 12:
            case 13:
            case 14:
            case 15:
            case 17:
                break;
            case 3:
            case 5:
                objArr[2] = "putAllValues";
                break;
            case ScatterMapKt.DefaultScatterCapacity /* 6 */:
                objArr[2] = "putValues";
                break;
            case 16:
                objArr[2] = "createSet";
                break;
            default:
                objArr[2] = "<init>";
                break;
        }
        String str2 = String.format(str, objArr);
        if (i != 2 && i != 4 && i != 17) {
            switch (i) {
                case ScatterMapKt.ClonedMetadataCount /* 7 */:
                case 8:
                case 9:
                case 10:
                case 11:
                case 12:
                case 13:
                case 14:
                case 15:
                    break;
                default:
                    throw new IllegalArgumentException(str2);
            }
        }
        throw new IllegalStateException(str2);
    }

    public MultiMap(Map<K, Collection<V>> map) {
        if (map == null) {
            $$$reportNull$$$0(0);
        }
        this.myMap = map;
    }

    public static <K, V> MultiMap<K, V> create() {
        return new MultiMap<>();
    }

    public static <K, V> MultiMap<K, V> createConcurrent() {
        return new MultiMap<K, V>(new ConcurrentHashMap()) { // from class: com.intellij.util.containers.MultiMap.4
            private static /* synthetic */ void $$$reportNull$$$0(int i) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/intellij/util/containers/MultiMap$4", "createCollection"));
            }

            @Override // com.intellij.util.containers.MultiMap
            public Collection<V> createCollection() {
                List listCreateLockFreeCopyOnWriteList = ContainerUtil.createLockFreeCopyOnWriteList();
                if (listCreateLockFreeCopyOnWriteList == null) {
                    $$$reportNull$$$0(0);
                }
                return listCreateLockFreeCopyOnWriteList;
            }
        };
    }

    public static <K, V> MultiMap<K, V> createLinked() {
        return new MultiMap<>(new LinkedHashMap());
    }

    public static <K, V> MultiMap<K, V> createLinkedSet() {
        return new MultiMap<K, V>(new LinkedHashMap()) { // from class: com.intellij.util.containers.MultiMap.2
            private static /* synthetic */ void $$$reportNull$$$0(int i) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/intellij/util/containers/MultiMap$2", "createEmptyCollection"));
            }

            @Override // com.intellij.util.containers.MultiMap
            public Collection<V> createCollection() {
                return new LinkedHashSet();
            }

            @Override // com.intellij.util.containers.MultiMap
            public Collection<V> createEmptyCollection() {
                Set set = Collections.EMPTY_SET;
                if (set == null) {
                    $$$reportNull$$$0(0);
                }
                return set;
            }
        };
    }

    public static <K, V> MultiMap<K, V> createSet() {
        return new MultiMap<K, V>() { // from class: com.intellij.util.containers.MultiMap.6
            private static /* synthetic */ void $$$reportNull$$$0(int i) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/intellij/util/containers/MultiMap$6", "createEmptyCollection"));
            }

            @Override // com.intellij.util.containers.MultiMap
            public Collection<V> createCollection() {
                return new SmartHashSet();
            }

            @Override // com.intellij.util.containers.MultiMap
            public Collection<V> createEmptyCollection() {
                Set set = Collections.EMPTY_SET;
                if (set == null) {
                    $$$reportNull$$$0(0);
                }
                return set;
            }
        };
    }

    public final void clear() {
        this.myMap.clear();
    }

    public Collection<V> createCollection() {
        return new SmartList();
    }

    public Collection<V> createEmptyCollection() {
        List list = Collections.EMPTY_LIST;
        if (list == null) {
            $$$reportNull$$$0(2);
        }
        return list;
    }

    public final Set<Map.Entry<K, Collection<V>>> entrySet() {
        Set<Map.Entry<K, Collection<V>>> setEntrySet = this.myMap.entrySet();
        if (setEntrySet == null) {
            $$$reportNull$$$0(7);
        }
        return setEntrySet;
    }

    public final boolean equals(Object obj) {
        if (this != obj) {
            return (obj instanceof MultiMap) && this.myMap.equals(((MultiMap) obj).myMap);
        }
        return true;
    }

    public final Collection<V> get(K k) {
        Collection<V> collectionCreateEmptyCollection = this.myMap.get(k);
        if (collectionCreateEmptyCollection == null) {
            collectionCreateEmptyCollection = createEmptyCollection();
        }
        if (collectionCreateEmptyCollection == null) {
            $$$reportNull$$$0(10);
        }
        return collectionCreateEmptyCollection;
    }

    public final Collection<V> getModifiable(K k) {
        Collection<V> collectionComputeIfAbsent = this.myMap.computeIfAbsent(k, new Function() { // from class: f8a
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return this.b.createCollection();
            }
        });
        if (collectionComputeIfAbsent == null) {
            $$$reportNull$$$0(12);
        }
        return collectionComputeIfAbsent;
    }

    public final int hashCode() {
        return this.myMap.hashCode();
    }

    public final Set<K> keySet() {
        Set<K> setKeySet = this.myMap.keySet();
        if (setKeySet == null) {
            $$$reportNull$$$0(13);
        }
        return setKeySet;
    }

    public final void putValue(K k, V v) {
        getModifiable(k).add(v);
    }

    public final void putValues(K k, Collection<? extends V> collection) {
        if (collection == null) {
            $$$reportNull$$$0(6);
        }
        getModifiable(k).addAll(collection);
    }

    public boolean remove(K k, V v) {
        Collection<V> collection = this.myMap.get(k);
        if (collection == null) {
            return false;
        }
        boolean zRemove = collection.remove(v);
        if (collection.isEmpty()) {
            this.myMap.remove(k);
        }
        return zRemove;
    }

    public final int size() {
        return this.myMap.size();
    }

    public final Map<K, Collection<V>> toHashMap() {
        Map<K, Collection<V>> map = this.myMap;
        if (!(map instanceof HashMap)) {
            return new HashMap(this.myMap);
        }
        Map<K, Collection<V>> map2 = (Map) ((HashMap) map).clone();
        if (map2 == null) {
            $$$reportNull$$$0(4);
        }
        return map2;
    }

    public final String toString() {
        return this.myMap.toString();
    }

    public static final class EmptyMap extends MultiMap<Object, Object> {
        private EmptyMap() {
            super(Collections.EMPTY_MAP);
        }

        public /* synthetic */ EmptyMap(AnonymousClass1 anonymousClass1) {
            this();
        }
    }

    public MultiMap() {
        this.myMap = new HashMap();
    }

    public final Collection<V> remove(K k) {
        return this.myMap.remove(k);
    }
}
