package com.reandroid.dex.key;

import com.reandroid.dex.key.Key;
import com.reandroid.dex.smali.SmaliWriter;
import com.reandroid.utils.CompareUtil;
import com.reandroid.utils.ObjectsUtil;
import com.reandroid.utils.collection.ArrayIterator;
import com.reandroid.utils.collection.ArraySort;
import com.reandroid.utils.collection.CombiningIterator;
import com.reandroid.utils.collection.InstanceIterator;
import com.reandroid.utils.collection.IterableIterator;
import java.io.IOException;
import java.util.Comparator;
import java.util.Iterator;
import java.util.function.Predicate;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public abstract class KeyList<T extends Key> implements Key, Iterable<T> {
    static final Key[] EMPTY_ARRAY = new Key[0];
    private final Key[] elements;
    private int mHash;
    private final Key[] sortedElements;

    public KeyList(Key[] keyArr, boolean z) {
        this.elements = keyArr;
        this.sortedElements = z ? sortElements(keyArr) : null;
    }

    public static <T1 extends Key> boolean equalsIgnoreEmpty(KeyList<T1> keyList, KeyList<T1> keyList2) {
        if (keyList == keyList2) {
            return true;
        }
        if (keyList == null || keyList.isEmpty()) {
            return keyList2 == null || keyList2.isEmpty();
        }
        return (keyList2 == null || keyList2.isEmpty()) ? keyList.isEmpty() : keyList.equalsElements(keyList2);
    }

    private Key[] getSortedElements() {
        Key[] keyArr = this.sortedElements;
        return keyArr == null ? this.elements : keyArr;
    }

    private Key[] newArray(int i) {
        return i == 0 ? EMPTY_ARRAY : new Key[i];
    }

    public static Key[] removeNulls(Key[] keyArr) {
        if (keyArr == null || keyArr.length == 0) {
            return EMPTY_ARRAY;
        }
        int length = keyArr.length;
        int i = 0;
        for (Key key : keyArr) {
            if (key != null) {
                i++;
            }
        }
        if (i == length) {
            return keyArr;
        }
        if (i == 0) {
            return EMPTY_ARRAY;
        }
        Key[] keyArr2 = new Key[i];
        int i2 = 0;
        for (Key key2 : keyArr) {
            if (key2 != null) {
                keyArr2[i2] = key2;
                i2++;
            }
        }
        return keyArr2;
    }

    private static <T extends Key, E extends Key> T[] sortElements(T[] tArr, Comparator<? super E> comparator) {
        if (tArr != null && tArr.length >= 2) {
            int length = tArr.length;
            T t = tArr[0];
            Comparator comparator2 = (Comparator) ObjectsUtil.cast(comparator);
            int i = 1;
            while (i < length) {
                T t2 = tArr[i];
                if (comparator2.compare(t, t2) > 0) {
                    T[] tArr2 = (T[]) ((Key[]) tArr.clone());
                    ArraySort.sort(tArr2, comparator2);
                    return tArr2;
                }
                i++;
                t = t2;
            }
        }
        return null;
    }

    public KeyList<T> add(T t) {
        Key[] keyArr = this.elements;
        int length = keyArr.length;
        Key[] keyArrNewArray = newArray(length + 1);
        for (int i = 0; i < length; i++) {
            keyArrNewArray[i] = keyArr[i];
        }
        keyArrNewArray[length] = t;
        return newInstance(keyArrNewArray);
    }

    @Override // com.reandroid.dex.key.Key, com.reandroid.dex.smali.SmaliFormat
    public void append(SmaliWriter smaliWriter) throws IOException {
        smaliWriter.appendAll(iterator());
    }

    public KeyList<T> clearDuplicates(Comparator<? super T> comparator) {
        Key[] sortedElements = getSortedElements();
        if (sortedElements.length >= 2) {
            Key[] keyArrSortElements = sortElements(sortedElements, comparator);
            if (keyArrSortElements == null) {
                keyArrSortElements = getSortedElements();
            }
            Comparator comparator2 = (Comparator) ObjectsUtil.cast(comparator);
            int length = keyArrSortElements.length;
            Key key = keyArrSortElements[0];
            int i = 1;
            Key[] keyArr = null;
            while (i < length) {
                Key key2 = keyArrSortElements[i];
                if (comparator2.compare(key, key2) == 0) {
                    if (keyArr == null) {
                        keyArr = (Key[]) keyArrSortElements.clone();
                    }
                    keyArr[i] = null;
                }
                i++;
                key = key2;
            }
            if (keyArr != null) {
                return newInstance(removeNulls(keyArr));
            }
        }
        return this;
    }

    public int compareElements(KeyList<?> keyList) {
        return CompareUtil.compare(getSortedElements(), keyList.getSortedElements());
    }

    public int computeHash() {
        return ObjectsUtil.hashElements(getSortedElements());
    }

    public boolean contains(Object obj) {
        for (Key key : this.elements) {
            if (ObjectsUtil.equals(key, obj)) {
                return true;
            }
        }
        return false;
    }

    public boolean equalsElements(KeyList<?> keyList) {
        return ObjectsUtil.equalsArray(getSortedElements(), keyList.getSortedElements());
    }

    public T get(int i) {
        Key[] keyArr = this.elements;
        if (i < 0 || i >= keyArr.length) {
            return null;
        }
        return (T) keyArr[i];
    }

    public T getDuplicate() {
        Object[] sortedElements = getSortedElements();
        int length = sortedElements.length;
        if (length < 2) {
            return null;
        }
        Object obj = sortedElements[0];
        int i = 1;
        while (i < length) {
            T t = (T) sortedElements[i];
            if (ObjectsUtil.equals(obj, t)) {
                return t;
            }
            i++;
            obj = t;
        }
        return null;
    }

    public Key[] getElements() {
        return this.elements;
    }

    public int getHashCode() {
        int i = this.mHash;
        if (i != 0) {
            return i;
        }
        int iComputeHash = computeHash();
        this.mHash = iComputeHash;
        return iComputeHash;
    }

    public int hashCode() {
        return getHashCode();
    }

    public int indexOf(Object obj) {
        Key[] keyArr = this.elements;
        int length = keyArr.length;
        for (int i = 0; i < length; i++) {
            if (ObjectsUtil.equals(keyArr[i], obj)) {
                return i;
            }
        }
        return -1;
    }

    public boolean isEmpty() {
        return size() == 0;
    }

    public <T1 extends Key> Iterator<T1> iterator(Class<T1> cls) {
        return InstanceIterator.of(iterator(), cls);
    }

    @Override // com.reandroid.dex.key.Key
    public Iterator<? extends Key> mentionedKeys() {
        return CombiningIterator.singleOne(this, new IterableIterator<T, Key>(iterator()) { // from class: com.reandroid.dex.key.KeyList.1
            public Iterator<Key> iterator(T t) {
                return (Iterator) ObjectsUtil.cast(t.mentionedKeys());
            }
        });
    }

    public abstract KeyList<T> newInstance(Key[] keyArr);

    public KeyList<T> remove(int i) {
        Key[] keyArr;
        int length;
        if (i < 0 || i >= (length = (keyArr = this.elements).length)) {
            return this;
        }
        if (length == 1) {
            return newInstance(newArray(0));
        }
        Key[] keyArrNewArray = newArray(length - 1);
        int i2 = 0;
        for (int i3 = 0; i3 < length; i3++) {
            if (i3 != i) {
                keyArrNewArray[i2] = keyArr[i3];
                i2++;
            }
        }
        return newInstance(keyArrNewArray);
    }

    public KeyList<T> removeIf(Predicate<? super T> predicate) {
        Key[] keyArr = this.elements;
        int length = keyArr.length;
        if (length != 0) {
            Key[] keyArr2 = null;
            int i = 0;
            for (int i2 = 0; i2 < length; i2++) {
                Key key = keyArr[i2];
                if (predicate.test(key)) {
                    if (keyArr2 == null) {
                        keyArr2 = (Key[]) keyArr.clone();
                        i = i2;
                    }
                } else if (keyArr2 != null) {
                    keyArr2[i] = key;
                    i++;
                }
            }
            if (keyArr2 != null) {
                Key[] keyArrNewArray = newArray(i);
                int length2 = keyArrNewArray.length;
                for (int i3 = 0; i3 < length2; i3++) {
                    keyArrNewArray[i3] = keyArr2[i3];
                }
                return newInstance(keyArrNewArray);
            }
        }
        return this;
    }

    public KeyList<T> replaceElements(Key key, Key key2) {
        int size = size();
        KeyList<T> keyList = this;
        for (int i = 0; i < size; i++) {
            Key key3 = get(i);
            if (key3 != null) {
                keyList = keyList.set(i, key3.replaceKey(key, key2));
            }
        }
        return keyList;
    }

    @Override // com.reandroid.dex.key.Key
    public KeyList<T> replaceKey(Key key, Key key2) {
        return equals(key) ? (KeyList) key2 : replaceElements(key, key2);
    }

    public KeyList<T> set(int i, T t) {
        if (ObjectsUtil.equals(t, get(i))) {
            return this;
        }
        Key[] keyArr = (Key[]) this.elements.clone();
        keyArr[i] = t;
        return newInstance(keyArr);
    }

    public int size() {
        return this.elements.length;
    }

    public KeyList<T> sort(Comparator<? super T> comparator) {
        Key[] keyArr = this.elements;
        int length = keyArr.length;
        if (length >= 2) {
            Key key = keyArr[0];
            int i = 1;
            while (i < length) {
                Key key2 = keyArr[i];
                if (comparator.compare(key, key2) > 0) {
                    Key[] keyArr2 = (Key[]) keyArr.clone();
                    ArraySort.sort(keyArr2, comparator);
                    return newInstance(keyArr2);
                }
                i++;
                key = key2;
            }
        }
        return this;
    }

    public KeyList<T> sorted() {
        Key[] keyArr = this.sortedElements;
        return keyArr == null ? this : newInstance(keyArr);
    }

    public String toString() {
        return SmaliWriter.toStringSafe(this);
    }

    public Iterator<T> iterator(Predicate<? super T> predicate) {
        return ArrayIterator.of(this.elements, predicate);
    }

    @Override // java.lang.Iterable
    public Iterator<T> iterator() {
        return ArrayIterator.of(this.elements);
    }

    public KeyList(Key[] keyArr) {
        this(keyArr, false);
    }

    private static <T extends Key> T[] sortElements(T[] tArr) {
        return (T[]) sortElements(tArr, CompareUtil.getComparableComparator());
    }

    public KeyList<T> remove(T t) {
        return remove(indexOf(t));
    }

    public KeyList<T> clearDuplicates() {
        return clearDuplicates(CompareUtil.getComparableComparator());
    }
}
