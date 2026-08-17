package com.reandroid.arsc.value.bag;

import com.reandroid.arsc.array.ResValueMapArray;
import com.reandroid.arsc.base.BlockArray;
import com.reandroid.arsc.chunk.PackageBlock;
import com.reandroid.arsc.chunk.TableBlock;
import com.reandroid.arsc.pool.TableStringPool;
import com.reandroid.arsc.value.Entry;
import com.reandroid.arsc.value.ResTableMapEntry;
import com.reandroid.arsc.value.ResValueMap;
import com.reandroid.arsc.value.bag.BagItem;
import java.util.AbstractMap;
import java.util.AbstractSet;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public abstract class MapBag<K, V extends BagItem> extends AbstractMap<K, V> implements Bag {
    protected final Entry entry;
    private int modCount = 0;

    public class EntrySet extends AbstractSet<Map.Entry<K, V>> {
        private EntrySet() {
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.Set
        public Iterator<Map.Entry<K, V>> iterator() {
            return (Iterator<Map.Entry<K, V>>) new Iterator<Map.Entry<K, V>>() { // from class: com.reandroid.arsc.value.bag.MapBag.EntrySet.1
                private final int expectedModCount;
                private final Iterator<ResValueMap> iterator;

                {
                    this.iterator = MapBag.this.getMapArray().iterator();
                    this.expectedModCount = MapBag.this.modCount;
                }

                private void checkValidity() {
                    if (this.expectedModCount != MapBag.this.modCount) {
                        throw new ConcurrentModificationException("Iterator is no longer valid because the size has changed.");
                    }
                }

                @Override // java.util.Iterator
                public boolean hasNext() {
                    checkValidity();
                    return this.iterator.hasNext();
                }

                @Override // java.util.Iterator
                public Map.Entry<K, V> next() {
                    checkValidity();
                    return new MapEntry(this.iterator.next());
                }
            };
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public int size() {
            return MapBag.this.getMapArray().size();
        }
    }

    public class MapEntry implements Map.Entry<K, V> {
        private final ResValueMap item;

        private MapEntry(ResValueMap resValueMap) {
            this.item = resValueMap;
        }

        @Override // java.util.Map.Entry
        public K getKey() {
            return (K) MapBag.this.getKeyFor(this.item);
        }

        @Override // java.util.Map.Entry
        public V getValue() {
            return (V) MapBag.this.createBagItem(this.item, false);
        }

        @Override // java.util.Map.Entry
        public V setValue(V v) {
            v.copyTo(this.item);
            return (V) getValue();
        }
    }

    public MapBag(Entry entry) {
        this.entry = entry;
    }

    private void updateSize() {
        getTableEntry().setValuesCount(size());
        this.modCount++;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public void clear() {
        getMapArray().clear();
        updateSize();
    }

    public abstract V createBagItem(ResValueMap resValueMap, boolean z);

    @Override // java.util.AbstractMap, java.util.Map
    public Set<Map.Entry<K, V>> entrySet() {
        return new EntrySet();
    }

    @Override // com.reandroid.arsc.value.bag.Bag
    public Entry getEntry() {
        return this.entry;
    }

    public abstract K getKeyFor(ResValueMap resValueMap);

    /* JADX WARN: Multi-variable type inference failed */
    public ResValueMapArray getMapArray() {
        return (ResValueMapArray) getTableEntry().getValue();
    }

    public TableStringPool getStringPool() {
        PackageBlock packageBlock;
        TableBlock tableBlock;
        Entry entry = getEntry();
        if (entry == null || (packageBlock = entry.getPackageBlock()) == null || (tableBlock = packageBlock.getTableBlock()) == null) {
            return null;
        }
        return tableBlock.getTableStringPool();
    }

    public ResTableMapEntry getTableEntry() {
        return (ResTableMapEntry) this.entry.getTableEntry();
    }

    public abstract ResValueMap newKey(K k);

    @Override // java.util.AbstractMap, java.util.Map
    public V put(K k, V v) {
        ResValueMap resValueMapNewKey = null;
        if (k == null) {
            x0e.a("key is null");
            return null;
        }
        if (v == null) {
            x0e.a("value is null");
            return null;
        }
        BlockArray mapArray = getMapArray();
        Iterator itClonedIterator = mapArray.clonedIterator();
        while (itClonedIterator.hasNext()) {
            ResValueMap resValueMap = (ResValueMap) itClonedIterator.next();
            if (getKeyFor(resValueMap).equals(k)) {
                resValueMapNewKey = resValueMap;
                break;
            }
        }
        if (resValueMapNewKey == null) {
            resValueMapNewKey = newKey(k);
            mapArray.add(resValueMapNewKey);
            updateSize();
        }
        v.copyTo(resValueMapNewKey);
        return (V) createBagItem(resValueMapNewKey, false);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // java.util.AbstractMap, java.util.Map
    public void putAll(Map<? extends K, ? extends V> map) {
        LinkedHashSet linkedHashSet = new LinkedHashSet(map.keySet());
        BlockArray mapArray = getMapArray();
        Iterator itClonedIterator = mapArray.clonedIterator();
        while (itClonedIterator.hasNext()) {
            ResValueMap resValueMap = (ResValueMap) itClonedIterator.next();
            Object keyFor = getKeyFor(resValueMap);
            if (linkedHashSet.remove(keyFor)) {
                map.get(keyFor).copyTo(resValueMap);
            }
        }
        for (Object obj : linkedHashSet) {
            if (obj == null) {
                x0e.a("Key is null");
                return;
            } else {
                ResValueMap resValueMapNewKey = newKey(obj);
                mapArray.add(resValueMapNewKey);
                map.get(obj).copyTo(resValueMapNewKey);
            }
        }
        updateSize();
    }

    @Override // java.util.AbstractMap, java.util.Map
    public V remove(Object obj) {
        BlockArray mapArray = getMapArray();
        Iterator itClonedIterator = mapArray.clonedIterator();
        while (itClonedIterator.hasNext()) {
            ResValueMap resValueMap = (ResValueMap) itClonedIterator.next();
            if (getKeyFor(resValueMap).equals(obj)) {
                if (!mapArray.remove(resValueMap)) {
                    k2d.a("Could not remove item");
                    break;
                }
                updateSize();
                return (V) createBagItem(resValueMap, true);
            }
        }
        return null;
    }
}
