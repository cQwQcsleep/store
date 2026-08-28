package com.shadow.kotlin.enums;

import com.shadow.kotlin.collections.AbstractList;
import com.shadow.kotlin.io.CloseableKt;
import java.io.Serializable;
import java.lang.Enum;
import kotlin.collections.AbstractList;

/* loaded from: /workspace/unpacked/classes2.dex */
final class EnumEntriesList<T extends Enum<T>> extends AbstractList<T> implements kotlin.enums.EnumEntries<T>, Serializable {
    private final T[] entries;

    public EnumEntriesList(T[] tArr) {
        CloseableKt.checkNotNullParameter(tArr, "entries");
        this.entries = tArr;
    }

    private final Object writeReplace() {
        return new EnumEntriesSerializationProxy(this.entries);
    }

    public final /* bridge */ boolean contains(Object obj) {
        if (obj instanceof Enum) {
            return contains((EnumEntriesList<T>) obj);
        }
        return false;
    }

    public int getSize() {
        return this.entries.length;
    }

    public final /* bridge */ int indexOf(Object obj) {
        if (obj instanceof Enum) {
            return indexOf((EnumEntriesList<T>) obj);
        }
        return -1;
    }

    public final /* bridge */ int lastIndexOf(Object obj) {
        if (obj instanceof Enum) {
            return lastIndexOf((EnumEntriesList<T>) obj);
        }
        return -1;
    }

    public boolean contains(T t) {
        CloseableKt.checkNotNullParameter(t, "element");
        T[] tArr = this.entries;
        int iOrdinal = t.ordinal();
        CloseableKt.checkNotNullParameter(tArr, "<this>");
        return ((iOrdinal < 0 || iOrdinal > tArr.length - 1) ? null : tArr[iOrdinal]) == t;
    }

    /* renamed from: get, reason: merged with bridge method [inline-methods] */
    public T m12get(int i) {
        AbstractList.Companion companion = com.shadow.kotlin.collections.AbstractList.Companion;
        int length = this.entries.length;
        companion.getClass();
        AbstractList.Companion.checkElementIndex$kotlin_stdlib(i, length);
        return this.entries[i];
    }

    public int indexOf(T t) {
        CloseableKt.checkNotNullParameter(t, "element");
        int iOrdinal = t.ordinal();
        T[] tArr = this.entries;
        CloseableKt.checkNotNullParameter(tArr, "<this>");
        if (((iOrdinal < 0 || iOrdinal > tArr.length + (-1)) ? null : tArr[iOrdinal]) == t) {
            return iOrdinal;
        }
        return -1;
    }

    public int lastIndexOf(T t) {
        CloseableKt.checkNotNullParameter(t, "element");
        return indexOf((Object) t);
    }
}
