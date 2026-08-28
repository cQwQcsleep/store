package com.shadow.kotlin.collections.builders;

import com.shadow.kotlin.collections.AbstractList;
import com.shadow.kotlin.io.CloseableKt;
import com.shadow.kotlin.ranges.IntRange;
import java.io.NotSerializableException;
import java.io.Serializable;
import java.util.Arrays;
import java.util.Collection;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Set;
import kotlin.collections.builders.MapBuilder;
import kotlin.jvm.internal.markers.KMappedMarker;

/* loaded from: /workspace/unpacked/classes2.dex */
public final class MapBuilder<K, V> implements Map<K, V>, Serializable, KMappedMarker {
    public static final Companion Companion = new Companion();
    private static final MapBuilder Empty;
    private kotlin.collections.builders.MapBuilderEntries<K, V> entriesView;
    private int[] hashArray;
    private int hashShift;
    private boolean isReadOnly;
    private K[] keysArray;
    private kotlin.collections.builders.MapBuilderKeys<K> keysView;
    private int length;
    private int maxProbeDistance;
    private int modCount;
    private int[] presenceArray;
    private int size;
    private V[] valuesArray;
    private kotlin.collections.builders.MapBuilderValues<V> valuesView;

    public final class Companion {
    }

    public final class EntriesItr<K, V> extends MapBuilder.Itr<K, V> implements Iterator<Map.Entry<K, V>>, KMappedMarker {
        @Override // java.util.Iterator
        public final Object next() {
            checkForComodification$kotlin_stdlib();
            if (getIndex$kotlin_stdlib() >= ((MapBuilder) getMap$kotlin_stdlib()).length) {
                throw new NoSuchElementException();
            }
            int index$kotlin_stdlib = getIndex$kotlin_stdlib();
            setIndex$kotlin_stdlib(index$kotlin_stdlib + 1);
            setLastIndex$kotlin_stdlib(index$kotlin_stdlib);
            EntryRef entryRef = new EntryRef(getMap$kotlin_stdlib(), getLastIndex$kotlin_stdlib());
            initNext$kotlin_stdlib();
            return entryRef;
        }
    }

    public final class EntryRef<K, V> implements Map.Entry<K, V>, KMappedMarker {
        private final int index;
        private final kotlin.collections.builders.MapBuilder<K, V> map;

        public EntryRef(kotlin.collections.builders.MapBuilder<K, V> mapBuilder, int i) {
            CloseableKt.checkNotNullParameter(mapBuilder, "map");
            this.map = mapBuilder;
            this.index = i;
        }

        @Override // java.util.Map.Entry
        public final boolean equals(Object obj) {
            if (obj instanceof Map.Entry) {
                Map.Entry entry = (Map.Entry) obj;
                if (CloseableKt.areEqual(entry.getKey(), getKey()) && CloseableKt.areEqual(entry.getValue(), getValue())) {
                    return true;
                }
            }
            return false;
        }

        @Override // java.util.Map.Entry
        public final K getKey() {
            return (K) ((MapBuilder) this.map).keysArray[this.index];
        }

        @Override // java.util.Map.Entry
        public final V getValue() {
            Object[] objArr = ((MapBuilder) this.map).valuesArray;
            CloseableKt.checkNotNull(objArr);
            return (V) objArr[this.index];
        }

        @Override // java.util.Map.Entry
        public final int hashCode() {
            K key = getKey();
            int iHashCode = key != null ? key.hashCode() : 0;
            V value = getValue();
            return iHashCode ^ (value != null ? value.hashCode() : 0);
        }

        @Override // java.util.Map.Entry
        public final V setValue(V v) {
            kotlin.collections.builders.MapBuilder<K, V> mapBuilder = this.map;
            mapBuilder.checkIsMutable$kotlin_stdlib();
            Object[] objArrAccess$allocateValuesArray = MapBuilder.access$allocateValuesArray(mapBuilder);
            int i = this.index;
            V v2 = (V) objArrAccess$allocateValuesArray[i];
            objArrAccess$allocateValuesArray[i] = v;
            return v2;
        }

        public final String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append(getKey());
            sb.append('=');
            sb.append(getValue());
            return sb.toString();
        }
    }

    public abstract class Itr<K, V> {
        private int expectedModCount;
        private int index;
        private int lastIndex;
        private final kotlin.collections.builders.MapBuilder<K, V> map;

        public Itr(kotlin.collections.builders.MapBuilder<K, V> mapBuilder) {
            CloseableKt.checkNotNullParameter(mapBuilder, "map");
            this.map = mapBuilder;
            this.lastIndex = -1;
            this.expectedModCount = ((MapBuilder) mapBuilder).modCount;
            initNext$kotlin_stdlib();
        }

        public final void checkForComodification$kotlin_stdlib() {
            if (((MapBuilder) this.map).modCount != this.expectedModCount) {
                throw new ConcurrentModificationException();
            }
        }

        public final int getIndex$kotlin_stdlib() {
            return this.index;
        }

        public final int getLastIndex$kotlin_stdlib() {
            return this.lastIndex;
        }

        public final kotlin.collections.builders.MapBuilder<K, V> getMap$kotlin_stdlib() {
            return this.map;
        }

        public final boolean hasNext() {
            return this.index < ((MapBuilder) this.map).length;
        }

        public final void initNext$kotlin_stdlib() {
            while (true) {
                int i = this.index;
                kotlin.collections.builders.MapBuilder<K, V> mapBuilder = this.map;
                if (i >= ((MapBuilder) mapBuilder).length) {
                    return;
                }
                int[] iArr = ((MapBuilder) mapBuilder).presenceArray;
                int i2 = this.index;
                if (iArr[i2] >= 0) {
                    return;
                } else {
                    this.index = i2 + 1;
                }
            }
        }

        public final void remove() {
            checkForComodification$kotlin_stdlib();
            if (this.lastIndex == -1) {
                throw new IllegalStateException("Call next() before removing element from the iterator.");
            }
            kotlin.collections.builders.MapBuilder<K, V> mapBuilder = this.map;
            mapBuilder.checkIsMutable$kotlin_stdlib();
            mapBuilder.removeKeyAt(this.lastIndex);
            this.lastIndex = -1;
            this.expectedModCount = ((MapBuilder) mapBuilder).modCount;
        }

        public final void setIndex$kotlin_stdlib(int i) {
            this.index = i;
        }

        public final void setLastIndex$kotlin_stdlib(int i) {
            this.lastIndex = i;
        }
    }

    public final class KeysItr<K, V> extends MapBuilder.Itr<K, V> implements Iterator<K>, KMappedMarker {
        @Override // java.util.Iterator
        public final K next() {
            checkForComodification$kotlin_stdlib();
            if (getIndex$kotlin_stdlib() >= ((MapBuilder) getMap$kotlin_stdlib()).length) {
                throw new NoSuchElementException();
            }
            int index$kotlin_stdlib = getIndex$kotlin_stdlib();
            setIndex$kotlin_stdlib(index$kotlin_stdlib + 1);
            setLastIndex$kotlin_stdlib(index$kotlin_stdlib);
            K k = (K) ((MapBuilder) getMap$kotlin_stdlib()).keysArray[getLastIndex$kotlin_stdlib()];
            initNext$kotlin_stdlib();
            return k;
        }
    }

    public final class ValuesItr<K, V> extends MapBuilder.Itr<K, V> implements Iterator<V>, KMappedMarker {
        @Override // java.util.Iterator
        public final V next() {
            checkForComodification$kotlin_stdlib();
            if (getIndex$kotlin_stdlib() >= ((MapBuilder) getMap$kotlin_stdlib()).length) {
                throw new NoSuchElementException();
            }
            int index$kotlin_stdlib = getIndex$kotlin_stdlib();
            setIndex$kotlin_stdlib(index$kotlin_stdlib + 1);
            setLastIndex$kotlin_stdlib(index$kotlin_stdlib);
            Object[] objArr = ((MapBuilder) getMap$kotlin_stdlib()).valuesArray;
            CloseableKt.checkNotNull(objArr);
            V v = (V) objArr[getLastIndex$kotlin_stdlib()];
            initNext$kotlin_stdlib();
            return v;
        }
    }

    static {
        MapBuilder mapBuilder = new MapBuilder(0);
        mapBuilder.isReadOnly = true;
        Empty = mapBuilder;
    }

    public MapBuilder() {
        this(8);
    }

    public static final Object[] access$allocateValuesArray(MapBuilder mapBuilder) {
        V[] vArr = mapBuilder.valuesArray;
        if (vArr != null) {
            return vArr;
        }
        int capacity$kotlin_stdlib = mapBuilder.getCapacity$kotlin_stdlib();
        if (capacity$kotlin_stdlib < 0) {
            throw new IllegalArgumentException("capacity must be non-negative.");
        }
        V[] vArr2 = (V[]) new Object[capacity$kotlin_stdlib];
        mapBuilder.valuesArray = vArr2;
        return vArr2;
    }

    private final void ensureExtraCapacity(int i) {
        V[] vArr;
        int capacity$kotlin_stdlib = getCapacity$kotlin_stdlib();
        int i2 = this.length;
        int i3 = capacity$kotlin_stdlib - i2;
        int size = i2 - size();
        if (i3 < i && i3 + size >= i && size >= getCapacity$kotlin_stdlib() / 4) {
            rehash(this.hashArray.length);
            return;
        }
        int i4 = this.length + i;
        if (i4 < 0) {
            throw new OutOfMemoryError();
        }
        if (i4 > getCapacity$kotlin_stdlib()) {
            AbstractList.Companion companion = AbstractList.Companion;
            int capacity$kotlin_stdlib2 = getCapacity$kotlin_stdlib();
            companion.getClass();
            int iNewCapacity$kotlin_stdlib = AbstractList.Companion.newCapacity$kotlin_stdlib(capacity$kotlin_stdlib2, i4);
            K[] kArr = this.keysArray;
            CloseableKt.checkNotNullParameter(kArr, "<this>");
            K[] kArr2 = (K[]) Arrays.copyOf(kArr, iNewCapacity$kotlin_stdlib);
            CloseableKt.checkNotNullExpressionValue(kArr2, "copyOf(...)");
            this.keysArray = kArr2;
            V[] vArr2 = this.valuesArray;
            if (vArr2 != null) {
                vArr = (V[]) Arrays.copyOf(vArr2, iNewCapacity$kotlin_stdlib);
                CloseableKt.checkNotNullExpressionValue(vArr, "copyOf(...)");
            } else {
                vArr = null;
            }
            this.valuesArray = vArr;
            int[] iArrCopyOf = Arrays.copyOf(this.presenceArray, iNewCapacity$kotlin_stdlib);
            CloseableKt.checkNotNullExpressionValue(iArrCopyOf, "copyOf(...)");
            this.presenceArray = iArrCopyOf;
            Companion.getClass();
            if (iNewCapacity$kotlin_stdlib < 1) {
                iNewCapacity$kotlin_stdlib = 1;
            }
            int iHighestOneBit = Integer.highestOneBit(iNewCapacity$kotlin_stdlib * 3);
            if (iHighestOneBit > this.hashArray.length) {
                rehash(iHighestOneBit);
            }
        }
    }

    private final int findKey(K k) {
        int iHash = hash(k);
        int i = this.maxProbeDistance;
        while (true) {
            int i2 = this.hashArray[iHash];
            if (i2 == 0) {
                return -1;
            }
            if (i2 > 0) {
                int i3 = i2 - 1;
                if (CloseableKt.areEqual(this.keysArray[i3], k)) {
                    return i3;
                }
            }
            i--;
            if (i < 0) {
                return -1;
            }
            iHash = iHash == 0 ? this.hashArray.length - 1 : iHash - 1;
        }
    }

    private final int findValue(V v) {
        int i = this.length;
        while (true) {
            i--;
            if (i < 0) {
                return -1;
            }
            if (this.presenceArray[i] >= 0) {
                V[] vArr = this.valuesArray;
                CloseableKt.checkNotNull(vArr);
                if (CloseableKt.areEqual(vArr[i], v)) {
                    return i;
                }
            }
        }
    }

    private final int hash(K k) {
        return ((k != null ? k.hashCode() : 0) * (-1640531527)) >>> this.hashShift;
    }

    /* JADX WARN: Code restructure failed: missing block: B:27:0x006d, code lost:
    
        r3[r0] = r7;
        r6.presenceArray[r2] = r0;
        r2 = r7;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private final void rehash(int i) {
        int i2;
        this.modCount++;
        int i3 = 0;
        if (this.length > size()) {
            V[] vArr = this.valuesArray;
            int i4 = 0;
            int i5 = 0;
            while (true) {
                i2 = this.length;
                if (i4 >= i2) {
                    break;
                }
                if (this.presenceArray[i4] >= 0) {
                    K[] kArr = this.keysArray;
                    kArr[i5] = kArr[i4];
                    if (vArr != null) {
                        vArr[i5] = vArr[i4];
                    }
                    i5++;
                }
                i4++;
            }
            ListBuilderKt.resetRange(this.keysArray, i5, i2);
            if (vArr != null) {
                ListBuilderKt.resetRange(vArr, i5, this.length);
            }
            this.length = i5;
        }
        int[] iArr = this.hashArray;
        if (i != iArr.length) {
            this.hashArray = new int[i];
            Companion.getClass();
            this.hashShift = Integer.numberOfLeadingZeros(i) + 1;
        } else {
            Arrays.fill(iArr, 0, iArr.length, 0);
        }
        while (i3 < this.length) {
            int i6 = i3 + 1;
            int iHash = hash(this.keysArray[i3]);
            int i7 = this.maxProbeDistance;
            while (true) {
                int[] iArr2 = this.hashArray;
                if (iArr2[iHash] == 0) {
                    break;
                }
                i7--;
                if (i7 < 0) {
                    throw new IllegalStateException("This cannot happen with fixed magic multiplier and grow-only hash array. Have object hashCodes changed?");
                }
                iHash = iHash == 0 ? iArr2.length - 1 : iHash - 1;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:29:0x0062 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:30:? A[LOOP:0: B:6:0x001e->B:30:?, LOOP_END, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final void removeKeyAt(int i) {
        K[] kArr = this.keysArray;
        CloseableKt.checkNotNullParameter(kArr, "<this>");
        kArr[i] = null;
        int length = this.presenceArray[i];
        int i2 = this.maxProbeDistance * 2;
        int length2 = this.hashArray.length / 2;
        if (i2 > length2) {
            i2 = length2;
        }
        int i3 = i2;
        int i4 = 0;
        int i5 = length;
        while (true) {
            length = length == 0 ? this.hashArray.length - 1 : length - 1;
            i4++;
            if (i4 > this.maxProbeDistance) {
                this.hashArray[i5] = 0;
                break;
            }
            int[] iArr = this.hashArray;
            int i6 = iArr[length];
            if (i6 == 0) {
                iArr[i5] = 0;
                break;
            }
            if (i6 < 0) {
                iArr[i5] = -1;
            } else {
                int i7 = i6 - 1;
                int iHash = hash(this.keysArray[i7]) - length;
                int[] iArr2 = this.hashArray;
                if ((iHash & (iArr2.length - 1)) >= i4) {
                    iArr2[i5] = i6;
                    this.presenceArray[i7] = i5;
                }
                i3--;
                if (i3 >= 0) {
                    this.hashArray[i5] = -1;
                    break;
                }
            }
            i5 = length;
            i4 = 0;
            i3--;
            if (i3 >= 0) {
            }
        }
        this.presenceArray[i] = -1;
        this.size = size() - 1;
        this.modCount++;
    }

    private final Object writeReplace() throws NotSerializableException {
        if (this.isReadOnly) {
            return new SerializedMap(this);
        }
        throw new NotSerializableException("The map cannot be serialized while it is being built.");
    }

    public final int addKey$kotlin_stdlib(K k) {
        checkIsMutable$kotlin_stdlib();
        while (true) {
            int iHash = hash(k);
            int i = this.maxProbeDistance * 2;
            int length = this.hashArray.length / 2;
            if (i > length) {
                i = length;
            }
            int i2 = 0;
            while (true) {
                int i3 = this.hashArray[iHash];
                if (i3 <= 0) {
                    if (this.length < getCapacity$kotlin_stdlib()) {
                        int i4 = this.length;
                        int i5 = i4 + 1;
                        this.length = i5;
                        this.keysArray[i4] = k;
                        this.presenceArray[i4] = iHash;
                        this.hashArray[iHash] = i5;
                        this.size = size() + 1;
                        this.modCount++;
                        if (i2 > this.maxProbeDistance) {
                            this.maxProbeDistance = i2;
                        }
                        return i4;
                    }
                    ensureExtraCapacity(1);
                } else {
                    if (CloseableKt.areEqual(this.keysArray[i3 - 1], k)) {
                        return -i3;
                    }
                    i2++;
                    if (i2 > i) {
                        rehash(this.hashArray.length * 2);
                        break;
                    }
                    iHash = iHash == 0 ? this.hashArray.length - 1 : iHash - 1;
                }
            }
        }
    }

    public final Map<K, V> build() {
        checkIsMutable$kotlin_stdlib();
        this.isReadOnly = true;
        if (size() > 0) {
            return this;
        }
        MapBuilder mapBuilder = Empty;
        CloseableKt.checkNotNull(mapBuilder, "null cannot be cast to non-null type kotlin.collections.Map<K of kotlin.collections.builders.MapBuilder, V of kotlin.collections.builders.MapBuilder>");
        return mapBuilder;
    }

    public final void checkIsMutable$kotlin_stdlib() {
        if (this.isReadOnly) {
            throw new UnsupportedOperationException();
        }
    }

    /* JADX WARN: Type inference failed for: r0v1, types: [com.shadow.kotlin.ranges.IntProgressionIterator] */
    @Override // java.util.Map
    public void clear() {
        checkIsMutable$kotlin_stdlib();
        ?? Iterator2 = new IntRange(0, this.length - 1, 1).iterator2();
        while (Iterator2.hasNext()) {
            int iNextInt = Iterator2.nextInt();
            int[] iArr = this.presenceArray;
            int i = iArr[iNextInt];
            if (i >= 0) {
                this.hashArray[i] = 0;
                iArr[iNextInt] = -1;
            }
        }
        ListBuilderKt.resetRange(this.keysArray, 0, this.length);
        V[] vArr = this.valuesArray;
        if (vArr != null) {
            ListBuilderKt.resetRange(vArr, 0, this.length);
        }
        this.size = 0;
        this.length = 0;
        this.modCount++;
    }

    public final boolean containsAllEntries$kotlin_stdlib(Collection<?> collection) {
        CloseableKt.checkNotNullParameter(collection, "m");
        for (Object obj : collection) {
            if (obj != null) {
                try {
                    if (!containsEntry$kotlin_stdlib((Map.Entry) obj)) {
                    }
                } catch (ClassCastException unused) {
                }
            }
            return false;
        }
        return true;
    }

    public final boolean containsEntry$kotlin_stdlib(Map.Entry<? extends K, ? extends V> entry) {
        CloseableKt.checkNotNullParameter(entry, "entry");
        int iFindKey = findKey(entry.getKey());
        if (iFindKey < 0) {
            return false;
        }
        V[] vArr = this.valuesArray;
        CloseableKt.checkNotNull(vArr);
        return CloseableKt.areEqual(vArr[iFindKey], entry.getValue());
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // java.util.Map
    public boolean containsKey(Object obj) {
        return findKey(obj) >= 0;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // java.util.Map
    public boolean containsValue(Object obj) {
        return findValue(obj) >= 0;
    }

    public final MapBuilder.EntriesItr<K, V> entriesIterator$kotlin_stdlib() {
        return new EntriesItr(this);
    }

    @Override // java.util.Map
    public final /* bridge */ Set<Map.Entry<K, V>> entrySet() {
        return getEntries();
    }

    @Override // java.util.Map
    public boolean equals(Object obj) {
        if (obj != this) {
            if (obj instanceof Map) {
                Map map = (Map) obj;
                if (size() != map.size() || !containsAllEntries$kotlin_stdlib(map.entrySet())) {
                }
            }
            return false;
        }
        return true;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // java.util.Map
    public V get(Object obj) {
        int iFindKey = findKey(obj);
        if (iFindKey < 0) {
            return null;
        }
        V[] vArr = this.valuesArray;
        CloseableKt.checkNotNull(vArr);
        return vArr[iFindKey];
    }

    public final int getCapacity$kotlin_stdlib() {
        return this.keysArray.length;
    }

    public Set<Map.Entry<K, V>> getEntries() {
        kotlin.collections.builders.MapBuilderEntries<K, V> mapBuilderEntries = this.entriesView;
        if (mapBuilderEntries != null) {
            return mapBuilderEntries;
        }
        MapBuilderEntries mapBuilderEntries2 = new MapBuilderEntries(this);
        this.entriesView = mapBuilderEntries2;
        return mapBuilderEntries2;
    }

    public Set<K> getKeys() {
        kotlin.collections.builders.MapBuilderKeys<K> mapBuilderKeys = this.keysView;
        if (mapBuilderKeys != null) {
            return mapBuilderKeys;
        }
        MapBuilderKeys mapBuilderKeys2 = new MapBuilderKeys(this);
        this.keysView = mapBuilderKeys2;
        return mapBuilderKeys2;
    }

    public int getSize() {
        return this.size;
    }

    public Collection<V> getValues() {
        kotlin.collections.builders.MapBuilderValues<V> mapBuilderValues = this.valuesView;
        if (mapBuilderValues != null) {
            return mapBuilderValues;
        }
        MapBuilderValues mapBuilderValues2 = new MapBuilderValues(this);
        this.valuesView = mapBuilderValues2;
        return mapBuilderValues2;
    }

    @Override // java.util.Map
    public int hashCode() {
        MapBuilder.EntriesItr<K, V> entriesItrEntriesIterator$kotlin_stdlib = entriesIterator$kotlin_stdlib();
        int i = 0;
        while (entriesItrEntriesIterator$kotlin_stdlib.hasNext()) {
            if (entriesItrEntriesIterator$kotlin_stdlib.getIndex$kotlin_stdlib() >= entriesItrEntriesIterator$kotlin_stdlib.getMap$kotlin_stdlib().length) {
                throw new NoSuchElementException();
            }
            int index$kotlin_stdlib = entriesItrEntriesIterator$kotlin_stdlib.getIndex$kotlin_stdlib();
            entriesItrEntriesIterator$kotlin_stdlib.setIndex$kotlin_stdlib(index$kotlin_stdlib + 1);
            entriesItrEntriesIterator$kotlin_stdlib.setLastIndex$kotlin_stdlib(index$kotlin_stdlib);
            Object obj = entriesItrEntriesIterator$kotlin_stdlib.getMap$kotlin_stdlib().keysArray[entriesItrEntriesIterator$kotlin_stdlib.getLastIndex$kotlin_stdlib()];
            int iHashCode = obj != null ? obj.hashCode() : 0;
            Object[] objArr = entriesItrEntriesIterator$kotlin_stdlib.getMap$kotlin_stdlib().valuesArray;
            CloseableKt.checkNotNull(objArr);
            Object obj2 = objArr[entriesItrEntriesIterator$kotlin_stdlib.getLastIndex$kotlin_stdlib()];
            int iHashCode2 = obj2 != null ? obj2.hashCode() : 0;
            entriesItrEntriesIterator$kotlin_stdlib.initNext$kotlin_stdlib();
            i += iHashCode ^ iHashCode2;
        }
        return i;
    }

    @Override // java.util.Map
    public boolean isEmpty() {
        return size() == 0;
    }

    public final boolean isReadOnly$kotlin_stdlib() {
        return this.isReadOnly;
    }

    @Override // java.util.Map
    public final /* bridge */ Set<K> keySet() {
        return getKeys();
    }

    public final MapBuilder.KeysItr<K, V> keysIterator$kotlin_stdlib() {
        return new KeysItr(this);
    }

    @Override // java.util.Map
    public V put(K k, V v) {
        checkIsMutable$kotlin_stdlib();
        int iAddKey$kotlin_stdlib = addKey$kotlin_stdlib(k);
        V[] vArr = this.valuesArray;
        if (vArr == null) {
            int capacity$kotlin_stdlib = getCapacity$kotlin_stdlib();
            if (capacity$kotlin_stdlib < 0) {
                throw new IllegalArgumentException("capacity must be non-negative.");
            }
            vArr = (V[]) new Object[capacity$kotlin_stdlib];
            this.valuesArray = vArr;
        }
        if (iAddKey$kotlin_stdlib >= 0) {
            vArr[iAddKey$kotlin_stdlib] = v;
            return null;
        }
        int i = (-iAddKey$kotlin_stdlib) - 1;
        V v2 = vArr[i];
        vArr[i] = v;
        return v2;
    }

    @Override // java.util.Map
    public void putAll(Map<? extends K, ? extends V> map) {
        CloseableKt.checkNotNullParameter(map, "from");
        checkIsMutable$kotlin_stdlib();
        Set<Map.Entry<? extends K, ? extends V>> setEntrySet = map.entrySet();
        if (setEntrySet.isEmpty()) {
            return;
        }
        ensureExtraCapacity(setEntrySet.size());
        for (Map.Entry<? extends K, ? extends V> entry : setEntrySet) {
            int iAddKey$kotlin_stdlib = addKey$kotlin_stdlib(entry.getKey());
            V[] vArr = this.valuesArray;
            if (vArr == null) {
                int capacity$kotlin_stdlib = getCapacity$kotlin_stdlib();
                if (capacity$kotlin_stdlib < 0) {
                    throw new IllegalArgumentException("capacity must be non-negative.");
                }
                vArr = (V[]) new Object[capacity$kotlin_stdlib];
                this.valuesArray = vArr;
            }
            if (iAddKey$kotlin_stdlib >= 0) {
                vArr[iAddKey$kotlin_stdlib] = entry.getValue();
            } else {
                int i = (-iAddKey$kotlin_stdlib) - 1;
                if (!CloseableKt.areEqual(entry.getValue(), vArr[i])) {
                    vArr[i] = entry.getValue();
                }
            }
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // java.util.Map
    public V remove(Object obj) {
        int iRemoveKey$kotlin_stdlib = removeKey$kotlin_stdlib(obj);
        if (iRemoveKey$kotlin_stdlib < 0) {
            return null;
        }
        V[] vArr = this.valuesArray;
        CloseableKt.checkNotNull(vArr);
        V v = vArr[iRemoveKey$kotlin_stdlib];
        vArr[iRemoveKey$kotlin_stdlib] = null;
        return v;
    }

    public final boolean removeEntry$kotlin_stdlib(Map.Entry<? extends K, ? extends V> entry) {
        CloseableKt.checkNotNullParameter(entry, "entry");
        checkIsMutable$kotlin_stdlib();
        int iFindKey = findKey(entry.getKey());
        if (iFindKey < 0) {
            return false;
        }
        V[] vArr = this.valuesArray;
        CloseableKt.checkNotNull(vArr);
        if (!CloseableKt.areEqual(vArr[iFindKey], entry.getValue())) {
            return false;
        }
        removeKeyAt(iFindKey);
        return true;
    }

    public final int removeKey$kotlin_stdlib(K k) {
        checkIsMutable$kotlin_stdlib();
        int iFindKey = findKey(k);
        if (iFindKey < 0) {
            return -1;
        }
        removeKeyAt(iFindKey);
        return iFindKey;
    }

    public final boolean removeValue$kotlin_stdlib(V v) {
        checkIsMutable$kotlin_stdlib();
        int iFindValue = findValue(v);
        if (iFindValue < 0) {
            return false;
        }
        removeKeyAt(iFindValue);
        return true;
    }

    @Override // java.util.Map
    public final /* bridge */ int size() {
        return getSize();
    }

    public String toString() {
        StringBuilder sb = new StringBuilder((size() * 3) + 2);
        sb.append("{");
        MapBuilder.EntriesItr<K, V> entriesItrEntriesIterator$kotlin_stdlib = entriesIterator$kotlin_stdlib();
        int i = 0;
        while (entriesItrEntriesIterator$kotlin_stdlib.hasNext()) {
            if (i > 0) {
                sb.append(", ");
            }
            if (entriesItrEntriesIterator$kotlin_stdlib.getIndex$kotlin_stdlib() >= entriesItrEntriesIterator$kotlin_stdlib.getMap$kotlin_stdlib().length) {
                throw new NoSuchElementException();
            }
            int index$kotlin_stdlib = entriesItrEntriesIterator$kotlin_stdlib.getIndex$kotlin_stdlib();
            entriesItrEntriesIterator$kotlin_stdlib.setIndex$kotlin_stdlib(index$kotlin_stdlib + 1);
            entriesItrEntriesIterator$kotlin_stdlib.setLastIndex$kotlin_stdlib(index$kotlin_stdlib);
            Object obj = entriesItrEntriesIterator$kotlin_stdlib.getMap$kotlin_stdlib().keysArray[entriesItrEntriesIterator$kotlin_stdlib.getLastIndex$kotlin_stdlib()];
            if (obj == entriesItrEntriesIterator$kotlin_stdlib.getMap$kotlin_stdlib()) {
                sb.append("(this Map)");
            } else {
                sb.append(obj);
            }
            sb.append('=');
            Object[] objArr = entriesItrEntriesIterator$kotlin_stdlib.getMap$kotlin_stdlib().valuesArray;
            CloseableKt.checkNotNull(objArr);
            Object obj2 = objArr[entriesItrEntriesIterator$kotlin_stdlib.getLastIndex$kotlin_stdlib()];
            if (obj2 == entriesItrEntriesIterator$kotlin_stdlib.getMap$kotlin_stdlib()) {
                sb.append("(this Map)");
            } else {
                sb.append(obj2);
            }
            entriesItrEntriesIterator$kotlin_stdlib.initNext$kotlin_stdlib();
            i++;
        }
        sb.append("}");
        String string = sb.toString();
        CloseableKt.checkNotNullExpressionValue(string, "toString(...)");
        return string;
    }

    @Override // java.util.Map
    public final /* bridge */ Collection<V> values() {
        return getValues();
    }

    public final MapBuilder.ValuesItr<K, V> valuesIterator$kotlin_stdlib() {
        return new ValuesItr(this);
    }

    public MapBuilder(int i) {
        if (i < 0) {
            throw new IllegalArgumentException("capacity must be non-negative.");
        }
        K[] kArr = (K[]) new Object[i];
        int[] iArr = new int[i];
        Companion.getClass();
        int iHighestOneBit = Integer.highestOneBit((i < 1 ? 1 : i) * 3);
        this.keysArray = kArr;
        this.valuesArray = null;
        this.presenceArray = iArr;
        this.hashArray = new int[iHighestOneBit];
        this.maxProbeDistance = 2;
        this.length = 0;
        this.hashShift = Integer.numberOfLeadingZeros(iHighestOneBit) + 1;
    }
}
