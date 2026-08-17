package com.reandroid.dex.key;

import com.reandroid.dex.key.Key;
import com.reandroid.dex.key.KeyPair;
import com.reandroid.utils.CompareUtil;
import com.reandroid.utils.ObjectsUtil;
import com.reandroid.utils.collection.ArrayCollection;
import com.reandroid.utils.collection.ComputeIterator;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.function.Function;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class KeyPair<T1 extends Key, T2 extends Key> implements Comparable<KeyPair<Key, Key>> {
    private T1 first;
    private T2 second;

    public KeyPair(T1 t1, T2 t2) {
        this.first = t1;
        this.second = t2;
    }

    public static <E1 extends Key, E2 extends Key> List<KeyPair<E2, E1>> flip(Collection<KeyPair<E1, E2>> collection) {
        ArrayCollection arrayCollection = new ArrayCollection(collection.size());
        arrayCollection.addAll(flip(collection.iterator()));
        return arrayCollection;
    }

    @Override // java.lang.Comparable
    public int compareTo(KeyPair<Key, Key> keyPair) {
        if (keyPair == null) {
            return -1;
        }
        Key first = getFirst();
        Key first2 = keyPair.getFirst();
        if (first == null) {
            return first2 == null ? 0 : 1;
        }
        if (first2 == null) {
            return -1;
        }
        int iCompareInnerFirst = first.getDeclaring().compareInnerFirst(first2.getDeclaring());
        return iCompareInnerFirst == 0 ? CompareUtil.compare(first, first2) : iCompareInnerFirst;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof KeyPair) {
            return ObjectsUtil.equals(getFirst(), ((KeyPair) obj).getFirst());
        }
        return false;
    }

    public boolean equalsBoth(KeyPair<?, ?> keyPair) {
        if (this == keyPair) {
            return true;
        }
        return ObjectsUtil.equals(getFirst(), keyPair.getFirst()) && ObjectsUtil.equals(getSecond(), keyPair.getSecond());
    }

    public T1 getFirst() {
        return this.first;
    }

    public T2 getSecond() {
        return this.second;
    }

    public int hashCode() {
        return ObjectsUtil.hash(getFirst());
    }

    public boolean isValid() {
        Key second;
        Key first = getFirst();
        if (first == null || (second = getSecond()) == null) {
            return false;
        }
        return !first.equals(second);
    }

    public void setFirst(T1 t1) {
        this.first = t1;
    }

    public void setSecond(T2 t2) {
        this.second = t2;
    }

    public String toString() {
        return getFirst() + "=" + getSecond();
    }

    public KeyPair(T1 t1) {
        this(t1, null);
    }

    public KeyPair() {
        this(null, null);
    }

    public static <E1 extends Key, E2 extends Key> Iterator<KeyPair<E2, E1>> flip(Iterator<KeyPair<E1, E2>> it) {
        return ComputeIterator.of(it, new Function() { // from class: i78
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return ((KeyPair) obj).flip();
            }
        });
    }

    public KeyPair<T2, T1> flip() {
        return new KeyPair<>(getSecond(), getFirst());
    }
}
