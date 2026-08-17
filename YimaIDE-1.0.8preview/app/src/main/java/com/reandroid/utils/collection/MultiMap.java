package com.reandroid.utils.collection;

import defpackage.aa0;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class MultiMap<K, V> {
    private Comparator<? super V> favouriteObjectsSorter;
    private int initialSize;
    private final Object mLock = new Object();
    private Map<K, Object> map;

    public static class EntryList<T> extends ArrayCollection<T> {
        public EntryList(Object obj, Object obj2) {
            super(new Object[]{obj, obj2});
        }
    }

    private Object combine(Object obj, Object obj2) {
        if (obj == obj2) {
            return obj;
        }
        if (obj == null) {
            return obj2;
        }
        if (obj2 == null) {
            return obj;
        }
        boolean z = false;
        boolean z2 = obj.getClass() == EntryList.class;
        boolean z3 = obj2.getClass() == EntryList.class;
        if (!z2 && !z3) {
            List<Object> entryList = new EntryList<>(obj, obj2);
            sortFavourites(entryList);
            return entryList;
        }
        if (z2 && !z3) {
            EntryList entryList2 = (EntryList) obj;
            if (!entryList2.containsExact(obj2)) {
                entryList2.add(obj2);
                sortFavourites(entryList2);
            }
            return entryList2;
        }
        if (!z2) {
            EntryList entryList3 = (EntryList) obj2;
            if (!entryList3.containsExact(obj)) {
                entryList3.add(obj);
                sortFavourites(entryList3);
            }
            return entryList3;
        }
        EntryList entryList4 = (EntryList) obj;
        for (Object obj3 : (EntryList) obj2) {
            if (!entryList4.containsExact(obj3)) {
                entryList4.add(obj3);
                z = true;
            }
        }
        if (z) {
            sortFavourites(entryList4);
        }
        return entryList4;
    }

    /* JADX WARN: Multi-variable type inference failed */
    private V getFromEntryList(K k, EntryList<?> entryList, Predicate<? super V> predicate) {
        if (!entryList.isEmpty()) {
            return predicate == 0 ? (V) entryList.getFirst() : (V) CollectionUtil.getFirst(entryList.iterator((Predicate<? super Object>) predicate));
        }
        this.map.remove(k);
        return null;
    }

    private Map<K, Object> getInitializedMap() {
        Map<K, Object> map = this.map;
        if (map == null) {
            int i = this.initialSize;
            map = i == 0 ? new HashMap<>() : new HashMap(i);
            this.map = map;
        }
        return map;
    }

    private void processDuplicateValues(Comparator<? super V> comparator, Consumer<List<V>> consumer, EntryList<V> entryList) {
        if (entryList.size() < 2) {
            return;
        }
        ArrayCollection arrayCollection = new ArrayCollection(entryList);
        int size = arrayCollection.size();
        if (!sortFavourites(arrayCollection)) {
            arrayCollection.sort(comparator);
        }
        aa0 aa0Var = (Object) arrayCollection.get(0);
        if (comparator.compare(aa0Var, (Object) arrayCollection.get(size - 1)) == 0) {
            consumer.accept(arrayCollection);
            return;
        }
        ArrayCollection arrayCollection2 = new ArrayCollection(size);
        arrayCollection2.add(aa0Var);
        int i = 1;
        while (i < size) {
            aa0 aa0Var2 = (Object) arrayCollection.get(i);
            if (comparator.compare(aa0Var, aa0Var2) != 0) {
                if (arrayCollection2.size() > 1) {
                    consumer.accept(arrayCollection2);
                }
                arrayCollection2.clearTemporarily();
            }
            arrayCollection2.add(aa0Var2);
            i++;
            aa0Var = aa0Var2;
        }
        if (arrayCollection2.size() > 1) {
            consumer.accept(arrayCollection2);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    private void putUnlocked(K k, V v) {
        if (k == null || v == null) {
            return;
        }
        Map<K, Object> initializedMap = getInitializedMap();
        Object obj = initializedMap.get(k);
        if (obj == null) {
            initializedMap.put(k, v);
            return;
        }
        if (obj.getClass() != EntryList.class) {
            initializedMap.put(k, combine(initializedMap.remove(k), v));
            return;
        }
        EntryList entryList = (EntryList) obj;
        if (entryList.containsExact(v)) {
            return;
        }
        entryList.add(v);
        sortFavourites(entryList);
    }

    private boolean sortFavourites(List<Object> list) {
        Comparator<? super V> comparator = this.favouriteObjectsSorter;
        if (comparator == null || list == null || list.size() < 2) {
            return false;
        }
        list.sort(comparator);
        return true;
    }

    public void clear() {
        synchronized (this.mLock) {
            try {
                Map<K, Object> map = this.map;
                if (map != null) {
                    if (this.initialSize == 0) {
                        this.initialSize = map.size();
                    }
                    this.map = null;
                    map.clear();
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public boolean containsKey(Object obj) {
        synchronized (this.mLock) {
            try {
                if (obj == null) {
                    return false;
                }
                Map<K, Object> map = this.map;
                if (map == null) {
                    return false;
                }
                return map.containsKey(obj);
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public boolean containsValue(Object obj, Predicate<? super V> predicate) {
        synchronized (this.mLock) {
            try {
                if (obj == null) {
                    return false;
                }
                Map<K, Object> map = this.map;
                if (map == null) {
                    return false;
                }
                Object obj2 = map.get(obj);
                if (obj2 == null) {
                    return false;
                }
                if (obj2.getClass() == EntryList.class) {
                    return ((EntryList) obj2).containsIf(predicate);
                }
                return predicate.test(obj2);
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public void findDuplicates(Comparator<? super V> comparator, Consumer<List<V>> consumer) {
        synchronized (this.mLock) {
            try {
                Map<K, Object> map = this.map;
                if (map != null && !map.isEmpty()) {
                    for (Map.Entry<K, Object> entry : map.entrySet()) {
                        Object value = entry.getValue();
                        if (value != null && value.getClass() == EntryList.class) {
                            EntryList<V> entryList = (EntryList) value;
                            if (entryList.size() == 1) {
                                entry.setValue(entryList.get(0));
                            } else {
                                processDuplicateValues(comparator, consumer, entryList);
                            }
                        }
                    }
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public V get(K k, Predicate<? super V> predicate) {
        synchronized (this.mLock) {
            V v = null;
            try {
                if (k == null) {
                    return null;
                }
                Map<K, Object> map = this.map;
                if (map == null) {
                    return null;
                }
                Object obj = map.get(k);
                if (obj == null) {
                    return null;
                }
                if (obj.getClass() == EntryList.class) {
                    return getFromEntryList(k, (EntryList) obj, predicate);
                }
                if (predicate == null || predicate.test(obj)) {
                    v = (V) obj;
                }
                return v;
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public Iterator<V> getAll(K k) {
        synchronized (this.mLock) {
            try {
                if (k == null) {
                    return EmptyIterator.of();
                }
                Map<K, Object> map = this.map;
                if (map == null) {
                    return EmptyIterator.of();
                }
                Object obj = map.get(k);
                if (obj == null) {
                    return EmptyIterator.of();
                }
                if (obj.getClass() == EntryList.class) {
                    return (Iterator<V>) ((EntryList) obj).iterator();
                }
                return SingleIterator.of(obj);
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public Set<K> keySet() {
        Set<K> setKeySet;
        synchronized (this.mLock) {
            setKeySet = this.map.keySet();
        }
        return setKeySet;
    }

    public void put(K k, V v) {
        synchronized (this.mLock) {
            putUnlocked(k, v);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public void putAll(Function<? super V, K> function, Iterator<? extends V> it) {
        synchronized (this.mLock) {
            while (it.hasNext()) {
                try {
                    V next = it.next();
                    putUnlocked(function.apply(next), next);
                } catch (Throwable th) {
                    throw th;
                }
            }
        }
    }

    public V remove(Object obj, Object obj2) {
        synchronized (this.mLock) {
            try {
                if (obj == null) {
                    return null;
                }
                Map<K, Object> map = this.map;
                if (map == null) {
                    return null;
                }
                Object obj3 = map.get(obj);
                if (obj3 == null) {
                    return null;
                }
                if (obj3.getClass() != EntryList.class) {
                    if (obj3 != obj2) {
                        return null;
                    }
                    return (V) map.remove(obj);
                }
                EntryList entryList = (EntryList) obj3;
                int iIndexOfExact = entryList.indexOfExact(obj2);
                if (iIndexOfExact < 0) {
                    return null;
                }
                V v = (V) entryList.remove(iIndexOfExact);
                if (entryList.isEmpty()) {
                    map.remove(obj);
                } else if (entryList.size() == 1) {
                    v = (V) entryList.get(0);
                    map.remove(obj);
                    map.put((K) obj, v);
                }
                return v;
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public V removeIf(Object obj, Predicate<? super V> predicate) {
        synchronized (this.mLock) {
            try {
                if (obj == null) {
                    return null;
                }
                Map<K, Object> map = this.map;
                if (map == null) {
                    return null;
                }
                Object obj2 = map.get(obj);
                if (obj2 == null) {
                    return null;
                }
                if (obj2.getClass() != EntryList.class) {
                    if (!predicate.test(obj2)) {
                        return null;
                    }
                    return (V) map.remove(obj);
                }
                EntryList entryList = (EntryList) obj2;
                int iIndexOfIf = entryList.indexOfIf(predicate);
                if (iIndexOfIf < 0) {
                    return null;
                }
                V v = (V) entryList.remove(iIndexOfIf);
                if (entryList.isEmpty()) {
                    map.remove(obj);
                } else if (entryList.size() == 1) {
                    v = (V) entryList.get(0);
                    map.remove(obj);
                    map.put((K) obj, v);
                }
                return v;
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public void setFavouriteObjectsSorter(Comparator<? super V> comparator) {
        synchronized (this.mLock) {
            this.favouriteObjectsSorter = comparator;
        }
    }

    public void setInitialSize(int i) {
        synchronized (this.mLock) {
            try {
                this.initialSize = i;
                Map<K, Object> map = this.map;
                if (map != null && map.isEmpty()) {
                    this.map = null;
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public int size() {
        synchronized (this.mLock) {
            try {
                Map<K, Object> map = this.map;
                if (map == null) {
                    return 0;
                }
                return map.size();
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public String toString() {
        return "size = " + size();
    }

    public boolean updateKey(K k, K k2, V v) {
        synchronized (this.mLock) {
            try {
                if (k == null && k2 == null) {
                    return false;
                }
                Map<K, Object> initializedMap = getInitializedMap();
                initializedMap.put(k2, combine(combine(initializedMap.remove(k), initializedMap.remove(k2)), v));
                return true;
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public V get(K k) {
        return get(k, null);
    }

    public V remove(Object obj) {
        synchronized (this.mLock) {
            try {
                if (obj == null) {
                    return null;
                }
                Map<K, Object> map = this.map;
                if (map == null) {
                    return null;
                }
                return (V) map.remove(obj);
            } catch (Throwable th) {
                throw th;
            }
        }
    }
}
